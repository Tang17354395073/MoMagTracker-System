<template>
   <div class="onlinelog-management">
      <div class="main-card">
         <div class="content-block">
            <!-- 搜索区域 -->
            <div class="search-section">
               <el-form :model="queryParams" ref="queryRef" v-show="showSearch" :inline="true" class="search-form">
                  <el-form-item label="登录地址" prop="ipaddr">
                     <el-input
                        v-model="queryParams.ipaddr"
                        placeholder="请输入登录地址"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="用户名称" prop="userName">
                     <el-input
                        v-model="queryParams.userName"
                        placeholder="请输入用户名称"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item class="search-actions">
                     <el-button type="primary" icon="Search" @click="handleQuery" class="tech-btn primary">搜索</el-button>
                     <el-button icon="Refresh" @click="resetQuery" class="tech-btn secondary">重置</el-button>
                  </el-form-item>
               </el-form>
            </div>

            <!-- 操作按钮区域 -->
            <div class="action-section">
               <el-row :gutter="10">
                  <el-col :span="1.5">
                     <el-button
                        type="danger"
                        plain
                        icon="Refresh"
                        @click="handleRefresh"
                        class="tech-btn danger"
                     >刷新</el-button>
                  </el-col>
                  <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" class="right-toolbar"></right-toolbar>
               </el-row>
            </div>

            <!-- 表格区域 -->
            <div class="table-section">
               <el-table 
                  ref="onlineRef" 
                  v-loading="loading" 
                  :data="onlineList" 
                  style="width: 100%;"
                  class="tech-table"
                  :default-sort="{ prop: 'loginTime', order: 'descending' }"
               >
                  <el-table-column label="序号" width="80" type="index" align="center" class-name="index-column">
                     <template #default="scope">
                        <span class="index-text">{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                     </template>
                  </el-table-column>
                  <el-table-column label="会话编号" align="center" prop="tokenId" :show-overflow-tooltip="true" min-width="200" />
                  <el-table-column label="登录名称" align="center" prop="userName" :show-overflow-tooltip="true" width="120" />
                  <el-table-column label="所属部门" align="center" prop="deptName" :show-overflow-tooltip="true" width="120" />
                  <el-table-column label="主机地址" align="center" prop="ipaddr" :show-overflow-tooltip="true" width="150" />
                  <el-table-column label="登录地点" align="center" prop="loginLocation" :show-overflow-tooltip="true" width="150" />
                  <el-table-column label="操作系统" align="center" prop="os" :show-overflow-tooltip="true" width="180" class-name="os-info" />
                  <el-table-column label="浏览器" align="center" prop="browser" :show-overflow-tooltip="true" width="180" class-name="browser-info" />
                  <el-table-column label="登录时间" align="center" prop="loginTime" width="180" sortable>
                     <template #default="scope">
                        <span class="time-text">{{ parseTime(scope.row.loginTime) }}</span>
                     </template>
                  </el-table-column>
                  <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120">
                     <template #default="scope">
                        <el-button 
                           link 
                           type="danger" 
                           icon="Delete" 
                           @click="handleForceLogout(scope.row)" 
                           v-hasPermi="['monitor:online:forceLogout']"
                           class="action-btn danger"
                        >强退</el-button>
                     </template>
                  </el-table-column>
               </el-table>

               <!-- 分页 -->
               <div class="pagination-section">
                  <pagination 
                     v-show="total > 0" 
                     :total="total" 
                     v-model:page="pageNum" 
                     v-model:limit="pageSize" 
                     class="tech-pagination"
                  />
               </div>
            </div>
         </div>
      </div>
   </div>
</template>

<script setup name="Online">
import { forceLogout, list as initData } from "@/api/monitor/online"

const { proxy } = getCurrentInstance()

const onlineList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const pageNum = ref(1)
const pageSize = ref(10)

const queryParams = ref({
  ipaddr: undefined,
  userName: undefined
})

/** 查询在线用户列表 */
function getList() {
  loading.value = true
  initData(queryParams.value).then(response => {
    onlineList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  pageNum.value = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 刷新按钮操作 */
function handleRefresh() {
  getList()
  proxy.$modal.msgSuccess("刷新成功")
}

/** 强退按钮操作 */
function handleForceLogout(row) {
  proxy.$modal.confirm('是否确认强退名称为"' + row.userName + '"的用户?').then(function () {
    return forceLogout(row.tokenId)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("强退成功")
  }).catch(() => {})
}

getList()
</script>

<style scoped lang="scss">
.onlinelog-management {
  font-family: "Inter", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
  color: #2c3e50;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  padding: 40px;
  display: flex;
  align-items: flex-start;
  justify-content: center;

  .main-card {
    background: rgba(255, 255, 255, 0.95);
    border-radius: 20px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
    backdrop-filter: blur(20px);
    width: 100%;
    max-width: 2000px;
    padding: 30px;
    margin: 20px;
    min-height: auto;
    height: auto;

    .content-block {
      background: white;
      border-radius: 16px;
      padding: 25px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
      border: 1px solid #f1f5f9;
      transition: all 0.3s ease;
      min-height: 700px;
      height: auto;

      &:hover {
        transform: translateY(-3px);
        box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
      }

      // 搜索区域
      .search-section {
        margin-bottom: 20px;
        
        .search-form {
          display: flex;
          flex-wrap: wrap;
          gap: 15px;
          align-items: flex-end;

          .el-form-item {
            margin-bottom: 0;
          }

          .search-actions {
            margin-left: auto;
          }
        }
      }

      // 操作按钮区域
      .action-section {
        margin-bottom: 20px;
        padding: 15px;
        background: #f8fafc;
        border-radius: 12px;
        border: 1px solid #e2e8f0;

        .right-toolbar {
          margin-left: auto;
        }
      }

      // 表格区域
      .table-section {
        .pagination-section {
          margin-top: 20px;
          padding-top: 15px;
          border-top: 1px solid #e2e8f0;
        }

        .index-column {
          .index-text {
            font-weight: 600;
            color: #667eea;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
          }
        }

        .action-btn {
          border-radius: 6px;
          padding: 6px 12px;
          font-weight: 500;
          transition: all 0.3s ease;
          
          &.danger {
            background: linear-gradient(135deg, rgba(239, 68, 68, 0.1) 0%, rgba(220, 38, 38, 0.1) 100%);
            color: #ef4444;
            
            &:hover {
              background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
              color: white;
              transform: translateY(-1px);
              box-shadow: 0 4px 12px rgba(239, 68, 68, 0.3);
            }
          }
        }
      }
    }
  }

  // 科技感组件样式
  .tech-input {
    width: 240px;

    :deep(.el-input__wrapper) {
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
      
      &:hover {
        border-color: #667eea;
        box-shadow: 0 2px 8px rgba(102, 126, 234, 0.1);
      }
      
      &.is-focus {
        border-color: #667eea;
        box-shadow: 0 0 0 2px rgba(102, 126, 234, 0.1);
      }
    }
  }

  .tech-select {
    width: 240px;

    :deep(.el-select__wrapper) {
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
      
      &:hover {
        border-color: #667eea;
      }
    }
  }

  .tech-date-picker {
    :deep(.el-input__wrapper) {
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;
      
      &:hover {
        border-color: #667eea;
      }
    }
  }

  .tech-btn {
    border-radius: 8px;
    font-weight: 600;
    transition: all 0.3s ease;
    border: none;
    
    &.primary {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(102, 126, 234, 0.4);
      }
    }

    &.secondary {
      background: white;
      border: 1px solid #e2e8f0;
      color: #475569;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);

      &:hover {
        transform: translateY(-2px);
        border-color: #667eea;
        color: #667eea;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      }
    }

    &.success {
      background: linear-gradient(135deg, #10b981 0%, #059669 100%);
      color: white;
      box-shadow: 0 4px 15px rgba(16, 185, 129, 0.3);

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(16, 185, 129, 0.4);
      }
    }

    &.danger {
      background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
      color: white;
      box-shadow: 0 4px 15px rgba(239, 68, 68, 0.3);

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(239, 68, 68, 0.4);
      }
    }

    &.warning {
      background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
      color: white;
      box-shadow: 0 4px 15px rgba(245, 158, 11, 0.3);

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(245, 158, 11, 0.4);
      }
    }

    &.info {
      background: linear-gradient(135deg, #6b7280 0%, #4b5563 100%);
      color: white;
      box-shadow: 0 4px 15px rgba(107, 114, 128, 0.3);

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(107, 114, 128, 0.4);
      }
    }
  }

  .tech-table {
    :deep(.el-table__header) {
      th {
        background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
        color: #374151;
        font-weight: 600;
        border-bottom: 2px solid #e2e8f0;
      }
    }

    :deep(.el-table__row) {
      transition: all 0.3s ease;
      
      &:hover {
        transform: translateY(-1px);
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        background: #f8fafc;
      }
    }

    :deep(.el-table__cell) {
      padding: 12px 0;
    }
  }

  .time-text {
    color: #6b7280;
    font-size: 0.9rem;
    font-family: 'Monaco', 'Consolas', monospace;
  }

  .tech-pagination {
    :deep(.el-pagination) {
      .btn-prev, .btn-next, .number, .more {
        border-radius: 6px;
        margin: 0 2px;
        transition: all 0.3s ease;
      }
      
      .number.active {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        border: none;
        box-shadow: 0 2px 8px rgba(102, 126, 234, 0.3);
      }

      .number:not(.active):hover {
        color: #667eea;
        border-color: #667eea;
      }
    }
  }
}

// 响应式设计
@media (max-width: 1024px) {
  .onlinelog-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .onlinelog-management {
    padding: 15px;

    .main-card {
      padding: 20px;

      .content-block {
        padding: 20px;
        min-height: 400px;

        .search-section {
          .search-form {
            flex-direction: column;
            align-items: stretch;
            
            .search-actions {
              margin-left: 0;
              text-align: center;
            }

            .tech-input, .tech-select {
              width: 100%;
            }
          }
        }

        .action-section {
          .el-col {
            margin-bottom: 10px;
          }
        }

        .table-section {
          overflow-x: auto;
        }
      }
    }
  }
}

@media (max-width: 480px) {
  .onlinelog-management {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;

      .content-block {
        padding: 15px;
        min-height: 350px;
      }
    }
  }
}

// 浏览器和操作系统信息样式优化
.tech-table {
  :deep(.el-table__body) {
    .browser-info, .os-info {
      font-family: 'Monaco', 'Consolas', monospace;
      font-size: 0.85rem;
      color: #4b5563;
    }
  }
}

// 在线用户状态指示器
.online-status {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background: linear-gradient(135deg, #10b981 0%, #059669 100%);
  margin-right: 6px;
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% {
    transform: scale(0.95);
    box-shadow: 0 0 0 0 rgba(16, 185, 129, 0.7);
  }
  
  70% {
    transform: scale(1);
    box-shadow: 0 0 0 6px rgba(16, 185, 129, 0);
  }
  
  100% {
    transform: scale(0.95);
    box-shadow: 0 0 0 0 rgba(16, 185, 129, 0);
  }
}

// 特殊状态样式
.status-tag {
  :deep(.el-tag) {
    &.el-tag--success {
      background: linear-gradient(135deg, #10b981 0%, #059669 100%);
      border: none;
      color: white;
    }
    
    &.el-tag--danger {
      background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
      border: none;
      color: white;
    }
    
    &.el-tag--info {
      background: linear-gradient(135deg, #6b7280 0%, #4b5563 100%);
      border: none;
      color: white;
    }
  }
}

// 加载动画优化
:deep(.el-loading-mask) {
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(10px);
  
  .el-loading-spinner {
    .path {
      stroke: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    }
  }
}

// 表格行细节优化
.tech-table {
  :deep(.el-table__row) {
    &.current-row {
      background: linear-gradient(135deg, rgba(102, 126, 234, 0.05) 0%, rgba(118, 75, 162, 0.05) 100%);
      border-left: 3px solid #667eea;
    }
  }
}

// 表单标签样式优化
:deep(.el-form-item__label) {
  font-weight: 600;
  color: #374151;
  margin-bottom: 4px;
}

// 卡片标题区域
.card-header {
  display: flex;
  justify-content: between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #e2e8f0;
  
  .title {
    font-size: 1.5rem;
    font-weight: 700;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
  }
}

// 统计信息卡片
.stats-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
  margin-bottom: 20px;
  
  .stat-card {
    background: white;
    border-radius: 12px;
    padding: 20px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    border: 1px solid #f1f5f9;
    transition: all 0.3s ease;
    
    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
    }
    
    .stat-value {
      font-size: 2rem;
      font-weight: 700;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      background-clip: text;
      margin-bottom: 8px;
    }
    
    .stat-label {
      color: #6b7280;
      font-size: 0.9rem;
      font-weight: 500;
    }
  }
}

// 空状态样式
.empty-state {
  text-align: center;
  padding: 60px 20px;
  
  .empty-icon {
    font-size: 4rem;
    color: #d1d5db;
    margin-bottom: 16px;
  }
  
  .empty-text {
    color: #6b7280;
    font-size: 1.1rem;
    margin-bottom: 16px;
  }
}

// 操作按钮组样式
.action-group {
  display: flex;
  gap: 8px;
  align-items: center;
  
  .action-btn {
    transition: all 0.3s ease;
    
    &.small {
      padding: 6px 12px;
      font-size: 0.85rem;
    }
    
    &.icon-only {
      width: 32px;
      height: 32px;
      padding: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}

// 标签样式优化
:deep(.el-tag) {
  border-radius: 6px;
  font-weight: 500;
  border: none;
  
  &.el-tag--success {
    background: linear-gradient(135deg, #10b981 0%, #059669 100%);
    color: white;
  }
  
  &.el-tag--warning {
    background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
    color: white;
  }
  
  &.el-tag--danger {
    background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
    color: white;
  }
  
  &.el-tag--info {
    background: linear-gradient(135deg, #6b7280 0%, #4b5563 100%);
    color: white;
  }
}

// 对话框样式优化
:deep(.el-dialog) {
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.2);
  
  .el-dialog__header {
    background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
    border-radius: 16px 16px 0 0;
    padding: 20px;
    margin: 0;
    
    .el-dialog__title {
      font-weight: 600;
      color: #374151;
    }
  }
  
  .el-dialog__body {
    padding: 20px;
  }
  
  .el-dialog__footer {
    background: #f8fafc;
    border-radius: 0 0 16px 16px;
    padding: 20px;
    margin: 0;
  }
}

// 消息提示样式优化
:deep(.el-message) {
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
  border: none;
  
  &.el-message--success {
    background: linear-gradient(135deg, #10b981 0%, #059669 100%);
    color: white;
  }
  
  &.el-message--warning {
    background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
    color: white;
  }
  
  &.el-message--error {
    background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
    color: white;
  }
  
  &.el-message--info {
    background: linear-gradient(135deg, #6b7280 0%, #4b5563 100%);
    color: white;
  }
}

// 确认框样式优化
:deep(.el-message-box) {
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.2);
  border: none;
  
  .el-message-box__header {
    background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
    border-radius: 16px 16px 0 0;
    padding: 20px;
    
    .el-message-box__title {
      font-weight: 600;
      color: #374151;
    }
  }
  
  .el-message-box__content {
    padding: 20px;
  }
  
  .el-message-box__btns {
    background: #f8fafc;
    border-radius: 0 0 16px 16px;
    padding: 20px;
    margin: 0;
  }
}

// 滚动条样式优化
:deep(::-webkit-scrollbar) {
  width: 6px;
  height: 6px;
}

:deep(::-webkit-scrollbar-track) {
  background: #f1f5f9;
  border-radius: 3px;
}

:deep(::-webkit-scrollbar-thumb) {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 3px;
}

:deep(::-webkit-scrollbar-thumb:hover) {
  background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
}

// 表格滚动条样式
.tech-table {
  :deep(.el-table__body-wrapper::-webkit-scrollbar) {
    height: 8px;
  }
  
  :deep(.el-table__body-wrapper::-webkit-scrollbar-track) {
    background: #f1f5f9;
  }
  
  :deep(.el-table__body-wrapper::-webkit-scrollbar-thumb) {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    border-radius: 4px;
  }
}

// 输入框聚焦效果增强
.tech-input {
  :deep(.el-input__wrapper) {
    transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    
    &.is-focus {
      transform: translateY(-1px);
      box-shadow: 0 4px 12px rgba(102, 126, 234, 0.2);
    }
  }
}

// 按钮点击效果
.tech-btn {
  &:active {
    transform: translateY(0) !important;
  }
}

// 表格行进入动画
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.tech-table {
  :deep(.el-table__row) {
    animation: fadeInUp 0.5s ease forwards;
    
    @for $i from 1 through 20 {
      &:nth-child(#{$i}) {
        animation-delay: #{$i * 0.05}s;
      }
    }
  }
}

// 响应式表格列隐藏
@media (max-width: 1200px) {
  .tech-table {
    :deep(.el-table__body) {
      .el-table__row {
        .el-table__cell:nth-child(6),
        .el-table__cell:nth-child(7) {
          .cell {
            max-width: 120px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          }
        }
      }
    }
  }
}

@media (max-width: 992px) {
  .tech-table {
    :deep(.el-table__body) {
      .el-table__row {
        .el-table__cell:nth-child(4),
        .el-table__cell:nth-child(5) {
          .cell {
            max-width: 100px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          }
        }
      }
    }
  }
}

// 打印样式优化
@media print {
  .onlinelog-management {
    background: white !important;
    padding: 0 !important;
    
    .main-card {
      box-shadow: none !important;
      background: white !important;
      margin: 0 !important;
      padding: 0 !important;
      
      .content-block {
        box-shadow: none !important;
        border: 1px solid #ddd !important;
      }
    }
    
    .search-section,
    .action-section {
      display: none !important;
    }
  }
}

// 高对比度模式支持
@media (prefers-contrast: high) {
  .onlinelog-management {
    .main-card {
      background: white;
      border: 2px solid #000;
      
      .content-block {
        border: 1px solid #000;
      }
    }
    
    .tech-btn {
      border: 1px solid #000;
    }
  }
}

// 减少动画模式支持
@media (prefers-reduced-motion: reduce) {
  .onlinelog-management {
    * {
      animation-duration: 0.01ms !important;
      animation-iteration-count: 1 !important;
      transition-duration: 0.01ms !important;
    }
  }
}

// 暗色模式支持
@media (prefers-color-scheme: dark) {
  .onlinelog-management {
    background: linear-gradient(135deg, #1e1b2e 0%, #2d1b69 100%);
    color: #e2e8f0;
    
    .main-card {
      background: rgba(30, 30, 30, 0.95);
      
      .content-block {
        background: #1f2937;
        border-color: #374151;
        color: #e2e8f0;
      }
    }
    
    .action-section {
      background: #374151;
      border-color: #4b5563;
    }
    
    .tech-table {
      :deep(.el-table__header) {
        th {
          background: linear-gradient(135deg, #374151 0%, #4b5563 100%);
          color: #e2e8f0;
          border-color: #6b7280;
        }
      }
      
      :deep(.el-table__row:hover) {
        background: #374151;
      }
    }
  }
}

// 焦点指示器可访问性优化
.tech-btn:focus-visible,
.tech-input :deep(.el-input__wrapper:focus-visible),
.tech-select :deep(.el-select__wrapper:focus-visible) {
  outline: 2px solid #667eea;
  outline-offset: 2px;
}

// 选择文本样式
::selection {
  background: rgba(102, 126, 234, 0.3);
  color: inherit;
}

// 占位符样式优化
:deep(::placeholder) {
  color: #9ca3af;
  opacity: 1;
}

// 禁用状态样式优化
:deep(.is-disabled) {
  opacity: 0.6;
  cursor: not-allowed;
}

// 最后一行样式优化
.tech-table {
  :deep(.el-table__row:last-child) {
    border-bottom: none;
  }
}

// 表格斑马纹样式
.tech-table.striped {
  :deep(.el-table__row--striped) {
    background: linear-gradient(135deg, rgba(248, 250, 252, 0.5) 0%, rgba(241, 245, 249, 0.5) 100%);
    
    &:hover {
      background: linear-gradient(135deg, rgba(248, 250, 252, 0.8) 0%, rgba(241, 245, 249, 0.8) 100%);
    }
  }
}

// 工具提示样式优化
:deep(.el-tooltip__popper) {
  border-radius: 8px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
  border: none;
  
  &.is-dark {
    background: linear-gradient(135deg, #374151 0%, #4b5563 100%);
  }
}

// 下拉菜单样式优化
:deep(.el-dropdown-menu) {
  border-radius: 12px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
  border: none;
  padding: 8px 0;
  
  .el-dropdown-menu__item {
    padding: 8px 16px;
    transition: all 0.3s ease;
    
    &:hover {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
    }
  }
}

// 徽章样式优化
:deep(.el-badge) {
  .el-badge__content {
    background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
    border: none;
    font-weight: 600;
  }
}

// 步骤条样式优化
:deep(.el-steps) {
  .el-step__head {
    .el-step__icon {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border: none;
      color: white;
    }
    
    &.is-process {
      .el-step__icon {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      }
    }
    
    &.is-success {
      .el-step__icon {
        background: linear-gradient(135deg, #10b981 0%, #059669 100%);
      }
    }
    
    &.is-wait {
      .el-step__icon {
        background: #d1d5db;
      }
    }
  }
}

// 时间线样式优化
:deep(.el-timeline) {
  .el-timeline-item {
    .el-timeline-item__node {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border: none;
    }
    
    .el-timeline-item__tail {
      border-color: #e2e8f0;
    }
  }
}

// 骨架屏样式优化
:deep(.el-skeleton) {
  .el-skeleton__item {
    background: linear-gradient(90deg, #f1f5f9 25%, #e2e8f0 50%, #f1f5f9 75%);
    background-size: 400% 100%;
    animation: el-skeleton-loading 1.4s ease infinite;
  }
}

@keyframes el-skeleton-loading {
  0% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0 50%;
  }
}

// 图片预览样式优化
:deep(.el-image-viewer) {
  .el-image-viewer__wrapper {
    background: rgba(0, 0, 0, 0.8);
    backdrop-filter: blur(10px);
  }
  
  .el-image-viewer__btn {
    background: rgba(255, 255, 255, 0.2);
    backdrop-filter: blur(10px);
    border: none;
    color: white;
    
    &:hover {
      background: rgba(255, 255, 255, 0.3);
    }
  }
}

// 回到顶部样式优化
:deep(.el-backtop) {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
  
  &:hover {
    background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
    box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
  }
}

// 无限滚动样式优化
:deep(.el-scrollbar) {
  .el-scrollbar__bar {
    .el-scrollbar__thumb {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    }
  }
}

// 级联选择器样式优化
:deep(.el-cascader) {
  .el-cascader__dropdown {
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
    border: none;
  }
}

// 颜色选择器样式优化
:deep(.el-color-picker) {
  .el-color-picker__trigger {
    border-radius: 8px;
    border: 1px solid #e2e8f0;
    transition: all 0.3s ease;
    
    &:hover {
      border-color: #667eea;
    }
  }
}

// 评分组件样式优化
:deep(.el-rate) {
  .el-rate__icon {
    font-size: 16px;
    
    .el-rate__decimal {
      color: #fbbf24;
    }
  }
}

// 滑块组件样式优化
:deep(.el-slider) {
  .el-slider__runway {
    background: #e2e8f0;
    
    .el-slider__bar {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    }
    
    .el-slider__button {
      border: none;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      box-shadow: 0 2px 6px rgba(102, 126, 234, 0.3);
    }
  }
}

// 开关组件样式优化
:deep(.el-switch) {
  .el-switch__core {
    border: none;
    background: #d1d5db;
    
    .el-switch__action {
      background: white;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
  }
  
  &.is-checked {
    .el-switch__core {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    }
  }
}

// 上传组件样式优化
:deep(.el-upload) {
  .el-upload-dragger {
    border: 2px dashed #e2e8f0;
    border-radius: 12px;
    transition: all 0.3s ease;
    
    &:hover {
      border-color: #667eea;
      background: rgba(102, 126, 234, 0.05);
    }
  }
}

// 树形组件样式优化
:deep(.el-tree) {
  .el-tree-node {
    .el-tree-node__content {
      border-radius: 6px;
      transition: all 0.3s ease;
      
      &:hover {
        background: rgba(102, 126, 234, 0.1);
      }
      
      &.is-current {
        background: linear-gradient(135deg, rgba(102, 126, 234, 0.2) 0%, rgba(118, 75, 162, 0.2) 100%);
        color: #667eea;
        font-weight: 600;
      }
    }
  }
}

// 日历组件样式优化
:deep(.el-calendar) {
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  border: 1px solid #f1f5f9;
  
  .el-calendar__header {
    background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
    border-radius: 12px 12px 0 0;
    padding: 16px;
    margin: 0;
  }
  
  .el-calendar__body {
    padding: 16px;
  }
  
  .el-calendar-day {
    border-radius: 6px;
    transition: all 0.3s ease;
    
    &:hover {
      background: rgba(102, 126, 234, 0.1);
    }
    
    &.is-selected {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
    }
  }
}

// 最终优化：性能考虑
@media (prefers-reduced-transparency: reduce) {
  .onlinelog-management {
    .main-card {
      backdrop-filter: none;
      background: white;
    }
  }
}

// 打印模式下的进一步优化
@media print {
  .onlinelog-management {
    * {
      color: black !important;
      background: white !important;
    }
    
    .tech-table {
      :deep(.el-table__header) {
        th {
          background: #f1f5f9 !important;
          color: black !important;
        }
      }
    }
  }
}

// 最终样式收尾
.onlinelog-management {
  // 优化字体渲染
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  // 确保所有过渡动画平滑
  * {
    box-sizing: border-box;
  }
  
  // 确保在高DPI设备上清晰显示
  @media (-webkit-min-device-pixel-ratio: 2), (min-resolution: 192dpi) {
    .main-card {
      border: 0.5px solid rgba(0, 0, 0, 0.1);
    }
  }
}

// 确保所有交互元素都有合适的光标
.tech-btn,
.action-btn,
:deep(.el-table__row),
:deep(.el-pagination .number),
:deep(.el-pagination .btn-prev),
:deep(.el-pagination .btn-next) {
  cursor: pointer;
}

:deep(.el-table__row) {
  &.el-table__row--striped {
    cursor: pointer;
  }
}

// 禁用状态的光标
:deep(.is-disabled) {
  cursor: not-allowed !important;
}
</style>