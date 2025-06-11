package com.PixelGround.back.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 5; // 5 horas

    public String generateToken(String subject, String rol) {
        return Jwts.builder()
            .setSubject(subject)
            .claim("rol", rol)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key)
            .compact();
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) 
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getClaim(String token, String claim) {
        return Jwts.parserBuilder()
                .setSigningKey(key) 
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(claim, String.class);
    }

    public boolean validateToken(String token, String subject) {
        try {
            return getSubject(token).equals(subject);
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean tieneRol(String token, String rol) {
        try {
            String tokenLimpio = token.replace("Bearer ", "");
            String rolExtraido = getClaim(tokenLimpio, "rol");
            return rolExtraido != null && rolExtraido.equalsIgnoreCase(rol);
        } catch (Exception e) {
            return false;
        }
    }

}

