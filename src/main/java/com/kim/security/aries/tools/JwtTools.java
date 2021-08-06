package com.kim.security.aries.tools;

import io.jsonwebtoken.*;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

public class JwtTools {

    private static final String SECRETID = "WNW!@$!*@(sajdaslk*!@*(!#(!54794564";

    private static final String SUBJECT = "WNW";

    //过期时间
    private static int NormalExpiretime = 3;
    private static int Refreshexpiretime = 30;

    public static String getToken(Map<String,Object> claims){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,NormalExpiretime);

        JwtBuilder builder = Jwts.builder();
        JwtBuilder jwtBuilder = builder.setHeaderParam("alg", "HAS256")
                .setHeaderParam("typ", "jwt")
                .setExpiration(calendar.getTime())
                .setSubject(SUBJECT)
                .signWith(SignatureAlgorithm.HS256,SECRETID)
                .setId(UUID.randomUUID().toString());
        claims.forEach((k,v) ->{
            jwtBuilder.claim(k,v);
        });
        return jwtBuilder.compact();
    }

    public static String getRefreshToken(Map<String,Object> claims){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,Refreshexpiretime);

        JwtBuilder builder = Jwts.builder();
        JwtBuilder jwtBuilder = builder.setHeaderParam("alg", "HAS256")
                .setHeaderParam("typ", "jwt")
                .setExpiration(calendar.getTime())
                .setSubject(SUBJECT)
                .signWith(SignatureAlgorithm.HS256,SECRETID)
                .setId(UUID.randomUUID().toString());
        claims.forEach((k,v) ->{
            jwtBuilder.claim(k,v);
        });
        return jwtBuilder.compact();
    }

    public static boolean verifyToken(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRETID);
        try {
            parser.parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static Claims verifyTokenWithClaims(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRETID);
        try {
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            return null;
        }
    }


}
