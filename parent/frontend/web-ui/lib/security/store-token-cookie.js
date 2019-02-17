// @node js (server.js)
const TOKEN_COOKIE_NAME = require('./security.constants').TOKEN_COOKIE_NAME

const getDateWithTimezoneOffset = require(
  '../date/get-date-with-timezone-offset').getDateWithTimezoneOffset

module.exports.storeTokenCookie = (tokenRequest, res, redirectUrl,
                                   oauth2Client) => {
  tokenRequest().then((response) => {
    res.cookie(TOKEN_COOKIE_NAME, JSON.stringify(
      {
        token_type: response.data.token_type,
        access_token: response.data.access_token,
        refresh_token: response.data.refresh_token,
        expires: getDateWithTimezoneOffset.getTime()
          + (response.data.expires_in /* seconds */ * 1000)
      }))
    return res.redirect(redirectUrl)
  })
    .catch(() => res.redirect(oauth2Client.code.getUri()))
}
