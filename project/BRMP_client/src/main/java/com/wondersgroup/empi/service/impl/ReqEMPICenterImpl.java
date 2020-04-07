package com.wondersgroup.empi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.empi.dao.intf.Dao4PersonIntf;
import com.wondersgroup.empi.po.empipo.EMPIObj;
import com.wondersgroup.empi.po.webservicepo.RequestPo;
import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.common.RestCXFClient;

@Service
public class ReqEMPICenterImpl implements ReqEMPICenterIntf {
	
	@Autowired
	private BaseResource baseResource;
	
	@Autowired
	private Dao4PersonIntf dao4PersonIntf;

	@Override
	public String ReqEMPICenter() {
		
		RequestPo reqPo = new RequestPo();
		//reqPo.setUsername("testSystem1");
		reqPo.setUsername("test_system1");
		reqPo.setPassword("1234567890");
		//reqPo.setParamType("测试错误Type");
		reqPo.setParamType("AddEMPI");
		reqPo.setModelType("model1");
		
		List<EMPIObj> list = null;
		try {
			list = dao4PersonIntf.selectPerson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		reqPo.setParams(CommonUtil.toJSONString(list));
		
		String json = CommonUtil.toJSONString(reqPo);
		System.out.println(json);
		String string = RestCXFClient.reqEMPICenter(baseResource.getEMPICenterAdress(), json);
		System.out.println(string);
		return null;
	}

	@Override
	public String ReqEMPICenter4Model() {
		RequestPo reqPo = new RequestPo();
		//reqPo.setUsername("testSystem1");
		reqPo.setUsername("test_system1");
		reqPo.setPassword("123");
		//reqPo.setParamType("测试错误Type");
		reqPo.setParamType("model");
		reqPo.setModelType("测试新建模型1");
		
		/*
		List<EMPIObj> list = null;
		try {
			list = dao4PersonIntf.selectPerson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		reqPo.setParams(CommonUtil.toJSONString(list));
		*/
		//reqPo.setParams("[	 {\"originId\":\"test005\",\"Id\":\"521203198706302947\",\"updateTime\":\"2019-01-01\",\"Along1\":20,\"自定义字段5\":0}, {\"originId\":\"test006\",\"Id\":\"521311197611264522\",\"updateTime\":\"2019-01-01 12:41:52\",\"Along1\":\"1\",\"Along1\":\"1\",\"aLong2\":0},{\"originId\":\"test007\",\"Id\":\"521203198706302947\",\"updateTime\":\"2019-01-01 12:41:52\",\"Along1\":\"899999999\",\"aLong2\":0},{\"originId\":\"testNull\",\"aLong2\":0}   ]");
		//reqPo.setParams("[{\"Id\":\"af93e34c2d1\",\"originId\":\"personId1\",\"updateTime\":\"2019-09-01 13:22:01\",\"Along1\":1,\"aLong2\":1,\"test123\":0.5,\"testint\":1,\"testdouble\":0.5,\"testdate\":\"2019-12-04 02:04:05\"  },{ \"Id\":\"af93e34c2d2\", \"originId\":\"personId2\",\"updateTime\":\"2019-09-02 14:23:08\",\"Along1\":2,\"aLong2\":2,\"test123\":0.6,\"testint\":2,\"testdouble\":0.6,\"testdate\":\"2019-12-05 03:16:08\"  } ]");
		
		//String json = CommonUtil.toJSONString(reqPo);
		String json = "{\"modelType\":\"测试新建模型1\",\"params\":\"[{\\\"Id\\\":\\\"af93e34c2d1\\\",\\\"originId\\\":\\\"personId1\\\",\\\"updateTime\\\":\\\"2019-09-01 13:22:01\\\",\\\"Along1\\\":1,\\\"aLong2\\\":1,\\\"test123\\\":0.5,\\\"testint\\\":1,\\\"testdouble\\\":0.5,\\\"testdate\\\":\\\"2019-12-04 02:04:05\\\"  },{ \\\"Id\\\":\\\"af93e34c2d2\\\", \\\"originId\\\":\\\"personId2\\\",\\\"updateTime\\\":\\\"2019-09-02 14:23:08\\\",\\\"Along1\\\":2,\\\"aLong2\\\":2,\\\"test123\\\":0.6,\\\"testint\\\":2,\\\"testdouble\\\":0.6,\\\"testdate\\\":\\\"2019-12-05 03:16:08\\\"  } ]\",\"username\":\"test_system1\", \"password\":\"123\", \"paramType\":\"model\" } ";
		System.out.println(json);
		String string = RestCXFClient.reqEMPICenter(baseResource.getEMPICenterAdress(), json);
		//System.out.println(string);
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
	
	
	
	@Override
	public String ReqGir2ForModel() {
		RequestPo reqPo = new RequestPo();
		//reqPo.setParamType("测试错误Type");
		reqPo.setParamType("model");
		reqPo.setModelType("newModel");
		
		String json = "{\"modelType\":\"newModel1\",\"params\":\"[{\\\"xgbz\\\":0,\\\"updateTime\\\":\\\"2019-09-01 13:22:01\\\",\\\"A1\\\":0.5,\\\"new\\\":\\\"testString1\\\" },{\\\"xgbz\\\":0,\\\"updateTime\\\":\\\"2019-09-02 14:23:08\\\",\\\"A1\\\":0.6,\\\"new\\\":\\\"testString2\\\" }]\",\"paramType\":\"model\"}  ";
		System.out.println(json);
		String string = RestCXFClient.reqEMPICenter("http://localhost:8080/GIR2BigData/webservice/sendData/sendData/", json);
		System.out.println(string);
		return string;
	}

}
