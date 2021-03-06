package com.wondersgroup.brmp.dao.daoutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 服务配置信息
 */
@Component
public class BrmpConfResource {
	
	@Value("${MainWebService}")
	private String MainWebService;
	
	@Value("${EMPIClientAdress}")
	private String EMPIClientAdress;

	/**
	 * 是否 是主网服务
	 */
	public String getMainWebService() {
		return MainWebService;
	}

	public void setMainWebService(String mainWebService) {
		MainWebService = mainWebService;
	}

	public String getEMPIClientAdress() {
		return EMPIClientAdress;
	}

	public void setEMPIClientAdress(String eMPIClientAdress) {
		EMPIClientAdress = eMPIClientAdress;
	}

}
