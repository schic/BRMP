package com.wondersgroup.base.login.model;

/**
 * 单点登录用户信息
 *
 */
@SuppressWarnings("serial")
public class AuthInfo implements java.io.Serializable {

	/**
	* 用户ID
	*/
	private String userId;

	/**
	* 登录名称
	*/
	private String loginName;

	/**
	 * 登录密码
	 */
	private String pwd;

	/**
	 * 机构ID
	 */
	private String organId;


	/**
	 * 机构代码
	 */
	private String organCode;

	/**
	 * 机构名称
	 */
	private String organName;

	/**
	 * 机构类型---行政机构、其他机构
	 */
	private String organType;

	/**
	 * 机构级别---省级、市级、区县级
	 */
	private String organLevel;

	/**
	 * 部门代码
	 */
	private String departmentCode;
	/**
	 * 部门名称
	 */
	private String departmentName;

	/**
	 * 工号
	 */
	private String gh;

	/**
	 * 员工ID
	 */
	private String empId;

	/**
	 * 员工姓名
	 */
	private String personName;

	/**
	 * 岗位编码
	 */
	private String positionCode;

	/**
	 * 有权管理区域编码（行政编码或机构代码）
	 */
	private String manageAreaCode;

	/**
	 * 有权管理区域名称（行政区域名称或机构名称）
	 */
	private String manageAreaShortName;
	
	/**
	 * "<资源管理及服务系统>"自添加
	 * 用户类型
	 */
	private String userType;//资源管理及服务系统添加  用户类型   admin '管理员'  system '数据接入者'  user '一般需求数据用户'

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getOrganType() {
		return organType;
	}

	public void setOrganType(String organType) {
		this.organType = organType;
	}

	public String getOrganLevel() {
		return organLevel;
	}

	public void setOrganLevel(String organLevel) {
		this.organLevel = organLevel;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getManageAreaCode() {
		return manageAreaCode;
	}

	public void setManageAreaCode(String manageAreaCode) {
		this.manageAreaCode = manageAreaCode;
	}

	public String getManageAreaShortName() {
		return manageAreaShortName;
	}

	public void setManageAreaShortName(String manageAreaShortName) {
		this.manageAreaShortName = manageAreaShortName;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}