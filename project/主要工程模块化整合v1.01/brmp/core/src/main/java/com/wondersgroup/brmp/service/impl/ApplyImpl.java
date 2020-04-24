package com.wondersgroup.brmp.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.brmp.dao.intf.ApplyDaoIntf;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.ApplyAttribute;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.service.intf.ApplyIntf;



@Service
public class ApplyImpl implements ApplyIntf {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired ApplyDaoIntf applyDaoIntf;
	
	@Override
	public String saveApply(ApplyManagement applyManagement, List<ApplyAttribute> applyAttributes) {
		String saveApplyAttributes = commonDaoIntf.saveObj(applyAttributes, "brmp_apply_attribute");
		if(!"数据保存完成save_complates".equals(saveApplyAttributes)){
			return saveApplyAttributes;
		}
		String saveApplyManagement = commonDaoIntf.saveObj(ApplyManagement.class, applyManagement, "brmp_apply_base");
		if(!"数据保存完成save_complates".equals(saveApplyManagement)){
			return saveApplyManagement;
		}
		return "完成";
	}

	@Override
	public List<ApplyManagement> selectApply(String beginDate, String endDate, String auditStatus) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if (!"".equals(auditStatus)  && !"null".equals(auditStatus) && null != auditStatus ){
			paramMap.put("auditStatus", Integer.parseInt(auditStatus));
		}
		Date dBeginDate = null;
		if (!"".equals(beginDate)  && !"null".equals(beginDate) && null != beginDate ){
			try {
				dBeginDate = DateUtils.parseDate(beginDate, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("beginDate", dBeginDate);
		}
		Date dEndDate = null;
		if (!"".equals(endDate)  && !"null".equals(endDate) && null != endDate ){
			try {
				dEndDate = DateUtils.parseDate(endDate, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("endDate", dEndDate);
		}
		if (null !=dBeginDate && null != dEndDate) {
			if (dEndDate.before(dBeginDate)){
				return null;
			}
		}
		return applyDaoIntf.qureyApplyManagement(paramMap);
		
	}
	
	@Override
	public List<ApplyManagement> selectApplyUser(String beginDate, String endDate, String auditStatus, String uname) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if (!"".equals(auditStatus)  && !"null".equals(auditStatus) && null != auditStatus ){
			paramMap.put("auditStatus", Integer.parseInt(auditStatus));
		}
		Date dBeginDate = null;
		if (!"".equals(beginDate)  && !"null".equals(beginDate) && null != beginDate ){
			try {
				dBeginDate = DateUtils.parseDate(beginDate, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("beginDate", dBeginDate);
		}
		Date dEndDate = null;
		if (!"".equals(endDate)  && !"null".equals(endDate) && null != endDate ){
			try {
				dEndDate = DateUtils.parseDate(endDate, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("endDate", dEndDate);
		}
		if (null !=dBeginDate && null != dEndDate) {
			if (dEndDate.before(dBeginDate)){
				return null;
			}
		}
		
		if (!"".equals(uname)  && !"null".equals(uname) && null != uname ){
			paramMap.put("userName", uname);
		}
		return applyDaoIntf.qureyApplyManagement(paramMap);
	}

	@Override
	public List<ModelDataAttribute> queryApplyById4ModelDataAttribute(String applyId) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("applyId", applyId);
		List<ApplyAttribute> applyAttributes = commonDaoIntf.selectObjListByParam(ApplyAttribute.class, paramMap);
		
		paramMap.clear();
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i=0;i<applyAttributes.size();i++){
			if(null != map.get(applyAttributes.get(i).getModelId()) ){
				continue;
			} else {
				map.put(applyAttributes.get(i).getModelId(), applyAttributes.get(i).getModelId());
				paramMap.put("modelId".concat(String.valueOf(i)), applyAttributes.get(i).getModelId());
				
			}
			
		}
		List<ModelData> modelDatas = commonDaoIntf.selectObjByParamlist(ModelData.class, paramMap);
		
		paramMap.clear();
		for (int i=0;i<modelDatas.size();i++){
			paramMap.put("modelId".concat(String.valueOf(i)), modelDatas.get(i).getModelId());
			
		}
		List<ModelDataAttribute> modelDataAttributesTemp = commonDaoIntf.selectObjByParamlist(ModelDataAttribute.class, paramMap);
		List<ModelDataAttribute> modelDataAttributes = new ArrayList<ModelDataAttribute>();
		for(int i=0;i<applyAttributes.size();i++){
			for(int j=0;j<modelDataAttributesTemp.size();j++){
				if(applyAttributes.get(i).getModelId().equals(modelDataAttributesTemp.get(j).getModelId() ) && applyAttributes.get(i).getModelColName().equals(modelDataAttributesTemp.get(j).getModelColName()) ){
					modelDataAttributes.add(modelDataAttributesTemp.get(j));
				}
			}
			
		}
		return modelDataAttributes;
	}
	
	@Override
	public List<List<ModelDataAttribute>> queryApplyById4ApplyDataAttribute(String applyId) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("applyId", applyId);
		List<ApplyAttribute> applyAttributes = commonDaoIntf.selectObjListByParam(ApplyAttribute.class, paramMap);
		
		paramMap.clear();
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i=0;i<applyAttributes.size();i++){
			if(null != map.get(applyAttributes.get(i).getModelId()) ){
				continue;
			} else {
				map.put(applyAttributes.get(i).getModelId(), applyAttributes.get(i).getModelId());
				paramMap.put("modelId".concat(String.valueOf(i)), applyAttributes.get(i).getModelId());
				
			}
			
		}
		List<ModelData> modelDatas = commonDaoIntf.selectObjByParamlist(ModelData.class, paramMap);
		
		List<List<ModelDataAttribute>> applyModelDataAttributes = new ArrayList<List<ModelDataAttribute>>();
		for (int i=0;i<modelDatas.size();i++){
			paramMap.clear();
			paramMap.put("modelId", modelDatas.get(i).getModelId());

			List<ModelDataAttribute> modelDataAttributesTemp = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
			List<ModelDataAttribute> modelDataAttributes = new ArrayList<ModelDataAttribute>();
			for(int k=0;k<applyAttributes.size();k++){
				for(int j=0;j<modelDataAttributesTemp.size();j++){
					if(applyAttributes.get(k).getModelId().equals(modelDataAttributesTemp.get(j).getModelId() ) && applyAttributes.get(k).getModelColName().equals(modelDataAttributesTemp.get(j).getModelColName()) ){
						modelDataAttributes.add(modelDataAttributesTemp.get(j));
					}
				}
				
			}
			applyModelDataAttributes.add(modelDataAttributes);
		}
		
		return applyModelDataAttributes;
	}

	@Override
	public String setAudit(String applyId, int i) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("applyId", applyId);
		ApplyManagement applyManagement = (ApplyManagement) commonDaoIntf.selectObjByParam(ApplyManagement.class, paramMap);
		if (null == applyManagement){
			return "申请不存在,请检查";
		} else if (applyManagement.getAuditStatus()!=1) {
			return "申请不是待审核状态";
		} else if (1==applyManagement.getAuditStatus()) {
			applyManagement.setAuditStatus(i); //审核状态 1:待审核 2:审核拒绝 9:审核通过
			commonDaoIntf.updateObj(applyManagement, "brmp_apply_base", "applyId");
		}
		return "审核完成";
	}

	

}
