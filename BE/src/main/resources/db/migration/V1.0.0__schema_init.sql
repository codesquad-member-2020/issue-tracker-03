DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS issue;

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT,
    email    VARCHAR(50),
    user_id  VARCHAR(50),
    nickname VARCHAR(50),
    password VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE issue
(
    id              BIGINT AUTO_INCREMENT,
    title           VARCHAR(50),
    contents        VARCHAR(500),
    is_open         BOOLEAN   DEFAULT TRUE,
    created_time_at TIMESTAMP DEFAULT NOW(),
    user_id         BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT issue_has_user_id FOREIGN KEY (user_id) REFERENCES user (id)
)
