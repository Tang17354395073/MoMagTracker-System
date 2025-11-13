<template>
  <div class="login-container">
    <div class="login-wrapper">
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form">
        <h3 class="title">{{ title }}</h3>

        <!-- 账号密码登录 -->
        <el-form-item prop="username" v-if="!isEmailLogin">
          <el-input
            v-model="loginForm.username"
            type="text"
            size="large"
            auto-complete="off"
            placeholder="请输入账号"
            class="custom-input"
          >
            <template #prefix><svg-icon icon-class="user" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>
        
        <el-form-item prop="password" v-if="!isEmailLogin">
          <el-input
            v-model="loginForm.password"
            type="password"
            size="large"
            auto-complete="off"
            placeholder="请输入密码"
            @keyup.enter="handleLogin"
            class="custom-input"
          >
            <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>
        
        <el-form-item prop="code" v-if="!isEmailLogin" class="code-form-item">
          <el-input
            v-model="loginForm.code"
            size="large"
            auto-complete="off"
            placeholder="验证码"
            @keyup.enter="handleLogin"
            class="code-input"
          >
            <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img"/>
          </div>
        </el-form-item>

        <!-- 邮箱登录 -->
        <el-form-item prop="email" v-if="isEmailLogin">
          <el-input 
            v-model="loginForm.email" 
            type="text" 
            size="large"
            auto-complete="off" 
            placeholder="请输入邮箱地址"
            class="custom-input"
          >
            <template #prefix><svg-icon icon-class="email" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>

        <el-form-item prop="emailCode" v-if="isEmailLogin" class="code-form-item">
          <el-input
            v-model="loginForm.emailCode"
            size="large"
            auto-complete="off"
            placeholder="邮箱验证码"
            @keyup.enter="handleLogin"
            class="code-input"
          >
            <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
          </el-input>
          <div class="email-code-btn">
            <el-button 
              round 
              @click.prevent="getEmailCode"
              :disabled="computeTime > 0"
              class="email-btn"
            >
              {{ computeTime > 0 ? `已发送(${computeTime}s)` : '获取验证码' }}
            </el-button>
          </div>
        </el-form-item>

        <!-- 底部操作区域 -->
        <div class="form-actions">
          <div class="left-actions">
            <el-checkbox v-model="loginForm.rememberMe" class="remember-me">记住密码</el-checkbox>
          </div>
          <div class="right-actions">
            <el-button
              size="small"
              type="primary"
              link
              @click.prevent="loginMethod"
              class="login-method-btn"
            >
              <span v-if="isEmailLogin">账号密码登录</span>
              <span v-else>邮箱登录</span>
            </el-button>
          </div>
        </div>
        
        <el-form-item class="login-btn-item">
          <el-button
            :loading="loading"
            size="large"
            type="primary"
            @click.prevent="handleLogin"
            class="login-btn"
          >
            <span v-if="!loading">{{ isEmailLogin ? '邮箱登录' : '账号登录' }}</span>
            <span v-else>{{ isEmailLogin ? '邮箱登录中...' : '账号登录中...' }}</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    
    <!-- 底部版权信息 -->
    <div class="login-footer">
      <span>Copyright © 2023-2026 MoMagTracker All Rights Reserved.</span>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getCodeImg, getEmailCode as apiGetEmailCode } from "@/api/login"
import Cookies from "js-cookie"
import { encrypt, decrypt } from "@/utils/jsencrypt"
import useUserStore from '@/store/modules/user'
import usePermissionStore from '@/store/modules/permission'
import { ElMessage } from 'element-plus'

const title = import.meta.env.VITE_APP_TITLE
const userStore = useUserStore()
const permissionStore = usePermissionStore()
const route = useRoute()
const router = useRouter()

// 响应式变量
const loginFormRef = ref()
const codeUrl = ref("")
const computeTime = ref(0)
const timer = ref(null)
const loading = ref(false)
const isEmailLogin = ref(false)
const redirect = ref(undefined)

// 表单数据
const loginForm = ref({
  username: "",
  password: "",
  rememberMe: false,
  code: "",
  uuid: "",
  email: "",
  emailCode: ""
})

