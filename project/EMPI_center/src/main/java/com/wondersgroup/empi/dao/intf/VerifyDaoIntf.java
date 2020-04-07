package com.wondersgroup.empi.dao.intf;

import com.wondersgroup.empi.po.common.VerifiedPo;

public interface VerifyDaoIntf {
	
	VerifiedPo verifyOriginSystem(String username,String password);

	VerifiedPo verifyApplyUser(String username, String password);

}
