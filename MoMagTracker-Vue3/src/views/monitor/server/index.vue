<template>
  <div class="server-monitor">
    <div class="main-card">
      <div class="content-block">
        <el-row :gutter="20">
          <!-- CPU信息卡片 -->
          <el-col :xs="24" :sm="12" :lg="12" class="card-box">
            <el-card class="tech-card cpu-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Cpu class="card-icon" />
                  <span class="card-title">CPU</span>
                  <div class="card-usage" v-if="server.cpu">
                    <div class="usage-percent">{{ server.cpu.used }}%</div>
                    <div class="usage-label">使用率</div>
                  </div>
                </div>
              </template>
              <div class="info-table">
                <div class="info-row" v-if="server.cpu">
                  <div class="info-label">核心数</div>
                  <div class="info-value">{{ server.cpu.cpuNum }} 核</div>
                </div>
                <div class="info-row" v-if="server.cpu">
                  <div class="info-label">用户使用率</div>
                  <div class="info-value">{{ server.cpu.used }}%</div>
                </div>
                <div class="info-row" v-if="server.cpu">
                  <div class="info-label">系统使用率</div>
                  <div class="info-value">{{ server.cpu.sys }}%</div>
                </div>
                <div class="info-row" v-if="server.cpu">
                  <div class="info-label">当前空闲率</div>
                  <div class="info-value success-text">{{ server.cpu.free }}%</div>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 内存信息卡片 -->
          <el-col :xs="24" :sm="12" :lg="12" class="card-box">
            <el-card class="tech-card memory-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Tickets class="card-icon" />
                  <span class="card-title">内存</span>
                  <div class="card-usage" v-if="server.mem">
                    <div class="usage-percent" :class="{'warning-text': server.mem.usage > 80, 'danger-text': server.mem.usage > 90}">
                      {{ server.mem.usage }}%
                    </div>
                    <div class="usage-label">使用率</div>
                  </div>
                </div>
              </template>
              <div class="info-table">
                <div class="info-row header-row">
                  <div class="info-label">属性</div>
                  <div class="info-value">系统内存</div>
                  <div class="info-value">JVM内存</div>
                </div>
                <div class="info-row" v-if="server.mem && server.jvm">
                  <div class="info-label">总内存</div>
                  <div class="info-value">{{ server.mem.total }}G</div>
                  <div class="info-value">{{ server.jvm.total }}M</div>
                </div>
                <div class="info-row" v-if="server.mem && server.jvm">
                  <div class="info-label">已用内存</div>
                  <div class="info-value">{{ server.mem.used }}G</div>
                  <div class="info-value">{{ server.jvm.used }}M</div>
                </div>
                <div class="info-row" v-if="server.mem && server.jvm">
                  <div class="info-label">剩余内存</div>
                  <div class="info-value success-text">{{ server.mem.free }}G</div>
                  <div class="info-value success-text">{{ server.jvm.free }}M</div>
                </div>
                <div class="info-row" v-if="server.mem && server.jvm">
                  <div class="info-label">使用率</div>
                  <div class="info-value" :class="{'warning-text': server.mem.usage > 80, 'danger-text': server.mem.usage > 90}">
                    {{ server.mem.usage }}%
                  </div>
                  <div class="info-value" :class="{'warning-text': server.jvm.usage > 80, 'danger-text': server.jvm.usage > 90}">
                    {{ server.jvm.usage }}%
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 服务器信息卡片 -->
          <el-col :xs="24" class="card-box">
            <el-card class="tech-card server-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Monitor class="card-icon" />
                  <span class="card-title">服务器信息</span>
                </div>
              </template>
              <div class="info-grid">
                <div class="info-item" v-if="server.sys">
                  <div class="info-label">服务器名称</div>
                  <div class="info-value">{{ server.sys.computerName }}</div>
                </div>
                <div class="info-item" v-if="server.sys">
                  <div class="info-label">操作系统</div>
                  <div class="info-value">{{ server.sys.osName }}</div>
                </div>
                <div class="info-item" v-if="server.sys">
                  <div class="info-label">服务器IP</div>
                  <div class="info-value ip-address">{{ server.sys.computerIp }}</div>
                </div>
                <div class="info-item" v-if="server.sys">
                  <div class="info-label">系统架构</div>
                  <div class="info-value">{{ server.sys.osArch }}</div>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- Java虚拟机信息卡片 -->
          <el-col :xs="24" class="card-box">
            <el-card class="tech-card jvm-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <CoffeeCup class="card-icon" />
                  <span class="card-title">Java虚拟机信息</span>
                </div>
              </template>
              <div class="info-grid two-columns">
                <div class="info-item" v-if="server.jvm">
                  <div class="info-label">Java名称</div>
                  <div class="info-value">{{ server.jvm.name }}</div>
                </div>
                <div class="info-item" v-if="server.jvm">
                  <div class="info-label">Java版本</div>
                  <div class="info-value">{{ server.jvm.version }}</div>
                </div>
                <div class="info-item" v-if="server.jvm">
                  <div class="info-label">启动时间</div>
                  <div class="info-value">{{ server.jvm.startTime }}</div>
                </div>
                <div class="info-item" v-if="server.jvm">
                  <div class="info-label">运行时长</div>
                  <div class="info-value">{{ server.jvm.runTime }}</div>
                </div>
                <div class="info-item full-width" v-if="server.jvm">
                  <div class="info-label">安装路径</div>
                  <div class="info-value path-text">{{ server.jvm.home }}</div>
                </div>
                <div class="info-item full-width" v-if="server.sys">
                  <div class="info-label">项目路径</div>
                  <div class="info-value path-text">{{ server.sys.userDir }}</div>
                </div>
                <div class="info-item full-width" v-if="server.jvm">
                  <div class="info-label">运行参数</div>
                  <div class="info-value args-text">{{ server.jvm.inputArgs }}</div>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 磁盘状态卡片 -->
          <el-col :xs="24" class="card-box">
            <el-card class="tech-card disk-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <MessageBox class="card-icon" />
                  <span class="card-title">磁盘状态</span>
                </div>
              </template>
              <div class="disk-table">
                <div class="table-header">
                  <div class="table-cell">盘符路径</div>
                  <div class="table-cell">文件系统</div>
                  <div class="table-cell">盘符类型</div>
                  <div class="table-cell">总大小</div>
                  <div class="table-cell">可用大小</div>
                  <div class="table-cell">已用大小</div>
                  <div class="table-cell">使用率</div>
                </div>
                <div class="table-body" v-if="server.sysFiles">
                  <div class="table-row" v-for="(sysFile, index) in server.sysFiles" :key="index">
                    <div class="table-cell path-cell">{{ sysFile.dirName }}</div>
                    <div class="table-cell">{{ sysFile.sysTypeName }}</div>
                    <div class="table-cell">{{ sysFile.typeName }}</div>
                    <div class="table-cell">{{ sysFile.total }}</div>
                    <div class="table-cell success-text">{{ sysFile.free }}</div>
                    <div class="table-cell">{{ sysFile.used }}</div>
                    <div class="table-cell">
                      <div class="usage-display" :class="{'warning-text': sysFile.usage > 80, 'danger-text': sysFile.usage > 90}">
                        {{ sysFile.usage }}%
                        <div class="usage-bar">
                          <div class="usage-fill" :style="{width: sysFile.usage + '%'}" :class="{'warning-fill': sysFile.usage > 80, 'danger-fill': sysFile.usage > 90}"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script setup>
