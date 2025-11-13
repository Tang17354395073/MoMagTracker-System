<template>
  <div class="container">
    <div class="left-board">
      <div class="logo-wrapper">
        <div class="logo">
          <img :src="logo" alt="logo" /> MoMagTracker 表单构建器
        </div>
      </div>
      <el-scrollbar class="left-scrollbar">
        <div class="components-list">
          <div class="components-title">
            <svg-icon icon-class="component" />输入型组件
          </div>
          <draggable class="components-draggable" :list="inputComponents"
            :group="{ name: 'componentsGroup', pull: 'clone', put: false }" :clone="cloneComponent"
            draggable=".components-item" :sort="false" @end="onEnd" item-key="label">
            <template #item="{ element, index }">
              <div :key="index" class="components-item" @click="addComponent(element)">
                <div class="components-body">
                  <svg-icon :icon-class="element.tagIcon" />
                  <span class="component-text">{{ element.label }}</span>
                </div>
              </div>
            </template>
          </draggable>
          <div class="components-title">
            <svg-icon icon-class="component" />选择型组件
          </div>
          <draggable class="components-draggable" :list="selectComponents"
            :group="{ name: 'componentsGroup', pull: 'clone', put: false }" :clone="cloneComponent"
            draggable=".components-item" :sort="false" @end="onEnd" item-key="label">
            <template #item="{ element, index }">
              <div :key="index" class="components-item" @click="addComponent(element)">
                <div class="components-body">
                  <svg-icon :icon-class="element.tagIcon" />
                  <span class="component-text">{{ element.label }}</span>
                </div>
              </div>
            </template>
          </draggable>
          <div class="components-title">
            <svg-icon icon-class="component" /> 布局型组件
          </div>
          <draggable class="components-draggable" :list="layoutComponents"
            :group="{ name: 'componentsGroup', pull: 'clone', put: false }" :clone="cloneComponent"
            draggable=".components-item" :sort="false" @end="onEnd" item-key="label">
            <template #item="{ element, index }">
              <div :key="index" class="components-item" @click="addComponent(element)">
                <div class="components-body">
                  <svg-icon :icon-class="element.tagIcon" />
                  <span class="component-text">{{ element.label }}</span>
                </div>
              </div>
            </template>
          </draggable>
        </div>
      </el-scrollbar>
    </div>
    <div class="center-board">
      <div class="action-bar">
        <el-button icon="Download" type="primary" text @click="download" class="tech-btn-primary">
          导出vue文件
        </el-button>
        <el-button icon="DocumentCopy" type="success" text @click="copy" class="tech-btn-secondary copy-btn-main">
          复制代码
        </el-button>
        <el-button icon="Delete" text @click="empty" type="danger" class="tech-btn-danger delete-btn">
          清空
        </el-button>
      </div>
      <el-scrollbar class="center-scrollbar">
        <el-row class="center-board-row" :gutter="formConf.gutter">
          <el-form :size="formConf.size" :label-position="formConf.labelPosition" :disabled="formConf.disabled"
            :label-width="formConf.labelWidth + 'px'">
            <draggable class="drawing-board" :list="drawingList" :animation="340" group="componentsGroup"
              item-key="label">
              <template #item="{ element, index }">
                <draggable-item :key="element.renderKey" :drawing-list="drawingList" :element="element" :index="index"
                  :active-id="activeId" :form-conf="formConf" @activeItem="activeFormItem" @copyItem="drawingItemCopy"
                  @deleteItem="drawingItemDelete" />
              </template>
            </draggable>
            <div v-show="!drawingList.length" class="empty-info">
              从左侧拖入或点选组件进行表单设计
            </div>
          </el-form>
        </el-row>
      </el-scrollbar>
    </div>
    <right-panel :active-data="activeData" :form-conf="formConf" :show-field="!!drawingList.length"
      @tag-change="tagChange" />

    <code-type-dialog v-model="dialogVisible" title="选择生成类型" :showFileName="showFileName" @confirm="generate" />
    <input id="copyNode" type="hidden" />
  </div>
</template>

