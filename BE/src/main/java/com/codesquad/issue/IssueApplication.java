package com.codesquad.issue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IssueApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueApplication.class, args);
    }

}
