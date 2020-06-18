package com.codesquad.issue.domain.comment;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.commmon.BaseTimeEntity;
import com.codesquad.issue.domain.issue.Issue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Builder
    private Comment(String contents, Account author, Issue issue) {
        this.contents = contents;
        this.author = author;
        this.issue = issue;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }
}
