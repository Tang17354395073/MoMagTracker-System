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