// 表单规则
const loginRules = ref({
  username: [
    { required: true, trigger: "blur", message: "用户名不能为空" }
  ],
  email: [
    { required: true, trigger: "blur", message: "邮箱地址不能为空" },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
  ],
  password: [
    { required: true, trigger: "blur", message: "密码不能为空" }
  ],
  code: [{ required: true, trigger: "change", message: "验证码不能为空" }],
  emailCode: [
    { required: true, trigger: "blur", message: "邮箱验证码不能为空" },
    { pattern: /^\d{6}$/, message: "验证码为6位数字" }
  ]
})

// 监听路由变化
watch(route, (newRoute) => {
  redirect.value = newRoute.query && newRoute.query.redirect
  console.log('重定向路径:', redirect.value)
}, { immediate: true })

// 方法
function loginMethod() {
  isEmailLogin.value = !isEmailLogin.value
}

function getEmailCode() {
  if (computeTime.value === 0) {
    loginFormRef.value.validateField('email', (valid) => {
      if (valid) {
        apiGetEmailCode(loginForm.value.email).then(res => {
          if (res.code === 200) {
            ElMessage.success('验证码已发送到您的邮箱')
            loginForm.value.uuid = res.uuid
            computeTime.value = 60
            timer.value = setInterval(() => {
              computeTime.value--
              if (computeTime.value <= 0) {
                clearInterval(timer.value)
              }
            }, 1000)
          }
        }).catch(error => {
          ElMessage.error('获取验证码失败: ' + (error.message || '未知错误'))
        })
      }
    })
  }
}

function getCode() {
  getCodeImg().then(res => {
    codeUrl.value = "data:image/gif;base64," + res.img
    loginForm.value.uuid = res.uuid
  }).catch(error => {
    console.error('获取验证码失败:', error)
  })
}

function getCookie() {
  const username = Cookies.get("username")
  const password = Cookies.get("password")
  const rememberMe = Cookies.get("rememberMe")
  const email = Cookies.get("email")
  loginForm.value = {
    username: username === undefined ? loginForm.value.username : username,
    password: password === undefined ? loginForm.value.password : decrypt(password),
    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
    code: loginForm.value.code,
    uuid: loginForm.value.uuid,
    email: email === undefined ? loginForm.value.email : email,
    emailCode: loginForm.value.emailCode
  }
}

