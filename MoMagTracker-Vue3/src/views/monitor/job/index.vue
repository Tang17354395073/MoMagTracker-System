<template>
   <div class="job-management">
      <div class="main-card">
         <div class="content-block">
            <!-- 搜索区域 -->
            <div class="search-section">
               <el-form :model="queryParams" ref="queryRef" v-show="showSearch" :inline="true" class="search-form">
                  <el-form-item label="任务名称" prop="jobName">
                     <el-input
                        v-model="queryParams.jobName"
                        placeholder="请输入任务名称"
                        clearable
                        class="tech-input"
                        @keyup.enter="handleQuery"
                     />
                  </el-form-item>
                  <el-form-item label="任务组名" prop="jobGroup">
                     <el-select v-model="queryParams.jobGroup" placeholder="请选择任务组名" clearable class="tech-select">
                        <el-option
                           v-for="dict in sys_job_group"
                           :key="dict.value"
                           :label="dict.label"
                           :value="dict.value"
                        />
                     </el-select>
                  </el-form-item>
                  <el-form-item label="任务状态" prop="status">
                     <el-select v-model="queryParams.status" placeholder="请选择任务状态" clearable class="tech-select">
                        <el-option
                           v-for="dict in sys_job_status"
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
                        v-hasPermi="['monitor:job:add']"
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
                        v-hasPermi="['monitor:job:edit']"
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
                        v-hasPermi="['monitor:job:remove']"
                        class="tech-btn danger"
                     >删除</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="warning"
                        plain
                        icon="Download"
                        @click="handleExport"
                        v-hasPermi="['monitor:job:export']"
                        class="tech-btn warning"
                     >导出</el-button>
                  </el-col>
                  <el-col :span="1.5">
                     <el-button
                        type="info"
                        plain
                        icon="Operation"
                        @click="handleJobLog"
                        v-hasPermi="['monitor:job:query']"
                        class="tech-btn info"
                     >日志</el-button>
                  </el-col>
                  <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" class="right-toolbar"></right-toolbar>
               </el-row>
            </div>

            <!-- 表格区域 -->
            <div class="table-section">
               <el-table 
                  ref="jobTableRef"
                  v-loading="loading" 
                  :data="jobList" 
                  @selection-change="handleSelectionChange"
                  class="tech-table"
                  :default-sort="{ prop: 'jobId', order: 'descending' }"
               >
                  <el-table-column type="selection" width="55" align="center" />
                  <el-table-column label="任务编号" width="100" align="center" prop="jobId" class-name="id-column" />
                  <el-table-column label="任务名称" align="center" prop="jobName" :show-overflow-tooltip="true" min-width="100" />
                  <el-table-column label="任务组名" align="center" prop="jobGroup" width="120">
                     <template #default="scope">
                        <dict-tag :options="sys_job_group" :value="scope.row.jobGroup" class="group-tag" />
                     </template>
                  </el-table-column>
                  <el-table-column label="调用目标字符串" align="center" prop="invokeTarget" :show-overflow-tooltip="true" min-width="200" class-name="target-column" />
                  <el-table-column label="cron表达式" align="center" prop="cronExpression" :show-overflow-tooltip="true" width="160" class-name="cron-column" />
                  <el-table-column label="状态" align="center" width="120">
                     <template #default="scope">
                        <div class="status-switch">
                           <el-switch
                              v-model="scope.row.status"
                              active-value="0"
                              inactive-value="1"
                              active-text="启用"
                              inactive-text="停用"
                              @change="handleStatusChange(scope.row)"
                              class="tech-switch"
                           ></el-switch>
                        </div>
                     </template>
                  </el-table-column>
                  <el-table-column label="操作" align="center" width="280" class-name="small-padding fixed-width">
                     <template #default="scope">
                        <div class="action-buttons">
                           <el-tooltip content="修改" placement="top">
                              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['monitor:job:edit']" class="action-btn primary"></el-button>
                           </el-tooltip>
                           <el-tooltip content="删除" placement="top">
                              <el-button link type="danger" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['monitor:job:remove']" class="action-btn danger"></el-button>
                           </el-tooltip>
                           <el-tooltip content="执行一次" placement="top">
                              <el-button link type="success" icon="CaretRight" @click="handleRun(scope.row)" v-hasPermi="['monitor:job:changeStatus']" class="action-btn success"></el-button>
                           </el-tooltip>
                           <el-tooltip content="任务详细" placement="top">
                              <el-button link type="info" icon="View" @click="handleView(scope.row)" v-hasPermi="['monitor:job:query']" class="action-btn info"></el-button>
                           </el-tooltip>
                           <el-tooltip content="调度日志" placement="top">
                              <el-button link type="warning" icon="Operation" @click="handleJobLog(scope.row)" v-hasPermi="['monitor:job:query']" class="action-btn warning"></el-button>
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

      <!-- 添加或修改定时任务对话框 -->
      <el-dialog :title="title" v-model="open" width="820px" append-to-body class="tech-dialog">
         <el-form ref="jobRef" :model="form" :rules="rules" label-width="120px" class="tech-form">
            <el-row :gutter="20">
               <el-col :span="12">
                  <el-form-item label="任务名称" prop="jobName">
                     <el-input v-model="form.jobName" placeholder="请输入任务名称" class="tech-input" />
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="任务分组" prop="jobGroup">
                     <el-select v-model="form.jobGroup" placeholder="请选择" class="tech-select">
                        <el-option
                           v-for="dict in sys_job_group"
                           :key="dict.value"
                           :label="dict.label"
                           :value="dict.value"
                        ></el-option>
                     </el-select>
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item prop="invokeTarget">
                     <template #label>
                        <span class="form-label">
                           调用方法
                           <el-tooltip placement="top" class="tech-tooltip">
                              <template #content>
                                 <div class="tooltip-content">
                                    <p><strong>Bean调用示例：</strong>ryTask.ryParams('ry')</p>
                                    <p><strong>Class类调用示例：</strong>com.ruoyi.quartz.task.RyTask.ryParams('ry')</p>
                                    <p><strong>参数说明：</strong>支持字符串，布尔类型，长整型，浮点型，整型</p>
                                 </div>
                              </template>
                              <el-icon class="help-icon"><question-filled /></el-icon>
                           </el-tooltip>
                        </span>
                     </template>
                     <el-input v-model="form.invokeTarget" placeholder="请输入调用目标字符串" class="tech-input" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="cron表达式" prop="cronExpression">
                     <el-input v-model="form.cronExpression" placeholder="请输入cron执行表达式" class="tech-input">
                        <template #append>
                           <el-button type="primary" @click="handleShowCron" class="cron-btn">
                              生成表达式
                              <i class="el-icon-time el-icon--right"></i>
                           </el-button>
                        </template>
                     </el-input>
                  </el-form-item>
               </el-col>
               <el-col :span="24" v-if="form.jobId !== undefined">
                  <el-form-item label="状态">
                     <el-radio-group v-model="form.status" class="tech-radio-group">
                        <el-radio
                           v-for="dict in sys_job_status"
                           :key="dict.value"
                           :label="dict.value"
                           class="tech-radio"
                        >{{ dict.label }}</el-radio>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="执行策略" prop="misfirePolicy">
                     <el-radio-group v-model="form.misfirePolicy" class="tech-radio-group">
                        <el-radio-button value="1" class="tech-radio-button">立即执行</el-radio-button>
                        <el-radio-button value="2" class="tech-radio-button">执行一次</el-radio-button>
                        <el-radio-button value="3" class="tech-radio-button">放弃执行</el-radio-button>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="是否并发" prop="concurrent">
                     <el-radio-group v-model="form.concurrent" class="tech-radio-group">
                        <el-radio-button value="0" class="tech-radio-button">允许</el-radio-button>
                        <el-radio-button value="1" class="tech-radio-button">禁止</el-radio-button>
                     </el-radio-group>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm" class="tech-btn primary">确 定</el-button>
               <el-button @click="cancel" class="tech-btn secondary">取 消</el-button>
            </div>
         </template>
      </el-dialog>

     <el-dialog title="Cron表达式生成器" v-model="openCron" append-to-body destroy-on-close class="tech-dialog cron-dialog">
       <crontab ref="crontabRef" @hide="openCron=false" @fill="crontabFill" :expression="expression"></crontab>
     </el-dialog>

      <!-- 任务日志详细 -->
      <el-dialog title="任务详细" v-model="openView" width="700px" append-to-body class="tech-dialog view-dialog">
         <el-form :model="form" label-width="120px" class="tech-form">
            <el-row :gutter="20">
               <el-col :span="12">
                  <el-form-item label="任务编号：">
                     <span class="detail-value">{{ form.jobId }}</span>
                  </el-form-item>
                  <el-form-item label="任务名称：">
                     <span class="detail-value">{{ form.jobName }}</span>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="任务分组：">
                     <span class="detail-value">{{ jobGroupFormat(form) }}</span>
                  </el-form-item>
                  <el-form-item label="创建时间：">
                     <span class="detail-value">{{ form.createTime }}</span>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="cron表达式：">
                     <span class="detail-value cron-value">{{ form.cronExpression }}</span>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="下次执行时间：">
                     <span class="detail-value time-value">{{ parseTime(form.nextValidTime) }}</span>
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="调用目标方法：">
                     <span class="detail-value target-value">{{ form.invokeTarget }}</span>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="任务状态：">
                     <div v-if="form.status == 0" class="status-badge success">正常</div>
                     <div v-else-if="form.status == 1" class="status-badge danger">暂停</div>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="是否并发：">
                     <div v-if="form.concurrent == 0" class="status-badge success">允许</div>
                     <div v-else-if="form.concurrent == 1" class="status-badge warning">禁止</div>
                  </el-form-item>
               </el-col>
               <el-col :span="12">
                  <el-form-item label="执行策略：">
                     <div v-if="form.misfirePolicy == 0" class="status-badge info">默认策略</div>
                     <div v-else-if="form.misfirePolicy == 1" class="status-badge success">立即执行</div>
                     <div v-else-if="form.misfirePolicy == 2" class="status-badge warning">执行一次</div>
                     <div v-else-if="form.misfirePolicy == 3" class="status-badge danger">放弃执行</div>
                  </el-form-item>
               </el-col>
            </el-row>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button @click="openView = false" class="tech-btn secondary">关 闭</el-button>
            </div>
         </template>
      </el-dialog>
   </div>
