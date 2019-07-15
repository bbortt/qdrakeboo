const webpack = require('webpack')
const withSass = require('@zeit/next-sass')

const dotenv = require('dotenv');
dotenv.config();

module.exports = {
  serverRuntimeConfig: {},
  publicRuntimeConfig: {
    apiUrl: process.env.API_URL || 'http://localhost:8080'
  },
  ...withSass({
    webpack(config, options) {
      config.plugins.push(
          new webpack.ProvidePlugin({
            '$': 'jquery',
            'jQuery': 'jquery',
          })
      )
      return config
    }
  })
}
