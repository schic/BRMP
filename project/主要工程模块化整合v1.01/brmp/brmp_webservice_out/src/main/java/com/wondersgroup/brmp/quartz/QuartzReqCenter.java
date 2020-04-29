package com.wondersgroup.brmp.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.brmp.dao.daoutil.BrmpConfResource;
import com.wondersgroup.brmp.service.intf.BrmpChangeServiceIntf;
import com.wondersgroup.brmp.service.intf.BrmpSendPropertiesIntf;

public class QuartzReqCenter {
	
	@Autowired BrmpChangeServiceIntf brmpChangeServiceIntf;
	
	@Autowired BrmpSendPropertiesIntf brmpSendPropertiesIntf;
	
	@Autowired BrmpConfResource brmpConfResource;
	
	private static boolean insertJob = false;//插入作业是否正在执行标志
	private static byte[] lockInsertJob = new byte[0];
	
	void executeReqCenter(){
		System.out.println("QuartzBigining定时任务开始");
		System.out.println(new Date());
		synchronized (lockInsertJob) {
			if (insertJob){
				System.out.println("insertJob方法正在执行，退出本次执行，is running，exit");
				return;
			}
			insertJob=true;
		}
		try {
			
			
			if("true".equals(brmpConfResource.getMainWebService())){
				brmpSendPropertiesIntf.sendProperties();
			}
			
			brmpChangeServiceIntf.sendModelData();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			synchronized (lockInsertJob) {
				insertJob=false;
			}
		}
	}

}
