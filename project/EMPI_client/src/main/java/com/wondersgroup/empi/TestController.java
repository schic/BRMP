package com.wondersgroup.empi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.empi.dao.intf.EMPIClientJobDaoIntf;
import com.wondersgroup.empi.service.intf.EMPIClientJobIntf;
import com.wondersgroup.empi.util.common.BaseResource;

@Controller
@RequestMapping("/")
public class TestController {
	
	@Autowired EMPIClientJobIntf empiClientJobIntf;
	
	@Autowired EMPIClientJobDaoIntf empiClientJobDaoIntf;
	
	@Autowired BaseResource baseResource;
	
	@RequestMapping("/")
	public String index(){
		//EMPIComputeServiceUtil.EMPIComputeMain(empiClientJobIntf, empiClientJobDaoIntf, baseResource);
		return "index";
	}
	

}