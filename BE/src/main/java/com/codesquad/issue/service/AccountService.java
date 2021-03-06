package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.*;
import com.codesquad.issue.domain.account.request.AccountSaveDto;
import com.codesquad.issue.domain.account.response.AccountResponse;
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

    public AccountResponse login(GithubAccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String token = oAuthLoginService.makeAccessToken(accessToken);
        GithubAccount request = oAuthLoginService.getAccountByToken(token, headers);
        Account account;

        try {
            account = accountRepository.findByEmail(request.getEmail())
                    .orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            account = save(AccountSaveDto.builder()
                    .email(request.getEmail())
                    .login(request.getLogin())
                    .name(request.getName())
                    .avatarUrl(request.getAvatarUrl())
                    .build());
        }
        return new AccountResponse(account.getName(), account.getAvatarUrl());
    }

    @Transactional
    public Account save(AccountSaveDto accountSaveDto) {
        return accountRepository.save(accountSaveDto.toEntity());
    }

    public Account findByUserId(String login) {
        return accountRepository.findByLogin(login)
                .orElseThrow(UserNotFoundException::new);
    }
}
