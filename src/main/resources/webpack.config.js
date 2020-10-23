const path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
  mode: 'production',
  entry: './client/index.js',
  optimization: {
    minimize: true
  },
  plugins: [
    new HtmlWebpackPlugin({
      filename: "index.html",
      template: "./client/index.html",
      minify: false
    }),
    new MiniCssExtractPlugin({
      filename: "xterm.css",
    }),
  ],
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist'),
  },
  module: {
    rules: [
      {
        test: /\.html$/i,
        use: [
          "html-loader?minimize=false",
        ]
      },
      {
        test: /\.css$/i,
        use: [MiniCssExtractPlugin.loader, 'css-loader'],
      },
    ],
  },
};