<script setup>
import draggable from "vuedraggable/dist/vuedraggable.common"
import ClipboardJS from 'clipboard'
import beautifier from 'js-beautify'
import logo from '@/assets/logo/logo.png'
import { inputComponents, selectComponents, layoutComponents, formConf as formConfData } from '@/utils/generator/config'
import { beautifierConf } from '@/utils/index'
import drawingDefalut from '@/utils/generator/drawingDefalut'
import { makeUpHtml, vueTemplate, vueScript, cssStyle } from '@/utils/generator/html'
import { makeUpJs } from '@/utils/generator/js'
import { makeUpCss } from '@/utils/generator/css'
import Download from '@/plugins/download'
import { ElNotification } from 'element-plus'
import DraggableItem from './DraggableItem'
import RightPanel from './RightPanel'
import CodeTypeDialog from './CodeTypeDialog'
import { onMounted, watch } from 'vue'

const drawingList = ref(drawingDefalut)
const { proxy } = getCurrentInstance()
const dialogVisible = ref(false)
const showFileName = ref(false)
const operationType = ref('')
const idGlobal = ref(100)
const activeData = ref(drawingDefalut[0])
const activeId = ref(drawingDefalut[0].formId)
const generateConf = ref(null)
const formData = ref({})
const formConf = ref(formConfData)
let oldActiveId
let tempActiveData

function activeFormItem(element) {
  activeData.value = element
  activeId.value = element.formId
}
function copy() {
  dialogVisible.value = true
  showFileName.value = false
  operationType.value = 'copy'
}
function download() {
  dialogVisible.value = true
  showFileName.value = true
  operationType.value = 'download'
}
function empty() {
  proxy.$modal.confirm('确定要清空所有组件吗？', '提示', { type: 'warning' }).then(() => {
      idGlobal.value = 100
      drawingList.value = []
    }
  )
}

function onEnd(obj, a) {
  if (obj.from !== obj.to) {
    activeData.value = tempActiveData
    activeId.value = idGlobal.value
  }
}

function addComponent(item) {
  const clone = cloneComponent(item)
  drawingList.value.push(clone)
  activeFormItem(clone)
}

function cloneComponent(origin) {
  const clone = JSON.parse(JSON.stringify(origin))
  clone.formId = ++idGlobal.value
  clone.renderKey = +new Date() // 改变renderKey后可以实现强制更新组件
  if (!clone.layout) clone.layout = 'colFormItem'
  if (clone.layout === 'colFormItem') {
    clone.vModel = `field${idGlobal.value}`
    clone.span = formConf.value.span // 仅在 colFormItem 中设置 span
    // 修正：当 placeholder 不存在时设置为 label，否则追加 label
    if (clone.placeholder === undefined) {
      clone.placeholder = clone.label
    } else {
      clone.placeholder += clone.label
    }
    tempActiveData = clone
  } else if (clone.layout === 'rowFormItem') {
    delete clone.label
    delete clone.span // 修正：rowFormItem 不需要 span 属性
    clone.componentName = `row${idGlobal.value}`
    clone.gutter = formConf.value.gutter
    tempActiveData = clone
  }
  return tempActiveData
}

function drawingItemCopy(item, parent) {
  let clone = JSON.parse(JSON.stringify(item))
  clone = createIdAndKey(clone)
  parent.push(clone)
  activeFormItem(clone)
}


function createIdAndKey(item) {
  item.formId = ++idGlobal.value
  item.renderKey = +new Date()
  if (item.layout === 'colFormItem') {
    item.vModel = `field${idGlobal.value}`
  } else if (item.layout === 'rowFormItem') {
    item.componentName = `row${idGlobal.value}`
  }
  if (Array.isArray(item.children)) {
    item.children = item.children.map(childItem => createIdAndKey(childItem))
  }
  return item
}

function drawingItemDelete(index, parent) {
  parent.splice(index, 1)
  nextTick(() => {
    const len = drawingList.value.length
    if (len) {
      activeFormItem(drawingList.value[len - 1])
    }
  })
}

function tagChange(newTag) {
  newTag = cloneComponent(newTag)
  newTag.vModel = activeData.value.vModel
  newTag.formId = activeId.value
  newTag.span = activeData.value.span
  delete activeData.value.tag
  delete activeData.value.tagIcon
  delete activeData.value.document
  Object.keys(newTag).forEach(key => {
    if (activeData.value[key] !== undefined
      && typeof activeData.value[key] === typeof newTag[key]) {
      newTag[key] = activeData.value[key]
    }
  })
  activeData.value = newTag
  updateDrawingList(newTag, drawingList.value)
}


function updateDrawingList(newTag, list) {
  const index = list.findIndex(item => item.formId === activeId.value)
  if (index > -1) {
    list.splice(index, 1, newTag)
  } else {
    list.forEach(item => {
      if (Array.isArray(item.children)) updateDrawingList(newTag, item.children)
    })
  }
}
function generate(data) {
  generateConf.value = data
  nextTick(() => {
    switch (operationType.value) {
      case 'copy':
        execCopy(data)
        break
      case 'download':
        execDownload(data)
        break
      default:
        break
    }
  })
}

