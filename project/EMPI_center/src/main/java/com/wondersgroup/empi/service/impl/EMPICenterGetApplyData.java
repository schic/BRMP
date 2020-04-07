package com.wondersgroup.empi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.empi.dao.intf.CommonDaoIntf;
import com.wondersgroup.empi.dao.intf.ModelDataDaoIntf;
import com.wondersgroup.empi.po.empipo.ApplyManagement;
import com.wondersgroup.empi.po.empipo.ModelData;
import com.wondersgroup.empi.po.empipo.ModelDataAttribute;
import com.wondersgroup.empi.po.webservicepo.ReqApplyBatchParams;
import com.wondersgroup.empi.po.webservicepo.ResApplyBatchParams;
import com.wondersgroup.empi.po.webservicepo.ResApplyParams;
import com.wondersgroup.empi.po.webservicepo.ResponsePo;
import com.wondersgroup.empi.service.intf.ApplyIntf;
import com.wondersgroup.empi.service.intf.EMPICenterService4ws;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.common.ResponseHead;
import com.wondersgroup.empi.util.common.ResponsePoMsg;

@Service("apply")
public class EMPICenterGetApplyData implements EMPICenterService4ws {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired ApplyIntf applyIntf;
	
	@Autowired ModelDataDaoIntf modelDataDaoIntf;
	
	@Override
	public ResponsePo parseWs(String params, String applyId, String modelName) {
		
		//验证applyId审核是否通过
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("applyId", applyId);
		ApplyManagement applyManagement = (ApplyManagement) commonDaoIntf.selectObjByParam(ApplyManagement.class, "brmp_apply_base", paramMap);
		if (applyManagement.getAuditStatus() != 9){
			return ResponsePoMsg.response2Obj(ResponseHead.Error,"该接口审核未通过审核，或正在审核中");
		}
		
		
		ResApplyParams resApplyParams = null;
		List<Map<String, Object>> applyDates = new ArrayList<>();
		List<List<ModelDataAttribute>> applyModelDataAttributes = applyIntf.queryApplyById4ApplyDataAttribute(applyId);
		if ("batch".equals(modelName) ){//批量获取数据
			if (applyModelDataAttributes.size()==1){//只申请了一种模型字段数据
				List<ModelDataAttribute> modelDataAttributes = applyModelDataAttributes.get(0);
				List<String> attributeNames = new ArrayList<String>();
				for(int i=0;i<modelDataAttributes.size();i++){
					attributeNames.add(modelDataAttributes.get(i).getModelColName());
				}
				ModelData modelData = modelDataDaoIntf.queryModelDataByModelId(modelDataAttributes.get(0).getModelId());
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
