package com.wondersgroup.brmp.po.empipo;

import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 用户申请字段属性配置
 * 
 * 表名：brmp_apply_attribute
 */
@Table(name="brmp_apply_attribute")
public class ApplyAttribute {
	
	private String applyId;//申请id用于关联对应的申请资源请求
	private String modelId;//字段所属资源id
	private String modelColName;//模型字段名
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getModelColName() {
		return modelColName;
	}
	public void setModelColName(String modelColName) {
		this.modelColName = modelColName;
	}
	
	

}
