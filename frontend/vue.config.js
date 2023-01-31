const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    devServer: {
      proxy: {
          '^/app': {
              target: 'http://localhost:8080/',
              ws: false,
              changeOrigin: true
          }
      }
    },
    transpileDependencies: true,
    configureWebpack: {
        module: {
            rules: [
                {
                    test: /\.less?$/,
                    use: [
                        'less-loader'
                    ]
                }
            ]
        }
    }

})
