CREATE SCHEMA `dbautomaticlife` ;

CREATE TABLE people (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
phoneNumber VARCHAR(15),
birthday DATETIME
);

INSERT INTO pessoa (name, phoneNumber, birthday) VALUES ("Rogério", "+5521986199779", "1998-11-18");