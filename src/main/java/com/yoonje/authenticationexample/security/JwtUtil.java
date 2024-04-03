package com.yoonje.authenticationexample.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:jwt.yml")
@Component
public class JwtUtil {
    private final String secretKey;

    public JwtUtil(
            @Value("${secret-key}") String secretKey
    ) {
        this.secretKey = secretKey;
    }

    public String getMemberIdFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        System.out.println("회원 아이디: " + String.valueOf(claims.getSubject()));
        return String.valueOf(claims.getSubject());
    }

}
