const path = require("path");

var NODE_DIR = path.resolve(__dirname, 'node_modules');
var BUILD_DIR = path.resolve(__dirname, 'public');
var APP_DIR = path.resolve(__dirname, 'source');

var config = {
  mode: 'production',
  entry: './index.js',
  output: {
    path: BUILD_DIR,
    filename: 'bundle.js'
  },
  target: 'node',
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: [NODE_DIR],
        include: [APP_DIR],
        loader: 'babel-loader',
        query: {
          presets: [
            '@babel/preset-env'
          ]
        }
      }
    ]
  }
};

module.exports = config;
