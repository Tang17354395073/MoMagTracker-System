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
