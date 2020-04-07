package com.wondersgroup.empi.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.empi.dao.intf.EMPIClientJobDaoIntf;
import com.wondersgroup.empi.service.intf.EMPIClientJobIntf;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.EMPIComputeServiceUtil;

public class EMPIClientJob {
	
	@Autowired EMPIClientJobIntf empiClientJobIntf;
	
	@Autowired EMPIClientJobDaoIntf empiClientJobDaoIntf;
	
	@Autowired BaseResource baseResource;
	
	
	public void executeEMPIClientJob() {
		String result = EMPIComputeServiceUtil.EMPIComputeMain(empiClientJobIntf, empiClientJobDaoIntf, baseResource);
		System.out.println(result);
	}
	
}
