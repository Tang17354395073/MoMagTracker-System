<template>
  <div class="cache-management">
    <div class="main-card">
      <div class="content-block">
        <el-row :gutter="20">
          <!-- 缓存列表 -->
          <el-col :xs="24" :md="8" class="card-box">
            <el-card class="tech-card cache-list-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Collection class="card-icon" />
                  <span class="card-title">缓存列表</span>
                  <div class="card-actions">
                    <el-tooltip content="刷新缓存列表" placement="top">
                      <el-button
                        link
                        type="primary"
                        icon="Refresh"
                        @click="refreshCacheNames()"
                        class="refresh-btn"
                      ></el-button>
                    </el-tooltip>
                  </div>
                </div>
              </template>
              <div class="table-container">
                <el-table
                  v-loading="loading"
                  :data="cacheNames"
                  :height="tableHeight"
                  highlight-current-row
                  @row-click="getCacheKeys"
                  class="tech-table"
                >
                  <el-table-column
                    label="序号"
                    width="70"
                    type="index"
                    align="center"
                  >
                    <template #default="scope">
                      <div class="index-badge">{{ scope.$index + 1 }}</div>
                    </template>
                  </el-table-column>

                  <el-table-column
                    label="缓存名称"
                    align="center"
                    prop="cacheName"
                    :show-overflow-tooltip="true"
                    :formatter="nameFormatter"
                  >
                    <template #default="scope">
                      <div class="cache-name">
                        <el-tag size="small" class="cache-tag">
                          {{ nameFormatter(scope.row) }}
                        </el-tag>
                      </div>
                    </template>
                  </el-table-column>

                  <el-table-column
                    label="备注"
                    align="center"
                    prop="remark"
                    :show-overflow-tooltip="true"
                  >
                    <template #default="scope">
                      <div class="remark-text">{{ scope.row.remark || '-' }}</div>
                    </template>
                  </el-table-column>
                  
                  <el-table-column
                    label="操作"
                    width="80"
                    align="center"
                    class-name="small-padding fixed-width"
                  >
                    <template #default="scope">
                      <el-tooltip content="清理缓存" placement="top">
                        <el-button
                          link
                          type="danger"
                          icon="Delete"
                          @click.stop="handleClearCacheName(scope.row)"
                          class="action-btn delete"
                        ></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>

          <!-- 键名列表 -->
          <el-col :xs="24" :md="8" class="card-box">
            <el-card class="tech-card keys-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Key class="card-icon" />
                  <span class="card-title">键名列表</span>
                  <div class="card-actions">
                    <el-tooltip content="刷新键名列表" placement="top">
                      <el-button
                        link
                        type="primary"
                        icon="Refresh"
                        @click="refreshCacheKeys()"
                        class="refresh-btn"
                      ></el-button>
                    </el-tooltip>
                  </div>
                </div>
              </template>
              <div class="table-container">
                <el-table
                  v-loading="subLoading"
                  :data="cacheKeys"
                  :height="tableHeight"
                  highlight-current-row
                  @row-click="handleCacheValue"
                  class="tech-table"
                >
                  <el-table-column
                    label="序号"
                    width="70"
                    type="index"
                    align="center"
                  >
                    <template #default="scope">
                      <div class="index-badge">{{ scope.$index + 1 }}</div>
                    </template>
                  </el-table-column>
                  
                  <el-table-column
                    label="缓存键名"
                    align="center"
                    :show-overflow-tooltip="true"
                    :formatter="keyFormatter"
                  >
                    <template #default="scope">
                      <div class="key-name">
                        <el-tag size="small" type="info" class="key-tag">
                          {{ keyFormatter(scope.row) }}
                        </el-tag>
                      </div>
                    </template>
                  </el-table-column>
                  
                  <el-table-column
                    label="操作"
                    width="80"
                    align="center"
                    class-name="small-padding fixed-width"
                  >
                    <template #default="scope">
                      <el-tooltip content="删除键名" placement="top">
                        <el-button
                          link
                          type="danger"
                          icon="Delete"
                          @click.stop="handleClearCacheKey(scope.row)"
                          class="action-btn delete"
                        ></el-button>
                      </el-tooltip>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-col>

          <!-- 缓存内容 -->
          <el-col :xs="24" :md="8" class="card-box">
            <el-card class="tech-card content-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Document class="card-icon" />
                  <span class="card-title">缓存内容</span>
                  <div class="card-actions">
                    <el-tooltip content="清理全部缓存" placement="top">
                      <el-button
                        link
                        type="danger"
                        icon="Delete"
                        @click="handleClearCacheAll()"
                        class="clear-all-btn"
                      >
                        清理全部
                      </el-button>
                    </el-tooltip>
                  </div>
                </div>
              </template>
              <div class="cache-content">
                <el-form :model="cacheForm" class="tech-form">
                  <el-row :gutter="20">
                    <el-col :span="24">
                      <el-form-item label="缓存名称:" class="form-item">
                        <el-input 
                          v-model="cacheForm.cacheName" 
                          :readonly="true" 
                          class="tech-input readonly"
                        />
                      </el-form-item>
                    </el-col>
                    <el-col :span="24">
                      <el-form-item label="缓存键名:" class="form-item">
                        <el-input 
                          v-model="cacheForm.cacheKey" 
                          :readonly="true" 
                          class="tech-input readonly"
                        />
                      </el-form-item>
                    </el-col>
                    <el-col :span="24">
                      <el-form-item label="缓存内容:" class="form-item">
                        <el-input
                          v-model="cacheForm.cacheValue"
                          type="textarea"
                          :rows="10"
                          :readonly="true"
                          class="tech-textarea readonly"
                          resize="none"
                        />
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script setup name="CacheList">
import { listCacheName, listCacheKey, getCacheValue, clearCacheName, clearCacheKey, clearCacheAll } from "@/api/monitor/cache"

