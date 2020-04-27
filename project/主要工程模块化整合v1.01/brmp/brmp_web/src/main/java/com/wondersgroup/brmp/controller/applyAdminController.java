package com.wondersgroup.brmp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.service.intf.ApplyIntf;
import com.wondersgroup.brmp.service.intf.ModelDataIntf;


@Controller
@RequestMapping("/apply_management")
public class applyAdminController {
	
	@Autowired ModelDataIntf modelDataIntf;
	
	@Autowired AuthService authService;
	
	@Autowired ApplyIntf applyIntf;
	
	@RequestMapping("/apply")
	public String apply(HttpServletRequest request,HttpServletResponse response) {
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
		HttpSession httpSession = request.getSession();
		if ("system".equals(authInfo.getUserType())){
			return "redirect:/apply_management/apply4system";
		} else if ("user".equals(authInfo.getUserType())) {
			List<ModelData> modelDatas = null;
			if (null == httpSession.getAttribute("modelDatas")) {
				modelDatas = modelDataIntf.queryModelData("1", "", "", "1", "9");
				httpSession.setAttribute("modelDatas", modelDatas);
			}
			return "apply_management/apply4user";
		} else if ("admin".equals(authInfo.getUserType())) {
			List<ModelData> modelDatas = null;
			if (null == httpSession.getAttribute("modelDatas")) {
				modelDatas = modelDataIntf.queryModelData("1", "", "", "1", "9");
				httpSession.setAttribute("modelDatas", modelDatas);
			}
			return "apply_management/apply";
		} else {
			request.setAttribute("errorMessage", "未知用户不能访问申请管理");//返回错误信息
			return "index1";
		}
	}
	
	/***********************************************************************************
	 * 申请管理,为admin管理员提供页面的controller
	 * 
	 ***********************************************************************************/
	
