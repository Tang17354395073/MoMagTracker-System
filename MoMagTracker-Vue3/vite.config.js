import { defineConfig, loadEnv } from 'vite'
import path from 'path'
import createVitePlugins from './vite/plugins'

const baseUrl = 'http://localhost:8080' // 后端接口

// https://vitejs.dev/config/
export default defineConfig(({ mode, command }) => {
  const env = loadEnv(mode, process.cwd())
  const { VITE_APP_ENV } = env
  
  return {
    // 部署生产环境和开发环境下的URL。
    base: VITE_APP_ENV === 'production' ? '/' : '/',
    plugins: createVitePlugins(env, command === 'build'),
    resolve: {
      // https://cn.vitejs.dev/config/#resolve-alias
      alias: {
        // 设置路径
        '~': path.resolve(__dirname, './'),
        // 设置别名
        '@': path.resolve(__dirname, './src')
      },
      // https://cn.vitejs.dev/config/#resolve-extensions
      extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
    },
    // 打包配置
    build: {
      // https://vite.dev/config/build-options.html
      sourcemap: command === 'build' ? false : 'inline',
      outDir: 'dist',
      assetsDir: 'assets',
      chunkSizeWarningLimit: 2000,
      rollupOptions: {
        output: {
          chunkFileNames: 'static/js/[name]-[hash].js',
          entryFileNames: 'static/js/[name]-[hash].js',
          assetFileNames: 'static/[ext]/[name]-[hash].[ext]'
        }
      }
    },
    // vite 相关配置
    server: {
      port: 81,
      host: true,
      open: true,
      proxy: {
        // https://cn.vitejs.dev/config/#server-proxy
        // 开发环境代理配置
        '/dev-api': {
          target: baseUrl,
          changeOrigin: true,
          rewrite: (p) => p.replace(/^\/dev-api/, '')
        },
        
        // Dify代理配置（开发环境）
        '/dify-api': {
          target: 'http://8.141.94.106:21777',
          changeOrigin: true,
          rewrite: (p) => p.replace(/^\/dify-api/, ''),
          configure: (proxy, options) => {
            // 移除或修改X-Frame-Options头（开发环境）
            proxy.on('proxyRes', (proxyRes, req, res) => {
              // 移除原有的X-Frame-Options头
              delete proxyRes.headers['x-frame-options']
              delete proxyRes.headers['X-Frame-Options']
              
              // 添加允许同源访问的头
              res.setHeader('X-Frame-Options', 'SAMEORIGIN')
              res.setHeader('Content-Security-Policy', "frame-ancestors 'self' http://localhost:*")
            })
          }
        },
        
        // Dify的静态资源代理
        '^/assets/': {
          target: 'http://8.141.94.106:21777',
          changeOrigin: true,
          configure: (proxy, options) => {
            proxy.on('proxyRes', (proxyRes, req, res) => {
              delete proxyRes.headers['x-frame-options']
              delete proxyRes.headers['X-Frame-Options']
            })
          }
        },
        
        // Dify的API路径代理
        '^/api/': {
          target: 'http://8.141.94.106:21777',
          changeOrigin: true,
          configure: (proxy, options) => {
            proxy.on('proxyRes', (proxyRes, req, res) => {
              delete proxyRes.headers['x-frame-options']
              delete proxyRes.headers['X-Frame-Options']
            })
          }
        },
        
        // Dify的控制台API代理
        '^/console/': {
          target: 'http://localhost:80',
          changeOrigin: true,
          configure: (proxy, options) => {
            proxy.on('proxyRes', (proxyRes, req, res) => {
              delete proxyRes.headers['x-frame-options']
              delete proxyRes.headers['X-Frame-Options']
            })
          }
        },
        
        // springdoc proxy
        '^/v3/api-docs/(.*)': {
          target: baseUrl,
          changeOrigin: true,
        }
      }
    },
    css: {
      postcss: {
        plugins: [
          {
            postcssPlugin: 'internal:charset-removal',
            AtRule: {
              charset: (atRule) => {
                if (atRule.name === 'charset') {
                  atRule.remove()
                }
              }
            }
          }
        ]
      }
    }
  }
})
