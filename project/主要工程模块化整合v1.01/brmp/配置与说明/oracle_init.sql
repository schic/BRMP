-- ----------------------------
-- Table structure for brmp_apply_attribute
-- ----------------------------
CREATE TABLE brmp_apply_attribute  (
  apply_id varchar2(32),
  model_id varchar2(32),
  model_col_name varchar2(255)
);
COMMENT ON TABLE brmp_apply_attribute IS '卫生资源整合具体申请属性表';
COMMENT ON COLUMN brmp_apply_attribute.apply_id is '申请id';
COMMENT ON COLUMN brmp_apply_attribute.model_id is '模型id';
COMMENT ON COLUMN brmp_apply_attribute.model_col_name is '模型字段名';


-- ----------------------------
-- Table structure for brmp_apply_base
-- ----------------------------
CREATE TABLE brmp_apply_base  (
  apply_id varchar2(32),
  model_id varchar2(32),
  user_id varchar2(255),
  user_name varchar2(255),
  apply_name varchar2(255),
  apply_org_name varchar2(255),
  apply_user varchar2(255),
  apply_direction varchar2(2048),
  apply_time date,
  audit_status NUMBER(1)
);
COMMENT ON TABLE brmp_apply_base IS '卫生资源整合申请基本表';
COMMENT ON COLUMN brmp_apply_base.apply_id is '申请id';
COMMENT ON COLUMN brmp_apply_base.model_id is '源系统建立的模型编号(申请主资源编号 一个主要资源)';
COMMENT ON COLUMN brmp_apply_base.user_id is '申请用户登录用户ID(或其他登录用唯一号)';
COMMENT ON COLUMN brmp_apply_base.user_name is '申请用户登录用户名';
COMMENT ON COLUMN brmp_apply_base.apply_name is '申请资源目录名称（申请名称）';
COMMENT ON COLUMN brmp_apply_base.apply_org_name is '申请机构名称';
COMMENT ON COLUMN brmp_apply_base.apply_user is '申请人姓名';
COMMENT ON COLUMN brmp_apply_base.apply_direction is '申请用途描述说明';
COMMENT ON COLUMN brmp_apply_base.apply_time is '申请时间';
COMMENT ON COLUMN brmp_apply_base.audit_status is '审核状态  1:待审核 2:审核拒绝 9:审核通过';


-- ----------------------------
-- Table structure for brmp_conf_origin_system_info
-- ----------------------------
CREATE TABLE brmp_conf_origin_system_info (
  ORIGIN_SYSTEM_ID varchar2(32),
  ORIGIN_SYSTEM_NAME varchar2(255),
  ORIGIN_SYSTEM_CNAME varchar2(255),
  ORIGIN_SYSTEM_URL varchar2(1024),
  USERNAME varchar2(255),
  PASSWORD varchar2(255)
);
COMMENT ON TABLE brmp_conf_origin_system_info IS '卫生资源整合接入系统基本信息表';
COMMENT ON COLUMN brmp_conf_origin_system_info.ORIGIN_SYSTEM_ID is '源系统名称编号';
COMMENT ON COLUMN brmp_conf_origin_system_info.ORIGIN_SYSTEM_NAME is '源系统名称';
COMMENT ON COLUMN brmp_conf_origin_system_info.ORIGIN_SYSTEM_CNAME is '源系统名称中文或别名';
COMMENT ON COLUMN brmp_conf_origin_system_info.ORIGIN_SYSTEM_URL is '接入系统接口的URL';
COMMENT ON COLUMN brmp_conf_origin_system_info.USERNAME is '接入系统用户名';
COMMENT ON COLUMN brmp_conf_origin_system_info.PASSWORD is '接入系统验证';

-- ----------------------------
-- Records of brmp_conf_origin_system_info
-- ----------------------------
INSERT INTO brmp_conf_origin_system_info VALUES ('1', 'admin', 'BRMP中心系统', 'http://172.18.17.15:8791/brmp-webservice-in/webservice/BrmpObj/reqBrmp', 'admin', 'admin');

-- ----------------------------
-- Table structure for brmp_conf_origin_system_model
-- ----------------------------
CREATE TABLE brmp_conf_origin_system_model (
  ORIGIN_SYSTEM_ID varchar2(32),
  MODEL_ID varchar2(32),
  MODEl_COL_NAME varchar2(255),
  MODEl_COL_DISPLAY_NAME varchar2(255),
  MODEL_COL_TYPE number(3),
  MODEL_COL_LENTH number(11),
  MODEL_COL_DECIMAL_LENTH number(11),
  DISPLAY_ORDER number(3),
  PK number(1)
);
COMMENT ON TABLE brmp_conf_origin_system_model IS '卫生资源整合接入系统建立模型的配置明细表';
COMMENT ON COLUMN brmp_conf_origin_system_model.ORIGIN_SYSTEM_ID is '源系统名称编号';
COMMENT ON COLUMN brmp_conf_origin_system_model.MODEL_ID is '源系统建立的模型编号';
COMMENT ON COLUMN brmp_conf_origin_system_model.MODEl_COL_NAME is '模型提供的字段';
COMMENT ON COLUMN brmp_conf_origin_system_model.MODEl_COL_DISPLAY_NAME is '模型字段展示名称';
COMMENT ON COLUMN brmp_conf_origin_system_model.MODEL_COL_TYPE is '字段类型 0:字符串 1:整数 2:浮点数 3:日期';
COMMENT ON COLUMN brmp_conf_origin_system_model.MODEL_COL_LENTH is '字段长度';
COMMENT ON COLUMN brmp_conf_origin_system_model.MODEL_COL_DECIMAL_LENTH is '小数长度';
COMMENT ON COLUMN brmp_conf_origin_system_model.DISPLAY_ORDER is '展示顺序';
COMMENT ON COLUMN brmp_conf_origin_system_model.PK is '是否主键 0否  1是';


