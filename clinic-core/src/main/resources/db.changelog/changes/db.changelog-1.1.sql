--liquibase formatted sql

--changeset dilyin:1
INSERT INTO role(name)
VALUES ('ADMIN'),
       ('CLIENT'),
       ('DOCTOR');
--rollback DELETE FROM roles;

--changeset dilyin:2
INSERT INTO users(username, password, name, surname)
VALUES ('dummyAdmin', '$2a$10$jYO7hCsoTx/JhH0o6/T58.POI5B.kh7gJzT7kozOh4gOopOhaJ1bC', 'AdminName', 'AdminSurname'),
       ('dummyClient', '$2y$10$wOdXMeS1RUfncPKU9hEwd.ueeLlSJ5h6VXmvM9c0Re71enfFgTbiK', 'ClientName', 'ClientSurname'),
       ('dummyDoctor', '$2y$10$RdhG6E0o9fT29V7Slgc0geW1KlpT47r8hlSyOj895DiZyh1jW5t8q', 'DoctorName', 'DoctorSurname');
--rollback DELETE FROM users;

--changeset dilyin:3
INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
--rollback DELETE FROM user_roles;

--changeset dilyin:4
INSERT INTO doctor(age_group, work_experience, rank, user_id)
VALUES ('ALL', 25, 'EXPERT', 3);
--rollback DELETE FROM users;