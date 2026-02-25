package com.example.Spring_back.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey encodedKey;

    @Value("${jwt.expire}")
    private int expire;

    public JwtUtil(@Value("${jwt_key}") String key) {
        this.encodedKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));;
    }
    public String createToken(Long idx, String email, String role) {
        System.out.println(email);
        String jwt = Jwts.builder()
                .claim("idx", idx)
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+expire)).signWith(encodedKey).compact();

        return jwt;
    }

    public Long getUserIdx(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(encodedKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("idx", Long.class);
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(encodedKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("email", String.class);
    }

    public String getRole(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(encodedKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("role", String.class);
    }
}