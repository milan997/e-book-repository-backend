package milan.miljus.eBookRepository2019.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by milan.miljus on 2019-07-05 01:00.
 */
class CustomAuthentication implements Authentication {

    private final UUID id;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated = true;

    CustomAuthentication(UUID id, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.id = id;
    }

    @Override
    public Object getPrincipal() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        if (!authenticated) {
            this.authenticated = false;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
