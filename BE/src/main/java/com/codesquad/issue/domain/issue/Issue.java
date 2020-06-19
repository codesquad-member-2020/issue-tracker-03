package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.commmon.BaseTimeEntity;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Issue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account author;

    @Builder
    private Issue(String title, String contents, Account author) {
        this.title = title;
        this.isOpen = true;
        this.author = author;
    }

    public void changeIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void modifyTitle(IssueModifyRequest request) {
        this.title = request.getTitle();
    }
}
