<template>
   <div class="operlog-management">
      <div class="main-card">
         <div class="content-block">
            <!-- 搜索区域 -->
            <div class="search-section">
               <el-form :model="queryParams" ref="queryRef" v-show="showSearch" :inline="true" class="search-form">
                  <el-form-item label="操作地址" prop="operIp">
                     <el-input
                        v-model="queryParams.operIp"
                        placeholder="请输入操作地址"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="系统模块" prop="title">
                     <el-input
                        v-model="queryParams.title"
                        placeholder="请输入系统模块"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="操作人员" prop="operName">
                     <el-input
                        v-model="queryParams.operName"
                        placeholder="请输入操作人员"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="类型" prop="businessType">
                     <el-select
                        v-model="queryParams.businessType"
                        placeholder="操作类型"
                        clearable
                        class="tech-select"
                     >
                        <el-option
                           v-for="dict in sys_oper_type"
                           :key="dict.value"
                           :label="dict.label"
                           :value="dict.value"
                        />
                     </el-select>
                  </el-form-item>
                  <el-form-item label="状态" prop="status">
                     <el-select
                        v-model="queryParams.status"
                        placeholder="操作状态"
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
                  <el-form-item label="操作时间" class="date-range">
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
                        v-hasPermi="['monitor:operlog:remove']"
                        class="tech-btn danger"
                     >删除</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="success"
                        plain
                        icon="Delete"
                        @click="handleClean"
                        v-hasPermi="['monitor:operlog:remove']"
                        class="tech-btn success"
                     >清空</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="warning"
                        plain
                        icon="Download"
                        @click="handleExport"
                        v-hasPermi="['monitor:operlog:export']"
                        class="tech-btn warning"
                     >导出</el-button>
                  </el-col>
                  <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" class="right-toolbar"></right-toolbar>
               </el-row>
            </div>

            <!-- 表格区域 -->
            <div class="table-section">
               <el-table ref="operlogRef" v-loading="loading" :data="operlogList" @selection-change="handleSelectionChange" :default-sort="defaultSort" @sort-change="handleSortChange" class="tech-table">
                  <el-table-column type="selection" width="50" align="center" />
                  <el-table-column label="日志编号" align="center" prop="operId" width="100" />
                  <el-table-column label="系统模块" align="center" prop="title" :show-overflow-tooltip="true" width="150" />
                  <el-table-column label="操作类型" align="center" prop="businessType" width="150">
                     <template #default="scope">
                        <dict-tag :options="sys_oper_type" :value="scope.row.businessType" class="status-tag" />
                     </template>
                  </el-table-column>
                  <el-table-column label="操作人员" align="center" width="150" prop="operName" :show-overflow-tooltip="true" sortable="custom" :sort-orders="['descending', 'ascending']" />
                  <el-table-column label="操作地址" align="center" prop="operIp" width="150" :show-overflow-tooltip="true" />
                  <el-table-column label="操作状态" align="center" prop="status" width="100">
                     <template #default="scope">
                        <dict-tag :options="sys_common_status" :value="scope.row.status" class="status-tag" />
                     </template>
                  </el-table-column>
                  <el-table-column label="操作日期" align="center" prop="operTime" width="300" sortable="custom" :sort-orders="['descending', 'ascending']">
                     <template #default="scope">
                        <span class="time-text">{{ parseTime(scope.row.operTime) }}</span>
                     </template>
                  </el-table-column>
                  <el-table-column label="消耗时间" align="center" prop="costTime" width="180" :show-overflow-tooltip="true" sortable="custom" :sort-orders="['descending', 'ascending']">
                     <template #default="scope">
                        <span class="cost-time">{{ scope.row.costTime }}<span class="unit">毫秒</span></span>
                     </template>
                  </el-table-column>
                  <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width">
                     <template #default="scope">
                        <div class="action-buttons">
                           <el-tooltip content="详细" placement="top">
                              <el-button link type="primary" icon="View" @click="handleView(scope.row, scope.index)" v-hasPermi="['monitor:operlog:query']" class="action-btn view"></el-button>
                           </el-tooltip>
                        </div>
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

      <!-- 操作日志详细 -->
      <el-dialog title="操作日志详细" v-model="open" width="800px" append-to-body class="tech-dialog">
         <el-form :model="form" label-width="100px" class="detail-form">
            <el-row :gutter="20">
               <el-col :span="12">
                  <el-form-item label="操作模块：" class="detail-item">
                     <div class="detail-value">{{ form.title }} / {{ typeFormat(form) }}</div>
                  </el-form-item>
                  <el-form-item label="登录信息：" class="detail-item">
                     <div class="detail-value">{{ form.operName }} / {{ form.operIp }} / {{ form.operLocation }}</div>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="请求地址：" class="detail-item">
                     <div class="detail-value url-text">{{ form.operUrl }}</div>
                  </el-form-item>
                  <el-form-item label="请求方式：" class="detail-item">
                     <div class="detail-value method-tag">{{ form.requestMethod }}</div>
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="操作方法：" class="detail-item">
                     <div class="detail-value method-text">{{ form.method }}</div>
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="请求参数：" class="detail-item">
                     <div class="detail-value param-text">{{ form.operParam }}</div>
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="返回参数：" class="detail-item">
                     <div class="detail-value result-text">{{ form.jsonResult }}</div>
                  </el-form-item>
               </el-col>
               <el-col :span="8">
                  <el-form-item label="操作状态：" class="detail-item">
                     <div class="detail-value">
                        <el-tag v-if="form.status === 0" type="success" class="status-tag">正常</el-tag>
                        <el-tag v-else-if="form.status === 1" type="danger" class="status-tag">失败</el-tag>
                     </div>
                  </el-form-item>
               </el-col>
               <el-col :span="8">
                  <el-form-item label="消耗时间：" class="detail-item">
                     <div class="detail-value cost-text">{{ form.costTime }}<span class="unit">毫秒</span></div>
                  </el-form-item>
               </el-col>
               <el-col :span="8">
                  <el-form-item label="操作时间：" class="detail-item">
                     <div class="detail-value time-text">{{ parseTime(form.operTime) }}</div>
                  </el-form-item>
               </el-col>
               <el-col :span="24" v-if="form.status === 1">
                  <el-form-item label="异常信息：" class="detail-item">
                     <div class="detail-value error-text">{{ form.errorMsg }}</div>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button @click="open = false" class="tech-btn secondary">关 闭</el-button>
            </div>
         </template>
      </el-dialog>
   </div>
