CREATE SCHEMA `dbautomaticlife` ;

CREATE TABLE pessoa (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100),
telefone VARCHAR(50),
dataNascimento DATE
);

INSERT INTO pessoa (nome, telefone, dataNascimento) VALUES ("Rogério", "+5521986199779", "1998-11-18");

SELECT * FROM pessoa;