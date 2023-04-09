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

delete from usuario; # Zerar a tabela

truncate table usuario;

insert into usuario values (null, 'Jeyp', 'jpzica', 'email@gmail.com', 'senha', '11121211');
insert into usuario values (null, 'Jota', 'jpirado', 'gmail@email.com', 'anhes', '22212122');
insert into usuario values (null, 'raypex', 'manoray', 'ray@gmail.com', 'segredo', '312312312');

select * from usuario;