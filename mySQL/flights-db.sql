CREATE DATABASE  IF NOT EXISTS `ltcnhcr46i5hgytj` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ltcnhcr46i5hgytj`;
-- MySQL dump 10.13  Distrib 8.0.24, for macos11 (x86_64)
--
-- Host: bmlx3df4ma7r1yh4.cbetxkdyhwsb.us-east-1.rds.amazonaws.com    Database: ltcnhcr46i5hgytj
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airline` (
  `airlineID` int NOT NULL AUTO_INCREMENT COMMENT 'PK - Airlines classified by ID. Used to identify airlines in other tables',
  `airlineName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`airlineID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES (1,'American Airlines'),(2,'United Airlines'),(3,'Delta Air Lines'),(4,'Southwest Airlines'),(5,'JetBlue Airways');
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `airportCode` varchar(3) NOT NULL COMMENT 'Other tables refer to airports by a 3-digit IATA airport code. These are standardized codes recognized globally.',
  `airportName` varchar(50) DEFAULT NULL COMMENT 'Formal long name of airport',
  PRIMARY KEY (`airportCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES ('IAH','George Bush Intercontinental Airport'),('LAX','Los Angeles International Airport'),('ORD','O\'Hare International Airport'),('SEA','Seattle-Tacoma International Airport'),('SFO','San Francisco International Airport');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `cityID` int NOT NULL AUTO_INCREMENT COMMENT 'City IDs are used as a FK by other tables. Needed, because duplicate city names exist across the planet.',
  `cityName` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cityID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'San Francisco','United States'),(2,'Los Angeles','United States'),(3,'Seattle','United States'),(4,'Houston','United States'),(5,'Chicago','United States');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `flightNum` varchar(10) NOT NULL COMMENT 'Should not contain spaces. Contains a standardized airline code followed by its flight number.',
  `airlineID` int DEFAULT NULL,
  `leaveTime` time DEFAULT NULL COMMENT 'TIME object -format: yyyy-MM-dd',
  `arriveTime` time DEFAULT NULL COMMENT 'TIME object -format: yyyy-MM-dd',
  `leaveDate` date DEFAULT NULL COMMENT 'Format: hh:mm:ss',
  `arriveDate` date DEFAULT NULL,
  `price` int DEFAULT NULL COMMENT 'Convey price in whole dollar units only',
  `numStops` int DEFAULT NULL,
  `seatsAvailable` int DEFAULT NULL COMMENT '#Seats remaining on flight. Automatically adjusted by app when reservation made',
  `originCity` int DEFAULT NULL,
  `destCity` int DEFAULT NULL,
  `originAirport` varchar(3) DEFAULT NULL COMMENT 'Use IATA 3-digit codes for this field',
  `destAirport` varchar(3) DEFAULT NULL COMMENT 'Use IATA 3-digit codes for this field',
  PRIMARY KEY (`flightNum`),
  KEY `fk_cityID_idx` (`originCity`,`destCity`),
  KEY `fk_airlineID_idx` (`airlineID`),
  KEY `fk_destCityID_idx` (`destCity`),
  KEY `fk_destAirport_idx` (`destAirport`),
  KEY `fk_originAirport_idx` (`originAirport`),
  CONSTRAINT `fk_airlineID` FOREIGN KEY (`airlineID`) REFERENCES `airline` (`airlineID`),
  CONSTRAINT `fk_destAirport` FOREIGN KEY (`destAirport`) REFERENCES `airport` (`airportCode`),
  CONSTRAINT `fk_destCityID` FOREIGN KEY (`destCity`) REFERENCES `city` (`cityID`),
  CONSTRAINT `fk_origCityID` FOREIGN KEY (`originCity`) REFERENCES `city` (`cityID`),
  CONSTRAINT `fk_originAirport` FOREIGN KEY (`originAirport`) REFERENCES `airport` (`airportCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES ('AA715',1,'09:15:00','13:00:00','2021-06-20','2021-06-20',376,0,29,1,4,'SFO','IAH'),('B6344',5,'22:00:00','02:30:00','2021-06-22','2021-06-23',200,0,12,3,5,'SEA','ORD'),('DL177',3,'12:00:00','13:15:00','2021-06-24','2021-06-24',115,0,57,1,2,'SFO','LAX'),('DL255',3,'06:30:00','10:30:00','2021-06-24','2021-06-24',360,1,2,5,4,'ORD','IAH'),('UA3818',2,'14:20:00','21:35:00','2021-06-18','2021-06-18',500,2,42,4,3,'IAH','SEA');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (58);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `passengerid` int NOT NULL COMMENT 'PK, Auto-generated by database. Do not define this in passenger object in app',
  `firstname` varchar(255) DEFAULT NULL,
  `flightnum` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `class` int DEFAULT NULL COMMENT '1- First class, 2- Business class, 3- Coach',
  `seatnum` varchar(255) DEFAULT NULL COMMENT 'Automatically generated in-application based on remaining seats. Don’t assign this manually',
  `user_email` varchar(255) DEFAULT NULL,
  `bookingorigin` int DEFAULT NULL COMMENT '0 - Flight booked in flight app. 1 - Flight booked externally with packages site',
  PRIMARY KEY (`passengerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (41,'test','AA715','name',0,'14',NULL,1),(42,'test','AA715','name',0,'24',NULL,1),(43,'test','AA715','name',0,'5',NULL,1),(44,'test','AA715','name',0,'11',NULL,1),(45,'test','AA715','name',0,'1',NULL,1),(46,'test','AA715','name',0,'18',NULL,1),(47,'test','AA715','name',0,'24',NULL,1),(48,'test','AA715','name',0,'4',NULL,1),(49,'test','AA715','name',0,'9',NULL,1),(50,'test','AA715','name',0,'28',NULL,1),(51,'test','AA715','name',0,'17',NULL,1),(52,'test','AA715','name',0,'8',NULL,1),(53,'test','DL177','name',0,'20',NULL,1),(54,'test','DL177','name',0,'16',NULL,1),(55,'test','DL177','name',0,'10',NULL,1),(56,'test','UA3818','name',0,'8',NULL,1),(57,'test','AA715','name',0,'9',NULL,1);
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'The type of role. For this application, all roles are equivalent to basic user.',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_USER'),(7,'ROLE_USER'),(8,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK - User’s ID. Used only on flights website.',
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT 'Secured with BCrypt',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'johngust07@gmail.com','John','Doe','$2a$10$8uLn/CcdIz3yFit1ve8Ar.OWjGOMbDyg9dKIN9X1AQYAkvgpo1jbq'),(2,'aa@gmail.com','TRE','Joh','$2a$10$.DzhbjpdDtPQwiuJ2S.yJuxj/FPwsLM7GEov46LLlbsigrzClOM.6'),(3,'a@gmail.com','ss','ss','$2a$10$ePk/ou3f4xEh36EKJpaqB.yqhoBiY9VDTM99Otf/LEUO2pIDSEXxq'),(4,'rbernard@csumb.edu','Joe','Jack','$2a$10$JRh6hl4i8nkg5wN.xUJi4.0w9eQoOhjlS.DNLfJpQO0KiCNivFZHC'),(5,'aaa@gmail.com','a','a','$2a$10$upwSVdrGZzxr.ZoUAKQ6..B6FrK82hhSmVIJpHRENR82Zc65tVn6C'),(6,'cboyd@csumb.edu','Chris','B','$2a$10$bRioaZSfF5g0ocTBru/e2u6Sy5X6pTNcYvhLd9lWGXcxpkmr1Dipe'),(7,'b@gmail.com','Joe','Jack','$2a$10$nHifJGnVUWvNWpTD/.PaRe0VqE8NizAjCeXgY.65TXCcSU2XaRsxi'),(8,'c@gmail.com','Joe','Mark','$2a$10$uTGV.p2io0MbobOJaqG0nO/B/rnXPEp/xHM4ytQxersoO1fNa8GP.');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-18 13:10:21
