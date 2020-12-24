SET TIME ZONE 'UTC';
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE app_user
(
    user_id CHAR(36) DEFAULT uuid_generate_v1() NOT NULL UNIQUE
        CONSTRAINT app_user_primary_key
            PRIMARY KEY,
    name    VARCHAR(150)                        NOT NULL,
    email   VARCHAR(255)                        NOT NULL UNIQUE
);

CREATE TABLE post
(
    post_id     CHAR(36) DEFAULT uuid_generate_v1() NOT NULL UNIQUE
        CONSTRAINT post_primary_key PRIMARY KEY,
    content     TEXT,
    app_user_id CHAR(36)                            NOT NULL
);

ALTER TABLE post
    ADD CONSTRAINT post_app_user_id_fk FOREIGN KEY (app_user_id) REFERENCES app_user (user_id);

INSERT INTO app_user (name, email)
VALUES ('Elias Meireles', 'elias@email.com');

CREATE TABLE post_images
(
    post_image_id     CHAR(36) DEFAULT uuid_generate_v1() NOT NULL UNIQUE
        CONSTRAINT post_image_primary_key PRIMARY KEY,
    post_id   CHAR(36)     NOT NULL,
    image_url VARCHAR(355) NOT NULL
);

ALTER TABLE post_images
    ADD CONSTRAINT post_images_post_id_fk FOREIGN KEY (post_id) REFERENCES post (post_id);