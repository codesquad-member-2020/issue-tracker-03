DROP TABLE IF EXISTS account;

CREATE TABLE account
(
    id         BIGINT AUTO_INCREMENT,
    email      VARCHAR(50) UNIQUE NOT NULL,
    name       VARCHAR(50) UNIQUE NOT NULL,
    nickname   VARCHAR(255),
    avatar_url VARCHAR(255),
    PRIMARY KEY (id)
);
