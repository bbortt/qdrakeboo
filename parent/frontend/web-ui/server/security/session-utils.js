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

module.exports.getTokenFromSession = (session) => {
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

module.exports.saveTokenToSession = (token, session) => {
  session.token = {
    tokenType: token.tokenType,
    accessToken: token.accessToken,
    refreshToken: token.refreshToken,
    expires: getDateWithTimezoneOffset().getTime()
      + (token.expires /* seconds */ * 1000)
  }

  session.expires = session.token.expires
}
