<template>
  <div class="app-container video-monitor">
    <!-- 顶部操作栏 -->
    <div class="top-action-bar">
      <div class="left-actions">
        <!-- 视频源选择 -->
        <div class="video-source-group">
          <div class="section-header">
            <!-- 自定义SVG图标 -->
            <svg-icon icon-class="video-source" class-name="header-icon custom-svg-icon"></svg-icon>
            <span class="section-label">视频源选择</span>
          </div>
          <div class="action-buttons">
            <el-upload
              class="upload-action"
              :show-file-list="false"
              :before-upload="handleVideoUpload"
              accept="video/*"
            >
              <el-button class="source-btn upload-btn">
                <svg-icon icon-class="upload" class-name="btn-svg-icon"></svg-icon>
                上传本地视频
              </el-button>
            </el-upload>
            
            <el-button 
              v-if="!cameraConnected"
              class="source-btn camera-btn"
              @click="connectCamera"
            >
              <svg-icon icon-class="camera-connect" class-name="btn-svg-icon"></svg-icon>
              连接摄像头
            </el-button>
            
            <el-button 
              v-else
              class="source-btn disconnect-btn"
              @click="disconnectCamera"
            >
              <svg-icon icon-class="camera-disconnect" class-name="btn-svg-icon"></svg-icon>
              断开摄像头
            </el-button>
          </div>
        </div>
      </div>
      
      <div class="right-actions">
        <!-- 开始处理按钮 -->
        <el-button 
          class="process-btn"
          :class="{ 'processing': processing }"
          @click="startProcessing"
          :loading="processing"
          :disabled="!videoSrc && !cameraConnected"
          size="large"
        >
          <svg-icon v-if="!processing" icon-class="start-process" class-name="process-svg-icon"></svg-icon>
          <svg-icon v-else icon-class="loading" class-name="process-svg-icon loading-icon"></svg-icon>
          {{ processing ? '处理中...' : '开始处理' }}
        </el-button>
      </div>
    </div>

    <!-- 主内容区 -->
    <div class="main-content">
      <!-- 左侧控制面板 -->
      <div class="control-panel">
        <el-card class="panel-card" shadow="never">
          <!-- 算法设置 -->
          <div class="panel-header-section">
            <div class="panel-header">
              <!-- 自定义SVG图标 -->
              <svg-icon icon-class="algorithm" class-name="header-icon custom-svg-icon"></svg-icon>
              <span class="header-title">算法设置</span>
            </div>
          </div>
          
          <div class="section-block algorithm-section">
            <div class="algorithm-options">
              <div 
                v-for="algo in algorithmOptions" 
                :key="algo.value"
                class="algorithm-item"
                :class="{ 'algorithm-active': algorithm === algo.value }"
                @click="algorithm = algo.value"
              >
                <div class="algorithm-icon" :style="{ background: algo.color }">
                  <svg-icon :icon-class="algo.icon" class-name="algo-svg-icon"></svg-icon>
                </div>
                <div class="algorithm-info">
                  <div class="algorithm-name">{{ algo.label }}</div>
                  <div class="algorithm-desc">{{ algo.desc }}</div>
                </div>
                <div v-if="algorithm === algo.value" class="algorithm-check">
                  <svg-icon icon-class="check" class-name="check-svg-icon"></svg-icon>
                </div>
              </div>
            </div>
          </div>

          <!-- 参数设置 -->
          <div class="panel-header-section">
            <div class="panel-header">
              <!-- 自定义SVG图标 -->
              <svg-icon icon-class="parameter" class-name="header-icon custom-svg-icon"></svg-icon>
              <span class="header-title">参数设置</span>
            </div>
          </div>
          
          <div class="section-block param-section">
            <!-- 参数项保持不变 -->
            <div class="param-item">
              <div class="param-header">
                <span class="param-label">放大倍数</span>
                <div class="param-value-group">
                  <span class="param-value" :style="{ color: getAmplificationColor() }">{{ amplification }}x</span>
                  <span class="param-range">(1-100)</span>
                </div>
              </div>
              <el-slider
                v-model="amplification"
                :min="1"
                :max="100"
                :step="1"
                :show-tooltip="true"
                @change="onAmplificationChange"
              />
            </div>
            
            <div class="param-item">
              <div class="param-header">
                <span class="param-label">上限阈值</span>
                <div class="param-value-group">
                  <span class="param-value" :style="{ color: getUpperThresholdColor() }">{{ upperThreshold.toFixed(1) }}</span>
                  <span class="param-range">(0-1)</span>
                </div>
              </div>
              <el-slider
                v-model="upperThreshold"
                :min="0"
                :max="1"
                :step="0.1"
                :show-tooltip="true"
                @change="onUpperThresholdChange"
              />
            </div>
            
            <div class="param-item">
              <div class="param-header">
                <span class="param-label">下限阈值</span>
                <div class="param-value-group">
                  <span class="param-value" :style="{ color: getLowerThresholdColor() }">{{ lowerThreshold.toFixed(2) }}</span>
                  <span class="param-range">(0-0.1)</span>
                </div>
              </div>
              <el-slider
                v-model="lowerThreshold"
                :min="0"
                :max="0.1"
                :step="0.01"
                :show-tooltip="true"
                @change="onLowerThresholdChange"
              />
            </div>
          </div>

          <!-- 视频状态 -->
          <div class="panel-header-section">
            <div class="panel-header">
              <!-- 自定义SVG图标 -->
              <svg-icon icon-class="video-status" class-name="header-icon custom-svg-icon"></svg-icon>
              <span class="header-title">视频状态</span>
            </div>
          </div>

          <div class="section-block status-section">
            <div class="status-info">
              <div class="status-item">
                <div class="status-icon" :class="getStatusIconClass()">
                  <svg-icon :icon-class="getStatusIcon()" class-name="status-svg-icon"></svg-icon>
                </div>
                <div class="status-content">
                  <div class="status-label">当前源</div>
                  <div class="status-value" :class="getStatusClass()">
                    {{ getVideoStatus() }}
                  </div>
                </div>
              </div>
              
              <div v-if="videoSrc && !cameraConnected" class="status-item">
                <div class="status-icon status-icon-file">
                  <svg-icon icon-class="file" class-name="status-svg-icon"></svg-icon>
                </div>
                <div class="status-content">
                  <div class="status-label">文件大小</div>
                  <div class="status-value">
                    {{ videoFile ? formatFileSize(videoFile.size) : '未知' }}
                  </div>
                </div>
              </div>
              
              <div v-if="cameraConnected" class="status-item">
                <div class="status-icon" :class="{ 'status-icon-recording': isRecording }">
                  <svg-icon v-if="!isRecording" icon-class="camera" class-name="status-svg-icon"></svg-icon>
                  <svg-icon v-else icon-class="camera-recording" class-name="status-svg-icon"></svg-icon>
                </div>
                <div class="status-content">
                  <div class="status-label">录制状态</div>
                  <div class="status-value recording-status" :class="{ 'recording': isRecording }">
                    {{ isRecording ? '录制中' : '待录制' }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </div>

      <!-- 右侧视频预览区 -->
      <div class="video-panel">
        <el-card class="panel-card" shadow="never">
          <!-- 视频预览区标题 -->
          <div class="panel-header-section">
            <div class="panel-header">
              <!-- 自定义SVG图标 -->
              <svg-icon icon-class="video-preview" class-name="header-icon custom-svg-icon"></svg-icon>
              <span class="header-title">视频预览区</span>
            </div>
          </div>
          
          <!-- 视频显示区域 -->
          <div class="video-container">
            <div v-if="videoSrc && !cameraConnected" class="video-player">
              <video
                ref="videoElement"
                :src="videoSrc"
                controls
                class="video-element"
              ></video>
            </div>
            
            <div v-else-if="cameraConnected" class="camera-preview">
              <video ref="cameraElement" autoplay class="camera-element"></video>
              <div class="camera-controls">
                <el-button-group>
                  <el-button 
                    class="camera-action-btn"
                    :class="{ 'recording': isRecording }"
                    @click="toggleRecording"
                    size="small"
                  >
                    <svg-icon v-if="!isRecording" icon-class="record-start" class-name="camera-svg-icon"></svg-icon>
                    <svg-icon v-else icon-class="record-stop" class-name="camera-svg-icon"></svg-icon>
                    {{ isRecording ? '停止录制' : '开始录制' }}
                  </el-button>
                  <el-button 
                    class="camera-action-btn" 
                    @click="captureFrame"
                    size="small"
                  >
                    <svg-icon icon-class="screenshot" class-name="camera-svg-icon"></svg-icon>
                    截图
                  </el-button>
                </el-button-group>
              </div>
            </div>
            
            <div v-else class="video-placeholder">
              <div class="placeholder-icon">
                <svg-icon icon-class="camera-placeholder" class-name="placeholder-svg-icon"></svg-icon>
              </div>
              <div class="placeholder-text">
                <p class="placeholder-title">等待视频输入</p>
                <p class="placeholder-hint">请在上方选择视频源</p>
              </div>
            </div>
          </div>

          <!-- 处理进度 -->
          <div v-if="processing" class="progress-section">
            <div class="panel-header-section">
              <div class="panel-header">
                <svg-icon v-if="processingProgress < 100" icon-class="progress" class-name="header-icon custom-svg-icon loading-icon"></svg-icon>
                <svg-icon v-else icon-class="success" class-name="header-icon custom-svg-icon"></svg-icon>
                <span class="header-title">处理进度</span>
              </div>
            </div>
            <div class="progress-content">
              <div class="progress-header">
                <span class="progress-percent" :style="{ color: getProgressColor() }">
                  {{ processingProgress }}%
                </span>
                <span class="progress-status">
                  {{ formatProgress(processingProgress) }}
                </span>
              </div>
              <el-progress 
                :percentage="processingProgress" 
                :stroke-width="10"
                :format="formatProgress"
                :color="getProgressColor()"
                class="custom-progress"
              />
            </div>
          </div>

          <!-- 处理结果 -->
          <div v-if="processedVideo" class="result-section">
            <div class="panel-header-section">
              <div class="panel-header">
                <svg-icon icon-class="result" class-name="header-icon custom-svg-icon"></svg-icon>
                <span class="header-title">处理结果</span>
              </div>
            </div>
            <div class="result-content">
              <div class="result-video-container">
                <video :src="processedVideo" controls class="result-video"></video>
              </div>
              <div class="result-actions">
                <el-button class="download-btn" @click="downloadResult">
                  <svg-icon icon-class="download-white" class-name="btn-svg-icon"></svg-icon>
                  下载处理结果
                </el-button>
                <el-button class="share-btn" @click="shareResult">
                  <svg-icon icon-class="share" class-name="btn-svg-icon"></svg-icon>
                  分享
                </el-button>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import * as videoApi from '@/api/data'

export default {
  name: 'VideoMonitor',
  data() {
    return {
      algorithm: 'lagrangian',
      amplification: 20,
      upperThreshold: 0.5,
      lowerThreshold: 0.03,
      videoSrc: '',
      cameraConnected: false,
      processing: false,
      processingProgress: 0,
      processedVideo: '',
      videoFile: null,
      mediaStream: null,
      isRecording: false,
      mediaRecorder: null,
      recordedChunks: [],
      currentTaskId: null,
      algorithmOptions: [
        {
          value: 'lagrangian',
          label: '拉格朗日视角视频运动放大',
          desc: '通过跟踪像素点的运动轨迹来实现运动放大，适合处理较大的运动',
          icon: 'lagrangian-icon', // 修改为自定义SVG图标名称
          color: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)'
        },
        {
          value: 'eulerian',
          label: '欧拉视角视频运动放大',
          desc: '通过分析像素值的变化来放大运动，适合处理细微的运动',
          icon: 'eulerian-icon', // 修改为自定义SVG图标名称
          color: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)'
        },
        {
          value: 'deeplearning',
          label: '深度学习视频运动放大',
          desc: '使用神经网络学习运动模式，能够处理复杂的运动场景',
          icon: 'deeplearning-icon', // 修改为自定义SVG图标名称
          color: 'linear-gradient(135deg, #4facfe 0%, #00f2fe 100%)'
        }
      ]
    }
  },
  mounted() {
    this.progressTimer = null
  },
  beforeDestroy() {
    this.stopProgressPolling()
    if (this.mediaStream) {
      this.disconnectCamera()
    }
  },
  methods: {
    getVideoStatus() {
      if (this.cameraConnected) return '摄像头已连接'
      if (this.videoSrc) return '视频已加载'
      return '未选择'
    },
    
    getStatusIcon() {
      if (this.cameraConnected) return 'camera'
      if (this.videoSrc) return 'video-play'
      return 'video-pause'
    },
    
    getStatusIconClass() {
      if (this.cameraConnected) return 'status-icon-camera'
      if (this.videoSrc) return 'status-icon-video'
      return 'status-icon-none'
    },
    
    getStatusClass() {
      if (this.cameraConnected) return 'status-connected'
      if (this.videoSrc) return 'status-uploaded'
      return 'status-none'
    },
    
    getProgressColor() {
      if (this.processingProgress < 30) return '#667eea'
      if (this.processingProgress < 70) return '#f093fb'
      return '#4facfe'
    },
    
    getAmplificationColor() {
      if (this.amplification < 20) return '#4cd964'
      if (this.amplification < 50) return '#ff9500'
      if (this.amplification < 80) return '#ff3b30'
      return '#8e44ad'
    },
    
    getUpperThresholdColor() {
      if (this.upperThreshold < 0.3) return '#4cd964'
      if (this.upperThreshold < 0.7) return '#ff9500'
      return '#ff3b30'
    },
    
    getLowerThresholdColor() {
      if (this.lowerThreshold < 0.03) return '#4cd964'
      if (this.lowerThreshold < 0.07) return '#ff9500'
      return '#ff3b30'
    },
    
    onAmplificationChange(value) {
      console.log('放大倍数改变:', value)
    },
    
    onUpperThresholdChange(value) {
      console.log('上限阈值改变:', value)
    },
    
    onLowerThresholdChange(value) {
      console.log('下限阈值改变:', value)
    },
    
    formatFileSize(bytes) {
      if (bytes === 0) return '0 B'
      const k = 1024
      const sizes = ['B', 'KB', 'MB', 'GB']
      const i = Math.floor(Math.log(bytes) / Math.log(k))
      return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i]
    },
    
    async startProcessing() {
      if (!this.videoFile && !this.cameraConnected) {
        this.$message.warning('请先选择视频源')
        return
      }

      this.processing = true
      this.processingProgress = 0
      
      try {
        const formData = new FormData()
        if (this.videoFile) {
          formData.append('video', this.videoFile)
        }
        formData.append('algorithm', this.algorithm)
        formData.append('amplification', this.amplification)
        formData.append('upperThreshold', this.upperThreshold)
        formData.append('lowerThreshold', this.lowerThreshold)
        
        const response = await videoApi.processVideo(formData)
        
        if (response.code === 200) {
          this.currentTaskId = response.data
          this.startProgressPolling(this.currentTaskId)
          this.$message.success('视频处理任务已提交')
        }
      } catch (error) {
        this.$message.error('处理失败：' + error.message)
        this.processing = false
      }
    },
    
    startProgressPolling(taskId) {
      this.stopProgressPolling()
      this.progressTimer = setInterval(async () => {
        try {
          const response = await videoApi.getProcessProgress(taskId)
          if (response.code === 200) {
            this.processingProgress = response.data
            if (this.processingProgress >= 100) {
              this.processing = false
              this.stopProgressPolling()
              this.downloadProcessedVideo(taskId)
            }
          }
        } catch (error) {
          console.error('获取进度失败:', error)
        }
      }, 1000)
    },
    
    stopProgressPolling() {
      if (this.progressTimer) {
        clearInterval(this.progressTimer)
        this.progressTimer = null
      }
    },
    
    async downloadProcessedVideo(taskId) {
      try {
        const response = await videoApi.downloadResult(taskId, {
          responseType: 'blob'
        })
        const url = window.URL.createObjectURL(new Blob([response]))
        this.processedVideo = url
        this.$message.success('视频处理完成')
      } catch (error) {
        this.$message.error('下载处理结果失败：' + error.message)
      }
    },
    
    downloadResult() {
      if (!this.processedVideo) {
        this.$message.warning('没有可下载的结果')
        return
      }
      
      const a = document.createElement('a')
      a.href = this.processedVideo
      a.download = `processed_video_${new Date().getTime()}.mp4`
      document.body.appendChild(a)
      a.click()
      document.body.removeChild(a)
    },
    
    shareResult() {
      this.$message.info('分享功能开发中')
    },
    
    formatProgress(percentage) {
      return percentage === 100 ? '处理完成' : `处理中 ${percentage}%`
    },
    
    handleVideoUpload(file) {
      this.videoFile = file
      this.videoSrc = URL.createObjectURL(file)
      this.cameraConnected = false
      if (this.mediaStream) {
        this.disconnectCamera()
      }
      return false
    },
    
    async connectCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: {
            width: { ideal: 1280 },
            height: { ideal: 720 },
            facingMode: 'environment'
          },
          audio: false
        })
        
        this.mediaStream = stream
        this.cameraConnected = true
        
        if (this.$refs.cameraElement) {
          this.$refs.cameraElement.srcObject = stream
        }
        
        this.$message.success('摄像头连接成功')
      } catch (error) {
        this.$message.error('无法访问摄像头：' + error.message)
      }
    },
    
    disconnectCamera() {
      if (this.mediaStream) {
        this.mediaStream.getTracks().forEach(track => track.stop())
        this.mediaStream = null
      }
      if (this.isRecording) {
        this.stopRecording()
      }
      this.cameraConnected = false
      this.$message.info('摄像头已断开')
    },
    
    toggleRecording() {
      if (this.isRecording) {
        this.stopRecording()
      } else {
        this.startRecording()
      }
    },
    
    startRecording() {
      if (!this.mediaStream) return
      
      this.recordedChunks = []
      this.mediaRecorder = new MediaRecorder(this.mediaStream)
      
      this.mediaRecorder.ondataavailable = (event) => {
        if (event.data.size > 0) {
          this.recordedChunks.push(event.data)
        }
      }
      
      this.mediaRecorder.onstop = () => {
        const blob = new Blob(this.recordedChunks, { type: 'video/webm' })
        this.videoFile = new File([blob], 'recorded_video.webm', { type: 'video/webm' })
        this.videoSrc = URL.createObjectURL(blob)
        this.$message.success('录制完成')
      }
      
      this.mediaRecorder.start()
      this.isRecording = true
      this.$message.info('开始录制视频')
    },
    
    stopRecording() {
      if (this.mediaRecorder && this.mediaRecorder.state !== 'inactive') {
        this.mediaRecorder.stop()
      }
      this.isRecording = false
    },
    
    captureFrame() {
      if (!this.mediaStream || !this.$refs.cameraElement) return
      
      const canvas = document.createElement('canvas')
      canvas.width = this.$refs.cameraElement.videoWidth
      canvas.height = this.$refs.cameraElement.videoHeight
      const ctx = canvas.getContext('2d')
      ctx.drawImage(this.$refs.cameraElement, 0, 0, canvas.width, canvas.height)
      
      canvas.toBlob((blob) => {
        const url = URL.createObjectURL(blob)
        const a = document.createElement('a')
        a.href = url
        a.download = 'screenshot_' + new Date().getTime() + '.png'
        document.body.appendChild(a)
        a.click()
        document.body.removeChild(a)
        URL.revokeObjectURL(url)
        this.$message.success('截图已保存')
      }, 'image/png')
    }
  }
}
</script>

