package cn.ittx.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.ittx.po.auth.LoginSession;
import cn.ittx.po.auth.LoginSessionURL;
import cn.ittx.po.auth.User;
import cn.ittx.service.intf.login.LoginService;
import cn.ittx.util.SessionJSON;
import cn.ittx.util.VerifyABC;
import cn.ittx.util.cipher.AES;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired private LoginService loginService;

	/*
	 * 验证用户登录
	 */
	@RequestMapping("/verify")
	public String verify(HttpServletRequest request,HttpServletResponse response,String userName,String passWord,String nextURL){
		User user = loginService.qureyUserName(StringUtils.strip(userName));
		if(user == null || !user.getuPid().equals(passWord)){
			request.setAttribute("errorMessage", "用户名或者密码无效");//返回错误信息
			return "login";
		}
		HttpSession session = request.getSession();
		session.setAttribute("ssoUser", user);
		String sessionId = session.getId();//服务器会自动存放一个sessionID在浏览器
		
		String sessionJSON = SessionJSON.getSessionJSON(session);//把需要的session封装成JSON
		String sessionMSG = AES.enCrypt(sessionJSON, AES.getKey1());
		
		LoginSession loginSession = new LoginSession();//把需要的session内容持久化保存，方便后续调取
		loginSession.setsId(sessionId);
		loginSession.setsValue(sessionMSG);
		loginSession.setCreateTime(new Date());
		loginService.addLoginSession(loginSession);
		
		LoginSessionURL loginSessionURL = new LoginSessionURL();//添加登录系统了的URL持久化
		loginSessionURL.setLoginSession(loginSession);
		String systemURL = VerifyABC.getSystemURLString(nextURL);
		loginSessionURL.setUrl(systemURL);
		loginSessionURL.setId(UUID.nameUUIDFromBytes(sessionId.concat(systemURL).getBytes()).toString());
		loginService.addLoginSessionURL(loginSessionURL);
		
		return "redirect:".concat(nextURL).concat("?").concat(sessionMSG);//跳转到需要访问的页，并附上加密用户信息。
	}
	
	@RequestMapping("/logout")//单点登录注销前置工作
	public String preLogout(HttpServletRequest request,HttpServletResponse response){
		String sessionId = request.getRequestedSessionId();
		String urls = JSON.toJSONString(loginService.getSessionURLs(sessionId));
		request.setAttribute("URLS", urls);
		return "logout";
	}
	
	@RequestMapping("/after_logout")//到各个系统注销后，最后注销单点登录
	public String afterLogout(HttpServletRequest request,HttpServletResponse response){
		String nextURL = request.getParameter("nextURL");
		loginService.removeLoginSession(request.getRequestedSessionId());//清除数据库存放的session
		request.getSession().invalidate();
		return "redirect:".concat(nextURL);
	}
	
	@RequestMapping("/ajax/get_pass_type")
	@ResponseBody
	public Map<String,Object> getPassType(String name){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result",loginService.qureyUserNameGetType(name));
		return result;
	}
	

}
