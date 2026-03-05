package com.example.tlias;

import java.util.Date;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTTest {
    @Test
    public void generateJWT(){
        var scKey=Keys.hmacShaKeyFor("a-secure-key-that-meets-length-requirements".getBytes());
        var token=Jwts.builder()
            .signWith(scKey,Jwts.SIG.HS256)
            .claims()
            .add("id",1)
            .add("username","admin")
            .expiration(new Date(System.currentTimeMillis()+3600*1000))
            .and()
            .compact();
        System.out.println(token);
        // eyJhbGciOiJIUzI1NiJ9. {"alg":"HS256"}
        // eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3MjQzMzA2N30. {"id":1,"username":"admin","exp":1772433067}
        // wJKfs9sMKckzObEiUQ2rAS7f-wvv_v8JBogcaTkzE0Y
    }

    @Test
    public void parseJWT(){
        var scKey=Keys.hmacShaKeyFor("a-secure-key-that-meets-length-requirements".getBytes());
        var token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3MjQzMzA2N30.wJKfs9sMKckzObEiUQ2rAS7f-wvv_v8JBogcaTkzE0Y";
        Claims claims=Jwts.parser()
            .verifyWith(scKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        System.out.println(claims);
    }
}
