package com.wondersgroup.brmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.brmp.dao.daoutil.BrmpConfResource;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.ApplyAttribute;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.po.webservicepo.RequestPo;
import com.wondersgroup.brmp.service.intf.BrmpSendPropertiesIntf;
import com.wondersgroup.brmp.util.anotation.Table;
import com.wondersgroup.brmp.util.common.CommonUtil;
import com.wondersgroup.brmp.util.webserviceutil.RestCXFClient;

@Service
public class SendProperties implements BrmpSendPropertiesIntf {
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired BrmpConfResource brmpConfResource;
	
	static {
		Map<String, Class<?>> propertiesTable1 = new HashMap<String, Class<?>>();
		propertiesTable1.put("ApplyAttribute", ApplyAttribute.class);
		propertiesTable1.put("ApplyManagement", ApplyManagement.class);
		propertiesTable1.put("ModelData", ModelData.class);
		propertiesTable1.put("ModelDataAttribute", ModelDataAttribute.class);
		propertiesTable1.put("OriginSystemInfo", OriginSystemInfo.class);
		
		propertiesTable = propertiesTable1;
	}
	
	final private static Map<String, Class<?>> propertiesTable;
	
	@Override
	public String sendProperties() {
		RequestPo reqPo = new RequestPo();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("originSystemId", "1");//获取系统管理员信息
		OriginSystemInfo originSystemInfo = (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		
		reqPo.setUsername(originSystemInfo.getUsername());
		reqPo.setPassword(originSystemInfo.getPassword());
		reqPo.setParamType("updateProperties");
		
		for (Class<?> clazz : propertiesTable.values()) {
			Table table = clazz.getAnnotation(Table.class);
			reqPo.setModelType(clazz.getName());
			int records = commonDaoIntf.getRecords(table.name());//总记录数
			int pageSize = 5000;//分页数
			int totalPage;//总页数
			if(records % pageSize == 0){
				totalPage = records / pageSize;
			}else{
				totalPage = records / pageSize + 1;
			}
			
			for (int i=0;i<totalPage;i++) {
				List<?> lists = commonDaoIntf.selectObj(clazz, i+1, pageSize);
				reqPo.setParams(CommonUtil.toJSONString(lists));
				String json = CommonUtil.toJSONString(reqPo);
				//System.out.println(json);
				String string = RestCXFClient.reqRestService(brmpConfResource.getBrmpChangeAdress(), json);
				System.out.println(string);
				
			}
			
		}
		
		return "sendProperties完成complate";
	}

}