const { proxy } = getCurrentInstance()

const cacheNames = ref([])
const cacheKeys = ref([])
const cacheForm = ref({})
const loading = ref(true)
const subLoading = ref(false)
const nowCacheName = ref("")
const tableHeight = ref(window.innerHeight - 200)

/** 查询缓存名称列表 */
function getCacheNames() {
  loading.value = true
  listCacheName().then(response => {
    cacheNames.value = response.data
    loading.value = false
  })
}

/** 刷新缓存名称列表 */
function refreshCacheNames() {
  getCacheNames()
  proxy.$modal.msgSuccess("刷新缓存列表成功")
}

/** 清理指定名称缓存 */
function handleClearCacheName(row) {
  proxy.$modal.confirm(`是否确认清理缓存名称 "${row.cacheName}"？`).then(function () {
    return clearCacheName(row.cacheName)
  }).then(() => {
    proxy.$modal.msgSuccess("清理缓存名称[" + row.cacheName + "]成功")
    getCacheKeys()
  }).catch(() => {})
}

/** 查询缓存键名列表 */
function getCacheKeys(row) {
  const cacheName = row !== undefined ? row.cacheName : nowCacheName.value
  if (cacheName === "") {
    return
  }
  subLoading.value = true
  listCacheKey(cacheName).then(response => {
    cacheKeys.value = response.data
    subLoading.value = false
    nowCacheName.value = cacheName
  })
}

/** 刷新缓存键名列表 */
function refreshCacheKeys() {
  getCacheKeys()
  proxy.$modal.msgSuccess("刷新键名列表成功")
}

/** 清理指定键名缓存 */
function handleClearCacheKey(cacheKey) {
  proxy.$modal.confirm(`是否确认删除缓存键名 "${cacheKey}"？`).then(function () {
    return clearCacheKey(cacheKey)
  }).then(() => {
    proxy.$modal.msgSuccess("清理缓存键名[" + cacheKey + "]成功")
    getCacheKeys()
  }).catch(() => {})
}

/** 列表前缀去除 */
function nameFormatter(row) {
  return row.cacheName.replace(":", "")
}

/** 键名前缀去除 */
function keyFormatter(cacheKey) {
  return cacheKey.replace(nowCacheName.value, "")
}

/** 查询缓存内容详细 */
function handleCacheValue(cacheKey) {
  getCacheValue(nowCacheName.value, cacheKey).then(response => {
    cacheForm.value = response.data
  })
}

/** 清理全部缓存 */
function handleClearCacheAll() {
  proxy.$modal.confirm("是否确认清理全部缓存？此操作不可恢复！").then(function () {
    return clearCacheAll()
  }).then(() => {
    proxy.$modal.msgSuccess("清理全部缓存成功")
    getCacheNames()
    cacheKeys.value = []
    cacheForm.value = {}
  }).catch(() => {})
}

getCacheNames()
</script>

