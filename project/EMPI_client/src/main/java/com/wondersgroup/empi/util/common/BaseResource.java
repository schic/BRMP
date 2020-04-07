package com.wondersgroup.empi.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseResource {
	
	@Value("${EMPICenterAdress}")
	private String EMPICenterAdress;
	
	@Value("${EMPIClientId}")
	private String EMPIClientId;

	public String getEMPICenterAdress() {
		return EMPICenterAdress;
	}

	public void setEMPICenterAdress(String eMPICenterAdress) {
		EMPICenterAdress = eMPICenterAdress;
	}

	public String getEMPIClientId() {
		return EMPIClientId;
	}

	public void setEMPIClientId(String eMPIClientId) {
		EMPIClientId = eMPIClientId;
	}

	
	
	

}
