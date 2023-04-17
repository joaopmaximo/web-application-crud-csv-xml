use banco_de_usuarios; # Selecionar banco de dados

create table usuario (
	id integer auto_increment primary key,
	nome_completo varchar(200) not null,
    	username varchar(200) not null unique,
    	email varchar(200) not null unique,
    	senha text not null,
    	telefone varchar(15) not null
);

alter table usuario drop column username; # Deletar coluna da tabela

alter table usuario rename column nome_completo to nome;

truncate table usuario; # Deletar tabela

ALTER TABLE usuario AUTO_INCREMENT = 4; # Alterar auto_increment

insert into usuario values (null, 'fulano', 'funalo@email.com', 'senha123', '11121211');

select * from usuario; # Mostrar tabela
