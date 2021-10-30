
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

/*Table structure for table `shop_order` */

DROP TABLE IF EXISTS `shop_order`;

CREATE TABLE `shop_order` (
  `id` bigint(20) NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `pro_id` bigint(20) NOT NULL COMMENT '商品id',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `failure_time` bigint(20) NOT NULL COMMENT '最大失败时间(订单未支付)',
  `success_time` bigint(20) NOT NULL COMMENT '订单支付成功时间',
  `user_phone` int(11) DEFAULT NULL COMMENT '用户手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `status` int(1) DEFAULT NULL COMMENT '订单状态 未支付;支付成功未发货;已经发货;已经签收...',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电商用订单表';

/*Data for the table `shop_order` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;