<style scoped lang="scss">
.app-container {
  padding: 20px;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  min-height: calc(100vh - 84px);
}

/* SVG图标样式 */
.custom-svg-icon {
  width: 20px;
  height: 20px;
  margin-right: 12px;
  font-size: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  background-clip: text; /* 标准属性 /
  -webkit-background-clip: text; / 带前缀的属性 */
  -webkit-text-fill-color: transparent;
}

.btn-svg-icon {
  width: 16px;
  height: 16px;
  margin-right: 8px;
}

.process-svg-icon {
  width: 18px;
  height: 18px;
  margin-right: 8px;
  
  &.loading-icon {
    animation: rotate 2s linear infinite;
  }
}

.algo-svg-icon {
  width: 22px;
  height: 22px;
  color: white;
}

.check-svg-icon {
  width: 22px;
  height: 22px;
  color: #4cd964;
}

.status-svg-icon {
  width: 22px;
  height: 22px;
  color: white;
}

.camera-svg-icon {
  width: 16px;
  height: 16px;
  margin-right: 8px;
}

.placeholder-svg-icon {
  width: 80px;
  height: 80px;
  color: #667eea;
  opacity: 0.8;
}

/* 旋转动画 */
@keyframes rotate {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

/* 顶部操作栏 */
.top-action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background: white;
  border-radius: 12px;
  margin-bottom: 20px;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.08);
  
  .left-actions {
    .video-source-group {
      display: flex;
      align-items: center;
      gap: 20px;
      
      .section-header {
        display: flex;
        align-items: center;
        gap: 10px;
        
        .section-label {
          font-weight: 700;
          color: #2c3e50;
          font-size: 18px;
        }
      }
      
      .action-buttons {
        display: flex;
        gap: 15px;
      }
    }
  }
  
  .right-actions {
    .process-btn {
      padding: 14px 36px;
      font-size: 16px;
      font-weight: 700;
      border-radius: 12px;
      border: none;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
      transition: all 0.3s ease;
      
      &:hover:not(:disabled) {
        transform: translateY(-3px);
        box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
      }
      
      &:disabled {
        opacity: 0.6;
        cursor: not-allowed;
      }
      
      &.processing {
        background: linear-gradient(135deg, #ff9500 0%, #ff3b30 100%);
      }
    }
  }
}

