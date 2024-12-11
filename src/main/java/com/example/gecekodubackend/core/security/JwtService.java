package com.example.gecekodubackend.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    //defines the secret key for jwt
    @Value("${jwt.key}")
    private String SECRET;

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        claims.put("can","wia");
        return createToken(claims.username);
    }

    private Boolean validateToken(String token, UserDetails userDetails){
        String username = exractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(username) && expirationDate.after(new Date());
    }

    private Date extractExpiration(String token){
        Claims claims = Jwts
                .parseBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    private String exractUser(String token){
        Claims claims = Jwts
                .parseBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis() + 1000*60*2))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
