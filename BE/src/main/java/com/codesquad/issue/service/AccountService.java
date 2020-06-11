package com.codesquad.issue.service;

import com.codesquad.issue.domain.account.*;
import com.codesquad.issue.domain.github.GithubAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final OAuthLoginService oAuthLoginService;

    public AccountResponse userLogin(GithubAccessToken accessToken) {
        HttpHeaders headers = new HttpHeaders();
        String token = oAuthLoginService.makeAccessToken(accessToken);
        AccountApiRequest request = oAuthLoginService.getUserByToken(token, headers);

        if (findAccountByEmail(request) == null) {
            saveAccount(AccountSaveDto.builder()
                    .email(request.getEmail())
                    .login(request.getLogin())
                    .name(request.getName())
                    .avatarUrl(request.getAvatarUrl())
                    .build());
            return new AccountResponse(request.getEmail());
        }
        return new AccountResponse(findAccountByEmail(request).getEmail());
    }

    @Transactional
    public void saveAccount(AccountSaveDto accountSaveDto) {
        accountRepository.save(accountSaveDto.toEntity());
    }

    private Account findAccountByEmail(AccountApiRequest request) {
        return accountRepository.findByEmail(request.getEmail());
    }
}
