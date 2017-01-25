/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : yshang_framwork

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-11-17 09:10:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_ou`
-- ----------------------------
DROP TABLE IF EXISTS `sys_ou`;
CREATE TABLE `sys_ou` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `is_root` int(11) DEFAULT '0' COMMENT '是否根节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_ou
-- ----------------------------
INSERT INTO `sys_ou` VALUES ('1', null, '英德监狱', '0', '2015-11-16 17:23:23', 'P', '1');
INSERT INTO `sys_ou` VALUES ('4028819a510ef9c401510effc1680000', '1', '11111', '0.1', '2015-11-16 17:23:26', 'P', '0');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '查询', 'list', null);
INSERT INTO `sys_permission` VALUES ('2', '编辑', 'update', null);
INSERT INTO `sys_permission` VALUES ('3', '删除', 'del', null);
INSERT INTO `sys_permission` VALUES ('4', '添加', 'add', null);
INSERT INTO `sys_permission` VALUES ('5', '授权', 'auth', '1');

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ico` varchar(100) DEFAULT NULL,
  `is_root` int(11) DEFAULT NULL,
  `is_show` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `parent_ids` varchar(200) DEFAULT NULL,
  `permission_ids` varchar(200) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sys_resource_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', null, '系统管理', 'sys', '/admin/sys/user', 'nav-supplier', '1', '1', '1', '1', null, null, null);
INSERT INTO `sys_resource` VALUES ('19', '8', '基础信息管理', null, '1', '', null, null, '1', '2', null, '', null);
INSERT INTO `sys_resource` VALUES ('2', '1', '用户与权限', '', null, '', null, null, null, '2', null, null, null);
INSERT INTO `sys_resource` VALUES ('20', '19', '菜单管理', null, '1', '', null, null, '1', '3', null, '1,2,3,4,5', null);
INSERT INTO `sys_resource` VALUES ('22', '1', '系统通用管理', null, '/admin/sys/common', '', null, null, '11', '2', null, '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('23', '19', '导航', null, '/admin/sys/user', null, null, null, '1', '3', null, '3,4', null);
INSERT INTO `sys_resource` VALUES ('4', '2', '用户管理', '/admin/sys/user', '/admin/sys/user', '', null, '1', '1', '3', '', '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('402880e8510e0aa301510e0ed3e40000', '2', '部门管理', '/admin/sys/ou', '/admin/sys/ou', null, null, null, '5', '3', null, '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('4028819a510ef9c401510f7d95240001', '2', 'test', '111', '', null, null, null, '1', '3', null, '', null);
INSERT INTO `sys_resource` VALUES ('5', '2', '权限管理', '/admin/sys/permission', '/admin/sys/permission', '', null, '1', '4', '3', null, '1,2,3', '1');
INSERT INTO `sys_resource` VALUES ('6', '2', '角色管理', '/admin/sys/role', '/admin/sys/role', '', null, null, '3', '3', null, '1,2,3,4,5', null);
INSERT INTO `sys_resource` VALUES ('7', '2', '资源管理', '/admin/sys/resource', '/admin/sys/resource', '', null, null, '2', '3', null, '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('8', null, '微信管理', '', null, 'nav-inventory', '1', '1', '2', '1', null, null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'Admin', '', null);
INSERT INTO `sys_role` VALUES ('12', '开发部', 'dev_dep', '公司的开发部们', null);
INSERT INTO `sys_role` VALUES ('2', '测试', 'Test', '1111eddd', null);

-- ----------------------------
-- Table structure for `sys_role_auth`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `resource_id` varchar(32) DEFAULT NULL,
  `permission_ids` longtext,
  PRIMARY KEY (`id`),
  KEY `FK_sys_role_auth_resource_id` (`resource_id`) USING BTREE,
  KEY `FK_sys_role_auth_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES ('275', '2', '4', '1,2');
INSERT INTO `sys_role_auth` VALUES ('276', '2', '7', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('277', '2', '6', '1,2,3');
INSERT INTO `sys_role_auth` VALUES ('278', '2', '20', '1,2,3,4,5');
INSERT INTO `sys_role_auth` VALUES ('279', '2', '1', '');
INSERT INTO `sys_role_auth` VALUES ('280', '2', '2', '');
INSERT INTO `sys_role_auth` VALUES ('281', '2', '8', '');
INSERT INTO `sys_role_auth` VALUES ('282', '2', '19', '');
INSERT INTO `sys_role_auth` VALUES ('364', '12', '6', '1,2,3,4,5');
INSERT INTO `sys_role_auth` VALUES ('365', '12', '5', '1,2,3');
INSERT INTO `sys_role_auth` VALUES ('366', '12', '1', '');
INSERT INTO `sys_role_auth` VALUES ('367', '12', '2', '');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74b9ff0000', '1', '1', '');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74b9ff0001', '1', '2', '');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74b9ff0002', '1', '4', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090003', '1', '7', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090004', '1', '6', '1,2,3,4,5');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090005', '1', '5', '1,2,3');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090006', '1', '402880e8510e0aa301510e0ed3e40000', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090007', '1', '22', '');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090008', '1', '8', '');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba090009', '1', '19', '');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba09000a', '1', '20', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('402880e8510e745e01510e74ba09000b', '1', '23', '3,4');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `creater_id` int(11) DEFAULT NULL,
  `login_name` varchar(100) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL COMMENT '1 正常 2 停用',
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_user_logname` (`login_name`) USING BTREE,
  KEY `FK_sys_user_creater_id` (`creater_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, 'admin', '5e35bbd3f9bd9f8f86a20d9c122d117f9e2b8dbb', '9590c47563aa9525', '管理员', 'aaa@gzjp.cn', '2014-06-20 11:29:10', '1', null, '1');
INSERT INTO `sys_user` VALUES ('402880e8510df4f501510df5c9ae0000', '1', 'ving0099', '734bedf35a385c3f9b4b15d6420cfd5850132d97', 'f5cf9c83cf5d84e7', 'ving0099', 'ving0099@126.com', '2015-11-16 09:43:10', '1', null, null);
INSERT INTO `sys_user` VALUES ('49', '1', 'test', '2722c13ff6e13be090f2551d689080dce50f12ed', '9bb0a0e2ba71f120', '测试', 'test@gzjp.cn1', '2014-07-22 00:00:00', '1', null, null);
INSERT INTO `sys_user` VALUES ('60', '1', 'dev_test01', '0b5cd59828078b153237e01fc3a3697902573ea6', 'd0cab1239702cf66', 'dev_test01', 'dev_test01@test.com', '2015-09-29 09:40:33', '1', null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK_sys_user_role_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '402880e8510df4f501510df5c9ae0000');
INSERT INTO `sys_user_role` VALUES ('2', '49');
INSERT INTO `sys_user_role` VALUES ('12', '60');
