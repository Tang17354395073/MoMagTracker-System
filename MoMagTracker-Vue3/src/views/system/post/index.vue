<template>
   <div class="post-management">
      <div class="main-card">
         <div class="content-block">
            <!-- 搜索区域 -->
            <div class="search-section">
               <el-form :model="queryParams" ref="queryRef" v-show="showSearch" :inline="true" class="search-form">
                  <el-form-item label="岗位编码" prop="postCode">
                     <el-input
                        v-model="queryParams.postCode"
                        placeholder="请输入岗位编码"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="岗位名称" prop="postName">
                     <el-input
                        v-model="queryParams.postName"
                        placeholder="请输入岗位名称"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="状态" prop="status">
                     <el-select 
                        v-model="queryParams.status" 
                        placeholder="岗位状态" 
                        clearable 
                        class="tech-select"
                     >
                        <el-option
                           v-for="dict in sys_normal_disable"
                           :key="dict.value"
                           :label="dict.label"
                           :value="dict.value"
                        />
                     </el-select>
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
                        icon="Plus"
                        @click="handleAdd"
                        v-hasPermi="['system:post:add']"
                        class="tech-btn primary"
                     >新增</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="success"
                        plain
                        icon="Edit"
                        :disabled="single"
                        @click="handleUpdate"
                        v-hasPermi="['system:post:edit']"
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
                        v-hasPermi="['system:post:remove']"
                        class="tech-btn danger"
                     >删除</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="warning"
                        plain
                        icon="Download"
                        @click="handleExport"
                        v-hasPermi="['system:post:export']"
                        class="tech-btn warning"
                     >导出</el-button>
                  </el-col>
                  <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" class="right-toolbar"></right-toolbar>
               </el-row>
            </div>

            <!-- 表格区域 -->
            <div class="table-section">
               <el-table 
                  v-loading="loading" 
                  :data="postList" 
                  @selection-change="handleSelectionChange" 
                  class="tech-table"
               >
                  <el-table-column type="selection" width="55" align="center" />
                  <el-table-column label="岗位编号" align="center" prop="postId" width="180" />
                  <el-table-column label="岗位编码" align="center" prop="postCode" width="200" />
                  <el-table-column label="岗位名称" align="center" prop="postName" width="200" />
                  <el-table-column label="岗位排序" align="center" prop="postSort" width="200">
                     <template #default="scope">
                        <span class="order-badge">{{ scope.row.postSort }}</span>
                     </template>
                  </el-table-column>
                  <el-table-column label="状态" align="center" prop="status" width="200">
                     <template #default="scope">
                        <dict-tag :options="sys_normal_disable" :value="scope.row.status" class="status-tag" />
                     </template>
                  </el-table-column>
                  <el-table-column label="创建时间" align="center" width="240" prop="createTime">
                     <template #default="scope">
                        <span class="time-text">{{ parseTime(scope.row.createTime) }}</span>
                     </template>
                  </el-table-column>
                  <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="240">
                     <template #default="scope">
                        <div class="action-buttons">
                           <el-tooltip content="修改" placement="top">
                              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:post:edit']" class="action-btn edit"></el-button>
                           </el-tooltip>
                           <el-tooltip content="删除" placement="top">
                              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:post:remove']" class="action-btn delete"></el-button>
                           </el-tooltip>
                        </div>
                     </template>
                  </el-table-column>
               </el-table>

               <!-- 分页 -->
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

      <!-- 添加或修改岗位对话框 -->
      <el-dialog :title="title" v-model="open" width="500px" append-to-body class="tech-dialog">
         <el-form ref="postRef" :model="form" :rules="rules" label-width="100px" class="tech-form">
            <el-form-item label="岗位名称" prop="postName">
               <el-input v-model="form.postName" placeholder="请输入岗位名称" class="tech-input" />
            </el-form-item>
            <el-form-item label="岗位编码" prop="postCode">
               <el-input v-model="form.postCode" placeholder="请输入编码名称" class="tech-input" />
            </el-form-item>
            <el-form-item label="岗位顺序" prop="postSort">
               <el-input-number v-model="form.postSort" controls-position="right" :min="0" class="tech-input-number" />
            </el-form-item>
            <el-form-item label="岗位状态" prop="status">
               <el-radio-group v-model="form.status" class="tech-radio-group">
                  <el-radio
                     v-for="dict in sys_normal_disable"
                     :key="dict.value"
                     :value="dict.value"
                     class="tech-radio"
                  >{{ dict.label }}</el-radio>
               </el-radio-group>
            </el-form-item>
            <el-form-item label="备注" prop="remark">
               <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" class="tech-textarea" />
            </el-form-item>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm" class="tech-btn primary">确 定</el-button>
               <el-button @click="cancel" class="tech-btn secondary">取 消</el-button>
            </div>
         </template>
      </el-dialog>
   </div>
