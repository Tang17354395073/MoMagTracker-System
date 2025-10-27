# 项目 MoMagTracker

## 1 创建 Github 仓库 MoMagTracker-SyStem

### 1.1 推送项目至远程仓库

进入到 `E:\RuoYiProject\MoMagTracker-System` 文件夹，打开 `git`，输入下述命令

```shell
git init

touch README.md

git status

git add .

git status

git commit -m "第一次提交，生成总体概括 README.md 文件，以及初始版前端 MoMagTracker-Vue 和后端 MoMagTracker-Vue3 代码"

git remote add origin git@17354395073_github_account:Tang17354395073/MoMagTracker-System.git

git push -u origin master
```

- 结果

```shell
17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System
$ git init
Initialized empty Git repository in E:/RuoYiProject/MoMagTracker-System/.git/

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ touch README.md

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ git status
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        MoMagTracker-Vue/
        MoMagTracker-Vue3/
        README.md

nothing added to commit but untracked files present (use "git add" to track)

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ git add .

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ git status
On branch master

No commits yet

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   MoMagTracker-Vue/.github/FUNDING.yml
        new file:   MoMagTracker-Vue/.gitignore
        new file:   MoMagTracker-Vue/LICENSE
        new file:   MoMagTracker-Vue/README.md
        new file:   MoMagTracker-Vue/bin/clean.bat
        new file:   MoMagTracker-Vue/bin/package.bat
        new file:   MoMagTracker-Vue/bin/run.bat
        new file:   "MoMagTracker-Vue/doc/\342\225\232\342\214\240\342\225\245\342\224\224\342\225\227\342\225\226\342\225\233\342\224\202\342\225\251\342\225\243\342\225\231\342\224\234\342\225\251\342\225\223\342\226\223\303\237.docx"
        new file:   MoMagTracker-Vue/pom.xml
        new file:   MoMagTracker-Vue/ry.bat
        new file:   MoMagTracker-Vue/ry.sh
        new file:   MoMagTracker-Vue/sql/quartz.sql
        new file:   MoMagTracker-Vue/sql/ry_20250522.sql
        new file:   MoMagTracker-Vue/tracker-admin/pom.xml
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/MoMagTrackerApplication.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/MoMagTrackerServletInitializer.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/common/CaptchaController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/common/CommonController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/CacheController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/ServerController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/SysLogininforController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/SysOperlogController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/SysUserOnlineController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysConfigController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysDeptController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysDictDataController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysDictTypeController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysIndexController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysLoginController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysMenuController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysNoticeController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysPostController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysProfileController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysRegisterController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysRoleController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysUserController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/tool/TestController.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/core/config/SwaggerConfig.java
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/META-INF/spring-devtools.properties
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/application-druid.yml
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/application.yml
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/banner.txt
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/i18n/messages.properties
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/logback.xml
        new file:   MoMagTracker-Vue/tracker-admin/src/main/resources/mybatis/mybatis-config.xml
        new file:   MoMagTracker-Vue/tracker-common/pom.xml
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Anonymous.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/DataScope.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/DataSource.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Excel.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Excels.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Log.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/RateLimiter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/RepeatSubmit.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Sensitive.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/config/RuoYiConfig.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/config/serializer/SensitiveJsonSerializer.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/CacheConstants.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/Constants.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/GenConstants.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/HttpStatus.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/ScheduleConstants.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/UserConstants.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/controller/BaseController.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/AjaxResult.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/BaseEntity.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/R.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/TreeEntity.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/TreeSelect.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysDept.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysDictData.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysDictType.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysMenu.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysRole.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysUser.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/model/LoginBody.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/model/LoginUser.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/model/RegisterBody.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/page/PageDomain.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/page/TableDataInfo.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/page/TableSupport.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/redis/RedisCache.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/text/CharsetKit.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/text/Convert.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/text/StrFormatter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/BusinessStatus.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/BusinessType.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/DataSourceType.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/DesensitizedType.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/HttpMethod.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/LimitType.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/OperatorType.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/UserStatus.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/DemoModeException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/GlobalException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/ServiceException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/UtilException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/base/BaseException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileNameLengthLimitExceededException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileSizeLimitExceededException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileUploadException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/InvalidExtensionException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/job/TaskException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/BlackListException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/CaptchaException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/CaptchaExpireException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserNotExistsException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserPasswordNotMatchException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserPasswordRetryLimitExceedException.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/PropertyPreExcludeFilter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/RepeatableFilter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/RepeatedlyRequestWrapper.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/XssFilter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/XssHttpServletRequestWrapper.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/Arith.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/DateUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/DesensitizedUtil.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/DictUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ExceptionUtil.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/LogUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/MessageUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/PageUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/SecurityUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ServletUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/StringUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/Threads.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/bean/BeanUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/bean/BeanValidators.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/FileTypeUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/FileUploadUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/FileUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/ImageUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/MimeTypeUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/html/EscapeUtil.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/html/HTMLFilter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/http/HttpHelper.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/http/HttpUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ip/AddressUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ip/IpUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/poi/ExcelHandlerAdapter.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/poi/ExcelUtil.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/reflect/ReflectUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/sign/Base64.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/sign/Md5Utils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/spring/SpringUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/sql/SqlUtil.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/uuid/IdUtils.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/uuid/Seq.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/uuid/UUID.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/xss/Xss.java
        new file:   MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/xss/XssValidator.java
        new file:   MoMagTracker-Vue/tracker-framework/pom.xml
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/DataScopeAspect.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/DataSourceAspect.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/LogAspect.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/RateLimiterAspect.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ApplicationConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/CaptchaConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/DruidConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/FastJson2JsonRedisSerializer.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/FilterConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/I18nConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/KaptchaTextCreator.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/MyBatisConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/RedisConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ResourcesConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/SecurityConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ServerConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ThreadPoolConfig.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/properties/DruidProperties.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/properties/PermitAllUrlProperties.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/datasource/DynamicDataSource.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/datasource/DynamicDataSourceContextHolder.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/interceptor/RepeatSubmitInterceptor.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/interceptor/impl/SameUrlDataInterceptor.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/manager/AsyncManager.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/manager/ShutdownManager.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/manager/factory/AsyncFactory.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/context/AuthenticationContextHolder.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/context/PermissionContextHolder.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/filter/JwtAuthenticationTokenFilter.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/handle/AuthenticationEntryPointImpl.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/handle/LogoutSuccessHandlerImpl.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/Server.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Cpu.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Jvm.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Mem.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Sys.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/SysFile.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/exception/GlobalExceptionHandler.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/PermissionService.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysLoginService.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysPasswordService.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysPermissionService.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysRegisterService.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/TokenService.java
        new file:   MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/UserDetailsServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-generator/pom.xml
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/config/GenConfig.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/controller/GenController.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/domain/GenTable.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/domain/GenTableColumn.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/mapper/GenTableColumnMapper.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/mapper/GenTableMapper.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/GenTableColumnServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/GenTableServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/IGenTableColumnService.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/IGenTableService.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/util/GenUtils.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/util/VelocityInitializer.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/util/VelocityUtils.java
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/generator.yml
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/mapper/generator/GenTableColumnMapper.xml
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/mapper/generator/GenTableMapper.xml
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/controller.java.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/domain.java.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/mapper.java.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/service.java.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/serviceImpl.java.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/sub-domain.java.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/js/api.js.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/sql/sql.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/index-tree.vue.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/index.vue.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/v3/index-tree.vue.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/v3/index.vue.vm
        new file:   MoMagTracker-Vue/tracker-generator/src/main/resources/vm/xml/mapper.xml.vm
        new file:   MoMagTracker-Vue/tracker-quartz/pom.xml
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/config/ScheduleConfig.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/controller/SysJobController.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/controller/SysJobLogController.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/domain/SysJob.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/domain/SysJobLog.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/mapper/SysJobLogMapper.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/mapper/SysJobMapper.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/ISysJobLogService.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/ISysJobService.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/impl/SysJobLogServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/impl/SysJobServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/task/RyTask.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/AbstractQuartzJob.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/CronUtils.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/JobInvokeUtil.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/QuartzDisallowConcurrentExecution.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/QuartzJobExecution.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/ScheduleUtils.java
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/resources/mapper/quartz/SysJobLogMapper.xml
        new file:   MoMagTracker-Vue/tracker-quartz/src/main/resources/mapper/quartz/SysJobMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/pom.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysCache.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysConfig.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysLogininfor.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysNotice.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysOperLog.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysPost.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysRoleDept.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysRoleMenu.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysUserOnline.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysUserPost.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysUserRole.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/vo/MetaVo.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/vo/RouterVo.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysConfigMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysDeptMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysDictDataMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysDictTypeMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysLogininforMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysMenuMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysNoticeMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysOperLogMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysPostMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysRoleDeptMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysRoleMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysRoleMenuMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysUserMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysUserPostMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysUserRoleMapper.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysConfigService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysDeptService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysDictDataService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysDictTypeService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysLogininforService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysMenuService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysNoticeService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysOperLogService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysPostService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysRoleService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysUserOnlineService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysUserService.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysConfigServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysDeptServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysDictDataServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysDictTypeServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysLogininforServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysMenuServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysNoticeServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysOperLogServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysPostServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysRoleServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysUserOnlineServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysUserServiceImpl.java
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysConfigMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysDeptMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysDictDataMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysDictTypeMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysLogininforMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysMenuMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysNoticeMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysOperLogMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysPostMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysRoleDeptMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysRoleMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysRoleMenuMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysUserMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysUserPostMapper.xml
        new file:   MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysUserRoleMapper.xml
        new file:   MoMagTracker-Vue/tracker-ui/.editorconfig
        new file:   MoMagTracker-Vue/tracker-ui/.env.development
        new file:   MoMagTracker-Vue/tracker-ui/.env.production
        new file:   MoMagTracker-Vue/tracker-ui/.env.staging
        new file:   MoMagTracker-Vue/tracker-ui/.gitignore
        new file:   MoMagTracker-Vue/tracker-ui/README.md
        new file:   MoMagTracker-Vue/tracker-ui/babel.config.js
        new file:   MoMagTracker-Vue/tracker-ui/bin/build.bat
        new file:   MoMagTracker-Vue/tracker-ui/bin/package.bat
        new file:   MoMagTracker-Vue/tracker-ui/bin/run-web.bat
        new file:   MoMagTracker-Vue/tracker-ui/build/index.js
        new file:   MoMagTracker-Vue/tracker-ui/package.json
        new file:   MoMagTracker-Vue/tracker-ui/public/favicon.ico
        new file:   MoMagTracker-Vue/tracker-ui/public/favicon.ico.bak
        new file:   MoMagTracker-Vue/tracker-ui/public/html/ie.html
        new file:   MoMagTracker-Vue/tracker-ui/public/index.html
        new file:   MoMagTracker-Vue/tracker-ui/public/robots.txt
        new file:   MoMagTracker-Vue/tracker-ui/public/styles/theme-chalk/index.css
        new file:   MoMagTracker-Vue/tracker-ui/src/App.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/api/login.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/menu.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/cache.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/job.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/jobLog.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/logininfor.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/online.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/operlog.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/monitor/server.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/config.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/dept.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/dict/data.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/dict/type.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/menu.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/notice.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/post.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/role.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/system/user.js
        new file:   MoMagTracker-Vue/tracker-ui/src/api/tool/gen.js
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/401_images/401.gif
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/404_images/404.png
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/404_images/404_cloud.png
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/404.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/bug.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/build.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/button.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/cascader.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/chart.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/checkbox.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/clipboard.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/code.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/color.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/component.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/dashboard.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/date-range.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/date.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/dict.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/documentation.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/download.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/drag.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/druid.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/edit.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/education.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/email.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/enter.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/example.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/excel.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/exit-fullscreen.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/eye-open.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/eye.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/form.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/fullscreen.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/github.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/guide.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/icon.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/input.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/international.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/job.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/language.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/link.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/list.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/lock.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/log.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/logininfor.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/message.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/money.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/monitor.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/more-up.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/nested.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/number.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/online.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/password.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/pdf.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/people.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/peoples.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/phone.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/post.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/qq.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/question.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/radio.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/rate.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/redis-list.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/redis.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/row.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/search.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/select.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/server.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/shopping.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/size.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/skill.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/slider.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/star.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/swagger.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/switch.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/system.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tab.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/table.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/textarea.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/theme.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/time-range.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/time.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tool.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tree-table.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tree.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/upload.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/user.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/validCode.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/wechat.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/zip.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/icons/svgo.yml
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/images/dark.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/images/light.svg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/images/login-background.jpg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/images/pay.png
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/images/profile.jpg
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/logo/logo.png
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/logo/logo.png.bak
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/btn.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/element-ui.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/element-variables.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/index.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/mixin.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/ruoyi.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/sidebar.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/transition.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/assets/styles/variables.scss
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Breadcrumb/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/day.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/hour.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/min.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/month.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/result.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/second.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/week.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Crontab/year.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/DictData/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/components/DictTag/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Editor/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/FileUpload/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Hamburger/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/HeaderSearch/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/IconSelect/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/IconSelect/requireIcons.js
        new file:   MoMagTracker-Vue/tracker-ui/src/components/ImagePreview/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/ImageUpload/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Pagination/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/PanThumb/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/ParentView/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/RightToolbar/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/RuoYi/Doc/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/RuoYi/Git/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/Screenfull/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/SizeSelect/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/SvgIcon/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/ThemePicker/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/TopNav/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/components/iFrame/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/dialog/drag.js
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/dialog/dragHeight.js
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/dialog/dragWidth.js
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/module/clipboard.js
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/permission/hasPermi.js
        new file:   MoMagTracker-Vue/tracker-ui/src/directive/permission/hasRole.js
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/AppMain.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Copyright/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/IframeToggle/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/InnerLink/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Navbar.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Settings/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/FixiOSBug.js
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/Item.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/Link.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/Logo.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/SidebarItem.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/TagsView/ScrollPane.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/TagsView/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/components/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/layout/mixin/ResizeHandler.js
        new file:   MoMagTracker-Vue/tracker-ui/src/main.js
        new file:   MoMagTracker-Vue/tracker-ui/src/permission.js
        new file:   MoMagTracker-Vue/tracker-ui/src/plugins/auth.js
        new file:   MoMagTracker-Vue/tracker-ui/src/plugins/cache.js
        new file:   MoMagTracker-Vue/tracker-ui/src/plugins/download.js
        new file:   MoMagTracker-Vue/tracker-ui/src/plugins/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/plugins/modal.js
        new file:   MoMagTracker-Vue/tracker-ui/src/plugins/tab.js
        new file:   MoMagTracker-Vue/tracker-ui/src/router/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/settings.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/getters.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/modules/app.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/modules/dict.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/modules/permission.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/modules/settings.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/modules/tagsView.js
        new file:   MoMagTracker-Vue/tracker-ui/src/store/modules/user.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/auth.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dict/Dict.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dict/DictConverter.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dict/DictData.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dict/DictMeta.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dict/DictOptions.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dict/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/dynamicTitle.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/errorCode.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/config.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/css.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/drawingDefault.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/html.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/icon.json
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/js.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/generator/render.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/index.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/jsencrypt.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/permission.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/request.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/ruoyi.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/scroll-to.js
        new file:   MoMagTracker-Vue/tracker-ui/src/utils/validate.js
        new file:   MoMagTracker-Vue/tracker-ui/src/views/dashboard/BarChart.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/dashboard/LineChart.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/dashboard/PanelGroup.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/dashboard/PieChart.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/dashboard/RaddarChart.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/dashboard/mixins/resize.js
        new file:   MoMagTracker-Vue/tracker-ui/src/views/error/401.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/error/404.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/index_v1.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/login.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/cache/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/cache/list.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/druid/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/job/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/job/log.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/logininfor/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/online/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/operlog/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/monitor/server/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/redirect.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/register.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/config/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/dept/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/dict/data.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/dict/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/menu/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/notice/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/post/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/role/authUser.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/role/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/role/selectUser.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/user/authRole.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/user/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/resetPwd.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/userAvatar.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/userInfo.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/build/CodeTypeDialog.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/build/DraggableItem.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/build/IconsDialog.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/build/RightPanel.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/build/TreeNodeDialog.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/build/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/gen/basicInfoForm.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/gen/createTable.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/gen/editTable.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/gen/genInfoForm.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/gen/importTable.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/gen/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/src/views/tool/swagger/index.vue
        new file:   MoMagTracker-Vue/tracker-ui/vue.config.js
        new file:   MoMagTracker-Vue3/.env.development
        new file:   MoMagTracker-Vue3/.env.production
        new file:   MoMagTracker-Vue3/.env.staging
        new file:   MoMagTracker-Vue3/.github/FUNDING.yml
        new file:   MoMagTracker-Vue3/.gitignore
        new file:   MoMagTracker-Vue3/LICENSE
        new file:   MoMagTracker-Vue3/README.md
        new file:   MoMagTracker-Vue3/bin/build.bat
        new file:   MoMagTracker-Vue3/bin/package.bat
        new file:   MoMagTracker-Vue3/bin/run-web.bat
        new file:   MoMagTracker-Vue3/html/ie.html
        new file:   MoMagTracker-Vue3/index.html
        new file:   MoMagTracker-Vue3/package.json
        new file:   MoMagTracker-Vue3/public/favicon.ico
        new file:   MoMagTracker-Vue3/public/favicon.ico.bak
        new file:   MoMagTracker-Vue3/src/App.vue
        new file:   MoMagTracker-Vue3/src/api/login.js
        new file:   MoMagTracker-Vue3/src/api/menu.js
        new file:   MoMagTracker-Vue3/src/api/monitor/cache.js
        new file:   MoMagTracker-Vue3/src/api/monitor/job.js
        new file:   MoMagTracker-Vue3/src/api/monitor/jobLog.js
        new file:   MoMagTracker-Vue3/src/api/monitor/logininfor.js
        new file:   MoMagTracker-Vue3/src/api/monitor/online.js
        new file:   MoMagTracker-Vue3/src/api/monitor/operlog.js
        new file:   MoMagTracker-Vue3/src/api/monitor/server.js
        new file:   MoMagTracker-Vue3/src/api/system/config.js
        new file:   MoMagTracker-Vue3/src/api/system/dept.js
        new file:   MoMagTracker-Vue3/src/api/system/dict/data.js
        new file:   MoMagTracker-Vue3/src/api/system/dict/type.js
        new file:   MoMagTracker-Vue3/src/api/system/menu.js
        new file:   MoMagTracker-Vue3/src/api/system/notice.js
        new file:   MoMagTracker-Vue3/src/api/system/post.js
        new file:   MoMagTracker-Vue3/src/api/system/role.js
        new file:   MoMagTracker-Vue3/src/api/system/user.js
        new file:   MoMagTracker-Vue3/src/api/tool/gen.js
        new file:   MoMagTracker-Vue3/src/assets/401_images/401.gif
        new file:   MoMagTracker-Vue3/src/assets/404_images/404.png
        new file:   MoMagTracker-Vue3/src/assets/404_images/404_cloud.png
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/404.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/bug.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/build.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/button.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/cascader.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/chart.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/checkbox.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/clipboard.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/code.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/color.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/component.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/dashboard.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/date-range.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/date.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/dict.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/documentation.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/download.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/drag.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/druid.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/edit.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/education.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/email.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/enter.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/example.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/excel.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/exit-fullscreen.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/eye-open.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/eye.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/form.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/fullscreen.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/github.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/guide.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/icon.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/input.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/international.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/job.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/language.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/link.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/list.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/lock.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/log.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/logininfor.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/message.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/money.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/monitor.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/moon.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/more-up.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/nested.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/number.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/online.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/password.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/pdf.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/people.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/peoples.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/phone.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/post.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/qq.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/question.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/radio.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/rate.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/redis-list.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/redis.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/row.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/search.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/select.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/server.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/shopping.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/size.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/skill.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/slider.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/star.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/sunny.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/swagger.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/switch.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/system.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/tab.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/table.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/textarea.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/theme.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/time-range.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/time.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/tool.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/tree-table.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/tree.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/upload.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/user.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/validCode.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/wechat.svg
        new file:   MoMagTracker-Vue3/src/assets/icons/svg/zip.svg
        new file:   MoMagTracker-Vue3/src/assets/images/dark.svg
        new file:   MoMagTracker-Vue3/src/assets/images/light.svg
        new file:   MoMagTracker-Vue3/src/assets/images/login-background-sdu.png
        new file:   MoMagTracker-Vue3/src/assets/images/login-background.jpg
        new file:   MoMagTracker-Vue3/src/assets/images/pay.png
        new file:   MoMagTracker-Vue3/src/assets/images/profile.jpg
        new file:   MoMagTracker-Vue3/src/assets/logo/logo.png
        new file:   MoMagTracker-Vue3/src/assets/logo/logo.png.bak
        new file:   MoMagTracker-Vue3/src/assets/styles/btn.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/element-ui.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/index.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/mixin.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/ruoyi.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/sidebar.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/transition.scss
        new file:   MoMagTracker-Vue3/src/assets/styles/variables.module.scss
        new file:   MoMagTracker-Vue3/src/components/Breadcrumb/index.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/day.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/hour.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/index.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/min.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/month.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/result.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/second.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/week.vue
        new file:   MoMagTracker-Vue3/src/components/Crontab/year.vue
        new file:   MoMagTracker-Vue3/src/components/DictTag/index.vue
        new file:   MoMagTracker-Vue3/src/components/Editor/index.vue
        new file:   MoMagTracker-Vue3/src/components/FileUpload/index.vue
        new file:   MoMagTracker-Vue3/src/components/Hamburger/index.vue
        new file:   MoMagTracker-Vue3/src/components/HeaderSearch/index.vue
        new file:   MoMagTracker-Vue3/src/components/IconSelect/index.vue
        new file:   MoMagTracker-Vue3/src/components/IconSelect/requireIcons.js
        new file:   MoMagTracker-Vue3/src/components/ImagePreview/index.vue
        new file:   MoMagTracker-Vue3/src/components/ImageUpload/index.vue
        new file:   MoMagTracker-Vue3/src/components/Pagination/index.vue
        new file:   MoMagTracker-Vue3/src/components/ParentView/index.vue
        new file:   MoMagTracker-Vue3/src/components/RightToolbar/index.vue
        new file:   MoMagTracker-Vue3/src/components/RuoYi/Doc/index.vue
        new file:   MoMagTracker-Vue3/src/components/RuoYi/Git/index.vue
        new file:   MoMagTracker-Vue3/src/components/Screenfull/index.vue
        new file:   MoMagTracker-Vue3/src/components/SizeSelect/index.vue
        new file:   MoMagTracker-Vue3/src/components/SvgIcon/index.vue
        new file:   MoMagTracker-Vue3/src/components/SvgIcon/svgicon.js
        new file:   MoMagTracker-Vue3/src/components/TopNav/index.vue
        new file:   MoMagTracker-Vue3/src/components/iFrame/index.vue
        new file:   MoMagTracker-Vue3/src/directive/common/copyText.js
        new file:   MoMagTracker-Vue3/src/directive/index.js
        new file:   MoMagTracker-Vue3/src/directive/permission/hasPermi.js
        new file:   MoMagTracker-Vue3/src/directive/permission/hasRole.js
        new file:   MoMagTracker-Vue3/src/layout/components/AppMain.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Copyright/index.vue
        new file:   MoMagTracker-Vue3/src/layout/components/IframeToggle/index.vue
        new file:   MoMagTracker-Vue3/src/layout/components/InnerLink/index.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Navbar.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Settings/index.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Sidebar/Link.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Sidebar/Logo.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Sidebar/SidebarItem.vue
        new file:   MoMagTracker-Vue3/src/layout/components/Sidebar/index.vue
        new file:   MoMagTracker-Vue3/src/layout/components/TagsView/ScrollPane.vue
        new file:   MoMagTracker-Vue3/src/layout/components/TagsView/index.vue
        new file:   MoMagTracker-Vue3/src/layout/components/index.js
        new file:   MoMagTracker-Vue3/src/layout/index.vue
        new file:   MoMagTracker-Vue3/src/main.js
        new file:   MoMagTracker-Vue3/src/permission.js
        new file:   MoMagTracker-Vue3/src/plugins/auth.js
        new file:   MoMagTracker-Vue3/src/plugins/cache.js
        new file:   MoMagTracker-Vue3/src/plugins/download.js
        new file:   MoMagTracker-Vue3/src/plugins/index.js
        new file:   MoMagTracker-Vue3/src/plugins/modal.js
        new file:   MoMagTracker-Vue3/src/plugins/tab.js
        new file:   MoMagTracker-Vue3/src/router/index.js
        new file:   MoMagTracker-Vue3/src/settings.js
        new file:   MoMagTracker-Vue3/src/store/index.js
        new file:   MoMagTracker-Vue3/src/store/modules/app.js
        new file:   MoMagTracker-Vue3/src/store/modules/dict.js
        new file:   MoMagTracker-Vue3/src/store/modules/permission.js
        new file:   MoMagTracker-Vue3/src/store/modules/settings.js
        new file:   MoMagTracker-Vue3/src/store/modules/tagsView.js
        new file:   MoMagTracker-Vue3/src/store/modules/user.js
        new file:   MoMagTracker-Vue3/src/utils/auth.js
        new file:   MoMagTracker-Vue3/src/utils/dict.js
        new file:   MoMagTracker-Vue3/src/utils/dynamicTitle.js
        new file:   MoMagTracker-Vue3/src/utils/errorCode.js
        new file:   MoMagTracker-Vue3/src/utils/generator/config.js
        new file:   MoMagTracker-Vue3/src/utils/generator/css.js
        new file:   MoMagTracker-Vue3/src/utils/generator/drawingDefalut.js
        new file:   MoMagTracker-Vue3/src/utils/generator/html.js
        new file:   MoMagTracker-Vue3/src/utils/generator/icon.json
        new file:   MoMagTracker-Vue3/src/utils/generator/js.js
        new file:   MoMagTracker-Vue3/src/utils/generator/render.js
        new file:   MoMagTracker-Vue3/src/utils/index.js
        new file:   MoMagTracker-Vue3/src/utils/jsencrypt.js
        new file:   MoMagTracker-Vue3/src/utils/permission.js
        new file:   MoMagTracker-Vue3/src/utils/request.js
        new file:   MoMagTracker-Vue3/src/utils/ruoyi.js
        new file:   MoMagTracker-Vue3/src/utils/scroll-to.js
        new file:   MoMagTracker-Vue3/src/utils/theme.js
        new file:   MoMagTracker-Vue3/src/utils/validate.js
        new file:   MoMagTracker-Vue3/src/views/error/401.vue
        new file:   MoMagTracker-Vue3/src/views/error/404.vue
        new file:   MoMagTracker-Vue3/src/views/index.vue
        new file:   MoMagTracker-Vue3/src/views/login.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/cache/index.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/cache/list.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/druid/index.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/job/index.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/job/log.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/logininfor/index.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/online/index.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/operlog/index.vue
        new file:   MoMagTracker-Vue3/src/views/monitor/server/index.vue
        new file:   MoMagTracker-Vue3/src/views/redirect/index.vue
        new file:   MoMagTracker-Vue3/src/views/register.vue
        new file:   MoMagTracker-Vue3/src/views/system/config/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/dept/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/dict/data.vue
        new file:   MoMagTracker-Vue3/src/views/system/dict/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/menu/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/notice/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/post/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/role/authUser.vue
        new file:   MoMagTracker-Vue3/src/views/system/role/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/role/selectUser.vue
        new file:   MoMagTracker-Vue3/src/views/system/user/authRole.vue
        new file:   MoMagTracker-Vue3/src/views/system/user/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/user/profile/index.vue
        new file:   MoMagTracker-Vue3/src/views/system/user/profile/resetPwd.vue
        new file:   MoMagTracker-Vue3/src/views/system/user/profile/userAvatar.vue
        new file:   MoMagTracker-Vue3/src/views/system/user/profile/userInfo.vue
        new file:   MoMagTracker-Vue3/src/views/tool/build/CodeTypeDialog.vue
        new file:   MoMagTracker-Vue3/src/views/tool/build/DraggableItem.vue
        new file:   MoMagTracker-Vue3/src/views/tool/build/IconsDialog.vue
        new file:   MoMagTracker-Vue3/src/views/tool/build/RightPanel.vue
        new file:   MoMagTracker-Vue3/src/views/tool/build/TreeNodeDialog.vue
        new file:   MoMagTracker-Vue3/src/views/tool/build/index.vue
        new file:   MoMagTracker-Vue3/src/views/tool/gen/basicInfoForm.vue
        new file:   MoMagTracker-Vue3/src/views/tool/gen/createTable.vue
        new file:   MoMagTracker-Vue3/src/views/tool/gen/editTable.vue
        new file:   MoMagTracker-Vue3/src/views/tool/gen/genInfoForm.vue
        new file:   MoMagTracker-Vue3/src/views/tool/gen/importTable.vue
        new file:   MoMagTracker-Vue3/src/views/tool/gen/index.vue
        new file:   MoMagTracker-Vue3/src/views/tool/swagger/index.vue
        new file:   MoMagTracker-Vue3/vite.config.js
        new file:   MoMagTracker-Vue3/vite/plugins/auto-import.js
        new file:   MoMagTracker-Vue3/vite/plugins/compression.js
        new file:   MoMagTracker-Vue3/vite/plugins/index.js
        new file:   MoMagTracker-Vue3/vite/plugins/setup-extend.js
        new file:   MoMagTracker-Vue3/vite/plugins/svg-icon.js
        new file:   README.md

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ git commit -m "第一次提交，生成总体概括 README.md 文件，以及初始版前端 MoMagTracker-Vue 和后端 MoMagTracker-Vue3 代码"
[master (root-commit) 91a45cf] 第一次提交，生成总体概括 README.md 文件，以及初始版前端 MoMagTracker-Vue 和后端 MoMagTracker-Vue3 代码
 891 files changed, 90099 insertions(+)
 create mode 100644 MoMagTracker-Vue/.github/FUNDING.yml
 create mode 100644 MoMagTracker-Vue/.gitignore
 create mode 100644 MoMagTracker-Vue/LICENSE
 create mode 100644 MoMagTracker-Vue/README.md
 create mode 100644 MoMagTracker-Vue/bin/clean.bat
 create mode 100644 MoMagTracker-Vue/bin/package.bat
 create mode 100644 MoMagTracker-Vue/bin/run.bat
 create mode 100644 "MoMagTracker-Vue/doc/\342\225\232\342\214\240\342\225\245\342\224\224\342\225\227\342\225\226\342\225\233\342\224\202\342\225\251\342\225\243\342\225\231\342\224\234\342\225\251\342\225\223\342\226\223\303\237.docx"
 create mode 100644 MoMagTracker-Vue/pom.xml
 create mode 100644 MoMagTracker-Vue/ry.bat
 create mode 100644 MoMagTracker-Vue/ry.sh
 create mode 100644 MoMagTracker-Vue/sql/quartz.sql
 create mode 100644 MoMagTracker-Vue/sql/ry_20250522.sql
 create mode 100644 MoMagTracker-Vue/tracker-admin/pom.xml
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/MoMagTrackerApplication.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/MoMagTrackerServletInitializer.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/common/CaptchaController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/common/CommonController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/CacheController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/ServerController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/SysLogininforController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/SysOperlogController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/monitor/SysUserOnlineController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysConfigController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysDeptController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysDictDataController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysDictTypeController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysIndexController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysLoginController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysMenuController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysNoticeController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysPostController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysProfileController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysRegisterController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysRoleController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/system/SysUserController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/controller/tool/TestController.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/java/tracker/web/core/config/SwaggerConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/META-INF/spring-devtools.properties
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/application-druid.yml
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/application.yml
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/banner.txt
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/i18n/messages.properties
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/logback.xml
 create mode 100644 MoMagTracker-Vue/tracker-admin/src/main/resources/mybatis/mybatis-config.xml
 create mode 100644 MoMagTracker-Vue/tracker-common/pom.xml
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Anonymous.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/DataScope.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/DataSource.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Excel.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Excels.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Log.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/RateLimiter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/RepeatSubmit.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/annotation/Sensitive.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/config/RuoYiConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/config/serializer/SensitiveJsonSerializer.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/CacheConstants.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/Constants.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/GenConstants.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/HttpStatus.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/ScheduleConstants.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/constant/UserConstants.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/controller/BaseController.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/AjaxResult.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/BaseEntity.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/R.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/TreeEntity.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/TreeSelect.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysDept.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysDictData.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysDictType.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysMenu.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysRole.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/entity/SysUser.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/model/LoginBody.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/model/LoginUser.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/domain/model/RegisterBody.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/page/PageDomain.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/page/TableDataInfo.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/page/TableSupport.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/redis/RedisCache.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/text/CharsetKit.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/text/Convert.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/core/text/StrFormatter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/BusinessStatus.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/BusinessType.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/DataSourceType.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/DesensitizedType.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/HttpMethod.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/LimitType.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/OperatorType.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/enums/UserStatus.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/DemoModeException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/GlobalException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/ServiceException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/UtilException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/base/BaseException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileNameLengthLimitExceededException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileSizeLimitExceededException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/FileUploadException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/file/InvalidExtensionException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/job/TaskException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/BlackListException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/CaptchaException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/CaptchaExpireException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserNotExistsException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserPasswordNotMatchException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/exception/user/UserPasswordRetryLimitExceedException.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/PropertyPreExcludeFilter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/RepeatableFilter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/RepeatedlyRequestWrapper.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/XssFilter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/filter/XssHttpServletRequestWrapper.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/Arith.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/DateUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/DesensitizedUtil.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/DictUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ExceptionUtil.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/LogUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/MessageUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/PageUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/SecurityUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ServletUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/StringUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/Threads.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/bean/BeanUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/bean/BeanValidators.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/FileTypeUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/FileUploadUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/FileUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/ImageUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/file/MimeTypeUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/html/EscapeUtil.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/html/HTMLFilter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/http/HttpHelper.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/http/HttpUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ip/AddressUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/ip/IpUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/poi/ExcelHandlerAdapter.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/poi/ExcelUtil.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/reflect/ReflectUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/sign/Base64.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/sign/Md5Utils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/spring/SpringUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/sql/SqlUtil.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/uuid/IdUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/uuid/Seq.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/utils/uuid/UUID.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/xss/Xss.java
 create mode 100644 MoMagTracker-Vue/tracker-common/src/main/java/tracker/common/xss/XssValidator.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/pom.xml
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/DataScopeAspect.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/DataSourceAspect.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/LogAspect.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/aspectj/RateLimiterAspect.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ApplicationConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/CaptchaConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/DruidConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/FastJson2JsonRedisSerializer.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/FilterConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/I18nConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/KaptchaTextCreator.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/MyBatisConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/RedisConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ResourcesConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/SecurityConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ServerConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/ThreadPoolConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/properties/DruidProperties.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/config/properties/PermitAllUrlProperties.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/datasource/DynamicDataSource.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/datasource/DynamicDataSourceContextHolder.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/interceptor/RepeatSubmitInterceptor.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/interceptor/impl/SameUrlDataInterceptor.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/manager/AsyncManager.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/manager/ShutdownManager.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/manager/factory/AsyncFactory.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/context/AuthenticationContextHolder.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/context/PermissionContextHolder.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/filter/JwtAuthenticationTokenFilter.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/handle/AuthenticationEntryPointImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/security/handle/LogoutSuccessHandlerImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/Server.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Cpu.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Jvm.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Mem.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/Sys.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/domain/server/SysFile.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/exception/GlobalExceptionHandler.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/PermissionService.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysLoginService.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysPasswordService.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysPermissionService.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/SysRegisterService.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/TokenService.java
 create mode 100644 MoMagTracker-Vue/tracker-framework/src/main/java/tracker/framework/web/service/UserDetailsServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/pom.xml
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/config/GenConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/controller/GenController.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/domain/GenTable.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/domain/GenTableColumn.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/mapper/GenTableColumnMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/mapper/GenTableMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/GenTableColumnServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/GenTableServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/IGenTableColumnService.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/service/IGenTableService.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/util/GenUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/util/VelocityInitializer.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/java/tracker/generator/util/VelocityUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/generator.yml
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/mapper/generator/GenTableColumnMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/mapper/generator/GenTableMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/controller.java.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/domain.java.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/mapper.java.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/service.java.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/serviceImpl.java.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/java/sub-domain.java.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/js/api.js.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/sql/sql.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/index-tree.vue.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/index.vue.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/v3/index-tree.vue.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/vue/v3/index.vue.vm
 create mode 100644 MoMagTracker-Vue/tracker-generator/src/main/resources/vm/xml/mapper.xml.vm
 create mode 100644 MoMagTracker-Vue/tracker-quartz/pom.xml
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/config/ScheduleConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/controller/SysJobController.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/controller/SysJobLogController.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/domain/SysJob.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/domain/SysJobLog.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/mapper/SysJobLogMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/mapper/SysJobMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/ISysJobLogService.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/ISysJobService.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/impl/SysJobLogServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/service/impl/SysJobServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/task/RyTask.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/AbstractQuartzJob.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/CronUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/JobInvokeUtil.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/QuartzDisallowConcurrentExecution.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/QuartzJobExecution.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/java/tracker/quartz/util/ScheduleUtils.java
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/resources/mapper/quartz/SysJobLogMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-quartz/src/main/resources/mapper/quartz/SysJobMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/pom.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysCache.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysConfig.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysLogininfor.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysNotice.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysOperLog.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysPost.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysRoleDept.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysRoleMenu.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysUserOnline.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysUserPost.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/SysUserRole.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/vo/MetaVo.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/domain/vo/RouterVo.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysConfigMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysDeptMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysDictDataMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysDictTypeMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysLogininforMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysMenuMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysNoticeMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysOperLogMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysPostMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysRoleDeptMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysRoleMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysRoleMenuMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysUserMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysUserPostMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/mapper/SysUserRoleMapper.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysConfigService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysDeptService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysDictDataService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysDictTypeService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysLogininforService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysMenuService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysNoticeService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysOperLogService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysPostService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysRoleService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysUserOnlineService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/ISysUserService.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysConfigServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysDeptServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysDictDataServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysDictTypeServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysLogininforServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysMenuServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysNoticeServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysOperLogServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysPostServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysRoleServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysUserOnlineServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/java/tracker/system/service/impl/SysUserServiceImpl.java
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysConfigMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysDeptMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysDictDataMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysDictTypeMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysLogininforMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysMenuMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysNoticeMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysOperLogMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysPostMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysRoleDeptMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysRoleMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysRoleMenuMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysUserMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysUserPostMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-system/src/main/resources/mapper/system/SysUserRoleMapper.xml
 create mode 100644 MoMagTracker-Vue/tracker-ui/.editorconfig
 create mode 100644 MoMagTracker-Vue/tracker-ui/.env.development
 create mode 100644 MoMagTracker-Vue/tracker-ui/.env.production
 create mode 100644 MoMagTracker-Vue/tracker-ui/.env.staging
 create mode 100644 MoMagTracker-Vue/tracker-ui/.gitignore
 create mode 100644 MoMagTracker-Vue/tracker-ui/README.md
 create mode 100644 MoMagTracker-Vue/tracker-ui/babel.config.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/bin/build.bat
 create mode 100644 MoMagTracker-Vue/tracker-ui/bin/package.bat
 create mode 100644 MoMagTracker-Vue/tracker-ui/bin/run-web.bat
 create mode 100644 MoMagTracker-Vue/tracker-ui/build/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/package.json
 create mode 100644 MoMagTracker-Vue/tracker-ui/public/favicon.ico
 create mode 100644 MoMagTracker-Vue/tracker-ui/public/favicon.ico.bak
 create mode 100644 MoMagTracker-Vue/tracker-ui/public/html/ie.html
 create mode 100644 MoMagTracker-Vue/tracker-ui/public/index.html
 create mode 100644 MoMagTracker-Vue/tracker-ui/public/robots.txt
 create mode 100644 MoMagTracker-Vue/tracker-ui/public/styles/theme-chalk/index.css
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/App.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/login.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/menu.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/cache.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/job.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/jobLog.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/logininfor.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/online.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/operlog.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/monitor/server.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/config.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/dept.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/dict/data.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/dict/type.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/menu.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/notice.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/post.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/role.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/system/user.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/api/tool/gen.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/401_images/401.gif
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/404_images/404.png
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/404_images/404_cloud.png
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/404.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/bug.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/build.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/button.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/cascader.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/chart.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/checkbox.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/clipboard.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/code.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/color.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/component.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/dashboard.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/date-range.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/date.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/dict.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/documentation.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/download.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/drag.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/druid.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/edit.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/education.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/email.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/enter.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/example.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/excel.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/exit-fullscreen.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/eye-open.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/eye.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/form.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/fullscreen.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/github.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/guide.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/icon.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/input.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/international.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/job.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/language.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/link.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/list.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/lock.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/log.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/logininfor.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/message.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/money.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/monitor.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/more-up.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/nested.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/number.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/online.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/password.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/pdf.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/people.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/peoples.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/phone.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/post.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/qq.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/question.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/radio.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/rate.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/redis-list.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/redis.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/row.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/search.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/select.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/server.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/shopping.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/size.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/skill.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/slider.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/star.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/swagger.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/switch.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/system.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tab.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/table.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/textarea.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/theme.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/time-range.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/time.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tool.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tree-table.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/tree.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/upload.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/user.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/validCode.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/wechat.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svg/zip.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/icons/svgo.yml
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/images/dark.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/images/light.svg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/images/login-background.jpg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/images/pay.png
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/images/profile.jpg
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/logo/logo.png
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/logo/logo.png.bak
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/btn.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/element-ui.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/element-variables.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/index.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/mixin.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/ruoyi.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/sidebar.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/transition.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/assets/styles/variables.scss
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Breadcrumb/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/day.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/hour.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/min.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/month.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/result.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/second.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/week.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Crontab/year.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/DictData/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/DictTag/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Editor/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/FileUpload/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Hamburger/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/HeaderSearch/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/IconSelect/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/IconSelect/requireIcons.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/ImagePreview/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/ImageUpload/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Pagination/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/PanThumb/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/ParentView/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/RightToolbar/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/RuoYi/Doc/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/RuoYi/Git/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/Screenfull/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/SizeSelect/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/SvgIcon/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/ThemePicker/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/TopNav/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/components/iFrame/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/dialog/drag.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/dialog/dragHeight.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/dialog/dragWidth.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/module/clipboard.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/permission/hasPermi.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/directive/permission/hasRole.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/AppMain.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Copyright/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/IframeToggle/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/InnerLink/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Navbar.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Settings/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/FixiOSBug.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/Item.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/Link.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/Logo.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/SidebarItem.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/Sidebar/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/TagsView/ScrollPane.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/TagsView/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/components/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/layout/mixin/ResizeHandler.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/main.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/permission.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/plugins/auth.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/plugins/cache.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/plugins/download.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/plugins/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/plugins/modal.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/plugins/tab.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/router/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/settings.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/getters.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/modules/app.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/modules/dict.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/modules/permission.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/modules/settings.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/modules/tagsView.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/store/modules/user.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/auth.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dict/Dict.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dict/DictConverter.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dict/DictData.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dict/DictMeta.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dict/DictOptions.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dict/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/dynamicTitle.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/errorCode.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/config.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/css.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/drawingDefault.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/html.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/icon.json
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/js.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/generator/render.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/index.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/jsencrypt.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/permission.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/request.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/ruoyi.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/scroll-to.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/utils/validate.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/dashboard/BarChart.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/dashboard/LineChart.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/dashboard/PanelGroup.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/dashboard/PieChart.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/dashboard/RaddarChart.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/dashboard/mixins/resize.js
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/error/401.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/error/404.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/index_v1.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/login.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/cache/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/cache/list.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/druid/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/job/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/job/log.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/logininfor/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/online/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/operlog/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/monitor/server/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/redirect.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/register.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/config/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/dept/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/dict/data.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/dict/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/menu/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/notice/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/post/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/role/authUser.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/role/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/role/selectUser.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/user/authRole.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/user/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/resetPwd.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/userAvatar.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/system/user/profile/userInfo.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/build/CodeTypeDialog.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/build/DraggableItem.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/build/IconsDialog.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/build/RightPanel.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/build/TreeNodeDialog.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/build/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/gen/basicInfoForm.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/gen/createTable.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/gen/editTable.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/gen/genInfoForm.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/gen/importTable.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/gen/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/src/views/tool/swagger/index.vue
 create mode 100644 MoMagTracker-Vue/tracker-ui/vue.config.js
 create mode 100644 MoMagTracker-Vue3/.env.development
 create mode 100644 MoMagTracker-Vue3/.env.production
 create mode 100644 MoMagTracker-Vue3/.env.staging
 create mode 100644 MoMagTracker-Vue3/.github/FUNDING.yml
 create mode 100644 MoMagTracker-Vue3/.gitignore
 create mode 100644 MoMagTracker-Vue3/LICENSE
 create mode 100644 MoMagTracker-Vue3/README.md
 create mode 100644 MoMagTracker-Vue3/bin/build.bat
 create mode 100644 MoMagTracker-Vue3/bin/package.bat
 create mode 100644 MoMagTracker-Vue3/bin/run-web.bat
 create mode 100644 MoMagTracker-Vue3/html/ie.html
 create mode 100644 MoMagTracker-Vue3/index.html
 create mode 100644 MoMagTracker-Vue3/package.json
 create mode 100644 MoMagTracker-Vue3/public/favicon.ico
 create mode 100644 MoMagTracker-Vue3/public/favicon.ico.bak
 create mode 100644 MoMagTracker-Vue3/src/App.vue
 create mode 100644 MoMagTracker-Vue3/src/api/login.js
 create mode 100644 MoMagTracker-Vue3/src/api/menu.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/cache.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/job.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/jobLog.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/logininfor.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/online.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/operlog.js
 create mode 100644 MoMagTracker-Vue3/src/api/monitor/server.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/config.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/dept.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/dict/data.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/dict/type.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/menu.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/notice.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/post.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/role.js
 create mode 100644 MoMagTracker-Vue3/src/api/system/user.js
 create mode 100644 MoMagTracker-Vue3/src/api/tool/gen.js
 create mode 100644 MoMagTracker-Vue3/src/assets/401_images/401.gif
 create mode 100644 MoMagTracker-Vue3/src/assets/404_images/404.png
 create mode 100644 MoMagTracker-Vue3/src/assets/404_images/404_cloud.png
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/404.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/bug.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/build.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/button.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/cascader.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/chart.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/checkbox.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/clipboard.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/code.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/color.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/component.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/dashboard.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/date-range.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/date.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/dict.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/documentation.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/download.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/drag.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/druid.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/edit.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/education.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/email.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/enter.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/example.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/excel.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/exit-fullscreen.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/eye-open.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/eye.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/form.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/fullscreen.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/github.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/guide.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/icon.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/input.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/international.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/job.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/language.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/link.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/list.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/lock.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/log.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/logininfor.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/message.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/money.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/monitor.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/moon.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/more-up.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/nested.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/number.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/online.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/password.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/pdf.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/people.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/peoples.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/phone.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/post.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/qq.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/question.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/radio.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/rate.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/redis-list.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/redis.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/row.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/search.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/select.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/server.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/shopping.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/size.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/skill.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/slider.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/star.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/sunny.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/swagger.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/switch.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/system.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/tab.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/table.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/textarea.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/theme.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/time-range.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/time.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/tool.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/tree-table.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/tree.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/upload.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/user.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/validCode.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/wechat.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/icons/svg/zip.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/images/dark.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/images/light.svg
 create mode 100644 MoMagTracker-Vue3/src/assets/images/login-background-sdu.png
 create mode 100644 MoMagTracker-Vue3/src/assets/images/login-background.jpg
 create mode 100644 MoMagTracker-Vue3/src/assets/images/pay.png
 create mode 100644 MoMagTracker-Vue3/src/assets/images/profile.jpg
 create mode 100644 MoMagTracker-Vue3/src/assets/logo/logo.png
 create mode 100644 MoMagTracker-Vue3/src/assets/logo/logo.png.bak
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/btn.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/element-ui.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/index.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/mixin.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/ruoyi.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/sidebar.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/transition.scss
 create mode 100644 MoMagTracker-Vue3/src/assets/styles/variables.module.scss
 create mode 100644 MoMagTracker-Vue3/src/components/Breadcrumb/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/day.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/hour.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/min.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/month.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/result.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/second.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/week.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Crontab/year.vue
 create mode 100644 MoMagTracker-Vue3/src/components/DictTag/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Editor/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/FileUpload/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Hamburger/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/HeaderSearch/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/IconSelect/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/IconSelect/requireIcons.js
 create mode 100644 MoMagTracker-Vue3/src/components/ImagePreview/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/ImageUpload/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Pagination/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/ParentView/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/RightToolbar/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/RuoYi/Doc/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/RuoYi/Git/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/Screenfull/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/SizeSelect/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/SvgIcon/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/SvgIcon/svgicon.js
 create mode 100644 MoMagTracker-Vue3/src/components/TopNav/index.vue
 create mode 100644 MoMagTracker-Vue3/src/components/iFrame/index.vue
 create mode 100644 MoMagTracker-Vue3/src/directive/common/copyText.js
 create mode 100644 MoMagTracker-Vue3/src/directive/index.js
 create mode 100644 MoMagTracker-Vue3/src/directive/permission/hasPermi.js
 create mode 100644 MoMagTracker-Vue3/src/directive/permission/hasRole.js
 create mode 100644 MoMagTracker-Vue3/src/layout/components/AppMain.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Copyright/index.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/IframeToggle/index.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/InnerLink/index.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Navbar.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Settings/index.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Sidebar/Link.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Sidebar/Logo.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Sidebar/SidebarItem.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/Sidebar/index.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/TagsView/ScrollPane.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/TagsView/index.vue
 create mode 100644 MoMagTracker-Vue3/src/layout/components/index.js
 create mode 100644 MoMagTracker-Vue3/src/layout/index.vue
 create mode 100644 MoMagTracker-Vue3/src/main.js
 create mode 100644 MoMagTracker-Vue3/src/permission.js
 create mode 100644 MoMagTracker-Vue3/src/plugins/auth.js
 create mode 100644 MoMagTracker-Vue3/src/plugins/cache.js
 create mode 100644 MoMagTracker-Vue3/src/plugins/download.js
 create mode 100644 MoMagTracker-Vue3/src/plugins/index.js
 create mode 100644 MoMagTracker-Vue3/src/plugins/modal.js
 create mode 100644 MoMagTracker-Vue3/src/plugins/tab.js
 create mode 100644 MoMagTracker-Vue3/src/router/index.js
 create mode 100644 MoMagTracker-Vue3/src/settings.js
 create mode 100644 MoMagTracker-Vue3/src/store/index.js
 create mode 100644 MoMagTracker-Vue3/src/store/modules/app.js
 create mode 100644 MoMagTracker-Vue3/src/store/modules/dict.js
 create mode 100644 MoMagTracker-Vue3/src/store/modules/permission.js
 create mode 100644 MoMagTracker-Vue3/src/store/modules/settings.js
 create mode 100644 MoMagTracker-Vue3/src/store/modules/tagsView.js
 create mode 100644 MoMagTracker-Vue3/src/store/modules/user.js
 create mode 100644 MoMagTracker-Vue3/src/utils/auth.js
 create mode 100644 MoMagTracker-Vue3/src/utils/dict.js
 create mode 100644 MoMagTracker-Vue3/src/utils/dynamicTitle.js
 create mode 100644 MoMagTracker-Vue3/src/utils/errorCode.js
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/config.js
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/css.js
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/drawingDefalut.js
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/html.js
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/icon.json
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/js.js
 create mode 100644 MoMagTracker-Vue3/src/utils/generator/render.js
 create mode 100644 MoMagTracker-Vue3/src/utils/index.js
 create mode 100644 MoMagTracker-Vue3/src/utils/jsencrypt.js
 create mode 100644 MoMagTracker-Vue3/src/utils/permission.js
 create mode 100644 MoMagTracker-Vue3/src/utils/request.js
 create mode 100644 MoMagTracker-Vue3/src/utils/ruoyi.js
 create mode 100644 MoMagTracker-Vue3/src/utils/scroll-to.js
 create mode 100644 MoMagTracker-Vue3/src/utils/theme.js
 create mode 100644 MoMagTracker-Vue3/src/utils/validate.js
 create mode 100644 MoMagTracker-Vue3/src/views/error/401.vue
 create mode 100644 MoMagTracker-Vue3/src/views/error/404.vue
 create mode 100644 MoMagTracker-Vue3/src/views/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/login.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/cache/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/cache/list.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/druid/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/job/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/job/log.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/logininfor/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/online/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/operlog/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/monitor/server/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/redirect/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/register.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/config/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/dept/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/dict/data.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/dict/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/menu/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/notice/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/post/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/role/authUser.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/role/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/role/selectUser.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/user/authRole.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/user/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/user/profile/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/user/profile/resetPwd.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/user/profile/userAvatar.vue
 create mode 100644 MoMagTracker-Vue3/src/views/system/user/profile/userInfo.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/build/CodeTypeDialog.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/build/DraggableItem.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/build/IconsDialog.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/build/RightPanel.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/build/TreeNodeDialog.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/build/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/gen/basicInfoForm.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/gen/createTable.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/gen/editTable.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/gen/genInfoForm.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/gen/importTable.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/gen/index.vue
 create mode 100644 MoMagTracker-Vue3/src/views/tool/swagger/index.vue
 create mode 100644 MoMagTracker-Vue3/vite.config.js
 create mode 100644 MoMagTracker-Vue3/vite/plugins/auto-import.js
 create mode 100644 MoMagTracker-Vue3/vite/plugins/compression.js
 create mode 100644 MoMagTracker-Vue3/vite/plugins/index.js
 create mode 100644 MoMagTracker-Vue3/vite/plugins/setup-extend.js
 create mode 100644 MoMagTracker-Vue3/vite/plugins/svg-icon.js
 create mode 100644 README.md

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ git remote add origin git@17354395073_github_account:Tang17354395073/MoMagTracker-System.git

17354@LAPTOP-3CJQNHQH MINGW64 /e/RuoYiProject/MoMagTracker-System (master)
$ git push -u origin master
Enumerating objects: 1067, done.
Counting objects: 100% (1067/1067), done.
Delta compression using up to 8 threads
Compressing objects: 100% (934/934), done.
Writing objects: 100% (1067/1067), 2.84 MiB | 1.46 MiB/s, done.
Total 1067 (delta 175), reused 0 (delta 0), pack-reused 0 (from 0)
remote: Resolving deltas: 100% (175/175), done.
To 17354395073_github_account:Tang17354395073/MoMagTracker-System.git
 * [new branch]      master -> master
branch 'master' set up to track 'origin/master'.
```

