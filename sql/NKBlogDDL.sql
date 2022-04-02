-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: nkblog
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.4-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(20) DEFAULT NULL COMMENT '유저아이디',
  `password` varchar(256) DEFAULT NULL COMMENT '비밀번호',
  `gender` varchar(2) DEFAULT NULL COMMENT '성별',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'admin','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','여자'),(2,'1234','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','여자'),(3,'test','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','남자'),(4,'test2','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','여자'),(7,'admin1','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','여자');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boardlist`
--

DROP TABLE IF EXISTS `boardlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boardlist` (
  `bno` int(11) NOT NULL AUTO_INCREMENT COMMENT '번호',
  `title` varchar(50) DEFAULT NULL COMMENT '제목',
  `uid` varchar(20) NOT NULL COMMENT '작성자',
  `created` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '작성일',
  `content` varchar(2048) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardlist`
--

LOCK TABLES `boardlist` WRITE;
/*!40000 ALTER TABLE `boardlist` DISABLE KEYS */;
INSERT INTO `boardlist` VALUES (1,'123123','admin','2022-03-28 15:00:00',' 12312312',1),(2,'테스트 ','admin','2022-03-28 15:00:00','이 글은 테스트 이다. ',1),(3,'1234','admin','2022-03-29 15:00:00',' 1234',1),(4,'네이버 테스트','\"Naka\"','2022-03-29 15:00:00','ㅇㄹㅇㄴㅁㄹㄴㅇㅁ ',1),(5,'498984','admin','2022-03-30 15:00:00',' 182626',1),(6,'1565165165','admin','2022-03-30 15:00:00',' 88888',1),(7,'dsf','admin','2022-03-30 15:00:00',' sdfsdfsdfsd',1),(8,'sfsdf','admin','2022-03-30 15:00:00',' sdfsdfsdf',1),(9,'sfsf','admin','2022-03-30 15:00:00',' sdfsdfsdfsd',1),(10,'sfsdfsfsd','admin','2022-03-30 15:00:00',' fsdfsfsfs',1),(11,'sdfsfdsfsd','admin','2022-03-30 15:00:00',' fsdfsdfsfsd',1),(12,'sfsdfsdfsd','admin','2022-03-30 15:00:00',' fdsfsdfsdf',1),(13,'fsfdsfdsfs','admin','2022-03-30 15:00:00',' fsfsfs',1),(14,'sdfdsfds','admin','2022-03-30 15:00:00',' fsdfsdfsfds',1),(15,'sdfsdf','admin','2022-03-30 15:00:00',' dsfdsfsfsd',1),(16,'dsfsdfsdfsd','admin','2022-03-30 15:00:00',' fsdfsdfsd',1),(17,'cxv','admin','2022-03-30 15:00:00',' xcvcxvcxvcxv',1),(18,'12121','admin','2022-03-30 15:00:00',' 2121212',1),(19,'13131','admin','2022-03-30 15:00:00',' 313131',1),(20,'dtest','admin','2022-03-30 15:00:00','1234',1),(21,'test','admin','2022-03-30 15:00:00',' test',1),(22,'418651','admin','2022-03-30 15:00:00',' 484848',1),(23,'123123','admin','2022-03-28 15:00:00',' 12312312',1),(24,'테스트 ','admin','2022-03-28 15:00:00','이 글은 테스트 이다. ',1),(25,'1234','admin','2022-03-29 15:00:00',' 1234',1),(26,'네이버 테스트','\"Naka\"','2022-03-29 15:00:00','ㅇㄹㅇㄴㅁㄹㄴㅇㅁ ',1),(27,'498984','admin','2022-03-30 15:00:00',' 182626',1),(28,'1565165165','admin','2022-03-30 15:00:00',' 88888',1),(29,'dsf','admin','2022-03-30 15:00:00',' sdfsdfsdfsd',1),(30,'sfsdf','admin','2022-03-30 15:00:00',' sdfsdfsdf',1),(31,'sfsf','admin','2022-03-30 15:00:00',' sdfsdfsdfsd',1),(32,'sfsdfsfsd','admin','2022-03-30 15:00:00',' fsdfsfsfs',1),(33,'sdfsfdsfsd','admin','2022-03-30 15:00:00',' fsdfsdfsfsd',1),(34,'sfsdfsdfsd','admin','2022-03-30 15:00:00',' fdsfsdfsdf',1),(35,'fsfdsfdsfs','admin','2022-03-30 15:00:00',' fsfsfs',1),(36,'sdfdsfds','admin','2022-03-30 15:00:00',' fsdfsdfsfds',1),(37,'sdfsdf','admin','2022-03-30 15:00:00',' dsfdsfsfsd',1),(38,'dsfsdfsdfsd','admin','2022-03-30 15:00:00',' fsdfsdfsd',1),(39,'cxv','admin','2022-03-30 15:00:00',' xcvcxvcxvcxv',1),(40,'12121','admin','2022-03-30 15:00:00',' 2121212',1),(41,'13131','admin','2022-03-30 15:00:00',' 313131',1),(42,'dtest','admin','2022-03-30 15:00:00',' test',1),(43,'test','admin','2022-03-30 15:00:00',' test',1),(44,'418651','admin','2022-03-30 15:00:00',' 484848',1),(45,'418651','admin','2022-03-30 15:00:00',' 484848',1),(46,'418651','admin','2022-03-30 15:00:00',' 484848',1),(47,'418651','admin','2022-03-30 15:00:00',' 484848',1),(48,'418651','admin','2022-03-30 15:00:00',' 484848',1),(49,'418651','admin','2022-03-30 15:00:00',' 484848',1),(50,'418651','admin','2022-03-30 15:00:00',' 484848',1),(51,'418651','admin','2022-03-30 15:00:00',' 484848',1),(52,'418651','admin','2022-03-30 15:00:00',' 484848',1),(53,'418651','admin','2022-03-30 15:00:00',' 484848',1),(54,'418651','admin','2022-03-30 15:00:00',' 484848',1),(55,'418651','admin','2022-03-30 15:00:00',' 484848',1),(56,'418651','admin','2022-03-30 15:00:00',' 484848',1),(57,'418651','admin','2022-03-30 15:00:00',' 484848',1),(58,'418651','admin','2022-03-30 15:00:00',' 484848',1),(59,'418651','admin','2022-03-30 15:00:00',' 484848',1),(60,'418651','admin','2022-03-30 15:00:00',' 484848',1),(61,'네이버 테스트','Naka','2022-03-30 15:00:00','테스트\r\n ',1);
/*!40000 ALTER TABLE `boardlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `bno` int(11) NOT NULL,
  `uid` varchar(20) NOT NULL,
  `comment` varchar(100) NOT NULL,
  KEY `comment_FK` (`bno`),
  CONSTRAINT `comment_FK` FOREIGN KEY (`bno`) REFERENCES `boardlist` (`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'test','testdfsad'),(20,'Naka',' 댓글 테스트 comment test'),(20,'Naka','댓글 테스트 입니다.');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nkblog'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-02  5:10:50
