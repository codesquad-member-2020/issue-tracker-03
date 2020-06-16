package com.codesquad.issue.domain.issue;

import com.codesquad.issue.domain.account.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IssueSaveRequestDto {
    private final String title;
    private final String contents;
    private final Account author;

    @Builder
    public IssueSaveRequestDto(String title, String contents, Account author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public Issue toEntity() {
        return Issue.builder()
                .title(title)
                .contents(contents)
                .author(author)
                .build();
    }
}
