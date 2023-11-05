create database dbCadastro;
use dbCadastro;

create table tb_usuarios (
id tinyint unsigned not null auto_increment primary key, 
nome varchar(255),
email varchar(255),
celular varchar(255)
);

insert into tb_usuarios(nome, email, celular) values
("Bruce Wayne", "batilhoesdedolares@wayne.com", "99847-3420"),
("Damian Wayne", "robinlhoesdedolares@wayne.com", "99847-8010"),
("Dick Grayson", "filhodobruce@wayne.com", "99847-5749");

select id, nome, email, celular from tb_usuarios;
