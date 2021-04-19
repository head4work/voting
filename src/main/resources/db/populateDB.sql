delete
from USER_ROLES;

delete
from USERS;

delete
from RESTAURANTS;


ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (USER_ID, ROLE)
VALUES (100000, 'USER'),
       (100001, 'ADMIN');

INSERT INTO RESTAURANTS (NAME)
VALUES ('name1'),
       ('name2');

INSERT INTO DISHES (RESTAURANT_ID, NAME, PRICE)
VALUES (100002, 'soup', 500),
       (100003, 'rice', 500),
       (100003, 'chicken', 700),
       (100003, 'spaghetti', 300);
