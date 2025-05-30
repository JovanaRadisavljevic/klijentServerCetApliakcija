/*
SQLyog Community v13.1.8 (64 bit)
MySQL - 10.4.32-MariaDB : Database - cet
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `cet`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) DEFAULT NULL,
  `lozinka` varchar(30) DEFAULT NULL,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `admin` */

insert  into `admin`(`id`,`email`,`lozinka`,`ime`,`prezime`) values 
(1,'jovan@gmail.com','jovan','Jovan','Jovanovic'),
(2,'nikola@gmail.com','nikola','Nikola','Nikolic');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `lozinka` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`id`,`username`,`lozinka`) values 
(1,'jovan123','jovan'),
(2,'nina','nina'),
(3,'sasa','sasa'),
(4,'filip','filip'),
(5,'pera','pera'),
(6,'marta','marta'),
(7,'nikola','nikola234');

/*Table structure for table `poruka` */

DROP TABLE IF EXISTS `poruka`;

CREATE TABLE `poruka` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `posiljalac` bigint(20) DEFAULT NULL,
  `primalac` bigint(20) DEFAULT NULL,
  `tekst` text DEFAULT NULL,
  `datum` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `posiljalac` (`posiljalac`),
  KEY `primalac` (`primalac`),
  CONSTRAINT `poruka_ibfk_1` FOREIGN KEY (`posiljalac`) REFERENCES `korisnik` (`id`),
  CONSTRAINT `poruka_ibfk_2` FOREIGN KEY (`primalac`) REFERENCES `korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `poruka` */

insert  into `poruka`(`id`,`posiljalac`,`primalac`,`tekst`,`datum`) values 
(2,3,1,'Ako imaš više sličnih problema — javi slobodno, mogu pomoći da zajedno pregledamo sve korake.','2025-05-08 13:33:33'),
(3,5,2,'Ej, jesi stigao kući? Javi mi kad budeš slobodan.\r\n\r\n','2025-05-06 14:10:45'),
(4,2,4,'Ne mogu da verujem šta se danas desilo na faksu!','2025-05-03 14:10:47'),
(5,1,3,'Aj da se vidimo sutra oko pet, ako ti to odgovara?','2025-10-18 14:11:06'),
(6,6,3,'Jesi gledao onu seriju što sam ti preporučila?','2025-05-09 14:11:25'),
(7,7,6,'Kad stigneš, pošalji mi slike s vikenda, jedva čekam!','2025-05-01 14:11:47'),
(8,3,2,'Ne znam kako da ti objasnim koliko mi je to važno.','2025-05-13 14:12:05'),
(9,1,5,'Ova metoda će raditi jer korisnik najčešće ima fokus na tabeli, pa Enter tu ima smisla.','2025-05-08 13:33:33'),
(10,1,1,'cao svima kako ste','2025-05-28 14:46:14'),
(11,1,2,'cao svima kako ste','2025-05-28 14:46:14'),
(12,1,3,'cao svima kako ste','2025-05-28 14:46:14'),
(13,1,4,'cao svima kako ste','2025-05-28 14:46:14'),
(14,1,5,'cao svima kako ste','2025-05-28 14:46:14'),
(15,1,6,'cao svima kako ste','2025-05-28 14:46:14'),
(16,1,7,'cao svima kako ste','2025-05-28 14:46:14'),
(17,2,1,'nina ovde kako ste','2025-05-28 14:49:06'),
(18,2,3,'nina ovde kako ste','2025-05-28 14:49:06'),
(19,2,4,'nina ovde kako ste','2025-05-28 14:49:06'),
(20,2,5,'nina ovde kako ste','2025-05-28 14:49:06'),
(21,2,6,'nina ovde kako ste','2025-05-28 14:49:06'),
(22,2,7,'nina ovde kako ste','2025-05-28 14:49:06'),
(23,1,2,'cao nina kako si jovan ovde','2025-05-30 12:19:33'),
(24,2,1,'NINA ovde kako si ????','2025-05-30 12:54:31'),
(25,2,1,'konacno sam zavrsila ovaj projekat','2025-05-30 13:56:36');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
