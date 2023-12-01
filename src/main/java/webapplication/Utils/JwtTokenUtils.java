package webapplication.Utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Getter
@Component
//@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtils {

    private byte[] secret = generateSecretKey();
    private Duration jwtLifetime = Duration.ofMinutes(30);

    private byte[] generateSecretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
    }

    public void setSecret(byte[] secret) {
        this.secret = secret;
    }

    public void setJwtLifetime(Duration jwtLifetime) {
        this.jwtLifetime = jwtLifetime;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    public List<?> getRoles(String token) {
        return parseToken(token).get("roles", List.class);
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret)).build().parseClaimsJws(token).getBody();
    }
}
