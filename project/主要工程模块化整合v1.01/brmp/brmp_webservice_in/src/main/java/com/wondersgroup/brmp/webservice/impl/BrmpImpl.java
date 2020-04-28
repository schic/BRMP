package com.wondersgroup.brmp.webservice.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.dao.daoutil.BrmpReqVerify;
import com.wondersgroup.brmp.dao.intf.VerifyDaoIntf;
import com.wondersgroup.brmp.po.webservicepo.RequestPo;
import com.wondersgroup.brmp.po.webservicepo.ResponsePo;
import com.wondersgroup.brmp.po.webservicepo.VerifiedPo;
import com.wondersgroup.brmp.service.intf.BrmpCenterService4ws;
import com.wondersgroup.brmp.util.common.CommonUtil;
import com.wondersgroup.brmp.util.webserviceutil.ResponseHead;
import com.wondersgroup.brmp.util.webserviceutil.ResponsePoMsg;
import com.wondersgroup.brmp.webservice.intf.BrmpIntf;

@WebService(serviceName="BrmpObj")
public class BrmpImpl implements BrmpIntf {
	
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired private VerifyDaoIntf verifyDaoIntf;
	
	@Override
	public String reqBrmp(String msg) {
		RequestPo reqPo = null;
		try {
			reqPo = JSON.parseObject(msg, RequestPo.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponsePoMsg.response2Json(ResponseHead.Error, e.getMessage());
		}	
		
		//进行验证，不通过返回结果，通过继续。
		VerifiedPo verifiedPo = BrmpReqVerify.brmpReqVerify4RequestPo(reqPo,verifyDaoIntf);
		if(!"pass".equals(verifiedPo.getMsg())){
			return ResponsePoMsg.response2Json(ResponseHead.Error, verifiedPo.getMsg());
		}
		
		BrmpCenterService4ws brmpCenterService4ws = null;
		String action = reqPo.getParamType();
		try {
			brmpCenterService4ws = applicationContext.getBean(action, BrmpCenterService4ws.class);
		} catch (Exception e) {
			return ResponsePoMsg.response2Json(ResponseHead.NoSupport, "ParamType错误erorr".concat(action));
		}
		ResponsePo resPo = brmpCenterService4ws.parseWs(reqPo.getParams(),verifiedPo.getOriginSystemId(),reqPo.getModelType());
		return CommonUtil.toJSONString(resPo);
	}

}
