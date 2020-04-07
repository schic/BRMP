package cn.ittx.service.impl.login;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ittx.dao.intf.login.LoginDao;
import cn.ittx.po.auth.LoginSession;
import cn.ittx.po.auth.LoginSessionURL;
import cn.ittx.po.auth.User;
import cn.ittx.service.intf.login.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired private LoginDao loginDao;

	/*
	 * 用户名查询用户
	 */
	@Override
	public User qureyUserName(String userName) {
		return this.loginDao.queryUserName(userName);
	}
	
	@Override//根据用户名得到type
	public String qureyUserNameGetType(String userName){
		User user = loginDao.queryUserName(userName);
		if (user==null){
			return "[2bd81117-d99f-435b-8dec-95495ae46cf7]";
		}
		return loginDao.getPwType(user.getPwType());//从数据库获取type
	}
	
	@Override//保存session到数据库
	public String addLoginSession(LoginSession loginSession) {
		return loginDao.addLoginSession(loginSession);
	}
	
	@Override
	public LoginSession getSession(String sessionId) {
		return loginDao.getSession(sessionId);
	}
	
	@Override
	public String removeLoginSession(String SessionId) {
		return loginDao.removeLoginSession(SessionId);
	}
	
	@Override
	public String addLoginSessionURL(LoginSessionURL loginSessionURL) {
		return loginDao.addLoginSessionURL(loginSessionURL);
	}
	
	@Override
	public List<String> getSessionURLs(String sessionId) {
		List<LoginSessionURL> list = loginDao.getSessionURLs(sessionId);
		List<String> urls = new ArrayList<String>();
		if (list != null && list.size()>0) {
			for (int i=0;i<list.size();i++){
				urls.add(list.get(i).getUrl());
			}
		}
		return urls;
	}
	@Override
	public List<LoginSession> getSessionByBeforeCreateTime(Date date) {
		
		return loginDao.getSessionByBeforeCreateTime(date);
	}
	
	
	

}
