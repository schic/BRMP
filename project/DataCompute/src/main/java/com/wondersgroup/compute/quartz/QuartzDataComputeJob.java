package com.wondersgroup.compute.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.compute.service.intf.DataComputeMainIntf;

public class QuartzDataComputeJob {
	
	@Autowired DataComputeMainIntf dataComputeMainIntf;
	
	public void execute(){
		System.out.println(new Date());
		dataComputeMainIntf.pushDataComputeMain();
	}

}
