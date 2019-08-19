const express = require('express')

const passport = require('passport')

const url = require('url')
const util = require('util')
const querystring = require('querystring')
const secured = require('../middleware/secured.middleware')

const {
  api,
  auth0,
  logoutRedirect,
} = require('../../next.config').serverRuntimeConfig

const logger = require('../logging/logger')

const router = express.Router()

router.get(
  '/login',
  passport.authenticate('auth0', {
    scope: 'openid profile email',
    audience: api.audience,
  }),
  (req, res) => {
    res.redirect('/')
  }
)

router.get('/callback', (req, res, next) => {
  passport.authenticate('auth0', (err, authentication, info) => {
    if (err) {
      return next(err)
    }

    if (!authentication) {
      return res.redirect('/login')
    }

    req.session.authentication = authentication

    return req.logIn(info, error => {
      if (error) {
        return next(error)
      }

      const { returnTo } = req.session
      delete req.session.returnTo

      return res.redirect(returnTo || '/home')
    })
  })(req, res, next)
})

router.get('/logout', secured(), (req, res) => {
  req.logout()

  if (req.session) {
    req.session.destroy(error => {
      if (error) {
        logger.error(error)
      }

      const logoutUrl = url.parse(
        util.format('https://%s/v2/logout', auth0.domain)
      )

      logoutUrl.search = querystring.stringify({
        client_id: auth0.clientId,
        returnTo: logoutRedirect,
      })

      res.redirect(url.format(logoutUrl))
    })
  }
})

module.exports = router
