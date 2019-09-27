const path = require('path');

const nodeExternals = require('webpack-node-externals');

module.exports = {
  entry: './server.js',
  output: {
    filename: 'federated-user-management.js',
    path: path.resolve(__dirname, 'dist'),
  },
  target: 'node',
  mode: 'production',
  externals: [nodeExternals()],
};