### 1.2 启动项目后端（Idea）

进入到 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-admin\src\main\java\tracker\MoMagTrackerApplication.java`

```shell
# 点击运行按钮
public class MoMagTrackerApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MoMagTrackerApplication.class, args);
        System.out.println(
                "(♥◠‿◠)ﾉﾞ  MoMagTracker 项目启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "#####  #####  #   #\n" +
                "  #      #    #   #\n" +
                "  #      #    # # #\n" +
                "  #      #    ## ##\n" +
                "  #    ###    #   #\n" +
                "\n" +
                "📍 项目：MoMagTracker\n" +
                "👤 作者：唐佳伟       \n" +
                "🕒 时间：" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "\n" +
                "✨ 系统初始化完成，开始为您服务！");
    }
}
```

![image-20251017101236143](README.assets/image-20251017101236143.png)

### 1.3 启动项目前端（VSCode）

进入到 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3`

```shell
# 终端启动服务
npm run dev
```

![image-20251017101430289](README.assets/image-20251017101430289.png)

## 2 系统功能开发（SMS 短信功能）

### 2.1 后端（MoMagTracker-Vue 模块 Idea）

#### 2.1.1 tracker-admin 模块

##### 1 增加生成验证码类

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-admin\src\main\java\tracker\web\controller\system` 文件夹中，创建 `SysGenerateEmail.java` 文件，并输入如下代码

```java
package tracker.web.controller.system;

