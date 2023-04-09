use banco_de_usuarios;

create table usuario (
	id integer auto_increment primary key,
    nome_completo varchar(200) not null,
    username varchar(200) not null unique,
    email varchar(200) not null unique,
    senha text not null,
    telefone varchar(15) not null
);

insert into usuario values (null, 'Jeyp', 'jpzica', 'email@gmail.com', 'senha', '11121211');

select * from usuario;