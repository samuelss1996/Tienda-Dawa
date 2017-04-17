CREATE USER 'dawa'@'localhost' IDENTIFIED BY 'dawa';
CREATE DATABASE dawa CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
GRANT USAGE ON *.* to 'dawa'@'localhost' IDENTIFIED BY 'dawa';
GRANT ALL PRIVILEGES ON dawa.* to 'dawa'@'localhost';
USE dawa;

CREATE TABLE user (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
	email VARCHAR(300) NOT NULL,
	password CHAR(128) NOT NULL, -- CHAR COUNT FOR SHA-512 HEXADECIMAL STRING
	signupDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE administrator (
	id INTEGER PRIMARY KEY,

	FOREIGN KEY(id) REFERENCES user(id)
		ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE client (
	id INTEGER PRIMARY KEY,
	type INTEGER NOT NULL,
	totalExpenses FLOAT NOT NULL,

	FOREIGN KEY(id) REFERENCES user(id)
		ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE product (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(300) NOT NULL,
	price FLOAT NOT NULL,
	stock INTEGER NOT NULL,
	type INTEGER NOT NULL
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE cd (
	id INTEGER PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	author VARCHAR(200) NULL,
	year YEAR NOT NULL,

	FOREIGN KEY(id) REFERENCES product(id)
		ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE cactus (
	id INTEGER PRIMARY KEY,
	species VARCHAR(200) NOT NULL,
	origin VARCHAR(200) NULL,

	FOREIGN KEY(id) REFERENCES product(id)
		ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `order` (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	finalPrice FLOAT NOT NULL,
	date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	discount FLOAT NOT NULL,
	client INTEGER NULL,

	FOREIGN KEY(client) REFERENCES client(id)
		ON DELETE SET NULL ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE orderLine (
	`order` INTEGER NOT NULL,
	lineNumber INTEGER NOT NULL,
	product INTEGER NULL,
	quantity INTEGER NOT NULL,
	unitPrice FLOAT NOT NULL,

	FOREIGN KEY(product) REFERENCES product(id)
		ON DELETE SET NULL ON UPDATE CASCADE,

	FOREIGN KEY(`order`) REFERENCES `order`(id)
		ON DELETE CASCADE ON UPDATE CASCADE,

	PRIMARY KEY(`order`, lineNumber)
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE rating (
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product INTEGER NOT NULL,
	client INTEGER NOT NULL,
	value FLOAT NOT NULL,
	date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

	FOREIGN KEY(product) REFERENCES product(id)
		ON DELETE CASCADE ON UPDATE CASCADE,

	FOREIGN KEY(client) REFERENCES client(id)
		ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE comment (
	rating INTEGER PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	content TEXT NOT NULL,

	FOREIGN KEY(rating) REFERENCES rating(id)
		ON DELETE CASCADE ON UPDATE CASCADE
) DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


DELIMITER '$';

CREATE PROCEDURE changePassword (IN argPassword CHAR(128), IN argUsername VARCHAR(100), IN argOldPassword CHAR(128))
BEGIN
    UPDATE user SET password = SHA2(argPassword, 512) WHERE username = argUsername AND password = SHA2(argOldPassword, 512);
END$

CREATE PROCEDURE fetchUser(IN userID INTEGER)
BEGIN
    SELECT * FROM user WHERE id = userID;
END$

CREATE PROCEDURE updateClientExpenses(IN argUserID INTEGER, IN argTotalExpenses FLOAT, IN argType INTEGER)
BEGIN
    UPDATE client SET totalExpenses = argTotalExpenses, type = argType WHERE id = argUserID;
END$

DELIMITER ';'$

-- DATOS INICIALES
INSERT INTO user(username, email, password) VALUES ('admin', 'admin@admin.com', SHA2('admin', 512));
INSERT INTO administrator(id) VALUES (1);
INSERT INTO product(name, price, stock, type) VALUES ('The Power Within - DragonForce (2012)', 50, 999, 1);
INSERT INTO product(name, price, stock, type) VALUES ('Primo Victoria - Sabaton (2005)', 30, 852, 1);
INSERT INTO product(name, price, stock, type) VALUES ('Aquarium - Aqua (1997)', 100.1, 5, 1);
INSERT INTO product(name, price, stock, type) VALUES ('Cactus Opuntia cilíndrica de África', 20, 50, 2);
INSERT INTO product(name, price, stock, type) VALUES ('Cactus Sahuaro de Sáhara', 50, 5, 2);
INSERT INTO product(name, price, stock, type) VALUES ('Cactus Echinocactus grusonii de España', 80, 10, 2);
INSERT INTO cactus(id, species, origin) VALUES (4, 'Opuntia cilíndrica', 'África');
INSERT INTO cactus(id, species, origin) VALUES (5, 'Sahuaro', 'Sáhara');
INSERT INTO cactus(id, species, origin) VALUES (6, 'Echinocactus grusonii', 'España');
INSERT INTO cd (id, title, author, year) VALUES (1, 'The Power Within', 'DragonForce', 2012);
INSERT INTO cd (id, title, author, year) VALUES (2, 'Primo Victoria', 'Sabaton', 2005);
INSERT INTO cd (id, title, author, year) VALUES (3, 'Aquarium', 'Aqua', 1997);