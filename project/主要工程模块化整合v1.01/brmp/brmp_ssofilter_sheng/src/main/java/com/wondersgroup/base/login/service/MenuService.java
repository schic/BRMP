package com.wondersgroup.base.login.service;

import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.core.hibernate.PageRequest;


@WebService(targetNamespace = "http://auth.wondersgroup.com/", serviceName = "menuWebService")
public interface MenuService {

	public AuthResource getResources(String loginName, String organId, String projectName, String string);

	public Object getFunctionMenus(HttpServletRequest request, String url);

	public AuthResource removeSomeResources(AuthResource resources);
	
	// 获取系统发布版本
	public String getVersionByResid(String resid);
	//分页查询系统历史版本信息
	public Map<String,Object> queryLogList(String resid,PageRequest pageRequest);
}
