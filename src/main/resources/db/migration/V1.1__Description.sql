SET TIME ZONE 'UTC';
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table app_user
(
    user_id uuid default uuid_generate_v1() not null
        constraint app_user_pk
            primary key,
    name varchar(150) not null,
    email varchar(255) not null
);


create unique index app_user_user_id_uindex
    on  app_user (user_id);

create unique index app_user_email_uindex
    on  app_user (email);

