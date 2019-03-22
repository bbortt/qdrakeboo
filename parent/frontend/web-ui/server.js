const express = require('express')
const session = require('express-session')
const async = require('express-async-await')

const fetch = require('node-fetch')

const next = require('next')
const config = require('./next.config')

const ClientOAuth2 = require('client-oauth2')

const sessionUtils = require('./lib/security/session-utils')
const getDateWithTimezoneOffset = require('./lib/date/get-date-with-timezone-offset')

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
    if (req.session.token) {
      return res.redirect('/home')
    }

    return handle(req, res)
  })

  server.get('/session', (req, res) => {
    oauth2Client.code.getToken(req.originalUrl).then((token) => {
      sessionUtils.saveTokenToSession(token, req.session)

      return res.redirect('/home')
    }, () => res.redirect(oauth2Client.code.getUri()))
  })

  server.get('/session/renew', (req, res) => {
    if (!req.session.token || req.session.token.expires > getDateWithTimezoneOffset().getTime()) {
      return res.redirect('/session')
    }

    const oldToken = sessionUtils.getTokenFromSession(req.session, oauth2Client)
      .refresh()
      .then((token) => {
        sessionUtils.saveTokenToSession(token, req.session)

        res.redirect(req.query.redirect)
      }, () => res.redirect('/session'))
  })

  server.get('/logout', (req, res) => {
    req.session.destroy((error) => {
      // TODO: Handle error?

      return res.redirect(serverRuntimeConfig.logoutUri)
    })
  })

  server.get('/api/:endpoint', async (req, res) => {
    const token = sessionUtils.getTokenFromSession(req.session, oauth2Client)

    const response = await fetch(`${serverRuntimeConfig.apiUrl}/${req.params.endpoint}`, {
      method: 'GET',
      headers: {
        'Authorization': `${token.tokenType} ${token.accessToken}`
      }
    })

    return await response.json()
  });

  server.get('*', (req, res) => {
    if (!sessionUtils.getTokenFromSession(req.session, oauth2Client)) {
      return res.redirect('/session')
    }

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
