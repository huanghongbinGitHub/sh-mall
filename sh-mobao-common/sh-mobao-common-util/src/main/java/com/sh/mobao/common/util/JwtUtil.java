package com.sh.mobao.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {

    private static final  String secret = "adsdsfdsfs";

    public static String createToken(String subject){
        String token = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 360))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
        return token;
    }

    public static String parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return body.getSubject();
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "马老师";
        String token = createToken(name);
        System.out.println("token === "+token);

        String parseToken = parseToken(token);
        System.out.println("解析出来 ==== "+parseToken);

        TimeUnit.SECONDS.sleep(4);
        parseToken = parseToken(token);
        System.out.println("再解析出来 ==== "+parseToken);
    }

}
