package com.wondersgroup.empi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("/")
	public String home(){
		return "index1";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index1";
	}
	
	@RequestMapping("/ssoLogout")
	public String ssoLogout(){
		return "ssoLogout";
	}

}
