require('dotenv').config({ path: process.env.DOTENV_FILE || '.env' })

const webpack = require('webpack')
const withSass = require('@zeit/next-sass')

module.exports = {
  serverRuntimeConfig: {
    auth0: {
      domain: process.env.AUTH0_DOMAIN,
      clientID: process.env.AUTH0_CLIENT_ID,
      clientSecret: process.env.AUTH0_CLIENT_SECRET,
      callbackURL: process.env.CALLBACK_URL || 'http://localhost:3000/callback',
    },
    api: {
      audience: process.env.API_AUDIENCE,
    },
    logoutRedirect:
      process.env.LOGOUT_REDIRECT || 'http://localhost:3000/goodbye',
  },
  publicRuntimeConfig: {
    apiForwardToHeaderName:
      process.env.API_FORWARD_TO_HEADER_NAME ||
      'qdrakeboo-api-proxy-request-to',
    apiUrl: process.env.API_URL || 'http://localhost:8080',
    publicUrl: process.env.PUBLIC_URL || 'http://localhost:3000',
  },
  ...withSass({
    webpack(config) {
      config.plugins.push(
        new webpack.ProvidePlugin({
          $: 'jquery',
          jQuery: 'jquery',
        }),
      )

      return config
    },
  }),
}
