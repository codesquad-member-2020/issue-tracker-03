DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT,
    email      VARCHAR(50) UNIQUE NOT NULL,
    name       VARCHAR(50) UNIQUE NOT NULL,
    nickname   VARCHAR(50),
    avatar_url VARCHAR(255),
    PRIMARY KEY (id)
);
