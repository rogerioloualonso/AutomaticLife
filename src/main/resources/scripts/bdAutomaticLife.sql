CREATE SCHEMA `dbautomaticlife` ;

CREATE TABLE pessoa (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100),
telefone VARCHAR(15),
dataNascimento DATETIME
);

INSERT INTO pessoa (nome, telefone, data_nascimento) VALUES ("Rogério", "+5521986199779", "1998-11-18");

SELECT * FROM pessoa;