import { getServer } from '@/api/monitor/server'

const server = ref([])
const { proxy } = getCurrentInstance()

function getList() {
  proxy.$modal.loading("正在加载服务监控数据，请稍候！")
  getServer().then(response => {
    server.value = response.data
    proxy.$modal.closeLoading()
  })
}

getList()
</script>

<style scoped lang="scss">
.server-monitor {
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
      background: transparent;
      border-radius: 0;
      padding: 0;
      box-shadow: none;
      border: none;
      min-height: auto;

      &:hover {
        transform: none;
        box-shadow: none;
      }
    }
  }

  .card-box {
    margin-bottom: 20px;
  }

  .tech-card {
    border-radius: 16px;
    border: 1px solid #f1f5f9;
    transition: all 0.3s ease;
    background: white;

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 12px 40px rgba(0, 0, 0, 0.15);
    }

    :deep(.el-card__header) {
      border-bottom: 1px solid #f1f5f9;
      padding: 20px;
      background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
      border-radius: 16px 16px 0 0;
    }

    .card-header {
      display: flex;
      align-items: center;
      justify-content: space-between;

      .card-icon {
        width: 24px;
        height: 24px;
        color: #667eea;
        margin-right: 12px;
      }

      .card-title {
        font-size: 1.2rem;
        font-weight: 700;
        color: #1e293b;
        flex: 1;
      }

      .card-usage {
        text-align: center;
        .usage-percent {
          font-size: 1.5rem;
          font-weight: 800;
          color: #667eea;
          line-height: 1;
        }
        .usage-label {
          font-size: 0.8rem;
          color: #6b7280;
          margin-top: 4px;
        }
      }
    }

    :deep(.el-card__body) {
      padding: 20px;
    }
  }

  // CPU卡片特殊样式
  .cpu-card {
    .card-header {
      .card-usage .usage-percent {
        color: #667eea;
      }
    }
  }

  // 内存卡片特殊样式
  .memory-card {
    .card-header {
      .card-usage .usage-percent {
        color: #10b981;
        &.warning-text { color: #f59e0b; }
        &.danger-text { color: #ef4444; }
      }
    }
  }

  .info-table {
    .info-row {
      display: flex;
      align-items: center;
      padding: 12px 0;
      border-bottom: 1px solid #f1f5f9;

      &:last-child {
        border-bottom: none;
      }

      &.header-row {
        background: #f8fafc;
        margin: -8px 0 8px;
        padding: 12px 16px;
        border-radius: 8px;
        font-weight: 600;
        color: #374151;
      }

      .info-label {
        flex: 1;
        color: #6b7280;
        font-weight: 500;
        font-size: 0.9rem;
      }

      .info-value {
        flex: 1;
        color: #374151;
        font-weight: 600;
        text-align: center;
      }
    }
  }

  .info-grid {
    display: grid;
    gap: 16px;

    &.two-columns {
      grid-template-columns: 1fr 1fr;
    }

    .info-item {
      display: flex;
      align-items: center;
      padding: 12px 16px;
      background: #f8fafc;
      border-radius: 8px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;

      &:hover {
        background: #f1f5f9;
        transform: translateX(4px);
      }

      &.full-width {
        grid-column: 1 / -1;
      }

      .info-label {
        flex: 0 0 120px;
        color: #6b7280;
        font-weight: 500;
        font-size: 0.9rem;
      }

      .info-value {
        flex: 1;
        color: #374151;
        font-weight: 600;
        word-break: break-all;

        &.path-text, &.args-text {
          font-family: 'Monaco', 'Consolas', monospace;
          font-size: 0.85rem;
          color: #4b5563;
        }

        &.ip-address {
          color: #667eea;
          font-weight: 700;
        }
      }
    }
  }

  .disk-table {
    .table-header {
      display: grid;
      grid-template-columns: 1.5fr 1fr 1fr 1fr 1fr 1fr 1fr;
      gap: 12px;
      padding: 16px;
      background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
      border-radius: 8px;
      margin-bottom: 12px;
      font-weight: 600;
      color: #374151;
      font-size: 0.9rem;
    }

    .table-body {
      .table-row {
        display: grid;
        grid-template-columns: 1.5fr 1fr 1fr 1fr 1fr 1fr 1fr;
        gap: 12px;
        padding: 16px;
        border-bottom: 1px solid #f1f5f9;
        transition: all 0.3s ease;

        &:hover {
          background: #f8fafc;
          border-radius: 8px;
        }

        &:last-child {
          border-bottom: none;
        }

        .table-cell {
          display: flex;
          align-items: center;
          color: #374151;
          font-weight: 500;
          font-size: 0.9rem;

          &.path-cell {
            font-family: 'Monaco', 'Consolas', monospace;
            color: #667eea;
          }

          .usage-display {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 4px;

            .usage-bar {
              width: 60px;
              height: 6px;
              background: #e2e8f0;
              border-radius: 3px;
              overflow: hidden;

              .usage-fill {
                height: 100%;
                background: linear-gradient(135deg, #10b981 0%, #059669 100%);
                border-radius: 3px;
                transition: all 0.3s ease;

                &.warning-fill {
                  background: linear-gradient(135deg, #f59e0b 0%, #d97706 100%);
                }

                &.danger-fill {
                  background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
                }
              }
            }
          }
        }
      }
    }
  }

  // 文本颜色工具类
  .success-text {
    color: #10b981 !important;
    font-weight: 600;
  }

  .warning-text {
    color: #f59e0b !important;
    font-weight: 600;
  }

  .danger-text {
    color: #ef4444 !important;
    font-weight: 600;
  }
}

// 响应式设计
@media (max-width: 1200px) {
  .server-monitor {
    .info-grid.two-columns {
      grid-template-columns: 1fr;
    }

    .disk-table {
      .table-header,
      .table-body .table-row {
        grid-template-columns: 1.5fr 1fr 1fr 1fr 1fr 1fr;
        
        .table-cell:nth-child(3) {
          display: none;
        }
      }
    }
  }
}

@media (max-width: 768px) {
  .server-monitor {
    padding: 15px;

    .main-card {
      padding: 20px;
      margin: 15px;

      .content-block {
        padding: 0;
      }
    }

    .card-header {
      flex-direction: column;
      align-items: flex-start;
      gap: 12px;

      .card-usage {
        align-self: flex-end;
      }
    }

    .info-table .info-row {
      flex-direction: column;
      align-items: flex-start;
      gap: 8px;

      .info-value {
        text-align: left;
        width: 100%;
      }
    }

    .disk-table {
      .table-header,
      .table-body .table-row {
        grid-template-columns: 1fr 1fr 1fr;
        
        .table-cell:nth-child(n+4) {
          display: none;
        }
      }
    }
  }
}

@media (max-width: 480px) {
  .server-monitor {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;
    }

    .info-grid .info-item {
      flex-direction: column;
      align-items: flex-start;
      gap: 8px;

      .info-label {
        flex: none;
      }
    }

    .disk-table {
      .table-header,
      .table-body .table-row {
        grid-template-columns: 1fr 1fr;
        
        .table-cell:nth-child(n+3) {
          display: none;
        }
      }
    }
  }
}
</style>