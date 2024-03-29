--liquibase formatted sql

--changeset dilyin:1
ALTER TABLE users
    ADD COLUMN birth_date DATE;
--rollback ALTER TABLE users DROP COLUMN birth_date;

--changeset dilyin:2
CREATE TABLE review
(
    id      BIGSERIAL PRIMARY KEY,
    rate    SMALLINT NOT NULL,
    comment VARCHAR(256),
    user_id BIGINT REFERENCES users (id)
);
--rollback DROP TABLE review;

--changeset dilyin:3
CREATE TABLE consultation
(
    id        BIGSERIAL PRIMARY KEY,
    date      TIMESTAMP NOT NULL,
    doctor_id BIGINT REFERENCES doctor (id),
    client_id BIGINT REFERENCES users (id),
    dialog_id BIGINT REFERENCES dialog (id)
);
--rollback DROP TABLE consultation;

--changeset dilyin:4
INSERT INTO consultation(date, doctor_id)
VALUES ('2001-02-16 20:38:40', 1);