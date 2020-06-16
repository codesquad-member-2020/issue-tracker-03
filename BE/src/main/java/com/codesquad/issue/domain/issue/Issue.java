package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@NoArgsConstructor
@Getter
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String contents;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    private LocalDateTime createdTimeAt;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account createdBy;

    @Builder
    private Issue(String title, String contents, Account created) {
        this.title = title;
        this.contents = contents;
        this.isOpen = true;
        this.createdTimeAt = now();
        this.createdBy = created;
    }

    public void changeIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
