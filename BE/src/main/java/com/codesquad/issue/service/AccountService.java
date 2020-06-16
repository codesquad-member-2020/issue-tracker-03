package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.*;
import com.codesquad.issue.global.error.exception.UnAuthorizedException;
import com.codesquad.issue.global.error.exception.UserNotFoundException;
import com.codesquad.issue.global.github.GithubAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final OAuthLoginService oAuthLoginService;

    public AccountResponse userLogin(GithubAccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String token = oAuthLoginService.makeAccessToken(accessToken);
        GithubAccount request = oAuthLoginService.getAccountByToken(token, headers);

        Account findAccount = accountRepository.findByEmail(request.getEmail())
                .orElseGet(() -> saveAccount(AccountSaveDto.builder()
                        .email(request.getEmail())
                        .login(request.getLogin())
                        .name(request.getName())
                        .avatarUrl(request.getAvatarUrl())
                        .build()));
        return new AccountResponse(findAccount.getName(), findAccount.getAvatarUrl());
    }

    @Transactional
    public Account saveAccount(AccountSaveDto accountSaveDto) {
        return accountRepository.save(accountSaveDto.toEntity());
    }

    @Transactional
    public Account findByUserId(String userId) {
        return accountRepository.findByLogin(userId).orElseThrow(UserNotFoundException::new);
    }
}
