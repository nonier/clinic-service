--liquibase formatted sql

--changeset dilyin:1
CREATE TABLE dialog(
    id BIGSERIAL PRIMARY KEY
);
--rollback DROP TABLE dialog;

--changeset dilyin:2
CREATE TABLE message(
    id BIGSERIAL PRIMARY KEY,
    dialog_id BIGINT REFERENCES dialog(id),
    text VARCHAR(1024),
    user_from BIGINT REFERENCES users(id)
)
--rollback DROP TABLE message;

--changeset dilyin:3
CREATE TABLE user_dialog(
    user_id BIGINT REFERENCES users(id),
    dialog_id BIGINT REFERENCES dialog(id),
    PRIMARY KEY (user_id, dialog_id)
);
--rollback DROP TABLE user_dialog;