package com.wondersgroup.brmp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.brmp.dao.daoutil.BrmpConfResource;
import com.wondersgroup.brmp.quartz.QuartzReqCenter;
import com.wondersgroup.brmp.service.intf.BrmpChangeServiceIntf;
import com.wondersgroup.brmp.service.intf.BrmpSendPropertiesIntf;


@Controller
@RequestMapping("/")
public class testController {
	
	@Autowired BrmpChangeServiceIntf brmpChangeServiceIntf;
	
	@Autowired BrmpSendPropertiesIntf brmpSendPropertiesIntf;
	
	@Autowired BrmpConfResource brmpConfResource;
	
	@RequestMapping("/")
	public String index(){
		System.out.println("BRMPtestWebservice");
		synchronized (QuartzReqCenter.lockInsertJob) {
			if (QuartzReqCenter.insertJob){
				System.out.println("insertJob方法正在执行，退出本次执行，is running，exit");
				return "index";
			}
			QuartzReqCenter.insertJob=true;
		}
		try {
			System.out.println(new Date());
		
			if("true".equals(brmpConfResource.getMainWebService())){
				brmpSendPropertiesIntf.sendProperties();
			}
			
			brmpChangeServiceIntf.sendModelData();
			
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		} finally {
			synchronized (QuartzReqCenter.lockInsertJob) {
				QuartzReqCenter.insertJob=false;
			}
		}
	}
	

}
