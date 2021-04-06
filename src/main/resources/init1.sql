create table if not exists books
(
	id serial not null
		constraint books_pk
			primary key,
	book_name varchar(40)
);

alter table books owner to postgres;

create unique index if not exists books_id_uindex
	on books (id);

