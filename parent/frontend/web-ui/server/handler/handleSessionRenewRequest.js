// @node js (server.js)
const sessionUtils = require('../security/session-utils')
const getDateWithTimezoneOffset = require(
    '../date/getDateWithTimezoneOffset')

module.exports = (req, res) => {
  if (!req.session.token || req.session.token.expires
      > getDateWithTimezoneOffset().getTime()) {
    return res.redirect('/session')
  }

  sessionUtils.getTokenFromSession(req.session)
  .refresh()
  .then((token) => {
    sessionUtils.saveTokenToSession(token, req.session)

    res.redirect(req.query.redirect ? req.query.redirect : '/home')
  }, () => res.redirect('/session'))
}
