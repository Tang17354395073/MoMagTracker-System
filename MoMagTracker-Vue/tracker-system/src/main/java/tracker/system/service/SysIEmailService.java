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