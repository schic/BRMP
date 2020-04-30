/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.9.33
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 172.16.9.33:3306
 Source Schema         : brmptest1

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 30/04/2020 18:43:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brmp_apply_attribute
-- ----------------------------
DROP TABLE IF EXISTS `brmp_apply_attribute`;
CREATE TABLE `brmp_apply_attribute`  (
  `apply_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请id',
  `model_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型id',
  `model_col_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型字段名'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for brmp_apply_base
-- ----------------------------
DROP TABLE IF EXISTS `brmp_apply_base`;
CREATE TABLE `brmp_apply_base`  (
  `apply_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请id',
  `model_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号(申请主资源编号 一个主要资源)',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用户登录ID(或其他登录用唯一号)',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用户登录用户名',
  `apply_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请资源目录名称（申请名称）',
  `apply_org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请机构名称',
  `apply_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人姓名',
  `apply_direction` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用途描述说明',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `audit_status` int(1) NULL DEFAULT NULL COMMENT '审核状态  1:待审核 2:审核拒绝 9:审核通过',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for brmp_conf_origin_system_info
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_info`;
CREATE TABLE `brmp_conf_origin_system_info`  (
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_SYSTEM_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称',
  `ORIGIN_SYSTEM_CNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称中文或别名',
  `ORIGIN_SYSTEM_URL` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接入系统接口的URL',
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接入系统用户名',
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接入系统验证'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_info
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_info` VALUES ('1', 'admin', 'BRMP中心系统', 'http://192.168.1.111:8980/brmp-webservice-in/webservice/BrmpObj/reqBrmp', 'admin', 'admin');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('test_system_001', 'test_system', '测试接入系统', 'http:123', 'testSystem', '123456');

-- ----------------------------
-- Table structure for brmp_conf_origin_system_mdbase
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_mdbase`;
CREATE TABLE `brmp_conf_origin_system_mdbase`  (
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型名称',
  `MODEL_TAB_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型表名',
  `MODEL_CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '模型创建时间',
  `MODEL_UPDETE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '模型更新时间',
  `MODEL_DESCRIPTION` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型描述',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '状态  0:停用 1:启用',
  `AUDIT_STATUS` int(1) NULL DEFAULT NULL COMMENT '审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过',
  `DATA_NUM` int(12) NULL DEFAULT 0 COMMENT '当前数据记录数'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统建立模型的配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_mdbase
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_mdbase` VALUES ('test_system_001', 'testmodel001', '测试模型test001', 'md_test001', '2020-04-29 17:28:03', '2020-04-29 17:36:47', '这是一个测试建立的模型', 1, 9, 0);

-- ----------------------------
-- Table structure for brmp_conf_origin_system_model
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_model`;
CREATE TABLE `brmp_conf_origin_system_model`  (
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-1' COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEl_COL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型提供的字段',
  `MODEl_COL_DISPLAY_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型字段展示名称',
  `MODEL_COL_TYPE` int(3) NULL DEFAULT 0 COMMENT '字段类型 0:字符串 1:整数 2:浮点数 3:日期',
  `MODEL_COL_LENTH` int(11) NULL DEFAULT -1 COMMENT '字段长度',
  `MODEL_COL_DECIMAL_LENTH` int(11) NULL DEFAULT -1 COMMENT '小数长度',
  `DISPLAY_ORDER` int(3) NULL DEFAULT -1 COMMENT '展示顺序',
  `PK` int(1) NULL DEFAULT -1 COMMENT '是否主键 0否  1是'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统建立模型的配置明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_model
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test_system_001', 'testmodel001', 'test001', '测试字段1', 0, 5, -1, 1, 0);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test_system_001', 'testmodel001', 'test2', '测试字段2', 0, 3, -1, 2, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test_system_001', 'testmodel001', 'Id', '系统预留id', 0, 64, -1, 3, 0);

-- ----------------------------
-- Table structure for brmp_dic_datatype
-- ----------------------------
DROP TABLE IF EXISTS `brmp_dic_datatype`;
CREATE TABLE `brmp_dic_datatype`  (
  `MODEL_COL_TYPE` int(11) NULL DEFAULT NULL COMMENT '字段类型编号',
  `DATATYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段类型名称',
  `JAVA_DATATYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应程序中使用的字段类型'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_dic_datatype
-- ----------------------------
INSERT INTO `brmp_dic_datatype` VALUES (0, 'varchar', 'String');
INSERT INTO `brmp_dic_datatype` VALUES (1, 'int', 'int');
INSERT INTO `brmp_dic_datatype` VALUES (2, 'float', 'float');
INSERT INTO `brmp_dic_datatype` VALUES (3, 'datetime', 'Date');
INSERT INTO `brmp_dic_datatype` VALUES (4, 'bigint', 'long');
INSERT INTO `brmp_dic_datatype` VALUES (5, 'double', 'double');

-- ----------------------------
-- Table structure for md_test001
-- ----------------------------
DROP TABLE IF EXISTS `md_test001`;
CREATE TABLE `md_test001`  (
  `test001` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '测试字段1',
  `test2` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '测试字段2',
  `Id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '系统预留id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of md_test001
-- ----------------------------
INSERT INTO `md_test001` VALUES ('1', '2', NULL);
INSERT INTO `md_test001` VALUES ('1', '3', NULL);
INSERT INTO `md_test001` VALUES ('11', '4', NULL);

-- ----------------------------
-- Table structure for md_test001_change
-- ----------------------------
DROP TABLE IF EXISTS `md_test001_change`;
CREATE TABLE `md_test001_change`  (
  `ZYBZ` int(1) NULL DEFAULT 0 COMMENT '作业标志,用于temp表',
  `test001` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '测试字段1',
  `test2` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '测试字段2',
  `Id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '系统预留id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_test001_ex
-- ----------------------------
DROP TABLE IF EXISTS `md_test001_ex`;
CREATE TABLE `md_test001_ex`  (
  `test2` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL COMMENT '测试字段2',
  `CGBZ_1` int(1) NULL DEFAULT 0 COMMENT '判断数据是否上传完成,0未上传,1上传完成'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_cs ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of md_test001_ex
-- ----------------------------
INSERT INTO `md_test001_ex` VALUES ('2', 1);
INSERT INTO `md_test001_ex` VALUES ('3', 1);
INSERT INTO `md_test001_ex` VALUES ('4', 1);

-- ----------------------------
-- Table structure for md_test001_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_test001_temp`;
CREATE TABLE `md_test001_temp`  (
  `test001` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL,
  `test2` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL,
  `Id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_as_cs ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
