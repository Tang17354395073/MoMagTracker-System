<template>
   <div class="loginlog-management">
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
                  <el-form-item label="状态" prop="status">
                     <el-select
                        v-model="queryParams.status"
                        placeholder="登录状态"
                        clearable
                        class="tech-select"
                     >
                        <el-option
                           v-for="dict in sys_common_status"
                           :key="dict.value"
                           :label="dict.label"
                           :value="dict.value"
                        />
                     </el-select>
                  </el-form-item>
                  <el-form-item label="登录时间" class="date-range">
                     <el-date-picker
                        v-model="dateRange"
                        value-format="YYYY-MM-DD HH:mm:ss"
                        type="daterange"
                        range-separator="-"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        :default-time="[new Date(2000, 1, 1, 0, 0, 0), new Date(2000, 1, 1, 23, 59, 59)]"
                        class="tech-date-picker"
                     ></el-date-picker>
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
                        icon="Delete"
                        :disabled="multiple"
                        @click="handleDelete"
                        v-hasPermi="['monitor:logininfor:remove']"
                        class="tech-btn danger"
                     >删除</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="success"
                        plain
                        icon="Delete"
                        @click="handleClean"
                        v-hasPermi="['monitor:logininfor:remove']"
                        class="tech-btn success"
                     >清空</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="primary"
                        plain
                        icon="Unlock"
                        :disabled="single"
                        @click="handleUnlock"
                        v-hasPermi="['monitor:logininfor:unlock']"
                        class="tech-btn primary"
                     >解锁</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="warning"
                        plain
                        icon="Download"
                        @click="handleExport"
                        v-hasPermi="['monitor:logininfor:export']"
                        class="tech-btn warning"
                     >导出</el-button>
                  </el-col>
                  <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" class="right-toolbar"></right-toolbar>
               </el-row>
            </div>

            <!-- 表格区域 -->
            <div class="table-section">
               <el-table ref="logininforRef" v-loading="loading" :data="logininforList" @selection-change="handleSelectionChange" :default-sort="defaultSort" @sort-change="handleSortChange" class="tech-table">
                  <el-table-column type="selection" width="55" align="center" />
                  <el-table-column label="访问编号" align="center" prop="infoId" width="100" />
                  <el-table-column label="用户名称" align="center" prop="userName" :show-overflow-tooltip="true" sortable="custom" :sort-orders="['descending', 'ascending']" width="120" />
                  <el-table-column label="地址" align="center" prop="ipaddr" :show-overflow-tooltip="true" width="150" />
                  <el-table-column label="登录地点" align="center" prop="loginLocation" :show-overflow-tooltip="true" width="150" />
                  <el-table-column label="操作系统" align="center" prop="os" :show-overflow-tooltip="true" width="180" />
                  <el-table-column label="浏览器" align="center" prop="browser" :show-overflow-tooltip="true" width="180" />
                  <el-table-column label="登录状态" align="center" prop="status" width="100">
                     <template #default="scope">
                        <dict-tag :options="sys_common_status" :value="scope.row.status" class="status-tag" />
                     </template>
                  </el-table-column>
                  <el-table-column label="描述" align="center" prop="msg" :show-overflow-tooltip="true" width="200" />
                  <el-table-column label="访问时间" align="center" prop="loginTime" sortable="custom" :sort-orders="['descending', 'ascending']" width="240">
                     <template #default="scope">
                        <span class="time-text">{{ parseTime(scope.row.loginTime) }}</span>
                     </template>
                  </el-table-column>
               </el-table>

               <!-- 分页 -->
               <div class="pagination-section">
                  <pagination
                     v-show="total > 0"
                     :total="total"
                     v-model:page="queryParams.pageNum"
                     v-model:limit="queryParams.pageSize"
                     @pagination="getList"
                     class="tech-pagination"
                  />
               </div>
            </div>
         </div>
      </div>
   </div>
</template>

<script setup name="Logininfor">
import { list, delLogininfor, cleanLogininfor, unlockLogininfor } from "@/api/monitor/logininfor"

const { proxy } = getCurrentInstance()
const { sys_common_status } = proxy.useDict("sys_common_status")

const logininforList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const selectName = ref("")
const total = ref(0)
const dateRange = ref([])
const defaultSort = ref({ prop: "loginTime", order: "descending" })

// 查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  ipaddr: undefined,
  userName: undefined,
  status: undefined,
  orderByColumn: undefined,
  isAsc: undefined
})

/** 查询登录日志列表 */
function getList() {
  loading.value = true
  list(proxy.addDateRange(queryParams.value, dateRange.value)).then(response => {
    logininforList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = []
  proxy.resetForm("queryRef")
  queryParams.value.pageNum = 1
  proxy.$refs["logininforRef"].sort(defaultSort.value.prop, defaultSort.value.order)
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.infoId)
  multiple.value = !selection.length
  single.value = selection.length != 1
  selectName.value = selection.map(item => item.userName)
}

/** 排序触发事件 */
function handleSortChange(column, prop, order) {
  queryParams.value.orderByColumn = column.prop
  queryParams.value.isAsc = column.order
  getList()
}

/** 删除按钮操作 */
function handleDelete(row) {
  const infoIds = row.infoId || ids.value
  proxy.$modal.confirm('是否确认删除访问编号为"' + infoIds + '"的数据项?').then(function () {
    return delLogininfor(infoIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 清空按钮操作 */
function handleClean() {
  proxy.$modal.confirm("是否确认清空所有登录日志数据项?").then(function () {
    return cleanLogininfor()
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("清空成功")
  }).catch(() => {})
}

/** 解锁按钮操作 */
function handleUnlock() {
  const username = selectName.value
  proxy.$modal.confirm('是否确认解锁用户"' + username + '"数据项?').then(function () {
    return unlockLogininfor(username)
  }).then(() => {
    proxy.$modal.msgSuccess("用户" + username + "解锁成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download("monitor/logininfor/export", {
    ...queryParams.value,
  }, `logininfor_${new Date().getTime()}.xlsx`)
}

getList()
</script>

<style scoped lang="scss">
.loginlog-management {
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

          .date-range {
            min-width: 320px;
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

        .status-tag {
          :deep(.el-tag) {
            border-radius: 6px;
            font-weight: 500;
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
      }
    }

    :deep(.el-table__row) {
      transition: all 0.3s ease;
      
      &:hover {
        transform: translateY(-1px);
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      }
    }
  }

  .time-text {
    color: #6b7280;
    font-size: 0.9rem;
  }

  .tech-pagination {
    :deep(.el-pagination) {
      .btn-prev, .btn-next, .number, .more {
        border-radius: 6px;
        margin: 0 2px;
      }
      
      .number.active {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        border: none;
      }
    }
  }
}

// 响应式设计
@media (max-width: 1024px) {
  .loginlog-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .loginlog-management {
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

            .date-range {
              min-width: auto;
              width: 100%;
            }
          }
        }

        .action-section {
          .el-col {
            margin-bottom: 10px;
          }
        }
      }
    }
  }
}

@media (max-width: 480px) {
  .loginlog-management {
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
</style>