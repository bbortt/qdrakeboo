const express = require('express')

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

let auth = true

app.prepare()
  .then(() => {
    const server = express()

    server.get('/', (req, res) => {
      oauth2Client.code.getToken(req.originalUrl)
        .then((user) => {
          console.log('no worries - i got you #LOL')
          auth = user.data
          return handle(req, res)
        })
        .catch((error) => {
          return handle(req, res)
        })
    })

    server.get('/login', (req, res) => {
      res.redirect(oauth2Client.code.getUri())
    })

    server.get('/logout', (req, res) => {
      auth = false
      return handle(req, res)
    })

    server.get('/auth', (req, res) => {
      if (!auth) {
        res.sendStatus(401)
      } else {
        res.json(auth)
      }
    })

    server.get('*', (req, res) => {
      return handle(req, res)
    })

    server.listen(3000, (err) => {
      if (err) throw err
      console.log('> Ready on http://localhost:3000')
    })
  })
  .catch((ex) => {
    console.error(ex.stack)
    process.exit(1)
  })
