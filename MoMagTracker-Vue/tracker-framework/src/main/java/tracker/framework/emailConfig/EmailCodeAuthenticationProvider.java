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