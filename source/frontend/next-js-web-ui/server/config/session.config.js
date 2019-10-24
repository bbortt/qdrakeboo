const session = require('express-session')
const { configureRedisClient } = require('./redis-client.config')

const sessionConfig = {
  secret: process.env.COOKIE_SECRET || 'random-secret *laughs*',
  cookie: {},
  resave: false,
  saveUninitialized: true,
}

const configureSession = dev => {
  if (!dev) {
    sessionConfig.cookie.secure = true

    const RedisStore = require('connect-redis')(session)
    sessionConfig.store = new RedisStore({ client: configureRedisClient() })
  }

  return session(sessionConfig)
}

module.exports = {
  configureSession,
}
