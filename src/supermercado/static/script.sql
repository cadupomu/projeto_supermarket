create database super;
create or replace table produto (
   id int primary key auto_increment,
   nome varchar(150) not null,
   preco double(9,2) not null unique,
   quantidade int not null unique,
   tipo varchar(150) not null
);