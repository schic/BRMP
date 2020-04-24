package com.wondersgroup.base.login.model;

/**
 * 功能： 系统用户对象，用于存放在SESSION中 创建日期：Nov 14, 2012 版权： 万达信息有限公司 *
 * 
 * @author "王胤洪"
 */
public class AuthUser {

  /**
   * 用户ID，系统内部使用，无意义，唯一标识个人，可以使用来获取用户所有角色
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
	private String orgId;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
