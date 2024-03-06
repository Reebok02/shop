INSERT INTO users (id, archive, email, name, password, role)
values (1, false, 'andrey.ig2012@mail.ru', 'admin', '$2a$10$DNU/8nPggo.51uul.KKjA.HpGIK3a/slVBg7sbDrhFv0rL5vQa5YS', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;