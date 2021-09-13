create table user_data
(
    id       bigserial primary key,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    active   boolean default true
);

create table role_data
(
    id   serial primary key,
    name varchar(50) not null
);

CREATE TABLE user_role
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references user_data (id),
    foreign key (role_id) references role_data (id)
);
insert into role_data (name)
values ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

insert into user_data (username, password, email)
values ('admin', 'admin', 'user@gmail.com');

insert into user_role (user_id, role_id)
values (1, 1),
       (1, 2);

create
extension if not exists pgcrypto;

update user_data
set password = crypt(password, gen_salt('bf', 8));