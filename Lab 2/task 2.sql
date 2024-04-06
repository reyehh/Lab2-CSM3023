CREATE TABLE users(
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(100),
password VARCHAR(255),
roles VARCHAR(10),
PRIMARY KEY (id))
AUTO_INCREMENT = 1;


INSERT INTO users
VALUES (NULL, 'Ali', '1234', 'admin'),
	(NULL, 'Ahmad', '4567', 'user');

