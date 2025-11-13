<template>
  <div class="cache-monitor">
    <div class="main-card">
      <div class="content-block">
        <el-row :gutter="20">
          <!-- 基本信息卡片 -->
          <el-col :xs="24" class="card-box">
            <el-card class="tech-card info-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Monitor class="card-icon" />
                  <span class="card-title">Redis基本信息</span>
                  <div class="card-status" v-if="cache.info">
                    <div class="status-indicator" :class="{'status-online': cache.info.connected_clients > 0}"></div>
                    <div class="status-text">{{ cache.info.connected_clients > 0 ? '运行中' : '离线' }}</div>
                  </div>
                </div>
              </template>
              <div class="info-grid three-columns">
                <div class="info-item">
                  <div class="info-label">Redis版本</div>
                  <div class="info-value version-text">{{ cache.info?.redis_version }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">运行模式</div>
                  <div class="info-value">
                    <el-tag :type="cache.info?.redis_mode === 'standalone' ? 'success' : 'warning'" class="mode-tag">
                      {{ cache.info?.redis_mode == "standalone" ? "单机" : "集群" }}
                    </el-tag>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-label">端口</div>
                  <div class="info-value">{{ cache.info?.tcp_port }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">客户端数</div>
                  <div class="info-value client-count">{{ cache.info?.connected_clients }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">运行时间</div>
                  <div class="info-value">{{ cache.info?.uptime_in_days }} 天</div>
                </div>
                <div class="info-item">
                  <div class="info-label">使用内存</div>
                  <div class="info-value memory-usage">{{ cache.info?.used_memory_human }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">使用CPU</div>
                  <div class="info-value">{{ cache.info ? parseFloat(cache.info.used_cpu_user_children).toFixed(2) : '0.00' }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">内存配置</div>
                  <div class="info-value">{{ cache.info?.maxmemory_human }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">AOF是否开启</div>
                  <div class="info-value">
                    <el-tag :type="cache.info?.aof_enabled == '0' ? 'danger' : 'success'" class="feature-tag">
                      {{ cache.info?.aof_enabled == "0" ? "否" : "是" }}
                    </el-tag>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-label">RDB是否成功</div>
                  <div class="info-value">
                    <el-tag :type="cache.info?.rdb_last_bgsave_status === 'ok' ? 'success' : 'danger'" class="feature-tag">
                      {{ cache.info?.rdb_last_bgsave_status }}
                    </el-tag>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-label">Key数量</div>
                  <div class="info-value key-count">{{ cache.dbSize }}</div>
                </div>
                <div class="info-item">
                  <div class="info-label">网络入口/出口</div>
                  <div class="info-value network-traffic">
                    <div class="traffic-in">{{ cache.info?.instantaneous_input_kbps }}kps</div>
                    <div class="traffic-out">{{ cache.info?.instantaneous_output_kbps }}kps</div>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>

          <!-- 命令统计卡片 -->
          <el-col :xs="24" :sm="12" :lg="12" class="card-box">
            <el-card class="tech-card command-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <PieChart class="card-icon" />
                  <span class="card-title">命令统计</span>
                </div>
              </template>
              <div class="chart-container">
                <div ref="commandstats" class="chart" style="height: 420px" />
              </div>
            </el-card>
          </el-col>

          <!-- 内存信息卡片 -->
          <el-col :xs="24" :sm="12" :lg="12" class="card-box">
            <el-card class="tech-card memory-card" shadow="hover">
              <template #header>
                <div class="card-header">
                  <Odometer class="card-icon" />
                  <span class="card-title">内存信息</span>
                  <div class="memory-indicator" v-if="cache.info">
                    <div class="memory-percent">{{ getMemoryUsagePercent() }}%</div>
                    <div class="memory-label">使用率</div>
                  </div>
                </div>
              </template>
              <div class="chart-container">
                <div ref="usedmemory" class="chart" style="height: 420px" />
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script setup name="Cache">
import { getCache } from '@/api/monitor/cache'
import * as echarts from 'echarts'

const cache = ref([])
const commandstats = ref(null)
const usedmemory = ref(null)
const { proxy } = getCurrentInstance()

// 计算内存使用百分比
function getMemoryUsagePercent() {
  if (!cache.value.info?.used_memory_human || !cache.value.info?.maxmemory_human) return 0
  
  const used = parseMemorySize(cache.value.info.used_memory_human)
  const max = parseMemorySize(cache.value.info.maxmemory_human)
  
  if (max === 0) return 0
  return Math.round((used / max) * 100)
}

// 解析内存大小字符串为字节数
function parseMemorySize(sizeStr) {
  if (!sizeStr) return 0
  
  const units = {
    'K': 1024,
    'M': 1024 * 1024,
    'G': 1024 * 1024 * 1024,
    'T': 1024 * 1024 * 1024 * 1024
  }
  
  const match = sizeStr.match(/^([\d.]+)([KMGTP]?)/i)
  if (!match) return 0
  
  const value = parseFloat(match[1])
  const unit = match[2].toUpperCase()
  
  return value * (units[unit] || 1)
}

function getList() {
  proxy.$modal.loading("正在加载缓存监控数据，请稍候！")
  getCache().then(response => {
    proxy.$modal.closeLoading()
    cache.value = response.data

    // 命令统计图表
    const commandstatsIntance = echarts.init(commandstats.value, "macarons")
    commandstatsIntance.setOption({
      tooltip: {
        trigger: "item",
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        backgroundColor: 'rgba(255, 255, 255, 0.95)',
        borderColor: '#e2e8f0',
        textStyle: {
          color: '#374151'
        }
      },
      legend: {
        orient: 'vertical',
        right: 10,
        top: 'center',
        textStyle: {
          color: '#6b7280'
        }
      },
      series: [
        {
          name: "命令",
          type: "pie",
          roseType: "radius",
          radius: ['15%', '70%'],
          center: ['40%', '50%'],
          data: response.data.commandStats,
          animationEasing: "cubicInOut",
          animationDuration: 1000,
          itemStyle: {
            borderRadius: 8,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            color: '#374151',
            fontWeight: '500'
          },
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    })

    // 内存信息图表
    const memoryPercent = getMemoryUsagePercent()
    const usedmemoryInstance = echarts.init(usedmemory.value, "macarons")
    usedmemoryInstance.setOption({
      tooltip: {
        formatter: '{b} <br/>{a} : ' + cache.value.info.used_memory_human,
        backgroundColor: 'rgba(255, 255, 255, 0.95)',
        borderColor: '#e2e8f0',
        textStyle: {
          color: '#374151'
        }
      },
      series: [
        {
          name: "内存消耗",
          type: "gauge",
          radius: '90%',
          min: 0,
          max: 100,
          progress: {
            show: true,
            width: 18,
            itemStyle: {
              color: getMemoryColor(memoryPercent)
            }
          },
          axisLine: {
            lineStyle: {
              width: 18,
              color: [
                [0.3, '#10b981'],
                [0.7, '#f59e0b'],
                [1, '#ef4444']
              ]
            }
          },
          axisTick: {
            distance: -18,
            length: 8,
            lineStyle: {
              color: '#fff',
              width: 2
            }
          },
          splitLine: {
            distance: -18,
            length: 18,
            lineStyle: {
              color: '#fff',
              width: 3
            }
          },
          axisLabel: {
            distance: -30,
            color: '#6b7280',
            fontSize: 12
          },
          anchor: {
            show: true,
            showAbove: true,
            size: 18,
            itemStyle: {
              borderWidth: 8,
              borderColor: getMemoryColor(memoryPercent)
            }
          },
          pointer: {
            icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',
            length: '12%',
            width: 20,
            offsetCenter: [0, '-60%'],
            itemStyle: {
              color: getMemoryColor(memoryPercent)
            }
          },
          detail: {
            valueAnimation: true,
            formatter: '{value}%',
            color: getMemoryColor(memoryPercent),
            fontSize: 24,
            fontWeight: 'bold',
            offsetCenter: [0, '30%']
          },
          title: {
            offsetCenter: [0, '70%'],
            color: '#6b7280',
            fontSize: 14
          },
          data: [
            {
              value: memoryPercent,
              name: '内存使用率'
            }
          ]
        }
      ]
    })

    // 窗口大小变化时重绘图表
    window.addEventListener("resize", () => {
      commandstatsIntance.resize()
      usedmemoryInstance.resize()
    })
  })
}

// 根据内存使用率获取颜色
function getMemoryColor(percent) {
  if (percent < 50) return '#10b981'
  if (percent < 80) return '#f59e0b'
  return '#ef4444'
}

getList()
</script>

<style scoped lang="scss">
.cache-monitor {
  font-family: "Inter", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
  color: #2c3e50;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  padding: 20px;
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

      .card-status {
        display: flex;
        align-items: center;
        gap: 8px;

        .status-indicator {
          width: 8px;
          height: 8px;
          border-radius: 50%;
          background: #ef4444;

          &.status-online {
            background: #10b981;
            box-shadow: 0 0 8px rgba(16, 185, 129, 0.6);
          }
        }

        .status-text {
          font-size: 0.9rem;
          color: #6b7280;
          font-weight: 500;
        }
      }

      .memory-indicator {
        text-align: center;
        .memory-percent {
          font-size: 1.3rem;
          font-weight: 800;
          color: #667eea;
          line-height: 1;
        }
        .memory-label {
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

  .info-grid {
    display: grid;
    gap: 16px;

    &.three-columns {
      grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    }

    .info-item {
      display: flex;
      align-items: center;
      padding: 16px;
      background: #f8fafc;
      border-radius: 12px;
      border: 1px solid #e2e8f0;
      transition: all 0.3s ease;

      &:hover {
        background: #f1f5f9;
        transform: translateX(4px);
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
      }

      .info-label {
        flex: 0 0 140px;
        color: #6b7280;
        font-weight: 500;
        font-size: 0.9rem;
      }

      .info-value {
        flex: 1;
        color: #374151;
        font-weight: 600;
        display: flex;
        align-items: center;
        gap: 8px;

        &.version-text {
          color: #667eea;
          font-family: 'Monaco', 'Consolas', monospace;
        }

        &.memory-usage {
          color: #10b981;
          font-weight: 700;
        }

        &.client-count, &.key-count {
          background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
          color: white;
          padding: 4px 12px;
          border-radius: 20px;
          font-size: 0.9rem;
          display: inline-flex;
          justify-content: center;
          min-width: 40px;
        }

        .network-traffic {
          display: flex;
          flex-direction: column;
          gap: 4px;

          .traffic-in {
            color: #10b981;
            font-weight: 600;
          }

          .traffic-out {
            color: #f59e0b;
            font-weight: 600;
          }
        }
      }
    }
  }

  .mode-tag, .feature-tag {
    :deep(.el-tag) {
      border-radius: 6px;
      font-weight: 600;
      border: none;
      min-width: 50px;
      text-align: center;
    }
  }

  .chart-container {
    .chart {
      width: 100%;
      border-radius: 8px;
    }
  }
}

// 响应式设计
@media (max-width: 1200px) {
  .cache-monitor {
    .info-grid.three-columns {
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    }
  }
}

@media (max-width: 768px) {
  .cache-monitor {
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

      .card-status, .memory-indicator {
        align-self: flex-end;
      }
    }

    .info-grid.three-columns {
      grid-template-columns: 1fr;
    }

    .info-item {
      flex-direction: column;
      align-items: flex-start;
      gap: 8px;

      .info-label {
        flex: none;
      }

      .info-value {
        width: 100%;
        justify-content: flex-start;
      }
    }
  }
}

@media (max-width: 480px) {
  .cache-monitor {
    padding: 10px;

    .main-card {
      padding: 15px;
      margin: 10px;
    }

    .chart-container .chart {
      height: 300px !important;
    }
  }
}

// ECharts 主题优化
:deep(.macarons) {
  .echarts-tooltip {
    background: rgba(255, 255, 255, 0.95) !important;
    border: 1px solid #e2e8f0 !important;
    border-radius: 8px !important;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1) !important;
  }
}
</style>