import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  const data = {
    username,
    password,
    code,
    uuid
  }
  return request({
    url: '/login',
    headers: {
      isToken: false,
      repeatSubmit: false
    },
    method: 'post',
    data: data
  })
}

// 邮箱登录方法
export function emailLogin(email, emailCode, uuid) {
  console.log('API调用参数:', { email, emailCode, uuid })
  console.log('参数 - email:', email)
  console.log('参数 - emailCode:', emailCode)
  console.log('参数 - uuid:', uuid)

  const data = {
    email,
    emailCode,
    uuid
  }
  return request({
    url: '/email/login',
    method: 'post',
    data: data
  })
}

// 发送邮箱验证码
export function getEmailCode(email) {

  const data = {
    email
  }

  return request({
    url: '/email/code',
    method: 'post',
    data:data
  })
}

// 注册方法
export function register(data) {
  return request({
    url: '/register',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}