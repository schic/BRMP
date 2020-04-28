package com.wondersgroup.brmp.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.brmp.dao.daoutil.BrmpConfResource;
import com.wondersgroup.brmp.service.intf.BrmpChangeServiceIntf;
import com.wondersgroup.brmp.service.intf.BrmpSendPropertiesIntf;

public class QuartzReqCenter {
	
	@Autowired BrmpChangeServiceIntf brmpChangeServiceIntf;
	
	@Autowired BrmpSendPropertiesIntf brmpSendPropertiesIntf;
	
	@Autowired BrmpConfResource brmpConfResource;
	
	void executeReqCenter(){
		System.out.println("QuartzBigining定时任务开始");
		
		if("true".equals(brmpConfResource.getMainWebService())){
			brmpSendPropertiesIntf.sendProperties();
		}
		
		//TODO
		brmpChangeServiceIntf.sendModelData();
		
	}

}
