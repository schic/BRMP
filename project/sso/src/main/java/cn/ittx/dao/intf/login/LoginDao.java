package cn.ittx.dao.intf.login;


import java.util.Date;
import java.util.List;

import cn.ittx.po.auth.LoginSession;
import cn.ittx.po.auth.LoginSessionURL;
import cn.ittx.po.auth.User;

public interface LoginDao {

	User queryUserName(String userName);

	String getPwType(int pwType);

	User queryUserById(String id);

	String addLoginSession(LoginSession loginSession);

	LoginSession getSession(String sessionId);

	String removeLoginSession(String requestedSessionId);


	List<LoginSessionURL> getSessionURLs(String sessionId);


	String addLoginSessionURL(LoginSessionURL loginSessionURL);

	List<LoginSession> getSessionByBeforeCreateTime(Date date);


}