</template>

<script setup name="Operlog">
import { list, delOperlog, cleanOperlog } from "@/api/monitor/operlog"

const { proxy } = getCurrentInstance()
const { sys_oper_type, sys_common_status } = proxy.useDict("sys_oper_type", "sys_common_status")

const operlogList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const dateRange = ref([])
const defaultSort = ref({ prop: "operTime", order: "descending" })

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    operIp: undefined,
    title: undefined,
    operName: undefined,
    businessType: undefined,
    status: undefined
  }
})

const { queryParams, form } = toRefs(data)

/** 查询登录日志 */
function getList() {
  loading.value = true
  list(proxy.addDateRange(queryParams.value, dateRange.value)).then(response => {
    operlogList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 操作日志类型字典翻译 */
function typeFormat(row, column) {
  return proxy.selectDictLabel(sys_oper_type.value, row.businessType)
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
  proxy.$refs["operlogRef"].sort(defaultSort.value.prop, defaultSort.value.order)
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.operId)
  multiple.value = !selection.length
}

/** 排序触发事件 */
function handleSortChange(column, prop, order) {
  queryParams.value.orderByColumn = column.prop
  queryParams.value.isAsc = column.order
  getList()
}

/** 详细按钮操作 */
function handleView(row) {
  open.value = true
  form.value = row
}

/** 删除按钮操作 */
function handleDelete(row) {
  const operIds = row.operId || ids.value
  proxy.$modal.confirm('是否确认删除日志编号为"' + operIds + '"的数据项?').then(function () {
    return delOperlog(operIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 清空按钮操作 */
function handleClean() {
  proxy.$modal.confirm("是否确认清空所有操作日志数据项?").then(function () {
    return cleanOperlog()
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("清空成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download("monitor/operlog/export",{
    ...queryParams.value,
  }, `config_${new Date().getTime()}.xlsx`)
}

getList()
</script>

<style scoped lang="scss">
.operlog-management {
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
    max-width: 1800px;
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

        .cost-time {
          color: #10b981;
          font-weight: 600;
          font-size: 0.9rem;
          
          .unit {
            color: #6b7280;
            font-size: 0.8rem;
            margin-left: 2px;
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

  .action-buttons {
    display: flex;
    justify-content: center;
    gap: 8px;

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
        background: #f1f5f9;
      }
    }
  }

  .time-text {
    color: #6b7280;
    font-size: 0.9rem;
  }

  .tech-dialog {
    :deep(.el-dialog) {
      border-radius: 16px;
      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.2);
      
      .el-dialog__header {
        border-bottom: 1px solid #e2e8f0;
        margin-right: 0;
        
        .el-dialog__title {
          font-weight: 700;
          color: #1e293b;
        }
      }
      
      .el-dialog__body {
        padding: 20px;
      }
    }

    .detail-form {
      .detail-item {
        margin-bottom: 16px;
        
        :deep(.el-form-item__label) {
          font-weight: 600;
          color: #374151;
          font-size: 0.9rem;
        }
      }

      .detail-value {
        padding: 8px 12px;
        background: #f8fafc;
        border-radius: 6px;
        border: 1px solid #e2e8f0;
        font-size: 0.9rem;
        word-break: break-all;
      }

      .url-text {
        color: #667eea;
        font-family: 'Monaco', 'Consolas', monospace;
      }

      .method-tag {
        display: inline-block;
        padding: 4px 8px;
        background: linear-gradient(135deg, #10b981 0%, #059669 100%);
        color: white;
        border-radius: 4px;
        font-weight: 600;
        font-size: 0.8rem;
      }

      .method-text {
        font-family: 'Monaco', 'Consolas', monospace;
        color: #374151;
      }

      .param-text, .result-text, .error-text {
        font-family: 'Monaco', 'Consolas', monospace;
        color: #6b7280;
        max-height: 120px;
        overflow-y: auto;
      }

      .error-text {
        color: #ef4444;
        background: #fef2f2;
        border-color: #fecaca;
      }

      .cost-text {
        color: #10b981;
        font-weight: 600;
        
        .unit {
          color: #6b7280;
          font-size: 0.8rem;
          margin-left: 2px;
        }
      }

      .status-tag {
        :deep(.el-tag) {
          border-radius: 6px;
          font-weight: 500;
          min-width: 50px;
          text-align: center;
        }
      }
    }
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
  .operlog-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .operlog-management {
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

    .tech-dialog {
      width: 95% !important;
      margin: 10px auto;
    }
  }
}

@media (max-width: 480px) {
  .operlog-management {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;

      .content-block {
        padding: 15px;
        min-height: 350px;
      }
    }

    .detail-form {
      .el-row {
        .el-col {
          width: 100%;
          margin-bottom: 15px;
        }
      }
    }
  }
}
</style>