SET TIME ZONE 'UTC';
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE app_user
(
    user_id UUID DEFAULT uuid_generate_v1() NOT NULL
        CONSTRAINT app_user_pk
            PRIMARY KEY,
    name    VARCHAR(150)                    NOT NULL,
    email   VARCHAR(255)                    NOT NULL
);

CREATE UNIQUE INDEX app_user_user_id_uindex
    ON app_user (user_id);

CREATE UNIQUE INDEX app_user_email_uindex
    ON app_user (email);

INSERT INTO app_user (name, email)
VALUES ('User 01', 'user1@email.com'),
       ('User 02', 'user2@email.com'),
       ('User 03', 'user3@email.com'),
       ('User 04', 'user4@email.com'),
       ('User 05', 'user5@email.com'),
       ('User 06', 'user6@email.com'),
       ('User 07', 'user07s@email.com')