/* 主内容区 */
.main-content {
  display: flex;
  gap: 20px;
  
  .control-panel {
    flex: 0 0 380px;
    
    .panel-card {
      border-radius: 12px;
      padding: 0;
      border: none;
      
      :deep(.el-card__body) {
        padding: 0;
      }
    }
  }
  
  .video-panel {
    flex: 1;
    
    .panel-card {
      border-radius: 12px;
      padding: 0;
      border: none;
      
      :deep(.el-card__body) {
        padding: 0;
      }
    }
  }
}

/* 统一的标题栏样式 */
.panel-header-section {
  padding: 18px 24px;
  background: linear-gradient(135deg, #f8fafc 0%, #e2e8f0 100%);
  border-bottom: 2px solid #e2e8f0;
  
  .panel-header {
    display: flex;
    align-items: center;
    
    .header-title {
      font-size: 16px;
      font-weight: 700;
      color: #2c3e50;
    }
  }
}

/* 内容区块样式 */
.section-block {
  padding: 24px;
  
  &.algorithm-section {
    border-bottom: 2px solid #e2e8f0;
  }
  
  &.param-section {
    border-bottom: 2px solid #e2e8f0;
  }
  
  &.status-section {
    border-bottom: none;
  }
}

/* 算法选择 */
.algorithm-options {
  .algorithm-item {
    display: flex;
    align-items: center;
    padding: 16px;
    border: 2px solid #e2e8f0;
    border-radius: 12px;
    margin-bottom: 12px;
    cursor: pointer;
    transition: all 0.3s ease;
    
    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    }
    
    &.algorithm-active {
      border-color: transparent;
      background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0%, rgba(118, 75, 162, 0.1) 100%);
      box-shadow: 0 8px 25px rgba(102, 126, 234, 0.15);
    }
    
    .algorithm-icon {
      width: 48px;
      height: 48px;
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 16px;
    }
    
    .algorithm-info {
      flex: 1;
      
      .algorithm-name {
        font-weight: 700;
        color: #2c3e50;
        margin-bottom: 6px;
        font-size: 15px;
      }
      
      .algorithm-desc {
        color: #718096;
        font-size: 13px;
        line-height: 1.5;
      }
    }
  }
}

