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
const getDateWithTimezoneOffset = require(
    './server/date/getDateWithTimezoneOffset')

module.exports = async (req, res) => {
  if (!req.session.token || req.session.token.expires
      > getDateWithTimezoneOffset().getTime()) {
    return res.redirect('/session')
  }

  sessionUtils.getTokenFromSession(req.session, oauth2Client)
  .refresh()
  .then((token) => {
    sessionUtils.saveTokenToSession(token, req.session)

    res.redirect(req.query.redirect ? req.query.redirect : '/home')
  }, () => res.redirect('/session'))
}
