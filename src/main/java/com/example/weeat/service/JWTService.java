package com.example.weeat.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private String secretKey="";

    public JWTService() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey key = keyGenerator.generateKey();
        secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<String, Object>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}