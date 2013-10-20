drop table if exists user;

create table user
(
	id integer AUTO_INCREMENT primary key not null,
	username varchar(25) unique not null,
	password varchar(25)  not null,
	email varchar(50) unique not null,
	active boolean not null
);
