package com.wondersgroup.brmp.dao.daoutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DaoConfResource {
	
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	public void setJdbcDriverClassName(String jdbcDriverClassName) {
		this.jdbcDriverClassName = jdbcDriverClassName;
	}

}


