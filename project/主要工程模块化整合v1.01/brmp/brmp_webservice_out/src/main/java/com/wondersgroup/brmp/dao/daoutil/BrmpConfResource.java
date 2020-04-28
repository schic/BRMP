package com.wondersgroup.brmp.dao.daoutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 服务配置信息
 *
 */
@Component
public class BrmpConfResource {
	
	@Value("${MainWebService}")
	private String MainWebService;
	
	@Value("${BrmpChangeAdress}")
	private String BrmpChangeAdress;
	
	@Value("${ReqCenterDate}")
	private String ReqCenterDate;
	
	/**
	#服务将在政务网 和 互联网 同时部署
	#政务网具有单点登录，默认为主配置为主网，互联网默认为从网。
	#主网将会向从网更新系统，模型等配置数据。
	#MainWebService应用服务所在网络配置 true为主网 false为从网 
	 */
	public String getMainWebService() {
		return MainWebService;
	}

	public void setMainWebService(String mainWebService) {
		MainWebService = mainWebService;
	}

	/**
	 * 两网数据交换地址,对网服务地址
	 */
	public String getBrmpChangeAdress() {
		return BrmpChangeAdress;
	}

	public void setBrmpChangeAdress(String brmpChangeAdress) {
		BrmpChangeAdress = brmpChangeAdress;
	}

	/**
	 * 定时日期ReqCenterDate
	 */
	public String getReqCenterDate() {
		return ReqCenterDate;
	}

	public void setReqCenterDate(String reqCenterDate) {
		ReqCenterDate = reqCenterDate;
	}
	
	
}
