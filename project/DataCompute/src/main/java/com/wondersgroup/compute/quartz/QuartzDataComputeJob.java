package com.wondersgroup.compute.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.compute.service.execute.ExecuteDataComputeJob;

public class QuartzDataComputeJob {
	
	@Autowired ExecuteDataComputeJob executeDataComputeJob;
	
	public void execute(){
		System.out.println(new Date());
		executeDataComputeJob.executeJob();
	}

}
