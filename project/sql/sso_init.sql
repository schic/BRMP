/*
 Navicat Premium Data Transfer

 Source Server         : localhost_root
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : sso

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 12/12/2019 18:30:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_activate_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_activate_mail`;
CREATE TABLE `t_activate_mail`  (
  `uid` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id,t_user',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `lost_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱地址',
  `activate_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '激活码',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth`  (
  `auth_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_aid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`auth_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5x9vgc60neoa5hksvdvr0ny5y`(`auth_id`) USING BTREE,
  INDEX `FKfta4nf0ff9g6y8hy99bgf6qko`(`role_id`) USING BTREE,
  CONSTRAINT `FK5x9vgc60neoa5hksvdvr0ny5y` FOREIGN KEY (`auth_id`) REFERENCES `t_auth` (`auth_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfta4nf0ff9g6y8hy99bgf6qko` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_login_session
-- ----------------------------
DROP TABLE IF EXISTS `t_login_session`;
CREATE TABLE `t_login_session`  (
  `s_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'session的id',
  `s_value` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'session内容的保存',
  `create_time` datetime(0) NOT NULL COMMENT '当前时间',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_login_session
-- ----------------------------
INSERT INTO `t_login_session` VALUES ('1A7E6F791E92A19E998D749BBE656FBE', 'El0/6HePVzoR2tkSujt7jlVVRzAQwpbzHpftRAhgWIZzipw2OW/ijogdDYrg9FDbTCFjvLGUQdKnir2RqxlHJZrh5SoQScY1YoE9Foj+4j6uq7dtStKih9j/JPZpP9RLc7bgRWzcv7GMsNulQu6z+6EuNuU9IY6eWY5bv+/0ZKfW+6fXLj0S7lMLQzOYtB49uPZIfky2cbKGvomQn8anjg0hjjnVXvSytUIo8iSPOd/7PKu/xQaPkRtNCXyL9PbRvA1HHDelFk+N8neV6cDms27nmEAUiqWXghQsVif7bUDqeFez/c7tqMGFQGAzazGHT0/Y8oxh+f3NE1rwuf3kJoXxqebBmIM1veaxCJu0IZLlrRQSRtE/HlKJbiQa5tStc58lOm/u3UJBj7uoOENLG8L9KQnc/pxsZDAqqlJVMdQdbGhNazkN5lOrGacExzBn3ztDWEz1xiE50R9HgQ3rPw==', '2019-12-12 17:51:05');
INSERT INTO `t_login_session` VALUES ('9900432F58EA9BDDA8349F09BFCCF4F2', 'El0/6HePVzoR2tkSujt7jlVVRzAQwpbzHpftRAhgWIZzipw2OW/ijogdDYrg9FDbTCFjvLGUQdKnir2RqxlHJZrh5SoQScY1YoE9Foj+4j6uq7dtStKih9j/JPZpP9RLc7bgRWzcv7GMsNulQu6z+6EuNuU9IY6eWY5bv+/0ZKfW+6fXLj0S7lMLQzOYtB49uPZIfky2cbKGvomQn8anjg0hjjnVXvSytUIo8iSPOd/7PKu/xQaPkRtNCXyL9PbRvA1HHDelFk+N8neV6cDms27nmEAUiqWXghQsVif7bUDqeFez/c7tqMGFQGAzazGHT0/Y8oxh+f3NE1rwuf3kJoXxqebBmIM1veaxCJu0IZLlrRQSRtE/HlKJbiQa5tStc58lOm/u3UJBj7uoOENLG0BlOqtIQW25p/bCg5DrokCU9lXUZUjFhIXwP4iV0+z/WaoVHg9A92iRuJsQOiWGCA==', '2019-12-12 18:04:22');
INSERT INTO `t_login_session` VALUES ('DD2516D762EF9DB0B07AF856528A1991', 'El0/6HePVzoR2tkSujt7jlVVRzAQwpbzHpftRAhgWIZzipw2OW/ijogdDYrg9FDbTCFjvLGUQdKnir2RqxlHJZrh5SoQScY1YoE9Foj+4j6uq7dtStKih9j/JPZpP9RLc7bgRWzcv7GMsNulQu6z+6EuNuU9IY6eWY5bv+/0ZKfW+6fXLj0S7lMLQzOYtB49uPZIfky2cbKGvomQn8anjg0hjjnVXvSytUIo8iSPOd/7PKu/xQaPkRtNCXyL9PbRvA1HHDelFk+N8neV6cDms27nmEAUiqWXghQsVif7bUDqeFez/c7tqMGFQGAzazGHT0/Y8oxh+f3NE1rwuf3kJoXxqebBmIM1veaxCJu0IZLlrRQSRtE/HlKJbiQa5tStc58lOm/u3UJBj7uoOENLG1f5/mO2nWmF05XK6dHly4GepNVaNeUwzPI28RW3rJBg5HYVBbwl+MKM5Ew7Syz6eg==', '2019-12-12 16:51:34');
INSERT INTO `t_login_session` VALUES ('EC6FB6EB8732A919DF76D586C6C7A0F0', 'El0/6HePVzoR2tkSujt7jlVVRzAQwpbzHpftRAhgWIZzipw2OW/ijogdDYrg9FDbTCFjvLGUQdKnir2RqxlHJZrh5SoQScY1YoE9Foj+4j6uq7dtStKih9j/JPZpP9RLc7bgRWzcv7GMsNulQu6z+6EuNuU9IY6eWY5bv+/0ZKfW+6fXLj0S7lMLQzOYtB49uPZIfky2cbKGvomQn8anjg0hjjnVXvSytUIo8iSPOd/7PKu/xQaPkRtNCXyL9PbRvA1HHDelFk+N8neV6cDms27nmEAUiqWXghQsVif7bUDqeFez/c7tqMGFQGAzazGHT0/Y8oxh+f3NE1rwuf3kJoXxqebBmIM1veaxCJu0IZLlrRQSRtE/HlKJbiQa5tStc58lOm/u3UJBj7uoOENLG2v4fTPlacpqMTIRv8+Y75dYBiSAq5HH1S8LPP4TSuk6xU2T7IfXd0ouKAzM3Wvy9A==', '2019-12-12 16:23:43');

-- ----------------------------
-- Table structure for t_login_session_url
-- ----------------------------
DROP TABLE IF EXISTS `t_login_session_url`;
CREATE TABLE `t_login_session_url`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'session_id关联',
  `s_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前session登录了的url',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `session_id`(`s_id`) USING BTREE,
  CONSTRAINT `session_id` FOREIGN KEY (`s_id`) REFERENCES `t_login_session` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_login_session_url
-- ----------------------------
INSERT INTO `t_login_session_url` VALUES ('1e18cda8-0984-384c-afe5-5199e525b22d', '9900432F58EA9BDDA8349F09BFCCF4F2', 'http://localhost:8980/EMPI_center');
INSERT INTO `t_login_session_url` VALUES ('7e960781-40e2-3f79-8296-c153fda6c648', '9900432F58EA9BDDA8349F09BFCCF4F2', 'http://192.168.1.179:8980/EMPI_center');
INSERT INTO `t_login_session_url` VALUES ('94d8f96c-1a84-3808-9d3b-ba95830bd897', 'DD2516D762EF9DB0B07AF856528A1991', 'http://192.168.1.179:8980/EMPI_center');
INSERT INTO `t_login_session_url` VALUES ('beef7dea-0ad6-3ade-9f35-fbe31ab6b3b2', 'EC6FB6EB8732A919DF76D586C6C7A0F0', 'http://192.168.1.179:8980/EMPI_center');
INSERT INTO `t_login_session_url` VALUES ('df1c8fd8-653c-307c-91c0-08722260be70', '1A7E6F791E92A19E998D749BBE656FBE', 'http://192.168.1.179:8980/EMPI_center');

-- ----------------------------
-- Table structure for t_pw_type
-- ----------------------------
DROP TABLE IF EXISTS `t_pw_type`;
CREATE TABLE `t_pw_type`  (
  `pw_type_id` int(11) NOT NULL,
  `pw_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pw_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pw_type
-- ----------------------------
INSERT INTO `t_pw_type` VALUES (0, 'C1m~l#k');
INSERT INTO `t_pw_type` VALUES (1, 'Rh54&');
INSERT INTO `t_pw_type` VALUES (2, '3*uE3^');
INSERT INTO `t_pw_type` VALUES (3, '!u$dA0');
INSERT INTO `t_pw_type` VALUES (4, 'kG8#(');
INSERT INTO `t_pw_type` VALUES (5, '60@qW');
INSERT INTO `t_pw_type` VALUES (6, 'Ii^f2');
INSERT INTO `t_pw_type` VALUES (7, 'x&m7J');
INSERT INTO `t_pw_type` VALUES (8, 'tPn4)-');
INSERT INTO `t_pw_type` VALUES (9, 'dB#%d9');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `pw_type` int(11) NOT NULL COMMENT '样式',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态，默认为1,停用为0',
  `u_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `lost_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱地址',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'user' COMMENT '用户类型 管理员:admin 系统接入:system 一般用户:user',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0', '2017-07-14 11:51:03', 0, '0', '0', '0', '0', NULL, '', 'user');
INSERT INTO `t_user` VALUES ('2bd81117-d99f-435b-8dec-95495ae46cf7', '2017-07-27 16:17:50', 92, '-', '1', '01dc3e408742c22e72922ed3f2eeedaf', 'admin', '2019-12-12 10:09:30', '123@123.cn', 'admin');
INSERT INTO `t_user` VALUES ('34be7c19-37e3-47ff-97c2-4947b1e096f6', '2019-12-12 10:24:53', 92, '-', '1', '01dc3e408742c22e72922ed3f2eeedaf', 'testUser', '2019-12-12 10:25:55', '123@123.cn', 'user');
INSERT INTO `t_user` VALUES ('65b65e95-b9c9-4efb-ad15-be387121762f', '2019-12-12 10:27:14', 92, '-', '1', '01dc3e408742c22e72922ed3f2eeedaf', 'testSystem', '2019-12-12 10:27:52', '123@123.cn', 'system');
INSERT INTO `t_user` VALUES ('6aa909ef-f3f6-4f69-b560-b466cb7a2529', '2019-12-12 14:31:06', 92, '-', '1', '01dc3e408742c22e72922ed3f2eeedaf', 'test_system1', '2019-12-12 14:29:54', '123@123.cn', 'system');

-- ----------------------------
-- Table structure for t_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth`;
CREATE TABLE `t_user_auth`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKe635st5jf62lh5aspb421ayv5`(`auth_id`) USING BTREE,
  INDEX `FK9tt6gotuwv63ndkieap15hmf1`(`user_id`) USING BTREE,
  CONSTRAINT `FK9tt6gotuwv63ndkieap15hmf1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKe635st5jf62lh5aspb421ayv5` FOREIGN KEY (`auth_id`) REFERENCES `t_auth` (`auth_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKa9c8iiy6ut0gnx491fqx4pxam`(`role_id`) USING BTREE,
  INDEX `FKq5un6x7ecoef5w1n39cop66kl`(`user_id`) USING BTREE,
  CONSTRAINT `FKa9c8iiy6ut0gnx491fqx4pxam` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKq5un6x7ecoef5w1n39cop66kl` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
