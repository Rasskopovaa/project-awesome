create table if not exists users
(
	id serial not null
		constraint user_pk
			primary key,
	username varchar(255) not null,
	password varchar(255) not null,
	role_id integer
		constraint role_id
			references roles
);

alter table users owner to postgres;

create unique index if not exists user_id_uindex
	on users (id);

