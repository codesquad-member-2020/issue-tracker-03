package com.codesquad.issue.domain.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void 유저불러오기() {
        //given
        String email = "kses1010@naver.com";
        String login = "ssh";
        String name = "haha";
        String avatarUrl = "avatar";

        accountRepository.save(Account.builder()
                .email(email)
                .name(name)
                .login(login)
                .avatarUrl(avatarUrl)
                .build());

        //when
        List<Account> accounts = accountRepository.findAll();
        Account accountByEmail = accountRepository.findByEmail("kses1010@naver.com").orElseThrow(()
                -> new IllegalStateException("해당유저는 없습니다."));
        Account accountByUserId = accountRepository.findByLogin("ssh").orElseThrow(()
                -> new IllegalStateException("해당유저는 없습니다."));
        //then
        Account account = accounts.get(0);
        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(accountByEmail.getEmail()).isEqualTo(email);
        assertThat(accountByUserId.getLogin()).isEqualTo(login);
    }
}
