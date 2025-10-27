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
