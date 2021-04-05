-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.18 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных mobile
CREATE DATABASE IF NOT EXISTS `mobile` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mobile`;

-- Дамп структуры для таблица mobile.accounting
CREATE TABLE IF NOT EXISTS `accounting` (
  `id_accounting` int(11) NOT NULL AUTO_INCREMENT,
  `vendercode` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL,
  `amount` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_accounting`),
  KEY `vendercode` (`vendercode`),
  CONSTRAINT `FK_accounting_presence` FOREIGN KEY (`vendercode`) REFERENCES `presence` (`vendercode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.accounting: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `accounting` DISABLE KEYS */;
INSERT INTO `accounting` (`id_accounting`, `vendercode`, `date`, `amount`) VALUES
	(1, '1015', '27-11-2020', 1),
	(2, '6735', '27-11-2020', 2);
/*!40000 ALTER TABLE `accounting` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.brand
CREATE TABLE IF NOT EXISTS `brand` (
  `id_brand` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  PRIMARY KEY (`id_brand`),
  UNIQUE KEY `brand1` (`brand`),
  KEY `brand` (`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.brand: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` (`id_brand`, `brand`) VALUES
	(2, 'ASUS'),
	(3, 'Huawei'),
	(1, 'iPhone'),
	(4, 'Lenovo'),
	(5, 'Meizu');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.goods
CREATE TABLE IF NOT EXISTS `goods` (
  `id_goods` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `year` int(11) NOT NULL,
  `os` varchar(50) NOT NULL,
  `vendercode` varchar(50) NOT NULL,
  `cost` double NOT NULL,
  PRIMARY KEY (`id_goods`),
  UNIQUE KEY `vendercode1` (`vendercode`),
  KEY `brand` (`brand`),
  KEY `year` (`year`),
  KEY `os` (`os`),
  KEY `vendercode` (`vendercode`),
  CONSTRAINT `FK_goods_brand` FOREIGN KEY (`brand`) REFERENCES `brand` (`brand`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_goods_os` FOREIGN KEY (`os`) REFERENCES `os` (`os`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_goods_year` FOREIGN KEY (`year`) REFERENCES `year` (`year`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.goods: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` (`id_goods`, `brand`, `model`, `type`, `year`, `os`, `vendercode`, `cost`) VALUES
	(3, 'iPhone', 'XR', 'Смартфон', 2020, 'iOS 11', '1015', 3999),
	(5, 'Huawei', 'P40', 'Смартфон', 2020, 'Android 11.1', '6735', 1949);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.os
CREATE TABLE IF NOT EXISTS `os` (
  `id_os` int(11) NOT NULL AUTO_INCREMENT,
  `os` varchar(50) NOT NULL,
  PRIMARY KEY (`id_os`),
  UNIQUE KEY `os1` (`os`),
  KEY `os` (`os`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.os: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `os` DISABLE KEYS */;
INSERT INTO `os` (`id_os`, `os`) VALUES
	(2, 'Android 10.0'),
	(7, 'Android 11.1'),
	(1, 'iOS 11');
/*!40000 ALTER TABLE `os` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.presence
CREATE TABLE IF NOT EXISTS `presence` (
  `id_presence` int(11) NOT NULL AUTO_INCREMENT,
  `vendercode` varchar(50) NOT NULL,
  `amount` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_presence`),
  UNIQUE KEY `vendercode` (`vendercode`),
  KEY `vendercode1` (`vendercode`),
  CONSTRAINT `FK_presence_goods` FOREIGN KEY (`vendercode`) REFERENCES `goods` (`vendercode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.presence: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `presence` DISABLE KEYS */;
INSERT INTO `presence` (`id_presence`, `vendercode`, `amount`) VALUES
	(1, '1015', 28),
	(2, '6735', 18);
/*!40000 ALTER TABLE `presence` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.role
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id_role`),
  KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.role: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id_role`, `role`) VALUES
	(1, 'Администратор'),
	(2, 'Пользователь');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login` (`login`),
  KEY `role` (`role`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role`) REFERENCES `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.user: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id_user`, `name`, `surname`, `login`, `password`, `role`) VALUES
	(1, 'qwe', 'qwe', 'qwe', 'qwe', 'Администратор'),
	(2, 'asd', 'asd', 'asd', 'asd', 'Пользователь'),
	(3, 'Admin', 'Admin', 'Admin', 'Admin', 'Администратор');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Дамп структуры для таблица mobile.year
CREATE TABLE IF NOT EXISTS `year` (
  `id_year` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id_year`),
  UNIQUE KEY `year1` (`year`),
  KEY `year` (`year`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Дамп данных таблицы mobile.year: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `year` DISABLE KEYS */;
INSERT INTO `year` (`id_year`, `year`) VALUES
	(4, 2017),
	(3, 2018),
	(2, 2019),
	(1, 2020);
/*!40000 ALTER TABLE `year` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
