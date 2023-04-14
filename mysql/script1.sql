use banco_de_usuarios;

create table usuario (
	id integer auto_increment primary key,
	nome_completo varchar(200) not null,
    	username varchar(200) not null unique,
    	email varchar(200) not null unique,
    	senha text not null,
    	telefone varchar(15) not null
);

alter table usuario drop column nome; # Deletar coluna da tabela

truncate table usuario;

ALTER TABLE usuario AUTO_INCREMENT = 4; # Alterar auto_increment

insert into usuario values (null, 'fulano', 'Ufulano', 'funalo@email.com', 'senha123', '11121211');

select * from usuario;
