-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: todolist
-- ------------------------------------------------------
-- Server version	9.0.0


-- Table structure for table `tasks`

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `state` enum('COMPLETE','INCOMPLETE') NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) UNIQUE NOT NULL,
  PRIMARY KEY (`id`)
);

LOCK TABLES `tasks` WRITE;
INSERT INTO `tasks` VALUES (1,'test1','Descripción1','INCOMPLETE'),(2,'test2','Descripción2','COMPLETE'),(3,'test3','Descripción3','INCOMPLETE');
LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'pato','Pa1o!1234', "pato@pato"),(2,'gallina','Gal1ina!1234', "gallina@gallina");
UNLOCK TABLES;





