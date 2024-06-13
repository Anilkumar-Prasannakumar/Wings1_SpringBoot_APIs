package com.online.ecommerce.service;

import com.online.ecommerce.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "abckjfrjoejk5yffhf333dffdfsadadfagfhgj32khklhl72khjkjjlk3shhg225";

    public String generateToken(UserInfo userInfo){
        return Jwts
                .builder()
                .subject(userInfo.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String jwt){
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(jwt);
        return resolver.apply(claims);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }

    public Date extractExpiration(String jwt){
        return extractClaim(jwt, Claims::getExpiration);
    }

    public boolean isTokenExpired(String jwt){
        return extractExpiration(jwt).before(new Date());
    }
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername());
    }
}
