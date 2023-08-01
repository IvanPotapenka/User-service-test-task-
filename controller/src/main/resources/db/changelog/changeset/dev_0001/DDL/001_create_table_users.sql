--liquibase formatted sql
--changeSet dev_0001:001-01

CREATE TABLE users
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name  VARCHAR(20) NOT NULL,
    sur_name    VARCHAR(40) NOT NULL,
    middle_name VARCHAR(40) NOT NULL,
    email       VARCHAR(50) NOT NULL UNIQUE
);

-- rollback DROP TABLE users;