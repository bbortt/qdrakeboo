const passport = require('passport');
const Auth0Strategy = require('passport-auth0');

const dotenv = require('dotenv');
dotenv.config();

const configurePassport = () => {
  const passportStrategy = new Auth0Strategy(
      {
        domain: process.env.AUTH0_DOMAIN,
        clientID: process.env.AUTH0_CLIENT_ID,
        clientSecret: process.env.AUTH0_CLIENT_SECRET,
        callbackURL:
            process.env.AUTH0_CALLBACK_URL || 'http://localhost:3000/callback'
      },
      (accessToken, refreshToken, extraParams, profile, done) => {
        return done(null, profile);
      }
  );

  passport.use(passportStrategy);

  passport.serializeUser((user, done) => {
    done(null, user);
  });

  passport.deserializeUser((user, done) => {
    done(null, user);
  });

  return passport;
};

module.exports = {
  configurePassport
};
