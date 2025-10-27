import auth from '@/plugins/auth'
import router, { constantRoutes, dynamicRoutes } from '@/router'
import { getRouters } from '@/api/menu'
import Layout from '@/layout/index'
import ParentView from '@/components/ParentView'
import InnerLink from '@/layout/components/InnerLink'

// 匹配views里面所有的.vue文件
const modules = import.meta.glob('./../../views/**/*.vue')

const usePermissionStore = defineStore(
  'permission',
  {
    state: () => ({
      routes: [],
      addRoutes: [],
      defaultRoutes: [],
      topbarRouters: [],
      sidebarRouters: []
    }),
    actions: {
      setRoutes(routes) {
        this.addRoutes = routes
        this.routes = constantRoutes.concat(routes)
      },
      setDefaultRoutes(routes) {
        this.defaultRoutes = constantRoutes.concat(routes)
      },
      setTopbarRoutes(routes) {
        this.topbarRouters = routes
      },
      setSidebarRouters(routes) {
        this.sidebarRouters = routes
      },
      generateRoutes(roles) {
        return new Promise(resolve => {
          // 获取用户store
          console.log('🔄 [权限Store] 开始生成动态路由')
          console.log('🔄 [权限Store] 传入角色:', roles)
          console.log('🔄 [权限Store] 当前路由状态 - routes:', this.routes.length)
          console.log('🔄 [权限Store] 当前路由状态 - addRoutes:', this.addRoutes.length)

          // 向后端请求路由数据
          getRouters().then(res => {
            console.log('✅ [权限Store] 后端返回的路由数据状态码:', res.code)
            console.log('✅ [权限Store] 后端返回的路由数据:', res.data)

            if (!res.data || res.data.length === 0) {
              console.error('❌ [权限Store] 后端返回的路由数据为空！')
              resolve([])
              return
            }

            // 详细检查每个路由项
            res.data.forEach((route, index) => {
              console.log(`📋 路由 ${index}:`, {
                path: route.path,
                component: route.component,
                name: route.name,
                children: route.children ? route.children.length : 0
              })
            })

            const sdata = JSON.parse(JSON.stringify(res.data))
            const rdata = JSON.parse(JSON.stringify(res.data))
            const defaultData = JSON.parse(JSON.stringify(res.data))

            const sidebarRoutes = filterAsyncRouter(sdata)
            const rewriteRoutes = filterAsyncRouter(rdata, false, true)
            const defaultRoutes = filterAsyncRouter(defaultData)
            const asyncRoutes = filterDynamicRoutes(dynamicRoutes)

            console.log('🔄 过滤后的路由数量:')
            console.log('🔄 - sidebarRoutes:', sidebarRoutes.length)
            console.log('🔄 - rewriteRoutes:', rewriteRoutes.length)
            console.log('🔄 - asyncRoutes:', asyncRoutes.length)

            // 添加动态路由前检查
            asyncRoutes.forEach(route => {
              console.log(`➕ 准备添加动态路由: ${route.path}, 组件:`, route.component)
              router.addRoute(route)
              console.log(`✅ 已添加动态路由: ${route.path}`)
            })

            this.setRoutes(rewriteRoutes)
            this.setSidebarRouters(constantRoutes.concat(sidebarRoutes))
            this.setDefaultRoutes(sidebarRoutes)
            this.setTopbarRoutes(defaultRoutes)

            // 验证路由是否添加成功
            const allRoutes = router.getRoutes()
            console.log('📊 当前所有路由数量:', allRoutes.length)
            allRoutes.forEach(route => {
              console.log(`📍 路由: ${route.path}, 名称: ${route.name || '未命名'}`)
            })

            console.log('✅ 路由生成完成，最终路由:', rewriteRoutes)
            resolve(rewriteRoutes)
          }).catch(error => {
            console.error('❌ [权限Store] 获取路由失败:', error)
            console.error('❌ [权限Store] 错误详情:', error.response || error.message)
            resolve([])
          })
        })
      }
    }
  })

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
  return asyncRouterMap.filter(route => {
    if (type && route.children) {
      route.children = filterChildren(route.children)
    }
    if (route.component) {
      // Layout ParentView 组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else if (route.component === 'ParentView') {
        route.component = ParentView
      } else if (route.component === 'InnerLink') {
        route.component = InnerLink
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children, route, type)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}

function filterChildren(childrenMap, lastRouter = false) {
  var children = []
  childrenMap.forEach(el => {
    el.path = lastRouter ? lastRouter.path + '/' + el.path : el.path
    if (el.children && el.children.length && el.component === 'ParentView') {
      children = children.concat(filterChildren(el.children, el))
    } else {
      children.push(el)
    }
  })
  return children
}

// 动态路由遍历，验证是否具备权限
export function filterDynamicRoutes(routes) {
  const res = []
  routes.forEach(route => {
    if (route.permissions) {
      if (auth.hasPermiOr(route.permissions)) {
        res.push(route)
      }
    } else if (route.roles) {
      if (auth.hasRoleOr(route.roles)) {
        res.push(route)
      }
    }
  })
  return res
}

export const loadView = (view) => {
  let res
  console.log(`🔄 加载视图组件: ${view}`)
  
  for (const path in modules) {
    const dir = path.split('views/')[1].split('.vue')[0]
    console.log(`🔍 检查路径: ${path}, 目录: ${dir}`)
    
    if (dir === view) {
      res = () => modules[path]()
      console.log(`✅ 找到组件: ${view} -> ${path}`)
      break
    }
  }
  
  if (!res) {
    console.error(`❌ 未找到组件: ${view}`)
    // 返回一个默认的404组件或者空组件
    return () => import('@/views/error/404.vue')
  }
  
  return res
}

export default usePermissionStore
