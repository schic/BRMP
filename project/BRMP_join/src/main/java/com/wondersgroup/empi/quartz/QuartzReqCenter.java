package com.wondersgroup.empi.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;

public class QuartzReqCenter {
	
	@Autowired
	ReqEMPICenterIntf reqEMPICenterIntf;
	
	public void executeReqCenter(){
		System.out.println(new Date());
		System.out.println("Quartz输出定时法方123");
		
		//new RestCXFClient().reqEMPICenterGet();
//		reqEMPICenterIntf.ReqEMPICenter();
		
	}

}


