const path = require('path');

module.exports = {
  mode: 'production',
  entry: './client/index.js',
  optimization: {
    minimize: true
  },
  plugins: [ ],
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'static/assets/js'),
  },
  module: {
    rules: [
    ],
  },
};