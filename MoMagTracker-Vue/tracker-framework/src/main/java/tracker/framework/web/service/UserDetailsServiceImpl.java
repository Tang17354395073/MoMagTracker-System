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
        log.info("=== UserDetailsServiceImpl 开始加载用户 ===");
        log.info("查询用户名: {}", username);
        SysUser user = userService.selectUserByUserName(username);

        if (StringUtils.isNull(user))
        {
            log.error("❌ 登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }

        log.info("✅ 用户查询成功 - 用户ID: {}, 用户名: {}, 密码哈希: {}",
                user.getUserId(), user.getUserName(), user.getPassword());
        log.info("用户状态 - status: {}, delFlag: {}", user.getStatus(), user.getDelFlag());

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.error("❌ 登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.error("❌ 登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        log.info("✅ 用户状态检查通过，创建 LoginUser");
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}