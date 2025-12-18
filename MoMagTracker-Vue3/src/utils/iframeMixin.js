import { getAutoToken } from '@/api/intelligentAgentFactory'
import { ElMessage } from 'element-plus'

export default {
  data() {
    return {
      iframeUrl: '',
    }
  },
  methods: {
    /**
     * 从API获取token并将其存储在sessionStorage中。
     * 返回一个在成功时解析token或在失败时拒绝的promise。
     */
    fetchAndStoreToken() {
      console.log('开始获取Dify令牌...')
      return new Promise((resolve, reject) => {
        getAutoToken().then((res) => {
          console.log('Dify令牌响应:', res)
          
          let accessToken, refreshToken;
          
          // 情况1: 标准返回格式
          if (res.code === 200 && res.data) {
            console.log('响应格式: 标准返回格式')
            accessToken = res.data.access_token || res.data.accessToken;
            refreshToken = res.data.refresh_token || res.data.refreshToken;
          } 
          // 情况2: 直接返回token对象
          else if (res.data && (res.data.access_token || res.data.accessToken)) {
            console.log('响应格式: 直接token格式')
            accessToken = res.data.access_token || res.data.accessToken;
            refreshToken = res.data.refresh_token || res.data.refreshToken;
          }
          // 情况3: 其他格式
          else if (res.access_token || res.accessToken) {
            console.log('响应格式: 简单对象格式')
            accessToken = res.access_token || res.accessToken;
            refreshToken = res.refresh_token || res.refreshToken;
          }
          
          console.log('解析出的token:', { accessToken, refreshToken })
          
          if (accessToken && refreshToken) {
            const tokens = {
              consoleToken: accessToken,
              difyToken: refreshToken,
            }
            sessionStorage.setItem('consoletoken', tokens.consoleToken)
            sessionStorage.setItem('refreshtoken', tokens.difyToken)
            console.log('Dify令牌已存储到sessionStorage')
            resolve(tokens)
          } else {
            ElMessage({
              message: '获取Dify令牌失败，请检查配置！',
              type: 'error',
            })
            console.error('获取Dify令牌失败，响应数据:', res)
            reject(new Error('Token fetch failed'))
          }
        }).catch(error => {
          console.error('获取Dify令牌失败:', error)
          ElMessage({
            message: '获取Dify令牌失败：' + (error.message || '未知错误'),
            type: 'error',
          })
          reject(error)
        })
      })
    },

    /**
     * 使用存储的token构造完整的iframe URL。
     * @param {string} path - iframe的具体路径 (例如, `/app/123/workflow`).
     * @returns {string|null} - 如果token存在，则返回完整的URL；否则返回null。
     */
    getIframeUrl(path) {
      console.log('开始构建Dify iframe URL...')
      
      // Vue3中使用import.meta.env访问环境变量
      const baseUrl = import.meta.env.VITE_APP_BASE_DIFY
      console.log('Dify基础URL:', baseUrl)
      
      if (!baseUrl) {
        console.error('环境变量VITE_APP_BASE_DIFY未配置')
        ElMessage({
          message: 'Dify系统配置错误，请联系管理员',
          type: 'error',
        })
        return null
      }
      
      const consoleToken = sessionStorage.getItem('consoletoken')
      const difyToken = sessionStorage.getItem('refreshtoken')
      
      console.log('从sessionStorage获取的令牌:', { 
        consoleToken: consoleToken ? '已存在' : '不存在',
        difyToken: difyToken ? '已存在' : '不存在'
      })
      
      if (consoleToken && difyToken) {
        // 构造URL - 注意参数名称要和Dify系统匹配
        const url = `${baseUrl}${path}?console_token=${consoleToken}&refresh_token=${difyToken}&isInternalJump=false`
        console.log('构建的Dify URL:', url)
        return url
      } else {
        console.warn('Dify令牌不存在，无法构建URL')
        ElMessage({
          message: 'Dify令牌失效，请重新登录或联系管理员！',
          type: 'error',
        })
        return null
      }
    },

    /**
     * 用于获取token并立即设置iframeUrl。
     * @param {string} path - iframe的具体路径 (例如, '/apps').
     */
    async initializeIframe(path) {
      console.log('初始化Dify iframe，路径:', path)
      console.log('当前路由:', this.$route)
      
      try {
        await this.fetchAndStoreToken()
        console.log('Dify令牌获取成功，开始构建URL')
        const url = this.getIframeUrl(path)
        if (url) {
          console.log('设置iframeUrl:', url)
          this.iframeUrl = url
        } else {
          console.error('构建Dify URL失败')
        }
      } catch (error) {
        console.error('初始化Dify iframe失败:', error)
      }
    },
  },
}