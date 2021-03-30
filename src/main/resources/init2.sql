create table if not exists roles
(
	id serial not null
		constraint roles_pk
			primary key,
	role_name varchar(100) not null
);

alter table roles owner to postgres;

create unique index if not exists roles_id_uindex
	on roles (id);

