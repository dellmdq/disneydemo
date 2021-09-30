#UNLOCK TABLES;
CREATE DATABASE  IF NOT EXISTS `disney_demo`;
USE `disney_demo`;
--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;

CREATE TABLE `actor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(200) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `bio` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ;

INSERT INTO `actor` VALUES 
	(1, 'foto', 'Bugs Bunny', 20, 88.9, 'A adorable bunny. Going through fun adventures.'),
    (2, 'foto', 'Rocko', 54, 75.4, 'A wallaby that works con Conglomo'),
    (3, 'foto', 'The Coyote', 33, 66.2, 'Acme sales people');

DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre`(
	`id`int NOT NULL auto_increment,
    `name` varchar(200) DEFAULT NULL,
    `image` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_unique` (`name`)
) ;

insert into `genre` values 
	(1,'Terror','foto'),
    (2,'Cartoon','foto'),
    (3,'Comedy','foto');

DROP TABLE IF EXISTS `movietvserie`;
CREATE TABLE `movietvserie`(
	`id`int NOT NULL auto_increment,
    `image` varchar(200) DEFAULT NULL,
    `title` varchar(200) DEFAULT NULL,
    `release_date` date DEFAULT NULL,
    `rating` int DEFAULT NULL,
    `genre_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `title_unique` (`title`),
    KEY `FK_GENRE_id` (`genre_id`),
    CONSTRAINT `FK_GENRE` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
    
);

insert into `movietvserie` values 
	(1,'foto', 'Bugs Bunny The Movie', '1998-10-21', 4, 2),
	(2,'foto', 'Rocko in Conglomo', '2015-09-11', 5, 1),
    (3,'foto', 'Pursuing the roadrunner', '2015-09-11', 5, 3);



   
DROP TABLE IF EXISTS `movietvserie_actor`;   
CREATE TABLE `movietvserie_actor` (
  `actor_id` int NOT NULL,
  `movietvserie_id` int NOT NULL,
  
  PRIMARY KEY (`actor_id`,`movietvserie_id`),
  
  KEY `FK_actor_id` (`actor_id`),
  
  CONSTRAINT `FK_movietvserie` FOREIGN KEY (`movietvserie_id`) REFERENCES `movietvserie` (`id`) ,
  
  CONSTRAINT `FK_actor` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) 
  
);

insert into `movietvserie_actor` values
	(1,1),
    (2,2),
    (3,3);
