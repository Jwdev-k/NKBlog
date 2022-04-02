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
  `uid` varchar(20) NOT NULL COMMENT '유저아이디',
  `password` varchar(256) DEFAULT NULL COMMENT '비밀번호',
  `gender` varchar(2) DEFAULT NULL COMMENT '성별',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('1234','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','여자'),('admin','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','여자'),('admin1','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','여자'),('test','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','남자'),('test2','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','여자');
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
  PRIMARY KEY (`bno`),
  KEY `boardlist_FK` (`uid`),
  CONSTRAINT `boardlist_FK` FOREIGN KEY (`uid`) REFERENCES `account` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardlist`
--

LOCK TABLES `boardlist` WRITE;
/*!40000 ALTER TABLE `boardlist` DISABLE KEYS */;
INSERT INTO `boardlist` VALUES (1,'테스트 게시글','admin','2022-04-02 10:38:53','이건 테스트이다.',1),(2,'asdfdas','admin','2022-04-01 15:00:00',' fsdafasdf',1),(3,'ㅇㄴㄻㅁㄴㅇㄹㄴㅇㅁㄻㄴ','test','2022-04-01 15:00:00','ㅇㄴ루ㅏㅁㄴ일;ㄴㅇ므흠ㄴ야 ',1);
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
  KEY `comment_FK_1` (`uid`),
  KEY `comment_FK` (`bno`),
  CONSTRAINT `comment_FK` FOREIGN KEY (`bno`) REFERENCES `boardlist` (`bno`),
  CONSTRAINT `comment_FK_1` FOREIGN KEY (`uid`) REFERENCES `account` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'test','두번째 테스트입니다. ㅎㅎ');
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

-- Dump completed on 2022-04-02 19:59:03
