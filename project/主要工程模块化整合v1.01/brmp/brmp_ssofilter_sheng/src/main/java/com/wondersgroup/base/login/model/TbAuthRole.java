package com.wondersgroup.base.login.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息(TB_AUTH_ROLE)
 * */
@SuppressWarnings("serial")
public class TbAuthRole implements java.io.Serializable {

	private String roleid;
	private String rolename;
	private String isChecked;//判断角色是否已经被授权
	
	//多对多，一个角色对应多个菜单资源 (TB_AUTH_ROLE_RESOURCE中间表)
	@SuppressWarnings("rawtypes")
	private Set tbAuthResources = new HashSet(0);
	//多对多，一个角色对应多个用户
	@SuppressWarnings("rawtypes")
	private Set tbAuthUsers = new HashSet(0);
	
	private TbAuthUser tbAuthUser;//创建角色用户
	
	public TbAuthRole() {
		super();
	}

	public TbAuthRole(String roleid, String rolename, @SuppressWarnings("rawtypes") Set tbAuthResources,
			@SuppressWarnings("rawtypes") Set tbAuthUsers) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.tbAuthResources = tbAuthResources;
		this.tbAuthUsers = tbAuthUsers;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@SuppressWarnings("rawtypes")
	public Set getTbAuthResources() {
		return tbAuthResources;
	}

	public void setTbAuthResources(@SuppressWarnings("rawtypes") Set tbAuthResources) {
		this.tbAuthResources = tbAuthResources;
	}

	@SuppressWarnings("rawtypes")
	public Set getTbAuthUsers() {
		return tbAuthUsers;
	}

	public void setTbAuthUsers(@SuppressWarnings("rawtypes") Set tbAuthUsers) {
		this.tbAuthUsers = tbAuthUsers;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public TbAuthUser getTbAuthUser() {
		return tbAuthUser;
	}

	public void setTbAuthUser(TbAuthUser tbAuthUser) {
		this.tbAuthUser = tbAuthUser;
	}
}