-- Server version	9.0.0

DROP SCHEMA IF EXISTS `todolist` ;

CREATE SCHEMA IF NOT EXISTS `todolist` DEFAULT CHARACTER SET utf8mb4;
USE `todolist` ;

-- Table structure for table `tasks`
--DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS users (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(17) UNIQUE NOT NULL,
  `password` varchar(250) NOT NULL,
  `email` varchar(40) UNIQUE NOT NULL,
  `register_date` date NOT NULL,
  `role` enum ('ADMIN','USER') DEFAULT NULL,
   PRIMARY KEY (`id`)
    );

--DROP TABLE IF EXISTS `tasks`;
CREATE TABLE IF NOT EXISTS tasks (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(60) default null,
  `state` enum('COMPLETE','INCOMPLETE') NOT NULL,
  `user_id` int NOT NULL,
   PRIMARY KEY (`id`),
   CONSTRAINT `fk_users`
       FOREIGN KEY (`user_id`)
       REFERENCES users (id)
       ON DELETE CASCADE);