</template>

<script setup name="Post">
import { listPost, addPost, delPost, getPost, updatePost } from "@/api/system/post"

const { proxy } = getCurrentInstance()
const { sys_normal_disable } = proxy.useDict("sys_normal_disable")

const postList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    postCode: undefined,
    postName: undefined,
    status: undefined
  },
  rules: {
    postName: [{ required: true, message: "岗位名称不能为空", trigger: "blur" }],
    postCode: [{ required: true, message: "岗位编码不能为空", trigger: "blur" }],
    postSort: [{ required: true, message: "岗位顺序不能为空", trigger: "blur" }],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询岗位列表 */
function getList() {
  loading.value = true
  listPost(queryParams.value).then(response => {
    postList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}

/** 表单重置 */
function reset() {
  form.value = {
    postId: undefined,
    postCode: undefined,
    postName: undefined,
    postSort: 0,
    status: "0",
    remark: undefined
  }
  proxy.resetForm("postRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.postId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加岗位"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const postId = row.postId || ids.value
  getPost(postId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改岗位"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["postRef"].validate(valid => {
    if (valid) {
      if (form.value.postId != undefined) {
        updatePost(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addPost(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const postIds = row.postId || ids.value
  proxy.$modal.confirm('是否确认删除岗位编号为"' + postIds + '"的数据项？').then(function() {
    return delPost(postIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/post/export", {
    ...queryParams.value
  }, `post_${new Date().getTime()}.xlsx`)
}

getList()
</script>

<style scoped lang="scss">
.post-management {
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
    max-width: 1600px;
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
        .order-badge {
          display: inline-flex;
          align-items: center;
          justify-content: center;
          width: 32px;
          height: 32px;
          background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
          color: white;
          border-radius: 8px;
          font-weight: 600;
          font-size: 0.9rem;
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
  }

  .tech-form {
    .el-form-item {
      margin-bottom: 20px;
    }
    
    .el-form-item__label {
      font-weight: 600;
      color: #374151;
    }
  }

  .tech-input-number {
    :deep(.el-input-number__decrease),
    :deep(.el-input-number__increase) {
      background: #f8fafc;
      border-radius: 6px;
    }
  }

  .tech-radio-group {
    :deep(.el-radio) {
      margin-right: 20px;
    }
  }

  .tech-radio {
    :deep(.el-radio__input.is-checked .el-radio__inner) {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border-color: #667eea;
    }
    
    :deep(.el-radio__label) {
      color: #374151;
      font-weight: 500;
    }
  }

  .tech-textarea {
    :deep(.el-textarea__inner) {
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;
      
      &:focus {
        border-color: #667eea;
        box-shadow: 0 0 0 2px rgba(102, 126, 234, 0.1);
      }
    }
  }

  .tech-pagination {
    margin-top: 20px;
    
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
  .post-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .post-management {
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
  }
}

@media (max-width: 480px) {
  .post-management {
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
</style>