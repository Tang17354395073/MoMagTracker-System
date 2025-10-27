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
