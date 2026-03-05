package com.example.tlias.utils;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTUtils {
    private static final String SECRET_KEY="a-secure-key-that-meets-length-requirements";

    private static final long EXPIRATION_TIME=12*60*60*1000L;


    public static String generateToken(Map<String,Object> claims){
        var scKey=Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        var claim=Jwts.builder()
            .signWith(scKey,Jwts.SIG.HS256)
            .claims();
        claims.forEach(claim::add);
        return claim.expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
            .and()
            .compact();
    }

    public static Map<String,?> parseToken(String token) throws Exception {
        var scKey=Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parser()
            .verifyWith(scKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
