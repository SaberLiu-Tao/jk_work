/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.35-log : Database - liutao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`liutao` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `liutao`;

/*Table structure for table `shop_user` */

DROP TABLE IF EXISTS `shop_user`;

CREATE TABLE `shop_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `gender` int(1) DEFAULT NULL COMMENT '用户性别',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone_num` int(11) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `active_flag` int(1) DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `shop_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
