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

/*Table structure for table `shop_product` */

DROP TABLE IF EXISTS `shop_product`;

CREATE TABLE `shop_product` (
  `pro_id` bigint(20) NOT NULL COMMENT '商品id',
  `pro_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `pro_price` varchar(255) DEFAULT NULL COMMENT '价格',
  `pro_icon_url` varchar(255) DEFAULT NULL COMMENT '展示图标地址',
  `pyq_url` varchar(255) DEFAULT NULL COMMENT '朋友圈转发图',
  `forward_url` varchar(255) DEFAULT NULL COMMENT '转发图',
  `detail_url` varchar(255) DEFAULT NULL COMMENT '详情展示图',
  `count` bigint(20) DEFAULT NULL COMMENT '库存',
  `status` int(1) DEFAULT NULL COMMENT '商品现在状态 正常或者其他状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '商品上下架',
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电商用商品表';

/*Data for the table `shop_product` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
