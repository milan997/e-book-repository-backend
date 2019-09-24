package milan.miljus.eBookRepository2019.config.security;

import milan.miljus.eBookRepository2019.model.enumeration.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Auth {

    public boolean hasRole(final Role role) {
        return role == getLoggedInUserRole();
    }

    public boolean loggedIn() {
        return getLoggedInUserRole() != null;
    }

    private Role getLoggedInUserRole() {
        final Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        final List<String> roles = grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        final String role = roles.get(0);
        return role != "ROLE_ANONYMOUS" ? Role.valueOf(role) : null;
    }

}
