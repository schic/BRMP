package cn.ittx.service.intf.login;


import java.util.Date;
import java.util.List;

import cn.ittx.po.auth.LoginSession;
import cn.ittx.po.auth.LoginSessionURL;
import cn.ittx.po.auth.User;

public interface LoginService {

	User qureyUserName(String userName);

	String qureyUserNameGetType(String userName);

	String addLoginSession(LoginSession loginSession);

	LoginSession getSession(String sessionId);

	String removeLoginSession(String requestedSessionId);

	String addLoginSessionURL(LoginSessionURL loginSessionURL);

	List<String> getSessionURLs(String sessionId);

	List<LoginSession> getSessionByBeforeCreateTime(Date date);
		

}
