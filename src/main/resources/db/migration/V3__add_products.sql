INSERT INTO products (id, price, name, amount, imageurl)
VALUES (1, 44999, '4.7" Smartfon Apple iPhone SE 2022 64 GB belyy', 5, 'iphoneSEwhite.jpg'),
       (2, 53499, '6.1" Smartfon Apple iPhone 11 64 GB belyy', 3, 'iphone11white.jpg'),
       (3, 54399, '4.7" Smartfon Apple iPhone SE 2022 128 GB belyy', 2, 'iphoneSEwhite.jpg'),
       (4, 67299, '6.1" Smartfon Apple iPhone 12 64 GB krasnyy', 6, 'iphone12red.jpg'),
       (5, 79899, '6.1" Smartfon Apple iPhone 14 128 GB chernyy', 1, 'iphone14black.jpg'),
       (6, 85499, '6.1" Smartfon Apple iPhone 13 256 GB zelenyy', 0, 'iphone13green.jpg');

ALTER SEQUENCE product_seq RESTART with 7;