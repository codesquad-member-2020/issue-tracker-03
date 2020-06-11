DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS issue;

CREATE TABLE account
(
    id         BIGINT AUTO_INCREMENT,
    email      VARCHAR(50),
    account_id VARCHAR(50),
    nickname   VARCHAR(50),
    password   VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE issue
(
    id              BIGINT AUTO_INCREMENT,
    title           VARCHAR(50),
    contents        VARCHAR(500),
    is_open         BOOLEAN   DEFAULT TRUE,
    created_time_at TIMESTAMP DEFAULT NOW(),
    account_id      BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT issue_has_account_id FOREIGN KEY (account_id) REFERENCES account (id)
)
