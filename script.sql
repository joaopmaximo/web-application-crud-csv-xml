USE banco_de_usuarios;

CREATE TABLE usuario (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    senha TEXT NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

INSERT INTO usuario VALUES (NULL, 'fulano', 'fulano@email.com', 'senhaFulano', '11121211');
