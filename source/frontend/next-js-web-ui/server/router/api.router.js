const express = require('express')
const httpProxy = require('http-proxy')
const secured = require('../middleware/secured.middleware')

const logger = require('../logging/logger')

const { API_FORWARD_TO_HEADER_NAME } = process.env

const proxy = httpProxy.createProxyServer()
const router = express.Router()

proxy.on('proxyReq', (proxyReq, req) => {
  const { accessToken } = req.session.authentication
  proxyReq.setHeader('Authorization', `Bearer ${accessToken}`)
})

proxy.on('proxyRes', proxyRes => {
  logger.debug(
    `Response from API: ${JSON.stringify({ status: proxyRes.statusCode })}`,
  )
})

router.use('/api', secured(), (req, res) => {
  const requestUrl = req.get(API_FORWARD_TO_HEADER_NAME)
  if (!requestUrl) {
    logger.warn(
      `Received API request without header '${API_FORWARD_TO_HEADER_NAME}'`,
    )

    return res.status(400).end(
      JSON.stringify({
        message: `missing header '${API_FORWARD_TO_HEADER_NAME}'`,
      }),
    )
  }

  logger.info(`Proxying request to '${requestUrl}'`)

  return proxy.web(req, res, { target: requestUrl }, error => {
    logger.error(`Error while fetching '${requestUrl}': ${error.toString()}`)
    res.status(500).end(JSON.stringify({ message: error.toString() }))
  })
})

module.exports = router
