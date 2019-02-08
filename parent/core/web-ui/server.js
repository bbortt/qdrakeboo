const express = require('express')
const session = require('express-session')

const next = require('next')
const config = require('./next.config')

const ClientOAuth2 = require('client-oauth2')

const dev = process.env.NODE_ENV !== 'production'
const app = next({dev})

const serverRuntimeConfig = config.serverRuntimeConfig

const sessionConfig = {
  secret: serverRuntimeConfig.cookieSecret,
  cookie: {}
}

if (!dev) {
  app.set('trust proxy', 1)
  sessionConfig.cookie.secure = true
}

app.use(session(sessionConfig))

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

  server.get('/', (req, res) => {
    if (req.session.auth) {
      return res.redirect('/home')
    }

    return handle(req, res)
  })

  server.get('/login', (req, res) => {
    oauth2Client.code.getToken(req.originalUrl)
    .then((user) => {
      req.session.auth = user.data
      return res.redirect('/home')
    })
    .catch((error) => res.redirect(oauth2Client.code.getUri()))
  })

  server.get('/logout', (req, res) => {
    req.session.destroy()
    return res.redirect(serverRuntimeConfig.logoutUri)
  })

  server.get('*', (req, res) => {
    if (!req.session.auth) {
      return res.redirect('/login')
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