async function handleLogin() {
  console.log('=== 开始登录流程 ===')
  console.log('当前登录方式:', isEmailLogin.value ? '邮箱登录' : '账号密码登录')

  // 根据当前登录方式显示相应的表单数据
  if (isEmailLogin.value) {
    console.log('邮箱登录表单数据详情:')
    console.log('  - email:', loginForm.value.email)
    console.log('  - emailCode:', loginForm.value.emailCode)
    console.log('  - uuid:', loginForm.value.uuid)
    console.log('  - emailCode 类型:', typeof loginForm.value.emailCode)
    console.log('  - emailCode 长度:', loginForm.value.emailCode ? loginForm.value.emailCode.length : 0)
  } else {
    console.log('账号密码登录表单数据详情:')
    console.log('  - username:', loginForm.value.username)
    console.log('  - password:', '***') // 不显示真实密码
    console.log('  - code:', loginForm.value.code)
    console.log('  - uuid:', loginForm.value.uuid)
    console.log('  - password 长度:', loginForm.value.password ? loginForm.value.password.length : 0)
  }
  
  loginFormRef.value.validate(async valid => {
    console.log('表单验证结果:', valid ? '通过' : '失败')

    if (valid) {
      console.log('表单验证通过，准备发送登录请求')
      
      if (isEmailLogin.value) {
        console.log('进入邮箱登录分支')
        loading.value = true

        // 确保参数正确传递
        const loginParams = {
          email: loginForm.value.email,
          emailCode: loginForm.value.emailCode,
          uuid: loginForm.value.uuid
        }
        console.log('发送的登录参数:', loginParams)

        if (loginForm.value.rememberMe) {
          Cookies.set("email", loginForm.value.email, { expires: 30 })
          Cookies.set('rememberMe', loginForm.value.rememberMe, { expires: 30 })
        } else {
          Cookies.remove("email")
          Cookies.remove('rememberMe')
        }
        
        try {
          // 执行邮箱登录
          await userStore.emailLogin({
            email: loginForm.value.email,
            emailCode: loginForm.value.emailCode,
            uuid: loginForm.value.uuid
          })
          
          console.log('✅ 邮箱登录成功，准备跳转')
          ElMessage.success('登录成功')

          // 确保用户信息完全加载
          console.log('🔄 确保用户信息完全加载...')
          await userStore.getInfo()
          
          // 生成动态路由
          console.log('🔄 生成动态路由...')
          await permissionStore.generateRoutes(userStore.roles)

          // 等待路由完全加载
          await new Promise(resolve => setTimeout(resolve, 100))

          // 详细检查用户状态
          console.log('登录后用户 Store 状态:', {
            token: userStore.token,
            roles: userStore.roles,
            permissions: userStore.permissions,
            name: userStore.name
          })

          // 检查路由守卫是否会被触发
          const targetPath = redirect.value || '/'
          console.log('准备跳转到:', targetPath)
          
          // 使用 replace 确保路由正确
          await router.replace({ path: redirect.value || "/" })
          console.log('跳转成功')
          
        } catch (error) {
          loading.value = false
          console.error('邮箱登录失败详情:', error)
          console.error('错误响应数据:', error.response?.data)
          console.error('错误状态码:', error.response?.status)
          console.error('错误信息:', error.response?.message)

          // 显示更具体的错误信息
          if (error.response?.data?.message) {
            ElMessage.error('登录失败: ' + error.response.data.message)
          } else {
            ElMessage.error(error.message || '登录失败，请检查邮箱和验证码')
          }
        }
      } else {
        console.log('进入账号密码登录分支')

        // 账号密码登录的参数
        const loginParams = {
          username: loginForm.value.username,
          password: loginForm.value.password,
          code: loginForm.value.code,
          uuid: loginForm.value.uuid
        }
        console.log('发送的账号密码登录参数:', loginParams)

        loading.value = true
        if (loginForm.value.rememberMe) {
          Cookies.set("username", loginForm.value.username, { expires: 30 })
          Cookies.set("password", encrypt(loginForm.value.password), { expires: 30 })
          Cookies.set("rememberMe", loginForm.value.rememberMe, { expires: 30 })
        } else {
          Cookies.remove("username")
          Cookies.remove("password")
          Cookies.remove("rememberMe")
        }
        
        try {
          // 执行账号密码登录
          await userStore.login({
            username: loginForm.value.username,
            password: loginForm.value.password,
            code: loginForm.value.code,
            uuid: loginForm.value.uuid
          })
          
          console.log('账号密码登录成功，准备跳转')
          ElMessage.success('登录成功')

          // 确保用户信息完全加载
          await userStore.getInfo()
          
          // 生成动态路由
          await permissionStore.generateRoutes(userStore.roles)

          // 等待路由完全加载
          await new Promise(resolve => setTimeout(resolve, 100))

          // 跳转到目标页面
          const targetPath = redirect.value || "/"
          console.log('准备跳转到:', targetPath)
          await router.replace(targetPath)
          console.log('账号密码登录跳转成功')
        } catch (error) {
          loading.value = false
          console.error('账号密码登录失败:', error)

          // 更详细的错误信息
          console.error('错误响应:', error.response)
          console.error('错误状态:', error.response?.status)
          console.error('错误数据:', error.response?.data)
          
          // 根据错误类型显示不同的提示信息
          if (error.response?.data?.message) {
            ElMessage.error('登录失败: ' + error.response.data.message)
          } else if (error.message.includes('用户不存在/密码错误')) {
            ElMessage.error('用户名或密码错误，请检查后重试')
          } else if (error.message.includes('验证码')) {
            ElMessage.error('验证码错误或已过期，请刷新验证码')
          } else {
            ElMessage.error(error.message || '登录失败，请检查网络连接')
          }
          
          getCode()
        }
      }
    } else {
      console.log('表单验证失败')
      ElMessage.warning('请完善表单信息')
    }
  })
}

// 初始化
onMounted(() => {
  getCode()
  getCookie()
})
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-image: url("../assets/images/login-background-sdu.png");
  background-size: cover;
  background-position: center 10%;
  background-repeat: no-repeat;
  background-color: #f0f8ff;
  position: relative;
  padding: 20px;
}

.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 440px;
  z-index: 1;
}

.title {
  margin: 0 0 32px 0;
  text-align: center;
  color: #2c3e50;
  font-size: 28px;
  font-weight: 600;
  letter-spacing: 1px;
  text-shadow: 0 2px 4px rgba(255, 255, 255, 0.8);
}

