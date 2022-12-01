--liquibase formatted sql

--changeset dilyin:1
CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(128) UNIQUE NOT NULL,
  password VARCHAR(128) NOT NULL,
  name VARCHAR(128),
  surname VARCHAR(128)
);
--rollback DROP TABLE users;

--changeset dilyin:2
CREATE TABLE doctor(
    id BIGSERIAL PRIMARY KEY,
    age_group VARCHAR(128) NOT NULL,
    work_experience INTEGER,
    rank VARCHAR(128) NOT NULL,
    user_id BIGINT REFERENCES users(id)
);
--rollback DROP TABLE doctor;

--changeset dilyin:3
CREATE TABLE role(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);
--rollback DROP TABLE role;

--changeset dilyin:4
CREATE TABLE user_roles(
    user_id BIGINT REFERENCES users(id),
    role_id BIGINT REFERENCES role(id),
    PRIMARY KEY (user_id, role_id)
);
--rollback DROP TABLE user_roles;

--changeset dilyin:5
CREATE TABLE specialization(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) UNIQUE NOT NULL
);
--rollback DROP TABLE specialization;

--changeset dilyin:6
CREATE TABLE doctor_specialization(
    doctor_id BIGINT REFERENCES doctor(id),
    specialization_id BIGINT REFERENCES specialization(id),
    PRIMARY KEY (doctor_id, specialization_id)
);
--rollback DROP TABLE doctor_specialization;