package cn.ittx.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ittx.po.auth.LoginSession;
import cn.ittx.po.auth.LoginSessionURL;
import cn.ittx.service.intf.login.LoginService;
import cn.ittx.util.VerifyABC;

@Controller
public class AdminController {
	
	@Autowired private LoginService loginService;
	
	/*
	 * 到登录页前先判断是否免密登录
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/")
	public String preLogin(HttpServletRequest request,HttpServletResponse response){
		System.out.println("-----即将到登录页面，登录前检查session是否存在，免登-------");
		Cookie[] cookies = request.getCookies();
		a:
		if (cookies != null ) {
			for (int i=0;i<cookies.length;i++) {
				Cookie cookie = cookies[i];
				if ("JSESSIONID".equals(cookie.getName())){
					LoginSession loginSession = loginService.getSession(cookie.getValue());
					if (loginSession == null) {
						break a;
					}
					if (loginSession.getCreateTime().getTime() + 2*60*60*1000 < new Date().getTime()) {
						System.out.println("session超时了");
						System.out.println("session创建的时间 "+loginSession.getCreateTime());
						System.out.println("当前时间： "+new Date());
						break a;//判断session是否超时
					}
					String sessionMSG = loginSession.getsValue();
					//System.out.println("请求的内容： ".concat(sessionMSG));
					String nextURL = request.getParameter("nextURL");
					
					LoginSessionURL loginSessionURL = new LoginSessionURL();//添加登录系统的新系统URL持久化
					loginSessionURL.setLoginSession(loginSession);
					String systemURL = VerifyABC.getSystemURLString(nextURL);
					loginSessionURL.setUrl(systemURL);
					loginSessionURL.setId(UUID.nameUUIDFromBytes(loginSession.getsId().concat(systemURL).getBytes()).toString());
					loginService.addLoginSessionURL(loginSessionURL);
					
					try {
						response.sendRedirect(nextURL.concat("?").concat(sessionMSG));//session存在验证免登录通过
					} catch (IOException e) {
						e.printStackTrace();	
					} finally {
						System.out.println("------免登检查结束,免登进入系统-----");
						return null;
					}
				}
			}
		}
		System.out.println("------免登检查结束,未能免登,进入登录页-----");
		return "login";
	}
	
}
