package com.wondersgroup.brmp.dao.daoutil;

import com.wondersgroup.brmp.dao.intf.VerifyDaoIntf;
import com.wondersgroup.brmp.po.webservicepo.RequestPo;
import com.wondersgroup.brmp.po.webservicepo.VerifiedPo;

public class BrmpReqVerify {
	
	/**
	 * 对请求类进行权限验证，返回验证结果
	 * @param reqPo
	 * @return
	 */
	public static VerifiedPo brmpReqVerify4RequestPo (RequestPo reqPo,VerifyDaoIntf verifyDaoIntf){
		if ("apply".equals(reqPo.getParamType()) ) {
			return verifyDaoIntf.verifyApplyUser(reqPo.getUsername(), reqPo.getPassword());
		}
		return verifyDaoIntf.verifyOriginSystem(reqPo.getUsername(), reqPo.getPassword());
		
	}

}
