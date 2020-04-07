/*
Navicat MySQL Data Transfer

Source Server         : localhost_root
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : brmp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-03-20 18:11:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brmp_apply_attribute
-- ----------------------------
DROP TABLE IF EXISTS `brmp_apply_attribute`;
CREATE TABLE `brmp_apply_attribute` (
  `apply_id` varchar(32) DEFAULT NULL COMMENT '申请id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '模型id',
  `model_col_name` varchar(255) DEFAULT NULL COMMENT '模型字段名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for brmp_apply_base
-- ----------------------------
DROP TABLE IF EXISTS `brmp_apply_base`;
CREATE TABLE `brmp_apply_base` (
  `apply_id` varchar(32) NOT NULL COMMENT '申请id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '源系统建立的模型编号(申请主资源编号 一个主要资源)',
  `user_name` varchar(255) DEFAULT NULL COMMENT '申请用户登录用户名(或其他登录用唯一号)',
  `apply_name` varchar(255) DEFAULT NULL COMMENT '申请资源目录名称（申请名称）',
  `apply_org_name` varchar(255) DEFAULT NULL COMMENT '申请机构名称',
  `apply_user` varchar(255) DEFAULT NULL COMMENT '申请人姓名',
  `apply_direction` varchar(2048) DEFAULT NULL COMMENT '申请用途描述说明',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `audit_status` int(1) DEFAULT NULL COMMENT '审核状态  1:待审核 2:审核拒绝 9:审核通过',
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brmp_apply_base
-- ----------------------------
INSERT INTO `brmp_apply_base` VALUES ('2d3ecac70aab4e4788e419b244336d95', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '测试多模型资源申请', 'testUser', 'testUser', '测试多模型资源申请', '2020-03-09 21:23:35', '1');
INSERT INTO `brmp_apply_base` VALUES ('4d43453e2cc24921a243a50bf18516e9', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '测试多资源申请2', 'testUser', 'testUser', '测试多资源申请2', '2020-03-09 21:26:59', '9');
INSERT INTO `brmp_apply_base` VALUES ('70af63c430fe4e0ca212472c89c39f8e', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', 'test资源目录名称', 'testUser', 'testUser', '-', '2020-01-03 11:55:42', '2');
INSERT INTO `brmp_apply_base` VALUES ('98921b573974490d8f07a02bc4c76a70', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '123', 'testUser', 'testUser', '123', '2020-01-03 12:23:08', '9');
INSERT INTO `brmp_apply_base` VALUES ('e4f78305e184435cb5220bbcee342428', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '目录名称测试1', 'testUser1', 'testUser1', '-', '2020-01-03 12:09:41', '2');
INSERT INTO `brmp_apply_base` VALUES ('ec547e47e238451dafc92254e4375ef6', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '123123', 'testUser1', 'testUser1', '测试资源用途说明', '2020-01-03 12:29:09', '1');
INSERT INTO `brmp_apply_base` VALUES ('f009185eb7d140f0a65ea7344699d190', '9fb6518c3e1a439c8e49a16a56ba9791', 'testUser', '目录名称testuser123', 'testUser', 'testUser', '用途说明123', '2020-01-07 18:56:44', '1');

-- ----------------------------
-- Table structure for brmp_conf_origin_system_info
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_info`;
CREATE TABLE `brmp_conf_origin_system_info` (
  `ORIGIN_SYSTEM_ID` varchar(32) DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_SYSTEM_NAME` varchar(255) DEFAULT NULL COMMENT '源系统名称',
  `ORIGIN_SYSTEM_CNAME` varchar(255) DEFAULT NULL COMMENT '源系统名称中文或别名',
  `ORIGIN_SYSTEM_URL` varchar(1024) DEFAULT NULL COMMENT '接入系统接口的URL',
  `USERNAME` varchar(255) DEFAULT NULL COMMENT '接入系统用户名',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '接入系统验证'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接入系统基本信息表';

-- ----------------------------
-- Records of brmp_conf_origin_system_info
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_info` VALUES ('1', 'admin', 'EMPI中心系统', 'http://127.0.0.1:8980/EMPI_center/webservice/EMPIObj/reqEMPIObj/', 'admin', 'admin');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('testA001', 'test_system1', '测试接入系统1', 'http://127.0.0.1:8980/BRMP_client/', 'test_system1', '1234567890');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('test002', 'test_system2', '测试接入系统2', 'http://127.0.0.1:8980/BRMP_client/', 'test_system2', '123');
INSERT INTO `brmp_conf_origin_system_info` VALUES ('36fd23db443c4fdb9af7aaf01b851a07', 'testSystem', '全民健康信息平台', 'http', 'testSystem', '1234567890');

-- ----------------------------
-- Table structure for brmp_conf_origin_system_model
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_model`;
CREATE TABLE `brmp_conf_origin_system_model` (
  `ORIGIN_SYSTEM_ID` varchar(32) DEFAULT '-1' COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEl_COL_NAME` varchar(255) DEFAULT NULL COMMENT '模型提供的字段',
  `MODEl_COL_DISPLAY_NAME` varchar(255) DEFAULT NULL COMMENT '模型字段展示名称',
  `MODEL_COL_TYPE` int(3) DEFAULT '0' COMMENT '字段类型 0:字符串 1:整数 2:浮点数 3:日期',
  `MODEL_COL_LENTH` int(11) DEFAULT '-1' COMMENT '字段长度',
  `MODEL_COL_DECIMAL_LENTH` int(11) DEFAULT '-1' COMMENT '小数长度',
  `DISPLAY_ORDER` int(3) DEFAULT '-1' COMMENT '展示顺序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接入系统建立模型的配置表';

-- ----------------------------
-- Records of brmp_conf_origin_system_model
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', 'test123456789', 'testString23', '测试字符串23', '0', '225', '-1', '1');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', 'test123456789', 'testString1', '测试字符串1', '0', '225', '-1', '2');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', 'test123456789', 'testfloat1', '测试float1', '2', '7', '2', '3');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', 'test123456789', 'testDate1', '测试Date1', '3', '-1', '-1', '4');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', 'test123456789', 'testInt1', '测试int1', '1', '2', '-1', '5');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test002', 'test002ABCDEF', 'test2String', '测试2字符串', '0', '120', '-1', '1');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test002', 'test002ABCDEF', 'test2String1', '测试2字符串1', '0', '50', '-1', '2');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test002', 'test002ABCDEF', 'test2Date1', '测试2Date1', '3', '-1', '-1', '3');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test002', 'test002ABCDEF', 'test2Int1', '测试2int1', '1', '2', '-1', '4');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('test002', 'test002ABCDEF', 'testinsert', '测试新增', '2', '5', '2', '5');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'xgbz', '修改标志', '1', '1', '-1', '1');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'Id', '模型中本记录主键', '0', '64', '-1', '2');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'originId', '记录关联人(或物)的主键', '0', '64', '-1', '3');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'updateTime', '记录生成日期', '3', '-1', '-1', '4');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'Along1', '新加123', '4', '10', '-1', '5');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'aLong2', '新增123', '4', '18', '-1', '6');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'test123', '测试123', '2', '3', '1', '7');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'testint', '测试int', '1', '1', '-1', '8');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdouble', '测试double字段', '5', '12', '2', '9');
INSERT INTO `brmp_conf_origin_system_model` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', 'testdate', '测试date字段', '3', '-1', '-1', '10');

-- ----------------------------
-- Table structure for brmp_conf_origin_system_modelbase
-- ----------------------------
DROP TABLE IF EXISTS `brmp_conf_origin_system_modelbase`;
CREATE TABLE `brmp_conf_origin_system_modelbase` (
  `ORIGIN_SYSTEM_ID` varchar(32) DEFAULT '' COMMENT '源系统名称编号',
  `MODEL_ID` varchar(32) DEFAULT NULL COMMENT '源系统建立的模型编号',
  `MODEL_NAME` varchar(255) DEFAULT NULL COMMENT '源系统建立的模型名称',
  `MODEL_TAB_NAME` varchar(255) DEFAULT NULL COMMENT '源系统建立的模型表名',
  `MODEL_CREATE_TIME` datetime DEFAULT NULL COMMENT '模型创建时间',
  `MODEL_UPDETE_TIME` datetime DEFAULT NULL COMMENT '模型更新时间',
  `MODEL_DESCRIPTION` varchar(4000) DEFAULT NULL COMMENT '模型描述',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态  0:停用 1:启用',
  `AUDIT_STATUS` int(1) DEFAULT NULL COMMENT '审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接入系统建立模型的配置表';

-- ----------------------------
-- Records of brmp_conf_origin_system_modelbase
-- ----------------------------
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('testA001', 'test123456789', 'TestModel1', 'MODELTEST1', '2019-11-19 09:59:46', '2019-12-02 17:57:09', '这个是测试模型1', '1', '9');
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('test002', 'test002ABCDEF', 'Tes2Model2', 'MODELTEST2', '2019-11-19 09:59:46', '2019-12-06 12:42:59', '这个是测试模型2', '1', '9');
INSERT INTO `brmp_conf_origin_system_modelbase` VALUES ('testA001', '9fb6518c3e1a439c8e49a16a56ba9791', '测试新建模型1', 'md_9fb6518c3e1a439c8e49a16a56ba9791', '2019-11-29 10:02:57', '2019-12-16 12:17:33', '模型描述测试新建模型123', '1', '9');

-- ----------------------------
-- Table structure for brmp_dic_datatype
-- ----------------------------
DROP TABLE IF EXISTS `brmp_dic_datatype`;
CREATE TABLE `brmp_dic_datatype` (
  `MODEL_COL_TYPE` int(11) DEFAULT NULL COMMENT '字段类型编号',
  `DATATYPE` varchar(255) DEFAULT NULL COMMENT '字段类型名称',
  `JAVA_DATATYPE` varchar(255) DEFAULT NULL COMMENT '对应程序中使用的字段类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brmp_dic_datatype
-- ----------------------------
INSERT INTO `brmp_dic_datatype` VALUES ('0', 'varchar', 'String');
INSERT INTO `brmp_dic_datatype` VALUES ('1', 'int', 'int');
INSERT INTO `brmp_dic_datatype` VALUES ('2', 'float', 'float');
INSERT INTO `brmp_dic_datatype` VALUES ('3', 'datetime', 'Date');
INSERT INTO `brmp_dic_datatype` VALUES ('4', 'bigint', 'long');
INSERT INTO `brmp_dic_datatype` VALUES ('5', 'double', 'double');

-- ----------------------------
-- Table structure for brmp_grxx
-- ----------------------------
DROP TABLE IF EXISTS `brmp_grxx`;
CREATE TABLE `brmp_grxx` (
  `RECORDID` varchar(32) DEFAULT NULL COMMENT '记录唯一号',
  `OBJ_ID` varchar(32) DEFAULT NULL COMMENT '个人(物)唯一主索引',
  `SFZH` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) DEFAULT NULL COMMENT '性别',
  `CSRQ` date DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) DEFAULT NULL COMMENT '联系人电话',
  `ZQDDF` int(3) DEFAULT '-1' COMMENT '信息准确度得分',
  `GXRQ` datetime DEFAULT NULL COMMENT '记录更新日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主索引终表';

-- ----------------------------
-- Records of brmp_grxx
-- ----------------------------
INSERT INTO `brmp_grxx` VALUES ('6f019a14a425326a9d010fa0ba7ef1f3', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试人员1', '2', '1987-06-30', '-', '四川', '-', '-', '62', '2019-10-24 17:05:13');
INSERT INTO `brmp_grxx` VALUES ('d6847005512c34dbbd943441c1b879df', '7abed33fc4c347ea8209f65ff10cd90e', '521211198807313945', '测试人员2', '2', '1988-07-01', '13133334444', '成都', '-', '-', '75', '2019-10-24 17:05:13');
INSERT INTO `brmp_grxx` VALUES ('deec237a6bdd350d8a2fd60f4f746d1a', '4745752f1d0e4682ab1a7ba167903cb4', '521311197611264522', '测试人员3', '1', '1976-11-26', '18055556666', '绵阳', '赵XX', '1234567', '51', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx` VALUES ('1cd41c49716d3178aca8aaa498cb2a91', 'caa016fb60f04816a4a932bc37c76f1d', '-', '-', '-', '1900-01-01', '-', '-', '-', '-', '25', '2019-10-24 17:50:55');

-- ----------------------------
-- Table structure for brmp_grxx_cache
-- ----------------------------
DROP TABLE IF EXISTS `brmp_grxx_cache`;
CREATE TABLE `brmp_grxx_cache` (
  `RECORDID` varchar(32) DEFAULT NULL COMMENT '记录唯一号',
  `OBJ_ID` varchar(32) DEFAULT NULL COMMENT '个人(物)唯一主索引',
  `SFZH` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) DEFAULT NULL COMMENT '性别',
  `CSRQ` date DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) DEFAULT NULL COMMENT '联系人电话',
  `ORIGIN_SYSTEM_ID` varchar(32) DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_ID` varchar(255) DEFAULT NULL COMMENT '源系统主键值',
  `ZQDDF` int(3) DEFAULT '-1' COMMENT '信息准确度得分',
  `GXRQ` datetime DEFAULT NULL COMMENT '记录更新日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主索引关联关系总表';

-- ----------------------------
-- Records of brmp_grxx_cache
-- ----------------------------
INSERT INTO `brmp_grxx_cache` VALUES ('5c836699d5cb3d76a0db1312cf12250f', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试人员1', '2', '1987-06-30', '13011112222', '四川', '王XX', '-', 'testA001', 'test001', '62', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('6f019a14a425326a9d010fa0ba7ef1f3', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试人员1', '2', '1987-06-30', '-', '四川', '-', '-', 'testA001', 'test002', '62', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('804d819085e03e6a964ea1b9f2cd191e', '7abed33fc4c347ea8209f65ff10cd90e', '521211198807313945', '测试人员2', '1', '1988-07-31', '13133334444', '成都', '李XX', '9090980', 'testA001', 'test003', '75', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('d6847005512c34dbbd943441c1b879df', '7abed33fc4c347ea8209f65ff10cd90e', '521211198807313945', '测试人员2', '2', '1988-07-01', '13133334444', '成都', '-', '-', 'testA001', 'test004', '75', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('b05eb1d97b2139f7ba1d1c245867eb67', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试错误姓名1', '2', '1987-06-30', '-', '自贡', '-', '-', 'testA001', 'test005', '51', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('deec237a6bdd350d8a2fd60f4f746d1a', '4745752f1d0e4682ab1a7ba167903cb4', '521311197611264522', '测试人员3', '1', '1976-11-26', '18055556666', '绵阳', '赵XX', '1234567', 'testA001', 'test006', '51', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('f5db176268ba32e7b94a52ef1786a533', 'd48672e421114ecd9871bbf703959ec8', '521203198706302947', '测试错误姓名1', '2', '1900-01-01', '-', '-', '-', '-', 'testA001', 'test007', '51', '2019-10-24 17:50:55');
INSERT INTO `brmp_grxx_cache` VALUES ('1cd41c49716d3178aca8aaa498cb2a91', 'caa016fb60f04816a4a932bc37c76f1d', '-', '-', '-', '1900-01-01', '-', '-', '-', '-', 'testA001', 'testNull', '25', '2019-10-24 17:50:55');

-- ----------------------------
-- Table structure for brmp_grxx_inter
-- ----------------------------
DROP TABLE IF EXISTS `brmp_grxx_inter`;
CREATE TABLE `brmp_grxx_inter` (
  `RECORDID` varchar(32) DEFAULT NULL COMMENT '记录唯一号',
  `SFZH` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) DEFAULT NULL COMMENT '性别',
  `CSRQ` date DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) DEFAULT NULL COMMENT '联系人电话',
  `ORIGIN_SYSTEM_ID` varchar(32) DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_ID` varchar(255) DEFAULT NULL COMMENT '源系统主键值',
  `BZ` int(11) DEFAULT '0' COMMENT '数据作业标志 默认0 为未作业数据 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主索引临时插入计算表';

-- ----------------------------
-- Records of brmp_grxx_inter
-- ----------------------------

-- ----------------------------
-- Table structure for brmp_relate_index
-- ----------------------------
DROP TABLE IF EXISTS `brmp_relate_index`;
CREATE TABLE `brmp_relate_index` (
  `RECORDID` varchar(32) DEFAULT NULL COMMENT '记录唯一号',
  `OBJ_ID` varchar(32) DEFAULT NULL COMMENT '资源唯一主索引',
  `OBJ_NAME` varchar(255) DEFAULT NULL COMMENT '名称(姓名)',
  `ORIGIN_SYSTEM_ID` varchar(32) DEFAULT NULL COMMENT '源系统名称编号',
  `ORIGIN_SYSTEM_MAIN_COLNAME` varchar(255) DEFAULT NULL COMMENT '源系统主键列名',
  `ORIGIN_SYSTEM_DATE` datetime DEFAULT NULL COMMENT '源系统本记录日期',
  `IS_PERSON` int(1) DEFAULT NULL COMMENT '是否是个人主索引 1: 是 0: 否'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='本表设计为一种可能,用于不存接入系统的数据.';

-- ----------------------------
-- Records of brmp_relate_index
-- ----------------------------

-- ----------------------------
-- Table structure for md_9fb6518c3e1a439c8e49a16a56ba9791
-- ----------------------------
DROP TABLE IF EXISTS `md_9fb6518c3e1a439c8e49a16a56ba9791`;
CREATE TABLE `md_9fb6518c3e1a439c8e49a16a56ba9791` (
  `xgbz` int(1) DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime DEFAULT NULL COMMENT '记录生成日期',
  `Along1` bigint(10) DEFAULT NULL COMMENT '新加123',
  `aLong2` bigint(18) DEFAULT NULL COMMENT '新增123',
  `test123` float(3,1) DEFAULT NULL COMMENT '测试123',
  `testint` int(1) DEFAULT NULL COMMENT '测试int',
  `testdouble` double(12,2) DEFAULT NULL COMMENT '测试double字段',
  `testdate` datetime DEFAULT NULL COMMENT '测试date字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of md_9fb6518c3e1a439c8e49a16a56ba9791
-- ----------------------------
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791` VALUES ('0', 'af93e34c2d1', 'personId1', '2019-09-01 13:22:01', '1', '1', '0.5', '3', '0.50', '2019-12-26 16:40:24');

-- ----------------------------
-- Table structure for md_9fb6518c3e1a439c8e49a16a56ba9791_temp
-- ----------------------------
DROP TABLE IF EXISTS `md_9fb6518c3e1a439c8e49a16a56ba9791_temp`;
CREATE TABLE `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` (
  `ZYBZ` int(1) DEFAULT '0' COMMENT '作业标志,用于temp表',
  `xgbz` int(1) DEFAULT NULL COMMENT '修改标志',
  `Id` varchar(64) DEFAULT NULL COMMENT '模型中本记录主键',
  `originId` varchar(64) DEFAULT NULL COMMENT '记录关联人(或物)的主键',
  `updateTime` datetime DEFAULT NULL COMMENT '记录生成日期',
  `Along1` bigint(10) DEFAULT NULL COMMENT '新加123',
  `aLong2` bigint(18) DEFAULT NULL COMMENT '新增123',
  `test123` float(3,1) DEFAULT NULL COMMENT '测试123',
  `testint` int(1) DEFAULT NULL COMMENT '测试int',
  `testdouble` double(12,2) DEFAULT NULL COMMENT '测试double字段',
  `testdate` datetime DEFAULT NULL COMMENT '测试date字段'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of md_9fb6518c3e1a439c8e49a16a56ba9791_temp
-- ----------------------------
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` VALUES ('0', '-1', 'af93e34c2d1', 'personId1', '2019-09-01 13:22:01', '1', '1', '0.5', '1', '0.50', '2019-12-04 02:04:05');
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` VALUES ('0', '-1', 'af93e34c2d2', 'personId2', '2019-09-02 14:23:08', '2', '2', '0.6', '2', '0.60', '2019-12-05 03:16:08');
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` VALUES ('0', '-1', 'af93e34c2d1', 'personId1', '2019-09-01 13:22:01', '1', '1', '0.5', '1', '0.50', '2019-12-04 02:04:05');
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` VALUES ('0', '-1', 'af93e34c2d2', 'personId2', '2019-09-02 14:23:08', '2', '2', '0.6', '2', '0.60', '2019-12-05 03:16:08');
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` VALUES ('0', '-1', 'af93e34c2d1', 'personId1', '2019-09-01 13:22:01', '1', '1', '0.5', '1', '0.50', '2019-12-04 02:04:05');
INSERT INTO `md_9fb6518c3e1a439c8e49a16a56ba9791_temp` VALUES ('0', '-1', 'af93e34c2d2', 'personId2', '2019-09-02 14:23:08', '2', '2', '0.6', '2', '0.60', '2019-12-05 03:16:08');

-- ----------------------------
-- Table structure for modeltest1_temp
-- ----------------------------
DROP TABLE IF EXISTS `modeltest1_temp`;
CREATE TABLE `modeltest1_temp` (
  `ZYBZ` int(1) DEFAULT '0' COMMENT '作业标志,用于temp表',
  `testString23` varchar(225) DEFAULT NULL COMMENT '测试字符串23',
  `testString1` varchar(225) DEFAULT NULL COMMENT '测试字符串1',
  `testfloat1` float(7,2) DEFAULT NULL COMMENT '测试float1',
  `testDate1` datetime DEFAULT NULL COMMENT '测试Date1',
  `testInt1` int(2) DEFAULT NULL COMMENT '测试int1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of modeltest1_temp
-- ----------------------------

-- ----------------------------
-- Table structure for modeltest2_temp
-- ----------------------------
DROP TABLE IF EXISTS `modeltest2_temp`;
CREATE TABLE `modeltest2_temp` (
  `ZYBZ` int(1) DEFAULT '0' COMMENT '作业标志,用于temp表',
  `test2String` varchar(120) DEFAULT NULL COMMENT '测试2字符串',
  `test2String1` varchar(50) DEFAULT NULL COMMENT '测试2字符串1',
  `test2Date1` datetime DEFAULT NULL COMMENT '测试2Date1',
  `test2Int1` int(2) DEFAULT NULL COMMENT '测试2int1',
  `testinsert` float(5,2) DEFAULT NULL COMMENT '测试新增'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of modeltest2_temp
-- ----------------------------

-- ----------------------------
-- Table structure for test_system1_grxx
-- ----------------------------
DROP TABLE IF EXISTS `test_system1_grxx`;
CREATE TABLE `test_system1_grxx` (
  `SFZH` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `XM` varchar(255) DEFAULT NULL COMMENT '姓名',
  `XB` varchar(2) DEFAULT NULL COMMENT '性别',
  `CSRQ` date DEFAULT NULL COMMENT '出生日期',
  `SJHM` varchar(56) DEFAULT NULL COMMENT '手机号码',
  `JTDZ` varchar(4000) DEFAULT NULL COMMENT '家庭地址',
  `LXR` varchar(255) DEFAULT NULL COMMENT '联系人',
  `LXRDH` varchar(56) DEFAULT NULL COMMENT '联系人电话',
  `ORIGIN_ID` varchar(255) DEFAULT NULL COMMENT '源系统主键值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='测试接入系统源数据';

-- ----------------------------
-- Records of test_system1_grxx
-- ----------------------------
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试人员1', '2', '1987-06-30', '13011112222', '四川', '王XX', null, 'test001');
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试人员1', '2', '1987-06-30', null, '四川', null, null, 'test002');
INSERT INTO `test_system1_grxx` VALUES ('521211198807313945', '测试人员2', '1', '1988-07-31', '13133334444', '成都', '李XX', '9090980', 'test003');
INSERT INTO `test_system1_grxx` VALUES ('521211198807313945', '测试人员2', '2', '1988-07-01', '13133334444', '成都', null, null, 'test004');
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试错误姓名1', '2', '1987-06-30', null, '自贡', null, null, 'test005');
INSERT INTO `test_system1_grxx` VALUES ('521311197611264522', '测试人员3', '1', '1976-11-26', '18055556666', '绵阳', '赵XX', '1234567', 'test006');
INSERT INTO `test_system1_grxx` VALUES ('521203198706302947', '测试错误姓名1', '2', null, null, null, null, null, 'test007');
INSERT INTO `test_system1_grxx` VALUES (null, null, null, null, null, null, null, null, 'testNull');
