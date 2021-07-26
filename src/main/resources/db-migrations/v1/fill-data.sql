-- Добавление ролей
insert into roles(id, role_name) VALUES (1, 'ROLE_USER');
insert into roles(id, role_name) VALUES (2,'ROLE_ADMIN');

-- Добавление книг
insert into books(book_name) values ('Дневник Анны Франк'),
                                     ('Думай и богатей'),
                                     ('Унесенные ветром'),
                                     ('Код да Винчи'),
                                      ('Алхимик');
 -- Пользователь
insert into users(username,password,role_id)
values('user','88c1ccbaae45dab4bdd27722199068967e627a8a',1),
('admin','5728ddfa76a294b4ae082451f9b0ca8928f5392e',2);