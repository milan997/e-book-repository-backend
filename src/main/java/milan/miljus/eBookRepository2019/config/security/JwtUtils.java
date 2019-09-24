package milan.miljus.eBookRepository2019.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import milan.miljus.eBookRepository2019.model.Sub;
import milan.miljus.eBookRepository2019.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by milan.miljus on 9/11/19 20:25.
 */
@UtilityClass
public class JwtUtils {

    private final byte[] JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf".getBytes();
    private final String TOKEN_ISSUER = "salvador";
    private final String TOKEN_AUDIENCE = "ebookrepository";
    private final String ROLE_KEY = "role";

    public Authentication getAuthentication(String token) {
        final Claims claims = parseClaimsFromToken(token);
        final String userId = claims.getSubject();
        final String role = (String) claims.get(ROLE_KEY);

        final List<SimpleGrantedAuthority> simpleGrantedAuthorities =
                Collections.singletonList(new SimpleGrantedAuthority(role));

        return new PreAuthenticatedAuthenticationToken(userId, null, simpleGrantedAuthorities);
    }

    private Claims parseClaimsFromToken(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public String generateToken(final User user) {
        final String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET), SignatureAlgorithm.HS256)
                .setHeaderParam("typ", "JWT")
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim(ROLE_KEY, user.getRole())
                .claim("categories", user.isSub() ? ((Sub) user).getCategoryIds() : true)
                .claim("username", user.getUsername())
                .compact();
        return token;
    }

}
