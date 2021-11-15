package com.company.util;

import com.company.dto.UserDetails;
import com.google.gson.Gson;
import io.jsonwebtoken.*;

import java.util.Date;

public class TokenProcess {

    private final static String secretKey = "kalit";

    public static String generateJwt(UserDetails userDetails) {

        JwtBuilder jwtBuilder = Jwts.builder();
//        jwtBuilder.setId("some Id");
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(new Gson().toJson(userDetails));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)));
//        jwtBuilder.setIssuer("doska.novosti");

        String jwt = jwtBuilder.compact();
        return jwt;
    }


    public static UserDetails encodeJwtJson(String jwt) {
        JwtParser jwtParser = Jwts.parser();

        jwtParser.setSigningKey(secretKey);
        Jws jws = jwtParser.parseClaimsJws(jwt);

        Claims claims = (Claims) jws.getBody();
        String json = claims.getSubject(); // GET ID

        return new Gson().fromJson(json, UserDetails.class);
    }

}
