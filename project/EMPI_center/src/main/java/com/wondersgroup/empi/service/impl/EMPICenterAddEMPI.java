package com.wondersgroup.empi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.empi.dao.intf.SaveEMPIObjIntf;
import com.wondersgroup.empi.po.empipo.EMPIObj;
import com.wondersgroup.empi.po.webservicepo.ResponsePo;
import com.wondersgroup.empi.service.intf.EMPICenterService4ws;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.ResponseHead;
import com.wondersgroup.empi.util.common.ResponsePoMsg;
import com.wondersgroup.empi.util.sqlutil.saveEMPIObjSql;
import com.wondersgroup.empi.util.webserviceutil.RestCXFClient;

@Service("AddEMPI")//名称为接口方法名称，不能随便修改;
public class EMPICenterAddEMPI implements EMPICenterService4ws {
	
	@Autowired BaseResource baseResource;

	@Autowired SaveEMPIObjIntf saveEMPIObjIntf;
	
	@Override
	public ResponsePo parseWs(String params,String systemId, String modelName) {
		List<EMPIObj> empiObjs = null;
		try {
			empiObjs = JSON.parseArray(params, EMPIObj.class);
			if (empiObjs.size()>5000){
				return ResponsePoMsg.response2Obj(ResponseHead.Error, "数据单次传入超过限制，单次传入不超过5000条");
			}
		} catch (Exception e) {
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "EMPI的json格式出错。或者系统转换出错——".concat(e.getMessage()));
		}
		String msg = saveEMPIObjIntf.saveEMPIObj(empiObjs, systemId, saveEMPIObjSql.saveEMPIObj);
		
		RestCXFClient.reqEMPIClient(baseResource.getEMPIClientAdress(), "Client执行索引计算");//请求EMPIClient系统
		return ResponsePoMsg.response2Obj(ResponseHead.Completenss, msg);
	}

}
