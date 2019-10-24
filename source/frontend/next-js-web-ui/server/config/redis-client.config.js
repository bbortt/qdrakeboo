const redis = require('redis')

const logger = require('../logging/logger')

const configureRedisClient = () => {
  const redisClient = redis.createClient({
    url: `${process.env.REDIS_PROTOCOL || 'redisClient'}://${
      process.env.REDIS_USER
    }:${process.env.REDIS_PASSWORD}@${process.env.REDIS_HOST}:${
      process.env.REDIS_PORT
    }/${process.env.REDIS_DB || ''}`,
  })

  redisClient.auth(process.env.REDIS_PASSWORD)

  redisClient.on('error', error => {
    logger.error(error)
  })

  return redisClient
}

module.exports = {
  configureRedisClient,
}
