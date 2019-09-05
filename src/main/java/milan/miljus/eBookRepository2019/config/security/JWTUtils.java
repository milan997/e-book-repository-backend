package milan.miljus.eBookRepository2019.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtils {

    private static final String USER_ID_KEY = "sub";
    private static final String ROLE_KEY = "role";

    Authentication getAuthentication(String token) {
        final Map<String, Claim> claims = getClaimsFromToken(token);

        final UUID userId = UUID.fromString(claims.get(USER_ID_KEY).asString());
        final String role = claims.get(ROLE_KEY).asString();

        final List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority(role));
        return new CustomAuthentication(userId, roles);
    }

    private Map<String, Claim> getClaimsFromToken(String token) {
        return JWT.decode(token).getClaims();
    }

}
