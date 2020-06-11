package com.codesquad.issue.domain.account;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    private String nickname;

    private String avatarUrl;

    @Builder
    public Account(long id, String email, String name, String nickname, String avatarUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }
}
