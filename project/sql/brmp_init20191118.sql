/*
 Navicat Premium Data Transfer

 Source Server         : localhost_root
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : brmp

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/11/2019 17:50:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
INSERT INTO `brmp_conf_origin_system_info` VALUES ('1', 'EMPI_center', 'EMPI中心系统', 'http://127.0.0.1:8980/EMPI_center/webservice/EMPIObj/reqEMPIObj/', NULL, NULL);
INSERT INTO `brmp_conf_origin_system_info` VALUES ('testA001', 'test_system1', '测试接入系统1', 'http://127.0.0.1:8980/BRMP_client/', 'test_system1', '123');

-- ----------------------------
-- Table structure for brmp_conf_origin_system_model
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_model`;
CREATE TABLE `brmp_conf_origin_system_model`  (
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEL_TAB_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型表名',
  `MODEl_COL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型提供的字段',
  `MODEL_COL_TYPE` int(3) NULL DEFAULT NULL COMMENT '字段类型 0:字符串 1:整数 2:浮点数 3:日期',
  `MODEL_COL_LENTH` int(11) NULL DEFAULT NULL COMMENT '字段长度'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统建立模型的配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_model
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', 'test123456789', 'MODEL_TEST1', 'testString1', 0, 225);

-- ----------------------------
-- Table structure for brmp_conf_origin_system_modelbase
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_modelbase`;
CREATE TABLE `brmp_conf_origin_system_modelbase`  (
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型名称',
  `MODEL_TAB_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型表名'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统建立模型的配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_modelbase
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('testA001', 'test123456789', 'test_model1', 'MODEL_TEST1');

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

-- ----------------------------
-- Table structure for brmp_grxx
-- ----------------------------
DROP TABLE IF EXISTS `brmp_grxx`;
CREATE TABLE `brmp_grxx`  (
  `RECORDID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录唯一号',
  `OBJ_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人(物)唯一主索引',
  `SFZH` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `CSRQ` date NULL DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `ZQDDF` int(3) NULL DEFAULT -1 COMMENT '信息准确度得分',
  `GXRQ` datetime(0) NULL DEFAULT NULL COMMENT '记录更新日期'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主索引终表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_grxx
-- ----------------------------
INSERT INTO `brmp_grxx` VALUES ('6f019a14a425326a9d010fa0ba7ef1f3', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试人员1', '2', '1987-06-30', '-', '四川', '-', '-', 62, '2019-10-24 17:05:13');
INSERT INTO `brmp_grxx` VALUES ('d6847005512c34dbbd943441c1b879df', '7abed33fc4c347ea8209f65ff10cd90e', '521211198807313945', '测试人员2', '2', '1988-07-01', '13133334444', '成都', '-', '-', 75, '2019-10-24 17:05:13');
INSERT INTO `brmp_grxx` VALUES ('deec237a6bdd350d8a2fd60f4f746d1a', '4745752f1d0e4682ab1a7ba167903cb4', '521311197611264522', '测试人员3', '1', '1976-11-26', '18055556666', '绵阳', '赵XX', '1234567', 51, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx` VALUES ('1cd41c49716d3178aca8aaa498cb2a91', 'caa016fb60f04816a4a932bc37c76f1d', '-', '-', '-', '1900-01-01', '-', '-', '-', '-', 25, '2019-10-24 17:50:55');

-- ----------------------------
-- Table structure for brmp_grxx_cache
-- ----------------------------
DROP TABLE IF EXISTS `brmp_grxx_cache`;
CREATE TABLE `brmp_grxx_cache`  (
  `RECORDID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录唯一号',
  `OBJ_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人(物)唯一主索引',
  `SFZH` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `CSRQ` date NULL DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统主键值',
  `ZQDDF` int(3) NULL DEFAULT -1 COMMENT '信息准确度得分',
  `GXRQ` datetime(0) NULL DEFAULT NULL COMMENT '记录更新日期'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主索引关联关系总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_grxx_cache
-- ----------------------------
INSERT INTO `brmp_grxx_cache` VALUES ('5c836699d5cb3d76a0db1312cf12250f', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试人员1', '2', '1987-06-30', '13011112222', '四川', '王XX', '-', 'testA001', 'test001', 62, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('6f019a14a425326a9d010fa0ba7ef1f3', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试人员1', '2', '1987-06-30', '-', '四川', '-', '-', 'testA001', 'test002', 62, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('804d819085e03e6a964ea1b9f2cd191e', '7abed33fc4c347ea8209f65ff10cd90e', '521211198807313945', '测试人员2', '1', '1988-07-31', '13133334444', '成都', '李XX', '9090980', 'testA001', 'test003', 75, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('d6847005512c34dbbd943441c1b879df', '7abed33fc4c347ea8209f65ff10cd90e', '521211198807313945', '测试人员2', '2', '1988-07-01', '13133334444', '成都', '-', '-', 'testA001', 'test004', 75, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('b05eb1d97b2139f7ba1d1c245867eb67', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试错误姓名1', '2', '1987-06-30', '-', '自贡', '-', '-', 'testA001', 'test005', 51, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('deec237a6bdd350d8a2fd60f4f746d1a', '4745752f1d0e4682ab1a7ba167903cb4', '521311197611264522', '测试人员3', '1', '1976-11-26', '18055556666', '绵阳', '赵XX', '1234567', 'testA001', 'test006', 51, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('f5db176268ba32e7b94a52ef1786a533', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试错误姓名1', '2', '1900-01-01', '-', '-', '-', '-', 'testA001', 'test007', 51, '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('1cd41c49716d3178aca8aaa498cb2a91', 'caa016fb60f04816a4a932bc37c76f1d', '-', '-', '-', '1900-01-01', '-', '-', '-', '-', 'testA001', 'testNull', 25, '2019-10-24 17:50:55');

-- ----------------------------
-- Table structure for brmp_grxx_inter
-- ----------------------------
DROP TABLE IF EXISTS `brmp_grxx_inter`;
CREATE TABLE `brmp_grxx_inter`  (
  `RECORDID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录唯一号',
  `SFZH` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `CSRQ` date NULL DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统主键值',
  `BZ` int(11) NULL DEFAULT 0 COMMENT '数据作业标志 默认0 为未作业数据 '
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主索引临时插入计算表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for brmp_relate_index
-- ----------------------------
DROP TABLE IF EXISTS `brmp_relate_index`;
CREATE TABLE `brmp_relate_index`  (
  `RECORDID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录唯一号',
  `OBJ_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源唯一主索引',
  `OBJ_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称(姓名)',
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_SYSTEM_MAIN_COLNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统主键列名',
  `ORIGIN_SYSTEM_DATE` datetime(0) NULL DEFAULT NULL COMMENT '源系统本记录日期',
  `IS_PERSON` int(1) NULL DEFAULT NULL COMMENT '是否是个人主索引 1: 是 0: 否'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '本表设计为一种可能,用于不存接入系统的数据.' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_system1_grxx
-- ----------------------------
DROP TABLE IF EXISTS `test_system1_grxx`;
CREATE TABLE `test_system1_grxx`  (
  `SFZH` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `CSRQ` date NULL DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `ORIGIN_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统主键值'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '测试接入系统源数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_system1_grxx
-- ----------------------------
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试人员1', '2', '1987-06-30', '13011112222', '四川', '王XX', NULL, 'test001');
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试人员1', '2', '1987-06-30', NULL, '四川', NULL, NULL, 'test002');
INSERT INTO `test_system1_grxx` VALUES ('521211198807313945', '测试人员2', '1', '1988-07-31', '13133334444', '成都', '李XX', '9090980', 'test003');
INSERT INTO `test_system1_grxx` VALUES ('521211198807313945', '测试人员2', '2', '1988-07-01', '13133334444', '成都', NULL, NULL, 'test004');
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试错误姓名1', '2', '1987-06-30', NULL, '自贡', NULL, NULL, 'test005');
INSERT INTO `test_system1_grxx` VALUES ('521311197611264522', '测试人员3', '1', '1976-11-26', '18055556666', '绵阳', '赵XX', '1234567', 'test006');
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试错误姓名1', '2', NULL, NULL, NULL, NULL, NULL, 'test007');
INSERT INTO `test_system1_grxx` VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'testNull');

SET FOREIGN_KEY_CHECKS = 1;
