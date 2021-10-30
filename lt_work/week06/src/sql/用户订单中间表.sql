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

/*Table structure for table `shop_user_order_middle` */

DROP TABLE IF EXISTS `shop_user_order_middle`;

CREATE TABLE `shop_user_order_middle` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `status` int(1) DEFAULT NULL COMMENT '订单状态 支付成功未发货;支付成功已发货;支付成功已签收;退货',
  `reason` varchar(255) DEFAULT NULL COMMENT '如果退货 理由',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户订单中间表 用后删除';

/*Data for the table `shop_user_order_middle` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
