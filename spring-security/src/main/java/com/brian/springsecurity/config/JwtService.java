package com.brian.springsecurity.config;

import org.springframework.boot.autoconfigure.ssl.SslBundleProperties.Key;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "FIv3CBYaZNmJSAqaR+VLQiWEu0bPgG1TD5whbk7RO9kc+8RJ3EJpxa4acv9YD09C";

    public String extractUsername(String jwt) {
        return null;
    }

    private Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimJws(jwt).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
