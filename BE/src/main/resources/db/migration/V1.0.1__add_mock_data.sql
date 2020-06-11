INSERT INTO account(email, account_id, password, nickname)
VALUES ('han@gmail.com', 'han1234', '1234', 'han'),
       ('zello@gmail.com', 'zello1234', '1234', 'zello'),
       ('sunny@gmail.com', 'sunny1234', '1234', 'sunny'),
       ('baekco@gmail.com', 'baekco1234', '1234', 'baekco');

INSERT INTO issue(title, contents, account_id)
VALUES ('첫번째 이슈입니다', '이슈1', 1),
       ('두번째 이슈입니다', '이슈2', 2),
       ('세번째 이슈입니다', '이슈3', 3),
       ('네번째 이슈입니다', '이슈4', 4);
