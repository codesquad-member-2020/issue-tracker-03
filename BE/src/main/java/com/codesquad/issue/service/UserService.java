package com.codesquad.issue.service;

import com.codesquad.issue.domain.github.GithubAccessToken;
import com.codesquad.issue.domain.user.UserApiRequest;
import com.codesquad.issue.domain.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final OAuthLoginService oAuthLoginService;

    public UserResponse userLogin(GithubAccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String token = oAuthLoginService.makeAccessToken(accessToken);
        UserApiRequest request = oAuthLoginService.getUserByToken(token, headers);
        if (request.getEmail() == null) {
            request.setEmail(oAuthLoginService.getEmailByToken(headers));
        }

        return UserResponse.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .build();
    }
}
