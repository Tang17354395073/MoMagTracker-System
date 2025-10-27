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