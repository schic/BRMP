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

 Date: 14/04/2020 12:49:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for md_2bfbd7d96cbb4397b45900d12356c093
-- ----------------------------
DROP TABLE IF EXISTS `md_2bfbd7d96cbb4397b45900d12356c093`;
CREATE TABLE `md_2bfbd7d96cbb4397b45900d12356c093`  (
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `parentId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父机构id',
  `orgCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构代码',
  `orgName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `orgAlias` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构别名',
  `orgAbbreviation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构缩写',
  `admiDivisionCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社区行政区划编码',
  `admiDivisionName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社区行政区划名称',
  `createYear` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建年份',
  `repealYesr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改年份',
  `typeCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态编码',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `provinceCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省级代码',
  `cityCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市代码',
  `areaCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域代码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_2bfbd7d96cbb4397b45900d12356c093_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_2bfbd7d96cbb4397b45900d12356c093_temp`;
CREATE TABLE `md_2bfbd7d96cbb4397b45900d12356c093_temp`  (
  `ZYBZ` int(1) NULL DEFAULT 0 COMMENT '作业标志,用于temp表',
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `parentId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父机构id',
  `orgCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构代码',
  `orgName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `orgAlias` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构别名',
  `orgAbbreviation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构缩写',
  `admiDivisionCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社区行政区划编码',
  `admiDivisionName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社区行政区划名称',
  `createYear` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建年份',
  `repealYesr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改年份',
  `typeCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态编码',
  `status` int(2) NULL DEFAULT NULL COMMENT '状态',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `provinceCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省级代码',
  `cityCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市代码',
  `areaCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域代码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_9fb6518c3e1a439c8e49a16a56ba9791
-- ----------------------------
DROP TABLE IF EXISTS `md_9fb6518c3e1a439c8e49a16a56ba9791`;
CREATE TABLE `md_9fb6518c3e1a439c8e49a16a56ba9791`  (
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `Along1` bigint(10) NULL DEFAULT NULL COMMENT '新加123',
  `aLong2` bigint(18) NULL DEFAULT NULL COMMENT '新增123',
  `test123` float(3, 1) NULL DEFAULT NULL COMMENT '测试123',
  `testint` int(1) NULL DEFAULT NULL COMMENT '测试int',
  `testdouble` double(12, 2) NULL DEFAULT NULL COMMENT '测试double字段',
  `testdate` datetime(0) NULL DEFAULT NULL COMMENT '测试date字段'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_9fb6518c3e1a439c8e49a16a56ba9791_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_9fb6518c3e1a439c8e49a16a56ba9791_temp`;
CREATE TABLE `md_9fb6518c3e1a439c8e49a16a56ba9791_temp`  (
  `ZYBZ` int(1) NULL DEFAULT 0 COMMENT '作业标志,用于temp表',
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `Along1` bigint(10) NULL DEFAULT NULL COMMENT '新加123',
  `aLong2` bigint(18) NULL DEFAULT NULL COMMENT '新增123',
  `test123` float(3, 1) NULL DEFAULT NULL COMMENT '测试123',
  `testint` int(1) NULL DEFAULT NULL COMMENT '测试int',
  `testdouble` double(12, 2) NULL DEFAULT NULL COMMENT '测试double字段',
  `testdate` datetime(0) NULL DEFAULT NULL COMMENT '测试date字段'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_1850a9d97a764e418743588d15a3adb0
-- ----------------------------
DROP TABLE IF EXISTS `md_1850a9d97a764e418743588d15a3adb0`;
CREATE TABLE `md_1850a9d97a764e418743588d15a3adb0`  (
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `BBQ_` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报表期',
  `USERID_` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `BTYPE_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否基层',
  `USERNAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `UPID_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级代码',
  `SHTAG_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `SHTAG2_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `OPTION_` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `USERSHTAG2_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `LOCKOWNER_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加锁状态',
  `SUBMITDATE_` datetime(0) NULL DEFAULT NULL COMMENT '上报时间',
  `STATE_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批状态',
  `PREV_APPROVER_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `PREV_APPROVERNAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `NEXT_APPROVER_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `NEXT_APPROVERNAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等待审批人',
  `TAG_` bigint(10) NULL DEFAULT NULL COMMENT '是否上报',
  `UPID0` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID3` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID4` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID5` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID6` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID7` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID8` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID9` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `C2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `C3` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件种类',
  `C4` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件号码',
  `C5` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `C6` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别代码',
  `C7` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族代码',
  `C8` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参加工作日期',
  `C9` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公室电话号码',
  `C10` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码(单位负责人及应急救治专家填写)',
  `C11` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聘用方式',
  `C12` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获得证书类别',
  `C13` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在科室代码',
  `C14` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室实际名称',
  `C15` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '从事专业类别代码',
  `C16` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医师/卫生监督员执业证书编码',
  `C17` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医师执业类别代码',
  `C18` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医师执业范围代码（可选三个）',
  `C19` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否多地点执业医师',
  `C20` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第2执业单位的机构类别',
  `C21` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第3执业单位的机构类别',
  `C22` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政/业务管理职务代码',
  `C23` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册护士执业证书编码',
  `C24` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业技术资格(评)代码',
  `C25` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业技术职务(聘)代码',
  `C26` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历代码',
  `C27` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学位代码',
  `C28` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业代码',
  `C29` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专科特长(仅要求医院主任、副主任医师填写)',
  `C30` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专科特长(仅要求医院主任、副主任医师填写)',
  `C31` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专科特长(仅要求医院主任、副主任医师填写)',
  `C32` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本年人员流动情况',
  `C33` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进入本单位方式（限政府办机构填写）',
  `C34` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调入/调出时间',
  `C35` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否本单位返聘人员',
  `C36` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制情况',
  `C37` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全科医生取得培训合格证书情况（限参加培训人员填写）',
  `C38` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已取得全科医生培训合格证书的医生执业注册情况',
  `C39` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位类别',
  `C40` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理岗位等级',
  `C42` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高级岗位',
  `C43` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中级岗位',
  `C44` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初级岗位',
  `C45` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术工岗位等级',
  `C46` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作',
  `C47` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `C48` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报机构代码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_1850a9d97a764e418743588d15a3adb0_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_1850a9d97a764e418743588d15a3adb0_temp`;
CREATE TABLE `md_1850a9d97a764e418743588d15a3adb0_temp`  (
  `ZYBZ` int(1) NULL DEFAULT 0 COMMENT '作业标志,用于temp表',
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `BBQ_` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报表期',
  `USERID_` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `BTYPE_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否基层',
  `USERNAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `UPID_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级代码',
  `SHTAG_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `SHTAG2_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `OPTION_` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态',
  `USERSHTAG2_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `LOCKOWNER_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加锁状态',
  `SUBMITDATE_` datetime(0) NULL DEFAULT NULL COMMENT '上报时间',
  `STATE_` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批状态',
  `PREV_APPROVER_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `PREV_APPROVERNAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `NEXT_APPROVER_` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `NEXT_APPROVERNAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等待审批人',
  `TAG_` bigint(10) NULL DEFAULT NULL COMMENT '是否上报',
  `UPID0` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID3` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID4` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID5` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID6` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID7` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID8` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `UPID9` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `C2` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `C3` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件种类',
  `C4` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证件号码',
  `C5` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `C6` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别代码',
  `C7` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族代码',
  `C8` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参加工作日期',
  `C9` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公室电话号码',
  `C10` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码(单位负责人及应急救治专家填写)',
  `C11` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聘用方式',
  `C12` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获得证书类别',
  `C13` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在科室代码',
  `C14` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室实际名称',
  `C15` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '从事专业类别代码',
  `C16` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医师/卫生监督员执业证书编码',
  `C17` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医师执业类别代码',
  `C18` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医师执业范围代码（可选三个）',
  `C19` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否多地点执业医师',
  `C20` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第2执业单位的机构类别',
  `C21` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第3执业单位的机构类别',
  `C22` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行政/业务管理职务代码',
  `C23` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册护士执业证书编码',
  `C24` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业技术资格(评)代码',
  `C25` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业技术职务(聘)代码',
  `C26` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历代码',
  `C27` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学位代码',
  `C28` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所学专业代码',
  `C29` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专科特长(仅要求医院主任、副主任医师填写)',
  `C30` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专科特长(仅要求医院主任、副主任医师填写)',
  `C31` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专科特长(仅要求医院主任、副主任医师填写)',
  `C32` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本年人员流动情况',
  `C33` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进入本单位方式（限政府办机构填写）',
  `C34` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调入/调出时间',
  `C35` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否本单位返聘人员',
  `C36` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制情况',
  `C37` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全科医生取得培训合格证书情况（限参加培训人员填写）',
  `C38` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已取得全科医生培训合格证书的医生执业注册情况',
  `C39` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位类别',
  `C40` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理岗位等级',
  `C42` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '高级岗位',
  `C43` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中级岗位',
  `C44` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初级岗位',
  `C45` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '技术工岗位等级',
  `C46` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否由乡镇卫生院或社区卫生服务机构派驻村卫生室工作',
  `C47` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新增',
  `C48` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '填报机构代码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_a0d377a53d354bdba8c055bd17d274ea
-- ----------------------------
DROP TABLE IF EXISTS `md_a0d377a53d354bdba8c055bd17d274ea`;
CREATE TABLE `md_a0d377a53d354bdba8c055bd17d274ea`  (
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `orgId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构ID',
  `parentId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上一级部门',
  `deptCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `deptName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `detailAddress` varchar(499) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在地址',
  `medicalSubjectsCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊疗科目代码',
  `medicalSubjectsName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊疗科目名称',
  `active` int(2) NULL DEFAULT NULL COMMENT '是否在用（状态） 0 停用 1 启用',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '新增创建日期'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_a0d377a53d354bdba8c055bd17d274ea_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_a0d377a53d354bdba8c055bd17d274ea_temp`;
CREATE TABLE `md_a0d377a53d354bdba8c055bd17d274ea_temp`  (
  `ZYBZ` int(1) NULL DEFAULT 0 COMMENT '作业标志,用于temp表',
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `orgId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构ID',
  `parentId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上一级部门',
  `deptCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `deptName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `detailAddress` varchar(499) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在地址',
  `medicalSubjectsCode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊疗科目代码',
  `medicalSubjectsName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊疗科目名称',
  `active` int(2) NULL DEFAULT NULL COMMENT '是否在用（状态） 0 停用 1 启用',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '新增创建日期'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_d13283af1a9644b48e9d48ff86696f3d
-- ----------------------------
DROP TABLE IF EXISTS `md_d13283af1a9644b48e9d48ff86696f3d`;
CREATE TABLE `md_d13283af1a9644b48e9d48ff86696f3d`  (
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `orgCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构代码',
  `orgName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `jobNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `postTypeCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位类别 ',
  `empTypeCode` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员性质',
  `comments` varchar(499) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `active` int(1) NULL DEFAULT NULL COMMENT '状态（是否在用 ）1 启用，0 停用',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `genderCode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthDate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `maritalStCode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `idNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `liveAddr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `workDate` datetime(0) NULL DEFAULT NULL COMMENT '参加工作数据',
  `graduatedSchool` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业学校'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for md_d13283af1a9644b48e9d48ff86696f3d_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_d13283af1a9644b48e9d48ff86696f3d_temp`;
CREATE TABLE `md_d13283af1a9644b48e9d48ff86696f3d_temp`  (
  `ZYBZ` int(1) NULL DEFAULT 0 COMMENT '作业标志,用于temp表',
  `xgbz` int(1) NULL DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '记录生成日期',
  `orgCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构代码',
  `orgName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `jobNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `postTypeCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位类别 ',
  `empTypeCode` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员性质',
  `comments` varchar(499) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `active` int(1) NULL DEFAULT NULL COMMENT '状态（是否在用 ）1 启用，0 停用',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `genderCode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthDate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `maritalStCode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `idNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `liveAddr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `workDate` datetime(0) NULL DEFAULT NULL COMMENT '参加工作数据',
  `graduatedSchool` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业学校'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
