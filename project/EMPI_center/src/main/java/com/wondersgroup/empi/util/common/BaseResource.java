package com.wondersgroup.empi.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseResource {
	
	@Value("${EMPIClientAdress}")
	private String EMPIClientAdress;

	public String getEMPIClientAdress() {
		return EMPIClientAdress;
	}

	public void setEMPIClientAdress(String eMPIClientAdress) {
		EMPIClientAdress = eMPIClientAdress;
	}
	
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	public void setJdbcDriverClassName(String jdbcDriverClassName) {
		this.jdbcDriverClassName = jdbcDriverClassName;
	}
	
	
	
}
