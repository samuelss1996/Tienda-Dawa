CREATE TABLE user (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
	email VARCHAR(300) NOT NULL,
	password CHAR(128) NOT NULL, -- CHAR COUNT FOR SHA-512 HEXADECIMAL STRING
	signupDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE administrator (
	id INTEGER PRIMARY KEY,

	FOREIGN KEY(id) REFERENCES user(id)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE client (
	id INTEGER PRIMARY KEY,
	type INTEGER NOT NULL,
	totalExpenses FLOAT NOT NULL,

	FOREIGN KEY(id) REFERENCES user(id)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE product (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(300) NOT NULL,
	price FLOAT NOT NULL,
	stock INTEGER NOT NULL,
	type INTEGER NOT NULL
);

CREATE TABLE cd (
	id INTEGER PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	author VARCHAR(200) NULL,
	year YEAR NOT NULL,

	FOREIGN KEY(id) REFERENCES product(id)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE cactus (
	id INTEGER PRIMARY KEY,
	species VARCHAR(200) NOT NULL,
	origin VARCHAR(200) NULL,

	FOREIGN KEY(id) REFERENCES product(id)
		ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `order` (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	finalPrice FLOAT NOT NULL,
	date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	discount FLOAT NOT NULL,
	client INTEGER NULL,

	FOREIGN KEY(client) REFERENCES client(id)
		ON DELETE SET NULL ON UPDATE CASCADE
);

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
);

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
);

CREATE TABLE comment (
	rating INTEGER PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	content TEXT NOT NULL,

	FOREIGN KEY(rating) REFERENCES rating(id)
		ON DELETE CASCADE ON UPDATE CASCADE
);