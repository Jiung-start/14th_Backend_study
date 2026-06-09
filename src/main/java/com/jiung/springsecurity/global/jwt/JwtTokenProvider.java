package com.jiung.springsecurity.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration ;


    public String createToken(Long userId, String email) {
        Key key = Keys.hmacShaKeyFor
                (secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .setExpiration(

                        new java.util.Date(
                                System.currentTimeMillis() +expiration
                        )
                )
                .signWith(key)
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(
                    secretKey.getBytes(StandardCharsets.UTF_8)
            );

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }

    }
    public Long getUserIdFromToken(String token) {

        Key key = Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );

        return ((Integer) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId"))
                .longValue();
    }
}
