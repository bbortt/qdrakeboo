const express = require('express')
const router = express.Router()

const passport = require('passport')

const url = require('url')
const util = require('util')
const querystring = require('querystring')

const secured = require('../middleware/secured.middleware')

const logger = require('../logging/logger')

router.get(
  '/login',
  passport.authenticate('auth0', {
    scope: 'openid profile email',
    audience: process.env.API_AUDIENCE,
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

    req.logIn(info, err => {
      if (err) {
        return next(err)
      }

      const returnTo = req.session.returnTo
      delete req.session.returnTo

      res.redirect(returnTo || '/home')
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
        util.format('https://%s/v2/logout', process.env.AUTH0_DOMAIN)
      )

      logoutUrl.search = querystring.stringify({
        client_id: process.env.AUTH0_CLIENT_ID,
        returnTo:
          process.env.LOGOUT_REDIRECT || 'http://localhost:3000/goodbye',
      })

      res.redirect(url.format(logoutUrl))
    })
  }
})

module.exports = router