/* 参数设置 */
.param-item {
  margin-bottom: 28px;
  
  .param-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;
    
    .param-label {
      color: #4a5568;
      font-weight: 600;
      font-size: 15px;
    }
    
    .param-value-group {
      display: flex;
      align-items: center;
      gap: 8px;
      
      .param-value {
        font-weight: 700;
        font-size: 18px;
      }
      
      .param-range {
        color: #a0aec0;
        font-size: 12px;
        font-weight: 500;
      }
    }
  }
  
  :deep(.el-slider) {
    .el-slider__runway {
      background-color: #e2e8f0;
      height: 8px;
      border-radius: 4px;
    }
    
    .el-slider__bar {
      height: 8px;
      border-radius: 4px;
    }
    
    .el-slider__button {
      width: 20px;
      height: 20px;
      border: 3px solid white;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    }
  }
}

/* 视频状态 */
.status-section {
  .status-info {
    .status-item {
      display: flex;
      align-items: center;
      padding: 16px;
      background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
      border-radius: 12px;
      margin-bottom: 12px;
      
      &:last-child {
        margin-bottom: 0;
      }
      
      .status-icon {
        width: 48px;
        height: 48px;
        border-radius: 12px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-right: 16px;
        
        &.status-icon-camera {
          background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
        }
        
        &.status-icon-video {
          background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }
        
        &.status-icon-none {
          background: linear-gradient(135deg, #a0aec0 0%, #718096 100%);
        }
        
        &.status-icon-file {
          background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
        }
        
        &.status-icon-recording {
          background: linear-gradient(135deg, #ff9500 0%, #ff3b30 100%);
        }
      }
      
      .status-content {
        .status-label {
          color: #718096;
          font-size: 13px;
          margin-bottom: 4px;
        }
        
        .status-value {
          font-weight: 700;
          font-size: 16px;
          
          &.status-connected {
            color: #4cd964;
          }
          
          &.status-uploaded {
            color: #667eea;
          }
          
          &.status-none {
            color: #a0aec0;
          }
          
          &.recording-status {
            &.recording {
              color: #ff3b30;
            }
          }
        }
      }
    }
  }
}

/* 视频预览区 */
.video-container {
  position: relative;
  width: 100%;
  min-height: 450px;
  background: linear-gradient(135deg, #000 0%, #1a202c 100%);
  border-radius: 0 0 12px 12px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  
  .video-player,
  .camera-preview {
    width: 100%;
    height: 100%;
    position: relative;
  }
  
  .video-element,
  .camera-element {
    width: 100%;
    height: 100%;
    object-fit: contain;
  }
  
  .camera-controls {
    position: absolute;
    bottom: 24px;
    left: 50%;
    transform: translateX(-50%);
    z-index: 10;
    
    .camera-action-btn {
      background: rgba(255, 255, 255, 0.95);
      color: #2c3e50;
      border: none;
      border-radius: 25px;
      padding: 10px 20px;
      font-weight: 600;
      transition: all 0.3s ease;
      margin: 0 5px;
      
      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
      }
      
      &.recording {
        background: linear-gradient(135deg, #ff9500 0%, #ff3b30 100%);
        color: white;
      }
    }
  }
  
  .video-placeholder {
    text-align: center;
    color: #a0aec0;
    
    .placeholder-text {
      .placeholder-title {
        font-size: 20px;
        margin-bottom: 8px;
        font-weight: 700;
        color: #e2e8f0;
      }
      
      .placeholder-hint {
        font-size: 15px;
        opacity: 0.8;
        color: #a0aec0;
      }
    }
  }
}

/* 处理进度 */
.progress-section {
  border-top: 2px solid #e2e8f0;
  
  .progress-content {
    padding: 24px;
    
    .progress-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 16px;
      
      .progress-percent {
        font-weight: 800;
        font-size: 28px;
      }
      
      .progress-status {
        color: #4a5568;
        font-weight: 600;
        font-size: 16px;
      }
    }
    
    .custom-progress {
      :deep(.el-progress-bar__outer) {
        background-color: #e2e8f0;
        border-radius: 8px;
        overflow: hidden;
      }
      
      :deep(.el-progress-bar__inner) {
        border-radius: 8px;
        transition: width 0.6s ease;
      }
      
      :deep(.el-progress__text) {
        font-weight: 700;
        color: #2c3e50;
        margin-left: 15px;
        font-size: 16px;
      }
    }
  }
}

/* 处理结果 */
.result-section {
  border-top: 2px solid #e2e8f0;
  
  .result-content {
    padding: 24px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
    
    .result-video-container {
      width: 100%;
      max-width: 700px;
      
      .result-video {
        width: 100%;
        border-radius: 12px;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
      }
    }
    
    .result-actions {
      display: flex;
      gap: 16px;
      
      .download-btn {
        padding: 12px 28px;
        background: linear-gradient(135deg, #4cd964 0%, #2ecc71 100%);
        color: white;
        border: none;
        border-radius: 10px;
        font-weight: 700;
        transition: all 0.3s ease;
        
        &:hover {
          transform: translateY(-3px);
          box-shadow: 0 10px 25px rgba(76, 217, 100, 0.4);
        }
      }
      
      .share-btn {
        padding: 12px 28px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        border: none;
        border-radius: 10px;
        font-weight: 700;
        transition: all 0.3s ease;
        
        &:hover {
          transform: translateY(-3px);
          box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
        }
      }
    }
  }
}

/* 按钮样式 */
.source-btn {
  padding: 12px 24px;
  font-weight: 700;
  border-radius: 10px;
  border: none;
  transition: all 0.3s ease;
  display: inline-flex;
  align-items: center;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
  
  &.upload-btn {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    
    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 12px 25px rgba(102, 126, 234, 0.3);
    }
  }
  
  &.camera-btn {
    background: linear-gradient(135deg, #4cd964 0%, #2ecc71 100%);
    color: white;
    
    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 12px 25px rgba(76, 217, 100, 0.3);
    }
  }
  
  &.disconnect-btn {
    background: linear-gradient(135deg, #ff9500 0%, #ff3b30 100%);
    color: white;
    
    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 12px 25px rgba(255, 149, 0, 0.3);
    }
  }
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .main-content {
    flex-direction: column;
    
    .control-panel {
      flex: none;
      width: 100%;
    }
  }
}

@media (max-width: 768px) {
  .app-container {
    padding: 15px;
  }
  
  .top-action-bar {
    flex-direction: column;
    gap: 20px;
    padding: 15px;
    
    .left-actions {
      width: 100%;
      
      .video-source-group {
        flex-direction: column;
        align-items: flex-start;
        gap: 15px;
        
        .action-buttons {
          width: 100%;
          flex-wrap: wrap;
          
          .source-btn {
            flex: 1;
            min-width: 120px;
          }
        }
      }
    }
    
    .right-actions {
      width: 100%;
      
      .process-btn {
        width: 100%;
      }
    }
  }
  
  .video-container {
    min-height: 350px;
    
    .camera-controls {
      bottom: 15px;
      
      .camera-action-btn {
        padding: 8px 16px;
        font-size: 13px;
      }
    }
    
    .video-placeholder {
      .placeholder-svg-icon {
        width: 60px;
        height: 60px;
      }
    }
  }
  
  .algorithm-options .algorithm-item {
    padding: 12px;
  }
}

@media (max-width: 480px) {
  .app-container {
    padding: 10px;
  }
  
  .top-action-bar {
    padding: 12px;
    
    .video-source-group .section-header {
      .section-label {
        font-size: 16px;
      }
    }
  }
  
  .video-container {
    min-height: 300px;
    
    .camera-controls {
      flex-direction: column;
      gap: 8px;
      bottom: 10px;
      
      .camera-action-btn {
        width: 100%;
        justify-content: center;
      }
    }
    
    .video-placeholder {
      .placeholder-svg-icon {
        width: 50px;
        height: 50px;
      }
      
      .placeholder-text .placeholder-title {
        font-size: 18px;
      }
    }
  }
}
</style>