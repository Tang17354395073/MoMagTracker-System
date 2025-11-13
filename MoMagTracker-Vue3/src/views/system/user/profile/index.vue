<template>
  <div class="profile-home">
    <div class="main-card">
      <el-row :gutter="30">
        <!-- 左侧个人信息 -->
        <el-col :span="8" :xs="24">
          <div class="content-block profile-block">
            <h3 class="block-title">
              <i class="el-icon-user-solid"></i>
              个人信息
            </h3>
            <div class="profile-content">
              <div class="avatar-section">
                <userAvatar />
              </div>
              <div class="info-list">
                <div class="info-item">
                  <div class="info-icon">
                    <i class="el-icon-user"></i>
                  </div>
                  <div class="info-content">
                    <span class="info-label">用户名称</span>
                    <span class="info-value">{{ state.user.nickName || '--' }}</span>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-icon">
                    <i class="el-icon-phone"></i>
                  </div>
                  <div class="info-content">
                    <span class="info-label">手机号码</span>
                    <span class="info-value">{{ state.user.phonenumber || '--' }}</span>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-icon">
                    <i class="el-icon-message"></i>
                  </div>
                  <div class="info-content">
                    <span class="info-label">用户邮箱</span>
                    <span class="info-value">{{ state.user.email || '--' }}</span>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-icon">
                    <i class="el-icon-office-building"></i>
                  </div>
                  <div class="info-content">
                    <span class="info-label">所属部门</span>
                    <span class="info-value">
                      {{ state.user.dept?.deptName || '--' }} / {{ state.postGroup || '--' }}
                    </span>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-icon">
                    <i class="el-icon-s-custom"></i>
                  </div>
                  <div class="info-content">
                    <span class="info-label">所属角色</span>
                    <span class="info-value">{{ state.roleGroup || '--' }}</span>
                  </div>
                </div>
                <div class="info-item">
                  <div class="info-icon">
                    <i class="el-icon-time"></i>
                  </div>
                  <div class="info-content">
                    <span class="info-label">创建日期</span>
                    <span class="info-value">{{ state.user.createTime || '--' }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <!-- 右侧基本资料 -->
        <el-col :span="16" :xs="24">
          <div class="content-block management-block">
            <h3 class="block-title">
              <i class="el-icon-setting"></i>
              基本资料
            </h3>
            <div class="management-content">
              <el-tabs v-model="selectedTab" class="custom-tabs">
                <el-tab-pane name="userinfo">
                  <template #label>
                    <span class="tab-label">
                      <i class="el-icon-edit"></i>
                      基本资料
                    </span>
                  </template>
                  <userInfo :user="state.user" />
                </el-tab-pane>
                <el-tab-pane name="resetPwd">
                  <template #label>
                    <span class="tab-label">
                      <i class="el-icon-lock"></i>
                      修改密码
                    </span>
                  </template>
                  <resetPwd />
                </el-tab-pane>
              </el-tabs>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup name="Profile">
import userAvatar from "./userAvatar"
import userInfo from "./userInfo"
import resetPwd from "./resetPwd"
import { getUserProfile } from "@/api/system/user"

const route = useRoute()
const selectedTab = ref("userinfo")
const state = reactive({
  user: {},
  roleGroup: {},
  postGroup: {}
})

function getUser() {
  getUserProfile().then(response => {
    state.user = response.data
    state.roleGroup = response.roleGroup
    state.postGroup = response.postGroup
  })
}

onMounted(() => {
  const activeTab = route.params && route.params.activeTab
  if (activeTab) {
    selectedTab.value = activeTab
  }
  getUser()
})
</script>

<style scoped lang="scss">
.profile-home {
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

    .content-block {
      background: white;
      border-radius: 16px;
      padding: 25px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
      border: 1px solid #f1f5f9;
      transition: all 0.3s ease;
      height: 100%;

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

    // 个人信息区块
    .profile-block {
      .profile-content {
        .avatar-section {
          text-align: center;
          margin-bottom: 25px;
          padding-bottom: 20px;
          border-bottom: 1px solid #f1f5f9;
        }

        .info-list {
          .info-item {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 12px 0;
            border-bottom: 1px solid #f8fafc;
            transition: all 0.3s ease;

            &:last-child {
              border-bottom: none;
            }

            &:hover {
              transform: translateX(5px);
              
              .info-icon {
                transform: scale(1.1);
              }
            }

            .info-icon {
              width: 36px;
              height: 36px;
              display: flex;
              align-items: center;
              justify-content: center;
              background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
              border-radius: 8px;
              color: white;
              font-size: 1.1rem;
              transition: all 0.3s ease;
            }

            .info-content {
              flex: 1;
              display: flex;
              flex-direction: column;

              .info-label {
                font-size: 0.85rem;
                color: #64748b;
                margin-bottom: 2px;
                font-weight: 500;
              }

              .info-value {
                font-size: 0.95rem;
                color: #1e293b;
                font-weight: 600;
              }
            }
          }
        }
      }
    }

    // 资料管理区块
    .management-block {
      .management-content {
        :deep(.custom-tabs) {
          .el-tabs__header {
            margin-bottom: 20px;

            .el-tabs__nav-wrap::after {
              background-color: #e2e8f0;
            }

            .el-tabs__active-bar {
              background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
              height: 3px;
              border-radius: 2px;
            }

            .el-tabs__item {
              font-weight: 600;
              color: #64748b;
              padding: 0 20px;
              height: 48px;
              display: flex;
              align-items: center;

              &.is-active {
                color: #667eea;
              }

              .tab-label {
                display: flex;
                align-items: center;
                gap: 8px;

                i {
                  font-size: 1.1rem;
                }
              }

              &:hover {
                color: #667eea;
              }
            }
          }

          .el-tabs__content {
            padding: 0 5px;
          }
        }
      }
    }
  }

  // 响应式设计
  @media (max-width: 1024px) {
    .main-card {
      padding: 25px;
      margin: 15px;
    }
  }

  @media (max-width: 768px) {
    .profile-home {
      padding: 15px;
    }

    .main-card {
      padding: 20px;

      .content-block {
        margin-bottom: 20px;
        
        .profile-content {
          .info-list {
            .info-item {
              padding: 15px 0;
            }
          }
        }
      }
    }
  }

  @media (max-width: 480px) {
    .profile-home {
      padding: 10px;
    }

    .main-card {
      padding: 15px;
      margin: 10px;

      .content-block {
        padding: 20px;
      }
    }
  }
}
</style>