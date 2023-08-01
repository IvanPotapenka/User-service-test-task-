--liquibase formatted sql
--changeSet dev_0001:003-01

CREATE TABLE user_role
(
    user_id BIGINT      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    role_id BIGINT      NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- rollback DROP TABLE user_role;