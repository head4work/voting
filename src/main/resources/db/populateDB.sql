delete
from USER_ROLES;

delete
from USERS;


ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (USER_ID, ROLE)
VALUES (100000, 'USER'),
       (100001, 'ADMIN');

