/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : aries

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2021-08-13 14:02:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permissionid` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) NOT NULL DEFAULT '0',
  `menu` varchar(255) DEFAULT NULL,
  `menuName` varchar(255) DEFAULT NULL,
  `menuBaseUrl` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `menuIcon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permissionid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '0', 'admin', 'admin全部权限', '/', '2021-08-06 16:12:22', '2021-08-06 16:19:31', null);
INSERT INTO `permission` VALUES ('2', '0', 'dashboard', '首页管理', '/home', '2021-08-09 12:43:34', '2021-08-10 15:19:09', 'el-icon-setting');
INSERT INTO `permission` VALUES ('3', '0', 'permission', '权限管理', '/permission', '2021-08-06 16:21:42', '2021-08-10 15:20:25', 'el-icon-document-copy');
INSERT INTO `permission` VALUES ('4', '3', 'permissionRoles', '角色管理', '/permission/Roles', '2021-08-09 12:41:40', '2021-08-10 13:37:50', null);
INSERT INTO `permission` VALUES ('5', '3', 'permissionManager', '添加用户', '/permission/users', '2021-08-09 12:34:31', '2021-08-10 13:37:54', null);
INSERT INTO `permission` VALUES ('6', '2', 'homeDashboard', '首页工作台', '/home/dashboard', '2021-08-10 10:46:08', '2021-08-10 11:07:37', null);
INSERT INTO `permission` VALUES ('7', '3', 'permissionMenusAdmin', '菜单管理', '/permission/menusAdmin', '2021-08-10 11:22:43', '2021-08-10 13:38:09', null);
INSERT INTO `permission` VALUES ('16', '0', 'users', '会员管理', '/users', '2021-08-11 17:20:57', '2021-08-13 13:44:34', 'el-icon-user');
INSERT INTO `permission` VALUES ('18', '16', 'usersInfo', '用户信息 ', '/usrs/usersInfo', '2021-08-13 13:44:23', '2021-08-13 14:02:07', '');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL,
  `stauts` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'admin', '1', '2021-08-06 16:00:14', '2021-08-06 16:00:14');
INSERT INTO `roles` VALUES ('5', '普通用户', '1', '2021-08-12 12:39:44', '2021-08-12 13:14:30');

-- ----------------------------
-- Table structure for rolespermission
-- ----------------------------
DROP TABLE IF EXISTS `rolespermission`;
CREATE TABLE `rolespermission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolespermission
-- ----------------------------
INSERT INTO `rolespermission` VALUES ('1', '1', '1');
INSERT INTO `rolespermission` VALUES ('17', '5', '2');
INSERT INTO `rolespermission` VALUES ('18', '5', '16');

-- ----------------------------
-- Table structure for usermanager
-- ----------------------------
DROP TABLE IF EXISTS `usermanager`;
CREATE TABLE `usermanager` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `stauts` int(11) DEFAULT '1',
  `version` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `phoneUnique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usermanager
-- ----------------------------
INSERT INTO `usermanager` VALUES ('1', 'kim', '18501990429', 'b829e96300d040e9017702f89a26f947', 'https://thirdwx.qlogo.cn/mmopen/vi_32/NXoTIWG0iaT4VgibeHgprMYic8icqZkVWIpWQW12dKRcG2gUXvnT5BfsVfrA3oexIM8f6lnGP0RZRus2rGtChAySqQ/132', '2456394723@qq.com', '1', '1', '2021-08-06 15:59:24', '2021-08-13 13:59:55');
INSERT INTO `usermanager` VALUES ('4', '金望', '17780306283', '14e1b600b1fd579f47433b88e8d85291', 'https://thirdwx.qlogo.cn/mmopen/vi_32/NXoTIWG0iaT4VgibeHgprMYic8icqZkVWIpWQW12dKRcG2gUXvnT5BfsVfrA3oexIM8f6lnGP0RZRus2rGtChAySqQ/132', null, '1', '1', '2021-08-13 10:25:48', '2021-08-13 13:34:38');

-- ----------------------------
-- Table structure for userroles
-- ----------------------------
DROP TABLE IF EXISTS `userroles`;
CREATE TABLE `userroles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userroles
-- ----------------------------
INSERT INTO `userroles` VALUES ('8', '1', '1');
INSERT INTO `userroles` VALUES ('14', '4', '1');
