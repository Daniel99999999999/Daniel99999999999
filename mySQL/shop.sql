-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.29

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
  `id` varchar(255) NOT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_client_LastName` (`LastName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('1','Kowalski','Agrestowa 1,Kielce','kowalski@mail.pl','Jan','kowalski'),('10','Zagajny','Jesionowa 10,Warszawa','zagajny@mail.pl','Bogdan','zagajny'),('2','Nowak','Boczna 2,Warszawa','nowak@mail.pl','Piotr','nowak'),('3','Brzeczyszczykiewicz','Cicha 3, Gdynia','grzesiu@mail.pl','Grzegorz','brzeczyszczykiewicz'),('4','Kowalczyk','Mickiewicza 4,Opole','kowalczyk@mail.pl','Adam','kowalczyk'),('5','Krawczyk','Rynek 5, Szczecin','krawczyk@mail.pl','Alina','krawczyk'),('6','Boczek','Cwiartki 3/6,Wroclaw','boczek@mail.pl','Arnold','boczek'),('7','Ochodzki','Teczowa 7,Warszawa','rysiek@mail.pl','Ryszard','ochodzki'),('8','Jarzabek','Warszawska 8,Warszawa','lubudubu@mail.pl','Waclaw','jarzabek'),('9','Dyrman','Spacerowa 9, Warszawa','dyrman@mail.pl','Zdzislaw','dyrman');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1','100','Mikrokontroler STM32F103RBT6 ARM Cortex-M3 LQFP64','STM32F103RBT6','40',NULL),('10','16','Czujnik temperatury DS18B20 onewire','DS18B20','20',NULL),('11','98','Zegar czasu rzeczywistego RTC DS1307 I2C TWI','DS1307','6',NULL),('12','74','Potencjometr cyfrowy DS1804-10  10k Ohm I2C','DS1804','20',NULL),('13','77','ICL7667 DIP8 Intersil 2x fast driver mosfet 1,5A','ICL7667','2',NULL),('14','200','ZESTAW REZYSTORÓW SMD 1206 - 1700 sztuk','SMD1206','50',NULL),('15','99','ZESTAW REZYSTORÓW SMD 0805 - 1700 sztuk','SMD0805','60',NULL),('16','65','ZESTAW REZYSTORÓW SMD 0603 - 1700 sztuk','SMD0603','60',NULL),('17','52','Zestaw diod LED SMD 0805 - 30szt','LED0805','7',NULL),('18','70','Kondensatory SMD 1206 - 120 szt','C1206','18',NULL),('19','5','Topnik Flux RMA 223 10ccm','RMA223','15',NULL),('2','15','Mikrokontroler AVR ATmega328P-AU SMD','ATmega328P','17',NULL),('20','23','Alkohol izopropylowy IPA 100ml','IPA','7',NULL),('21','88','CYNA DO LUTOWANIA 0,80MM 50G SN45%, PB55%','Cyna','13',NULL),('22','16','Plecionka do cyny ZD-180 1 mm x 1,5 m','ZD-180','6',NULL),('23','41','Tranzystor IRFZ44N N-MOSFET 55V 49A TO-220','IRFZ44N','2',NULL),('24','90','Tranzystor P-MOSFET IRF9540 TO-220','IRF9540','21',NULL),('25','999','Tranzystor BSS138 SMD N-MOSFET SOT23 10 SZT','BSS138','2',NULL),('26','14','Dioda LED LE2835A 6000K 5 sztuk','LE2835A','2',NULL),('27','1000','Tranzystor BC547 TO-92 NPN 45V 0.1A 5 sztuk','BC547','4',NULL),('28','900','Tranzystor PNP 65V/100mA BC556B 10 sztuk','BC556','2',NULL),('29','199','Dioda 1N4148 - 150mA/100V  50sztuk','1N4148 ','1',NULL),('3','0','Wyswietlacz LCD 2×16 znakow zielony HD44780','HD44780','10',''),('30','600','Wzmacniacz operacyjny TL072 SO-08 - 2 sztuki','TL072','2',NULL),('4','54','Wyswietlacz dotykowy TFT LCD 2,4″ 240x320px z czytnikiem SD SPI','TFT LCD 2,4','55',NULL),('5','5','Zestaw uruchomieniowy Arduino Atmega2560 Rev.3','Arduino MEGA 2560','50',NULL),('6','2','Zestaw uruchomieniowy STM32 NUCLEO-F411RE mbed ARM Cortex-M4','NUCLEO-F411RE','100',NULL),('7','6','Programator AVR ATB USBASP 4.2','ATB-USBASP','30',NULL),('8','1','Konwerter ATB-USB-RS232','ATB-USB-RS232','80',NULL),('9','4','Modul WiFi ESP8266-01S','ESP8266-01S','11',NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_shopping`
--

DROP TABLE IF EXISTS `product_shopping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_shopping` (
  `s_id` varchar(255) NOT NULL,
  `p_id` varchar(255) NOT NULL,
  KEY `FK7up491cvddr2k2vx2c5l98kce` (`p_id`),
  KEY `FKrgkdpswq5fbn6dnd68ya5wgis` (`s_id`),
  CONSTRAINT `FK7up491cvddr2k2vx2c5l98kce` FOREIGN KEY (`p_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKrgkdpswq5fbn6dnd68ya5wgis` FOREIGN KEY (`s_id`) REFERENCES `shopping_cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_shopping`
--

LOCK TABLES `product_shopping` WRITE;
/*!40000 ALTER TABLE `product_shopping` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_shopping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlegqpf0apoematndm58vxssri` (`client_id`),
  CONSTRAINT `FKlegqpf0apoematndm58vxssri` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-28 17:20:56
