package com.javarush.springfinal.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
@AllArgsConstructor
public class JwtTokenHelper {

    private final JwtProperties jwtProperties;

    public String createToken(String username, Map<String, Object> claims) {

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(jwtProperties.getSecret()),
            SignatureAlgorithm.HS256.getJcaName());

        Date expiryDate =
                Date.from(Instant.ofEpochMilli(System.currentTimeMillis() +
                        jwtProperties.getValidateTime()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer("MyApplication")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(hmacKey)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String tokenUsername = extractUsername(token);
        return tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }



    private Claims extractAllClaims(String token) {
        return parseJwt(token).getBody();
    }

    public Jws<Claims> parseJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecret())
                .build()
                .parseClaimsJws(token);
    }

}
