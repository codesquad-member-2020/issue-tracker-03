package com.codesquad.issue.service;

import com.codesquad.issue.domain.github.GithubAccessToken;
import com.codesquad.issue.domain.account.AccountApiRequest;
import com.codesquad.issue.domain.account.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final OAuthLoginService oAuthLoginService;

    public AccountResponse userLogin(GithubAccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String token = oAuthLoginService.makeAccessToken(accessToken);
        AccountApiRequest request = oAuthLoginService.getUserByToken(token, headers);

        return AccountResponse.builder()
                .email(request.getEmail())
                .userName(request.getLogin())
                .avatarUrl(request.getAvatarUrl())
                .nickname(request.getName())
                .build();
    }
}
