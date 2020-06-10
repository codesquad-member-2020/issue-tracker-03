package com.codesquad.issue.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserApiRequest {

    @JsonProperty("login")
    private String userName;

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
}
