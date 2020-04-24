package com.wondersgroup.brmp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.brmp.ssofilter.SsoFilter;

@WebServlet("/ssoLogout")
public class servlet4logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servlet4logout() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("ssoUser", null);//清除session内容注销
		request.getSession().invalidate();
		String url = "http://".concat(SsoFilter.getSsoUrl()).concat("/sso/login/logout?nextURL=").concat(SsoFilter.getLocalUrl());
		response.sendRedirect(url);//将注销传给单点登录完成单点登录全部注销
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
