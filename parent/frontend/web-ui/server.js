const express = require('express')
const session = require('express-session')
const async = require('express-async-await')

const fetch = require('node-fetch')

const next = require('next')
const config = require('./next.config')

const ClientOAuth2 = require('client-oauth2')

const sessionUtils = require('./lib/security/session-utils')
const getDateWithTimezoneOffset = require(
    './lib/date/getDateWithTimezoneOffset')

const dev = process.env.NODE_ENV !== 'production'
const app = next({dev})

const serverRuntimeConfig = config.serverRuntimeConfig

// TODO: REDIS Store
const sessionConfig = {
  secret: serverRuntimeConfig.sessionSecret,
  cookie: {},
  resave: false,
  saveUninitialized: false
}

const oauth2Client = new ClientOAuth2({
  clientId: serverRuntimeConfig.clientId,
  clientSecret: serverRuntimeConfig.clientSecret,
  accessTokenUri: serverRuntimeConfig.accessTokenUri,
  authorizationUri: serverRuntimeConfig.authorizationUri,
  redirectUri: serverRuntimeConfig.redirectUri,
  scopes: serverRuntimeConfig.scopes
})

const handle = app.getRequestHandler()

app.prepare().then(() => {
  const server = express()

  if (!dev) {
    server.set('trust proxy', 1)
    sessionConfig.cookie.secure = true
  }

  server.use(session(sessionConfig))

  server.get('/', (req, res) => {
    return handle(req, res)
  })

  server.get('/session', (req, res) => {
    oauth2Client.code.getToken(req.originalUrl).then((token) => {
      sessionUtils.saveTokenToSession(token, req.session)

      return res.redirect('/home')
    }, () => res.redirect(oauth2Client.code.getUri()))
  })

  server.get('/session/renew', (req, res) => {
    if (!req.session.token || req.session.token.expires
        > getDateWithTimezoneOffset().getTime()) {
      return res.redirect('/session')
    }

    sessionUtils.getTokenFromSession(req.session, oauth2Client)
    .refresh()
    .then((token) => {
      sessionUtils.saveTokenToSession(token, req.session)

      res.redirect(req.query.redirect ? req.query.redirect : '/home')
    }, () => res.redirect('/session'))
  })

  server.get('/logout', (req, res) => {
    req.session.destroy((error) => {
      // TODO: Handle error?

      return res.redirect(serverRuntimeConfig.logoutUri)
    })
  })

  server.get('/api/*', async (req, res) => {
    const token = sessionUtils.getTokenFromSession(req.session, oauth2Client)

    if (!token) {
      return res.redirect('/session')
    }

    try {
      const response = await fetch(
          `${serverRuntimeConfig.apiUrl}${req.originalUrl.replace('/api', '')}`,
          {
            method: 'GET',
            headers: {
              'Authorization': `${token.tokenType} ${token.accessToken}`
            }
          })

      res.status(response.status)
      res.setHeader('content-type', response.headers.get('content-type'))
      res.end(JSON.stringify(await response.json()))
    } catch (error) {
      console.log('request error: ', error)
    }
  })

  server.get('*', (req, res) => {
    return handle(req, res)
  })

  server.listen(3000, (err) => {
    if (err) {
      throw err
    }

    console.log('> Ready on http://localhost:3000')
  })
})
.catch((ex) => {
  console.error(ex.stack)
  process.exit(1)
})
