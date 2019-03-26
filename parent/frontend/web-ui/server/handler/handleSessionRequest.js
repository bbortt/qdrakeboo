// @node js (server.js)
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
  oauth2Client.code.getToken(req.originalUrl).then((token) => {
    sessionUtils.saveTokenToSession(token, req.session)

    return res.redirect('/home')
  }, () => res.redirect(oauth2Client.code.getUri()))
}
