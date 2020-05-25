package com.wondersgroup.brmp.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.applypo.ReqApplyParams;
import com.wondersgroup.brmp.po.applypo.ResApplyParams;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.webservicepo.ResponsePo;
import com.wondersgroup.brmp.service.intf.BrmpCenterService4ws;
import com.wondersgroup.brmp.service4webservice.ApplyService4Webservice;
import com.wondersgroup.brmp.util.cipher.EncryptUtil;
import com.wondersgroup.brmp.util.common.CommonUtil;
import com.wondersgroup.brmp.util.webserviceutil.ResponseHead;
import com.wondersgroup.brmp.util.webserviceutil.ResponsePoMsg;

/**
 * 通过请求申请，返回给请求者需要数据。
 */
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
		
		ResApplyParams<Map<String, Object>> resApplyParams = new ResApplyParams<Map<String, Object>>();
		List<Map<String, Object>> applyDates = new ArrayList<>();
		
		List<List<ModelDataAttribute>> applyModelDataAttributes = applyService4Webservice.queryApplyById4ApplyDataAttribute(applyId);
		
		if (applyModelDataAttributes.size()==1){//只申请了一种模型字段数据
			List<ModelDataAttribute> modelDataAttributes = applyModelDataAttributes.get(0);
			List<String> attributeNames = new ArrayList<String>();
			for(int i=0;i<modelDataAttributes.size();i++){
				attributeNames.add(modelDataAttributes.get(i).getModelColName());
			}
			paramMap.clear();
			paramMap.put("modelId", modelDataAttributes.get(0).getModelId());
			ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
			
			ReqApplyParams rParams = null; 
			try {
				rParams = JSON.parseObject(params, ReqApplyParams.class);	
			} catch (Exception e) {
				e.printStackTrace();
				return ResponsePoMsg.response2Obj(ResponseHead.Error, "params参数的json格式出错——".concat(e.getMessage()));
			} finally {
				if (rParams == null) {
					return ResponsePoMsg.response2Obj(ResponseHead.Error, "params参数的格式错误");
				}	
			}
			
			if ("batch".equals(modelName) ){//0:批量数据申请
				try {
					applyDates = commonDaoIntf.selectObj(attributeNames, modelData.getModelTabName(), rParams.getPageNo() , rParams.getPageSize() );
				} catch (Exception e) {
					e.printStackTrace();
					return ResponsePoMsg.response2Obj(ResponseHead.Error, e.getMessage());
				}
				resApplyParams.setRecords(commonDaoIntf.getRecords(modelData.getModelTabName()));
			} else if ("date".equals(modelName)) {//1:带入库日期批量数据申请
				if (null == rParams.getBigenDate() || null == rParams.getEndDate()) {
					return ResponsePoMsg.response2Obj(ResponseHead.Error, "入库日期未正确 bigenDate endDate");
				}
				try {
					applyDates = commonDaoIntf.selectObj(attributeNames, modelData.getModelTabName(), rParams.getPageNo(), rParams.getPageSize(), rParams.getBigenDate(), rParams.getEndDate(), "JLGXSJ");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponsePoMsg.response2Obj(ResponseHead.Error, e.getMessage());
				}
				resApplyParams.setRecords(commonDaoIntf.getRecords(modelData.getModelTabName(), rParams.getBigenDate(), rParams.getEndDate(), "JLGXSJ"));
			} else if ("condition".equals(modelName)) {//2:带条件查询批量数据申请
				
				Iterator<Map.Entry<String, Object>> e = rParams.getParamMap().entrySet().iterator();
				while (e.hasNext()) {
					Map.Entry<String, Object> param = e.next();
					boolean conditionFlag = false;
					for(int i=0;i<attributeNames.size();i++){
						if (attributeNames.get(i).equals(param.getKey())){
							conditionFlag = true;
						}
					}
					if(!conditionFlag){
						return ResponsePoMsg.response2Obj(ResponseHead.NoSupport, "未找到需要查询的条件参数字段名称:".concat(param.getKey()));
					}
					try {
						param.setValue(DateUtils.parseDate(param.getValue().toString(), "yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e1) {
						//如果是日期格式的参数，则转为日期保存
					}
				}
				
				try {
					applyDates = commonDaoIntf.selectObj(attributeNames, modelData.getModelTabName(), rParams.getPageNo(), rParams.getPageSize(), rParams.getParamMap());
				} catch (Exception e1) {
					e1.printStackTrace();
					return ResponsePoMsg.response2Obj(ResponseHead.Error, "获取数据失败,请查看condition条件参数,数据格式是否正确");
				}
				resApplyParams.setRecords(commonDaoIntf.getRecords(modelData.getModelTabName(),rParams.getParamMap()));
				
			} else {
				return ResponsePoMsg.response2Obj(ResponseHead.NoSupport, "不支持的类型ModelType:".concat(modelName));
			}
			
			resApplyParams.setData(applyDates);
			resApplyParams.setPages(rParams.getPageNo());
			resApplyParams.setPageSize(rParams.getPageSize());
			
			
		} else {//申请多个模型的数据
			// TODO Auto-generated method stub
			return ResponsePoMsg.response2Obj(ResponseHead.NoSupport, "功能未完成");
		}
		
		
		return ResponsePoMsg.response2Obj(ResponseHead.Success, EncryptUtil.enctypt(CommonUtil.toJSONString(resApplyParams), applyManagement));
	}

}
