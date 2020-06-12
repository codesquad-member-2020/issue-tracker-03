package com.codesquad.issue.domain.account;

import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountResponse {

  private final String id;

  private final String avatarUrl;

  public static AccountResponse of(Map<String, String> accountMap) {
    return new AccountResponse(accountMap);
  }

  public static AccountResponse of(Account account) {
    return new AccountResponse(account);
  }

  private AccountResponse(Map<String, String> accountMap) {
    this.id = accountMap.get("id");
    this.avatarUrl = accountMap.get("avatarUrl");
  }

  private AccountResponse(Account account) {
    this.id = account.getLogin();
    this.avatarUrl = account.getAvatarUrl();
  }
}
