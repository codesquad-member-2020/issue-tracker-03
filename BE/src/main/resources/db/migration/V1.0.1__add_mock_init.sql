INSERT INTO account(email, login, name, avatar_url)
VALUES ('sunny@gmail.com', 'sunny1234', 'sunny', 'url');

INSERT INTO issue(title, account_id)
VALUES ('첫번째 이슈입니다', 1),
       ('두번째 이슈입니다', 1),
       ('세번째 이슈입니다', 1),
       ('네번째 이슈입니다', 1);

INSERT INTO issue(title, account_id, is_open)
VALUES ('첫번째 닫힌 이슈입니다', 1, false),
       ('두번째 닫힌 이슈입니다', 1, false),
       ('세번째 닫힌 이슈입니다', 1, false);

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

INSERT INTO issue_label(issue_id, label_id)
VALUES (1, 1),
       (1, 2);

INSERT INTO milestone (name, description, due_date)
VALUES ('1주차', 'scrum', '2020-06-27'),
       ('2주차', 'coding', '2020-07-01'),
       ('3주차', 'deploy', '2020-07-31');
