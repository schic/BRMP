package com.wondersgroup.compute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.compute.service.intf.DataComputeMainIntf;

@Controller
@RequestMapping("/")
public class testController {
	
	@Autowired DataComputeMainIntf dataComputeMainIntf;
	
	@RequestMapping("/")
	public String index(){
		dataComputeMainIntf.pushDataComputeMain();
		return "index";
	}
	
}