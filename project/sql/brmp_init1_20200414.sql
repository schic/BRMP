/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.9.33
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 172.16.9.33:3306
 Source Schema         : brmp

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 14/04/2020 12:48:44
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
-- Records of brmp_apply_attribute
-- ----------------------------
INSERT INTO `brmp_apply_attribute` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'Along1');
INSERT INTO `brmp_apply_attribute` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'aLong2');
INSERT INTO `brmp_apply_attribute` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'test123');
INSERT INTO `brmp_apply_attribute` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'testint');
INSERT INTO `brmp_apply_attribute` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdouble');
INSERT INTO `brmp_apply_attribute` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');
INSERT INTO `brmp_apply_attribute` VALUES ('e4f78305e184435cb5220bbcee342428', '9fb6518c3e1a439c8e49a16a56ba9791', 'Along1');
INSERT INTO `brmp_apply_attribute` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'Along1');
INSERT INTO `brmp_apply_attribute` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'aLong2');
INSERT INTO `brmp_apply_attribute` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'test123');
INSERT INTO `brmp_apply_attribute` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'testint');
INSERT INTO `brmp_apply_attribute` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdouble');
INSERT INTO `brmp_apply_attribute` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');
INSERT INTO `brmp_apply_attribute` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'Along1');
INSERT INTO `brmp_apply_attribute` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'aLong2');
INSERT INTO `brmp_apply_attribute` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'test123');
INSERT INTO `brmp_apply_attribute` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'testint');
INSERT INTO `brmp_apply_attribute` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdouble');
INSERT INTO `brmp_apply_attribute` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');
INSERT INTO `brmp_apply_attribute` VALUES ('f009185eb7d140f0a65ea7344699d190', '9fb6518c3e1a439c8e49a16a56ba9791', 'aLong2');
INSERT INTO `brmp_apply_attribute` VALUES ('f009185eb7d140f0a65ea7344699d190', '9fb6518c3e1a439c8e49a16a56ba9791', 'testint');
INSERT INTO `brmp_apply_attribute` VALUES ('f009185eb7d140f0a65ea7344699d190', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdouble');
INSERT INTO `brmp_apply_attribute` VALUES ('f009185eb7d140f0a65ea7344699d190', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');
INSERT INTO `brmp_apply_attribute` VALUES ('2d3ecac70aab4e4788e419b244336d95', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');
INSERT INTO `brmp_apply_attribute` VALUES ('2d3ecac70aab4e4788e419b244336d95', 'test123456789', 'testInt1');
INSERT INTO `brmp_apply_attribute` VALUES ('4d43453e2cc24921a243a50bf18516e9', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');
INSERT INTO `brmp_apply_attribute` VALUES ('4d43453e2cc24921a243a50bf18516e9', 'test123456789', 'testInt1');
INSERT INTO `brmp_apply_attribute` VALUES ('4d43453e2cc24921a243a50bf18516e9', 'test002ABCDEF', 'testinsert');
INSERT INTO `brmp_apply_attribute` VALUES ('7354b68db0af402886a63fc060e7f4e5', '9fb6518c3e1a439c8e49a16a56ba9791', 'testint');
INSERT INTO `brmp_apply_attribute` VALUES ('7354b68db0af402886a63fc060e7f4e5', '9fb6518c3e1a439c8e49a16a56ba9791', 'test123');
INSERT INTO `brmp_apply_attribute` VALUES ('7354b68db0af402886a63fc060e7f4e5', '9fb6518c3e1a439c8e49a16a56ba9791', 'aLong2');
INSERT INTO `brmp_apply_attribute` VALUES ('7354b68db0af402886a63fc060e7f4e5', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate');

-- ----------------------------
-- Table structure for brmp_apply_base
-- ----------------------------
DROP TABLE IF EXISTS `brmp_apply_base`;
CREATE TABLE `brmp_apply_base`  (
  `apply_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请id',
  `model_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号(申请主资源编号 一个主要资源)',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用户登录用户名(或其他登录用唯一号)',
  `apply_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请资源目录名称（申请名称）',
  `apply_org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请机构名称',
  `apply_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人姓名',
  `apply_direction` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用途描述说明',
  `apply_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `audit_status` int(1) NULL DEFAULT NULL COMMENT '审核状态  1:待审核 2:审核拒绝 9:审核通过',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_apply_base
-- ----------------------------
INSERT INTO `brmp_apply_base` VALUES ('2d3ecac70aab4e4788e419b244336d95', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '测试多模型资源申请', 'testUser', 'testUser', '测试多模型资源申请', '2020-03-09 21:23:35', 1);
INSERT INTO `brmp_apply_base` VALUES ('4d43453e2cc24921a243a50bf18516e9', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '测试多资源申请2', 'testUser', 'testUser', '测试多资源申请2', '2020-03-09 21:26:59', 9);
INSERT INTO `brmp_apply_base` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', 'test资源目录名称', 'testUser', 'testUser', '-', '2020-01-03 11:55:42', 2);
INSERT INTO `brmp_apply_base` VALUES ('7354b68db0af402886a63fc060e7f4e5', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', 'test222', 'testUser', 'testUser', '11111', '2020-03-23 15:04:35', 9);
INSERT INTO `brmp_apply_base` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '123', 'testUser', 'testUser', '123', '2020-01-03 12:23:08', 9);
INSERT INTO `brmp_apply_base` VALUES ('e4f78305e184435cb5220bbcee342428', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '目录名称测试1', 'testUser1', 'testUser1', '-', '2020-01-03 12:09:41', 2);
INSERT INTO `brmp_apply_base` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '123123', 'testUser1', 'testUser1', '测试资源用途说明', '2020-01-03 12:29:09', 1);
INSERT INTO `brmp_apply_base` VALUES ('f009185eb7d140f0a65ea7344699d190', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '目录名称testuser123', 'testUser', 'testUser', '用途说明123', '2020-01-07 18:56:44', 1);

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
INSERT INTO `brmp_conf_origin_system_info` VALUES ('1', 'admin', 'EMPI中心系统', 'http://172.16.9.33:8980/EMPI_center/webservice/EMPIObj/reqEMPIObj/', 'admin', 'admin');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('testA001', 'test_system1', '测试接入系统1', 'http://172.16.9.33:8080/BRMP_client/', 'test_system1', '1234567890');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('test002', 'test_system2', '测试接入系统2', 'http://127.0.0.1:8980/BRMP_client/', 'test_system2', '123');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('36fd23db443c4fdb9af7aaf01b851a07', 'testSystem', '全民健康信息平台', 'http', 'testSystem', '1234567890');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'proPlatform', '四川省全民健康信息平台', '-', 'proPlatform', '123456');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('7a61d403eb334f54b01c8a891ec65133', 'testWTData', '测试卫统数据接入', '-', 'testWTData', '123456');

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
  `DISPLAY_ORDER` int(3) NULL DEFAULT -1 COMMENT '展示顺序'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统建立模型的配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_model
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'orgCode', '机构代码', 0, 50, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'orgName', '机构名称', 0, 50, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'jobNumber', '工号', 0, 50, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'postTypeCode', '岗位类别 ', 0, 10, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'empTypeCode', '人员性质', 0, 5, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'comments', '备注', 0, 499, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'active', '状态（是否在用 ）1 启用，0 停用', 1, 1, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'createDate', '创建日期', 3, -1, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'name', '姓名', 0, 50, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'genderCode', '性别', 0, 6, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'birthDate', '出生日期', 3, -1, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'maritalStCode', '婚姻状况', 0, 6, -1, 16);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'idNo', '身份证号', 0, 30, -1, 17);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'liveAddr', '家庭地址', 0, 200, -1, 18);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'workDate', '参加工作数据', 3, -1, -1, 19);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', 'graduatedSchool', '毕业学校', 0, 200, -1, 20);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'parentId', '父机构id', 0, 50, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'orgCode', '机构代码', 0, 50, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'orgName', '机构名称', 0, 100, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'orgAlias', '机构别名', 0, 100, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'orgAbbreviation', '机构缩写', 0, 100, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'admiDivisionCode', '社区行政区划编码', 0, 100, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'admiDivisionName', '社区行政区划名称', 0, 100, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'createYear', '创建年份', 0, 20, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'repealYesr', '修改年份', 0, 20, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'typeCode', '状态编码', 0, 20, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'status', '状态', 1, 2, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'createDate', '创建时间', 3, -1, -1, 16);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'provinceCode', '省级代码', 0, 50, -1, 17);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'cityCode', '城市代码', 0, 50, -1, 18);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', 'areaCode', '区域代码', 0, 50, -1, 19);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'orgId', '机构ID', 0, 50, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'parentId', '上一级部门', 0, 50, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'deptCode', '部门编码', 0, 50, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'deptName', '部门名称', 0, 100, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'detailAddress', '所在地址', 0, 499, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'medicalSubjectsCode', '诊疗科目代码', 0, 100, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'medicalSubjectsName', '诊疗科目名称', 0, 100, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'active', '是否在用（状态） 0 停用 1 启用', 1, 2, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', 'createDate', '新增创建日期', 3, -1, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'BBQ_', '报表期', 0, 11, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'USERID_', '用户ID', 0, 32, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'BTYPE_', '是否基层', 0, 1, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'USERNAME_', '机构名称', 0, 100, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID_', '上级代码', 0, 18, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'SHTAG_', '新增', 0, 1, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'SHTAG2_', '新增', 0, 1, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'OPTION_', '审核状态', 0, 10, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'USERSHTAG2_', '新增', 0, 1, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'LOCKOWNER_', '加锁状态', 0, 18, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'SUBMITDATE_', '上报时间', 3, -1, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'STATE_', '审批状态', 0, 1, -1, 16);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'PREV_APPROVER_', '新增', 0, 18, -1, 17);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'PREV_APPROVERNAME_', '新增', 0, 100, -1, 18);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'NEXT_APPROVER_', '新增', 0, 18, -1, 19);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'NEXT_APPROVERNAME_', '等待审批人', 0, 100, -1, 20);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'TAG_', '是否上报', 4, 10, -1, 21);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID0', '新增', 0, 20, -1, 22);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID1', '新增', 0, 20, -1, 23);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID2', '新增', 0, 20, -1, 24);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID3', '新增', 0, 20, -1, 25);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID4', '新增', 0, 20, -1, 26);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID5', '新增', 0, 20, -1, 27);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID6', '新增', 0, 20, -1, 28);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID7', '新增', 0, 20, -1, 29);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID8', '新增', 0, 20, -1, 30);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'UPID9', '新增', 0, 20, -1, 31);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C2', '姓名', 0, 50, -1, 32);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C3', '身份证件种类', 0, 12, -1, 33);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C4', '身份证件号码', 0, 30, -1, 34);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C5', '出生日期', 0, 12, -1, 35);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C6', '性别代码', 0, 24, -1, 36);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C7', '民族代码', 0, 20, -1, 37);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C8', '参加工作日期', 0, 12, -1, 38);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C9', '办公室电话号码', 0, 20, -1, 39);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C10', '手机号码(单位负责人及应急救治专家填写)', 0, 11, -1, 40);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C11', '聘用方式', 0, 24, -1, 41);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C12', '获得证书类别', 0, 24, -1, 42);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C13', '所在科室代码', 0, 10, -1, 43);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C14', '科室实际名称', 0, 50, -1, 44);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C15', '从事专业类别代码', 0, 20, -1, 45);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C16', '医师/卫生监督员执业证书编码', 0, 50, -1, 46);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C17', '医师执业类别代码', 0, 20, -1, 47);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C18', '医师执业范围代码（可选三个）', 0, 50, -1, 48);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C19', '是否多地点执业医师', 0, 12, -1, 49);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C20', '第2执业单位的机构类别', 0, 12, -1, 50);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C21', '第3执业单位的机构类别', 0, 12, -1, 51);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C22', '行政/业务管理职务代码', 0, 20, -1, 52);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C23', '注册护士执业证书编码', 0, 24, -1, 53);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C24', '专业技术资格(评)代码', 0, 20, -1, 54);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C25', '专业技术职务(聘)代码', 0, 20, -1, 55);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C26', '学历代码', 0, 20, -1, 56);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C27', '学位代码', 0, 20, -1, 57);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C28', '所学专业代码', 0, 20, -1, 58);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C29', '专科特长(仅要求医院主任、副主任医师填写)', 0, 50, -1, 59);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C30', '专科特长(仅要求医院主任、副主任医师填写)', 0, 50, -1, 60);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C31', '专科特长(仅要求医院主任、副主任医师填写)', 0, 50, -1, 61);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C32', '本年人员流动情况', 0, 20, -1, 62);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C33', '进入本单位方式（限政府办机构填写）', 0, 24, -1, 63);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C34', '调入/调出时间', 0, 12, -1, 64);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C35', '是否本单位返聘人员', 0, 20, -1, 65);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C36', '编制情况', 0, 24, -1, 66);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C37', '全科医生取得培训合格证书情况（限参加培训人员填写）', 0, 24, -1, 67);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C38', '已取得全科医生培训合格证书的医生执业注册情况', 0, 100, -1, 68);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C39', '岗位类别', 0, 24, -1, 69);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C40', '管理岗位等级', 0, 24, -1, 70);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C42', '高级岗位', 0, 24, -1, 71);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C43', '中级岗位', 0, 24, -1, 72);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C44', '初级岗位', 0, 24, -1, 73);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C45', '技术工岗位等级', 0, 24, -1, 74);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C46', '是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作', 0, 24, -1, 75);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C47', '新增', 0, 255, -1, 76);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', 'C48', '填报机构代码', 0, 255, -1, 77);

