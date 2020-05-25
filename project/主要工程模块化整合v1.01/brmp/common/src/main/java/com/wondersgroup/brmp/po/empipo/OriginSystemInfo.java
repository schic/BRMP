package com.wondersgroup.brmp.po.empipo;

import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 接入系统基本
 *
 *表名：brmp_conf_origin_system_info
 */
@Table(name="brmp_conf_origin_system_info")
public class OriginSystemInfo {
	private String originSystemId;//源系统名称编号
	private String originSystemName;//源系统名称
	private String originSystemCname;//源系统名称中文或别名
	private String originSystemUrl;//接入系统接口的URL
	private String username;//接入系统用户名
	private String password;//接口验证码
	private int encryptionType;//接入加密方式 0:无 ;1:AES;2:AESVi;3:DES;4:ThreeDES;5:AES变化;6:AESVi变化;7:DES变化
	
	public String getOriginSystemId() {
		return originSystemId;
	}
	public void setOriginSystemId(String originSystemId) {
		this.originSystemId = originSystemId;
	}
	public String getOriginSystemName() {
		return originSystemName;
	}
	public void setOriginSystemName(String originSystemName) {
		this.originSystemName = originSystemName;
	}
	public String getOriginSystemCname() {
		return originSystemCname;
	}
	public void setOriginSystemCname(String originSystemCname) {
		this.originSystemCname = originSystemCname;
	}
	public String getOriginSystemUrl() {
		return originSystemUrl;
	}
	public void setOriginSystemUrl(String originSystemUrl) {
		this.originSystemUrl = originSystemUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEncryptionType() {
		return encryptionType;
	}
	public void setEncryptionType(int encryptionType) {
		this.encryptionType = encryptionType;
	}
	
	

}
