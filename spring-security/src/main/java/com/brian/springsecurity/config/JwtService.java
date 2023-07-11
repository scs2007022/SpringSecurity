package com.brian.springsecurity.config;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "FIv3CBYaZNmJSAqaR+VLQiWEu0bPgG1TD5whbk7RO9kc+8RJ3EJpxa4acv9YD09C";

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }
    
    public boolean isTokenValid(String jwt, UserDetails userDetails){
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }
    
    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
    
    private Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
    }
    
    public String generateJwt(UserDetails userDetails){
        return generateJwt(new HashMap<>(), userDetails);
    }

    public String generateJwt(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+3*24*60*60))
        .signWith(getSignInKey(),SignatureAlgorithm.HS256)
        .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
