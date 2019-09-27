const path = require('path');

const nodeExternals = require('webpack-node-externals');

module.exports = {
  entry: './gateway.js',
  output: {
    filename: 'apollo-federation-express-gateway.js',
    path: path.resolve(__dirname, 'dist'),
  },
  target: 'node',
  mode: 'production',
  externals: [nodeExternals()],
};
