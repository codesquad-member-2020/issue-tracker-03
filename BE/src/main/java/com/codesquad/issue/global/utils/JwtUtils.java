package com.codesquad.issue.global.utils;

import com.codesquad.issue.domain.account.response.AccountResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String JWT_KEY_USER_ID = "userId";
    private static final String JWT_KEY_AVATAR_URL = "avatarURL";
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwtUtils() {
    }

    public static String jwtCreate(AccountResponse account) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put(JWT_KEY_USER_ID, account.getUserId());
        payloads.put(JWT_KEY_AVATAR_URL, account.getAvatarUrl());

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(key)
                .compact();
    }

    public static AccountResponse jwtParsing(String jwt) {

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            return AccountResponse.builder()
                    .userId(jws.getBody().get(JWT_KEY_USER_ID, String.class))
                    .avatarUrl(jws.getBody().get(JWT_KEY_AVATAR_URL, String.class))
                    .build();

        } catch (JwtException e) {
            throw new IllegalStateException("인증되지 않은 JWT TOKEN입니다.");
        }
    }
}
