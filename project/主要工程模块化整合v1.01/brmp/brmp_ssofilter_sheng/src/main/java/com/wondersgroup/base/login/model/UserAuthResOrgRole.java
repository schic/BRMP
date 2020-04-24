package com.wondersgroup.base.login.model;
/***
 * 用户权限来源对应机构，直接授权，角色
 */
public class UserAuthResOrgRole {
	
	private String resName;//资源名称
	
	private String role=""; //对应角色的权限
	
	private String unit="";//对应所在组织的权限
	
	private String resource="";//直接授予的权限

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}
	
}

