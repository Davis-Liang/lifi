CREATE DATABASE  IF NOT EXISTS `ddr` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `ddr`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ddr
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `dim_account`
--

DROP TABLE IF EXISTS `dim_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account_type` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_account`
--

LOCK TABLES `dim_account` WRITE;
/*!40000 ALTER TABLE `dim_account` DISABLE KEYS */;
INSERT INTO `dim_account` VALUES (1,'Belle','Normal','',1),(2,'GoodBaby','GB','',1);
/*!40000 ALTER TABLE `dim_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_resource`
--

DROP TABLE IF EXISTS `dim_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resource_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resource_value` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_resource`
--

LOCK TABLES `dim_resource` WRITE;
/*!40000 ALTER TABLE `dim_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `dim_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_role`
--

DROP TABLE IF EXISTS `dim_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_role`
--

LOCK TABLES `dim_role` WRITE;
/*!40000 ALTER TABLE `dim_role` DISABLE KEYS */;
INSERT INTO `dim_role` VALUES (1,'ROLE_ADMIN','管理员角色',1),(3,'ROLE_LIFI_USER','LIFI用户角色',1),(4,'ROLE_DSI_USER','DSI用户角色',1);
/*!40000 ALTER TABLE `dim_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_user`
--

DROP TABLE IF EXISTS `dim_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_user`
--

LOCK TABLES `dim_user` WRITE;
/*!40000 ALTER TABLE `dim_user` DISABLE KEYS */;
INSERT INTO `dim_user` VALUES (1,'clayyu','e10adc3949ba59abbe56e057f20f883e',1,'2018-06-11 02:54:49');
/*!40000 ALTER TABLE `dim_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stg_resource_role`
--

DROP TABLE IF EXISTS `stg_resource_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stg_resource_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stg_resource_role_1` (`resource_id`),
  KEY `fk_stg_resource_role_2` (`role_id`),
  CONSTRAINT `fk_stg_resource_role_1` FOREIGN KEY (`resource_id`) REFERENCES `dim_resource` (`id`),
  CONSTRAINT `fk_stg_resource_role_2` FOREIGN KEY (`role_id`) REFERENCES `dim_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stg_resource_role`
--

LOCK TABLES `stg_resource_role` WRITE;
/*!40000 ALTER TABLE `stg_resource_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `stg_resource_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stg_user_account`
--

DROP TABLE IF EXISTS `stg_user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stg_user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stg_user_account_1` (`user_id`),
  KEY `fk_stg_user_account_2` (`account_id`),
  CONSTRAINT `fk_stg_user_account_1` FOREIGN KEY (`user_id`) REFERENCES `dim_user` (`id`),
  CONSTRAINT `fk_stg_user_account_2` FOREIGN KEY (`account_id`) REFERENCES `dim_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stg_user_account`
--

LOCK TABLES `stg_user_account` WRITE;
/*!40000 ALTER TABLE `stg_user_account` DISABLE KEYS */;
INSERT INTO `stg_user_account` VALUES (1,1,1);
/*!40000 ALTER TABLE `stg_user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stg_user_role`
--

DROP TABLE IF EXISTS `stg_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stg_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stg_user_role_1` (`user_id`),
  KEY `fk_stg_user_role_2` (`role_id`),
  CONSTRAINT `fk_stg_user_role_1` FOREIGN KEY (`user_id`) REFERENCES `dim_user` (`id`),
  CONSTRAINT `fk_stg_user_role_2` FOREIGN KEY (`role_id`) REFERENCES `dim_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stg_user_role`
--

LOCK TABLES `stg_user_role` WRITE;
/*!40000 ALTER TABLE `stg_user_role` DISABLE KEYS */;
INSERT INTO `stg_user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `stg_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ddr'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-15 17:37:45