function execDownload(data) {
  const codeStr = generateCode()
  const blob = new Blob([codeStr], { type: 'text/plain;charset=utf-8' })
  Download.saveAs(blob, data.fileName)
}

function execCopy(data) {
  document.getElementById('copyNode').click()
}
function AssembleFormData() {
  formData.value = { fields: JSON.parse(JSON.stringify(drawingList.value)), ...formConf.value }
}
function generateCode() {
  const { type } = generateConf.value
  AssembleFormData()
  const script = vueScript(makeUpJs(formData.value, type))
  const html = vueTemplate(makeUpHtml(formData.value, type))
  const css = cssStyle(makeUpCss(formData.value))
  return beautifier.html(html + script + css, beautifierConf.html)
}
watch(() => activeData.value.label, (val, oldVal) => {
  if (
    activeData.value.placeholder === undefined
    || !activeData.value.tag
    || oldActiveId !== activeId.value
  ) {
    return
  }
  activeData.value.placeholder = activeData.value.placeholder.replace(oldVal, '') + val
})
watch(activeId, (val) => {
  oldActiveId = val
}, { immediate: true })

onMounted(() => {
  const clipboard = new ClipboardJS('#copyNode', {
    text: trigger => {
      const codeStr = generateCode()
      ElNotification({ title: '成功', message: '代码已复制到剪切板，可粘贴。', type: 'success' })
      return codeStr
    }
  })
  clipboard.on('error', e => {
    proxy.$modal.msgError('代码复制失败')
  })
})
</script>

<style lang='scss'>
@use "sass:color";
$primaryColor: #8B5FBF; // 主色调淡紫色
$lightPurple: #E6E6FA; // 更淡的紫色
$darkPurple: #6A4C9C; // 深紫色用于强调
$textDark: #333333; // 深色文字
$textLight: #666666; // 浅色文字
$borderColor: #D8BFD8; // 边框颜色
$white: #FFFFFF; // 白色
$lightGray: #F5F5F5; // 浅灰色背景
$successColor: #10b981;
$dangerColor: #ef4444;
$warningColor: #f59e0b;

