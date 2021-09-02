create
sequence hibernate_sequence start 100 increment 1;

CREATE TABLE IF NOT EXISTS product
(
    id       bigserial primary key not null,
    title    text                  not null,
    cost     float               not null,
    img_link text
);

CREATE TABLE IF NOT EXISTS category
(
    id        bigserial primary key not null,
    title     text                  not null,
    alias     text                  not null,
    parent_id int,
    foreign key (parent_id) references category (id)
);

create index category_parent_id_idx on category (parent_id);


CREATE TABLE IF NOT EXISTS product_category
(
    product_id  bigint not null,
    category_id bigint not null,

    primary key (product_id, category_id),
    foreign key (product_id) references product (id),
    foreign key (category_id) references category (id)
)