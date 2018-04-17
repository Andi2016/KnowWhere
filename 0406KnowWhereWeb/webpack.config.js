const path = require('path');

module.exports = (env) => {
  const isProduction = env === 'production';
  console.log('env', env);
  return {
    entry: './src/app.js',
  output: {
    path: path.join(__dirname, 'public'),
    filename: 'bundle.js'
  },
  module: {
    rules: [{
      loader: 'babel-loader',
      test: /\.js$/,
      exclude: /node_modules/
    }, {
      test: /\.s?css$/,
      use: [
        'style-loader',
        'css-loader',
        'sass-loader'
      ]
    }]
  },
  devtool: isProduction ? 'source-map' : 'cheap-module-eval-source-map',
  devServer: {
    host: '0.0.0.0',
    disableHostCheck: true,
    contentBase: path.join(__dirname, 'public'),
    historyApiFallback: true,
    proxy: {
           '/api': {
             target: 'http://143.215.113.90:8080',
             changeOrigin: true,
             secure:false,
             pathRewrite:{
               '^/api':''
             }
           }
         }
          }
};

}