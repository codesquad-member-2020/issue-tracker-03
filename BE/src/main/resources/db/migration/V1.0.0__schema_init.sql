DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS issue;
DROP TABLE IF EXISTS label;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS issue_label;
DROP TABLE IF EXISTS milestone;
DROP TABLE IF EXISTS issue_milestone;

CREATE TABLE account
(
    id         BIGINT AUTO_INCREMENT,
    email      VARCHAR(50) UNIQUE NOT NULL,
    login      VARCHAR(50) UNIQUE NOT NULL,
    name       VARCHAR(255),
    avatar_url VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE issue
(
    id               BIGINT AUTO_INCREMENT,
    title            VARCHAR(50),
    is_open          BOOLEAN   DEFAULT TRUE,
    created_time_at  TIMESTAMP DEFAULT NOW(),
    modified_time_at TIMESTAMP DEFAULT NOW(),
    account_id       BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT issue_has_account_id FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE label
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(500),
    color       VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE comment
(
    id               BIGINT AUTO_INCREMENT,
    contents         VARCHAR(500),
    created_time_at  TIMESTAMP DEFAULT NOW(),
    modified_time_at TIMESTAMP DEFAULT NOW(),
    issue_id         BIGINT,
    account_id       BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT comment_has_account_id FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT comment_has_issue_id FOREIGN KEY (issue_id) REFERENCES issue (id)
);

CREATE TABLE issue_label
(
    id       BIGINT AUTO_INCREMENT,
    issue_id BIGINT,
    label_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT issue_label_has_issue_id FOREIGN KEY (issue_id) REFERENCES issue (id),
    CONSTRAINT issue_label_has_label_id FOREIGN KEY (label_id) REFERENCES label (id)
);

CREATE TABLE milestone
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(500) NOT NULL,
    description VARCHAR(500),
    due_date    Date,
    issue_id    BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT milestone_has_issue_id FOREIGN KEY (issue_id) REFERENCES issue (id)
);

CREATE TABLE issue_milestone
(
    id           BIGINT AUTO_INCREMENT,
    issue_id     BIGINT,
    milestone_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT issue_milestone_has_issue_id FOREIGN KEY (issue_id) REFERENCES issue (id),
    CONSTRAINT issue_milestone_has_milestone_id FOREIGN KEY (milestone_id) REFERENCES milestone (id)
);
