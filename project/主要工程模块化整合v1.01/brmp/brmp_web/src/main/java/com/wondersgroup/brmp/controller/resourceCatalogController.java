package com.wondersgroup.brmp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.brmp.po.empipo.ApplyAttribute;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.service.intf.ApplyIntf;
import com.wondersgroup.brmp.service.intf.ModelDataIntf;
import com.wondersgroup.brmp.util.cipher.IDUtil;

@Controller
@RequestMapping("/resource_catalog")
public class resourceCatalogController {
	
	@Autowired ModelDataIntf modelDataIntf;
	
	@Autowired ApplyIntf applyIntf;
	
	@Autowired AuthService authService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/catalog")
	public String catalog(HttpServletRequest request){
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (null == authInfo.getUserType()){
			authInfo.setUserType(authService.getUserType(authInfo.getUserId()));
			try {
				SessionUtil.setCurrAuthInfo(request, authInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
//		if ("system".equals(ssoUser.get("userType"))) {
//			request.setAttribute("errorMessage", "系统接入不需要访问资源目录");//返给页面错误信息
//			return "index1";
//		}
		if ("system".equals(authInfo.getUserType())) {
			request.setAttribute("errorMessage", "系统接入不需要访问资源目录");//返给页面错误信息
			return "index1";
		}
		
		HttpSession httpSession = request.getSession();
		List<ModelData> modelDatas = null;
		if (null == httpSession.getAttribute("modelDatas")) {
			modelDatas = modelDataIntf.queryModelData("1", "", "", "1", "9");
			httpSession.setAttribute("modelDatas", modelDatas);
		}
		if (null == httpSession.getAttribute("originSystemInfos")) {
			List<String> originSystemIds = new ArrayList<String>();
			if (null == modelDatas){
				modelDatas = (List<ModelData>) httpSession.getAttribute("modelDatas");
			}
			for (int i=0;i<modelDatas.size();i++){
				String originSystemId = modelDatas.get(i).getOriginSystemId();
				if (!originSystemIds.contains(originSystemId)){
					originSystemIds.add(originSystemId);
				}
			}
			List<OriginSystemInfo> originSystemInfos = modelDataIntf.queryOriginSystemByOriginSystemIds(originSystemIds);
			httpSession.setAttribute("originSystemInfos", originSystemInfos);
		}
		
		return "resource_catalog/catalog";
	}
	
	@RequestMapping("/catalog/ajax/treeModel")
	@ResponseBody
	public List<Map<String, Object>> catalogAjaxTreeModel(HttpServletRequest request){
		
		@SuppressWarnings("unchecked")
		List<ModelData> modelDatas = (List<ModelData>) request.getSession().getAttribute("modelDatas");
		
		List<String> originSystemIds = new ArrayList<String>();
		
		for (int i=0;i<modelDatas.size();i++){
			String originSystemId = modelDatas.get(i).getOriginSystemId();
			if (!originSystemIds.contains(originSystemId)){
				originSystemIds.add(originSystemId);
			}
		}
		
		@SuppressWarnings("unchecked")
		List<OriginSystemInfo> originSystemInfos = (List<OriginSystemInfo>) request.getSession().getAttribute("originSystemInfos");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i=0;i<modelDatas.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", modelDatas.get(i).getModelName());
			map.put("value", modelDatas.get(i).getModelId());
			String originSystemName = "";
			a:
			for (int j=0;j<originSystemInfos.size();j++) {
				String originSystemId = originSystemInfos.get(j).getOriginSystemId();
				if (originSystemId.equals(modelDatas.get(i).getOriginSystemId()) ){
					originSystemName = originSystemInfos.get(j).getOriginSystemCname();
					break a;
				}
			}
			map.put("group", originSystemName);
			list.add(map);
		}
		
		
		return list;
	}
	
	@RequestMapping("/catalog/ajax/treeModel4applyDataMoreModel")
	@ResponseBody
	public List<Map<String, Object>> catalogAjaxTreeModel4applyDataMoreModel(HttpServletRequest request,String modelId){
		
		List<ModelData> modelDatas = modelDataIntf.queryModelData("1", "", "", "1", "9");
		
		List<String> originSystemIds = new ArrayList<String>();
		
		int index = -1;
		for (int i=0;i<modelDatas.size();i++){
			//modelId同选择的主模型的modelId相同的在集合的索引
			if(modelId.equals(modelDatas.get(i).getModelId() ) ){
				index = i;
			}
			
			String originSystemId = modelDatas.get(i).getOriginSystemId();
			if (!originSystemIds.contains(originSystemId)){
				originSystemIds.add(originSystemId);
			}
		}
		if(index!=-1){//移除选择的主要的模型，不需要再选择。
			modelDatas.remove(index);
		}
		
		@SuppressWarnings("unchecked")
		List<OriginSystemInfo> originSystemInfos = (List<OriginSystemInfo>) request.getSession().getAttribute("originSystemInfos");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i=0;i<modelDatas.size();i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", modelDatas.get(i).getModelName());
			map.put("value", modelDatas.get(i).getModelId());
			String originSystemName = "";
			a:
			for (int j=0;j<originSystemInfos.size();j++) {
				String originSystemId = originSystemInfos.get(j).getOriginSystemId();
				if (originSystemId.equals(modelDatas.get(i).getOriginSystemId()) ){
					originSystemName = originSystemInfos.get(j).getOriginSystemCname();
					break a;
				}
			}
			map.put("group", originSystemName);
			list.add(map);
		}
		
		
		return list;
	}
	
	@RequestMapping("/catalog/ajax/getInitData")
	@ResponseBody
	public Map<String, Object> catalogAjaxGetInitData(HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		@SuppressWarnings("unchecked")
		List<ModelData> modelDatas = (List<ModelData>) httpSession.getAttribute("modelDatas");
		
		Map<String, Object> modelDatasMap = new HashMap<String, Object>();
		for (int i=0;i<modelDatas.size();i++) {
			modelDatasMap.put(modelDatas.get(i).getModelId(),modelDatas.get(i));  
		}
		
		@SuppressWarnings("unchecked")
		List<DataType> dataTypes = (List<DataType>) request.getSession().getAttribute("dataTypes");
		if (null == request.getSession().getAttribute("dataTypes")) {
			dataTypes = modelDataIntf.queryDataTypes();
			request.getSession().setAttribute("dataTypes", dataTypes);
		} 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modelDatasMap", modelDatasMap);
		map.put("dataTypes", dataTypes);
		
		return map;
	}
	
	@RequestMapping("/catalog/ajax/getModelCol")
	@ResponseBody
	public List<ModelDataAttribute> dataTypeAjaxGetModelCol(String modelId){
		List<ModelDataAttribute> modelDataAttributes = modelDataIntf.queryModelById(modelId);
		int j = 4;
		if (modelDataAttributes.size()<4){
			j=modelDataAttributes.size();
		}
		for (int i=0;i<j;i++){
			//删除前4个字段，系统固定字段，不需要展示
			modelDataAttributes.remove(0);
		}
			
		return modelDataAttributes;
	}
	
	@RequestMapping("/catalog/ajax/getModelColMoreModel")
	@ResponseBody
	public List<ModelDataAttribute> dataTypeAjaxGetModelColMoreModel(String modelId){
		if (null == modelId){
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<String> modelIds = JSON.parseObject(modelId, ArrayList.class);
		
		List<ModelDataAttribute> modelDataAttributes = new ArrayList<ModelDataAttribute>();
		List<ModelDataAttribute> modelDataAttributesTemp = null;
		
		for (String id : modelIds) {
			modelDataAttributesTemp = modelDataIntf.queryModelById(id);
			int j = 4;
			if (modelDataAttributesTemp.size()<4){
				j=modelDataAttributesTemp.size();
			}
			for (int i=0;i<j;i++){
				//删除前4个字段，系统固定字段，不需要展示
				modelDataAttributesTemp.remove(0);
			}
		
			for(int k=0;k<modelDataAttributesTemp.size();k++){
				modelDataAttributes.add(modelDataAttributesTemp.get(k));
			}
			
		}
		
		return modelDataAttributes;
	}
	
	
	@RequestMapping("/catalog/ajax/selectDetailModelData")
	@ResponseBody
	public Map<String, Object> catalogAjaxSelectDetailModelData(String modelId){
		List<ModelDataAttribute> modelDataAttributes = modelDataIntf.queryModelById(modelId);
		int j = 4;
		if (modelDataAttributes.size()<4){
			j=modelDataAttributes.size();
		}
		for (int i=0;i<j;i++){
			//删除前4个字段，系统固定字段，不需要展示
			modelDataAttributes.remove(0);
		}
		
		String json = modelDataIntf.getOfficialModelData(modelId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modelDataAttributes", modelDataAttributes);
		map.put("json", json);
		return map;
	}
	
	@RequestMapping("catalog/ajax/commitApplyData")
	@ResponseBody
	public String catalogAjaxCommitApplyData(String modelId,String applyName,String applyOrgName,String applyUser,String applyDirection,int encryptionType, String json, HttpServletRequest request){
		
//		Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
//		if (!"user".equals(ssoUser.get("userType"))) {
//			request.setAttribute("errorMessage", "系统接入不需要访问资源目录");//返给页面错误信息
//			return "用户类型不是一般需求资源用户不需要申请资源";
//		}
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (!"user".equals(authInfo.getUserType())) {
			request.setAttribute("errorMessage", "系统接入不需要访问资源目录");//返给页面错误信息
			return "用户类型不是一般需求资源用户不需要申请资源";
		}
		
		if (StringUtils.isBlank(modelId)) {
			return "模型选择异常";
		} else if (StringUtils.isBlank(applyName)) {
			return "申请资源目录名称不能为空";
		} else if (StringUtils.isBlank(applyOrgName)) {
			return "申请机构名称不能为空";
		} else if (StringUtils.isBlank(applyUser)) {
			return "申请人不能为空";
		} else if (StringUtils.isBlank(applyDirection)) {
			return "申请描述不能为空";
		} 

		String applyId = IDUtil.getUUID();
		
		ApplyManagement applyManagement = new ApplyManagement();
		applyManagement.setApplyId(applyId);
		applyManagement.setModelId(modelId);
		applyManagement.setUserId(authInfo.getUserId());
//		applyManagement.setUserName(String.valueOf(ssoUser.get("uname")));
		applyManagement.setUserName(authInfo.getLoginName());
		applyManagement.setApplyName(applyName);
		applyManagement.setApplyOrgName(applyOrgName);
		applyManagement.setApplyUser(applyUser);
		applyManagement.setApplyDirection(applyDirection);
		applyManagement.setApplyTime(new Date());
		applyManagement.setAuditStatus(1);//审核状态  1:待审核
		applyManagement.setEncryptionType(encryptionType);
		
		List<ModelDataAttribute> modelDataAttributes = JSONArray.parseArray(json, ModelDataAttribute.class);
		if (modelDataAttributes.size()==0) {
			return "申请字段未选择,请重新选择申请字段";
		}
		
		
		List<ApplyAttribute> applyAttributes = new ArrayList<ApplyAttribute>();
		for(int i=0;i<modelDataAttributes.size();i++){
			ApplyAttribute applyAttribute = new ApplyAttribute();
			applyAttribute.setApplyId(applyId);
			applyAttribute.setModelId(modelDataAttributes.get(i).getModelId());
			applyAttribute.setModelColName(modelDataAttributes.get(i).getModelColName());
			applyAttributes.add(applyAttribute);
		}
		
		return applyIntf.saveApply(applyManagement, applyAttributes);
		
	}
	

}
