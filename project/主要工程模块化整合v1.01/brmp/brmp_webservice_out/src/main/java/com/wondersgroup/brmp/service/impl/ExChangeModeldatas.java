package com.wondersgroup.brmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.dao.daoutil.BrmpConfResource;
import com.wondersgroup.brmp.dao.daoutil.DaoConfResource;
import com.wondersgroup.brmp.dao.daoutil.Sql4WebserviceOut;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.po.webservicepo.RequestPo;
import com.wondersgroup.brmp.po.webservicepo.ResponsePo;
import com.wondersgroup.brmp.service.intf.BrmpChangeServiceIntf;
import com.wondersgroup.brmp.util.common.CommonUtil;
import com.wondersgroup.brmp.util.webserviceutil.ResponseHead;
import com.wondersgroup.brmp.util.webserviceutil.RestCXFClient;

@Service
public class ExChangeModeldatas implements BrmpChangeServiceIntf {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired DaoConfResource daoConfResource;
	
	@Autowired BrmpConfResource brmpConfResource;
	
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
			ModelData modelData = modelDatas.get(i);
			reqPo.setModelType(modelData.getModelName());
			paramMap.clear();
			paramMap.put("modelId", modelData.getModelId());
			List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
			
			boolean hasDataFlag = true;
			while (hasDataFlag) {
				//修改前5000条，CGBZ_1
				commonDaoIntf.createTable(Sql4WebserviceOut.updateEx9(modelData.getModelTabName(), daoConfResource.getJdbcDriverClassName()));
				//获取这5000条待插入对面网络数据
				List<Map<String, Object>> objMapList = commonDaoIntf.selectObj(Sql4WebserviceOut.getWaitInsert2Data(modelDataAttributes, modelData.getModelTabName()));
				if (objMapList.size()==0 || objMapList==null){//如果为空表示该模型没有新进入数据了，不再向对网同步数据
					hasDataFlag = false;
					break;
				}
				reqPo.setParams(CommonUtil.toJSONString(objMapList));
				String json = CommonUtil.toJSONString(reqPo);
				//System.out.println(json);
				try {
					String responsePoMsg = RestCXFClient.reqRestService(brmpConfResource.getBrmpChangeAdress(), json);
					System.out.println(responsePoMsg);
					ResponsePo responsePo = JSON.parseObject(responsePoMsg, ResponsePo.class);
					if(responsePo.getCode()==ResponseHead.Completenss.getIndex() || responsePo.getCode()==ResponseHead.Success.getIndex() ){
						commonDaoIntf.createTable(Sql4WebserviceOut.updateEx1(modelData.getModelTabName()));
					} else {// ↑上面为成功↑，↓下面为失败↓
						commonDaoIntf.createTable(Sql4WebserviceOut.updateEx2(modelData.getModelTabName()));
					}
				} catch (Exception e) {
					e.printStackTrace();
					commonDaoIntf.createTable(Sql4WebserviceOut.updateEx2(modelData.getModelTabName()));
				}
			}
			
		}
		return "完成";
	}

}
