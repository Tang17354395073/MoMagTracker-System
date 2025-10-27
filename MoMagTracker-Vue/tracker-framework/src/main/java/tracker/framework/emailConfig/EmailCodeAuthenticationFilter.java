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