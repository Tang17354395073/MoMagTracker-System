import request from '@/utils/request'

// 视频处理相关API

/**
 * 处理视频文件
 * @param {FormData} data 表单数据，包含video文件、algorithm算法、amplification放大倍数等
 * @returns Promise
 */
export function processVideo(data) {
  return request({
    url: '/video/process',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 300000 // 5分钟超时，视频处理需要较长时间
  })
}

/**
 * 获取视频处理进度
 * @param {string} taskId 任务ID
 * @returns Promise
 */
export function getProcessProgress(taskId) {
  return request({
    url: `/video/progress/${taskId}`,
    method: 'get'
  })
}

/**
 * 开始实时视频处理
 * @param {Object} data 参数对象
 * @returns Promise
 */
export function startRealtimeProcess(data) {
  return request({
    url: '/video/realtime/start',
    method: 'post',
    data
  })
}

/**
 * 停止实时视频处理
 * @returns Promise
 */
export function stopRealtimeProcess() {
  return request({
    url: '/video/realtime/stop',
    method: 'post'
  })
}

/**
 * 下载处理结果
 * @param {string} taskId 任务ID
 * @returns Promise
 */
export function downloadResult(taskId) {
  return request({
    url: `/video/download/${taskId}`,
    method: 'get',
    responseType: 'blob'
  })
}

/**
 * 获取摄像头列表
 * @returns Promise
 */
export function getCameraList() {
  return request({
    url: '/video/cameras',
    method: 'get'
  })
}

/**
 * 获取处理历史记录
 * @param {Object} params 查询参数
 * @returns Promise
 */
export function getProcessHistory(params) {
  return request({
    url: '/video/history',
    method: 'get',
    params
  })
}

/**
 * 删除处理记录
 * @param {string} taskId 任务ID
 * @returns Promise
 */
export function deleteProcessRecord(taskId) {
  return request({
    url: `/video/history/${taskId}`,
    method: 'delete'
  })
}

/**
 * 批量删除处理记录
 * @param {Array} taskIds 任务ID数组
 * @returns Promise
 */
export function batchDeleteProcessRecord(taskIds) {
  return request({
    url: '/video/history/batch',
    method: 'delete',
    data: taskIds
  })
}

/**
 * 获取视频处理算法列表
 * @returns Promise
 */
export function getAlgorithmList() {
  return request({
    url: '/video/algorithms',
    method: 'get'
  })
}

/**
 * 获取视频信息（分辨率、时长等）
 * @param {FormData} formData 包含视频文件
 * @returns Promise
 */
export function getVideoInfo(formData) {
  return request({
    url: '/video/info',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

/**
 * 上传视频切片（用于大文件分片上传）
 * @param {FormData} formData 包含chunk切片文件
 * @returns Promise
 */
export function uploadVideoChunk(formData) {
  return request({
    url: '/video/upload/chunk',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

/**
 * 合并视频切片
 * @param {Object} data 包含fileHash文件哈希、fileName文件名等信息
 * @returns Promise
 */
export function mergeVideoChunks(data) {
  return request({
    url: '/video/upload/merge',
    method: 'post',
    data
  })
}

/**
 * 验证视频文件是否已上传
 * @param {string} fileHash 文件哈希
 * @returns Promise
 */
export function verifyVideoFile(fileHash) {
  return request({
    url: `/video/upload/verify/${fileHash}`,
    method: 'get'
  })
}

/**
 * 获取实时处理状态
 * @returns Promise
 */
export function getRealtimeStatus() {
  return request({
    url: '/video/realtime/status',
    method: 'get'
  })
}

/**
 * 获取视频截图
 * @param {string} taskId 任务ID
 * @param {number} timestamp 时间戳（秒）
 * @returns Promise
 */
export function getVideoScreenshot(taskId, timestamp) {
  return request({
    url: `/video/screenshot/${taskId}`,
    method: 'get',
    params: { timestamp },
    responseType: 'blob'
  })
}

/**
 * 保存视频处理配置模板
 * @param {Object} data 配置模板数据
 * @returns Promise
 */
export function saveConfigTemplate(data) {
  return request({
    url: '/video/config/template',
    method: 'post',
    data
  })
}

/**
 * 获取配置模板列表
 * @returns Promise
 */
export function getConfigTemplates() {
  return request({
    url: '/video/config/templates',
    method: 'get'
  })
}

/**
 * 应用配置模板
 * @param {string} templateId 模板ID
 * @returns Promise
 */
export function applyConfigTemplate(templateId) {
  return request({
    url: `/video/config/template/${templateId}`,
    method: 'post'
  })
}

/**
 * 获取系统状态（CPU、内存、磁盘等）
 * @returns Promise
 */
export function getSystemStatus() {
  return request({
    url: '/video/system/status',
    method: 'get'
  })
}

/**
 * 测试算法效果
 * @param {Object} data 测试参数
 * @returns Promise
 */
export function testAlgorithm(data) {
  return request({
    url: '/video/test/algorithm',
    method: 'post',
    data
  })
}

/**
 * 批量处理视频
 * @param {FormData} formData 包含多个视频文件
 * @returns Promise
 */
export function batchProcessVideos(formData) {
  return request({
    url: '/video/batch/process',
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    timeout: 600000 // 10分钟超时
  })
}