-- ----------------------------
-- Table structure for brmp_conf_origin_system_modelbase
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_modelbase`;
CREATE TABLE `brmp_conf_origin_system_modelbase`  (
  `ORIGIN_SYSTEM_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEL_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型名称',
  `MODEL_TAB_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '源系统建立的模型表名',
  `MODEL_CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '模型创建时间',
  `MODEL_UPDETE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '模型更新时间',
  `MODEL_DESCRIPTION` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型描述',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '状态  0:停用 1:启用',
  `AUDIT_STATUS` int(1) NULL DEFAULT NULL COMMENT '审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接入系统建立模型的配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brmp_conf_origin_system_modelbase
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'd13283af1a9644b48e9d48ff86696f3d', '医护人员信息', 'md_d13283af1a9644b48e9d48ff86696f3d', '2020-04-01 14:48:59', '2020-04-10 14:58:48', '省平台医护人员信息', 1, 9);
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', '2bfbd7d96cbb4397b45900d12356c093', '机构信息', 'md_2bfbd7d96cbb4397b45900d12356c093', '2020-04-07 10:05:58', '2020-04-10 14:58:53', '省平台机构信息', 1, 9);
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('a20cf1c8063d4162aaa335f6e3269bd4', 'a0d377a53d354bdba8c055bd17d274ea', '科室信息', 'md_a0d377a53d354bdba8c055bd17d274ea', '2020-04-07 14:43:20', '2020-04-10 14:58:58', '省平台科室信息', 1, 9);
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('7a61d403eb334f54b01c8a891ec65133', '1850a9d97a764e418743588d15a3adb0', '卫统人力表', 'md_1850a9d97a764e418743588d15a3adb0', '2020-04-13 16:49:58', '2020-04-13 19:54:35', '人力表（不含村卫生室、诊所）', 1, 9);

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
