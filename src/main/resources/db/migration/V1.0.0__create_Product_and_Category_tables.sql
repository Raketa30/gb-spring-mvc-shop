create
sequence hibernate_sequence start 100 increment 1;

CREATE TABLE IF NOT EXISTS product
(
    id       bigserial primary key not null,
    title    text                  not null,
    cost     float                 not null,
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
);

INSERT INTO category(id, title, alias)
VALUES (1, 'test_root', 'test_root_alias');

INSERT INTO category(id, title, alias, parent_id)
VALUES (2, 'test2', 'test_alias2', 1),
       (3, 'test3', 'test_alias3', 1),
       (4, 'test4', 'test_alias4', 3),
       (5, 'test5', 'test_alias5', 3);

INSERT INTO product(id, title, cost, img_link)
VALUES (1, 'test_product1', 0.01, 'upload/images/empty/empty.png'),
       (2, 'test_product2', 0.01, 'upload/images/empty/empty.png'),
       (3, 'test_product3', 0.01, 'upload/images/empty/empty.png'),
       (4, 'test_product4', 0.01, 'upload/images/empty/empty.png'),
       (5, 'test_product5', 0.01, 'upload/images/empty/empty.png'),
       (6, 'test_product6', 0.01, 'upload/images/empty/empty.png'),
       (7, 'test_product7', 0.01, 'upload/images/empty/empty.png'),
       (8, 'test_product8', 0.01, 'upload/images/empty/empty.png'),
       (9, 'test_product9', 0.01, 'upload/images/empty/empty.png'),
       (10, 'test_product10', 0.01, 'upload/images/empty/empty.png'),
       (11, 'test_product11', 0.01, 'upload/images/empty/empty.png'),
       (12, 'test_product12', 0.01, 'upload/images/empty/empty.png');


INSERT INTO product_category(product_id, category_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 2),
       (7, 3),
       (8, 4),
       (9, 5),
       (10, 5),
       (11, 4),
       (12, 2);

alter
sequence product_id_seq restart with 100;
alter
sequence category_id_seq restart with 100;