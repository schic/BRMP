package com.wondersgroup.brmp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.brmp.dao.daoutil.DaoConfResource;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.service.intf.ModelDataIntf;
import com.wondersgroup.brmp.util.cipher.IDUtil;

@Controller
@RequestMapping("/model_admin")
public class ModelAdminController {
	
	@Autowired ModelDataIntf modelDataIntf;
	
	@Autowired AuthService authService;
	
	@Autowired DaoConfResource daoConfResource;
	
	@RequestMapping("/datatype")
	public String dataType(HttpServletRequest request,HttpServletResponse response){
		//Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (null == authInfo.getUserType()){
			authInfo.setUserType(authService.getUserType(authInfo.getUserId()));
			try {
				SessionUtil.setCurrAuthInfo(request, authInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("user".equals(authInfo.getUserType())) {
			request.setAttribute("errorMessage", "一般用户不能访问数据模型管理");//返给页面错误信息
			return "index1";
		} else if ("system".equals(authInfo.getUserType())) {
			OriginSystemInfo originSystemInfo = null;
			if (null == request.getSession().getAttribute("originSystemInfo") ) {
				originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemId(authInfo.getUserId());
				request.getSession().setAttribute("originSystemInfo", originSystemInfo);
			} else {
				originSystemInfo = (OriginSystemInfo) request.getSession().getAttribute("originSystemInfo");
			}
			if (null == originSystemInfo) {//接入系统需要完善信息
				request.setAttribute("errorMessage", "申请管理< 接入系统需要完善信息 >");//返给页面信息
				return "apply_management/apply4system";
				
			}
		}
//		OriginSystemInfo originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemId(authInfo.getUserId());
//		request.getSession().setAttribute("originSystemInfo", originSystemInfo);
		return "model_admin/datatype";
	}
	
	@RequestMapping("/datatype/ajax/querySelect")
	@ResponseBody
	public List<ModelData> dataTypeAjaxQuerySelect(String beginDate,String endDate,String status,String auditStatus,HttpServletRequest request,HttpServletResponse response){
		//Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (null == authInfo.getUserType()){
			authInfo.setUserType(authService.getUserType(authInfo.getUserId()));
			try {
				SessionUtil.setCurrAuthInfo(request, authInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null == request.getSession().getAttribute("originSystemInfo") ) {
			String originSystemId = authInfo.getUserId();
			if ("admin".equals(authInfo.getUserType())){
				originSystemId = "1";//管理员admin默认为"1"
			}
			OriginSystemInfo originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemId(originSystemId);
			request.getSession().setAttribute("originSystemInfo", originSystemInfo);
		}
		OriginSystemInfo originSystemInfo = (OriginSystemInfo) request.getSession().getAttribute("originSystemInfo");
		//"1"现在默认未1系统为管理员，查询全部模板不对系统id过滤
		List<ModelData> modelDatas = modelDataIntf.queryModelData(originSystemInfo.getOriginSystemId(),beginDate,endDate,status,auditStatus);
		return modelDatas;
	}
	
	@RequestMapping("/datatype/ajax/1")
	@ResponseBody
	public List<ModelDataAttribute> dataTypeAjax1(String modelId){
		List<ModelDataAttribute> modelDataAttributes = modelDataIntf.queryModelById(modelId);
		return modelDataAttributes;
	}
	
	@RequestMapping("/datatype/ajax/modelDataAttributes")
	@ResponseBody
	public Map<String,Object> dataTypeAjaxModelDataAttributes(String modelId){
		List<ModelDataAttribute> modelDataAttributes = modelDataIntf.queryModelById(modelId);
		
		Map<String,Object> map = new HashMap<>();
		map.put("modelDataAttributes", modelDataAttributes);
		
		if ("oracle.jdbc.driver.OracleDriver".equals(daoConfResource.getJdbcDriverClassName())){
			map.put("dataBase", 1);//1为oracle 查询后的字段属性全部为大写
		}
		return map;
	}
	
	
	@RequestMapping("/datatype/ajax/2")
	@ResponseBody
	public List<DataType> dataTypeAjax2(HttpServletRequest request){
		@SuppressWarnings("unchecked")
		List<DataType> dataTypes =  (List<DataType>) request.getSession().getAttribute("dataTypes");
		if (null == dataTypes) {
			dataTypes = modelDataIntf.queryDataTypes();
			request.getSession().setAttribute("dataTypes", dataTypes);
		} 
		return dataTypes;
	}
	
	@RequestMapping("/datatype/ajax/updateModelData")
	@ResponseBody
	public String dataTypeAjaxUpdateModelData(String json,String originSystemId,String modelId,String modelName,String modelDescription){
		List<ModelDataAttribute> modelDataAttributes = JSONArray.parseArray(json, ModelDataAttribute.class);
		ModelData modelData = new ModelData();
		modelData.setOriginSystemId(originSystemId);
		modelData.setModelId(modelId);
		modelData.setModelName(modelName);
		modelData.setModelDescription(modelDescription);
		return modelDataIntf.updateModelData(modelDataAttributes, modelData);
	}
	
	@RequestMapping("/datatype/ajax/newModel")
	@ResponseBody
	public String dataTypeAjaxNewModel(String json,String jsonModelData){
		List<ModelDataAttribute> modelDataAttributes = JSONArray.parseArray(json, ModelDataAttribute.class);
		ModelData modelData = JSON.parseObject(jsonModelData, ModelData.class);
		modelData.setModelId(IDUtil.getUUID());
		modelData.setModelTabName("md_".concat(modelData.getModelId().substring(0,20)));//表名
		modelData.setStatus(1);//状态  0:停用 1:启用
		modelData.setAuditStatus(0);//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
		modelData.setModelCreateTime(new Date());
		modelData.setModelUpdeteTime(new Date());
		
		return modelDataIntf.insertModelData(modelDataAttributes, modelData);
	}
	
	@RequestMapping("/datatype/ajax/getTestParams")
	@ResponseBody
	public String dataTypeAjaxGetTestParams(String modelId){
		String testJson = modelDataIntf.getTestParams(modelId);
		return testJson;
	}
	
	@RequestMapping("/datatype/ajax/testSendData")
	@ResponseBody
	public String dataTypeAjaxTestSendData(String modelId){
		return modelDataIntf.testSendData(modelId);
	}
	
	@RequestMapping("/datatype/ajax/getCenterUrl")
	@ResponseBody
	public String dataTypeAjaxGetCenterUrl(){
		return modelDataIntf.getCenterUrl();
	}
	
	@RequestMapping("/datatype/ajax/entityModelData")
	@ResponseBody
	public String dataTypeAjaxEntityModelData(String modelId){
		String json = modelDataIntf.getEntityModelData(modelId);
		return json;
	}
	
	@RequestMapping("/datatype/ajax/clearEntityModelData")
	@ResponseBody
	public String dataTypeAjaxClearEntityModelData(String modelId){
		return modelDataIntf.clearEntityModelData(modelId);
	}
	
	@RequestMapping("/datatype/ajax/commit2audit")
	@ResponseBody
	public String dataTypeAjaxCommit2audit(String modelId,String json){
		@SuppressWarnings("rawtypes")
		List<HashMap> dataList = JSON.parseArray(json, HashMap.class);
		if (dataList.size()<1 || null == dataList) {
			return "未发现验证数据，请刷新数据或传入数据再试";
		}
		return modelDataIntf.sendAudit(modelId);
	}
	
	// 2:审核拒绝 9:审核通过
	@RequestMapping("/datatype/ajax/auditReject")
	@ResponseBody
	public String dataTypeAjaxAuditReject(String modelId,HttpServletRequest request,HttpServletResponse response){
		//Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (null == authInfo.getUserType()){
			authInfo.setUserType(authService.getUserType(authInfo.getUserId()));
			try {
				SessionUtil.setCurrAuthInfo(request, authInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!"admin".equals(authInfo.getUserType())) {
			return "不是系统管理员不能审核";
		}
		return modelDataIntf.setAudit(modelId,2);
	}
	
	@RequestMapping("/datatype/ajax/auditPass")
	@ResponseBody
	public String dataTypeAjaxAuditPass(String modelId,HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (null == authInfo.getUserType()){
			authInfo.setUserType(authService.getUserType(authInfo.getUserId()));
			try {
				SessionUtil.setCurrAuthInfo(request, authInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!"admin".equals(authInfo.getUserType())) {
			return "不是系统管理员不能审核";
		}
		String msg = modelDataIntf.createOfficialTable(modelId);//审核通过在数据库建立正式表
		if (!"建立模型完成".equals(msg)) {
			return "审核时建立模型失败,请检查系统";
		}
		msg = modelDataIntf.createExTable(modelId);
		if (!"建立模型完成".equals(msg)) {
			return "审核时建立扩展表失败,请检查系统";
		}
		return modelDataIntf.setAudit(modelId,9);
	}
	
	
	
}
