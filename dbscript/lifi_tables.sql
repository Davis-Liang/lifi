CREATE DATABASE  IF NOT EXISTS `lifi` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `lifi`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: lifi
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
-- Table structure for table `dim_config_entity`
--

DROP TABLE IF EXISTS `dim_config_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_config_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_config_entity`
--

LOCK TABLES `dim_config_entity` WRITE;
/*!40000 ALTER TABLE `dim_config_entity` DISABLE KEYS */;
INSERT INTO `dim_config_entity` VALUES (1,'CONFIG_SHEET_PPK'),(2,'DSI_RESULT');
/*!40000 ALTER TABLE `dim_config_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_config_sheet_ppk`
--

DROP TABLE IF EXISTS `dim_config_sheet_ppk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_config_sheet_ppk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) NOT NULL,
  `prod_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ppk` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_config_sheet_ppk`
--

LOCK TABLES `dim_config_sheet_ppk` WRITE;
/*!40000 ALTER TABLE `dim_config_sheet_ppk` DISABLE KEYS */;
INSERT INTO `dim_config_sheet_ppk` VALUES (5,14,'SX4705-101','PPK 6','2018-06-12 10:01:11');
/*!40000 ALTER TABLE `dim_config_sheet_ppk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_dsi_result`
--

DROP TABLE IF EXISTS `dim_dsi_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_dsi_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) NOT NULL,
  `property1` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property2` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property3` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property4` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property5` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property6` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property7` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property8` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property9` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property10` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property11` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property12` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property13` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_dsi_result`
--

LOCK TABLES `dim_dsi_result` WRITE;
/*!40000 ALTER TABLE `dim_dsi_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `dim_dsi_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_dsi_result_param`
--

DROP TABLE IF EXISTS `dim_dsi_result_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_dsi_result_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) NOT NULL,
  `account_type` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prod_engn_desc` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `season_from` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `season_to` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `properties` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_dsi_result_param`
--

LOCK TABLES `dim_dsi_result_param` WRITE;
/*!40000 ALTER TABLE `dim_dsi_result_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `dim_dsi_result_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dim_sequence`
--

DROP TABLE IF EXISTS `dim_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dim_sequence` (
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_sequence`
--

LOCK TABLES `dim_sequence` WRITE;
/*!40000 ALTER TABLE `dim_sequence` DISABLE KEYS */;
INSERT INTO `dim_sequence` VALUES ('batch_id',14,1);
/*!40000 ALTER TABLE `dim_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stg_config_user_mapping`
--

DROP TABLE IF EXISTS `stg_config_user_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stg_config_user_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stg_config_user_mapping`
--

LOCK TABLES `stg_config_user_mapping` WRITE;
/*!40000 ALTER TABLE `stg_config_user_mapping` DISABLE KEYS */;
INSERT INTO `stg_config_user_mapping` VALUES (4,14,1,1,'2018-06-12 10:01:11','2018-06-12 10:01:11');
/*!40000 ALTER TABLE `stg_config_user_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'lifi'
--
/*!50003 DROP FUNCTION IF EXISTS `currval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     DECLARE value INTEGER; 
     SET value = 0; 
     SELECT current_value INTO value 
          FROM dim_sequence
          WHERE name = seq_name; 
     RETURN value; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE dim_sequence
          SET current_value = current_value + increment 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `setval` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `setval`(seq_name VARCHAR(50), value INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE dim_sequence
          SET current_value = value 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-15 17:38:09
