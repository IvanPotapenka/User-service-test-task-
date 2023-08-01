DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name  VARCHAR(20) NOT NULL,
    sur_name    VARCHAR(40) NOT NULL,
    middle_name VARCHAR(40) NOT NULL,
    email       VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE roles
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id, role_id)
);



INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Ivan', 'Ivanov', 'Ivanovich', 'ivano@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Kira', 'Potapenko', 'Aleksandrovna', 'kirap@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Kirill', 'Ivanov', 'Ivanovich', 'kirill@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Artem', 'Petrov', 'Petrovich', 'artem@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Ilia', 'Victorov', 'Anatolievich', 'ilia@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Igor', 'Petrov', 'Aleksandrovich', 'igor@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Ivan', 'Ivanov', 'Ivanovich', 'aric@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Kira', 'Potapenko', 'Aleksandrovna', 'maks@mail.ru');
INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Ivan', 'Ivanov', 'Ivanovich', 'ivan@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Kira', 'Potapenko', 'Aleksandrovna', 'ulov@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Ivan', 'Ivanov', 'Ivanovich', 'dima@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Kira', 'Potapenko', 'Aleksandrovna', 'nikita@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Ivan', 'Ivanov', 'Ivanovich', 'olga@mail.ru');

INSERT INTO users (first_name, sur_name, middle_name, email)
values ('Kira', 'Potapenko', 'Aleksandrovna', 'nate@mail.ru');


INSERT INTO roles (role)
values ('SALE');

INSERT INTO roles (role)
values ('CUSTOMER');

INSERT INTO roles (role)
values ('SECURE_API');

INSERT INTO roles (role)
values ('ADMINISTRATOR');

INSERT INTO user_role (user_id, role_id)
values (1, 1);
INSERT INTO user_role (user_id, role_id)
values (1, 2);
INSERT INTO user_role (user_id, role_id)
values (2, 1);
INSERT INTO user_role (user_id, role_id)
values (3, 1);
INSERT INTO user_role (user_id, role_id)
values (4, 1);
INSERT INTO user_role (user_id, role_id)
values (5, 3);
INSERT INTO user_role (user_id, role_id)
values (5, 4);
INSERT INTO user_role (user_id, role_id)
values (6, 1);
INSERT INTO user_role (user_id, role_id)
values (7, 2);
INSERT INTO user_role (user_id, role_id)
values (8, 3);
INSERT INTO user_role (user_id, role_id)
values (9, 1);
INSERT INTO user_role (user_id, role_id)
values (9, 2);
INSERT INTO user_role (user_id, role_id)
values (9, 3);
INSERT INTO user_role (user_id, role_id)
values (9, 4);
INSERT INTO user_role (user_id, role_id)
values (10, 1);
INSERT INTO user_role (user_id, role_id)
values (11, 1);
INSERT INTO user_role (user_id, role_id)
values (12, 1);
INSERT INTO user_role (user_id, role_id)
values (13, 1);
INSERT INTO user_role (user_id, role_id)
values (14, 3);