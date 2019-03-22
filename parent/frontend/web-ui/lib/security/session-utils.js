// @node js (server.js)

const getDateWithTimezoneOffset = require('../date/get-date-with-timezone-offset')

module.exports.getTokenFromSession = (session, oauth2Client) => {
  if (!session.token) {
    return null;
  }

  const {token} = session

  return oauth2Client.createToken(
    token.access_token,
    token.refresh_token,
    token.token_type,
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
