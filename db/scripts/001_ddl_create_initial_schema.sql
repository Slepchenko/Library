create table files
(
    id   serial primary key,
    name varchar not null,
    path varchar not null unique
);

create table books
(
	id serial primary key,
	name varchar not null,
	author varchar not null,
	deposit_price int not null,
	rental_price int not null,
	genre varchar not null,
	file_id int references files (id) not null
);

create table users
(
	id serial primary key,
	surname varchar not null,
	name varchar not null,
	patronymic varchar not null,
	telephone_number int not null,
	increase_spend int not null
);

create table borrowed_books
(
	id serial primary key,
	book_id int references books (id) not null,
	borrow_date timestamp not null,
	refund_date timestamp not null,
	user_id int references users (id) not null
);

insert into files(name, path) values('book1', '/book1.webp');
insert into files(name, path) values('book2', '/book2.webp');
insert into files(name, path) values('book3', '/book3.webp');
insert into files(name, path) values('book4', '/book4.webp');
insert into files(name, path) values('book5', '/book5.webp');
insert into files(name, path) values('book6', '/book6.webp');
insert into files(name, path) values('book7', '/book7.webp');
insert into files(name, path) values('book8', '/book8.webp');
insert into files(name, path) values('book9', '/book9.webp');

insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('Древности российского государства', 'Федор Солнцев', 100, 500, 'Альбом', 1);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('Одно лето в аду', 'Артюр Рембо', 100, 450, 'Стихотворения и прозы', 2);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('О вращении небесных сфер', 'Николай Коперник', 100, 320, 'Научные труды', 3);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('Сказка про Кролика Питера', 'Беатрикс Поттер', 170, 300, 'Сказка', 4);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('В наше время', 'Эрнест Хемингуэй', 100, 340, 'Сборник рассказов', 5);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('Приключения Алисы в Стране чудес', 'Льюис Кэрролл', 100, 510, 'Сказка', 6);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('"Тамерлан" и другие стихотворения', 'Эдгар Аллан По', 100, 470, 'Сборник стихов', 7);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('Первая книга Уризена', 'Уильям Блейк', 150, 600, 'Поэма', 8);
insert into books (name, author, deposit_price, rental_price, genre, file_id) values ('Сараевская агада', 'Без автора', 190, 610, 'Религиозный текст', 9);