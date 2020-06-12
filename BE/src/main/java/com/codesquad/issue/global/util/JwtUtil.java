package com.codesquad.issue.global.util;

import com.codesquad.issue.domain.account.AccountResponse;
import com.codesquad.issue.global.error.exception.LoginRequiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtUtil {

  private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private static final String JWT_KEY = "user";
  private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

  public static String createJws(String jwtKey, Object data) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");
    headers.put("alg", "HS256");

    Map<String, Object> payloads = new HashMap<>();
    payloads.put(jwtKey, data);

    return Jwts.builder()
        .setHeader(headers)
        .setClaims(payloads)
        .signWith(key)
        .compact();
  }

  public static String createJwt(AccountResponse data) {
    Map<String, Object> headers = new HashMap<>();
    headers.put("typ", "JWT");
    headers.put("alg", "HS256");

    Map<String, AccountResponse> payloads = new HashMap<>();
    payloads.put(JWT_KEY, data);

    return Jwts.builder()
        .setHeader(headers)
        .setClaims(payloads)
        .signWith(key)
        .compact();
  }

  public static AccountResponse getAccountFromJws(String jws) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(jws);
      return AccountResponse.of((LinkedHashMap) claims.getBody().get(JWT_KEY));
    } catch (JwtException ex) {
      log.error("인증되지 않은 jwt token입니다. jws: {}", jws);
      throw new LoginRequiredException("인증되지 않은 jwt token입니다.");
    }
  }
}
