import router from './router'
import { ElMessage } from 'element-plus'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
import { isHttp, isPathMatch } from '@/utils/validate'
import { isRelogin } from '@/utils/request'
import useUserStore from '@/store/modules/user'
import useSettingsStore from '@/store/modules/settings'
import usePermissionStore from '@/store/modules/permission'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/register']

const isWhiteList = (path) => {
  return whiteList.some(pattern => isPathMatch(pattern, path))
}

// 添加路由加载状态标识
let isRouteLoaded = false

router.beforeEach(async (to, from, next) => {
  NProgress.start()
  console.log('🚦 路由守卫开始 ===', to.path, 'Token:', getToken())
  
  if (getToken()) {
    console.log('✅ 检测到Token')
    to.meta.title && useSettingsStore().setTitle(to.meta.title)
    
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else if (isWhiteList(to.path)) {
      next()
    } else {
      const userStore = useUserStore()
      const permissionStore = usePermissionStore()
      console.log('👤 用户角色信息:', userStore.roles, '长度:', userStore.roles.length)
      
      // 无论用户信息是否存在，都确保路由已加载
      // 页面刷新时 userStore.roles 可能为空，但 isRouteLoaded 为 true，需要重新加载路由
      if (userStore.roles.length === 0 || !isRouteLoaded) {
        console.log('🔄 需要加载用户信息或路由...')
        isRelogin.show = true
        
        try {
          // 确保用户信息已加载
          if (userStore.roles.length === 0) {
            console.log('🔄 获取用户信息...')
            await userStore.getInfo()
            console.log('✅ 获取用户信息成功，角色:', userStore.roles)
          }

          // 确保动态路由已生成
          console.log('🔄 生成动态路由...')
          const accessRoutes = await permissionStore.generateRoutes(userStore.roles)
          console.log('🔄 生成的路由数量:', accessRoutes.length)

          // 添加动态路由（避免重复添加）
          // 页面刷新时需要重新添加路由
          if (!isRouteLoaded) {
            accessRoutes.forEach(route => {
              if (!isHttp(route.path)) {
                console.log('➕ 添加路由:', route.path, route.name)
                router.addRoute(route)
              }
            })
            isRouteLoaded = true
            console.log('✅ 所有路由添加完成')
          }
          
          isRelogin.show = false

          console.log('🔄 路由加载完成，重新导航到:', to.fullPath)
          next({ ...to, replace: true })
        } catch (error) {
          console.error('❌ 路由加载失败:', error)
          isRelogin.show = false
          userStore.logOut().then(() => {
            ElMessage.error('登录状态已过期，请重新登录')
            next({ path: '/login' })
          })
        }
        
      } else {
        console.log('✅ 用户信息和路由都已加载，直接跳转')
        // 确保当前路径可访问
        if (to.matched.length > 0) {
          next()
        } else {
          console.log('🔄 路径未匹配，尝试重新导航')
          next({ ...to, replace: true })
        }
      }
    }
  } else {
    // 没有token
    console.log('❌ 未检测到Token')
    if (isWhiteList(to.path)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  console.log('🏁 路由跳转完成')
  NProgress.done()
})

// 重置路由加载状态（在用户登出时调用）
export const resetRouteLoadStatus = () => {
  isRouteLoaded = false
}