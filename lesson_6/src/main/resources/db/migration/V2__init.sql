CREATE TYPE enum_product_type AS ENUM('ACCOUNT', 'CARD');

CREATE TABLE products
(
    id bigserial primary key,
    account_number varchar(30) unique,
    balance numeric default 0.0,
    product_type enum_product_type not null,
    user_id bigserial not null references users(id)
);

INSERT INTO products (account_number, balance, product_type, user_id)
VALUES
    ('5147 2351 2551 2426 2325', 355.865, 'CARD', 1),
    ('1525148336541225488632', 124.5654, 'ACCOUNT', 1),
    ('1525148336541287789654', 5000000.777, 'ACCOUNT', 2),
    ('4788 5634 2551 3548 2325', 124, 'CARD', 3),
    ('4258 8654 3364 4568 2354', 789555.32, 'CARD', 2),
    ('5238 1254 7574 5687 5534', 10000000000, 'CARD', 4),
    ('1525148336541965874536', 3578, 'ACCOUNT', 4),
    ('1525148336525874154236', 5899965.5698, 'ACCOUNT', 4),
    ('4871 2543 3584 2247 3578', 4787.4521, 'CARD', 2),
    ('1525148336543652158632', 356987.68, 'ACCOUNT', 1),
    ('1525148336541225963247', 1000.78, 'ACCOUNT', 1),
    ('4258 2351 4542 2426 1542', 214544.55, 'CARD', 3);