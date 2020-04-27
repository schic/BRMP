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

import com.wondersgroup.brmp.dao.daoutil.DaoConfResource;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.dao.intf.ModelDataDaoIntf;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.service.intf.ModelDataIntf;
import com.wondersgroup.brmp.util.common.CommonUtil;
import com.wondersgroup.brmp.util.common.ModelDataUtil;
import com.wondersgroup.brmp.util.common.ViewDataVerifyUtil;


@Service
public class ModelDataImpl implements ModelDataIntf {

	@Autowired ModelDataDaoIntf modelDataDaoIntf;
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired DaoConfResource daoConfResource;
	
	@Override
	public List<ModelData> queryModelData() {
		return commonDaoIntf.selectObj(ModelData.class);
	}
	
	@Override
	public List<ModelData> queryModelData(String originSystemId, String beginDate, String endDate, String status,
			String auditStatus) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if (!"".equals(originSystemId) || !"null".equals(originSystemId) || null != originSystemId){
			if ("1".equals(originSystemId) ) {//不做任何处理不添加查询条件
				//admin默认系统号 "1"
			} else {
				paramMap.put("originSystemId", originSystemId);
			}
		} else {
			return null;
		}
		if (!"".equals(status)  && !"null".equals(status) && null != status ){
			paramMap.put("status", Integer.parseInt(status));
		}
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
		return modelDataDaoIntf.queryModelData(paramMap);
	}


	@Override
	public List<ModelDataAttribute> queryModelById(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		return modelDataAttributes;
	}

	@Override
	public List<DataType> queryDataTypes() {
		List<DataType> dataTypes = commonDaoIntf.selectObj(DataType.class);
		return dataTypes;
	}

	@Override
	public String updateModelData(List<ModelDataAttribute> modelDataAttributes,ModelData modelData) {
		String viewDataVerify = ViewDataVerifyUtil.verifyModelDataAttributes(modelDataAttributes);
		if (!viewDataVerify.equals("pass")){
			return viewDataVerify;
		}
		
		if (null == modelData.getModelId() || "".equals(modelData.getModelId()) ){
			return "模型不存在";
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelData.getModelId());
		ModelData oldModelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == oldModelData) {
			return "模型不存在";
		} else if (oldModelData.getAuditStatus()==9 || oldModelData.getAuditStatus()==1 ){//0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return "模型已经审核通过或正在审核，不能再修改";
		}
		
		try {
			return modelDataDaoIntf.updateModelData(modelDataAttributes, modelData);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String insertModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) {
		String viewDataVerify = ViewDataVerifyUtil.verifyModelDataAttributes(modelDataAttributes);
		if (!viewDataVerify.equals("pass")){
			return viewDataVerify;
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelName", modelData.getModelName());
		ModelData oldModelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null != oldModelData) {
			return "模型名称已经被使用,请修改模型名称";
		}
		
		try {
			return modelDataDaoIntf.insertModelData(modelDataAttributes, modelData);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Override
	public String testSendData(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if (null == modelId || "".equals(modelId) ){
			return "模型不存在";
		}
		paramMap.put("modelId", modelId);
		ModelData oldModelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == oldModelData) {
			return "模型不存在";
		} else if (oldModelData.getAuditStatus()==9 || oldModelData.getAuditStatus()==1 ){//0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return "模型已经审核通过或正在审核，不用再测试";
		}
		
		paramMap.clear();
		paramMap.put("modelId", oldModelData.getModelId());
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		List<DataType> dataTypes = commonDaoIntf.selectObj(DataType.class);
		Map<Integer, String> dataTypeMap = ModelDataUtil.dataTypeList2Map(dataTypes);
		
		String tableName = oldModelData.getModelTabName().concat("_temp");//拼接临时表标记
		if (commonDaoIntf.isTable(tableName) ){//表存在
			commonDaoIntf.delDropTable(tableName);
		}
		
		String createSql = ModelDataUtil.createModelDataSql(tableName, modelDataAttributes, dataTypeMap, daoConfResource.getJdbcDriverClassName());
				
		//创建表
		String createTableMsg = commonDaoIntf.createTable(createSql);
		if ("建立模型失败".equals(createTableMsg)) {
			return "模型在后台创建失败,请联系管理员";
		}
		
		return createTableMsg;
	}

	@Override
	public String getTestParams(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		paramMap.clear();
		paramMap.put("originSystemId", modelData.getOriginSystemId());
		OriginSystemInfo originSystemInfo =  (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{\n");
		sBuffer.append("\"modelType\":\"").append(modelData.getModelName()).append("\",\n");
		sBuffer.append("\"params\":\"[\n");
		sBuffer.append("           {\n");
		for(int i=0;i<modelDataAttributes.size();i++){
			ModelDataAttribute modelDataAttribute = modelDataAttributes.get(i);
			String testValue1 = "";
			if ("xgbz".equals(modelDataAttribute.getModelColName())){
				testValue1 = "0";
			} else if ("Id".equals(modelDataAttribute.getModelColName())) {
				testValue1 = "\\\"af93e34c2d1\\\"";
			} else if ("originId".equals(modelDataAttribute.getModelColName())) {
				testValue1 = "\\\"personId1\\\"";
			} else if ("updateTime".equals(modelDataAttribute.getModelColName())) {
				testValue1 = "\\\"2019-09-01 13:22:01\\\"";
			} else {
				if (0==modelDataAttribute.getModelColType()){//String
					testValue1 = "\\\"testString1\\\"";
				} else if (1==modelDataAttribute.getModelColType() || 4==modelDataAttribute.getModelColType() ){//int long
					testValue1 = "1";
				} else if (2==modelDataAttribute.getModelColType() || 5==modelDataAttribute.getModelColType() ){//float double
					testValue1 = "0.5";
				} else if (3==modelDataAttribute.getModelColType()){//Date
					testValue1 = "\\\"2019-12-04 02:04:05\\\"";
				}	
			}
			sBuffer.append("           \\\"").append(modelDataAttribute.getModelColName()).append("\\\":").append(testValue1);
			if (i!=modelDataAttributes.size()-1){
				sBuffer.append(",\n");
			} else {
				sBuffer.append("  },{ \n");
			}
		}
		for(int i=0;i<modelDataAttributes.size();i++){
			ModelDataAttribute modelDataAttribute = modelDataAttributes.get(i);
			String testValue2 = "";
			if ("xgbz".equals(modelDataAttribute.getModelColName())){
				testValue2 = "0";
			} else if ("Id".equals(modelDataAttribute.getModelColName())) {
				testValue2 = "\\\"af93e34c2d2\\\"";
			} else if ("originId".equals(modelDataAttribute.getModelColName())) {
				testValue2 = "\\\"personId2\\\"";
			} else if ("updateTime".equals(modelDataAttribute.getModelColName())) {
				testValue2 = "\\\"2019-09-02 14:23:08\\\"";
			} else {
				if (0==modelDataAttribute.getModelColType()){//String
					testValue2 = "\\\"testString2\\\"";
				} else if (1==modelDataAttribute.getModelColType() || 4==modelDataAttribute.getModelColType() ){//int long
					testValue2 = "2";
				} else if (2==modelDataAttribute.getModelColType() || 5==modelDataAttribute.getModelColType() ){//float double
					testValue2 = "0.6";
				} else if (3==modelDataAttribute.getModelColType()){//Date
					testValue2 = "\\\"2019-12-05 03:16:08\\\"";
				}	
			}
			sBuffer.append("           \\\"").append(modelDataAttribute.getModelColName()).append("\\\":").append(testValue2);
			if (i!=modelDataAttributes.size()-1){
				sBuffer.append(",\n");
			} else {
				sBuffer.append("  }\n");
			}
		}
		sBuffer.append("            ]\", \n");
		sBuffer.append("\"username\":\"").append(originSystemInfo.getUsername()).append("\", \n");
		sBuffer.append("\"password\":\"***\", \n");
		sBuffer.append("\"paramType\":\"model\" \n");
		sBuffer.append("}");
		//System.out.println(sBuffer);
		return sBuffer.toString();
	}

	@Override
	public String getCenterUrl() {
		return commonDaoIntf.getCenterUrl();
	}

	@Override
	public String getEntityModelData(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		List<String> attributeNames = new ArrayList<String>();
		for(int i=0;i<modelDataAttributes.size();i++){
			attributeNames.add(modelDataAttributes.get(i).getModelColName());
		}
		List<Map<String, Object>> entityModelData = commonDaoIntf.selectObj(attributeNames, modelData.getModelTabName().concat("_temp"), 1, 20);
		return CommonUtil.toJSONString(entityModelData);
	}
	
	@Override
	public String getOfficialModelData(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		List<String> attributeNames = new ArrayList<String>();
		for(int i=4;i<modelDataAttributes.size();i++){//不要系统规定的前4条字段
			attributeNames.add(modelDataAttributes.get(i).getModelColName());
		}
		List<Map<String, Object>> entityModelData = commonDaoIntf.selectObj(attributeNames, modelData.getModelTabName(), 1, 20);
		return CommonUtil.toJSONString(entityModelData);
	}

	@Override
	public String clearEntityModelData(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == modelData) {
			return "模型不存在";
		} else if (modelData.getAuditStatus()==9 || modelData.getAuditStatus()==1 ){//0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return "模型已经审核通过或正在审核，不用再测试";
		}
		return commonDaoIntf.delTruncateTable(modelData.getModelTabName().concat("_temp"));
	}

	@Override
	public String sendAudit(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == modelData) {
			return "模型不存在";
		} else if (modelData.getAuditStatus()==9 || modelData.getAuditStatus()==1 ){//0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return "模型已经审核通过或正在审核，不用再提交审核";
		}
		
		if (0==modelData.getAuditStatus()){
			modelData.setAuditStatus(1);//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
			modelData.setModelUpdeteTime(new Date());
			commonDaoIntf.updateObj(modelData, "brmp_conf_origin_system_modelbase", modelId);
			//modelDataDaoIntf.updateModelData(modelData);
		}
		return "提交审核完成";
	}
	
	@Override
	public List<OriginSystemInfo> queryOriginSystemByOriginSystemIds(List<String> originSystemIds) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		for(int i=0;i<originSystemIds.size();i++){
			paramMap.put("originSystemId".concat(String.valueOf(i)), originSystemIds.get(i));
		}
		List<OriginSystemInfo> originSystemInfos = commonDaoIntf.selectObjByParamlist(OriginSystemInfo.class, paramMap);
		return originSystemInfos;
	}
	
	@Override
	public OriginSystemInfo queryOriginSystemByOriginSystemId(String originSystemId) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("originSystemId", originSystemId);
		OriginSystemInfo originSystemInfo =  (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		return originSystemInfo;
	}


	@Override
	public OriginSystemInfo queryOriginSystemByOriginSystemName(String originSystemName) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("originSystemName", originSystemName);
		OriginSystemInfo originSystemInfo =  (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		return originSystemInfo;
	}

	@Override
	public OriginSystemInfo queryOriginSystemByOriginSystemCName(String originSystemCname) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("originSystemCname", originSystemCname);
		OriginSystemInfo originSystemInfo =  (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		return originSystemInfo;
	}

	/**
	 * 保存或更新系统
	 */
	@Override
	public String saveOriginSystem(OriginSystemInfo originSystemInfo) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		String tableName = "brmp_conf_origin_system_info";
		paramMap.put("originSystemId", originSystemInfo.getOriginSystemId());
		OriginSystemInfo oldOriginSystemInfo = (OriginSystemInfo) commonDaoIntf.selectObjByParam(OriginSystemInfo.class, paramMap);
		if (null == oldOriginSystemInfo) {
			List<OriginSystemInfo> list = new ArrayList<OriginSystemInfo>();
			list.add(originSystemInfo);
			commonDaoIntf.saveObj(list, tableName);
		} else {
			commonDaoIntf.updateObj(originSystemInfo, tableName, "originSystemId");
		}
		return "更新完成";
	}

	@Override
	public String setAudit(String modelId, int i) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == modelData) {
			return "模型不存在";
		} else if (modelData.getAuditStatus()!=1){//0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return "模型不是待审核状态";
		} else if (1 == modelData.getAuditStatus()) {
			modelData.setAuditStatus(i);//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
			modelData.setModelUpdeteTime(new Date());
			commonDaoIntf.updateObj(modelData, "brmp_conf_origin_system_modelbase", modelId);
		}
		return "审核完成";
		
	}

	@Override
	public String createOfficialTable(String modelId) {
		if (null == modelId || "".equals(modelId) ){
			return "模型不存在";
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		ModelData oldModelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == oldModelData) {
			return "模型不存在";
		} else if (oldModelData.getAuditStatus()!=1 ){//0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return "模型不是待审核状态";
		}
		paramMap.clear();
		paramMap.put("modelId", oldModelData.getModelId());
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		List<DataType> dataTypes = commonDaoIntf.selectObj(DataType.class);
		Map<Integer, String> dataTypeMap = ModelDataUtil.dataTypeList2Map(dataTypes);
		
		String tableName = oldModelData.getModelTabName();//根据表名创建正式表
		if (commonDaoIntf.isTable(tableName) ){//表存在
			commonDaoIntf.delDropTable(tableName);
		}
		
		String createSql = ModelDataUtil.createModelDataSql(tableName, modelDataAttributes, dataTypeMap, daoConfResource.getJdbcDriverClassName());
				
		//创建表
		String createTableMsg = commonDaoIntf.createTable(createSql);
		if ("建立模型失败".equals(createTableMsg)) {
			return "模型在后台创建失败,请联系管理员";
		}
		
		return createTableMsg;
	}

	

	

	
	
}
