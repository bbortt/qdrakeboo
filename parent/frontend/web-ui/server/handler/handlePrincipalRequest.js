// @node js (server.js)
const fetch = require('node-fetch')

const config = require('../../next.config')
const serverRuntimeConfig = config.serverRuntimeConfig

const sessionUtils = require('../security/session-utils')

const unauthenticated = (res) => {
  return res.status(401)
}

module.exports = async (req, res) => {
  const token = sessionUtils.getTokenFromSession(req.session)

  if (!token) {
    return unauthenticated(res)
  }

  const apiUrl = `${serverRuntimeConfig.apiUrl}/microservice/principal`

  try {
    const response = await fetch(apiUrl, {
      method: 'GET',
      headers: {
        'Authorization': `${token.tokenType} ${token.accessToken}`
      }
    })

    if (response.status && response.status === 401) {
      return unauthenticated(res)
    }

    res.status(response.status)
    res.setHeader('content-type', response.headers.get('content-type'))
    res.end(JSON.stringify(await response.json()))
  } catch (error) {
    // TODO: Use a logger
    console.log(`error fetching ${apiUrl}: ${error}`)

    unauthenticated(res)
  }
}
