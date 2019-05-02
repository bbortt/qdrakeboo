// @node js (server.js)
const getDateWithTimezoneOffset = require('../date/getDateWithTimezoneOffset')

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

const getTokenFromSession = (session) => {
  if (!session.token) {
    return null
  }

  const {token} = session

  return oauth2Client.createToken(
      token.accessToken,
      token.refreshToken,
      token.tokenType,
      {})
}

const saveTokenToSession = (token, session) => {
  session.token = {
    tokenType: token.tokenType,
    accessToken: token.accessToken,
    refreshToken: token.refreshToken,
    expires: getDateWithTimezoneOffset().getTime()
        + (token.expires /* seconds */ * 1000)
  }

  session.expires = session.token.expires
}

const isAuthenticated = async (req, res) => {
  const token = getTokenFromSession(req.session)

  if (!token) {
    return false
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
      return false
    }

    return response.status === 200
  } catch (error) {
    // TODO: Use a logger
    console.log(`error fetching ${apiUrl}: ${error}`)

    return false
  }
}

module.exports = {
  getTokenFromSession,
  saveTokenToSession,
  isAuthenticated
}