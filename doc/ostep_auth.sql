/*
 Navicat MySQL Data Transfer

 Source Server         : ostep-local
 Source Server Type    : MySQL
 Source Server Version : 50550
 Source Host           : localhost
 Source Database       : ostep

 Target Server Type    : MySQL
 Target Server Version : 50550
 File Encoding         : utf-8

 Date: 07/24/2019 14:25:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ostep_role`
-- ----------------------------
DROP TABLE IF EXISTS `ostep_role`;
CREATE TABLE `ostep_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `ostep_user`
-- ----------------------------
DROP TABLE IF EXISTS `ostep_user`;
CREATE TABLE `ostep_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `user_status` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `ostep_user_open_id`
-- ----------------------------
DROP TABLE IF EXISTS `ostep_user_open_id`;
CREATE TABLE `ostep_user_open_id` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `account_type` int(11) DEFAULT NULL,
  `open_id` varchar(64) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `ostep_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `ostep_user_roles`;
CREATE TABLE `ostep_user_roles` (
  `user_id` int(11) DEFAULT NULL,
  `roles_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `ostep_user_verify_code`
-- ----------------------------
DROP TABLE IF EXISTS `ostep_user_verify_code`;
CREATE TABLE `ostep_user_verify_code` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `code_type` int(11) DEFAULT NULL,
  `code` varchar(15) DEFAULT NULL,
  `code_message` varchar(64) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `code_status` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
