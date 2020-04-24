package com.wondersgroup.brmp.dao.intf;

import com.wondersgroup.brmp.po.webservicepo.VerifiedPo;

public interface VerifyDaoIntf {
	
	VerifiedPo verifyOriginSystem(String username,String password);

	VerifiedPo verifyApplyUser(String username, String password);

}
