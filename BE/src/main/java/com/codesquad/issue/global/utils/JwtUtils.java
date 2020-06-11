package com.codesquad.issue.global.utils;

import com.codesquad.issue.domain.account.AccountResponse;
import com.codesquad.issue.domain.account.AccountSaveDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String JWT_KEY = "user";
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwtUtils() {}

    public static String jwtCreate(AccountResponse account) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", account.getEmail());

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(key)
                .compact();
    }

    public static String jwtParsing(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
            return jws.getBody().get("email", String.class);
        } catch (JwtException e) {
            throw new IllegalStateException("인증되지 않은 JWT TOKEN입니다.");
        }

    }
}
