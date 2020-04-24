package com.wondersgroup.brmp.dao.daoutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BrmpConfResource {
	
	@Value("${EMPIClientAdress}")
	private String EMPIClientAdress;

	public String getEMPIClientAdress() {
		return EMPIClientAdress;
	}

	public void setEMPIClientAdress(String eMPIClientAdress) {
		EMPIClientAdress = eMPIClientAdress;
	}
	
}
