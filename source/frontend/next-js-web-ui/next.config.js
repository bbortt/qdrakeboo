require('dotenv').config()

const webpack = require('webpack')
const withSass = require('@zeit/next-sass')

const serverRuntimeConfig = require('./server-runtime.config')

module.exports = {
  serverRuntimeConfig: {
    ...serverRuntimeConfig,
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
