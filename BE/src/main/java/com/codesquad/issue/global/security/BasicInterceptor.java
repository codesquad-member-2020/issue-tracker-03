package com.codesquad.issue.global.security;

import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.global.error.exception.UnauthorizedException;
import com.codesquad.issue.global.utils.JwtUtils;
import com.codesquad.issue.service.AccountService;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Slf4j
@RequiredArgsConstructor
@Component
public class BasicInterceptor extends HandlerInterceptorAdapter {

    private final AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) {
        log.debug("HTTP : {}", request.getMethod());
        if (request.getMethod().equals(HttpMethod.POST.name()) ||
                request.getMethod().equals(HttpMethod.PUT.name()) ||
                request.getMethod().equals(HttpMethod.DELETE.name()) ||
                request.getMethod().equals(HttpMethod.PATCH.name())
        ) {
            Cookie[] cookies = request.getCookies();

            if (cookies == null || cookies.length < 1) {
                throw new UnauthorizedException("인증되지 않은 요청입니다.");
            }

            String token = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals("jwt"))
                    .map(Cookie::getValue)
                    .reduce(String::concat)
                    .orElse("");

            AccountResponse accountResponse = JwtUtils.jwtParsing(token);
            log.debug("accountResponse : {}", accountResponse.getUserId());

            return accountService.isUserExist(accountResponse);
        }
        return true;
    }
}
