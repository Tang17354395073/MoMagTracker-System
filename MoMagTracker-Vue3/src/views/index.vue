<template>
  <div class="app-container home">
    <div class="main-card">
      <!-- 主标题区域 -->
      <div class="header-section">
        <h1 class="main-title">结构健康监测系统后台管理框架</h1>
        <p class="description">
          与传统的接触式监测方法相比，本系统基于先进的非接触式视觉微小运动放大技术，能够在不接触目标的情况下，帮助用户更精准地检测和分析微小的运动，尤其是那些人类肉眼无法直接感知的微弱变化。可以用于所有的 Web 应用程序，同时支持移动客户端访问。
        </p>
        <div class="version-info">
          <span class="version-label">当前版本：</span>
          <span class="version-value">v{{ version }}</span>
          <el-tag type="success" class="free-tag">免费开源</el-tag>
        </div>
        <div class="action-buttons">
          <el-button
            type="primary"
            icon="Cloudy"
            class="custom-btn primary-btn"
            @click="goTarget('https://github.com/Tang17354395073/MoMagTracker-System')"
          >
            访问 Github
          </el-button>
          <el-button
            icon="HomeFilled"
            class="custom-btn secondary-btn"
            @click="goTarget('http://ruoyi.vip')"
          >
            访问 Gitee
          </el-button>
        </div>
      </div>

      <!-- 内容区域 -->
      <div class="content-section">
        <!-- 技术选型 -->
        <div class="content-block tech-block">
          <h3 class="block-title">
            <i class="el-icon-cpu"></i>
            技术选型
          </h3>
          <div class="tech-content">
            <div class="tech-column">
              <h4 class="tech-category">
                <i class="el-icon-monitor"></i>
                后端技术
              </h4>
              <ul class="tech-list">
                <li><i class="el-icon-check"></i> SpringBoot</li>
                <li><i class="el-icon-check"></i> Spring Security</li>
                <li><i class="el-icon-check"></i> JWT</li>
                <li><i class="el-icon-check"></i> MyBatis</li>
                <li><i class="el-icon-check"></i> Druid</li>
                <li><i class="el-icon-check"></i> Fastjson</li>
                <li><i class="el-icon-more"></i> ...</li>
              </ul>
            </div>
            <div class="tech-column">
              <h4 class="tech-category">
                <i class="el-icon-data-line"></i>
                前端技术
              </h4>
              <ul class="tech-list">
                <li><i class="el-icon-check"></i> Vue</li>
                <li><i class="el-icon-check"></i> Vuex</li>
                <li><i class="el-icon-check"></i> Element-ui</li>
                <li><i class="el-icon-check"></i> Axios</li>
                <li><i class="el-icon-check"></i> Sass</li>
                <li><i class="el-icon-check"></i> Quill</li>
                <li><i class="el-icon-more"></i> ...</li>
              </ul>
            </div>
          </div>
        </div>

        <!-- 联系信息 -->
        <div class="content-block contact-block">
          <h3 class="block-title">
            <i class="el-icon-phone-outline"></i>
            联系信息
          </h3>
          <div class="contact-content">
            <div class="contact-item">
              <i class="el-icon-s-promotion contact-icon"></i>
              <div class="contact-details">
                <span class="contact-label">官网：</span>
                <el-link 
                  href="http://www.ruoyi.vip" 
                  target="_blank"
                  class="contact-link"
                >
                  http://www.tangjiawei.com
                </el-link>
              </div>
            </div>
            <div class="contact-item">
              <i class="el-icon-chat-dot-round contact-icon"></i>
              <div class="contact-details">
                <span class="contact-label">电话：</span>
                <span class="contact-text">15948660043</span>
              </div>
            </div>
            <div class="contact-item">
              <i class="el-icon-message contact-icon"></i>
              <div class="contact-details">
                <span class="contact-label">邮箱：</span>
                <span class="contact-text">15948660043@163.com</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 更新日志 -->
        <div class="content-block update-block">
          <h3 class="block-title">
            <i class="el-icon-notebook-2"></i>
            更新日志
          </h3>
          <div class="update-content">
            <el-collapse accordion class="custom-collapse">
              <el-collapse-item name="2.0.0" class="collapse-item">
                <template #title>
                  <div class="collapse-title">
                    <span class="version-tag">v2.0.0</span>
                    <span class="version-date">2025-10-27</span>
                  </div>
                </template>
                <div class="update-details">
                  <ol class="update-list">
                    <li>结构健康监测系统 V2.0.0 正式发布</li>
                  </ol>
                </div>
              </el-collapse-item>
              <el-collapse-item name="1.0.0" class="collapse-item">
                <template #title>
                  <div class="collapse-title">
                    <span class="version-tag">v1.0.0</span>
                    <span class="version-date">2025-03-05</span>
                  </div>
                </template>
                <div class="update-details">
                  <ol class="update-list">
                    <li>结构健康监测系统 V1.0.0 正式发布</li>
                  </ol>
                </div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </div>

        <!-- 捐赠支持 -->
        <div class="content-block donation-block">
          <h3 class="block-title">
            <i class="el-icon-coffee-cup"></i>
            捐赠支持
          </h3>
          <div class="donation-content">
            <div class="donation-container">
              <div class="donation-item">
                <div class="donation-title">
                  <i class="el-icon-alipay"></i>
                  支付宝扫一扫付款
                </div>
                <div class="donation-image-wrapper">
                  <img
                    src="@/assets/images/alipay.jpg"
                    alt="支付宝付款码"
                    class="donation-image"
                  />
                </div>
              </div>
              
              <div class="divider"></div>
              
              <div class="donation-item">
                <div class="donation-title">
                  <i class="el-icon-wechat"></i>
                  微信扫一扫转账
                </div>
                <div class="donation-image-wrapper">
                  <img
                    src="@/assets/images/wechat.jpg"
                    alt="微信转账码"
                    class="donation-image"
                  />
                </div>
              </div>
            </div>
            <div class="donation-footer">
              <i class="el-icon-chat-line-round"></i>
              你可以请作者喝杯咖啡表示鼓励
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup name="Index">
const version = ref('2.0.0')

