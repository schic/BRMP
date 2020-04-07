package com.wondersgroup.empi.util.ssoutil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
  * 为sso的工具类
 *
 */
public class SsoUtil {
	
	/**
	 * 返回单点登录user信息
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getSsoUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String,Object> ssoUser = (Map<String, Object>) session.getAttribute("ssoUser");
		return ssoUser;
	}

}