import tracker.common.constant.Constants;
import tracker.common.core.domain.AjaxResult;
import tracker.common.core.redis.RedisCache;
import tracker.common.utils.StringUtils;
import tracker.common.utils.uuid.IdUtils;
import tracker.system.service.SysIEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(tags = "邮箱验证码接口")
@RestController
@RequestMapping("/email")
public class SysGenerateEmail {
    private static final Logger logger = LoggerFactory.getLogger(SysGenerateEmail.class);

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysIEmailService emailService;

    @ApiOperation("生成邮箱验证码")
    @PostMapping("/code")
    public AjaxResult generateEmailCode(@RequestBody Map<String, String> requestBody) {
        logger.info("收到邮箱验证码请求，参数: {}", requestBody);
        try {
            String email = requestBody.get("email");

            // 参数验证
            if (StringUtils.isEmpty(email)) {
                return AjaxResult.error("邮箱地址不能为空");
            }

            // 邮箱格式验证
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return AjaxResult.error("邮箱格式不正确");
            }

            // 生成验证码和 uuid
            String uuid = IdUtils.simpleUUID();
            String verifyKey = Constants.EMAIL_CAPTCHA_CODE_KEY + uuid;

            // 生成 6 位数字验证码
            int code = (int) (Math.random() * 900000) + 100000;

            // 构建验证码信息
            Map<String, Object> emailInfo = new HashMap<>();
            emailInfo.put("email", email);
            emailInfo.put("code", String.valueOf(code));
            emailInfo.put("createTime", System.currentTimeMillis());

            // 存储到 Redis，设置 3 分钟过期
            redisCache.setCacheObject(verifyKey, emailInfo, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);

            // 发送邮件
            boolean sendResult = emailService.sendVerificationCode(email, String.valueOf(code));

            if (sendResult) {
                logger.info("为邮箱 {} 发送验证码成功：{}, UUID: {}", email, code, uuid);

                AjaxResult ajax = AjaxResult.success("验证码发送成功");
                ajax.put("uuid", uuid);
                return ajax;
            } else {
                logger.error("为邮箱 {} 发送验证码失败", email);
                return AjaxResult.error("验证码发送失败，请稍后重试");
            }

        } catch (Exception e) {
            logger.error("生成邮箱验证码失败", e);
            return AjaxResult.error("验证码发送失败，请稍后重试");
        }
    }
}
```

- 结果

![image-20251027093849808](README.assets/image-20251027093849808.png)

##### 2 添加手机号登录映射 

在 ` E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-admin\src\main\java\tracker\web\controller\system\SysLoginController.java` 文件中添加下述代码

```java
package tracker.web.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Set;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tracker.common.constant.Constants;
import tracker.common.core.domain.AjaxResult;
import tracker.common.core.domain.entity.SysMenu;
import tracker.common.core.domain.entity.SysUser;
import tracker.common.core.domain.model.LoginBody;
import tracker.common.core.domain.model.LoginUser;
import tracker.common.core.text.Convert;
import tracker.common.utils.DateUtils;
import tracker.common.utils.SecurityUtils;
import tracker.common.utils.StringUtils;
import tracker.framework.web.service.SysLoginService;
import tracker.framework.web.service.SysPermissionService;
import tracker.framework.web.service.TokenService;
import tracker.system.service.ISysConfigService;
import tracker.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author tangjiawei
 */
