const express = require('express')

const next = require('next')

const { configureActuator } = require('./server/config/actuator.config')
const { configurePassport } = require('./server/config/passport.config')
const { configureSession } = require('./server/config/session.config')

const secured = require('./server/middleware/secured.middleware')
const unsecured = require('./server/middleware/unsecured.middleware')

const dev = process.env.NODE_ENV !== 'production'
const app = next({ dev })

const applicationName = process.env.APP_NAME || 'Next.js Webapplication'

app.prepare().then(() => {
  const logger = require('./server/logging/logger')
  const { bindContextfulMiddleware } = require('contextful-winston-logger')
  logger.info(`Starting ${applicationName}..`)

  const server = express()
  const port = process.env.PORT || 3000
  const handle = app.getRequestHandler()

  const passport = configurePassport()

  server.use(configureSession(dev))
  server.use(passport.initialize())
  server.use(passport.session())
  server.use(bindContextfulMiddleware(logger))
  server.use(configureActuator())

  const apiRouter = require('./server/router/api.router')
  const authRouter = require('./server/router/auth.router')
  const userRouter = require('./server/router/user.router')

  server.use('/', apiRouter)
  server.use('/', authRouter)
  server.use('/', userRouter)

  server.get('/', unsecured(handle))
  server.get('/actuator', unsecured(handle))
  server.get('/goodbye', unsecured(handle))

  server.get('/_next/*', (req, res) => handle(req, res))
  server.get('/favicon.ico', (req, res) => handle(req, res))

  server.get('*', secured(), (req, res) => {
    const { _raw, _json, ...userProfile } = req.user
    return app.render(req, res, req.path, { userInfo: userProfile })
  })

  server.listen({ port }, err => {
    if (err) {
      throw err
    }

    logger.info(`🚀 Server ready at http://localhost:${port}`)
  })
})
