--liquibase formatted sql
--changeSet dev_0001:002-02

INSERT INTO roles (role)
values ('SALE');

INSERT INTO roles (role)
values ('CUSTOMER');

INSERT INTO roles (role)
values ('SECURE_API');

INSERT INTO roles (role)
values ('ADMINISTRATOR');