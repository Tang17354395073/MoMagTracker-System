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