@RestController
public class SysLoginController
{
    private static final Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 邮箱登录方法
     *
     * @param loginBody 登录信息（包含邮箱、邮箱验证码、uuid）
     * @return 结果
     */
    @ApiOperation("邮箱登录")
    @PostMapping("/email/login")
    public AjaxResult emailLogin(@RequestBody LoginBody loginBody) {
        logger.info("收到邮箱登录请求 - 邮箱: {}, 验证码: {}, UUID: {}",
                loginBody.getEmail(),
                loginBody.getEmailCode(),
                loginBody.getUuid());

        try {
            String email = loginBody.getEmail();
            String emailCode = loginBody.getEmailCode();
            String uuid = loginBody.getUuid();

            // 参数验证
            if (StringUtils.isEmpty(email) || StringUtils.isEmpty(emailCode) || StringUtils.isEmpty(uuid)) {
                logger.error("参数不完整 - 邮箱: {}, 验证码: {}, UUID: {}", email, emailCode, uuid);
                return AjaxResult.error("参数不完整");
            }

            // 邮箱格式验证
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                return AjaxResult.error("邮箱格式不正确");
            }

            // 调用登录服务
            AjaxResult ajax = loginService.emailLogin(email, emailCode, uuid);
            return ajax;

        } catch (Exception e) {
            logger.error("邮箱登录失败", e);
            return AjaxResult.error("登录失败，请稍后重试");
        }
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        if (!loginUser.getPermissions().equals(permissions))
        {
            loginUser.setPermissions(permissions);
            tokenService.refreshToken(loginUser);
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        ajax.put("isDefaultModifyPwd", initPasswordIsModify(user.getPwdUpdateDate()));
        ajax.put("isPasswordExpired", passwordIsExpiration(user.getPwdUpdateDate()));
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        logger.info("🔄 [后端] 获取路由信息，用户ID: {}", userId);

        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        logger.info("✅ [后端] selectMenuTreeByUserId 返回的菜单数量: {}", (menus != null ? menus.size() : 0));
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    
    // 检查初始密码是否提醒修改
    public boolean initPasswordIsModify(Date pwdUpdateDate)
    {
        Integer initPasswordModify = Convert.toInt(configService.selectConfigByKey("sys.account.initPasswordModify"));
        return initPasswordModify != null && initPasswordModify == 1 && pwdUpdateDate == null;
    }

    // 检查密码是否过期
    public boolean passwordIsExpiration(Date pwdUpdateDate)
    {
        Integer passwordValidateDays = Convert.toInt(configService.selectConfigByKey("sys.account.passwordValidateDays"));
        if (passwordValidateDays != null && passwordValidateDays > 0)
        {
            if (StringUtils.isNull(pwdUpdateDate))
            {
                // 如果从未修改过初始密码，直接提醒过期
                return true;
            }
            Date nowDate = DateUtils.getNowDate();
            return DateUtils.differentDaysByMillisecond(nowDate, pwdUpdateDate) > passwordValidateDays;
        }
        return false;
    }
}
```

- 结果

![image-20251027092558037](C:/Users/17354/Desktop/InSpur Project/项目 MoMagTracker-System.assets/image-20251027092558037.png)

##### 3 网易邮箱配置

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-admin\src\main\resources\application.yml` 文件中添加下述代码

```java
# Spring配置
spring:
  # 邮件配置 - 新增网易邮箱配置
  mail:
    host: smtp.163.com  # 邮箱服务器
    port: 465           # SSL端口
    username: 15948660043@163.com  # 发件人邮箱
    password: LVxYK34uNZTgMfUM  # SMTP授权码
    default-encoding: UTF-8
    protocol: smtp
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: false  # 不使用 TLS
          ssl:
            enable: true   # 使用 SSL 加密
          connectiontimeout: 5000
          timeout: 5000
          writetimeout: 5000
```

- 结果

![image-20251027092745320](README.assets/image-20251027092745320.png)

##### 4 继承父类 pom.xml

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-admin\pom.xml` 文件中加入下述代码（**父类地址：E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\pom.xml**）

```java
        <!-- 系统模块 - 新增 -->
        <dependency>
            <groupId>tracker</groupId>
            <artifactId>tracker-system</artifactId>
            <version>3.9.0</version>
        </dependency>
```

- 结果

![image-20251027092852936](README.assets/image-20251027092852936.png)

#### 2.1.2 tracker-common 模块

##### 1 增加手机号及短信验证码字段

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-common\src\main\java\tracker\common\core\domain\model\LoginBody.java` 文件中添加下述代码

```java
package tracker.common.core.domain.model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 用户登录对象
 * 
 * @author tangjiawei
 */
public class LoginBody
{
    private static final Logger logger = LoggerFactory.getLogger(LoginBody.class);

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 邮箱验证码
     */
    private String emailCode;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getEmail() {
        logger.info("🔧 getEmail 被调用，参数: {}", email);
        return email;
    }

    public void setEmail(String email) {
        logger.info("🔧 setEmail 被调用，参数: {}", email);
        this.email = email;
    }

    public String getEmailCode() {
        logger.info("🔧 getEmailCode 被调用，返回值: {}", emailCode);
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        logger.info("🔧 setEmailCode 被调用，参数: {}", emailCode);
        this.emailCode = emailCode;
    }
}
```

- 结果

![image-20251027093224515](README.assets/image-20251027093224515.png)

##### 2 添加相关常量

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-common\src\main\java\tracker\common\constant\Constants.java` 文件中添加如下代码

```java
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 邮箱验证码 redis key
     */
    public static final String EMAIL_CAPTCHA_CODE_KEY = "email_captcha_codes:";

