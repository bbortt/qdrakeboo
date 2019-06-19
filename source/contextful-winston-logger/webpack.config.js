const path = require("path");

var NODE_DIR = path.resolve(__dirname, 'node_modules');
var BUILD_DIR = path.resolve(__dirname, 'public');
var APP_DIR = path.resolve(__dirname, 'source');

var config = {
  mode: 'production',
  entry: './index.js',
  output: {
    path: BUILD_DIR,
    filename: 'bundle.js',
    library: 'contextfulWinstonLogger',
    libraryTarget: 'umd'
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
  },
  externals: {
    'continuation-local-storage': {
      commonjs: 'continuation-local-storage',
      commonjs2: 'continuation-local-storage',
      amd: 'continuation-local-storage',
      root: '_'
    },
    'uuid': {
      commonjs: 'uuid',
      commonjs2: 'uuid',
      amd: 'uuid',
      root: '_'
    },
    'winston': {
      commonjs: 'winston',
      commonjs2: 'winston',
      amd: 'winston',
      root: '_'
    }
  }
};

module.exports = config;
