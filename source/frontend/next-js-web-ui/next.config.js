const webpack = require('webpack')
const withSass = require('@zeit/next-sass')

module.exports = {
  serverRuntimeConfig: {},
  publicRuntimeConfig: {},
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
