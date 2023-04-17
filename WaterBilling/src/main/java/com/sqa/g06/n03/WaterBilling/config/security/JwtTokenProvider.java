package com.sqa.g06.n03.WaterBilling.config.security;

import com.sqa.g06.n03.WaterBilling.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}")
    private String MY_SECRET_KEY;
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public Map<String, String> signToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        Map<String, String> map = new HashMap<>();

        map.put("username", user.getUsername());
        map.put("firstname", user.getFirstname());
        map.put("lastname", user.getLastname());
        map.put("phone", user.getPhone());
        map.put("email", user.getEmail());
        map.put("role", user.getRole().getName());

        String token =  Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(new SecretKeySpec(MY_SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName()), SignatureAlgorithm.HS256)
                .compact();
        map.put("token", token);
        return map;
    }

    public String validateToken(String token) {
        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(new SecretKeySpec(MY_SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
                    .build()
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            return claims.getSubject();
        }catch(Exception ex){
            System.out.println("Error when decode");
            return null;
        }
    }
}
