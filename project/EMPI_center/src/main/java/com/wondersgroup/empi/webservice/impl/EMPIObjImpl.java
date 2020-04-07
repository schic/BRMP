package com.wondersgroup.empi.webservice.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.empi.dao.intf.VerifyDaoIntf;
import com.wondersgroup.empi.po.common.VerifiedPo;
import com.wondersgroup.empi.po.webservicepo.RequestPo;
import com.wondersgroup.empi.po.webservicepo.ResponsePo;
import com.wondersgroup.empi.service.intf.EMPICenterService4ws;
import com.wondersgroup.empi.util.auth.EMPIReqVerify;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.common.ResponseHead;
import com.wondersgroup.empi.util.common.ResponsePoMsg;
import com.wondersgroup.empi.webservice.intf.EMPIObjIntf;

@WebService(serviceName="EMPIObj")
public class EMPIObjImpl implements EMPIObjIntf {
	
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired private VerifyDaoIntf verifyDaoIntf;
	
	@Override
	public String reqEMPIObj(String msg) {
		RequestPo reqPo = null;
		try {
			reqPo = JSON.parseObject(msg, RequestPo.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponsePoMsg.response2Json(ResponseHead.Error, e.getMessage());
		}	
		
		//进行验证，不通过返回结果，通过继续。
		VerifiedPo verifiedPo = EMPIReqVerify.EMPIReqVerify4RequestPo(reqPo,verifyDaoIntf);
		if(!"pass".equals(verifiedPo.getMsg())){
			return ResponsePoMsg.response2Json(ResponseHead.Error, verifiedPo.getMsg());
		}
		
		EMPICenterService4ws EMPICenterService4ws = null;
		String action = reqPo.getParamType();
		try {
			EMPICenterService4ws = applicationContext.getBean(action, EMPICenterService4ws.class);
		} catch (Exception e) {
			//throw new IllegalArgumentException("不支持的服务类型：" + action);
			return ResponsePoMsg.response2Json(ResponseHead.NoSupport, "ParamType错误erorr".concat(action));
		}
		ResponsePo resPo = EMPICenterService4ws.parseWs(reqPo.getParams(),verifiedPo.getOriginSystemId(),reqPo.getModelType());
		return CommonUtil.toJSONString(resPo);
	}

}
