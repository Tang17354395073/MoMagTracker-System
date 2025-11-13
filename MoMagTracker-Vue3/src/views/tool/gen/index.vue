<template>
  <div class="gen-management">
    <div class="main-card">
      <div class="content-block">
        <!-- 搜索区域 -->
        <div class="search-section">
          <el-form :model="queryParams" ref="queryRef" v-show="showSearch" :inline="true" class="search-form">
            <el-form-item label="表名称" prop="tableName">
              <el-input
                v-model="queryParams.tableName"
                placeholder="请输入表名称"
                clearable
                class="tech-input"
                @keyup.enter="handleQuery"
              />
            </el-form-item>
            <el-form-item label="表描述" prop="tableComment">
              <el-input
                v-model="queryParams.tableComment"
                placeholder="请输入表描述"
                clearable
                class="tech-input"
                @keyup.enter="handleQuery"
              />
            </el-form-item>
            <el-form-item label="创建时间" class="date-range">
              <el-date-picker
                v-model="dateRange"
                value-format="YYYY-MM-DD"
                type="daterange"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
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
                type="primary"
                plain
                icon="Download"
                :disabled="multiple"
                @click="handleGenTable"
                v-hasPermi="['tool:gen:code']"
                class="tech-btn warning"
              >生成</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="primary"
                plain
                icon="Plus"
                @click="openCreateTable"
                v-hasRole="['admin']"
                class="tech-btn primary"
              >创建</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="info"
                plain
                icon="Upload"
                @click="openImportTable"
                v-hasPermi="['tool:gen:import']"
                class="tech-btn info"
              >导入</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="success"
                plain
                icon="Edit"
                :disabled="single"
                @click="handleEditTable"
                v-hasPermi="['tool:gen:edit']"
                class="tech-btn success"
              >修改</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="danger"
                plain
                icon="Delete"
                :disabled="multiple"
                @click="handleDelete"
                v-hasPermi="['tool:gen:remove']"
                class="tech-btn danger"
              >删除</el-button>
            </el-col>
            <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" class="right-toolbar"></right-toolbar>
          </el-row>
        </div>

        <!-- 表格区域 -->
        <div class="table-section">
          <el-table ref="genRef" v-loading="loading" :data="tableList" @selection-change="handleSelectionChange" :default-sort="defaultSort" @sort-change="handleSortChange" class="tech-table">
            <el-table-column type="selection" align="center" width="55"></el-table-column>
            <el-table-column label="序号" type="index" width="70" align="center">
              <template #default="scope">
                <div class="index-badge">{{(queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1}}</div>
              </template>
            </el-table-column>
            <el-table-column label="表名称" align="center" prop="tableName" :show-overflow-tooltip="true" width="180" />
            <el-table-column label="表描述" align="center" prop="tableComment" :show-overflow-tooltip="true" width="200" />
            <el-table-column label="实体" align="center" prop="className" :show-overflow-tooltip="true" width="180" />
            <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable="custom" :sort-orders="['descending', 'ascending']">
              <template #default="scope">
                <span class="time-text">{{ scope.row.createTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="更新时间" align="center" prop="updateTime" width="180" sortable="custom" :sort-orders="['descending', 'ascending']">
              <template #default="scope">
                <span class="time-text">{{ scope.row.updateTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" width="380" class-name="small-padding fixed-width">
              <template #default="scope">
                <div class="action-buttons">
                  <el-tooltip content="预览" placement="top">
                    <el-button link type="primary" icon="View" @click="handlePreview(scope.row)" v-hasPermi="['tool:gen:preview']" class="action-btn preview"></el-button>
                  </el-tooltip>
                  <el-tooltip content="编辑" placement="top">
                    <el-button link type="primary" icon="Edit" @click="handleEditTable(scope.row)" v-hasPermi="['tool:gen:edit']" class="action-btn edit"></el-button>
                  </el-tooltip>
                  <el-tooltip content="删除" placement="top">
                    <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['tool:gen:remove']" class="action-btn delete"></el-button>
                  </el-tooltip>
                  <el-tooltip content="同步" placement="top">
                    <el-button link type="primary" icon="Refresh" @click="handleSynchDb(scope.row)" v-hasPermi="['tool:gen:edit']" class="action-btn sync"></el-button>
                  </el-tooltip>
                  <el-tooltip content="生成代码" placement="top">
                    <el-button link type="primary" icon="Download" @click="handleGenTable(scope.row)" v-hasPermi="['tool:gen:code']" class="action-btn download"></el-button>
                  </el-tooltip>
                </div>
              </template>
            </el-table-column>
          </el-table>

          <!-- 分页 -->
          <div class="pagination-section">
            <pagination
              v-show="total>0"
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

    <!-- 预览界面 -->
    <el-dialog :title="preview.title" v-model="preview.open" width="80%" top="5vh" append-to-body class="tech-dialog preview-dialog scrollbar">
      <el-tabs v-model="preview.activeName" class="preview-tabs">
        <el-tab-pane
          v-for="(value, key) in preview.data"
          :label="key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm'))"
          :name="key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm'))"
          :key="value"
        >
          <div class="code-header">
            <el-tag type="info" class="file-tag">{{ key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm')) }}</el-tag>
            <el-link :underline="false" icon="DocumentCopy" v-copyText="value" v-copyText:callback="copyTextSuccess" class="copy-btn">
              &nbsp;复制代码
            </el-link>
          </div>
          <div class="code-container">
            <pre class="code-content">{{ value }}</pre>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

    <import-table ref="importRef" @ok="handleQuery" />
    <create-table ref="createRef" @ok="handleQuery" />
  </div>
</template>

<script setup name="Gen">
import { listTable, previewTable, delTable, genCode, synchDb } from "@/api/tool/gen"
import router from "@/router"
import importTable from "./importTable"
import createTable from "./createTable"

const route = useRoute()
const { proxy } = getCurrentInstance()

const tableList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const tableNames = ref([])
const dateRange = ref([])
const uniqueId = ref("")
const defaultSort = ref({ prop: "createTime", order: "descending" })

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    tableName: undefined,
    tableComment: undefined,
    orderByColumn: defaultSort.value.prop,
    isAsc: defaultSort.value.order
  },
  preview: {
    open: false,
    title: "代码预览",
    data: {},
    activeName: "domain.java"
  }
})

const { queryParams, preview } = toRefs(data)

onActivated(() => {
  const time = route.query.t
  if (time != null && time != uniqueId.value) {
    uniqueId.value = time
    queryParams.value.pageNum = Number(route.query.pageNum)
    dateRange.value = []
    proxy.resetForm("queryForm")
    getList()
  }
})

/** 查询表集合 */
function getList() {
  loading.value = true
  listTable(proxy.addDateRange(queryParams.value, dateRange.value)).then(response => {
    tableList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 生成代码操作 */
function handleGenTable(row) {
  const tbNames = row.tableName || tableNames.value
  if (tbNames == "") {
    proxy.$modal.msgError("请选择要生成的数据")
    return
  }
  if (row.genType === "1") {
    genCode(row.tableName).then(response => {
      proxy.$modal.msgSuccess("成功生成到自定义路径：" + row.genPath)
    })
  } else {
    proxy.$download.zip("/tool/gen/batchGenCode?tables=" + tbNames, "ruoyi.zip")
  }
}

/** 同步数据库操作 */
function handleSynchDb(row) {
  const tableName = row.tableName
  proxy.$modal.confirm('确认要强制同步"' + tableName + '"表结构吗？').then(function () {
    return synchDb(tableName)
  }).then(() => {
    proxy.$modal.msgSuccess("同步成功")
  }).catch(() => {})
}

/** 打开导入表弹窗 */
function openImportTable() {
  proxy.$refs["importRef"].show()
}

/** 打开创建表弹窗 */
function openCreateTable() {
  proxy.$refs["createRef"].show()
}

/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = []
  proxy.resetForm("queryRef")
  queryParams.value.pageNum = 1
  proxy.$refs["genRef"].sort(defaultSort.value.prop, defaultSort.value.order)
}

/** 预览按钮 */
function handlePreview(row) {
  previewTable(row.tableId).then(response => {
    preview.value.data = response.data
    preview.value.open = true
    preview.value.activeName = "domain.java"
  })
}

/** 复制代码成功 */
function copyTextSuccess() {
  proxy.$modal.msgSuccess("复制成功")
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.tableId)
  tableNames.value = selection.map(item => item.tableName)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 排序触发事件 */
function handleSortChange(column, prop, order) {
  queryParams.value.orderByColumn = column.prop
  queryParams.value.isAsc = column.order
  getList()
}

/** 修改按钮操作 */
function handleEditTable(row) {
  const tableId = row.tableId || ids.value[0]
  const tableName = row.tableName || tableNames.value[0]
  const params = { pageNum: queryParams.value.pageNum }
  proxy.$tab.openPage("修改[" + tableName + "]生成配置", '/tool/gen-edit/index/' + tableId, params)
}

/** 删除按钮操作 */
function handleDelete(row) {
  const tableIds = row.tableId || ids.value
  proxy.$modal.confirm('是否确认删除表编号为"' + tableIds + '"的数据项？').then(function () {
    return delTable(tableIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

getList()
</script>

<style scoped lang="scss">
.gen-management {
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
            min-width: 240px;
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
      }
    }
  }

  // 科技感组件样式
  .tech-input {
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

    .index-badge {
      display: inline-flex;
      align-items: center;
      justify-content: center;
      width: 28px;
      height: 28px;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      border-radius: 50%;
      font-size: 0.8rem;
      font-weight: 600;
    }

    .time-text {
      color: #6b7280;
      font-size: 0.9rem;
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

  // 预览对话框样式
  .preview-dialog {
    :deep(.el-dialog__body) {
      padding: 0;
    }

    .preview-tabs {
      :deep(.el-tabs__header) {
        margin: 0;
        padding: 0 20px;
        background: #f8fafc;
        border-bottom: 1px solid #e2e8f0;
      }

      :deep(.el-tabs__content) {
        padding: 0;
      }

      .code-header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 16px 20px;
        background: #f8fafc;
        border-bottom: 1px solid #e2e8f0;

        .file-tag {
          border-radius: 6px;
          font-weight: 600;
          border: none;
        }

        .copy-btn {
          color: #667eea;
          font-weight: 500;
          transition: all 0.3s ease;

          &:hover {
            color: #764ba2;
            transform: translateY(-1px);
          }
        }
      }

      .code-container {
        background: #1e293b;
        border-radius: 0 0 8px 8px;
        overflow: hidden;

        .code-content {
          color: #e2e8f0;
          font-family: 'Monaco', 'Consolas', 'Courier New', monospace;
          font-size: 0.9rem;
          line-height: 1.5;
          margin: 0;
          padding: 20px;
          overflow-x: auto;
          white-space: pre-wrap;
          word-wrap: break-word;
        }
      }
    }
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
    }
  }
}

// 响应式设计
@media (max-width: 1024px) {
  .gen-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .gen-management {
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
          }
        }

        .action-section {
          .el-col {
            margin-bottom: 10px;
          }
        }
      }
    }

    .preview-dialog {
      width: 95% !important;
      margin: 10px auto;
    }
  }
}

@media (max-width: 480px) {
  .gen-management {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;

      .content-block {
        padding: 15px;
        min-height: 350px;
      }
    }

    .action-buttons {
      gap: 4px;

      .action-btn {
        width: 28px;
        height: 28px;
      }
    }
  }
}
</style>