    /**
     * 邮箱验证码有效期（分钟）
     */
    public static final Integer EMAIL_EXPIRATION = 1;
```

- 结果

![image-20251027093518022](README.assets/image-20251027093518022.png)

#### 2.1.3 tracker-framework 模块

##### 1 添加手机号登录验证方法

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-framework\src\main\java\tracker\framework\web\service\SysLoginService.java` 文件中输入下述代码

```java
package tracker.framework.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tracker.common.constant.CacheConstants;
import tracker.common.constant.Constants;
import tracker.common.constant.UserConstants;
import tracker.common.core.domain.AjaxResult;
import tracker.common.core.domain.entity.SysUser;
import tracker.common.core.domain.model.LoginUser;
import tracker.common.core.redis.RedisCache;
import tracker.common.exception.ServiceException;
import tracker.common.exception.user.BlackListException;
import tracker.common.exception.user.CaptchaException;
import tracker.common.exception.user.CaptchaExpireException;
import tracker.common.exception.user.UserNotExistsException;
import tracker.common.exception.user.UserPasswordNotMatchException;
import tracker.common.utils.DateUtils;
import tracker.common.utils.MessageUtils;
import tracker.common.utils.StringUtils;
import tracker.common.utils.ip.IpUtils;
import tracker.framework.manager.AsyncManager;
import tracker.framework.manager.factory.AsyncFactory;
import tracker.framework.security.context.AuthenticationContextHolder;
import tracker.framework.emailConfig.EmailCodeAuthenticationToken;
import tracker.system.service.ISysConfigService;
import tracker.system.service.ISysUserService;

import java.util.Map;

/**
 * 登录校验方法
 *
 * @author tangjiawei
 */
@Component
public class SysLoginService
{
    private static final Logger logger = LoggerFactory.getLogger(SysLoginService.class);

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        // 验证码校验
        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户验证
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            if (captcha == null)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            redisCache.deleteObject(verifyKey);
            if (!code.equalsIgnoreCase(captcha))
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    /**
     * 邮箱登录验证
     *
     * @param email 邮箱地址
     * @param emailCode 邮箱验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public AjaxResult emailLogin(String email, String emailCode, String uuid)
    {
        logger.info("=== 开始邮箱登录验证 ===");
        logger.info("邮箱: {}, UUID: {}, 验证码: {}", email, uuid, emailCode);

        // 用户验证
        Authentication authentication = null;
        try
        {
            logger.info("1. 检查邮箱验证码...");
            checkEmailCode(email, emailCode, uuid);
            logger.info("2. 邮箱验证码检查通过");

            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            // 使用 EmailCodeAuthenticationToken 进行认证
            logger.info("3. 创建 EmailCodeAuthenticationToken");
            EmailCodeAuthenticationToken authenticationToken = new EmailCodeAuthenticationToken(email, emailCode, uuid);
            AuthenticationContextHolder.setContext(authenticationToken);

            logger.info("4. 开始身份认证...");
            authentication = authenticationManager.authenticate(authenticationToken);
            logger.info("5. 身份认证成功");

            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();

            logger.info("6. 生成token");
            String token = tokenService.createToken(loginUser);

            AjaxResult ajax = AjaxResult.success();
            ajax.put(Constants.TOKEN, token);
            logger.info("7. 邮箱登录成功，返回token");

            return ajax;
        }
        catch (Exception e)
        {
            logger.error("邮箱登录失败 - 邮箱: {}, 错误: {}", email, e.getMessage(), e);
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_FAIL, e.getMessage()));
            return AjaxResult.error("登录失败: " + e.getMessage());
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
    }

    /**
     * 检查邮箱验证码
     * @param email 邮箱地址
     * @param inputCode 输入的验证码
     * @param uuid 验证码唯一标识
     */
    private void checkEmailCode(String email,String inputCode, String uuid) {
        logger.info("开始验证邮箱验证码 - 邮箱: {}, UUID: {}, 输入验证码: {}", email, uuid, inputCode);

        String verifyKey = Constants.EMAIL_CAPTCHA_CODE_KEY + uuid;
        logger.info("Redis键: {}", verifyKey);

        Map<String, Object> emailInfo =  redisCache.getCacheObject(verifyKey);
        logger.info("从 Redis 获取的验证码信息: {}", emailInfo);

        if(StringUtils.isEmpty(inputCode)){
            logger.error("验证码为空");
            throw new BadCredentialsException("验证码不能为空");
        }

        if(emailInfo == null) {
            logger.error("Redis 中未找到验证码信息，可能已过期");
            throw new BadCredentialsException("验证码失效");
        }

        String storedEmail = (String) emailInfo.get("email");
        Object codeObj = emailInfo.get("code");

        logger.info("存储的邮箱: {}, 存储的验证码对象: {}", storedEmail, codeObj);

        if(codeObj == null) {
            logger.error("验证码对象为null");
            throw new BadCredentialsException("验证码格式错误");
        }

        // 统一转为字符串比较
        String storedCode = String.valueOf(codeObj);
        logger.info("转换后的验证码: {}", storedCode);

        // 验证邮箱一致性
        if(!email.equals(storedEmail)) {
            logger.error("邮箱不匹配 - 输入: {}, 存储: {}", email, storedEmail);
            throw new BadCredentialsException("邮箱地址不一致");
        }

        // 验证验证码
        if(!storedCode.equals(inputCode)) {
            logger.error("验证码不匹配 - 输入: {}, 存储: {}", inputCode, storedCode);
            throw new BadCredentialsException("验证码错误");
        }

        // 验证通过后删除 Redis 中的验证码
        redisCache.deleteObject(verifyKey);
        logger.info("验证码验证成功，已从Redis删除");
    }
}
```

- 结果

![image-20251017171831450](C:/Users/17354/Desktop/InSpur Project/项目 MoMagTracker-System.assets/image-20251017171831450.png)

##### 2 增加短信配置

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-framework\src\main\java\tracker\framework` 文件夹下创建 `emailConfig` 文件夹，结构如下：

![image-20251027094308158](README.assets/image-20251027094308158.png)

##### 3 增加短信相关文件

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-framework\src\main\java\tracker\framework\emailConfig` 文件夹下创建下述文件，并在相应的文件中填入如下代码

###### CustomAuthenticationFailureHandler

```java
package tracker.framework.emailConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import tracker.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = getErrorMessage(exception);
        logger.warn("邮箱登录失败: {} - {}", exception.getClass().getSimpleName(), errorMessage);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        AjaxResult ajax = AjaxResult.error(errorMessage);
        response.getWriter().write(objectMapper.writeValueAsString(ajax));
    }

    /**
     * 根据异常类型返回更友好的错误信息
     */
    private String getErrorMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return exception.getMessage();
        } else if (exception instanceof InternalAuthenticationServiceException) {
            return "系统内部错误，请稍后重试";
        } else {
            return exception.getMessage() != null ? exception.getMessage() : "登录失败";
        }
    }
}
```

###### CustomAuthenticationSuccessHandler

```java
package tracker.framework.emailConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import tracker.common.constant.Constants;
import tracker.common.core.domain.AjaxResult;
import tracker.common.core.domain.model.LoginUser;
import tracker.framework.web.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TokenService tokenService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("邮箱登录成功");

        AjaxResult ajax = AjaxResult.success();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String token = tokenService.createToken(loginUser);
        ajax.put(Constants.TOKEN, token);

        logger.info("用户 {} 邮箱登录成功，生成token", loginUser.getUsername());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ajax));
    }
}
```

###### EmailCodeAuthenticationFilter

```shell
package tracker.framework.emailConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

/**
 * 邮箱登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter 实现
 */
public class EmailCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 邮箱地址的字段name
     */
    public static final String SPRING_SECURITY_FORM_EMAIL_KEY = "email";

    /**
     * 邮箱验证码的字段name
     */
    public static final String SPRING_SECURITY_FORM_EMAIL_CODE_KEY = "emailCode";

    /**
     * UUID的字段name
     */
    public static final String SPRING_SECURITY_FORM_UUID_KEY = "uuid";

    private String emailParameter = SPRING_SECURITY_FORM_EMAIL_KEY;
    private String emailCodeParameter = SPRING_SECURITY_FORM_EMAIL_CODE_KEY;
    private String uuidParameter = SPRING_SECURITY_FORM_UUID_KEY;

    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public EmailCodeAuthenticationFilter() {
        // 邮箱登录的请求 post 方式的 /email/login
        super(new AntPathRequestMatcher("/email/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        // 从请求体中读取 JSON 数据
        Map<String, String> requestBody = readRequestBody(request);

        String email = requestBody.get(emailParameter);
        String emailCode = requestBody.get(emailCodeParameter);
        String uuid = requestBody.get(uuidParameter);

        if (email == null) {
            email = "";
        }
        if (emailCode == null) {
            emailCode = "";
        }
        if (uuid == null) {
            uuid = "";
        }

        email = email.trim();

        // 创建包含所有认证信息的 Token
        EmailCodeAuthenticationToken authRequest = new EmailCodeAuthenticationToken(email, emailCode, uuid);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 从请求体中读取 JSON 数据
     */
    private Map<String, String> readRequestBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = reader.read(charBuffer)) != -1) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }

            if (stringBuilder.length() > 0) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(stringBuilder.toString(), Map.class);
            }
        } catch (Exception e) {
            logger.warn("读取请求体失败: " + e.getMessage());
        }
        return java.util.Collections.emptyMap();
    }

    protected void setDetails(HttpServletRequest request, EmailCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public String getEmailParameter() {
        return emailParameter;
    }

    public void setEmailParameter(String emailParameter) {
        Assert.hasText(emailParameter, "Email parameter must not be empty or null");
        this.emailParameter = emailParameter;
    }

    public String getEmailCodeParameter() {
        return emailCodeParameter;
    }

    public void setEmailCodeParameter(String emailCodeParameter) {
        Assert.hasText(emailCodeParameter, "Email code parameter must not be empty or null");
        this.emailCodeParameter = emailCodeParameter;
    }

    public String getUuidParameter() {
        return uuidParameter;
    }

    public void setUuidParameter(String uuidParameter) {
        Assert.hasText(uuidParameter, "UUID parameter must not be empty or null");
        this.uuidParameter = uuidParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
```

###### EmailCodeAuthenticationProvider

```java
package tracker.framework.emailConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tracker.common.constant.Constants;
import tracker.common.core.redis.RedisCache;
import tracker.common.utils.StringUtils;
import tracker.framework.web.service.EmailUserDetailsServiceImpl;

import java.util.Map;

/**
 * 邮箱登录鉴权 Provider
 */
@Component
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(EmailCodeAuthenticationProvider.class);

    private final EmailUserDetailsServiceImpl userDetailsService;
    private final RedisCache redisCache;

    // 明确指定使用 EmailUserDetailsServiceImpl
    @Autowired
    public EmailCodeAuthenticationProvider(EmailUserDetailsServiceImpl userDetailsService, RedisCache redisCache) {
        this.userDetailsService = userDetailsService;
        this.redisCache = redisCache;
        logger.info("EmailCodeAuthenticationProvider 初始化完成，userDetailsService: {}, redisCache: {}",
                userDetailsService != null ? "已注入" : "未注入",
                redisCache != null ? "已注入" : "未注入");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("开始邮箱认证流程");

        if (!(authentication instanceof EmailCodeAuthenticationToken)) {
            logger.error("认证类型不匹配，期望: EmailCodeAuthenticationToken, 实际: {}", authentication.getClass());
            return null;
        }

        EmailCodeAuthenticationToken authenticationToken = (EmailCodeAuthenticationToken) authentication;

        String email = (String) authenticationToken.getPrincipal();
        String inputCode = (String) authenticationToken.getCredentials();
        String uuid = (String) authenticationToken.getUuid();

        logger.info("邮箱认证 - 邮箱地址: {}, UUID: {}, 验证码: {}", email, uuid, inputCode);

        try {
            // 验证验证码
            checkEmailCode(email, inputCode, uuid);

            // 使用 EmailUserDetailsServiceImpl 加载用户
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            logger.info("成功加载用户信息: {}", userDetails.getUsername());

            // 创建认证成功的 Token
            EmailCodeAuthenticationToken authenticationResult =
                    new EmailCodeAuthenticationToken(userDetails, null, null, userDetails.getAuthorities());

            authenticationResult.setDetails(authenticationToken.getDetails());
            logger.info("邮箱认证成功");

            return authenticationResult;
        } catch (UsernameNotFoundException e) {
            logger.error("用户不存在: {}", email);
            throw new BadCredentialsException("用户不存在");
        } catch (Exception e) {
            logger.error("邮箱认证失败: {}", e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        }
    }

    /**
     * 验证邮箱验证码
     */
    private void checkEmailCode(String email, String inputCode, String uuid) {
        logger.info("开始验证邮箱验证码 - 邮箱: {}, UUID: {}, 输入验证码: {}", email, uuid, inputCode);

        // 检查 redisCache 是否注入成功
        if (redisCache == null) {
            logger.error("redisCache 未正确注入，无法进行验证码验证");
            throw new BadCredentialsException("系统配置错误，请稍后重试");
        }

        // 参数校验
        if (StringUtils.isEmpty(inputCode)) {
            logger.error("验证码为空");
            throw new BadCredentialsException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid)) {
            logger.error("UUID为空");
            throw new BadCredentialsException("UUID不能为空");
        }
        if (StringUtils.isEmpty(email)) {
            logger.error("邮箱地址为空");
            throw new BadCredentialsException("邮箱地址不能为空");
        }

        String verifyKey = Constants.EMAIL_CAPTCHA_CODE_KEY + uuid;
        logger.info("Redis键: {}", verifyKey);

        Map<String, Object> emailInfo = redisCache.getCacheObject(verifyKey);
        logger.info("从 Redis 获取的验证码信息: {}", emailInfo);

        if (emailInfo == null) {
            logger.error("Redis 中未找到验证码信息，可能已过期");
            throw new BadCredentialsException("验证码已失效，请重新获取");
        }

        String storedEmail = (String) emailInfo.get("email");
        Object codeObj = emailInfo.get("code");

        logger.info("存储的邮箱: {}, 存储的验证码对象: {}", storedEmail, codeObj);

        if (codeObj == null) {
            logger.error("验证码对象为null");
            throw new BadCredentialsException("验证码格式错误");
        }

        // 统一转为字符串比较
        String storedCode = String.valueOf(codeObj);
        logger.info("转换后的验证码: {}", storedCode);

        // 验证邮箱一致性
        if (!email.equals(storedEmail)) {
            logger.error("邮箱不匹配 - 输入: {}, 存储: {}", email, storedEmail);
            throw new BadCredentialsException("邮箱地址不一致");
        }

        // 验证验证码
        if (!storedCode.equals(inputCode)) {
            logger.error("验证码不匹配 - 输入: {}, 存储: {}", inputCode, storedCode);
            throw new BadCredentialsException("验证码错误");
        }

        // 验证通过后删除 Redis 中的验证码
        redisCache.deleteObject(verifyKey);
        logger.info("验证码验证成功，已从Redis删除");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean supports = EmailCodeAuthenticationToken.class.isAssignableFrom(authentication);
        logger.info("检查是否支持认证类型: {}, 结果: {}", authentication, supports);
        return supports;
    }
}
```

###### EmailCodeAuthenticationSecurityConfig

```java
package tracker.framework.emailConfig;

import tracker.framework.web.service.EmailUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class EmailCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private EmailUserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private EmailCodeAuthenticationProvider emailCodeAuthenticationProvider; // 直接注入已经配置好的 Provider

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 1. 创建并配置邮箱登录过滤器
        EmailCodeAuthenticationFilter emailCodeAuthenticationFilter = new EmailCodeAuthenticationFilter();
        emailCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        emailCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        emailCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        // 2. 不再创建新的 Provider，使用已经注入的 emailCodeAuthenticationProvider
        // 注意：这里不再设置 userDetailsService，因为已经在构造器中注入了

        // 3. 注册提供者并添加过滤器
        http.authenticationProvider(emailCodeAuthenticationProvider)
                .addFilterAfter(emailCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
```

###### EmailCodeAuthenticationToken

```java
package tracker.framework.emailConfig;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 邮箱登录 AuthenticationToken
 */
public class EmailCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;  // 邮箱地址
    private final Object credentials; // 验证码
    private final Object uuid;       // UUID

    /**
     * 构建一个没有鉴权的 EmailCodeAuthenticationToken
     */
    public EmailCodeAuthenticationToken(Object principal, Object credentials, Object uuid) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.uuid = uuid;
        setAuthenticated(false);
    }

    /**
     * 构建拥有鉴权的 EmailCodeAuthenticationToken
     */
    public EmailCodeAuthenticationToken(Object principal, Object credentials, Object uuid,
                                        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.uuid = uuid;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public Object getUuid() {
        return this.uuid;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
```

###### SpringContextUtil

```java
package tracker.framework.emailConfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    //     Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> requiredType) {

        return applicationContext.getBean(requiredType);
    }
}
```

##### 4 增加短信登录实现

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-framework\src\main\java\tracker\framework\web\service` 文件夹下新增 `EmailUserDetailsServiceImpl` 文件，并添加下述代码

```java
package tracker.framework.web.service;

import tracker.common.core.domain.entity.SysUser;
import tracker.common.core.domain.model.LoginUser;
import tracker.common.enums.UserStatus;
import tracker.common.exception.base.BaseException;
import tracker.common.utils.StringUtils;
import tracker.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 邮箱用户验证处理
 *
 * @author tangjiawei
 */
@Service
public class EmailUserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger logger = LoggerFactory.getLogger(EmailUserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        logger.info("EmailUserDetailsServiceImpl - 开始通过邮箱加载用户信息: {}", email);

        // 通过邮箱查找用户
        SysUser user = userService.selectUserByEmail(email);
        logger.info("通过邮箱查询到的用户: {}", user);

        if (StringUtils.isNull(user))
        {
            logger.info("登录邮箱：{} 不存在.", email);
            throw new UsernameNotFoundException("登录邮箱：" + email + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            logger.info("登录用户：{} 已被删除.", email);
            throw new BaseException("对不起，您的账号：" + email + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            logger.info("登录用户：{} 已被停用.", email);
            throw new BaseException("对不起，您的账号：" + email + " 已停用");
        }

        logger.info("用户 {} 加载成功", email);
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
```

- 结果

![image-20251027094717649](README.assets/image-20251027094717649.png)

##### 5 增加 @Primary 注解

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-framework\src\main\java\tracker\framework\web\service\UserDetailsServiceImpl.java` 文件中加入下述代码

```java
package tracker.framework.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tracker.common.core.domain.entity.SysUser;
import tracker.common.core.domain.model.LoginUser;
import tracker.common.enums.UserStatus;
import tracker.common.exception.base.BaseException;
import tracker.common.utils.StringUtils;
import tracker.system.service.ISysUserService;

/**
 * 用户验证处理
 *
 * @author tangjiawei
 */
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
```

- 结果

![image-20251027094914257](README.assets/image-20251027094914257.png)

##### 6 添加注入配置及放行映射

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-framework\src\main\java\tracker\framework\config\SecurityConfig.java` 文件中加入下述代码

```java
package tracker.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;
import tracker.framework.config.properties.PermitAllUrlProperties;
import tracker.framework.emailConfig.EmailCodeAuthenticationProvider;
import tracker.framework.emailConfig.EmailCodeAuthenticationSecurityConfig;
import tracker.framework.security.filter.JwtAuthenticationTokenFilter;
import tracker.framework.security.handle.AuthenticationEntryPointImpl;
import tracker.framework.security.handle.LogoutSuccessHandlerImpl;

import java.util.Arrays;

/**
 * spring security配置
 * 
 * @author tangjiawei
 */
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig
{
    /**
     * 自定义用户认证逻辑
     */
    @Autowired
    private UserDetailsService userDetailsService;
    
    /**
     * 认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    /**
     * 退出处理类
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * token认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;
    
    /**
     * 跨域过滤器
     */
    @Autowired
    private CorsFilter corsFilter;

    /**
     * 允许匿名访问的地址
     */
    @Autowired
    private PermitAllUrlProperties permitAllUrl;

    @Autowired
    private EmailCodeAuthenticationProvider emailCodeAuthenticationProvider;

    @Autowired
    private EmailCodeAuthenticationSecurityConfig emailCodeAuthenticationSecurityConfig;

    /**
     * 身份验证实现
     */
    @Bean
    public AuthenticationManager authenticationManager()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        // 创建包含多个认证提供者的 AuthenticationManager
        return new ProviderManager(Arrays.asList(
                daoAuthenticationProvider,
                emailCodeAuthenticationProvider
        ));
    }

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
            // CSRF禁用，因为不使用session
            .csrf(csrf -> csrf.disable())
            // 禁用HTTP响应标头
            .headers((headersCustomizer) -> {
                headersCustomizer.cacheControl(cache -> cache.disable()).frameOptions(options -> options.sameOrigin());
            })
            // 认证失败处理类
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            // 基于token，所以不需要session
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 注解标记允许匿名访问的url
            .authorizeHttpRequests((requests) -> {
                permitAllUrl.getUrls().forEach(url -> requests.antMatchers(url).permitAll());
                // 对于登录 login 注册 register 验证码 captchaImage 邮箱相关接口 允许匿名访问
                requests.antMatchers("/email/**","/login", "/register", "/captchaImage").permitAll()
                    // 静态资源，可匿名访问
                    .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                    .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest().authenticated();
            })
            // 添加邮箱登录认证配置
            .apply(emailCodeAuthenticationSecurityConfig)
            .and()
            // 添加Logout filter
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler))
            // 添加JWT filter
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
            // 添加CORS filter
            .addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class)
            .addFilterBefore(corsFilter, LogoutFilter.class)
            .build();
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
```

- 结果

![image-20251027095104232](README.assets/image-20251027095104232.png)

#### 2.1.4 tracker-system 模块

##### 1 添加手机号查询用户接口

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\src\main\java\tracker\system\service\ISysUserService.java` 文件中加入下述代码

```java
    /**
     * 通过邮箱地址查询用户
     *
     * @param email 邮箱地址
     * @return 用户对象信息
     */
    public SysUser selectUserByEmail(String email);
```

- 结果

![image-20251027095433044](README.assets/image-20251027095433044.png)

##### 2 添加手机号查询用户接口实现

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\src\main\java\tracker\system\service\impl\SysUserServiceImpl.java` 文件中加入下述代码

```java
    /**
     * 通过邮箱地址查询用户
     *
     * @param email 邮箱地址
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }
```

- 结果

![image-20251027095706479](README.assets/image-20251027095706479.png)

##### 3 添加手机号查询用户 tracker.system

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\src\main\java\tracker\system\mapper\SysUserMapper.java` 文件中加入下述代码

```java
    /**
     * 通过邮箱地址查询用户
     *
     * @param email 邮箱地址
     * @return 用户对象信息
     */
    public SysUser selectUserByEmail(String email);
```

- 结果

![image-20251027095918506](README.assets/image-20251027095918506.png)

##### 4 添加手机号查询用户 mapper.system

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\src\main\resources\mapper\system\SysUserMapper.xml` 文件中加入下述代码

```java
	<select id="selectUserByEmail" parameterType="String" resultMap="SysUserResult">
		<include refid="selectUserVo"/>
		where u.email = #{email}
	</select>
```

- 结果

![image-20251027100027052](README.assets/image-20251027100027052.png)

##### 5 SysEmailServiceImpl

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\src\main\java\tracker\system\service\impl\` 文件夹下新增 `SysEmailServiceImpl.java` 文件并添加下述代码

```java
package tracker.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tracker.system.service.SysIEmailService;

/**
 * 邮件服务实现类
 */
@Service
public class SysEmailServiceImpl implements SysIEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.name:MoMagTracker}")
    private String appName;

    @Override
    public boolean sendVerificationCode(String toEmail, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(appName + " - 登录验证码");
            message.setText("您的登录验证码是：" + code + "\n验证码有效期为1分钟，请勿泄露给他人。");

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
```

- 结果

![image-20251027100159611](README.assets/image-20251027100159611.png)

##### 6 SysIEmailService

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\src\main\java\tracker\system\service\` 文件夹下新增 `SysIEmailService.java` 文件并添加下述代码

```java
package tracker.system.service;

/**
 * 邮件服务接口
 */
public interface SysIEmailService {
    /**
     * 发送验证码邮件
     * @param toEmail 收件人邮箱
     * @param code 验证码
     * @return 发送结果
     */
    boolean sendVerificationCode(String toEmail, String code);
}
```

- 结果

![image-20251027100256444](README.assets/image-20251027100256444.png)

##### 7 继承父类 pom.xml

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\tracker-system\pom.xml` 文件中加入下述代码（**父类地址：E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue\pom.xml**）

```shell
        <!-- 添加邮件依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
            <version>2.5.15</version>
        </dependency>
```

##### Q: 如果存在 org.springframework.boot 识别不了的问题

清理并重新下载依赖：在 `IDEA` 中，选择 `File` -> `Invalidate Caches`  -> `Restart` 来清理缓存并重新启动 `IDEA`。这将清除可能存在的缓存问题并重新下载依赖。

- 结果

![image-20251027100829654](README.assets/image-20251027100829654.png)

### 2.2 前端（MoMagTracker-VUE3 模块 VsCode）

#### 2.2.1 增加短信登录和发送短信验证码方法

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3\src\api\login.js` 文件中加入下述代码

```java
import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  const data = {
    username,
    password,
    code,
    uuid
  }
  return request({
    url: '/login',
    headers: {
      isToken: false,
      repeatSubmit: false
    },
    method: 'post',
    data: data
  })
}

