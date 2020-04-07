package com.wondersgroup.empi.util.auth;

import com.wondersgroup.empi.dao.intf.VerifyDaoIntf;
import com.wondersgroup.empi.po.common.VerifiedPo;
import com.wondersgroup.empi.po.webservicepo.RequestPo;

public class EMPIReqVerify {
	
	/**
	 * 对请求类进行权限验证，返回验证结果
	 * @param reqPo
	 * @return
	 */
	public static VerifiedPo EMPIReqVerify4RequestPo (RequestPo reqPo,VerifyDaoIntf verifyDaoIntf){
		if ("apply".equals(reqPo.getParamType()) ) {
			return verifyDaoIntf.verifyApplyUser(reqPo.getUsername(), reqPo.getPassword());
		}
		return verifyDaoIntf.verifyOriginSystem(reqPo.getUsername(), reqPo.getPassword());
		
	}

}
