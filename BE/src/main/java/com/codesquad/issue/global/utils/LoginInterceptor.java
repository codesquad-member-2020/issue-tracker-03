package com.codesquad.issue.global.utils;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.global.error.exception.UnAuthorizedException;
import com.codesquad.issue.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        log.info("### preHandler");

        if (request.getMethod().equals("OPTIONS") || request.getMethod().equals("GET")) {
            log.info("### method: {}", request.getMethod());
            return true;
        }

        try {
            Cookie cookie = WebUtils.getCookie(request, "jwt");
            if (cookie == null) {
                throw new UnAuthorizedException("쿠키가 없습니다.");
            }

            String cookieUserId = cookie.getValue();
            log.info("##### cookie : {}", cookieUserId);

            String jwtUserId = JwtUtils.jwtParsing(cookieUserId);
            accountService.findByUserId(jwtUserId);
            request.setAttribute("jwt", jwtUserId);
            return true;
        } catch (UnAuthorizedException e) {
            response.setStatus(401);
            return false;
        }
    }
}
