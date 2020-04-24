package com.wondersgroup.base.login.model;

import java.util.Date;

@SuppressWarnings("serial")
public class VisitLogs implements java.io.Serializable {

	/**
	* ID
	*/
	private String id;

	/**
	* 机构ID
	*/
	private String userOrgId;

	/**
	 * 部门ID
	 */
	private String userDeptId;

	/**
	 * 用户ID
	 */
	private String userId;


	/**
	 * 用户名字
	 */
	private String userName;

	/**
	 * 系统ID
	 */
	private String systemId;

	/**
	 * 菜单ID
	 */
	private String menuId;

	/**
	 * 登录时间
	 */
	private Date loginTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getUserDeptId() {
		return userDeptId;
	}

	public void setUserDeptId(String userDeptId) {
		this.userDeptId = userDeptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}