package com.wondersgroup.empi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.empi.po.model.Dept;
import com.wondersgroup.empi.po.model.Org;
import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;

@Controller
@RequestMapping("/")
public class testController {
	
	@Autowired ReqEMPICenterIntf reqEMPICenterIntf;
	
	@RequestMapping("/")
	public String index(){
		//reqEMPICenterIntf.ReqEMPICenter4Model(Person.class);
		reqEMPICenterIntf.ReqEMPICenter4Model(Dept.class);
		reqEMPICenterIntf.ReqEMPICenter4Model(Org.class);
		return "index";
	}
	

}