// 邮箱登录方法
export function emailLogin(email, emailCode, uuid) {
  console.log('API调用参数:', { email, emailCode, uuid })
  console.log('参数 - email:', email)
  console.log('参数 - emailCode:', emailCode)
  console.log('参数 - uuid:', uuid)

  const data = {
    email,
    emailCode,
    uuid
  }
  return request({
    url: '/email/login',
    method: 'post',
    data: data
  })
}

// 发送邮箱验证码
export function getEmailCode(email) {

  const data = {
    email
  }

  return request({
    url: '/email/code',
    method: 'post',
    data:data
  })
}

// 注册方法
export function register(data) {
  return request({
    url: '/register',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}
```

- 结果

![image-20251021083538445](README.assets/image-20251021083538445.png)

##### 1 API 接口定义流程

**定义认证接口 → 配置请求参数 → 封装请求方法 → 返回Promise对象**

##### 2 接口分类流程

**认证接口 → 验证码接口 → 用户信息接口 → 退出接口**

##### 3 请求配置流程

**设置请求路径 → 配置请求方法 → 定义请求头 → 传递数据参数 → 处理特殊配置**

#### 2.2.2 增加短信登录方法

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3\src\store\modules\user.js` 文件中加入下述代码

```java
import { defineStore } from 'pinia'
import router from '@/router'
import { ElMessageBox, } from 'element-plus'
import { emailLogin as apiEmailLogin, login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { isHttp, isEmpty } from "@/utils/validate"
import defAva from '@/assets/images/readme.jpg'

const useUserStore = defineStore(
  'user',
  {
    state: () => ({
      token: getToken(),
      id: '',
      name: '',
      nickName: '',
      avatar: '',
      roles: [],
      permissions: []
    }),
    actions: {
      // 登录
      login(userInfo) {
        const username = userInfo.username.trim()
        const password = userInfo.password
        const code = userInfo.code
        const uuid = userInfo.uuid
        return new Promise((resolve, reject) => {
          login(username, password, code, uuid).then(res => {
            // 存储 token 到本地
            setToken(res.token)
            this.token = res.token
            // 登录成功后自动获取用户信息
            this.getInfo().then(() => {
              resolve()
            }).catch(error => {
              // 即使获取用户信息失败，也认为登录成功
              console.error('获取用户信息失败:', error)
              resolve()
            })
          }).catch(error => {
            reject(error)
          })
        })
      },
      // 邮箱登录
      emailLogin(userInfo) { 
        const email = userInfo.email.trim()
        const emailCode = userInfo.emailCode
        const uuid = userInfo.uuid
        return new Promise((resolve, reject) => {
          apiEmailLogin(email, emailCode, uuid).then(res => {
            setToken(res.token)
            this.token = res.token 
            // 登录成功后自动获取用户信息
            this.getInfo().then(() => {
              resolve()
            }).catch(error => {
              // 即使获取用户信息失败，也认为登录成功
              console.error('获取用户信息失败:', error)
              resolve()
            })
          }).catch(error => {
            reject(error)
          })
        })
      },
      // 获取用户信息
      getInfo() {
        return new Promise((resolve, reject) => {
          getInfo().then(res => {
            console.log('=== 获取用户信息响应 ===')
            console.log('用户信息:', res.user)
            console.log('角色信息:', res.roles)
            console.log('权限信息:', res.permissions)

            const user = res.user

            // 头像处理逻辑
            let avatar = user.avatar || ""
            if (!isHttp(avatar)) {
              avatar = (isEmpty(avatar)) ? defAva : import.meta.env.VITE_APP_BASE_API + avatar
            }

            // 角色和权限处理
            if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
              this.roles = res.roles
              this.permissions = res.permissions
              console.log('✅ 设置用户角色:', this.roles)
            } else {
              this.roles = ['ROLE_DEFAULT']
              console.warn('⚠️ 用户没有分配角色，使用默认角色')
            }

            // 设置用户基本信息
            this.id = user.userId
            this.name = user.userName
            this.nickName = user.nickName
            this.avatar = avatar
            console.log('✅ 用户Store状态更新完成')

            /* 初始密码提示 */
            if(res.isDefaultModifyPwd) {
              ElMessageBox.confirm('您的密码还是初始密码，请修改密码！',  '安全提示', {  confirmButtonText: '确定',  cancelButtonText: '取消',  type: 'warning' }).then(() => {
                router.push({ name: 'Profile', params: { activeTab: 'resetPwd' } })
              }).catch(() => {})
            }
            /* 过期密码提示 */
            if(!res.isDefaultModifyPwd && res.isPasswordExpired) {
              ElMessageBox.confirm('您的密码已过期，请尽快修改密码！',  '安全提示', {  confirmButtonText: '确定',  cancelButtonText: '取消',  type: 'warning' }).then(() => {
                router.push({ name: 'Profile', params: { activeTab: 'resetPwd' } })
              }).catch(() => {})
            }
            resolve(res)
          }).catch(error => {
            console.error('❌ 获取用户信息失败:', error)
            reject(error)
          })
        })
      },
      // 退出系统
      logOut() {
        return new Promise((resolve, reject) => {
          logout(this.token).then(() => {
            this.token = ''
            this.roles = []
            this.permissions = []
            // 清除本地token
            removeToken()
            resolve()
          }).catch(error => {
            reject(error)
          })
        })
      }
    }
  })

export default useUserStore
```

- 结果

![image-20251021083953338](README.assets/image-20251021083953338.png)

##### 1 登录流程

**调用登录 API → 获取 token → 存储 token → 获取用户信息 → 更新 store 状态**

##### 2 用户信息流程

**调用 getInfo API → 处理头像路径 → 设置角色权限 → 检查密码状态 → 更新所有用户状态**

##### 3 退出流程

**调用退出 API → 清除本地 token → 重置 store 状态**

#### 2.2.3 增加发送短信验证码和短信登录逻辑

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3\src\views\login.vue` 文件中加入下述代码

```java
<template>
  <div class="login-container">
    <div class="login-wrapper">
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form">
        <h3 class="title">{{ title }}</h3>

        <!-- 账号密码登录 -->
        <el-form-item prop="username" v-if="!isEmailLogin">
          <el-input
            v-model="loginForm.username"
            type="text"
            size="large"
            auto-complete="off"
            placeholder="请输入账号"
            class="custom-input"
          >
            <template #prefix><svg-icon icon-class="user" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>
        
        <el-form-item prop="password" v-if="!isEmailLogin">
          <el-input
            v-model="loginForm.password"
            type="password"
            size="large"
            auto-complete="off"
            placeholder="请输入密码"
            @keyup.enter="handleLogin"
            class="custom-input"
          >
            <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>
        
        <el-form-item prop="code" v-if="!isEmailLogin" class="code-form-item">
          <el-input
            v-model="loginForm.code"
            size="large"
            auto-complete="off"
            placeholder="验证码"
            @keyup.enter="handleLogin"
            class="code-input"
          >
            <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img"/>
          </div>
        </el-form-item>

        <!-- 邮箱登录 -->
        <el-form-item prop="email" v-if="isEmailLogin">
          <el-input 
            v-model="loginForm.email" 
            type="text" 
            size="large"
            auto-complete="off" 
            placeholder="请输入邮箱地址"
            class="custom-input"
          >
            <template #prefix><svg-icon icon-class="email" class="el-input__icon input-icon" /></template>
          </el-input>
        </el-form-item>

        <el-form-item prop="emailCode" v-if="isEmailLogin" class="code-form-item">
          <el-input
            v-model="loginForm.emailCode"
            size="large"
            auto-complete="off"
            placeholder="邮箱验证码"
            @keyup.enter="handleLogin"
            class="code-input"
          >
            <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
          </el-input>
          <div class="email-code-btn">
            <el-button 
              round 
              @click.prevent="getEmailCode"
              :disabled="computeTime > 0"
              class="email-btn"
            >
              {{ computeTime > 0 ? `已发送(${computeTime}s)` : '获取验证码' }}
            </el-button>
          </div>
        </el-form-item>

        <!-- 底部操作区域 -->
        <div class="form-actions">
          <div class="left-actions">
            <el-checkbox v-model="loginForm.rememberMe" class="remember-me">记住密码</el-checkbox>
          </div>
          <div class="right-actions">
            <el-button
              size="small"
              type="primary"
              link
              @click.prevent="loginMethod"
              class="login-method-btn"
            >
              <span v-if="isEmailLogin">账号密码登录</span>
              <span v-else>邮箱登录</span>
            </el-button>
          </div>
        </div>
        
        <el-form-item class="login-btn-item">
          <el-button
            :loading="loading"
            size="large"
            type="primary"
            @click.prevent="handleLogin"
            class="login-btn"
          >
            <span v-if="!loading">{{ isEmailLogin ? '邮箱登录' : '账号登录' }}</span>
            <span v-else>{{ isEmailLogin ? '邮箱登录中...' : '账号登录中...' }}</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    
    <!-- 底部版权信息 -->
    <div class="login-footer">
      <span>Copyright © 2023-2026 MoMagTracker All Rights Reserved.</span>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getCodeImg, getEmailCode as apiGetEmailCode } from "@/api/login"
import Cookies from "js-cookie"
import { encrypt, decrypt } from "@/utils/jsencrypt"
import useUserStore from '@/store/modules/user'
import usePermissionStore from '@/store/modules/permission'
import { ElMessage } from 'element-plus'

const title = import.meta.env.VITE_APP_TITLE
const userStore = useUserStore()
const permissionStore = usePermissionStore()
const route = useRoute()
const router = useRouter()

// 响应式变量
const loginFormRef = ref()
const codeUrl = ref("")
const computeTime = ref(0)
const timer = ref(null)
const loading = ref(false)
const isEmailLogin = ref(false)
const redirect = ref(undefined)

// 表单数据
const loginForm = ref({
  username: "",
  password: "",
  rememberMe: false,
  code: "",
  uuid: "",
  email: "",
  emailCode: ""
})

// 表单规则
const loginRules = ref({
  username: [
    { required: true, trigger: "blur", message: "用户名不能为空" }
  ],
  email: [
    { required: true, trigger: "blur", message: "邮箱地址不能为空" },
    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
  ],
  password: [
    { required: true, trigger: "blur", message: "密码不能为空" }
  ],
  code: [{ required: true, trigger: "change", message: "验证码不能为空" }],
  emailCode: [
    { required: true, trigger: "blur", message: "邮箱验证码不能为空" },
    { pattern: /^\d{6}$/, message: "验证码为6位数字" }
  ]
})

// 监听路由变化
watch(route, (newRoute) => {
  redirect.value = newRoute.query && newRoute.query.redirect
  console.log('重定向路径:', redirect.value)
}, { immediate: true })

// 方法
function loginMethod() {
  isEmailLogin.value = !isEmailLogin.value
}

function getEmailCode() {
  if (computeTime.value === 0) {
    loginFormRef.value.validateField('email', (valid) => {
      if (valid) {
        apiGetEmailCode(loginForm.value.email).then(res => {
          if (res.code === 200) {
            ElMessage.success('验证码已发送到您的邮箱')
            loginForm.value.uuid = res.uuid
            computeTime.value = 60
            timer.value = setInterval(() => {
              computeTime.value--
              if (computeTime.value <= 0) {
                clearInterval(timer.value)
              }
            }, 1000)
          }
        }).catch(error => {
          ElMessage.error('获取验证码失败: ' + (error.message || '未知错误'))
        })
      }
    })
  }
}

function getCode() {
  getCodeImg().then(res => {
    codeUrl.value = "data:image/gif;base64," + res.img
    loginForm.value.uuid = res.uuid
  }).catch(error => {
    console.error('获取验证码失败:', error)
  })
}

function getCookie() {
  const username = Cookies.get("username")
  const password = Cookies.get("password")
  const rememberMe = Cookies.get("rememberMe")
  const email = Cookies.get("email")
  loginForm.value = {
    username: username === undefined ? loginForm.value.username : username,
    password: password === undefined ? loginForm.value.password : decrypt(password),
    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
    code: loginForm.value.code,
    uuid: loginForm.value.uuid,
    email: email === undefined ? loginForm.value.email : email,
    emailCode: loginForm.value.emailCode
  }
}

async function handleLogin() {
  console.log('=== 开始登录流程 ===')
  console.log('当前登录方式:', isEmailLogin.value ? '邮箱登录' : '账号密码登录')
  // 详细检查表单数据
  console.log('表单数据详情:')
  console.log('  - email:', loginForm.value.email)
  console.log('  - emailCode:', loginForm.value.emailCode)
  console.log('  - uuid:', loginForm.value.uuid)
  console.log('  - emailCode 类型:', typeof loginForm.value.emailCode)
  console.log('  - emailCode 长度:', loginForm.value.emailCode ? loginForm.value.emailCode.length : 0)
  
  loginFormRef.value.validate(async valid => {
    console.log('表单验证结果:', valid ? '通过' : '失败')

    if (valid) {
      console.log('表单验证通过，准备发送登录请求')
      
      if (isEmailLogin.value) {
        console.log('进入邮箱登录分支')
        loading.value = true

        // 确保参数正确传递
        const loginParams = {
          email: loginForm.value.email,
          emailCode: loginForm.value.emailCode,
          uuid: loginForm.value.uuid
        }
        console.log('发送的登录参数:', loginParams)

        if (loginForm.value.rememberMe) {
          Cookies.set("email", loginForm.value.email, { expires: 30 })
          Cookies.set('rememberMe', loginForm.value.rememberMe, { expires: 30 })
        } else {
          Cookies.remove("email")
          Cookies.remove('rememberMe')
        }
        
        try {
          // 执行邮箱登录
          await userStore.emailLogin({
            email: loginForm.value.email,
            emailCode: loginForm.value.emailCode,
            uuid: loginForm.value.uuid
          })
          
          console.log('✅ 邮箱登录成功，准备跳转')
          ElMessage.success('登录成功')

          // 确保用户信息完全加载
          console.log('🔄 确保用户信息完全加载...')
          await userStore.getInfo()
          
          // 生成动态路由
          console.log('🔄 生成动态路由...')
          await permissionStore.generateRoutes(userStore.roles)

          // 等待路由完全加载
          await new Promise(resolve => setTimeout(resolve, 100))

          // 详细检查用户状态
          console.log('登录后用户 Store 状态:', {
            token: userStore.token,
            roles: userStore.roles,
            permissions: userStore.permissions,
            name: userStore.name
          })

          // 检查路由守卫是否会被触发
          const targetPath = redirect.value || '/'
          console.log('准备跳转到:', targetPath)
          
          // 使用 replace 确保路由正确
          await router.replace({ path: redirect.value || "/" })
          console.log('跳转成功')
          
        } catch (error) {
          loading.value = false
          console.error('邮箱登录失败详情:', error)
          console.error('错误响应数据:', error.response?.data)
          console.error('错误状态码:', error.response?.status)
          console.error('错误信息:', error.response?.message)

          // 显示更具体的错误信息
          if (error.response?.data?.message) {
            ElMessage.error('登录失败: ' + error.response.data.message)
          } else {
            ElMessage.error(error.message || '登录失败，请检查邮箱和验证码')
          }
        }
      } else {
        console.log('进入账号密码登录分支')

        loading.value = true
        if (loginForm.value.rememberMe) {
          Cookies.set("username", loginForm.value.username, { expires: 30 })
          Cookies.set("password", encrypt(loginForm.value.password), { expires: 30 })
          Cookies.set("rememberMe", loginForm.value.rememberMe, { expires: 30 })
        } else {
          Cookies.remove("username")
          Cookies.remove("password")
          Cookies.remove("rememberMe")
        }
        
        try {
          // 执行账号密码登录
          await userStore.login(loginForm.value)
          
          console.log('账号密码登录成功，准备跳转')
          ElMessage.success('登录成功')

          // 确保用户信息完全加载
          await userStore.getInfo()
          
          // 生成动态路由
          await permissionStore.generateRoutes(userStore.roles)

          // 等待路由完全加载
          await new Promise(resolve => setTimeout(resolve, 100))

          // 跳转到目标页面
          const targetPath = redirect.value || "/"
          console.log('准备跳转到:', targetPath)
          await router.replace(targetPath)
          console.log('账号密码登录跳转成功')
        } catch (error) {
          loading.value = false
          console.error('账号密码登录失败:', error)
          ElMessage.error(error.message || '登录失败，请检查账号和密码')
          getCode()
        }
      }
    } else {
      console.log('表单验证失败')
      ElMessage.warning('请完善表单信息')
    }
  })
}

// 初始化
onMounted(() => {
  getCode()
  getCookie()
})
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-image: url("../assets/images/login-background-sdu.png");
  background-size: cover;
  background-position: center 10%;
  background-repeat: no-repeat;
  background-color: #f0f8ff;
  position: relative;
  padding: 20px;
}

