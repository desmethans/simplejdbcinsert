create user if not exists example identified by 'example';
create database example1;
use example1;
create table persons (
	firstname varchar(50)
);
grant select,insert on persons to example;

create database example2;
use example2;
create table persons (
	lastname varchar(50)
);
grant select,insert on persons to example;