const express = require('express')

const cookieParser = require('cookie-parser')
const TOKEN_COOKIE_NAME = require(
    './lib/security/security.constants').TOKEN_COOKIE_NAME

const next = require('next')
const config = require('./next.config')

const ClientOAuth2 = require('client-oauth2')

const dev = process.env.NODE_ENV !== 'production'
const app = next({dev})

const serverRuntimeConfig = config.serverRuntimeConfig

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

  server.use(cookieParser())

  server.get('/', (req, res) => {
    return handle(req, res)
  })

  server.get('/login', (req, res) => {
    oauth2Client.code.getToken(req.originalUrl)
    .then((response) => {
      res.cookie(TOKEN_COOKIE_NAME, JSON.stringify(response.data))
      return res.redirect('/home')
    })
    .catch(() => res.redirect(oauth2Client.code.getUri()))
  })

  server.get('/logout', (req, res) => {
    res.clearCookie(TOKEN_COOKIE_NAME)
    return res.redirect(serverRuntimeConfig.logoutUri)
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
