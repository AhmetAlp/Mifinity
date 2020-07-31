CREATE DATABASE `cards` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE USER 'carduser'@'localhost' IDENTIFIED BY 'carduser';
GRANT ALL PRIVILEGES ON cards.* TO 'carduser'@'localhost';

CREATE TABLE cards.card (
  `number` varchar(255) NOT NULL,
  `holdername` varchar(255) NOT NULL,
  `expirydate` char(5) NOT NULL,
  PRIMARY KEY (`number`)
);

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
);

INSERT INTO `cards`.`card` (`number`, `holdername`, `expirydate`) VALUES ('4111 1111 1111 1111', 'Ahmet Alp', '23/07');
INSERT INTO `cards`.`card` (`number`, `holdername`, `expirydate`) VALUES ('5500 0000 0000 0004', 'Paul Doe', '21/11');

INSERT INTO `cards`.`user` (`username`, `password`) VALUES ('Ahmet', '81dc9bdb52d04dc20036dbd8313ed055');