-- ----------------------------
-- Table structure for brmp_conf_origin_system_mdbase
-- ----------------------------
CREATE TABLE brmp_conf_origin_system_mdbase (
  ORIGIN_SYSTEM_ID varchar2(32),
  MODEL_ID varchar2(32),
  MODEL_NAME varchar2(255),
  MODEL_TAB_NAME varchar2(255),
  MODEL_CREATE_TIME date,
  MODEL_UPDETE_TIME date,
  MODEL_DESCRIPTION varchar2(4000),
  STATUS number(1),
  AUDIT_STATUS number(1),
  DATA_NUM number(12)
);
COMMENT ON TABLE brmp_conf_origin_system_mdbase IS '卫生资源整合接入系统建立模型的配置表';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.ORIGIN_SYSTEM_ID is '源系统名称编号';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.MODEL_ID is '源系统建立的模型编号';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.MODEL_NAME is '源系统名称编号';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.MODEL_TAB_NAME is '源系统建立的模型表名';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.MODEL_CREATE_TIME is '模型创建时间';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.MODEL_UPDETE_TIME is '模型更新时间';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.MODEL_DESCRIPTION is '模型描述';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.STATUS is '状态  0:停用 1:启用';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.AUDIT_STATUS is '审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过';
COMMENT ON COLUMN brmp_conf_origin_system_mdbase.DATA_NUM is '当前数据记录数';

-- ----------------------------
-- Table structure for brmp_dic_datatype
-- ----------------------------
CREATE TABLE brmp_dic_datatype (
  MODEL_COL_TYPE number(11),
  DATATYPE varchar2(255),
  JAVA_DATATYPE varchar2(255)
);
COMMENT ON TABLE brmp_dic_datatype IS '卫生资源整合数据字段类型对照表';
COMMENT ON COLUMN brmp_dic_datatype.MODEL_COL_TYPE is '字段类型编号';
COMMENT ON COLUMN brmp_dic_datatype.DATATYPE is '字段类型名称';
COMMENT ON COLUMN brmp_dic_datatype.JAVA_DATATYPE is '对应程序中使用的字段类型';

-- ----------------------------
-- Records of brmp_dic_datatype
-- ----------------------------
INSERT INTO brmp_dic_datatype VALUES (0, 'varchar2', 'String');
INSERT INTO brmp_dic_datatype VALUES (1, 'number', 'int');
INSERT INTO brmp_dic_datatype VALUES (2, 'number', 'float');
INSERT INTO brmp_dic_datatype VALUES (3, 'date', 'Date');
INSERT INTO brmp_dic_datatype VALUES (4, 'number', 'long');
INSERT INTO brmp_dic_datatype VALUES (5, 'number', 'double');
INSERT INTO brmp_dic_datatype VALUES (6, 'nvarchar2', 'String(n)');

commit;

-- ----------------------------
-- 创建值域字典表
-- ----------------------------
create table BRMP_DICTIONARY (
DIC_ID varchar2(64),
ORIGIN_SYSTEM_ID varchar2(32),
DIC_CODE varchar2(64),
DIC_NAME varchar2(256),
STATUS number(1),
AUDIT_STATUS number(1)
);
COMMENT ON TABLE BRMP_DICTIONARY IS '卫生资源整合值域字典表';
COMMENT ON COLUMN BRMP_DICTIONARY.DIC_ID is '字典ID';
COMMENT ON COLUMN BRMP_DICTIONARY.ORIGIN_SYSTEM_ID is '源系统名称编号';
COMMENT ON COLUMN BRMP_DICTIONARY.DIC_CODE is '字典编码名称';
COMMENT ON COLUMN BRMP_DICTIONARY.DIC_NAME is '字典中文名称';
COMMENT ON COLUMN BRMP_DICTIONARY.STATUS is '状态  0:停用 1:启用';
COMMENT ON COLUMN BRMP_DICTIONARY.AUDIT_STATUS is '审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过';

-- ----------------------------
-- 创建值域字典数据项表
-- ----------------------------
create table BRMP_DICTIONARY_ITEM (
DIC_ID varchar2(64),
ORIGIN_SYSTEM_ID varchar2(32),
DIC_CODE varchar2(64),
CODE varchar2(64),
NAME varchar2(512),
DISPLAY_ORDER number(3)
);
COMMENT ON TABLE BRMP_DICTIONARY_ITEM IS '卫生资源整合值域字典数据项表';
COMMENT ON COLUMN BRMP_DICTIONARY_ITEM.DIC_ID is '字典ID';
COMMENT ON COLUMN BRMP_DICTIONARY_ITEM.ORIGIN_SYSTEM_ID is '源系统名称编号';
COMMENT ON COLUMN BRMP_DICTIONARY_ITEM.DIC_CODE is '字典编码名称';
COMMENT ON COLUMN BRMP_DICTIONARY_ITEM.CODE is '值域代码';
COMMENT ON COLUMN BRMP_DICTIONARY_ITEM.NAME is '值域名称';
COMMENT ON COLUMN BRMP_DICTIONARY_ITEM.DISPLAY_ORDER is '展示顺序';

