package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.Account;
import com.codesquad.issue.domain.account.response.AccountResponse;
import com.codesquad.issue.domain.commmon.BaseTimeEntity;
import com.codesquad.issue.domain.issue.request.IssueModifyRequest;
import com.codesquad.issue.domain.issue.response.IssueResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "issue", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<IssueLabel> issueLabels = new HashSet<>();

    @Builder
    private Issue(String title, Account author) {
        this.title = title;
        this.isOpen = true;
        this.author = author;
    }

    public void changeOpenOrClose() {
        this.isOpen = !isOpen;
    }

    public void modifyTitle(IssueModifyRequest request) {
        this.title = request.getTitle();
    }

    public IssueResponse toResponse() {
        return IssueResponse.builder()
                .id(id)
                .title(title)
                .isOpen(isOpen)
                .author(AccountResponse.builder()
                        .userId(author.getLogin())
                        .avatarUrl(author.getAvatarUrl())
                        .build())
                .labels(issueLabels.stream().map(
                        issueLabel -> issueLabel.toLabelResponse())
                        .collect(Collectors.toList()))
                .createdTimeAt(getCreatedTimeAt())
                .modifiedTimeAt(getModifiedTimeAt())
                .build();
    }
}
