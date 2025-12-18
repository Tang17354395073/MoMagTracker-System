<template>
  <div class="iframe-container">
    <iframe 
      v-if="iframeUrl" 
      :src="iframeUrl" 
      frameborder="0" 
      allowfullscreen
      @load="onIframeLoad"
      @error="onIframeError"
    ></iframe>
    <div v-else class="loading-container">
      <div class="loading-content">
        <el-icon class="loading-icon"><Loading /></el-icon>
        <div class="loading-text">正在加载Dify数据管理页面...</div>
        <div v-if="errorMessage" class="error-message">
          {{ errorMessage }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import iframeMixin from '@/utils/iframeMixin'
import { ElMessage } from 'element-plus'
import { Loading } from '@element-plus/icons-vue'

export default {
  name: 'KnowledgeManagement',
  components: {
    Loading
  },
  mixins: [iframeMixin],
  data() {
    return {
      errorMessage: '',
      hasShownSuccessMessage: false,
    }
  },
  created() {
    console.log('KnowledgeManagement组件已创建')
    console.log('当前路由路径:', this.$route.path)
    console.log('当前路由参数:', this.$route.params)
    this.initializeIframe('/datasets')
  },
  mounted() {
    console.log('KnowledgeManagement组件已挂载')
  },
  methods: {
    onIframeLoad() {
      console.log('Dify数据管理页面iframe加载完成')
      if (!this.hasShownSuccessMessage) {
        ElMessage.success('数据管理页面加载成功') // 更新消息文本
        this.hasShownSuccessMessage = true
      }
    },
    onIframeError() {
      console.error('Dify数据管理页面iframe加载失败')
      this.errorMessage = 'Dify数据管理页面加载失败，请检查网络连接或联系管理员'
      ElMessage.error('Dify数据管理页面加载失败')
    }
  }
}
</script>

<style scoped lang="scss">
.iframe-container {
  width: 100%;
  height: calc(100vh - 50px); /* 减去顶部导航栏高度 */
  margin: 0 auto;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.08);
  scrollbar-width: none;
  -ms-overflow-style: none;
  &::-webkit-scrollbar {
    display: none;
  }

  display: flex;
  flex-direction: column;
  align-items: stretch;
  min-height: 600px;
  position: relative;
}

.iframe-container iframe {
  flex: 1;
  width: 100%;
  min-height: 600px;
  border: none;
}

.loading-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background: #f5f7fa;
}

.loading-content {
  text-align: center;
  padding: 40px;
}

.loading-icon {
  font-size: 48px;
  color: #409eff;
  margin-bottom: 16px;
  animation: rotate 2s linear infinite;
}

.loading-text {
  font-size: 16px;
  color: #606266;
  margin-bottom: 8px;
}

.error-message {
  color: #f56c6c;
  font-size: 14px;
  margin-top: 12px;
  padding: 8px 12px;
  background: #fef0f0;
  border-radius: 4px;
  border: 1px solid #fde2e2;
}

@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>