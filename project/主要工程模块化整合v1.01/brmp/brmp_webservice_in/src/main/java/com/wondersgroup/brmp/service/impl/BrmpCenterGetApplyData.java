package com.wondersgroup.brmp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.applypo.ReqApplyBatchParams;
import com.wondersgroup.brmp.po.applypo.ResApplyBatchParams;
import com.wondersgroup.brmp.po.applypo.ResApplyParams;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.webservicepo.ResponsePo;
import com.wondersgroup.brmp.service.intf.BrmpCenterService4ws;
import com.wondersgroup.brmp.service4webservice.ApplyService4Webservice;
import com.wondersgroup.brmp.util.common.CommonUtil;
import com.wondersgroup.brmp.util.webserviceutil.ResponseHead;
import com.wondersgroup.brmp.util.webserviceutil.ResponsePoMsg;


@Service("apply")
public class BrmpCenterGetApplyData implements BrmpCenterService4ws {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired ApplyService4Webservice applyService4Webservice;
	
	//@Autowired ApplyIntf applyIntf;
	
	//@Autowired ModelDataDaoIntf modelDataDaoIntf;
	
	@Override
	public ResponsePo parseWs(String params, String applyId, String modelName) {
		
		//验证applyId审核是否通过
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("applyId", applyId);
		ApplyManagement applyManagement = (ApplyManagement) commonDaoIntf.selectObjByParam(ApplyManagement.class, paramMap);
		if (applyManagement.getAuditStatus() != 9){
			return ResponsePoMsg.response2Obj(ResponseHead.Error,"该接口审核未通过审核，或正在审核中");
		}
		
		
		ResApplyParams resApplyParams = null;
		List<Map<String, Object>> applyDates = new ArrayList<>();
		
		List<List<ModelDataAttribute>> applyModelDataAttributes = applyService4Webservice.queryApplyById4ApplyDataAttribute(applyId);
		if ("batch".equals(modelName) ){//批量获取数据
			if (applyModelDataAttributes.size()==1){//只申请了一种模型字段数据
				List<ModelDataAttribute> modelDataAttributes = applyModelDataAttributes.get(0);
				List<String> attributeNames = new ArrayList<String>();
				for(int i=0;i<modelDataAttributes.size();i++){
					attributeNames.add(modelDataAttributes.get(i).getModelColName());
				}
				paramMap.clear();
				paramMap.put("modelId", modelDataAttributes.get(0).getModelId());
				ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
				ReqApplyBatchParams rParams = null;
				try {
					rParams = JSON.parseObject(params, ReqApplyBatchParams.class);	
				} catch (Exception e) {
					e.printStackTrace();
					return ResponsePoMsg.response2Obj(ResponseHead.Error, "params参数的json格式出错——".concat(e.getMessage()));
				} finally {
					if (rParams == null) {
						return ResponsePoMsg.response2Obj(ResponseHead.Error, "params参数的格式错误");
					}	
				}
				
				applyDates = commonDaoIntf.selectObj(attributeNames, modelData.getModelTabName(), rParams.getPageNo() , rParams.getPageSize() );
				ResApplyBatchParams<Map<String, Object>> resApplyBatchParams = new ResApplyBatchParams<Map<String, Object>>();
				resApplyBatchParams.setData(applyDates);
				resApplyBatchParams.setPages(rParams.getPageNo());
				resApplyBatchParams.setPageSize(rParams.getPageSize());
				resApplyBatchParams.setRecords(commonDaoIntf.getRecords(modelData.getModelTabName()));
				resApplyParams = resApplyBatchParams;
				
			} else {//申请多个模型的数据
				// TODO Auto-generated method stub
				return ResponsePoMsg.response2Obj(ResponseHead.NoSupport, "功能未完成");
			}
			
		} else if ("condition".equals(modelName) ){//通过条件获取数据
			// TODO Auto-generated method stub
			return ResponsePoMsg.response2Obj(ResponseHead.NoSupport, "功能未完成");
		} else {
			return ResponsePoMsg.response2Obj(ResponseHead.NoSupport, "不支持的类型ModelType:".concat(modelName));
		}
		
		return ResponsePoMsg.response2Obj(ResponseHead.Success, CommonUtil.toJSONString(resApplyParams));
	}

}
