package com.wondersgroup.base.login.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SsoFilterConfResource {
	
	@Value("${brmp.admin}")
	private String brmpAdmin;
	
	@Value("${brmp.system}")
	private String brmpSystem;

	public String getBrmpAdmin() {
		return brmpAdmin;
	}

	public void setBrmpAdmin(String brmpAdmin) {
		this.brmpAdmin = brmpAdmin;
	}

	public String getBrmpSystem() {
		return brmpSystem;
	}

	public void setBrmpSystem(String brmpSystem) {
		this.brmpSystem = brmpSystem;
	}
	
}


