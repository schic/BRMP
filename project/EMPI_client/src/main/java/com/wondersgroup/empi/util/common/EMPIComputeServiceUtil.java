package com.wondersgroup.empi.util.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wondersgroup.empi.dao.intf.EMPIClientJobDaoIntf;
import com.wondersgroup.empi.po.empipo.EMPIObj;
import com.wondersgroup.empi.service.intf.EMPIClientJobIntf;
import com.wondersgroup.empi.util.sqlutil.EMPIJobSqlUtil;

public class EMPIComputeServiceUtil {
	
	private static Map<String,Object> paramMap = new HashMap<String, Object>();
	
	/**
	 * 开始执行主索引计算程序
	 */
	public synchronized static String EMPIComputeMain(EMPIClientJobIntf empiClientJobIntf,EMPIClientJobDaoIntf empiClientJobDaoIntf,BaseResource baseResource){
		empiClientJobIntf.useEMPIJob1();//step1
		
		//step2
		paramMap.clear();
		paramMap.put("EMPIClientId", baseResource.getEMPIClientId());//修改标志位
		List<EMPIObj> empiObjs = empiClientJobDaoIntf.select(EMPIJobSqlUtil.select_cen_inter_EMPIObj,EMPIObj.class,paramMap);
		
		for (int i=0;i<empiObjs.size();i++) {
			empiClientJobIntf.useEMPIJob2(empiObjs.get(i));
		}
		
		//step3
		empiClientJobIntf.useEMPIJob3();
		
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "EMPIClientJob结束end...".concat(sdFormat.format(new Date()));
		
	}

}
