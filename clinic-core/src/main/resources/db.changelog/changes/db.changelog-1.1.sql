--liquibase formatted sql

--changeset dilyin:1
INSERT INTO role(name)
VALUES ('ADMIN'),
       ('USER');
--rollback DELETE FROM roles;

--changeset dilyin:2
INSERT INTO users(username, password)
VALUES ('dummyAdmin', '{bcrypt}$2a$12$JTSPcbcFwDAFPet/ws1DgetDzp9hxQUn7ux/oPRgUHLWQLmx3kWhi');
--rollback DELETE FROM users;

--changeset dilyin:3
INSERT INTO user_roles(user_id, role_id)
VALUES (1,1);
--rollback DELETE FROM user_roles;