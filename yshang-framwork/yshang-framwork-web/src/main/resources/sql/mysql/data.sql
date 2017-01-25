


/*
 * 
 * 
 * 此文件夹的数据已废弃，转移到了yshang-framwork-doc 项目下
 * 
 * 
 * 
 * 
 * */



-- ----------------------------
-- Records of hibernate_sequences
-- ----------------------------
INSERT INTO `hibernate_sequences` VALUES ('sys_role', '2');
INSERT INTO `hibernate_sequences` VALUES ('sys_permission', '5');
INSERT INTO `hibernate_sequences` VALUES ('sys_resource', '5');
INSERT INTO `hibernate_sequences` VALUES ('sys_role_auth', '60');
INSERT INTO `hibernate_sequences` VALUES ('sys_user', '10');


-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '查询', 'list', null);
INSERT INTO `sys_permission` VALUES ('2', '编辑', 'update', null);
INSERT INTO `sys_permission` VALUES ('3', '删除', 'del', null);
INSERT INTO `sys_permission` VALUES ('4', '添加', 'add', null);
INSERT INTO `sys_permission` VALUES ('5', '授权', 'auth', '1');


-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', null, '系统管理', 'sys', '/admin/sys/user', 'nav-supplier', '1', '1', '1', '1', null, '', null);
INSERT INTO `sys_resource` VALUES ('2', '1', '用户与权限', '', null, '', null, null, '1', '2', null, '', null);
INSERT INTO `sys_resource` VALUES ('4', '2', '用户管理', '/admin/sys/user', '/admin/sys/user', '', null, '1', '1', '3', '', '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('5', '2', '权限管理', '/admin/sys/permission', '/admin/sys/permission', '', null, '1', '4', '3', null, '1,2,3', '1');
INSERT INTO `sys_resource` VALUES ('6', '2', '角色管理', '/admin/sys/role', '/admin/sys/role', '', null, null, '3', '3', null, '1,2,3,4,5', null);
INSERT INTO `sys_resource` VALUES ('7', '2', '资源管理', '/admin/sys/resource', '/admin/sys/resource', '', null, null, '2', '3', null, '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('8', null, '微信管理', 'weixin', '', 'nav-inventory', '1', '1', '2', '1', null, '', null);
INSERT INTO `sys_resource` VALUES ('19', '8', '基础信息管理', null, '1', '', null, null, '1', '2', null, '', null);
INSERT INTO `sys_resource` VALUES ('20', '19', '菜单管理', null, '1', '', null, null, '1', '3', null, '1,2,3,4,5', null);
INSERT INTO `sys_resource` VALUES ('22', '1', '系统通用管理', null, '', '', null, null, '2', '2', null, '', null);
INSERT INTO `sys_resource` VALUES ('23', '19', '导航', null, '/admin/sys/user', null, null, null, '1', '3', null, '3,4', null);
INSERT INTO `sys_resource` VALUES ('30', '22', '数据字典分类', '/admin/sys/dictionaryType', '/admin/sys/dictionaryType', null, null, null, '1', '3', null, '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('31', '22', '数据字典', '/admin/sys/dictionary', '/admin/sys/dictionary', null, null, null, '2', '3', null, '1,2,3,4', null);
INSERT INTO `sys_resource` VALUES ('36', '1', '系统性能管理', null, null, null, null, null, '3', '2', null, '', null);
INSERT INTO `sys_resource` VALUES ('37', '36', '系统监控', '/admin/monitor/druid', '/admin/monitor/druid', null, null, null, '1', '3', null, '1', null);

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'Admin', '', null);
INSERT INTO `sys_role` VALUES ('2', '测试', 'Test', '', null);



-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES ('248', '1', '1', '');
INSERT INTO `sys_role_auth` VALUES ('249', '1', '2', '');
INSERT INTO `sys_role_auth` VALUES ('250', '1', '4', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('251', '1', '7', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('252', '1', '6', '1,2,3,4,5');
INSERT INTO `sys_role_auth` VALUES ('253', '1', '5', '1,2,3');
INSERT INTO `sys_role_auth` VALUES ('254', '1', '22', '');
INSERT INTO `sys_role_auth` VALUES ('255', '1', '8', '');
INSERT INTO `sys_role_auth` VALUES ('256', '1', '19', '');
INSERT INTO `sys_role_auth` VALUES ('257', '1', '20', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('258', '1', '23', '3,4');
INSERT INTO `sys_role_auth` VALUES ('275', '2', '4', '1,2');
INSERT INTO `sys_role_auth` VALUES ('276', '2', '7', '1,2,3,4');
INSERT INTO `sys_role_auth` VALUES ('277', '2', '6', '1,2,3');
INSERT INTO `sys_role_auth` VALUES ('278', '2', '20', '1,2,3,4,5');
INSERT INTO `sys_role_auth` VALUES ('279', '2', '1', '');
INSERT INTO `sys_role_auth` VALUES ('280', '2', '2', '');
INSERT INTO `sys_role_auth` VALUES ('281', '2', '8', '');
INSERT INTO `sys_role_auth` VALUES ('282', '2', '19', '');



-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', null, 'admin', '5e35bbd3f9bd9f8f86a20d9c122d117f9e2b8dbb', '9590c47563aa9525', '管理员', 'aaa@gzjp.cn', '2014-06-20 11:29:10', '1', null, '1');
INSERT INTO `sys_user` VALUES ('49', '1', 'test', '2722c13ff6e13be090f2551d689080dce50f12ed', '9bb0a0e2ba71f120', '测试', 'test@gzjp.cn1', '2014-07-22 00:00:00', '1', null, null);


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '49');
