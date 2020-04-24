/**
 * 功能： 系统角色，用于存放在SESSION中，可以获取角色所属机构（名称及代码）、部门（名称及代码）以及机构所属数据权限范围<br>
 * 创建日期：Nov 14, 2012
 * 版权： 万达信息有限公司
 * @author "王胤洪"
 */
package com.wondersgroup.base.login.model;

/**
 * 系统角色，用于存放在SESSION中，可以获取角色所属机构（名称及代码）、部门（名称及代码）以及机构所属数据权限范围<br>
 * 
 * @author "王胤洪"
 */
@SuppressWarnings("serial")
public class AuthRole implements java.io.Serializable {
	/**
	 * 角色ID，无意义，可以使用来获取所有有权限的资源
	 */
	private String roleId;

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
	 * 工号
	 */
	private String gh;

	/**
	 * 员工ID
	 */
	private String empid;

	/**
	 * 角色名称-岗位名称
	 */
	private String roleName;

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
	 * 部门代码
	 */
	private String departmentCode;
	/**
	 * 部门名称
	 */
	private String departmentName;

	/**
	 * 功能：获取角色ID
	 * 
	 * @return 角色ID
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 功能：设置角色ID
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 功能：获取机构代码
	 * 
	 * @return 机构代码
	 */
	public String getOrganCode() {
		return organCode;
	}

	/**
	 * @param organCode
	 *            the organCode to set
	 */
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @param organName
	 *            the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	/**
	 * @return the organType
	 */
	public String getOrganType() {
		return organType;
	}

	/**
	 * @param organType
	 *            the organType to set
	 */
	public void setOrganType(String organType) {
		this.organType = organType;
	}

	/**
	 * @return the manageAreaCode
	 */
	public String getManageAreaCode() {
		return manageAreaCode;
	}

	/**
	 * @param manageAreaCode
	 *            the manageAreaCode to set
	 */
	public void setManageAreaCode(String manageAreaCode) {
		this.manageAreaCode = manageAreaCode;
	}

	/**
	 * @return the manageAreaShortName
	 */
	public String getManageAreaShortName() {
		return manageAreaShortName;
	}

	/**
	 * @param manageAreaShortName
	 *            the manageAreaShortName to set
	 */
	public void setManageAreaShortName(String manageAreaShortName) {
		this.manageAreaShortName = manageAreaShortName;
	}

	/**
	 * @return the departmentCode
	 */
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode
	 *            the departmentCode to set
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getGh() {
		return gh;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * organId.
	 *
	 * @return the organId
	 */
	public String getOrganId() {
		return organId;
	}

	/**
	 * organId.
	 *
	 * @param organId the organId to set
	 */
	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganLevel() {
		return organLevel;
	}

	public void setOrganLevel(String organLevel) {
		this.organLevel = organLevel;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

}