function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  font-family: "Inter", "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
  color: #2c3e50;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  padding: 0px;
  display: flex;
  align-items: center;
  justify-content: center;

  .main-card {
    background: rgba(255, 255, 255, 0.95);
    border-radius: 20px;
    box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
    backdrop-filter: blur(20px);
    width: 100%;
    max-width: 1400px;
    padding: 20px;
    margin: 20px;

    // 头部区域
    .header-section {
      text-align: center;
      margin-bottom: 40px;
      padding-bottom: 30px;
      border-bottom: 2px solid #f1f5f9;

      .main-title {
        font-size: 2.8rem;
        font-weight: 800;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        margin-bottom: 20px;
        line-height: 1.2;
        letter-spacing: -0.5px;
      }

      .description {
        font-size: 1.2rem;
        line-height: 1.6;
        color: #64748b;
        margin-bottom: 25px;
        text-align: left;
        max-width: 100%;
        margin-left: 0;
        margin-right: 0;
        text-indent: 2em;
      }

      .version-info {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 20px;
        margin-bottom: 30px;
        flex-wrap: wrap;

        .version-label {
          font-weight: 600;
          color: #475569;
        }

        .version-value {
          font-weight: 700;
          color: #1e293b;
          font-size: 1.1rem;
        }

        .free-tag {
          font-weight: 600;
          padding: 8px 20px;
          border-radius: 25px;
          font-size: 0.95rem;
          background: linear-gradient(135deg, #10b981 0%, #059669 100%);
          border: none;
          color: white;
        }
      }

      .action-buttons {
        display: flex;
        gap: 15px;
        justify-content: center;
        flex-wrap: wrap;

        .custom-btn {
          padding: 14px 28px;
          border-radius: 12px;
          font-weight: 600;
          font-size: 1rem;
          transition: all 0.3s ease;
          border: none;
          min-width: 140px;

          &.primary-btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);

            &:hover {
              transform: translateY(-3px);
              box-shadow: 0 12px 30px rgba(102, 126, 234, 0.4);
            }
          }

          &.secondary-btn {
            background: white;
            border: 2px solid #e2e8f0;
            color: #475569;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);

            &:hover {
              transform: translateY(-3px);
              box-shadow: 0 12px 30px rgba(0, 0, 0, 0.1);
              border-color: #667eea;
              color: #667eea;
            }
          }
        }
      }
    }

    // 内容区域
    .content-section {
      display: grid;
      grid-template-columns: repeat(2, 1fr);
      gap: 30px;

      .content-block {
        background: white;
        border-radius: 16px;
        padding: 25px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
        border: 1px solid #f1f5f9;
        transition: all 0.3s ease;

        &:hover {
          transform: translateY(-5px);
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

      // 技术选型区块
      .tech-block {
        .tech-content {
          display: grid;
          grid-template-columns: 1fr 1fr;
          gap: 25px;

          .tech-column {
            .tech-category {
              display: flex;
              align-items: center;
              gap: 8px;
              font-size: 1.1rem;
              font-weight: 600;
              color: #374151;
              margin-bottom: 15px;

              i {
                color: #667eea;
              }
            }

            .tech-list {
              list-style: none;
              padding: 0;
              margin: 0;

              li {
                display: flex;
                align-items: center;
                gap: 8px;
                padding: 10px 0;
                color: #4b5563;
                font-weight: 500;
                transition: all 0.3s ease;
                border-bottom: 1px solid #f8fafc;

                &:last-child {
                  border-bottom: none;
                }

                &:hover {
                  color: #667eea;
                  transform: translateX(5px);
                }

                i {
                  color: #10b981;

                  &.el-icon-more {
                    color: #9ca3af;
                  }
                }
              }
            }
          }
        }
      }

      // 联系信息区块
      .contact-block {
        .contact-content {
          .contact-item {
            display: flex;
            align-items: center;
            gap: 15px;
            padding: 15px 0;
            border-bottom: 1px solid #f8fafc;

            &:last-child {
              border-bottom: none;
            }

            .contact-icon {
              color: #667eea;
              font-size: 1.3rem;
              width: 24px;
              text-align: center;
            }

            .contact-details {
              flex: 1;

              .contact-label {
                font-weight: 600;
                color: #374151;
                margin-right: 8px;
              }

              .contact-link {
                color: #667eea;
                text-decoration: none;
                font-weight: 500;

                &:hover {
                  text-decoration: underline;
                }
              }

              .contact-text {
                color: #4b5563;
                font-weight: 500;
              }
            }
          }
        }
      }

      // 更新日志区块
      .update-block {
        .update-content {
          .custom-collapse {
            border: none;

            .collapse-item {
              margin-bottom: 12px;
              border: 1px solid #e2e8f0;
              border-radius: 12px;
              overflow: hidden;

              &:last-child {
                margin-bottom: 0;
              }

              :deep(.el-collapse-item__header) {
                background: #f8fafc;
                border: none;
                padding: 18px 20px;
                font-weight: 600;

                .collapse-title {
                  display: flex;
                  align-items: center;
                  gap: 15px;

                  .version-tag {
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white;
                    padding: 6px 14px;
                    border-radius: 20px;
                    font-size: 0.9rem;
                    font-weight: 600;
                  }

                  .version-date {
                    color: #6b7280;
                    font-weight: 500;
                  }
                }
              }

              :deep(.el-collapse-item__wrap) {
                border: none;
                background: white;

                .update-details {
                  padding: 20px;

                  .update-list {
                    margin: 0;
                    padding-left: 20px;

                    li {
                      padding: 8px 0;
                      color: #4b5563;
                      line-height: 1.5;
                      font-weight: 500;
                    }
                  }
                }
              }
            }
          }
        }
      }

      // 捐赠支持区块
      .donation-block {
        .donation-content {
          .donation-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 20px;

            .donation-item {
              flex: 1;
              text-align: center;
              display: flex;
              flex-direction: column;
              align-items: center;

              .donation-title {
                display: flex;
                align-items: center;
                gap: 8px;
                font-weight: 600;
                margin-bottom: 15px;
                color: #1e293b;
                font-size: 0.95rem;

                i {
                  font-size: 1.3rem;

                  &.el-icon-alipay {
                    color: #1677ff;
                  }

                  &.el-icon-wechat {
                    color: #07c160;
                  }
                }
              }

              .donation-image-wrapper {
                width: 130px;
                height: 130px;
                padding: 8px;
                border: 2px solid #e2e8f0;
                border-radius: 12px;
                background: white;
                transition: all 0.3s ease;

                &:hover {
                  border-color: #667eea;
                  transform: scale(1.05);
                }
              }

              .donation-image {
                width: 100%;
                height: 100%;
                object-fit: contain;
                border-radius: 8px;
              }
            }

            .divider {
              width: 1px;
              height: 140px;
              background: linear-gradient(to bottom, transparent, #e2e8f0, transparent);
              margin: 0 20px;
            }
          }

          .donation-footer {
            text-align: center;
            padding: 15px;
            background: #f8fafc;
            border-radius: 12px;
            color: #6b7280;
            font-weight: 500;

            i {
              margin-right: 8px;
              color: #667eea;
            }
          }
        }
      }
    }
  }

  // 响应式设计
  @media (max-width: 1024px) {
    .main-card {
      padding: 30px;
      margin: 15px;

      .content-section {
        grid-template-columns: 1fr;
        gap: 25px;
      }
    }
  }

  @media (max-width: 768px) {
    .home {
      padding: 15px;
    }

    .main-card {
      padding: 25px 20px;

      .header-section {
        .main-title {
          font-size: 2.2rem;
        }

        .description {
          font-size: 1.1rem;
        }

        .version-info {
          flex-direction: column;
          gap: 15px;
        }

        .action-buttons {
          flex-direction: column;
          align-items: center;

          .custom-btn {
            width: 100%;
            max-width: 250px;
          }
        }
      }

      .content-section {
        .tech-block .tech-content {
          grid-template-columns: 1fr;
          gap: 20px;
        }

        .donation-block .donation-content .donation-container {
          flex-direction: column;

          .divider {
            width: 80%;
            height: 1px;
            margin: 20px 0;
          }

          .donation-item {
            margin-bottom: 20px;
          }
        }
      }
    }
  }

  @media (max-width: 480px) {
    .main-card {
      padding: 20px 15px;

      .header-section .main-title {
        font-size: 1.8rem;
      }

      .content-section {
        .content-block {
          padding: 20px;
        }

        .donation-block .donation-content .donation-container .donation-item .donation-image-wrapper {
          width: 110px;
          height: 110px;
        }
      }
    }
  }
}
</style>