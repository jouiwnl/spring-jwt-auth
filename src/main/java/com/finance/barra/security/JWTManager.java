package com.finance.barra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTManager {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public boolean tokenValid(String token) {
        Claims claims = this.getClaims(token);

        if (claims != null) {
            String user = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            return user != null && expiration != null && now.before(expiration);
        }

        return false;
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUser(String token) {
        Claims claims = this.getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

}