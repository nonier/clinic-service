--liquibase formatted sql

--changeset dilyin:1
INSERT INTO role(name)
VALUES ('ADMIN'),
       ('USER');
--rollback DELETE FROM roles;

--changeset dilyin:2
INSERT INTO users(id, username, password, name, surname)
VALUES (1, 'dummyAdmin', '$2a$10$GRo6dSssomqOnxzHvgA6ievYOKm4Jszgo0e2.DpjXMbLRXp7NXQyO', 'Admin', 'Admin'),
       (2, 'dummyUser', '$2a$10$1ifxJCBfgg/DyCZCI0ZN3uv3yJ2Knn2SD.I8QTiR9pGyUCIACHsYe', 'user', 'user');
--rollback DELETE FROM users;

--changeset dilyin:3
INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);
--rollback DELETE FROM user_roles;

--changeset dilyin:4
INSERT INTO doctor(id, age_group, work_experience, rank, user_id)
VALUES (1, 'ALL', 25, 'EXPERT', 1);
--rollback DELETE FROM users;