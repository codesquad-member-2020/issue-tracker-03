package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    private String name;

    private String avatarUrl;

    @Builder
    private Account(String email, String login, String name, String avatarUrl) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }
}
