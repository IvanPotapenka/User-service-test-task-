--liquibase formatted sql
--changeSet dev_0001:002-01

CREATE TABLE roles
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    role   VARCHAR(15) UNIQUE NOT NULL
);

-- rollback DROP TABLE roles;