package com.wondersgroup.brmp.po.empipo;

import com.wondersgroup.brmp.util.anotation.Column;
import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 模型字段属性配置
 *
 * 表名：brmp_conf_origin_system_model
 */
@Table(name="brmp_conf_origin_system_model")
public class ModelDataAttribute {
	private String originSystemId;//源系统名称编号
	private String modelId;//源系统建立的模型编号
	private String modelColName;//模型提供的字段
	private String modelColDisplayName;//模型字段展示名称
	private int modelColType;//字段类型 0:字符串 1:整数 2:浮点数 3:日期
	private int modelColLenth;//字段长度
	private int modelColDecimalLenth;//小数长度(用于有小数的字段)
	private int displayOrder;//页面展示顺序
	
	@Column(name="")
	private int pk;//是否源系统主键 (0否 1是)
	
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
	public String getModelColName() {
		return modelColName;
	}
	public void setModelColName(String modelColName) {
		this.modelColName = modelColName;
	}
	public String getModelColDisplayName() {
		return modelColDisplayName;
	}
	public void setModelColDisplayName(String modelColDisplayName) {
		this.modelColDisplayName = modelColDisplayName;
	}
	public int getModelColType() {
		return modelColType;
	}
	public void setModelColType(int modelColType) {
		this.modelColType = modelColType;
	}
	public int getModelColLenth() {
		return modelColLenth;
	}
	public void setModelColLenth(int modelColLenth) {
		this.modelColLenth = modelColLenth;
	}
	public int getModelColDecimalLenth() {
		return modelColDecimalLenth;
	}
	public void setModelColDecimalLenth(int modelColDecimalLenth) {
		this.modelColDecimalLenth = modelColDecimalLenth;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	
	

}
