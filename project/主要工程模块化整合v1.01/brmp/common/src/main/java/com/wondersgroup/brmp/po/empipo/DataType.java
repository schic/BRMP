package com.wondersgroup.brmp.po.empipo;

import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 自定义模型的数据类型配置
 * 
 * brmp_dic_datatype
 */
@Table(name="brmp_dic_datatype")
public class DataType {
	private int modelColType;
	private String datatype;
	private String javaDatatype;
	
	public int getModelColType() {
		return modelColType;
	}
	public void setModelColType(int modelColType) {
		this.modelColType = modelColType;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getJavaDatatype() {
		return javaDatatype;
	}
	public void setJavaDatatype(String javaDatatype) {
		this.javaDatatype = javaDatatype;
	}
	
	

}
