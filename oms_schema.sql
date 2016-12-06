/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : oms_schema

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-12-05 16:33:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exception
-- ----------------------------
DROP TABLE IF EXISTS `exception`;
CREATE TABLE `exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oId` varchar(32) DEFAULT NULL,
  `channelOid` varchar(32) DEFAULT NULL,
  `orderStatus` varchar(16) DEFAULT NULL,
  `orderFrom` varchar(64) DEFAULT NULL,
  `exceptionType` varchar(32) DEFAULT NULL,
  `expceptionCause` varchar(255) DEFAULT NULL,
  `exceptionStatus` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyMan` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exception
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsNo` varchar(32) NOT NULL,
  `goodsName` varchar(50) DEFAULT NULL,
  `goodsVnum` int(16) DEFAULT NULL,
  `goodsPrice` decimal(16,2) DEFAULT NULL,
  `goodsRnum` int(16) DEFAULT NULL,
  `goodsTolnum` int(16) DEFAULT NULL,
  PRIMARY KEY (`goodsNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for inboundorder
-- ----------------------------
DROP TABLE IF EXISTS `inboundorder`;
CREATE TABLE `inboundorder` (
  `iid` int(32) NOT NULL AUTO_INCREMENT,
  `oId` varchar(32) DEFAULT NULL,
  `channelOid` varchar(32) DEFAULT NULL,
  `returnedId` varchar(32) DEFAULT NULL,
  `inboundState` varchar(32) DEFAULT NULL,
  `inboundId` varchar(32) DEFAULT NULL,
  `synchroState` tinyint(4) DEFAULT NULL,
  `warehouse` varchar(32) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyMan` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inboundorder
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oId` varchar(32) DEFAULT NULL,
  `channelOid` varchar(32) DEFAULT NULL,
  `orderStatus` varchar(16) DEFAULT NULL,
  `orderForm` varchar(50) DEFAULT NULL,
  `buyerId` varchar(16) DEFAULT NULL,
  `orderTime` datetime DEFAULT NULL,
  `baseStatus` varchar(10) DEFAULT NULL,
  `payStatus` varchar(10) DEFAULT NULL,
  `payStyle` varchar(10) DEFAULT NULL,
  `payTime` datetime DEFAULT NULL,
  `goodsTolprice` decimal(16,2) DEFAULT NULL,
  `discountPrice` double(16,0) DEFAULT NULL,
  `orderTolprice` decimal(16,2) DEFAULT NULL,
  `goodsWarehouse` varchar(16) DEFAULT NULL,
  `logisticsCompany` varchar(32) DEFAULT NULL,
  `logisticsId` varchar(32) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `receiverName` varchar(16) DEFAULT NULL,
  `receiverMobel` varchar(11) DEFAULT NULL,
  `receiverTelnum` varchar(15) DEFAULT NULL,
  `receiverProvince` varchar(32) DEFAULT NULL,
  `receiverCity` varchar(32) DEFAULT NULL,
  `receiverArea` varchar(32) DEFAULT NULL,
  `detailAddress` varchar(255) DEFAULT NULL,
  `zipCode` varchar(32) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyMan` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for outboundorder
-- ----------------------------
DROP TABLE IF EXISTS `outboundorder`;
CREATE TABLE `outboundorder` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `oId` varchar(32) DEFAULT NULL,
  `channelOid` varchar(32) DEFAULT NULL,
  `orderStatus` varchar(16) DEFAULT NULL,
  `warehouseObId` varchar(32) DEFAULT NULL,
  `outboundId` varchar(32) DEFAULT NULL,
  `outboundState` varchar(32) DEFAULT NULL,
  `synchroState` tinyint(4) DEFAULT NULL,
  `receiverName` varchar(16) DEFAULT NULL,
  `expressCompany` varchar(32) DEFAULT NULL,
  `expressId` varchar(32) DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyMan` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outboundorder
-- ----------------------------

-- ----------------------------
-- Table structure for refoundorder
-- ----------------------------
DROP TABLE IF EXISTS `refoundorder`;
CREATE TABLE `refoundorder` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `drawbackId` varchar(32) DEFAULT NULL,
  `drawbackMoney` decimal(16,2) DEFAULT NULL,
  `drawbackStatus` varchar(16) DEFAULT NULL,
  `returnedId` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyMan` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of refoundorder
-- ----------------------------

-- ----------------------------
-- Table structure for relationog
-- ----------------------------
DROP TABLE IF EXISTS `relationog`;
CREATE TABLE `relationog` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `oId` varchar(32) DEFAULT NULL,
  `goodsNo` varchar(32) DEFAULT NULL,
  `goodNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relationog
-- ----------------------------

-- ----------------------------
-- Table structure for relationrg
-- ----------------------------
DROP TABLE IF EXISTS `relationrg`;
CREATE TABLE `relationrg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `returnedId` varchar(32) DEFAULT NULL,
  `goodsNo` varchar(32) DEFAULT NULL,
  `goodNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relationrg
-- ----------------------------

-- ----------------------------
-- Table structure for returnedorder
-- ----------------------------
DROP TABLE IF EXISTS `returnedorder`;
CREATE TABLE `returnedorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `returnedId` varchar(32) DEFAULT NULL,
  `returnedOrChange` varchar(16) DEFAULT NULL,
  `returnedStatus` varchar(16) DEFAULT NULL,
  `oId` varchar(32) DEFAULT NULL,
  `channelOid` varchar(32) DEFAULT NULL,
  `returnedMoney` decimal(16,2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifyMan` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of returnedorder
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(10) NOT NULL,
  `uPassword` varchar(10) NOT NULL,
  `uRole` varchar(10) NOT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
