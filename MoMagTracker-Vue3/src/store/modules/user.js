import { defineStore } from 'pinia'
import router from '@/router'
import { ElMessageBox, } from 'element-plus'
import { emailLogin as apiEmailLogin, login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { isHttp, isEmpty } from "@/utils/validate"
import defAva from '@/assets/images/readme.jpg'

const useUserStore = defineStore(
  'user',
  {
    state: () => ({
      token: getToken(),
      id: '',
      name: '',
      nickName: '',
      avatar: '',
      roles: [],
      permissions: []
    }),
    actions: {
      // 登录
      login(userInfo) {
        console.log('=== 调用 userStore.login ===')
        console.log('接收到的用户信息:', {
          username: userInfo.username,
          password: '***', // 不打印真实密码
          code: userInfo.code,
          uuid: userInfo.uuid
        })
        
        const username = userInfo.username.trim()
        const password = userInfo.password
        const code = userInfo.code
        const uuid = userInfo.uuid

        return new Promise((resolve, reject) => {
          login(username, password, code, uuid).then(res => {
            console.log('✅ 登录API调用成功，响应:', res)

            // 存储 token 到本地
            setToken(res.token)
            this.token = res.token
            // 登录成功后自动获取用户信息
            this.getInfo().then(() => {
              resolve()
            }).catch(error => {
              // 即使获取用户信息失败，也认为登录成功
              console.error('获取用户信息失败:', error)
              resolve()
            })
          }).catch(error => {
            console.error('❌ 登录API调用失败:')
            console.error('错误对象:', error)
            console.error('错误信息:', error.message)
            console.error('响应数据:', error.response?.data)
            console.error('状态码:', error.response?.status)
            
            reject(error)
          })
        })
      },
      // 邮箱登录
      emailLogin(userInfo) { 
        console.log('=== 调用 userStore.login ===')
        console.log('接收到的用户信息:', {
          email: userInfo.email,
          emailCode: userInfo.emailCode,
          uuid: userInfo.uuid
        })

        const email = userInfo.email.trim()
        const emailCode = userInfo.emailCode
        const uuid = userInfo.uuid

        return new Promise((resolve, reject) => {
          apiEmailLogin(email, emailCode, uuid).then(res => {
            setToken(res.token)
            this.token = res.token 
            // 登录成功后自动获取用户信息
            this.getInfo().then(() => {
              resolve()
            }).catch(error => {
              // 即使获取用户信息失败，也认为登录成功
              console.error('获取用户信息失败:', error)
              resolve()
            })
          }).catch(error => {
            reject(error)
          })
        })
      },
      // 获取用户信息
      getInfo() {
        return new Promise((resolve, reject) => {
          getInfo().then(res => {
            console.log('=== 获取用户信息响应 ===')
            console.log('用户信息:', res.user)
            console.log('角色信息:', res.roles)
            console.log('权限信息:', res.permissions)

            const user = res.user

            // 头像处理逻辑
            let avatar = user.avatar || ""
            if (!isHttp(avatar)) {
              avatar = (isEmpty(avatar)) ? defAva : import.meta.env.VITE_APP_BASE_API + avatar
            }

            // 角色和权限处理
            if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
              this.roles = res.roles
              this.permissions = res.permissions
              console.log('✅ 设置用户角色:', this.roles)
            } else {
              this.roles = ['ROLE_DEFAULT']
              console.warn('⚠️ 用户没有分配角色，使用默认角色')
            }

            // 设置用户基本信息
            this.id = user.userId
            this.name = user.userName
            this.nickName = user.nickName
            this.avatar = avatar
            console.log('✅ 用户Store状态更新完成')

            /* 初始密码提示 */
            if(res.isDefaultModifyPwd) {
              ElMessageBox.confirm('您的密码还是初始密码，请修改密码！',  '安全提示', {  confirmButtonText: '确定',  cancelButtonText: '取消',  type: 'warning' }).then(() => {
                router.push({ name: 'Profile', params: { activeTab: 'resetPwd' } })
              }).catch(() => {})
            }
            /* 过期密码提示 */
            if(!res.isDefaultModifyPwd && res.isPasswordExpired) {
              ElMessageBox.confirm('您的密码已过期，请尽快修改密码！',  '安全提示', {  confirmButtonText: '确定',  cancelButtonText: '取消',  type: 'warning' }).then(() => {
                router.push({ name: 'Profile', params: { activeTab: 'resetPwd' } })
              }).catch(() => {})
            }
            resolve(res)
          }).catch(error => {
            console.error('❌ 获取用户信息失败:', error)
            reject(error)
          })
        })
      },
      // 退出系统
      logOut() {
        return new Promise((resolve, reject) => {
          logout(this.token).then(() => {
            this.token = ''
            this.roles = []
            this.permissions = []
            // 清除本地token
            removeToken()
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      }
    }
  })

export default useUserStore