.login-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 440px;
  z-index: 1;
}

.title {
  margin: 0 0 32px 0;
  text-align: center;
  color: #2c3e50;
  font-size: 28px;
  font-weight: 600;
  letter-spacing: 1px;
  text-shadow: 0 2px 4px rgba(255, 255, 255, 0.8);
}

.login-form {
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.92);
  width: 100%;
  padding: 40px 35px 30px;
  box-shadow: 
    0 12px 40px rgba(0, 0, 0, 0.15),
    0 4px 12px rgba(0, 0, 0, 0.08);
  backdrop-filter: blur(8px);
  border: 1px solid rgba(255, 255, 255, 0.3);
  
  :deep(.el-form-item) {
    margin-bottom: 24px;
  }
  
  :deep(.el-input) {
    height: 48px;
    font-size: 15px;
    
    input {
      height: 48px;
      border-radius: 10px;
      border: 1.5px solid #e1e8ed;
      background: #ffffff;
      transition: all 0.3s ease;
      font-size: 15px;
      color: #2c3e50;
      
      &:focus {
        border-color: #3498db;
        background: #fff;
        box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
      }
      
      &::placeholder {
        color: #95a5a6;
        font-size: 14px;
      }
    }
  }
  
  :deep(.input-icon) {
    height: 39px;
    width: 16px;
    margin-left: 4px;
    color: #7f8c8d;
  }
}

