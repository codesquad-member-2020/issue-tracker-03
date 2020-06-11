package com.codesquad.issue.domain;

import static java.time.LocalDateTime.now;

import com.codesquad.issue.Account;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotNull(message = "제목은 꼭 입력해주세요")
  private String title;

  private String contents;

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
}
