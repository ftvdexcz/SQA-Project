CREATE DATABASE  IF NOT EXISTS `water_billing_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `water_billing_management`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: water_billing_management
-- ------------------------------------------------------
-- Server version	8.0.31

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

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` varchar(12) NOT NULL,
  `address` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clientUser` (`user_id`),
  CONSTRAINT `FK_clientUser` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('KH0Nv2jDP89Q','642 Khương Trung, Hà Nội',203),('KH0XHuxdU7QH','254 Võ Chí Công, Hà Nội',167),('KH1aRBSFTOv2','656 Thái Hà, Hà Nội',260),('KH1KSpbAyvFU','818 Chùa Bộc, Hà Nội',195),('KH1tgbwWkGvB','647 Bạch Mai, Hà Nội',290),('KH2KbwUvqJ3w','50 Khương Thượng, Hà Nội',247),('KH2OzFTCcLrS','48 Thái Hà, Hà Nội',232),('KH2QeupioXGi','497 Láng Hạ, Hà Nội',303),('KH3eI7DaTLwH','356 Tôn Thất Tùng, Hà Nội',254),('KH3jiLBZ9oBK','1000 Bạch Mai, Hà Nội',196),('KH4jMavmQqEk','878 Láng Hạ, Hà Nội',294),('KH5FKEk35hyY','869 Bạch Mai, Hà Nội',252),('KH5G9WSgSBDS','193 Láng Hạ, Hà Nội',280),('KH5NsBQ5BsMo','95 Khương Trung, Hà Nội',299),('KH5qVe21AYrH','420 Trường Chinh, Hà Nội',191),('KH5SMjxlA3UX','783 Lê Duẩn, Hà Nội',257),('KH6gKPUvvd1I','826 Trường Chinh, Hà Nội',261),('KH6If8DgFq4q','97 Khương Trung, Hà Nội',215),('KH6ToikTgs8q','22 Võ Chí Công, Hà Nội',244),('KH73cxQOrg1D','157 Thái Hà, Hà Nội',228),('KH7JQLaYt418','210 Giải Phóng, Hà Nội',284),('KH7la7NMtMno','548 Láng Hạ, Hà Nội',273),('KH7OB1ZCo1zg','455 Giải Phóng, Hà Nội',229),('KH8ghXiUTWIz','576 Hoàng Mai, Hà Nội',224),('KH8iFqXSl8gw','897 Xã Đàn, Hà Nội',170),('KH8sXhNqloFT','866 Láng Hạ, Hà Nội',295),('KH8z5TLWLP2W','507 Đại La, Hà Nội',289),('KH9CAkx1YOuU','491 Thái Hà, Hà Nội',259),('KH9YLYzhOAAP','601 Khương Trung, Hà Nội',190),('KH9yYiygBuRY','848 Trường Chinh, Hà Nội',197),('KHAHjdiLhUhT','161 Chùa Bộc, Hà Nội',242),('KHar59iKDAm4','743 Lò Đúc, Hà Nội',268),('KHAxR7bNNILt','858 Chùa Bộc, Hà Nội',255),('KHBA3vTLeBnZ','164 Chùa Bộc, Hà Nội',202),('KHbd78kJdq86','923 Hoàng Mai, Hà Nội',296),('KHbMwubn4udV','825 Đại La, Hà Nội',240),('KHbr55tUYojr','335 Võ Chí Công, Hà Nội',177),('KHbrgk9ZZO1T','267 Khương Trung, Hà Nội',307),('KHBvgZNrPQhl','859 Tôn Thất Tùng, Hà Nội',249),('KHcnxDhC2rSL','117 Tôn Thất Tùng, Hà Nội',231),('KHcXNxuwslu4','388 Láng Hạ, Hà Nội',239),('KHcZtOHyO3BO','519 Bạch Mai, Hà Nội',222),('KHd1ZrtzJr3k','872 Khương Thượng, Hà Nội',282),('KHD3gXm15ku7','24 Bạch Mai, Hà Nội',278),('KHd5F0EHutBt','117 Khương Thượng, Hà Nội',243),('KHdshocFqi7F','476 Lê Duẩn, Hà Nội',182),('KHDtG7ezgduD','107 Thái Hà, Hà Nội',256),('KHent1sRl687','748 Hoàng Mai, Hà Nội',237),('KHEwOuZqDO8Y','774 Chùa Bộc, Hà Nội',176),('KHF9iC1KMFoP','862 Giải Phóng, Hà Nội',269),('KHFsWeV2KeJf','929 Lò Đúc, Hà Nội',199),('KHFtmmsoqvli','677 Lò Đúc, Hà Nội',264),('KHGSZtpQpr0x','667 Giải Phóng, Hà Nội',265),('KHgYXi1TJuYE','755 Xã Đàn, Hà Nội',272),('KHgzB0MQjeAp','249 Chùa Bộc, Hà Nội',216),('KHHdWExyJmvt','289 Lê Duẩn, Hà Nội',292),('KHhH717E92Z3','955 Bạch Mai, Hà Nội',174),('KHI7yYJqqTJw','654 Khương Trung, Hà Nội',189),('KHiAXdARS8wX','887 Đại La, Hà Nội',186),('KHIBRTkLdA1f','883 Xã Đàn, Hà Nội',275),('KHIlwRHav8KU','929 Trường Chinh, Hà Nội',181),('KHIvHRK4SS7p','401 Đại La, Hà Nội',293),('KHjBcbdQkvR7','852 Khương Thượng, Hà Nội',288),('KHJdeQoPrWpJ','926 Lò Đúc, Hà Nội',277),('KHjLUcsl8ami','258 Hoàng Mai, Hà Nội',179),('KHjzOu2AEEpy','824 Xã Đàn, Hà Nội',217),('KHKGx4OXPs0k','260 Đại La, Hà Nội',220),('KHkMrKB1VMqW','524 Láng Hạ, Hà Nội',210),('KHKqitsSWXoj','260 Trường Chinh, Hà Nội',214),('KHkTKJdsXkQq','783 Lò Đúc, Hà Nội',304),('KHKw19ZCPfdy','257 Đại La, Hà Nội',233),('KHL1e6sZ1tna','744 Giải Phóng, Hà Nội',225),('KHl6XWu5Yxc0','496 Chùa Bộc, Hà Nội',235),('KHL8YpqndKOE','124 Láng Hạ, Hà Nội',300),('KHlftZdfgR83','772 Khương Trung, Hà Nội',285),('KHlLGRfZxQCh','539 Láng Hạ, Hà Nội',185),('KHlllCZP7nMc','711 Giải Phóng, Hà Nội',169),('KHLTL9Tbl9AA','448 Khương Thượng, Hà Nội',274),('KHlu4x1VGIkH','124 Chùa Bộc, Hà Nội',219),('KHMLaCpjJa4l','652 Hoàng Mai, Hà Nội',234),('KHMvxZ2F9tQ5','429 Trường Chinh, Hà Nội',238),('KHn01VTZpA7m','951 Khương Thượng, Hà Nội',218),('KHnbkK14h1j5','650 Xã Đàn, Hà Nội',212),('KHNjfGMwi8DA','728 Chùa Bộc, Hà Nội',271),('KHnmpmyniEYH','337 Tôn Thất Tùng, Hà Nội',283),('KHNtCKzM2WeX','926 Tôn Thất Tùng, Hà Nội',209),('KHoaATj3IOTU','152 Tôn Thất Tùng, Hà Nội',291),('KHoqlfFC15yh','457 Thái Hà, Hà Nội',267),('KHoZvFutGOra','359 Chùa Bộc, Hà Nội',173),('KHp4kYaJVoRj','457 Khương Thượng, Hà Nội',171),('KHPbedc7gzZd','274 Trường Chinh, Hà Nội',258),('KHpDrDHYgjAk','355 Khương Thượng, Hà Nội',253),('KHpielYjhmyA','111 Thái Hà, Hà Nội',184),('KHplEKGeOVy5','872 Trường Chinh, Hà Nội',301),('KHpVQTtiyZkn','461 Xã Đàn, Hà Nội',211),('KHq3EMb6BpuB','764 Xã Đàn, Hà Nội',226),('KHq51zGVfDF2','709 Xã Đàn, Hà Nội',266),('KHqaH3jGvHNe','190 Giải Phóng, Hà Nội',207),('KHqq4UkZqMR2','26 Tôn Thất Tùng, Hà Nội',287),('KHQQXPMkuCLV','462 Trường Chinh, Hà Nội',270),('KHqS4jHEKJ5y','116 Chùa Bộc, Hà Nội',223),('KHQXHaHUga3Q','783 Lê Duẩn, Hà Nội',263),('KHQzAWrEhNfD','403 Trường Chinh, Hà Nội',281),('KHr3oyhGxAhk','336 Láng Hạ, Hà Nội',236),('KHRDktqgpVaL','876 Chùa Bộc, Hà Nội',297),('KHRrtqb2Q2xE','508 Lò Đúc, Hà Nội',198),('KHRsDbr3N44H','722 Xã Đàn, Hà Nội',251),('KHRsTmpSGaIQ','726 Đại La, Hà Nội',168),('KHruzuQBxq62','923 Hoàng Mai, Hà Nội',302),('KHsJFmFGDQON','197 Thái Hà, Hà Nội',200),('KHsoqXYpLvyy','213 Hoàng Mai, Hà Nội',187),('KHSqZO3DNKDe','974 Lò Đúc, Hà Nội',309),('KHsrj9S77Smz','995 Hoàng Mai, Hà Nội',208),('KHsw7IwqXDb2','720 Tôn Thất Tùng, Hà Nội',230),('KHSYVDha2Q1i','959 Lê Duẩn, Hà Nội',213),('KHT3dqHuwGQP','148 Đại La, Hà Nội',204),('KHtC4tHHqY3h','456 Lò Đúc, Hà Nội',175),('KHtHJFnSfooV','758 Võ Chí Công, Hà Nội',246),('KHtOgzQxD5yl','93 Đại La, Hà Nội',194),('KHU8TbbJ7Fyj','791 Lê Duẩn, Hà Nội',183),('KHU9RhmbluNc','720 Xã Đàn, Hà Nội',262),('KHuDCgr5VZiI','306 Đại La, Hà Nội',286),('KHvBEx79j0nv','552 Thái Hà, Hà Nội',305),('KHVczGAadIGT','945 Láng Hạ, Hà Nội',172),('KHvRvyJvp3Kw','833 Khương Trung, Hà Nội',250),('KHVSBzQ5tjW4','262 Thái Hà, Hà Nội',205),('KHw5kOiHPMZm','302 Khương Trung, Hà Nội',221),('KHwhIdqmHfGe','684 Đại La, Hà Nội',206),('KHWHpNVlh3bS','697 Chùa Bộc, Hà Nội',276),('KHWTd1APGP52','907 Giải Phóng, Hà Nội',310),('KHx10uxnFNIP','577 Trường Chinh, Hà Nội',241),('KHx3Ibl9tiE0','654 Trường Chinh, Hà Nội',248),('KHx8ACCGVG3d','95 Tôn Thất Tùng, Hà Nội',227),('KHxa8SlKjevg','992 Hoàng Mai, Hà Nội',192),('KHXdpOOg9vvx','426 Lò Đúc, Hà Nội',180),('KHxhsGA6iWkc','12 Lò Đúc, Hà Nội',193),('KHyBPmBusKNg','734 Lê Duẩn, Hà Nội',279),('KHyjKQpCAfxa','527 Khương Thượng, Hà Nội',178),('KHyMkn6r5QIP','97 Xã Đàn, Hà Nội',245),('KHYXuIYcsOIo','336 Võ Chí Công, Hà Nội',298),('KHyZ5SWm7t0A','184 Chùa Bộc, Hà Nội',306),('KHz3aYgWm9Th','328 Tôn Thất Tùng, Hà Nội',308),('KHZKHAcDEc86','138 Đại La, Hà Nội',201),('KHzOS3tnGNii','571 Bạch Mai, Hà Nội',188);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-19 12:58:23
