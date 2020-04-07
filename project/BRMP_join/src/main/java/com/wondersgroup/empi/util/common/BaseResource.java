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
	
}
