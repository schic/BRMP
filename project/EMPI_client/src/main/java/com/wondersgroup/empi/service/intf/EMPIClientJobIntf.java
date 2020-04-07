package com.wondersgroup.empi.service.intf;

import com.wondersgroup.empi.po.empipo.EMPIObj;

public interface EMPIClientJobIntf {

	/**
	 * 第一步更新标志位为客户端操作标志，开始操作索引计算
	 */
	String useEMPIJob1();

	/**
	 * 第二步计算记录进入总关联表和索引表
	 */
	String useEMPIJob2(EMPIObj empiObj);
	
	
	/**
	 * 第三步完成后删除临时索引表，标志位为完成计算的索引
	 */
	String useEMPIJob3();
	

}
