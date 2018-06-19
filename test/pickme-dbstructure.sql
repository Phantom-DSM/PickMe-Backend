-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: pickme
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `established_date` date NOT NULL,
  `ceo_name` varchar(45) NOT NULL,
  `company_type` varchar(60) NOT NULL,
  `one_line_intro` varchar(100) NOT NULL,
  `personnel` int(11) NOT NULL,
  `annual_sales` int(11) DEFAULT NULL,
  `website_url` varchar(100) NOT NULL,
  `talent` tinytext NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `modified_date` datetime(3) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_image_src`
--

DROP TABLE IF EXISTS `company_image_src`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_image_src` (
  `company_id` int(11) NOT NULL,
  `image_src` varchar(100) NOT NULL,
  PRIMARY KEY (`company_id`,`image_src`),
  CONSTRAINT `FK_company_image_src_company_company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `period_start` date NOT NULL,
  `period_end` date DEFAULT NULL,
  `project_type` enum('PERSONAL','TEAM') NOT NULL,
  `one_line_intro` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  `personnel` int(11) NOT NULL,
  `introduction` tinytext NOT NULL,
  `contents` tinytext NOT NULL,
  `expected_effect` tinytext NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `modified_date` datetime(3) NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `project_user_user_id_idx` (`user_id`),
  CONSTRAINT `project_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_member`
--

DROP TABLE IF EXISTS `project_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_member` (
  `project_id` int(11) NOT NULL,
  `member_id` char(32) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `belong` varchar(100) NOT NULL DEFAULT '대덕소프트웨어마이스터고등학교',
  PRIMARY KEY (`project_id`),
  KEY `FK_project_member_user_user_id_idx` (`member_id`),
  CONSTRAINT `FK_project_member_project_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_project_member_user_user_id` FOREIGN KEY (`member_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_skill`
--

DROP TABLE IF EXISTS `project_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_skill` (
  `project_id` int(11) NOT NULL,
  `skill_name` varchar(45) NOT NULL,
  `created_date` datetime(3) NOT NULL,
  PRIMARY KEY (`project_id`,`skill_name`),
  CONSTRAINT `FK_project_skill_project_project_id0` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resume_clinic`
--

DROP TABLE IF EXISTS `resume_clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resume_clinic` (
  `clinic_id` int(11) NOT NULL AUTO_INCREMENT,
  `snapshot_id` int(11) NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `done` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`clinic_id`),
  KEY `FK_resume_clinic_snapshot_id_idx` (`snapshot_id`),
  CONSTRAINT `FK_resume_clinic_snapshot_id` FOREIGN KEY (`snapshot_id`) REFERENCES `resume_snapshot` (`snapshot_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resume_clinic_apply`
--

DROP TABLE IF EXISTS `resume_clinic_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resume_clinic_apply` (
  `clinic_id` int(11) NOT NULL,
  `teacher_id` char(32) NOT NULL,
  PRIMARY KEY (`clinic_id`,`teacher_id`),
  KEY `FK_self_introduction_clinic_apply_teacher_teacher_id_idx` (`teacher_id`),
  CONSTRAINT `FK_resume_clinic_apply_clinic_id_resume_clinic` FOREIGN KEY (`clinic_id`) REFERENCES `resume_clinic` (`clinic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_resume_clinic_apply_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resume_clinic_comment`
--

DROP TABLE IF EXISTS `resume_clinic_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resume_clinic_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_id` int(11) NOT NULL,
  `teacher_id` char(32) NOT NULL,
  `comment` tinytext NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_self_introductiond_clinic_comment_teacher_id_idx` (`teacher_id`),
  KEY `FK_self_introduction_clinic_comment_clinic_id0_idx` (`clinic_id`),
  CONSTRAINT `FK_self_introduction_clinic_comment_clinic_id0` FOREIGN KEY (`clinic_id`) REFERENCES `resume_clinic` (`clinic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_self_introductiond_clinic_comment_teacher_id0` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resume_snapshot`
--

DROP TABLE IF EXISTS `resume_snapshot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resume_snapshot` (
  `snapshot_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `bio` varchar(100) NOT NULL,
  `birth_day` int(2) NOT NULL,
  `birth_month` int(2) NOT NULL,
  `birth_year` int(4) NOT NULL,
  `sex` enum('MAN','WOMAN') NOT NULL,
  `base_addr` varchar(45) NOT NULL,
  `detail_addr` varchar(45) NOT NULL,
  `desired_min_sal` int(11) DEFAULT NULL,
  `desired_max_sal` int(11) DEFAULT NULL,
  PRIMARY KEY (`snapshot_id`),
  KEY `FK_resume_snapshot_user_user_id_idx` (`user_id`),
  CONSTRAINT `FK_resume_snapshot_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `self_introduction`
--

DROP TABLE IF EXISTS `self_introduction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `self_introduction` (
  `introduction_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL,
  `title` varchar(45) NOT NULL,
  `growth` tinytext NOT NULL,
  `strength_and_weakness` tinytext NOT NULL,
  `school_life` tinytext NOT NULL,
  `motivation_and_aspiration` tinytext NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `modified_date` datetime(3) NOT NULL,
  PRIMARY KEY (`introduction_id`),
  KEY `FK_self_introduction_user_user_id` (`user_id`),
  CONSTRAINT `FK_self_introduction_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `self_introduction_clinic`
--

DROP TABLE IF EXISTS `self_introduction_clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `self_introduction_clinic` (
  `clinic_id` int(11) NOT NULL AUTO_INCREMENT,
  `snapshot_id` int(11) NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `done` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`clinic_id`),
  KEY `FK_self_introduction_clinic_self_introduction_snapshot_id_idx` (`snapshot_id`),
  CONSTRAINT `FK_self_introduction_clinic_self_introduction_snapshot_id` FOREIGN KEY (`snapshot_id`) REFERENCES `self_introduction_snapshot` (`snapshot_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `self_introduction_clinic_apply`
--

DROP TABLE IF EXISTS `self_introduction_clinic_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `self_introduction_clinic_apply` (
  `clinic_id` int(11) NOT NULL,
  `teacher_id` char(32) NOT NULL,
  PRIMARY KEY (`clinic_id`,`teacher_id`),
  KEY `FK_self_introduction_clinic_apply_teacher_teacher_id_idx` (`teacher_id`),
  CONSTRAINT `FK_self_introduction_clinic_apply_clinic_clinic_id` FOREIGN KEY (`clinic_id`) REFERENCES `self_introduction_clinic` (`clinic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_self_introduction_clinic_apply_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `self_introduction_clinic_comment`
--

DROP TABLE IF EXISTS `self_introduction_clinic_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `self_introduction_clinic_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_id` int(11) NOT NULL,
  `teacher_id` char(32) NOT NULL,
  `comment` tinytext NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_self_introduction_clinic_comment_clinic_id_idx` (`clinic_id`),
  KEY `FK_self_introductiond_clinic_comment_teacher_id_idx` (`teacher_id`),
  CONSTRAINT `FK_self_introduction_clinic_comment_clinic_id` FOREIGN KEY (`clinic_id`) REFERENCES `self_introduction_clinic` (`clinic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_self_introductiond_clinic_comment_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `self_introduction_snapshot`
--

DROP TABLE IF EXISTS `self_introduction_snapshot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `self_introduction_snapshot` (
  `snapshot_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL,
  `title` varchar(45) NOT NULL,
  `growth` tinytext NOT NULL,
  `strength_and_weakness` tinytext NOT NULL,
  `school_life` tinytext NOT NULL,
  `motivation_and_aspiration` tinytext NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `modified_date` datetime(3) NOT NULL,
  PRIMARY KEY (`snapshot_id`),
  KEY `FK_self_introduction_user_user_id0` (`user_id`),
  CONSTRAINT `FK_self_introduction_user_user_id0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` char(32) NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `un_confirmed_user`
--

DROP TABLE IF EXISTS `un_confirmed_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `un_confirmed_user` (
  `user_id` char(32) NOT NULL,
  `user_code` char(32) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `birth_day` int(2) NOT NULL,
  `birth_month` int(2) NOT NULL,
  `birth_year` int(4) NOT NULL,
  `post_number` varchar(45) NOT NULL,
  `base_addr` varchar(45) NOT NULL,
  `detail_addr` varchar(45) NOT NULL,
  `bio` varchar(100) NOT NULL,
  `profile_img_src` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_un_confirmed_user_user_code_idx` (`user_code`),
  CONSTRAINT `FK_un_confirmed_user_user_code` FOREIGN KEY (`user_code`) REFERENCES `user_code_tbl` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` char(32) NOT NULL,
  `email` varchar(45) NOT NULL,
  `email_open` tinyint(4) NOT NULL DEFAULT '1',
  `username` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(45) NOT NULL,
  `stu_num` int(5) NOT NULL,
  `sex` enum('MAN','WOMAN') NOT NULL,
  `phone` varchar(13) NOT NULL,
  `phone_open` tinyint(4) NOT NULL DEFAULT '1',
  `birth_day` int(2) NOT NULL,
  `birth_month` int(2) NOT NULL,
  `birth_year` int(4) NOT NULL,
  `post_number` varchar(45) NOT NULL,
  `base_addr` varchar(45) NOT NULL,
  `detail_addr` varchar(45) NOT NULL,
  `bio` varchar(100) NOT NULL,
  `desired_min_sal` int(11) DEFAULT NULL,
  `desired_max_sal` int(11) DEFAULT NULL,
  `profile_img_src` varchar(60) DEFAULT NULL,
  `created_date` datetime(3) NOT NULL,
  `modified_date` datetime(3) NOT NULL,
  `last_password_reset_date` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `stu_num_UNIQUE` (`stu_num`),
  UNIQUE KEY `profile_img_src_UNIQUE` (`profile_img_src`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_certificate`
--

DROP TABLE IF EXISTS `user_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_certificate` (
  `certificate_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL,
  `kind` enum('CERTIFICATE','PRIZE','EXTERNAL_ACTIVITY','EXAM') NOT NULL,
  `name` varchar(45) NOT NULL,
  `agency` varchar(50) NOT NULL,
  `result` varchar(45) NOT NULL,
  `granted_date` date NOT NULL,
  `created_date` datetime(3) NOT NULL,
  `modified_date` datetime(3) NOT NULL,
  PRIMARY KEY (`certificate_id`),
  KEY `FK_user_certificate_user_user_id_idx` (`user_id`),
  CONSTRAINT `FK_user_certificate_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_code_tbl`
--

DROP TABLE IF EXISTS `user_code_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_code_tbl` (
  `user_code` char(32) NOT NULL,
  `name` varchar(45) NOT NULL,
  `stu_num` int(5) NOT NULL,
  `sex` enum('MAN','WOMAN') NOT NULL,
  PRIMARY KEY (`user_code`),
  UNIQUE KEY `stu_num_UNIQUE` (`stu_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_major`
--

DROP TABLE IF EXISTS `user_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_major` (
  `user_id` char(32) NOT NULL,
  `major_name` varchar(45) NOT NULL,
  `created_date` datetime(3) NOT NULL,
  PRIMARY KEY (`user_id`,`major_name`),
  CONSTRAINT `FK_user_major_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_skill`
--

DROP TABLE IF EXISTS `user_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_skill` (
  `user_id` char(32) NOT NULL,
  `skill_name` varchar(45) NOT NULL,
  `created_date` datetime(3) NOT NULL,
  PRIMARY KEY (`user_id`,`skill_name`),
  CONSTRAINT `FK_user_skill_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-19 16:40:06
