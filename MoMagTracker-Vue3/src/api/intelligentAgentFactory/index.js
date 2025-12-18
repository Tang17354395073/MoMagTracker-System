import request from "@/utils/request";

// 获取Dify访问令牌
export function getAutoToken() {
  return request({
    url: '/dify/getAutoToken',
    method: 'get',
  })
}

// 获取Dify应用列表 - 对应"应用创建"菜单
export function getApps(query) {
  return request({
    url: '/dify/applicationCreation',
    method: 'get',
    params: query || {
      page: 1,
      limit: 30,
      is_created_by_me: false,
      name: ''
    }
  })
}

// 获取Dify数据集 - 对应"数据管理"菜单
export function getDatasets(query) {
  return request({
    url: '/dify/knowledgeManagement',
    method: 'get',
    params: query || {
      page: 1,
      limit: 30
    }
  })
}

// 获取Dify工具列表 - 对应"工具调用"菜单
export function getTools(query) {
  return request({
    url: '/dify/toolInvoker',
    method: 'get',
    params: query || {
      page: 1,
      limit: 30,
      name: ''
    }
  })
}