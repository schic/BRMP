package com.wondersgroup.empi.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseResource {
	
	@Value("${EMPICenterAdress}")
	private String EMPICenterAdress;
	
	@Value("${ReqCenterDate}")
	private String ReqCenterDate;

	public String getReqCenterDate() {
		return ReqCenterDate;
	}

	public void setReqCenterDate(String reqCenterDate) {
		ReqCenterDate = reqCenterDate;
	}

	public String getEMPICenterAdress() {
		return EMPICenterAdress;
	}

	public void setEMPICenterAdress(String EMPICenterAdress) {
		this.EMPICenterAdress = EMPICenterAdress;
	}
	
	
	
	
	
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	/**
	 * 驱动名称--得知连接数据库的类型
	 */
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}
	public void setJdbcDriverClassName(String jdbcDriverClassName) {
		this.jdbcDriverClassName = jdbcDriverClassName;
	}
	
	@Value("${BRMP.username}")
	private String BRMPUsername;
	@Value("${BRMP.password}")
	private String BRMPPassword;
	/**
	 * 请求接口用户名
	 */
	public String getBRMPUsername() {
		return BRMPUsername;
	}
	public void setBRMPUsername(String BRMPUsername) {
		this.BRMPUsername = BRMPUsername;
	}
	/**
	 * 请求接口密码
	 */
	public String getBRMPPassword() {
		return BRMPPassword;
	}
	public void setBRMPPassword(String BRMPPassword) {
		this.BRMPPassword = BRMPPassword;
	}
	

}
