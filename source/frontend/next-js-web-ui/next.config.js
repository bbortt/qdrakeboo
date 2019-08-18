require('dotenv').config()

const path = require('path')

const webpack = require('webpack')
const withSass = require('@zeit/next-sass')

const Dotenv = require('dotenv-webpack')

const serverRuntimeConfig = require('./server-runtime.config')

module.exports = {
  env: {
    API_FORWARD_TO_HEADER_NAME: 'qdrakeboo-api-proxy-request-to',
    API_URL: process.env.API_URL || 'http://localhost:8080',
    PUBLIC_URL: process.env.PUBLIC_URL || 'http://localhost:3000',
  },
  serverRuntimeConfig: {
    ...serverRuntimeConfig,
  },
  ...withSass({
    webpack(config) {
      config.plugins.push(
        new Dotenv({
          path: path.join(__dirname, '.env'),
          systemvars: true,
        }),
      )

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