<style scoped lang="scss">
.cache-management {
  font-family: "Inter", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
  color: #2c3e50;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  padding: 20px;
  display: flex;
  align-items: flex-start;
  justify-content: center;

  .main-card {
    background: rgba(255, 255, 255, 0.95);
    border-radius: 20px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
    backdrop-filter: blur(20px);
    width: 100%;
    max-width: 2200px;
    padding: 30px;
    margin: 20px;
    min-height: auto;
    height: auto;

    .content-block {
      background: transparent;
      border-radius: 0;
      padding: 0;
      box-shadow: none;
      border: none;
      min-height: auto;

      &:hover {
        transform: none;
        box-shadow: none;
      }
    }
  }

  .card-box {
    margin-bottom: 0;
  }

  .tech-card {
    border-radius: 16px;
    border: 1px solid #f1f5f9;
    transition: all 0.3s ease;
    background: white;
    height: calc(100vh - 180px);
    display: flex;
    flex-direction: column;

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 12px 40px rgba(0, 0, 0, 0.15);
    }

    :deep(.el-card__header) {
      border-bottom: 1px solid #f1f5f9;
      padding: 20px;
      background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
      border-radius: 16px 16px 0 0;
      flex-shrink: 0;
    }

    .card-header {
      display: flex;
      align-items: center;
      justify-content: space-between;

      .card-icon {
        width: 20px;
        height: 20px;
        color: #667eea;
        margin-right: 12px;
      }

      .card-title {
        font-size: 1.1rem;
        font-weight: 700;
        color: #1e293b;
        flex: 1;
      }

      .card-actions {
        display: flex;
        align-items: center;
        gap: 8px;

        .refresh-btn, .clear-all-btn {
          padding: 4px 8px;
          border-radius: 6px;
          transition: all 0.3s ease;
          font-weight: 500;

          &:hover {
            background: #f1f5f9;
            transform: scale(1.05);
          }
        }

        .clear-all-btn {
          color: #ef4444;
          
          &:hover {
            color: #dc2626;
            background: #fef2f2;
          }
        }
      }
    }

    :deep(.el-card__body) {
      padding: 0;
      flex: 1;
      display: flex;
      flex-direction: column;
    }
  }

  .table-container {
    flex: 1;
    padding: 16px;
    display: flex;
    flex-direction: column;
  }

  .tech-table {
    flex: 1;
    border-radius: 8px;
    overflow: hidden;

    :deep(.el-table__header) {
      th {
        background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
        color: #374151;
        font-weight: 600;
        border-bottom: 1px solid #e2e8f0;
      }
    }

    :deep(.el-table__body) {
      tr {
        transition: all 0.3s ease;
        
        &:hover {
          transform: translateX(2px);
          box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        &.current-row {
          background: linear-gradient(135deg, #667eea15 0%, #764ba215 100%);
        }
      }
    }

    .index-badge {
      display: inline-flex;
      align-items: center;
      justify-content: center;
      width: 24px;
      height: 24px;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      border-radius: 50%;
      font-size: 0.8rem;
      font-weight: 600;
    }

    .cache-name, .key-name {
      display: flex;
      justify-content: center;
    }

    .cache-tag, .key-tag {
      :deep(.el-tag) {
        border: none;
        border-radius: 6px;
        font-weight: 500;
        max-width: 120px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }

    .cache-tag {
      :deep(.el-tag) {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
      }
    }

    .key-tag {
      :deep(.el-tag) {
        background: linear-gradient(135deg, #10b981 0%, #059669 100%);
        color: white;
      }
    }

    .remark-text {
      color: #6b7280;
      font-size: 0.9rem;
    }

    .action-btn {
      width: 32px;
      height: 32px;
      border-radius: 6px;
      display: flex;
      align-items: center;
      justify-content: center;
      transition: all 0.3s ease;

      &:hover {
        transform: scale(1.1);
        background: #fef2f2;
      }

      &.delete {
        color: #ef4444;
      }
    }
  }

  .cache-content {
    flex: 1;
    padding: 20px;
    display: flex;
    flex-direction: column;
  }

  .tech-form {
    flex: 1;
    display: flex;
    flex-direction: column;

    .form-item {
      margin-bottom: 20px;

      :deep(.el-form-item__label) {
        font-weight: 600;
        color: #374151;
        margin-bottom: 8px;
      }
    }
  }

  .tech-input, .tech-textarea {
    :deep(.el-input__wrapper) {
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
      background: #f8fafc;
      
      &:hover {
        border-color: #667eea;
        box-shadow: 0 2px 8px rgba(102, 126, 234, 0.1);
      }
    }

    &.readonly {
      :deep(.el-input__wrapper) {
        background: #f8fafc;
        border-color: #e2e8f0;
        
        .el-input__inner {
          color: #6b7280;
          cursor: not-allowed;
        }
      }
    }
  }

  .tech-textarea {
    :deep(.el-textarea__inner) {
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      background: #f8fafc;
      font-family: 'Monaco', 'Consolas', monospace;
      font-size: 0.9rem;
      color: #374151;
      resize: vertical;
      
      &:focus {
        border-color: #667eea;
        box-shadow: 0 0 0 2px rgba(102, 126, 234, 0.1);
      }
    }

    &.readonly {
      :deep(.el-textarea__inner) {
        background: #f8fafc;
        border-color: #e2e8f0;
        color: #6b7280;
        cursor: not-allowed;
        resize: none;
      }
    }
  }
}

// 响应式设计
@media (max-width: 1200px) {
  .cache-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }

    .tech-card {
      height: calc(100vh - 160px);
    }
  }
}

@media (max-width: 768px) {
  .cache-management {
    padding: 15px;

    .main-card {
      padding: 20px;
      margin: 15px;
    }

    .tech-card {
      height: auto;
      min-height: 500px;
      margin-bottom: 20px;
    }

    .card-header {
      flex-direction: column;
      align-items: flex-start;
      gap: 12px;

      .card-actions {
        align-self: flex-end;
      }
    }
  }
}

@media (max-width: 480px) {
  .cache-management {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;
    }

    .table-container {
      padding: 12px;
    }

    .cache-content {
      padding: 15px;
    }
  }
}

// 加载动画优化
:deep(.el-loading-mask) {
  background-color: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(4px);
  
  .el-loading-spinner {
    .path {
      stroke: #667eea;
    }
  }
}
</style>