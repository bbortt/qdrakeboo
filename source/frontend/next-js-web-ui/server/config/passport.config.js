const passport = require('passport')
const Auth0Strategy = require('passport-auth0')

const { serverRuntimeConfig } = require('../../next.config')

const configurePassport = () => {
  const passportStrategy = new Auth0Strategy(
    {
      domain: serverRuntimeConfig.auth0.domain,
      clientID: serverRuntimeConfig.auth0.clientId,
      clientSecret: serverRuntimeConfig.auth0.clientSecret,
      callbackURL:
        serverRuntimeConfig.auth0.callbackURL ||
        'http://localhost:3000/callback',
    },
    (accessToken, refreshToken, extraParams, profile, done) => {
      return done(
        null,
        {
          accessToken,
          refreshToken,
          idToken: extraParams.id_token,
        },
        profile
      )
    }
  )

  passport.use(passportStrategy)

  passport.serializeUser((user, done) => {
    done(null, user)
  })

  passport.deserializeUser((user, done) => {
    done(null, user)
  })

  return passport
}

module.exports = {
  configurePassport,
}
