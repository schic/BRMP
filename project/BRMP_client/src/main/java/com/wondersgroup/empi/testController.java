package com.wondersgroup.empi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;

@Controller
@RequestMapping("/")
public class testController {
	
	@Autowired ReqEMPICenterIntf reqEMPICenterIntf;
	
	@RequestMapping("/")
	public String index(){
		reqEMPICenterIntf.ReqEMPICenter();
		//reqEMPICenterIntf.ReqEMPICenter4Model();
		//reqEMPICenterIntf.ReqGir2ForModel();
		return "index";
	}
	

}