</template>

<script setup name="Job">
import Crontab from '@/components/Crontab'
import { listJob, getJob, delJob, addJob, updateJob, runJob, changeJobStatus } from "@/api/monitor/job"

const router = useRouter()
const { proxy } = getCurrentInstance()
const { sys_job_group, sys_job_status } = proxy.useDict("sys_job_group", "sys_job_status")

const jobList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const openView = ref(false)
const openCron = ref(false)
const expression = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    jobName: undefined,
    jobGroup: undefined,
    status: undefined
  },
  rules: {
    jobName: [{ required: true, message: "任务名称不能为空", trigger: "blur" }],
    invokeTarget: [{ required: true, message: "调用目标字符串不能为空", trigger: "blur" }],
    cronExpression: [{ required: true, message: "cron执行表达式不能为空", trigger: "change" }]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询定时任务列表 */
function getList() {
  loading.value = true
  listJob(queryParams.value).then(response => {
    jobList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 任务组名字典翻译 */
function jobGroupFormat(row, column) {
  return proxy.selectDictLabel(sys_job_group.value, row.jobGroup)
}

/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}

/** 表单重置 */
function reset() {
  form.value = {
    jobId: undefined,
    jobName: undefined,
    jobGroup: undefined,
    invokeTarget: undefined,
    cronExpression: undefined,
    misfirePolicy: 1,
    concurrent: 1,
    status: "0"
  }
  proxy.resetForm("jobRef")
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

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.jobId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

// 更多操作触发
function handleCommand(command, row) {
  switch (command) {
    case "handleRun":
      handleRun(row)
      break
    case "handleView":
      handleView(row)
      break
    case "handleJobLog":
      handleJobLog(row)
      break
    default:
      break
  }
}

// 任务状态修改
function handleStatusChange(row) {
  let text = row.status === "0" ? "启用" : "停用"
  proxy.$modal.confirm('确认要"' + text + '""' + row.jobName + '"任务吗?').then(function () {
    return changeJobStatus(row.jobId, row.status)
  }).then(() => {
    proxy.$modal.msgSuccess(text + "成功")
  }).catch(function () {
    row.status = row.status === "0" ? "1" : "0"
  })
}

/* 立即执行一次 */
function handleRun(row) {
  proxy.$modal.confirm('确认要立即执行一次"' + row.jobName + '"任务吗?').then(function () {
    return runJob(row.jobId, row.jobGroup)
  }).then(() => {
    proxy.$modal.msgSuccess("执行成功")})
  .catch(() => {})
}

/** 任务详细信息 */
function handleView(row) {
  getJob(row.jobId).then(response => {
    form.value = response.data
    openView.value = true
  })
}

/** cron表达式按钮操作 */
function handleShowCron() {
  expression.value = form.value.cronExpression
  openCron.value = true
}

/** 确定后回传值 */
function crontabFill(value) {
  form.value.cronExpression = value
}

/** 任务日志列表查询 */
function handleJobLog(row) {
  const jobId = row.jobId || 0
  router.push('/monitor/job-log/index/' + jobId)
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加任务"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const jobId = row.jobId || ids.value
  getJob(jobId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改任务"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["jobRef"].validate(valid => {
    if (valid) {
      if (form.value.jobId != undefined) {
        updateJob(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addJob(form.value).then(response => {
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
  const jobIds = row.jobId || ids.value
  proxy.$modal.confirm('是否确认删除定时任务编号为"' + jobIds + '"的数据项?').then(function () {
    return delJob(jobIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download("monitor/job/export", {
    ...queryParams.value,
  }, `job_${new Date().getTime()}.xlsx`)
}

getList()
</script>

<style scoped lang="scss">
.job-management {
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

        .status-switch {
          display: flex;
          justify-content: center;
          align-items: center;
        }

        .action-buttons {
          display: flex;
          justify-content: center;
          gap: 8px;
        }

        .id-column {
          .cell {
            font-weight: 600;
            color: #667eea;
          }
        }

        .target-column, .cron-column {
          .cell {
            font-family: 'Monaco', 'Consolas', monospace;
            font-size: 0.85rem;
          }
        }
      }
    }
  }

  // 科技感组件样式
  .tech-input {
    width: 100%;

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
    width: 100%;

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

  .tech-switch {
    :deep(.el-switch__core) {
      border-radius: 12px;
      border: none;
      background: #e2e8f0;
      transition: all 0.3s ease;

      .el-switch__action {
        background: white;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
      }
    }

    :deep(.el-switch.is-checked .el-switch__core) {
      background: linear-gradient(135deg, #10b981 0%, #059669 100%);
    }

    :deep(.el-switch__label) {
      color: #6b7280;
      font-weight: 500;
      
      &.is-active {
        color: #374151;
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

  .action-btn {
    border-radius: 6px;
    padding: 6px;
    font-weight: 500;
    transition: all 0.3s ease;
    
    &.primary {
      background: rgba(102, 126, 234, 0.1);
      color: #667eea;
      
      &:hover {
        background: #667eea;
        color: white;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
      }
    }

    &.success {
      background: rgba(16, 185, 129, 0.1);
      color: #10b981;
      
      &:hover {
        background: #10b981;
        color: white;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(16, 185, 129, 0.3);
      }
    }

    &.danger {
      background: rgba(239, 68, 68, 0.1);
      color: #ef4444;
      
      &:hover {
        background: #ef4444;
        color: white;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(239, 68, 68, 0.3);
      }
    }

    &.warning {
      background: rgba(245, 158, 11, 0.1);
      color: #f59e0b;
      
      &:hover {
        background: #f59e0b;
        color: white;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(245, 158, 11, 0.3);
      }
    }

    &.info {
      background: rgba(107, 114, 128, 0.1);
      color: #6b7280;
      
      &:hover {
        background: #6b7280;
        color: white;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(107, 114, 128, 0.3);
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

  // 对话框样式
  .tech-dialog {
    :deep(.el-dialog) {
      border-radius: 16px;
      box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
      background: white;
      
      .el-dialog__header {
        background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
        border-radius: 16px 16px 0 0;
        padding: 20px;
        margin: 0;
        
        .el-dialog__title {
          font-weight: 700;
          background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
          background-clip: text;
          font-size: 1.25rem;
        }
      }
      
      .el-dialog__body {
        padding: 25px;
      }
      
      .el-dialog__footer {
        background: #f8fafc;
        border-radius: 0 0 16px 16px;
        padding: 20px;
        margin: 0;
      }
    }

    &.cron-dialog {
      :deep(.el-dialog) {
        width: 800px;
        max-width: 90vw;
      }
    }

    &.view-dialog {
      .detail-value {
        font-weight: 500;
        color: #374151;
        
        &.cron-value {
          font-family: 'Monaco', 'Consolas', monospace;
          color: #667eea;
          background: rgba(102, 126, 234, 0.1);
          padding: 4px 8px;
          border-radius: 4px;
        }
        
        &.time-value {
          color: #10b981;
          font-weight: 600;
        }
        
        &.target-value {
          font-family: 'Monaco', 'Consolas', monospace;
          background: #f8fafc;
          padding: 8px 12px;
          border-radius: 6px;
          border: 1px solid #e2e8f0;
          display: block;
          word-break: break-all;
        }
      }
      
      .status-badge {
        display: inline-block;
        padding: 4px 12px;
        border-radius: 20px;
        font-size: 0.85rem;
        font-weight: 600;
        color: white;
        
        &.success {
          background: linear-gradient(135deg, #10b981 0%, #059669 100%);
        }
        
        &.danger {
          background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
        }
        
        &.warning {
          background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
        }
        
        &.info {
          background: linear-gradient(135deg, #6b7280 0%, #4b5563 100%);
        }
      }
    }
  }

  .tech-form {
    .form-label {
      display: flex;
      align-items: center;
      gap: 6px;
      font-weight: 600;
      color: #374151;
    }
    
    .help-icon {
      color: #6b7280;
      cursor: pointer;
      transition: color 0.3s ease;
      
      &:hover {
        color: #667eea;
      }
    }
    
    .cron-btn {
      border-radius: 0 6px 6px 0;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border: none;
      color: white;
      font-weight: 600;
      
      &:hover {
        background: linear-gradient(135deg, #5a6fd8 0%, #6a4190 100%);
      }
    }
  }

  .tech-radio-group {
    :deep(.el-radio) {
      margin-right: 20px;
      
      .el-radio__label {
        color: #374151;
        font-weight: 500;
      }
      
      .el-radio__input.is-checked + .el-radio__label {
        color: #667eea;
      }
    }
    
    :deep(.el-radio-button) {
      .el-radio-button__inner {
        border-radius: 6px;
        margin: 0 4px;
        border: 1px solid #e2e8f0;
        background: white;
        color: #6b7280;
        transition: all 0.3s ease;
        
        &:hover {
          color: #667eea;
          border-color: #667eea;
        }
      }
      
      .el-radio-button__orig-radio:checked + .el-radio-button__inner {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        border-color: #667eea;
        color: white;
        box-shadow: 0 2px 6px rgba(102, 126, 234, 0.3);
      }
    }
  }

  .group-tag {
    :deep(.el-tag) {
      border-radius: 6px;
      font-weight: 500;
      border: none;
      
      &.el-tag--primary {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
      }
      
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
  }

  .tech-tooltip {
    .tooltip-content {
      p {
        margin: 4px 0;
        line-height: 1.4;
        
        strong {
          color: #667eea;
        }
      }
    }
  }
}

// 响应式设计
@media (max-width: 1024px) {
  .job-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .job-management {
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
          
          .action-buttons {
            flex-wrap: wrap;
            gap: 4px;
          }
        }
      }
    }
  }
}

@media (max-width: 480px) {
  .job-management {
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

// 动画效果
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

// 工具提示样式优化
:deep(.el-tooltip__popper) {
  border-radius: 8px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
  border: none;
  
  &.is-dark {
    background: linear-gradient(135deg, #374151 0%, #4b5563 100%);
  }
}

// 最终优化确保所有交互元素都有合适的光标
.tech-btn,
.action-btn,
:deep(.el-table__row),
:deep(.el-pagination .number),
:deep(.el-pagination .btn-prev),
:deep(.el-pagination .btn-next),
:deep(.el-radio),
:deep(.el-radio-button) {
  cursor: pointer;
}

:deep(.is-disabled) {
  cursor: not-allowed !important;
  opacity: 0.6;
}

// 确保在高DPI设备上清晰显示
@media (-webkit-min-device-pixel-ratio: 2), (min-resolution: 192dpi) {
  .job-management {
    .main-card {
      border: 0.5px solid rgba(0, 0, 0, 0.1);
    }
  }
}

// 打印样式优化
@media print {
  .job-management {
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

// 暗色模式支持
@media (prefers-color-scheme: dark) {
  .job-management {
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

// 最终样式收尾
.job-management {
  // 优化字体渲染
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  
  // 确保所有过渡动画平滑
  * {
    box-sizing: border-box;
  }
}
</style>