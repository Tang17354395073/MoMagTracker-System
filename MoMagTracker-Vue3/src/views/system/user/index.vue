<template>
  <div class="user-management">
    <div class="main-card">
      <splitpanes :horizontal="appStore.device === 'mobile'" class="default-theme">
        <!--部门数据-->
        <pane size="16">
          <div class="content-block dept-block">
            <div class="dept-content">
              <div class="search-container">
                <el-input 
                  v-model="deptName" 
                  placeholder="请输入部门名称" 
                  clearable 
                  prefix-icon="Search" 
                  class="tech-input"
                />
              </div>
              <div class="tree-container">
                <el-tree 
                  :data="deptOptions" 
                  :props="{ label: 'label', children: 'children' }" 
                  :expand-on-click-node="false" 
                  :filter-node-method="filterNode" 
                  ref="deptTreeRef" 
                  node-key="id" 
                  highlight-current 
                  default-expand-all 
                  @node-click="handleNodeClick" 
                  class="tech-tree"
                />
              </div>
            </div>
          </div>
        </pane>
        
        <!--用户数据-->
        <pane size="84">
          <div class="content-block user-block">   
            <!-- 搜索区域 -->
            <div class="search-section">
              <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" class="search-form">
                <el-form-item label="用户名称" prop="userName">
                  <el-input 
                    v-model="queryParams.userName" 
                    placeholder="请输入用户名称" 
                    clearable 
                    class="tech-input"
                    @keyup.enter="handleQuery" 
                  />
                </el-form-item>
                <el-form-item label="手机号码" prop="phonenumber">
                  <el-input 
                    v-model="queryParams.phonenumber" 
                    placeholder="请输入手机号码" 
                    clearable 
                    class="tech-input"
                    @keyup.enter="handleQuery" 
                  />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                  <el-select 
                    v-model="queryParams.status" 
                    placeholder="用户状态" 
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

            <!-- 操作按钮 -->
            <div class="action-section">
              <el-row :gutter="10">
                <el-col :span="1.5">
                  <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['system:user:add']" class="tech-btn primary">新增</el-button>
                </el-col>
                <el-col :span="1.5">
                  <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate" v-hasPermi="['system:user:edit']" class="tech-btn success">修改</el-button>
                </el-col>
                <el-col :span="1.5">
                  <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:user:remove']" class="tech-btn danger">删除</el-button>
                </el-col>
                <el-col :span="1.5">
                  <el-button type="info" plain icon="Upload" @click="handleImport" v-hasPermi="['system:user:import']" class="tech-btn info">导入</el-button>
                </el-col>
                <el-col :span="1.5">
                  <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['system:user:export']" class="tech-btn warning">导出</el-button>
                </el-col>
                <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns" class="right-toolbar"></right-toolbar>
              </el-row>
            </div>

            <!-- 用户表格 -->
            <div class="table-section">
              <el-table 
                v-loading="loading" 
                :data="userList" 
                @selection-change="handleSelectionChange"
                class="tech-table"
              >
                <el-table-column type="selection" width="50" align="center" />
                <el-table-column label="用户编号" align="center" key="userId" prop="userId" v-if="columns.userId.visible" />
                <el-table-column label="用户名称" align="center" key="userName" prop="userName" v-if="columns.userName.visible" :show-overflow-tooltip="true" />
                <el-table-column label="用户昵称" align="center" key="nickName" prop="nickName" v-if="columns.nickName.visible" :show-overflow-tooltip="true" />
                <el-table-column label="部门" align="center" key="deptName" prop="dept.deptName" v-if="columns.deptName.visible" :show-overflow-tooltip="true" />
                <el-table-column label="手机号码" align="center" key="phonenumber" prop="phonenumber" v-if="columns.phonenumber.visible" width="120" />
                <el-table-column label="状态" align="center" key="status" v-if="columns.status.visible">
                  <template #default="scope">
                    <el-switch
                      v-model="scope.row.status"
                      active-value="0"
                      inactive-value="1"
                      @change="handleStatusChange(scope.row)"
                      class="tech-switch"
                    ></el-switch>
                  </template>
                </el-table-column>
                <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns.createTime.visible" width="160">
                  <template #default="scope">
                    <span class="time-text">{{ parseTime(scope.row.createTime) }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
                  <template #default="scope">
                    <div class="action-buttons">
                      <el-tooltip content="修改" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:user:edit']" class="action-btn edit"></el-button>
                      </el-tooltip>
                      <el-tooltip content="删除" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['system:user:remove']" class="action-btn delete"></el-button>
                      </el-tooltip>
                      <el-tooltip content="重置密码" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="Key" @click="handleResetPwd(scope.row)" v-hasPermi="['system:user:resetPwd']" class="action-btn reset"></el-button>
                      </el-tooltip>
                      <el-tooltip content="分配角色" placement="top" v-if="scope.row.userId !== 1">
                        <el-button link type="primary" icon="CircleCheck" @click="handleAuthRole(scope.row)" v-hasPermi="['system:user:edit']" class="action-btn role"></el-button>
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
        </pane>
      </splitpanes>
    </div>

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body class="tech-dialog">
      <el-form :model="form" :rules="rules" ref="userRef" label-width="80px" class="tech-form">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户昵称" prop="nickName">
              <el-input v-model="form.nickName" placeholder="请输入用户昵称" maxlength="30" class="tech-input" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="deptId">
              <el-tree-select 
                v-model="form.deptId" 
                :data="enabledDeptOptions" 
                :props="{ value: 'id', label: 'label', children: 'children' }" 
                value-key="id" 
                placeholder="请选择归属部门" 
                clearable 
                check-strictly 
                class="tech-tree-select"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" class="tech-input" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" class="tech-input" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item v-if="form.userId == undefined" label="用户名称" prop="userName">
              <el-input v-model="form.userName" placeholder="请输入用户名称" maxlength="30" class="tech-input" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入用户密码" type="password" maxlength="20" show-password class="tech-input" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="用户性别">
              <el-select v-model="form.sex" placeholder="请选择" class="tech-select">
                <el-option v-for="dict in sys_user_sex" :key="dict.value" :label="dict.label" :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="form.status" class="tech-radio-group">
                <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :value="dict.value" class="tech-radio">{{ dict.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="岗位">
              <el-select v-model="form.postIds" multiple placeholder="请选择" class="tech-select">
                <el-option v-for="item in postOptions" :key="item.postId" :label="item.postName" :value="item.postId" :disabled="item.status == 1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色">
              <el-select v-model="form.roleIds" multiple placeholder="请选择" class="tech-select">
                <el-option v-for="item in roleOptions" :key="item.roleId" :label="item.roleName" :value="item.roleId" :disabled="item.status == 1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" class="tech-textarea"></el-input>
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

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" v-model="upload.open" width="400px" append-to-body class="tech-dialog">
      <el-upload 
        ref="uploadRef" 
        :limit="1" 
        accept=".xlsx, .xls" 
        :headers="upload.headers" 
        :action="upload.url + '?updateSupport=' + upload.updateSupport" 
        :disabled="upload.isUploading" 
        :on-progress="handleFileUploadProgress" 
        :on-success="handleFileSuccess" 
        :auto-upload="false" 
        drag
        class="tech-upload"
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <template #tip>
          <div class="el-upload__tip text-center">
            <div class="el-upload__tip">
              <el-checkbox v-model="upload.updateSupport" class="tech-checkbox" />是否更新已经存在的用户数据
            </div>
            <span>仅允许导入xls、xlsx格式文件。</span>
            <el-link type="primary" :underline="false" style="font-size: 12px; vertical-align: baseline" @click="importTemplate" class="tech-link">下载模板</el-link>
          </div>
        </template>
      </el-upload>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitFileForm" class="tech-btn primary">确 定</el-button>
          <el-button @click="upload.open = false" class="tech-btn secondary">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="User">
import { getToken } from "@/utils/auth"
import useAppStore from '@/store/modules/app'
import { changeUserStatus, listUser, resetUserPwd, delUser, getUser, updateUser, addUser, deptTreeSelect } from "@/api/system/user"
import { Splitpanes, Pane } from "splitpanes"
import "splitpanes/dist/splitpanes.css"

const router = useRouter()
const appStore = useAppStore()
const { proxy } = getCurrentInstance()
const { sys_normal_disable, sys_user_sex } = proxy.useDict("sys_normal_disable", "sys_user_sex")

const userList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")
const dateRange = ref([])
const deptName = ref("")
const deptOptions = ref(undefined)
const enabledDeptOptions = ref(undefined)
const initPassword = ref(undefined)
const postOptions = ref([])
const roleOptions = ref([])
/*** 用户导入参数 */
const upload = reactive({
  // 是否显示弹出层（用户导入）
  open: false,
  // 弹出层标题（用户导入）
  title: "",
  // 是否禁用上传
  isUploading: false,
  // 是否更新已经存在的用户数据
  updateSupport: 0,
  // 设置上传的请求头部
  headers: { Authorization: "Bearer " + getToken() },
  // 上传的地址
  url: import.meta.env.VITE_APP_BASE_API + "/system/user/importData"
})
// 列显隐信息
const columns = ref({
  userId: { label: '用户编号', visible: true },
  userName: { label: '用户名称', visible: true },
  nickName: { label: '用户昵称', visible: true },
  deptName: { label: '部门', visible: true },
  phonenumber: { label: '手机号码', visible: true },
  status: { label: '状态', visible: true },
  createTime: { label: '创建时间', visible: true }
})

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userName: undefined,
    phonenumber: undefined,
    status: undefined,
    deptId: undefined
  },
  rules: {
    userName: [{ required: true, message: "用户名称不能为空", trigger: "blur" }, { min: 2, max: 20, message: "用户名称长度必须介于 2 和 20 之间", trigger: "blur" }],
    nickName: [{ required: true, message: "用户昵称不能为空", trigger: "blur" }],
    password: [{ required: true, message: "用户密码不能为空", trigger: "blur" }, { min: 5, max: 20, message: "用户密码长度必须介于 5 和 20 之间", trigger: "blur" }, { pattern: /^[^<>"'|\\]+$/, message: "不能包含非法字符：< > \" ' \\\ |", trigger: "blur" }],
    email: [{ type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"] }],
    phonenumber: [{ pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 通过条件过滤节点  */
const filterNode = (value, data) => {
  if (!value) return true
  return data.label.indexOf(value) !== -1
}

/** 根据名称筛选部门树 */
watch(deptName, val => {
  proxy.$refs["deptTreeRef"].filter(val)
})

/** 查询用户列表 */
function getList() {
  loading.value = true
  listUser(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
    loading.value = false
    userList.value = res.rows
    total.value = res.total
  })
}

/** 查询部门下拉树结构 */
function getDeptTree() {
  deptTreeSelect().then(response => {
    deptOptions.value = response.data
    enabledDeptOptions.value = filterDisabledDept(JSON.parse(JSON.stringify(response.data)))
  })
}

/** 过滤禁用的部门 */
function filterDisabledDept(deptList) {
  return deptList.filter(dept => {
    if (dept.disabled) {
      return false
    }
    if (dept.children && dept.children.length) {
      dept.children = filterDisabledDept(dept.children)
    }
    return true
  })
}

/** 节点单击事件 */
function handleNodeClick(data) {
  queryParams.value.deptId = data.id
  handleQuery()
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
  queryParams.value.deptId = undefined
  proxy.$refs.deptTreeRef.setCurrentKey(null)
  handleQuery()
}

/** 删除按钮操作 */
function handleDelete(row) {
  const userIds = row.userId || ids.value
  proxy.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function () {
    return delUser(userIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/user/export", {
    ...queryParams.value,
  },`user_${new Date().getTime()}.xlsx`)
}

/** 用户状态修改  */
function handleStatusChange(row) {
  let text = row.status === "0" ? "启用" : "停用"
  proxy.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗?').then(function () {
    return changeUserStatus(row.userId, row.status)
  }).then(() => {
    proxy.$modal.msgSuccess(text + "成功")
  }).catch(function () {
    row.status = row.status === "0" ? "1" : "0"
  })
}

/** 更多操作 */
function handleCommand(command, row) {
  switch (command) {
    case "handleResetPwd":
      handleResetPwd(row)
      break
    case "handleAuthRole":
      handleAuthRole(row)
      break
    default:
      break
  }
}

/** 跳转角色分配 */
function handleAuthRole(row) {
  const userId = row.userId
  router.push("/system/user-auth/role/" + userId)
}

/** 重置密码按钮操作 */
function handleResetPwd(row) {
  proxy.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    closeOnClickModal: false,
    inputPattern: /^.{5,20}$/,
    inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
    inputValidator: (value) => {
      if (/<|>|"|'|\||\\/.test(value)) {
        return "不能包含非法字符：< > \" ' \\\ |"
      }
    },
  }).then(({ value }) => {
    resetUserPwd(row.userId, value).then(response => {
      proxy.$modal.msgSuccess("修改成功，新密码是：" + value)
    })
  }).catch(() => {})
}

/** 选择条数  */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 导入按钮操作 */
function handleImport() {
  upload.title = "用户导入"
  upload.open = true
}

/** 下载模板操作 */
function importTemplate() {
  proxy.download("system/user/importTemplate", {
  }, `user_template_${new Date().getTime()}.xlsx`)
}

/**文件上传中处理 */
const handleFileUploadProgress = (event, file, fileList) => {
  upload.isUploading = true
}

/** 文件上传成功处理 */
const handleFileSuccess = (response, file, fileList) => {
  upload.open = false
  upload.isUploading = false
  proxy.$refs["uploadRef"].handleRemove(file)
  proxy.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true })
  getList()
}

/** 提交上传文件 */
function submitFileForm() {
  proxy.$refs["uploadRef"].submit()
}

/** 重置操作表单 */
function reset() {
  form.value = {
    userId: undefined,
    deptId: undefined,
    userName: undefined,
    nickName: undefined,
    password: undefined,
    phonenumber: undefined,
    email: undefined,
    sex: undefined,
    status: "0",
    remark: undefined,
    postIds: [],
    roleIds: []
  }
  proxy.resetForm("userRef")
}

/** 取消按钮 */
function cancel() {
  open.value = false
  reset()
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  getUser().then(response => {
    postOptions.value = response.posts
    roleOptions.value = response.roles
    open.value = true
    title.value = "添加用户"
    form.value.password = initPassword.value
  })
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const userId = row.userId || ids.value
  getUser(userId).then(response => {
    form.value = response.data
    postOptions.value = response.posts
    roleOptions.value = response.roles
    form.value.postIds = response.postIds
    form.value.roleIds = response.roleIds
    open.value = true
    title.value = "修改用户"
    form.password = ""
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["userRef"].validate(valid => {
    if (valid) {
      if (form.value.userId != undefined) {
        updateUser(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addUser(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

onMounted(() => {
  getDeptTree()
  getList()
  proxy.getConfigKey("sys.user.initPassword").then(response => {
    initPassword.value = response.msg
  })
})
</script>

<style scoped lang="scss">
.user-management {
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

    :deep(.splitpanes) {
      min-height: 700px;

      .splitpanes__pane {
        background: transparent;
      }
      
      .splitpanes__splitter {
        background: #e2e8f0;
        border-radius: 4px;
        margin: 0 5px;
        
        &:hover {
          background: #667eea;
        }
      }
    }

    .content-block {
      background: white;
      border-radius: 16px;
      padding: 25px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
      border: 1px solid #f1f5f9;
      transition: all 0.3s ease;
      height: 100%;

      &:hover {
        transform: translateY(-3px);
        box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
      }

      .block-title {
        display: flex;
        align-items: center;
        gap: 10px;
        font-size: 1.3rem;
        font-weight: 700;
        color: #1e293b;
        margin-bottom: 20px;
        padding-bottom: 15px;
        border-bottom: 2px solid #f1f5f9;

        i {
          color: #667eea;
          font-size: 1.4rem;
        }
      }
    }

    // 部门区块样式
    .dept-block {
      .dept-content {
        .search-container {
          margin-bottom: 20px;
        }

        .tree-container {
          max-height: 600px;
          overflow-y: auto;
        }
      }
    }

    // 用户管理区块样式
    .user-block {
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

  .tech-switch {
    :deep(.el-switch__core) {
      border-radius: 12px;
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

  .tech-tree-select {
    :deep(.el-select__wrapper) {
      border-radius: 8px;
    }
  }

  .tech-radio-group {
    :deep(.el-radio) {
      margin-right: 20px;
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

  .tech-upload {
    :deep(.el-upload-dragger) {
      border-radius: 12px;
      border: 2px dashed #e2e8f0;
      transition: all 0.3s ease;
      
      &:hover {
        border-color: #667eea;
      }
    }
  }

  .tech-checkbox {
    :deep(.el-checkbox__input.is-checked .el-checkbox__inner) {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      border-color: #667eea;
    }
  }

  .tech-link {
    color: #667eea;
    font-weight: 500;
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
  .user-management {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }
}

@media (max-width: 768px) {
  .user-management {
    padding: 15px;

    .main-card {
      padding: 20px;

      .content-block {
        padding: 20px;
      }

      .user-block {
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
      }
    }
  }
}

@media (max-width: 480px) {
  .user-management {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;

      .content-block {
        padding: 15px;
      }
    }
  }
}
</style>