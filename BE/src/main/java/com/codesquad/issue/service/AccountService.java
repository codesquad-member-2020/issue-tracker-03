package com.codesquad.issue.service;

import static com.codesquad.issue.global.error.exception.ErrorCode.ACCOUNT_NOT_FOUND;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.AccountRepository;
import com.codesquad.issue.domain.account.GithubAccount;
import com.codesquad.issue.domain.account.request.AccountSaveDto;
import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.global.error.exception.NotFoundException;
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
            account = accountRepository.findByEmail(request.getEmail()).orElseThrow(
                    () -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage(request.getEmail())));
        } catch (NotFoundException e) {
            log.debug(request.getEmail() + "에 해당하는 계정을 찾을 수 없어 새로 저장합니다.");
            account = save(AccountSaveDto.builder()
                    .email(request.getEmail())
                    .login(request.getLogin())
                    .name(request.getName())
                    .avatarUrl(request.getAvatarUrl())
                    .build());
        }
        return new AccountResponse(account.getLogin(), account.getAvatarUrl());
    }

    @Transactional
    public Account save(AccountSaveDto accountSaveDto) {
        return accountRepository.save(accountSaveDto.toEntity());
    }

    public Account findByUserId(String login) {
        return accountRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException(ACCOUNT_NOT_FOUND.getMessage(login)));
    }

    public boolean isUserExist(AccountResponse accountResponse) {
        return accountRepository.findByLogin(accountResponse.getUserId()).isPresent();
    }
}