.login-form {
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.92);
  width: 100%;
  padding: 40px 35px 30px;
  box-shadow: 
    0 12px 40px rgba(0, 0, 0, 0.15),
    0 4px 12px rgba(0, 0, 0, 0.08);
  backdrop-filter: blur(8px);
  border: 1px solid rgba(255, 255, 255, 0.3);
  
  :deep(.el-form-item) {
    margin-bottom: 24px;
  }
  
  :deep(.el-input) {
    height: 48px;
    font-size: 15px;
    
    input {
      height: 48px;
      border-radius: 10px;
      border: 1.5px solid #e1e8ed;
      background: #ffffff;
      transition: all 0.3s ease;
      font-size: 15px;
      color: #2c3e50;
      
      &:focus {
        border-color: #3498db;
        background: #fff;
        box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
      }
      
      &::placeholder {
        color: #95a5a6;
        font-size: 14px;
      }
    }
  }
  
  :deep(.input-icon) {
    height: 39px;
    width: 16px;
    margin-left: 4px;
    color: #7f8c8d;
  }
}

.code-form-item {
  display: flex;
  gap: 12px;
  align-items: flex-start;
  
  .code-input {
    flex: 1;
  }
}

.login-code {
  width: 120px;
  height: 48px;
  
  .login-code-img {
    width: 100%;
    height: 100%;
    border-radius: 10px;
    cursor: pointer;
    border: 1.5px solid #e1e8ed;
    transition: all 0.3s ease;
    
    &:hover {
      border-color: #3498db;
      box-shadow: 0 2px 8px rgba(52, 152, 219, 0.2);
    }
  }
}

.email-code-btn {
  width: 120px;
  
  .email-btn {
    width: 100%;
    height: 48px;
    border-radius: 10px;
    font-size: 13px;
    font-weight: 500;
    transition: all 0.3s ease;
    
    &:not(:disabled) {
      background: #3498db;
      border: none;
      color: white;
      
      &:hover {
        background: #2980b9;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(52, 152, 219, 0.3);
      }
    }
    
    &:disabled {
      background: #bdc3c7;
      color: #7f8c8d;
      border-color: #bdc3c7;
    }
  }
}

.form-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 8px 0 24px;
  
  .left-actions {
    .remember-me {
      :deep(.el-checkbox__label) {
        color: #2c3e50;
        font-size: 14px;
        font-weight: 400;
      }
      
      :deep(.el-checkbox__inner) {
        border-radius: 4px;
        border-color: #bdc3c7;
        
        &:hover {
          border-color: #3498db;
        }
      }
    }
  }
  
  .right-actions {
    .login-method-btn {
      color: #3498db;
      font-size: 14px;
      font-weight: 500;
      padding: 4px 8px;
      transition: all 0.3s ease;
      
      &:hover {
        color: #2980b9;
        transform: translateX(2px);
      }
    }
  }
}

.login-btn-item {
  margin-bottom: 0;
  
  .login-btn {
    width: 100%;
    height: 48px;
    border-radius: 12px;
    background: #3498db;
    border: none;
    font-size: 16px;
    font-weight: 600;
    letter-spacing: 1px;
    transition: all 0.3s ease;
    box-shadow: 0 4px 15px rgba(52, 152, 219, 0.3);
    
    &:hover:not(:disabled) {
      background: #2980b9;
      transform: translateY(-2px);
      box-shadow: 0 6px 20px rgba(52, 152, 219, 0.4);
    }
    
    &:active {
      transform: translateY(0);
    }
  }
}

.login-footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 50px;
  line-height: 50px;
  text-align: center;
  color: rgba(255, 255, 255, 0.9);
  font-size: 13px;
  font-weight: 400;
  letter-spacing: 0.5px;
  background: rgba(44, 62, 80, 0.7);
  backdrop-filter: blur(5px);
  z-index: 1;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.3);
}

// 响应式设计
@media (max-width: 480px) {
  .login-container {
    padding: 15px;
  }
  
  .login-form {
    padding: 30px 25px 25px;
  }
  
  .title {
    font-size: 24px;
    margin-bottom: 28px;
  }
  
  .form-actions {
    flex-direction: column;
    gap: 15px;
    align-items: stretch;
    
    .left-actions,
    .right-actions {
      text-align: center;
    }
  }
}
</style>
