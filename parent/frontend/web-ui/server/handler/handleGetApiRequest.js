// @node js (server.js)
const fetch = require('node-fetch')

const config = require('../../next.config')
const serverRuntimeConfig = config.serverRuntimeConfig

const ClientOAuth2 = require('client-oauth2')
const oauth2Client = new ClientOAuth2({
  clientId: serverRuntimeConfig.clientId,
  clientSecret: serverRuntimeConfig.clientSecret,
  accessTokenUri: serverRuntimeConfig.accessTokenUri,
  authorizationUri: serverRuntimeConfig.authorizationUri,
  redirectUri: serverRuntimeConfig.redirectUri,
  scopes: serverRuntimeConfig.scopes
})

const sessionUtils = require('../security/session-utils')

module.exports = async (req, res) => {
  const token = sessionUtils.getTokenFromSession(req.session, oauth2Client)

  if (!token) {
    return res.redirect('/session')
  }

  try {
    const response = await fetch(
        `${serverRuntimeConfig.apiUrl}${req.originalUrl.replace('/api', '')}`,
        {
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
    res.redirect('/error')
  }
}
