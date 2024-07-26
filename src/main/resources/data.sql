LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'patoTest','Pa1o!1234', "pato@pato",'2024-06-20', 'USER'),(2,'gallina','Gal1ina!1234', "gallina@gallina", '2024-06-20', 'USER');
LOCK TABLES `tasks` WRITE;
INSERT INTO `tasks` VALUES (1,'test1','Descripción1','INCOMPLETE', 1),(2,'test2','','COMPLETE', 1),(3,'test3','Descripción3','INCOMPLETE', 2);
UNLOCK TABLES;





