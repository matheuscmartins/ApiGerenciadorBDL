package br.com.brigadadoslobos.gerenciador.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
    // nova: gera token incluindo roles como claim
    public String generateToken(String email, List<String> roles){
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)              // aqui incluímos os perfis
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
    // novo: obter lista de roles do token (pode retornar null)
    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            Object rolesObj = claims.get("roles");
            if (rolesObj instanceof List) {
                return (List<String>) rolesObj;
            }
        }
        return null;
    }
}
