
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
INSERT INTO `brmp_conf_origin_system_info` VALUES ('1', 'admin', 'BRMP中心系统', 'http://172.16.9.33:8980/brmp_ws_in/webservice/BrmpObj/reqBrmp', 'admin', 'admin');

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
