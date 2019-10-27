const webpack = require('webpack')
const withSass = require('@zeit/next-sass')

module.exports = {
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
