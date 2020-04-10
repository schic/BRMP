package com.wondersgroup.empi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.empi.dao.intf.CommonDaoIntf;
import com.wondersgroup.empi.po.RequestPo;
import com.wondersgroup.empi.po.model.Person;
import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.common.RestCXFClient;

@Service
public class ReqEMPICenterImpl implements ReqEMPICenterIntf {
	
	@Autowired
	private BaseResource baseResource;
	
	@Autowired CommonDaoIntf commonDaoIntf;

	@Override
	public String ReqEMPICenter4Model(String ModelType) {
		RequestPo reqPo = new RequestPo();
		
		reqPo.setUsername(baseResource.getUsername());
		reqPo.setPassword(baseResource.getPassword());
		reqPo.setParamType("model");
		reqPo.setModelType(ModelType);
		
		List<Person> persons = commonDaoIntf.selectObj(Person.class, "tableName", 2, 5000);
		
		reqPo.setParams(CommonUtil.toJSONString(persons));
		String json = CommonUtil.toJSONString(reqPo);
		System.out.println(json);
		String string = RestCXFClient.reqEMPICenter(baseResource.getEMPICenterAdress(), json);
		System.out.println(string);
		return string;
	}
	/*
	 {
"modelType":"测试新建模型1",
"paramType":"model",
"params":"[{\"originId\":\"test005\",
			\"Id\":\"521203198706302947\",
			\"updateTime\":\"2019-01-01\",
			\"Along1\":20,\"自定义字段5\":0}, 
		   {\"originId\":\"test006\",
		    \"Id\":\"521311197611264522\",
			\"updateTime\":\"2019-01-01 12:41:52\",
			\"Along1\":\"1\",
			\"Along1\":\"1\",
			\"aLong2\":0},
		   {\"originId\":\"test007\",
		    \"Id\":\"521203198706302947\",
			\"updateTime\":\"2019-01-01 12:41:52\",
			\"Along1\":\"899999999\",
			\"aLong2\":0},
		   {\"originId\":\"testNull\",
		    \"aLong2\":0}   
		  ]",
"password":"123",
"username":"test_system1"
}
	  
	  
	  
	 */
	
	

}
