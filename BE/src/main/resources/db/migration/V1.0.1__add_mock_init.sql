INSERT INTO label(name, description, color)
VALUES ('bug', 'Something isn''t working', '#d73a4a'),
       ('documentation', 'Improvements or additions to documentation', '#0075ca'),
       ('duplicate', 'This issue or pull request already exists', '#cfd3d7'),
       ('enhancement', 'New feature or request', '#a2eeef'),
       ('good first issue', 'Good for newcomers', '#7057ff'),
       ('help wanted', 'Extra attention is needed', '#008672'),
       ('invalid', 'This doesn''t seem right', '#e4e669'),
       ('question', 'Further information is requested', '#d876e3'),
       ('wontfix', 'This will not be worked on', '#ffffff');

INSERT INTO milestone (name, description, due_date)
VALUES ('1주차', 'scrum', '2020-06-28'),
       ('2주차', 'coding', '2020-07-05'),
       ('3주차', 'deploy', '2020-07-12');
