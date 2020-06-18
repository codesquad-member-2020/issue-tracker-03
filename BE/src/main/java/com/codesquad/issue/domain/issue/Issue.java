package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.commmon.BaseTimeEntity;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

import static java.time.LocalDateTime.now;

@Entity
@NoArgsConstructor
@Getter
public class Issue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String contents;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account author;

    @Builder
    private Issue(String title, String contents, Account author) {
        this.title = title;
        this.contents = contents;
        this.isOpen = true;
        this.author = author;
    }

    public void changeIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void modifyTitleAndContents(IssueModifyRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();
    }
}
