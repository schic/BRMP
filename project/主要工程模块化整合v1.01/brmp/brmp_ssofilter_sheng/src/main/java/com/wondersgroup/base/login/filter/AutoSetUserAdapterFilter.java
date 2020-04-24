package com.wondersgroup.base.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.validation.Assertion;

import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.base.login.web.LoginController;

public class AutoSetUserAdapterFilter implements Filter {
	
	private LoginController loginAction;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		 if (SessionUtil.get((HttpServletRequest)request, "HAD_LOGIN")!=null){
		    	chain.doFilter(request, response); 
		    	return;
		 }
	 
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
		// _const_cas_assertion_是CAS中存放登录用户名的session标志                
		Object object = httpRequest.getSession().getAttribute("_const_cas_assertion_"); 
		if (object != null) {  
			String mykeyword=httpRequest.getParameter("keyword");
			String ifAuth=httpRequest.getParameter("ifAuth");
			 System.out.println("AutoSetUserAdapterFilter :@@@@@@@@@@@@ "+mykeyword);
			 Assertion assertion = (Assertion) object;                         
			 String loginName = assertion.getPrincipal().getName();   
			 //loginName="admin",在内网登陆口输入的用户名
			 //通过登录名取得映射中间表对应的人员姓名和ID
//			 Map<String, Object> userMap = commonService.getNameAndIdByLoginName(loginName);
			  SessionUtil.put((HttpServletRequest)request, "LOGIN_NAME",loginName);
			  SessionUtil.put((HttpServletRequest)request, "KEYWORD",mykeyword);
			  SessionUtil.put((HttpServletRequest)request, "IFAUTH",ifAuth);
			  SessionUtil.put((HttpServletRequest)request, "HAD_LOGIN","1");
			// System.out.println("******************OK!!!");
			// System.out.println(loginName);
			 //第一次登陆系统
			 // loginAction.setRequest(httpRequest);
		}
		//System.out.println("******************  goto   OK!!!");
		chain.doFilter(request, response); 
		

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		String ssowsUrl=filterConfig.getInitParameter("ssowsUrl");
		System.out.println("ssowsUrl is :::::::::::::::::"+ ssowsUrl);
		SessionUtil.setSsowsUrl(ssowsUrl);
	}

	public LoginController getLoginAction() {
		return loginAction;
	}

	public void setLoginAction(LoginController loginAction) {
		this.loginAction = loginAction;
	}

}
