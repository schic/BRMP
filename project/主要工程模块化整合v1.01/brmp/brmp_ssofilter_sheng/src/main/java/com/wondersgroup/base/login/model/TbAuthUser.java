package com.wondersgroup.base.login.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息(TB_AUTH_USER)
 * */
@SuppressWarnings("serial")
public class TbAuthUser implements java.io.Serializable {

	private String userid;
	private String username;
	private String loginname;
	private String psw;
	private String comments;
	private String gender;
	private String mobilephone;
	private String telephone;
	private String email;
	

	//多对一，多个用户对应一个单位
	private TbAuthUnit tbAuthUnit;
	
	private TbAuthUnit tbAuthUnitRoot; //这个所属的根机构
	
	private TbAuthOrganization tbAuthOrganization;//如果是节点管理员，所绑定的机构	
	
	//多对一，多个用户对应一个岗位
	private TbAuthDepartment tbAuthDepartment;
	
	
	//用户所授予的权限
	@SuppressWarnings("rawtypes")
	private Set tbAuthResources = new HashSet(0);
	
	//多对多，一个用户可以对应多个角色，一个角色也可以对应多个用户
	private Set<TbAuthRole> tbAuthRoles;
	
	//用户创建的角色
	@SuppressWarnings("rawtypes")
	private Set userCreateRoles;
	
	public TbAuthUser() {
		super();
	}

	@SuppressWarnings("unchecked")
	public TbAuthUser(String userid, String username, String loginname,
			String psw, String comments, String gender, String mobilephone,
			String telephone, String email, @SuppressWarnings("rawtypes") Set tbAuthRoles,
			TbAuthUnit tbAuthUnit, TbAuthDepartment tbAuthDepartment) {
		super();
		this.userid = userid;
		this.username = username;
		this.loginname = loginname;
		this.psw = psw;
		this.comments = comments;
		this.gender = gender;
		this.mobilephone = mobilephone;
		this.telephone = telephone;
		this.email = email;
		this.tbAuthRoles = tbAuthRoles;
		this.tbAuthUnit = tbAuthUnit;
		this.tbAuthDepartment = tbAuthDepartment;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TbAuthUnit getTbAuthUnit() {
		return tbAuthUnit;
	}

	public void setTbAuthUnit(TbAuthUnit tbAuthUnit) {
		this.tbAuthUnit = tbAuthUnit;
	}

	public TbAuthDepartment getTbAuthDepartment() {
		return tbAuthDepartment;
	}

	public void setTbAuthDepartment(TbAuthDepartment tbAuthDepartment) {
		this.tbAuthDepartment = tbAuthDepartment;
	}

	@SuppressWarnings("rawtypes")
	public Set getTbAuthResources() {
		return tbAuthResources;
	}

	public void setTbAuthResources(@SuppressWarnings("rawtypes") Set tbAuthResources) {
		this.tbAuthResources = tbAuthResources;
	}

	public TbAuthUnit getTbAuthUnitRoot() {
		return tbAuthUnitRoot;
	}

	public void setTbAuthUnitRoot(TbAuthUnit tbAuthUnitRoot) {
		this.tbAuthUnitRoot = tbAuthUnitRoot;
	}

	@SuppressWarnings("rawtypes")
	public Set getUserCreateRoles() {
		return userCreateRoles;
	}

	public void setUserCreateRoles(@SuppressWarnings("rawtypes") Set userCreateRoles) {
		this.userCreateRoles = userCreateRoles;
	}

	public TbAuthOrganization getTbAuthOrganization() {
		return tbAuthOrganization;
	}

	public void setTbAuthOrganization(TbAuthOrganization tbAuthOrganization) {
		this.tbAuthOrganization = tbAuthOrganization;
	}

	public Set<TbAuthRole> getTbAuthRoles() {
		return tbAuthRoles;
	}

	public void setTbAuthRoles(Set<TbAuthRole> tbAuthRoles) {
		this.tbAuthRoles = tbAuthRoles;
	}
	
}