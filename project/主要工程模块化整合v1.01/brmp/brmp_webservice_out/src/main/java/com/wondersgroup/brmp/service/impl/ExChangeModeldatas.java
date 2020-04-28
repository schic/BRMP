package com.wondersgroup.brmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.po.webservicepo.RequestPo;
import com.wondersgroup.brmp.service.intf.BrmpChangeServiceIntf;

@Service
public class ExChangeModeldatas implements BrmpChangeServiceIntf {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Override
	public String sendModelData() {
		RequestPo reqPo = new RequestPo();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("originSystemId", "1");//获取系统管理员信息
		OriginSystemInfo originSystemInfo = (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		
		reqPo.setUsername(originSystemInfo.getUsername());
		reqPo.setPassword(originSystemInfo.getPassword());
		reqPo.setParamType("SynchronousData");
		
		paramMap.clear();
		/*
		private int status;//状态  0:停用 1:启用
		private int auditStatus;//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
		*/
		paramMap.put("status", 1);
		paramMap.put("auditStatus", 9);
		List<ModelData> modelDatas = commonDaoIntf.selectObjListByParam(ModelData.class, paramMap);
		for(int i=0;i<modelDatas.size();i++) {
			
			
		}
		
		
		
		return "完成";
	}

}
