package com.wondersgroup.base.login.model;
/**
 * 机构类型字典表（TB_AUTH_ORG_DIC_TYPE）
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class TbAuthDicOrgType implements java.io.Serializable{

	private String id;//主键
	private String type;//类别
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
