package com.brian.springsecurity.config;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    private static final String SECRET_KEY = "";

    public String extractUsername(String jwt) {
        return null;
    }

    private Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimJws(jwt).getBody();
    }
}
