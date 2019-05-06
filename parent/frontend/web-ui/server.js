const express = require('express')
const session = require('express-session')

const next = require('next')
const config = require('./next.config')

const sessionUtils = require('./server/security/session-utils')

const handleSessionRequest = require('./server/handler/handleSessionRequest.js')
const handleSessionRenewRequest = require(
  './server/handler/handleSessionRenewRequest.js')
const handleGetApiRequest = require('./server/handler/handleGetApiRequest')

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

if (!dev) {
  const redis = require('redis').createClient();
  const RedisStore = require('connect-redis')(session)
  sessionConfig.store = new RedisStore({client: redis})
}

const handle = app.getRequestHandler()

app.prepare().then(() => {
  const server = express()

  if (!dev) {
    server.set('trust proxy', 1)
    sessionConfig.cookie.secure = true
  }

  server.use(session(sessionConfig))

  server.get('/', async (req, res) => handle(req, res))

  server.get('/session', (req, res) => handleSessionRequest(req, res))

  server.get('/session/check', async (req, res) => await sessionUtils.isAuthenticated(req, res))

  server.get('/session/renew',
    (req, res) => handleSessionRenewRequest(req, res))

  server.get('/logout', (req, res) => req.session.destroy((error) => {
    // TODO: Handle error?

    return res.redirect(serverRuntimeConfig.logoutUri)
  }))

  server.get('/api/*', async (req, res) => await handleGetApiRequest(req, res))

  server.get('*', async (req, res) => {
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
