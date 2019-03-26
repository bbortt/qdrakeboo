// @node js (server.js)
const fetch = require('node-fetch')

const config = require('../../next.config')
const serverRuntimeConfig = config.serverRuntimeConfig

const sessionUtils = require('../security/session-utils')

module.exports = async (req, res) => {
  const token = sessionUtils.getTokenFromSession(req.session)

  if (!token) {
    return res.redirect('/session')
  }

  const apiUrl = `${serverRuntimeConfig.apiUrl}${req.originalUrl.replace('/api',
      '')}`

  try {
    const response = await fetch(apiUrl, {
      method: 'GET',
      headers: {
        'Authorization': `${token.tokenType} ${token.accessToken}`
      }
    })

    if (response.status && response.status === 401) {
      return res.redirect('/session/renew')
    }

    res.status(response.status)
    res.setHeader('content-type', response.headers.get('content-type'))
    res.end(JSON.stringify(await response.json()))
  } catch (error) {
    console.log(`error fetching ${apiUrl}: ${error}`)

    res.redirect('/error')
  }
}
