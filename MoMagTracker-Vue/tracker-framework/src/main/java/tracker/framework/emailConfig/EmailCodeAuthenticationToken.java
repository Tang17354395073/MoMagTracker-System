package tracker.framework.emailConfig;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 邮箱登录 AuthenticationToken
 */
public class EmailCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;  // 邮箱地址
    private final Object credentials; // 验证码
    private final Object uuid;       // UUID

    /**
     * 构建一个没有鉴权的 EmailCodeAuthenticationToken
     */
    public EmailCodeAuthenticationToken(Object principal, Object credentials, Object uuid) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.uuid = uuid;
        setAuthenticated(false);
    }

    /**
     * 构建拥有鉴权的 EmailCodeAuthenticationToken
     */
    public EmailCodeAuthenticationToken(Object principal, Object credentials, Object uuid,
                                        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.uuid = uuid;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public Object getUuid() {
        return this.uuid;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}