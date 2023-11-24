package com.vseven.launchpad.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtGenerator {

    public static SecretKey key = Jwts.SIG.HS256.key().build();
    private final long JWT_EXPIRATION = 31082004;

    public static String createToken(Authentication authentication) {
        Date now = new Date();
        Date expired = new Date(now.getTime()  + 100000000);
      //System.out.println(username);

        String token = Jwts.builder()
            .subject(authentication.getName())
                .issuedAt(now)
                .expiration(expired)
                .signWith(key)
                .compact();

        return token;
    }
    public  String getUsernameFromJWT(String token)  {
        String jyp = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token).getBody().getSubject();

        System.out.println(jyp);
        return jyp;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect",ex.fillInStackTrace());
        }
    }
}
