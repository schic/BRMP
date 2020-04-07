package com.wondersgroup.empi.webservice.impl;

import javax.jws.WebService;

import com.wondersgroup.empi.util.common.EMPIClientJobQ;
import com.wondersgroup.empi.webservice.intf.EMPIComputeJobIntf;

@WebService
public class EMPIComputeJobImpl implements EMPIComputeJobIntf {

	@Override
	public String reqEMPIClinet(String msg) {
		System.out.println(msg);
		EMPIClientJobQ.empiClientJobQueue.add(true);
		return "OK";
	}

}
