package com.wondersgroup.empi.po.empipo;

import java.util.Date;

/**
 * 模型基本
 * 
 * 表名：brmp_conf_origin_system_modelbase
 */
public class ModelData {
	
	String originSystemId;//源系统名称编号
	String modelId;//源系统建立的模型编号
	String modelName;//源系统建立的模型名称
	String modelTabName;//源系统建立的模型表名
	Date modelCreateTime;//模型创建时间
	Date modelUpdeteTime;//模型更新时间
	String modelDescription;//模型描述
	int status;//状态  0:停用 1:启用
	int auditStatus;//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
	
	public String getOriginSystemId() {
		return originSystemId;
	}
	public void setOriginSystemId(String originSystemId) {
		this.originSystemId = originSystemId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelTabName() {
		return modelTabName;
	}
	public void setModelTabName(String modelTabName) {
		this.modelTabName = modelTabName;
	}
	public Date getModelCreateTime() {
		return modelCreateTime;
	}
	public void setModelCreateTime(Date modelCreateTime) {
		this.modelCreateTime = modelCreateTime;
	}
	public Date getModelUpdeteTime() {
		return modelUpdeteTime;
	}
	public void setModelUpdeteTime(Date modelUpdeteTime) {
		this.modelUpdeteTime = modelUpdeteTime;
	}
	public String getModelDescription() {
		return modelDescription;
	}
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	
	
}