	@RequestMapping("/apply/ajax/getInitData")
	@ResponseBody
	public Map<String, Object> applyAjaxGetInitData(HttpServletRequest request){
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
	
	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param status
	 * @param auditStatus
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/apply/ajax/querySelect")
	@ResponseBody
	public List<ApplyManagement> applyAjaxQuerySelect(String beginDate,String endDate,String auditStatus,HttpServletRequest request,HttpServletResponse response){
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
		if (!"admin".equals(authInfo.getUserType()) ) {
			request.setAttribute("errorMessage", "用户不是管理员不能使用申请管理");
			return null;
		}
		
		List<ApplyManagement> applyManagements = applyIntf.selectApply(beginDate, endDate, auditStatus);
		
		return applyManagements;
	}
	
	@RequestMapping("/apply/ajax/getModelData")
	@ResponseBody
	public String applyAjaxGetModelData(String modelId,HttpServletRequest request){
		String modelName = "";
		@SuppressWarnings("unchecked")
		List<ModelData> modelDatas = (List<ModelData>) request.getSession().getAttribute("modelDatas");
		for (int i=0;i<modelDatas.size();i++) {
			if(modelId == modelDatas.get(i).getModelId()){
				modelName = modelDatas.get(i).getModelName();
			}
		}
		return modelName;
	}
	
	
	@RequestMapping("/apply/ajax/getApplyCol")
	@ResponseBody
	public List<ModelDataAttribute> applyAjaxGetApplyCol(String applyId){
		List<ModelDataAttribute> modelDataAttributes = applyIntf.queryApplyById4ModelDataAttribute(applyId);
		
		return modelDataAttributes;
	}
	
	@RequestMapping("/apply/ajax/auditPass")
	@ResponseBody
	public String applyAjaxAuditPass(String applyId,HttpServletRequest request){
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
		return applyIntf.setAudit(applyId,9);
	}
	
	@RequestMapping("/apply/ajax/auditReject")
	@ResponseBody
	public String applyAjaxAuditReject(String applyId,HttpServletRequest request){
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
		return applyIntf.setAudit(applyId,2);
	}
	
	/***********************************************************************************
	 * 申请管理,为用户类型为user(一般用户)提供页面的controller
	 * 
	 ***********************************************************************************/
	
	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param status
	 * @param auditStatus
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/apply/ajax/querySelectUser")
	@ResponseBody
	public List<ApplyManagement> applyAjaxQuerySelectUser(String beginDate,String endDate,String auditStatus,HttpServletRequest request,HttpServletResponse response){
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
		if (!"user".equals(authInfo.getUserType()) ) {
			request.setAttribute("errorMessage", "用户不是一般用户不用查看申请管理");
			return null;
		}
		
		String uname = authInfo.getLoginName();
		List<ApplyManagement> applyManagements = applyIntf.selectApplyUser(beginDate, endDate, auditStatus, uname);
		
		return applyManagements;
	}
	
	@RequestMapping("/apply/ajax/getTestParams")
	@ResponseBody
	public String applyAjaxGetTestParams(HttpServletRequest request){
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
		if (!"user".equals(authInfo.getUserType()) ) {
			request.setAttribute("errorMessage", "用户不是一般用户不用查看申请管理");
			return null;
		}
		String uname = authInfo.getLoginName();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("批量获取数据传参样例，\n");
		sBuffer.append("PageNo为选定页数，PageSize为选定每页的记录数。\n");
		sBuffer.append("数据申请者可根据成功后返回的总记录数算得对应的需要PageNo和PageSize\n\n");
		
		sBuffer.append("{\n");
		sBuffer.append("\"modelType\":\"batch\",\n");
		sBuffer.append("\"paramType\":\"apply\",\n");
		sBuffer.append("\"params\":\"{\\\"PageNo\\\":1,\\\"PageSize\\\":1}\",\n");
		sBuffer.append("\"password\":\"【本接口对应的passkey】\",\n");
		sBuffer.append("\"username\":\"").append(uname).append("\",\n");
		sBuffer.append("}\n");
		return sBuffer.toString();
	}
	
	
	/***********************************************************************************
	 * 申请管理,为用户类型为system(系统接入)提供页面的controller
	 * 
	 ***********************************************************************************/
	
	/**
	 * 申请管理,为用户类型为system(系统接入)提供页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/apply4system")
	public String apply4system(HttpServletRequest request,HttpServletResponse response){
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
		if  (!"system".equals(authInfo.getUserType())){
			request.setAttribute("errorMessage", "用户类型不能访问");
			return "index1";
		}
		if (null == request.getSession().getAttribute("originSystemInfo") ) {
			OriginSystemInfo originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemId(authInfo.getUserId());
			request.getSession().setAttribute("originSystemInfo", originSystemInfo);
		}
		return "apply_management/apply4system";
	}
	
	@RequestMapping("/apply4system_form")
	public String apply4systemForm(String sysName,String sysPassword,String systemUrl,HttpServletRequest request,HttpServletResponse response){
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
		if ("null".equals(sysName)) {
			request.setAttribute("errorMessage", "系统中文名出错");
		} else if ("null".equals(sysPassword)) {
			request.setAttribute("errorMessage", "接口密码出错");
		} else {
			OriginSystemInfo originSystemInfo;
			if (null == request.getSession().getAttribute("originSystemInfo") ) {
				//通过单点登录名获取系统
				originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemId(authInfo.getUserId());
				if (null == originSystemInfo) {
					originSystemInfo = new OriginSystemInfo();
					originSystemInfo.setOriginSystemId(authInfo.getUserId());
					originSystemInfo.setOriginSystemName(authInfo.getLoginName());
					originSystemInfo.setOriginSystemCname(sysName);
					originSystemInfo.setUsername(authInfo.getLoginName());
					originSystemInfo.setPassword(sysPassword);
					originSystemInfo.setOriginSystemUrl(systemUrl);
				}
				request.getSession().setAttribute("originSystemInfo", originSystemInfo);
			} else {
				originSystemInfo = (OriginSystemInfo) request.getSession().getAttribute("originSystemInfo");
				originSystemInfo.setOriginSystemCname(sysName);
				originSystemInfo.setPassword(sysPassword);
				originSystemInfo.setOriginSystemUrl(systemUrl);
			}
			modelDataIntf.saveOriginSystem(originSystemInfo);
			request.setAttribute("infoMessage", "系统接入用户信息更新完成".concat(new Date().toString()));
		}
		return "apply_management/apply4system";
	}
	
	/**
	 * system完善信息验证
	 */
	/*
	 * ajax验证系统名是否一致
	 * 
	@RequestMapping("/verify_sysname")
	public void verifyUserName(HttpServletRequest request,HttpServletResponse response){
		String sysName = StringUtils.strip(request.getParameter("sysName"));
		OriginSystemInfo originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemCName(sysName);
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			if(null != originSystemInfo || StringUtils.isEmpty(sysName)){
				printWriter.println("<font color='red'>×不可用</font>");
			}else{
				printWriter.println("<font color='green'>✔可用</font>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			printWriter.close();
		}
	}
	 */

}
