const express = require('express')

const next = require('next')
const config = require('./next.config')

const ClientOAuth2 = require('client-oauth2')

const cookieParser = require('cookie-parser')
const storeTokenCookie = require(
    './lib/security/store-token-cookie').storeTokenCookie
const TOKEN_COOKIE_NAME = require(
    './lib/security/security.constants').TOKEN_COOKIE_NAME

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

  server.get('/session', (req, res) => {
    return storeTokenCookie(
        () => oauth2Client.code.getToken(req.originalUrl),
        res, '/home', oauth2Client)
  })

  server.get('/session/renew', (req, res) => {
    const token = req.cookies[TOKEN_COOKIE_NAME]

    if (!token) {
      return res.redirect('/session')
    }

    return storeTokenCookie(
        () =>
            oauth2Client.createToken(
                token.access_token,
                token.refresh_token,
                token.token_type,
                {}).refresh(),
        res, req.query.redirect, oauth2Client)
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