.container {
  position: relative;
  width: 100%;
  background: radial-gradient(circle at center, $white 0%, $lightPurple 70%, color.adjust($primaryColor, $lightness: 20%) 100%);
  height: calc(100vh - 50px - 40px);
  overflow: hidden;
  font-family: "Inter", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
  color: $textDark;

  .left-board {
    width: 260px;
    position: absolute;
    left: 0;
    top: 0;
    height: calc(100vh - 50px - 40px);
    background: rgba($white, 0.95);
    backdrop-filter: blur(10px);
    border-right: 1px solid $borderColor;
    box-shadow: 2px 0 8px rgba(0, 0, 0, 0.1);

    .logo-wrapper {
      position: relative;
      height: 42px;
      border-bottom: 1px solid $borderColor;
      box-sizing: border-box;
      background: linear-gradient(135deg, $lightPurple 0%, $white 100%);

      .logo {
        position: absolute;
        left: 12px;
        top: 6px;
        line-height: 30px;
        color: $darkPurple;
        font-weight: 600;
        font-size: 15px;
        white-space: nowrap;
        display: flex;
        align-items: center;
        gap: 8px;

        >img {
          width: 30px;
          height: 30px;
          vertical-align: top;
          border-radius: 6px;
        }
      }
    }

    .left-scrollbar {
      .el-scrollbar__wrap {
        box-sizing: border-box;
        overflow-x: hidden !important;
        margin-bottom: 0 !important;

        .components-list {
          padding: 8px;
          box-sizing: border-box;
          height: 100%;

          .components-title {
            font-size: 14px;
            color: $darkPurple;
            margin: 12px 2px 8px 2px;
            padding: 8px 10px;
            background: rgba($lightPurple, 0.5);
            border-radius: 6px;
            border-left: 3px solid $primaryColor;
            font-weight: 600;
            display: flex;
            align-items: center;
            justify-content: flex-start;
            gap: 8px;
            line-height: 1.2;
            text-align: left;

            .svg-icon {
              color: $primaryColor;
              font-size: 16px;
              flex-shrink: 0;
              line-height: 1;
            }
          }

          .components-draggable {
            padding-bottom: 20px;

            .components-item {
              display: inline-block;
              width: 48%;
              margin: 1%;
              transition: transform 0.2s ease !important;

              .components-body {
                display: flex;
                align-items: center;
                padding: 12px 8px;
                background: $white;
                font-size: 12px;
                cursor: move;
                border: 1px solid $borderColor;
                border-radius: 8px;
                transition: all 0.3s ease;
                color: $textDark;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
                line-height: 1;
                min-height: 40px;
                width: 100%;
                box-sizing: border-box;
                position: relative;

                .svg-icon {
                  color: $primaryColor;
                  font-size: 16px;
                  flex-shrink: 0;
                  line-height: 1;
                  width: 16px;
                  position: absolute;
                  left: 13px;
                  top: 50%;
                  transform: translateY(-50%);
                }

                .component-text {
                  flex: 1;
                  text-align: center;
                  white-space: nowrap;
                  overflow: hidden;
                  text-overflow: ellipsis;
                  padding: 0 10px;
                }

                &:hover {
                  border: 1px solid $primaryColor;
                  background: rgba($lightPurple, 0.3);
                  transform: translateY(-2px);
                  box-shadow: 0 4px 12px rgba(139, 95, 191, 0.2);
                }
              }
            }
          }
        }
      }
    }
  }

  .center-board {
    height: calc(100vh - 50px - 40px);
    width: auto;
    margin: 0 350px 0 260px;
    box-sizing: border-box;
    background: rgba($white, 0.95);
    backdrop-filter: blur(10px);
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);

    .action-bar {
      position: relative;
      height: 42px;
      padding: 0 15px;
      box-sizing: border-box;
      border-bottom: 1px solid $borderColor;
      border-top: none;
      border-left: none;
      display: flex;
      align-items: center;
      justify-content: flex-end;
      background: linear-gradient(135deg, $lightPurple 0%, $white 100%);
    }

    .center-scrollbar {
      height: calc(100vh - 50px - 40px - 42px);
      overflow: hidden;
      border-left: 1px solid $borderColor;
      border-right: 1px solid $borderColor;
      box-sizing: border-box;

      .el-scrollbar__view {
        overflow-x: hidden;
      }

      .center-board-row {
        padding: 12px 12px 15px 12px;
        box-sizing: border-box;

        &>.el-form {
          height: calc(100vh - 50px - 40px - 69px);
          flex: 1;

          .drawing-board {
            height: 100%;
            position: relative;

            .components-body {
              padding: 0;
              margin: 0;
              font-size: 0;
            }

            .sortable-ghost {
              position: relative;
              display: block;
              overflow: hidden;

              &::before {
                content: " ";
                position: absolute;
                left: 0;
                right: 0;
                top: 0;
                height: 3px;
                background: $primaryColor;
                z-index: 2;
              }
            }

            .components-item.sortable-ghost {
              width: 100%;
              height: 60px;
              background: rgba($lightPurple, 0.3);
            }

            .active-from-item {
              &>.el-form-item {
                background: rgba($lightPurple, 0.2);
                border-radius: 8px;
                border: 1px dashed $primaryColor;
              }

              &>.drawing-item-copy,
              &>.drawing-item-delete {
                display: initial;
              }

              &>.component-name {
                color: $primaryColor;
              }

              .el-input__wrapper {
                box-shadow: 0 0 0 1px $primaryColor inset;
              }
            }

            .el-form-item {
              margin-bottom: 15px;
              background: $white;
              border-radius: 6px;
              padding: 8px;
              border: 1px solid transparent;
              transition: all 0.3s ease;
              box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);

              &:hover {
                border-color: $borderColor;
              }
            }
          }

          .drawing-item {
            position: relative;
            cursor: move;

            &.unfocus-bordered:not(.activeFromItem)>div:first-child {
              border: 1px dashed $borderColor;
            }

            .el-form-item {
              padding: 12px 10px;
            }
          }

          .drawing-row-item {
            position: relative;
            cursor: move;
            box-sizing: border-box;
            border: 1px dashed $borderColor;
            border-radius: 6px;
            padding: 0 2px;
            margin-bottom: 15px;
            background: $white;

            .drawing-row-item {
              margin-bottom: 2px;
            }

            .el-col {
              margin-top: 22px;
            }

            .el-form-item {
              margin-bottom: 0;
            }

            .drag-wrapper {
              min-height: 80px;
              flex: 1;
              display: flex;
              flex-wrap: wrap;
            }

            &.active-from-item {
              border: 1px dashed $primaryColor;
              background: rgba($lightPurple, 0.1);
            }

            .component-name {
              position: absolute;
              top: 0;
              left: 0;
              font-size: 12px;
              color: $textDark;
              display: inline-block;
              padding: 0 6px;
              background: rgba($lightPurple, 0.3);
              border-radius: 0 0 6px 0;
            }
          }

          .drawing-item,
          .drawing-row-item {
            &:hover {
              &>.el-form-item {
                background: rgba($lightPurple, 0.1);
                border-radius: 6px;
              }

              &>.drawing-item-copy,
              &>.drawing-item-delete {
                display: initial;
              }
            }

            &>.drawing-item-copy,
            &>.drawing-item-delete {
              display: none;
              position: absolute;
              top: -10px;
              width: 24px;
              height: 24px;
              line-height: 24px;
              text-align: center;
              border-radius: 50%;
              font-size: 12px;
              border: 1px solid;
              cursor: pointer;
              z-index: 1;
              transition: all 0.3s ease;
            }

            &>.drawing-item-copy {
              right: 56px;
              border-color: $primaryColor;
              color: $primaryColor;
              background: $white;

              &:hover {
                background: $primaryColor;
                color: #fff;
                transform: scale(1.1);
              }
            }

            &>.drawing-item-delete {
              right: 24px;
              border-color: $dangerColor;
              color: $dangerColor;
              background: $white;

              &:hover {
                background: $dangerColor;
                color: #fff;
                transform: scale(1.1);
              }
            }
          }

          .empty-info {
            position: absolute;
            top: 46%;
            left: 0;
            right: 0;
            text-align: center;
            font-size: 16px;
            color: $textLight;
            letter-spacing: 2px;
          }
        }
      }
    }
  }
}

