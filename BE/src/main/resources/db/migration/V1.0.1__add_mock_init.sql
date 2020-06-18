INSERT INTO account(email, login, name, avatar_url)
VALUES ('sunny@gmail.com', 'sunny1234', 'sunny', 'url');

INSERT INTO issue(title, contents, account_id)
VALUES ('첫번째 이슈입니다', '이슈1', 1),
       ('두번째 이슈입니다', '이슈2', 1),
       ('세번째 이슈입니다', '이슈3', 1),
       ('네번째 이슈입니다', '이슈4', 1);

INSERT INTO issue(title, contents, account_id, is_open)
VALUES ('첫번째 닫힌 이슈입니다', '이슈1', 1, false),
       ('두번째 닫힌 이슈입니다', '이슈2', 1, false),
       ('세번째 닫힌 이슈입니다', '이슈3', 1, false);

INSERT INTO label(name, description, color)
VALUES ('bug', 'Something isn''t working', '#d73a4a'),
       ('documentation', 'Improvements or additions to documentation', '#0075ca'),
       ('question', 'Further information is requested', '#d876e3'),
       ('invalid', 'This doesn''t seem right', '#e4e669'),
       ('help wanted', 'Extra attention is needed', '#008672');

INSERT INTO comment(contents, issue_id, account_id)
VALUES ('댓글1', 1, 1),
       ('댓글2', 1, 1),
       ('댓글3', 1, 1);
