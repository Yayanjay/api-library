package com.library.apilibrary.config;

import java.util.Date;

import com.library.apilibrary.service.UserDetailsImpl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
    private String jwtSecret = "secretkey";
    private int jwtExpirationMS = 60000;

    // generate token
    public String generatetoken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()*jwtExpirationMS))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
    }

    // retrieve username
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    // token validation
    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);

            return true;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return false;
        }
        
    }
}