// 科技感按钮样式
.tech-btn-primary,
.tech-btn-secondary,
.tech-btn-danger {
  border-radius: 6px;
  font-weight: 600;
  transition: all 0.3s ease;
  border: none;
  font-size: 13px;
  backdrop-filter: blur(10px);
}

.tech-btn-primary {
  background: rgba($lightPurple, 0.7);
  color: $darkPurple;
  border: 1px solid rgba($primaryColor, 0.3);
  box-shadow: 0 2px 8px rgba(139, 95, 191, 0.15);

  &:hover {
    transform: translateY(-1px);
    background: rgba($lightPurple, 0.9);
    box-shadow: 0 4px 15px rgba(139, 95, 191, 0.25);
    color: $darkPurple;
  }

  &:active {
    transform: translateY(0);
    background: rgba($primaryColor, 0.1);
  }
}

.tech-btn-secondary {
  background: rgba($white, 0.8);
  border: 1px solid rgba($borderColor, 0.6);
  color: $textDark;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);

  &:hover {
    transform: translateY(-1px);
    border-color: rgba($primaryColor, 0.4);
    background: rgba($white, 0.95);
    color: $primaryColor;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  }
}

.tech-btn-danger {
  background: rgba(239, 68, 68, 0.1);
  color: #dc2626;
  border: 1px solid rgba(239, 68, 68, 0.3);
  box-shadow: 0 2px 8px rgba(239, 68, 68, 0.1);

  &:hover {
    transform: translateY(-1px);
    background: rgba(239, 68, 68, 0.15);
    box-shadow: 0 4px 15px rgba(239, 68, 68, 0.2);
    color: #dc2626;
  }

  &:active {
    transform: translateY(0);
    background: rgba(239, 68, 68, 0.2);
  }
}

// 美化滚动条
.el-scrollbar {
  .el-scrollbar__bar {
    &.is-vertical {
      width: 6px;
    }
    
    .el-scrollbar__thumb {
      background: rgba(139, 95, 191, 0.3);
      border-radius: 3px;
      
      &:hover {
        background: rgba(139, 95, 191, 0.5);
      }
    }
  }
}

// 美化表单元素
.el-form-item {
  .el-form-item__label {
    color: $textDark;
    font-weight: 500;
  }
}

// 美化输入框
.el-input {
  .el-input__wrapper {
    background: $white;
    border: 1px solid $borderColor;
    border-radius: 6px;
    box-shadow: none;
    
    &:hover {
      border-color: $primaryColor;
    }
    
    &.is-focus {
      border-color: $primaryColor;
      box-shadow: 0 0 0 1px $primaryColor;
    }
  }
  
  .el-input__inner {
    color: $textDark;
    
    &::placeholder {
      color: $textLight;
    }
  }
}
</style>