.code-form-item {
  display: flex;
  gap: 12px;
  align-items: flex-start;
  
  .code-input {
    flex: 1;
  }
}

.login-code {
  width: 120px;
  height: 48px;
  
  .login-code-img {
    width: 100%;
    height: 100%;
    border-radius: 10px;
    cursor: pointer;
    border: 1.5px solid #e1e8ed;
    transition: all 0.3s ease;
    
    &:hover {
      border-color: #3498db;
      box-shadow: 0 2px 8px rgba(52, 152, 219, 0.2);
    }
  }
}

.email-code-btn {
  width: 120px;
  
  .email-btn {
    width: 100%;
    height: 48px;
    border-radius: 10px;
    font-size: 13px;
    font-weight: 500;
    transition: all 0.3s ease;
    
    &:not(:disabled) {
      background: #3498db;
      border: none;
      color: white;
      
      &:hover {
        background: #2980b9;
        transform: translateY(-1px);
        box-shadow: 0 4px 12px rgba(52, 152, 219, 0.3);
      }
    }
    
    &:disabled {
      background: #bdc3c7;
      color: #7f8c8d;
      border-color: #bdc3c7;
    }
  }
}

.form-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 8px 0 24px;
  
  .left-actions {
    .remember-me {
      :deep(.el-checkbox__label) {
        color: #2c3e50;
        font-size: 14px;
        font-weight: 400;
      }
      
      :deep(.el-checkbox__inner) {
        border-radius: 4px;
        border-color: #bdc3c7;
        
        &:hover {
          border-color: #3498db;
        }
      }
    }
  }
  
  .right-actions {
    .login-method-btn {
      color: #3498db;
      font-size: 14px;
      font-weight: 500;
      padding: 4px 8px;
      transition: all 0.3s ease;
      
      &:hover {
        color: #2980b9;
        transform: translateX(2px);
      }
    }
  }
}

.login-btn-item {
  margin-bottom: 0;
  
  .login-btn {
    width: 100%;
    height: 48px;
    border-radius: 12px;
    background: #3498db;
    border: none;
    font-size: 16px;
    font-weight: 600;
    letter-spacing: 1px;
    transition: all 0.3s ease;
    box-shadow: 0 4px 15px rgba(52, 152, 219, 0.3);
    
    &:hover:not(:disabled) {
      background: #2980b9;
      transform: translateY(-2px);
      box-shadow: 0 6px 20px rgba(52, 152, 219, 0.4);
    }
    
    &:active {
      transform: translateY(0);
    }
  }
}

.login-footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 50px;
  line-height: 50px;
  text-align: center;
  color: rgba(255, 255, 255, 0.9);
  font-size: 13px;
  font-weight: 400;
  letter-spacing: 0.5px;
  background: rgba(44, 62, 80, 0.7);
  backdrop-filter: blur(5px);
  z-index: 1;
  text-shadow: 0 1px 2px rgba(0, 0, 0, 0.3);
}

// 响应式设计
@media (max-width: 480px) {
  .login-container {
    padding: 15px;
  }
  
  .login-form {
    padding: 30px 25px 25px;
  }
  
  .title {
    font-size: 24px;
    margin-bottom: 28px;
  }
  
  .form-actions {
    flex-direction: column;
    gap: 15px;
    align-items: stretch;
    
    .left-actions,
    .right-actions {
      text-align: center;
    }
  }
}
</style>
```

##### 1 登录界面渲染流程

**构建页面结构 → 渲染登录表单 → 处理两种登录模式 → 添加交互元素**

##### 2 用户交互处理流程

**监听用户输入 → 验证表单数据 → 调用相应API → 处理响应结果**

##### 3 状态管理流程

**初始化响应式数据 → 管理登录状态 → 处理加载状态 → 控制界面切换**

##### 4 生命周期流程

**组件挂载初始化 → 获取验证码 → 读取Cookie → 监听路由变化**

##### 5 样式处理流程

**设置基础样式 → 配置响应式布局 → 美化表单元素 → 添加动画效果**

#### 2.2.4 路由配置

##### 1 main.js

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3\src\main.js` 文件中加入下述代码

```java
import './permission'
```

##### 2 permission.js

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3\src\permission.js` 文件中加入下述代码

```java
import router from './router'
import { ElMessage } from 'element-plus'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
import { isHttp, isPathMatch } from '@/utils/validate'
import { isRelogin } from '@/utils/request'
import useUserStore from '@/store/modules/user'
import useSettingsStore from '@/store/modules/settings'
import usePermissionStore from '@/store/modules/permission'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/register']

const isWhiteList = (path) => {
  return whiteList.some(pattern => isPathMatch(pattern, path))
}

// 添加路由加载状态标识
let isRouteLoaded = false

router.beforeEach(async (to, from, next) => {
  NProgress.start()
  console.log('🚦 路由守卫开始 ===', to.path, 'Token:', getToken())
  
  if (getToken()) {
    console.log('✅ 检测到Token')
    to.meta.title && useSettingsStore().setTitle(to.meta.title)
    
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else if (isWhiteList(to.path)) {
      next()
    } else {
      const userStore = useUserStore()
      const permissionStore = usePermissionStore()
      console.log('👤 用户角色信息:', userStore.roles, '长度:', userStore.roles.length)
      
      // 无论用户信息是否存在，都确保路由已加载
      // 页面刷新时 userStore.roles 可能为空，但 isRouteLoaded 为 true，需要重新加载路由
      if (userStore.roles.length === 0 || !isRouteLoaded) {
        console.log('🔄 需要加载用户信息或路由...')
        isRelogin.show = true
        
        try {
          // 确保用户信息已加载
          if (userStore.roles.length === 0) {
            console.log('🔄 获取用户信息...')
            await userStore.getInfo()
            console.log('✅ 获取用户信息成功，角色:', userStore.roles)
          }

          // 确保动态路由已生成
          console.log('🔄 生成动态路由...')
          const accessRoutes = await permissionStore.generateRoutes(userStore.roles)
          console.log('🔄 生成的路由数量:', accessRoutes.length)

          // 添加动态路由（避免重复添加）
          // 页面刷新时需要重新添加路由
          if (!isRouteLoaded) {
            accessRoutes.forEach(route => {
              if (!isHttp(route.path)) {
                console.log('➕ 添加路由:', route.path, route.name)
                router.addRoute(route)
              }
            })
            isRouteLoaded = true
            console.log('✅ 所有路由添加完成')
          }
          
          isRelogin.show = false

          console.log('🔄 路由加载完成，重新导航到:', to.fullPath)
          next({ ...to, replace: true })
        } catch (error) {
          console.error('❌ 路由加载失败:', error)
          isRelogin.show = false
          userStore.logOut().then(() => {
            ElMessage.error('登录状态已过期，请重新登录')
            next({ path: '/login' })
          })
        }
        
      } else {
        console.log('✅ 用户信息和路由都已加载，直接跳转')
        // 确保当前路径可访问
        if (to.matched.length > 0) {
          next()
        } else {
          console.log('🔄 路径未匹配，尝试重新导航')
          next({ ...to, replace: true })
        }
      }
    }
  } else {
    // 没有token
    console.log('❌ 未检测到Token')
    if (isWhiteList(to.path)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  console.log('🏁 路由跳转完成')
  NProgress.done()
})

// 重置路由加载状态（在用户登出时调用）
export const resetRouteLoadStatus = () => {
  isRouteLoaded = false
}
```

##### 3 modules\permission.js

在 `E:\RuoYiProject\MoMagTracker-System\MoMagTracker-Vue3\src\store\modules\permission.js` 文件中加入下述代码

```java
import auth from '@/plugins/auth'
import router, { constantRoutes, dynamicRoutes } from '@/router'
import { getRouters } from '@/api/menu'
import Layout from '@/layout/index'
import ParentView from '@/components/ParentView'
import InnerLink from '@/layout/components/InnerLink'

// 匹配views里面所有的.vue文件
const modules = import.meta.glob('./../../views/**/*.vue')

const usePermissionStore = defineStore(
  'permission',
  {
    state: () => ({
      routes: [],
      addRoutes: [],
      defaultRoutes: [],
      topbarRouters: [],
      sidebarRouters: []
    }),
    actions: {
      setRoutes(routes) {
        this.addRoutes = routes
        this.routes = constantRoutes.concat(routes)
      },
      setDefaultRoutes(routes) {
        this.defaultRoutes = constantRoutes.concat(routes)
      },
      setTopbarRoutes(routes) {
        this.topbarRouters = routes
      },
      setSidebarRouters(routes) {
        this.sidebarRouters = routes
      },
      generateRoutes(roles) {
        return new Promise(resolve => {
          // 获取用户store
          console.log('🔄 [权限Store] 开始生成动态路由')
          console.log('🔄 [权限Store] 传入角色:', roles)
          console.log('🔄 [权限Store] 当前路由状态 - routes:', this.routes.length)
          console.log('🔄 [权限Store] 当前路由状态 - addRoutes:', this.addRoutes.length)

          // 向后端请求路由数据
          getRouters().then(res => {
            console.log('✅ [权限Store] 后端返回的路由数据状态码:', res.code)
            console.log('✅ [权限Store] 后端返回的路由数据:', res.data)

            if (!res.data || res.data.length === 0) {
              console.error('❌ [权限Store] 后端返回的路由数据为空！')
              resolve([])
              return
            }

            // 详细检查每个路由项
            res.data.forEach((route, index) => {
              console.log(`📋 路由 ${index}:`, {
                path: route.path,
                component: route.component,
                name: route.name,
                children: route.children ? route.children.length : 0
              })
            })

            const sdata = JSON.parse(JSON.stringify(res.data))
            const rdata = JSON.parse(JSON.stringify(res.data))
            const defaultData = JSON.parse(JSON.stringify(res.data))

            const sidebarRoutes = filterAsyncRouter(sdata)
            const rewriteRoutes = filterAsyncRouter(rdata, false, true)
            const defaultRoutes = filterAsyncRouter(defaultData)
            const asyncRoutes = filterDynamicRoutes(dynamicRoutes)

            console.log('🔄 过滤后的路由数量:')
            console.log('🔄 - sidebarRoutes:', sidebarRoutes.length)
            console.log('🔄 - rewriteRoutes:', rewriteRoutes.length)
            console.log('🔄 - asyncRoutes:', asyncRoutes.length)

            // 添加动态路由前检查
            asyncRoutes.forEach(route => {
              console.log(`➕ 准备添加动态路由: ${route.path}, 组件:`, route.component)
              router.addRoute(route)
              console.log(`✅ 已添加动态路由: ${route.path}`)
            })

            this.setRoutes(rewriteRoutes)
            this.setSidebarRouters(constantRoutes.concat(sidebarRoutes))
            this.setDefaultRoutes(sidebarRoutes)
            this.setTopbarRoutes(defaultRoutes)

            // 验证路由是否添加成功
            const allRoutes = router.getRoutes()
            console.log('📊 当前所有路由数量:', allRoutes.length)
            allRoutes.forEach(route => {
              console.log(`📍 路由: ${route.path}, 名称: ${route.name || '未命名'}`)
            })

            console.log('✅ 路由生成完成，最终路由:', rewriteRoutes)
            resolve(rewriteRoutes)
          }).catch(error => {
            console.error('❌ [权限Store] 获取路由失败:', error)
            console.error('❌ [权限Store] 错误详情:', error.response || error.message)
            resolve([])
          })
        })
      }
    }
  })

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
  return asyncRouterMap.filter(route => {
    if (type && route.children) {
      route.children = filterChildren(route.children)
    }
    if (route.component) {
      // Layout ParentView 组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else if (route.component === 'ParentView') {
        route.component = ParentView
      } else if (route.component === 'InnerLink') {
        route.component = InnerLink
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children, route, type)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}

function filterChildren(childrenMap, lastRouter = false) {
  var children = []
  childrenMap.forEach(el => {
    el.path = lastRouter ? lastRouter.path + '/' + el.path : el.path
    if (el.children && el.children.length && el.component === 'ParentView') {
      children = children.concat(filterChildren(el.children, el))
    } else {
      children.push(el)
    }
  })
  return children
}

// 动态路由遍历，验证是否具备权限
export function filterDynamicRoutes(routes) {
  const res = []
  routes.forEach(route => {
    if (route.permissions) {
      if (auth.hasPermiOr(route.permissions)) {
        res.push(route)
      }
    } else if (route.roles) {
      if (auth.hasRoleOr(route.roles)) {
        res.push(route)
      }
    }
  })
  return res
}

export const loadView = (view) => {
  let res
  console.log(`🔄 加载视图组件: ${view}`)
  
  for (const path in modules) {
    const dir = path.split('views/')[1].split('.vue')[0]
    console.log(`🔍 检查路径: ${path}, 目录: ${dir}`)
    
    if (dir === view) {
      res = () => modules[path]()
      console.log(`✅ 找到组件: ${view} -> ${path}`)
      break
    }
  }
  
  if (!res) {
    console.error(`❌ 未找到组件: ${view}`)
    // 返回一个默认的404组件或者空组件
    return () => import('@/views/error/404.vue')
  }
  
  return res
}

export default usePermissionStore
```

### 2.3 数据库（Idea 连接 MySQL 数据库）

打开侧边栏的 `Database`，并输入自己数据库的账号和密码，下载对应的 `MySQL` 驱动，测试链接是否成功。

```shell
user：root
password：tang2001
```

![image-20251027084544359](README.assets/image-20251027084544359.png)

### 2.4 推送至远程仓库

```shell
ls -a

git status

git add .

git status

git commit -m "第二次提交，修改总体概括 README.md 文件，在原来的账号密码登录功能之外，新增邮箱登录功能"

git push -u origin dev
```













