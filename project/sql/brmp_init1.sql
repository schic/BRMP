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

 Date: 23/04/2020 16:07:07
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
INSERT INTO `brmp_conf_origin_system_info` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', 'testZSperson', '执业三证_机构医师护士', '-', 'testZSperson', '123456');

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
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'XingMing', '姓名', 0, 50, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'XingBie', '性别', 0, 20, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'ShenFenZhengHao', '身份证号', 0, 50, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'ZhengShuBianMa', '证书编码', 0, 50, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'QianFaRiQi', '签发日期', 3, -1, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'YiShiZiGeZhengShuBianMa', '医师资格证书编码', 0, 50, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'ZhiYeLeiBie', '执业类别', 0, 50, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'ZhiYeFanWei', '执业范围', 0, 200, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'ZhiYeDiDian', '执业地点', 0, 200, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'FaZhengJiGuan', '发证机关', 0, 100, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengXiangMuYi', '变更项目一', 0, 200, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengRiQiYi', '变更日期一', 3, -1, -1, 16);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'PiZhunJiGuanYi', '批准机关一', 0, 50, -1, 17);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengXiangMuEr', '变更项目二', 0, 200, -1, 18);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengRiQiEr', '变更日期二', 3, -1, -1, 19);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'PiZhunJiGuanEr', '批准机关二', 0, 50, -1, 20);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengXiangMuSan', '变更项目三', 0, 200, -1, 21);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengRiQiSan', '变更日期三', 3, -1, -1, 22);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'PiZhunJiGuanSan', '批准机关三', 0, 50, -1, 23);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengXiangMuSi', '变更项目四', 0, 200, -1, 24);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BianGengRiQiSi', '变更日期四', 3, -1, -1, 25);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'PiZhunJiGuanSi', '批准机关四', 0, 50, -1, 26);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'BeiZhu', '备注', 0, 1000, -1, 27);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', 'bgzt', '变更状态', 0, 1, -1, 28);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'FaZhengJiGuan', '发证机关', 0, 50, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'FaZhengRiQi', '发证日期', 3, -1, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'QianFaRen', '签发人', 0, 20, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'XingMing', '姓名', 0, 50, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'ChuShengRiQi', '出生日期', 3, -1, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'XingBie', '性别', 0, 2, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'GuoJi', '国籍', 0, 100, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'ZhiYeDiDian', '执业地点', 0, 200, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'HuShiZhiYeZhengShuBianHao', '护士执业证书编号', 0, 100, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'ShenFenZhengHao', '身份证号', 0, 50, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'ZhuCeRiQi', '注册日期', 3, -1, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'ZhuCeYouXiaoQiZhi', '注册有效期至', 3, -1, -1, 16);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeRiQiYi', '延续注册日期一', 3, -1, -1, 17);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeYouXiaoQiZhiYi', '延续注册有效期至一', 3, -1, -1, 18);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeRiQiEr', '延续注册日期二', 3, -1, -1, 19);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeYouXiaoQiZhiEr', '延续注册有效期至二', 3, -1, -1, 20);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeRiQiSan', '延续注册日期三', 3, -1, -1, 21);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeYouXiaoQiZhiSan', '延续注册有效期至三', 3, -1, -1, 22);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeRiQiSi', '延续注册日期四', 3, -1, -1, 23);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'YanXuZhuCeYouXiaoQiZhiSi', '延续注册有效期至四', 3, -1, -1, 24);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengRiQiYi', '变更日期一', 3, -1, -1, 25);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengShiXiangYi', '变更事项一', 0, 200, -1, 26);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengRiQiEr', '变更日期二', 3, -1, -1, 27);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengShiXiangEr', '变更事项二', 0, 200, -1, 28);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengRiQiSan', '变更日期三', 3, -1, -1, 29);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengShiXiangSan', '变更事项三', 0, 200, -1, 30);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengRiQiSi', '变更日期四', 3, -1, -1, 31);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'BianGengShiXiangSi', '变更事项四', 0, 200, -1, 32);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', 'bgzt', '变更状态', 0, 1, -1, 33);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'JiGouMingChen', '机构名称', 0, 100, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'DiZhi', '地址', 0, 400, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'ZhenLiaoKeMu', '诊疗科目', 0, 2000, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'FaDingDaiBiaoRen', '法定代表人', 0, 100, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'ZhuYaoFuZeRen', '主要负责人', 0, 100, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'DengJiHao', '登记号', 0, 100, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'YouXiaoQiZi', '有效期自', 3, -1, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'YouXiaoQiZhi', '有效期至', 3, -1, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'FaZhengJiGuan', '发证机关 ', 0, 100, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'FaZhengRiQi', '发证日期', 3, -1, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', 'bgzt', '变更状态', 0, 1, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'xgbz', '修改标志', 1, 1, -1, 1);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'Id', '模型中本记录主键', 0, 64, -1, 2);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'originId', '记录关联人(或物)的主键', 0, 64, -1, 3);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'updateTime', '记录生成日期', 3, -1, -1, 4);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'CAPTION', '机构名称', 0, 100, -1, 5);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'PARENT', '上级机构ID', 0, 50, -1, 6);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DESCRIPTION', '新增', 0, 4000, -1, 7);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'GOVERNOR', '新增', 0, 50, -1, 8);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TEL', '新增', 0, 30, -1, 9);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ISJC', '新增', 0, 1, -1, 10);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ENABLED', '新增', 4, 18, -1, 11);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID0', '卫生部机构Id', 0, 20, -1, 12);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID1', '省机构ID', 0, 20, -1, 13);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID2', '市机构ID', 0, 20, -1, 14);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID3', '区市县机构ID', 0, 20, -1, 15);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID4', '新增', 0, 20, -1, 16);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID5', '新增', 0, 20, -1, 17);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID6', '新增', 0, 20, -1, 18);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID7', '新增', 0, 20, -1, 19);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID8', '新增', 0, 20, -1, 20);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'UPID9', '新增', 0, 20, -1, 21);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DBH', '新增', 0, 1, -1, 22);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'JGBD', '新增', 0, 5, -1, 23);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_CODE', '机构代码', 0, 20, -1, 24);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'JGFLGL', '新增', 0, 1, -1, 25);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'JJLX', '新增', 0, 2, -1, 26);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_ADRRESSCODE', '行政区划代码（区市县）', 0, 6, -1, 27);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_CLASS', '机构分类', 0, 50, -1, 28);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_XZJDCODE', '行政区划代码（镇）', 0, 20, -1, 29);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TX_DZ', '地址', 0, 200, -1, 30);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TX_YZBM', '邮政编码', 0, 6, -1, 31);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TX_DHHM', '联系电话', 0, 20, -1, 32);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TX_EMAIL', '电子邮箱', 0, 50, -1, 33);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TX_WEBSITE', '单位网站域名', 0, 50, -1, 34);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'CLSJ', '新增', 0, 4, -1, 35);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'FZR', '新增', 0, 40, -1, 36);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ZCZJ', '新增', 5, 13, 2, 37);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'YYDJ_J', '新增', 0, 2, -1, 38);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'YYDJ_D', '新增', 0, 2, -1, 39);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ZBDW', '新增', 0, 2, -1, 40);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'LSGX', '新增', 0, 2, -1, 41);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'FZJG', '新增', 0, 1, -1, 42);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'PZWH', '新增', 0, 30, -1, 43);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DJPZJG', '登记批准机构', 0, 60, -1, 44);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'BZRQ', '办证日期', 0, 15, -1, 45);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ZFRQ', '新增', 0, 15, -1, 46);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'JBR', '经办人', 0, 20, -1, 47);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'LLR', '新增', 0, 20, -1, 48);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'CJSJ', '新增', 3, -1, -1, 49);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DBH2', '新增', 0, 1, -1, 50);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DBH3', '新增', 0, 1, -1, 51);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_CDBJG', '新增', 0, 20, -1, 52);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'LASTUPDATE_', '新增', 3, -1, -1, 53);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'FROMDATE_', '新增', 0, 8, -1, 54);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'TODATE_', '新增', 0, 8, -1, 55);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_ZSDBJG', '新增', 0, 20, -1, 56);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'CREATETIME_', '新增', 3, -1, -1, 57);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'LOCKOWNER_', '新增', 0, 100, -1, 58);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'SYCW', '新增', 0, 12, -1, 59);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'JSFP01', '新增', 0, 1, -1, 60);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ISJZ', '新增', 0, 1, -1, 61);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'DEPT_CWHCODE', '新增', 0, 22, -1, 62);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'YYDJ_PZWH', '新增', 0, 60, -1, 63);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'YYDJ_PZWH_TIME', '新增', 0, 20, -1, 64);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'A41', '新增', 5, 18, 2, 65);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'PTYLJGDM', '新增', 0, 40, -1, 66);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'INDEX_', '机构表主键', 4, 18, -1, 67);
INSERT INTO `brmp_conf_origin_system_model` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', 'ID_ORG', '机构ID', 0, 50, -1, 68);

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
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '74a2fcd44e774ffb8150e6f334b5ca6f', '医师执业证书', 'md_74a2fcd44e774ffb8150e6f334b5ca6f', '2020-04-14 18:19:46', '2020-04-15 16:00:15', '医师执业证书证照', 1, 9);
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '4af15c77fa3445dabff50fe08b96556b', '护士执业证书', 'md_4af15c77fa3445dabff50fe08b96556b', '2020-04-14 18:43:41', '2020-04-15 16:00:26', '护士执业证书证照', 1, 9);
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('2c1c35e2fb8f46c78ca5d170b864946c', '56cbefa85dce456f845a8e94cc06d444', '医疗机构执业许可证', 'md_56cbefa85dce456f845a8e94cc06d444', '2020-04-14 18:56:18', '2020-04-15 16:00:34', '医疗机构执业许可证证照', 1, 9);
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('7a61d403eb334f54b01c8a891ec65133', '469debc0190f45cebfcdd70af438ea19', '卫统机构表', 'md_469debc0190f45cebfcdd70af438ea19', '2020-04-17 14:46:53', '2020-04-17 18:49:19', '卫统机构表', 1, 9);

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

SET FOREIGN_KEY_CHECKS = 1;
