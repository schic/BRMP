package com.wondersgroup.compute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.compute.service.execute.ExecuteDataComputeJob;

@Controller
@RequestMapping("/")
public class testController {
	
	@Autowired ExecuteDataComputeJob executeDataComputeJob;
	
	@RequestMapping("/")
	public String index(){
		executeDataComputeJob.executeJob();
		return "index";
	}
	
}