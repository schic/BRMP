package cn.ittx.dao.impl.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.ittx.dao.intf.login.LoginDao;
import cn.ittx.po.auth.LoginSession;
import cn.ittx.po.auth.LoginSessionURL;
import cn.ittx.po.auth.PwType;
import cn.ittx.po.auth.User;

@Repository
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {
	
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}

	@Override//用户名验证
	public User queryUserName(String userName) {
		@SuppressWarnings("unchecked")
		ArrayList<User> userList = (ArrayList<User>) this.getHibernateTemplate().find("from User where uname=?0", userName);
		if(userList.size()==0) {
			return null;
		}
		return userList.get(0);
	}

	@Override//pwType 用"[2bd81117-d99f-435b-8dec-95495ae46cf7]"分割开
	public String getPwType(int pwType) {
		int firstPWTypeid = pwType/10;
		int lostPWTYpeid = pwType%10;
		String sql = "from PwType where pw_type_id=?0 ";
		PwType p1 = (PwType) this.getHibernateTemplate().find(sql, firstPWTypeid).get(0);
		PwType p2 = (PwType) this.getHibernateTemplate().find(sql, lostPWTYpeid).get(0);
		String str = p1.getType().concat("[2bd81117-d99f-435b-8dec-95495ae46cf7]").concat(p2.getType());
		return str;
	}
	
	@Override
	public User queryUserById(String id) {
		return this.getHibernateTemplate().get(User.class, id);
	}

	@Override
	public String addLoginSession(LoginSession loginSession) {
		this.getHibernateTemplate().saveOrUpdate(loginSession);
		return "session保存完成";
	}

	@Override//获取持久化里的session
	public LoginSession getSession(String sessionId) {
		@SuppressWarnings("unchecked")
		List<LoginSession>  list = (List<LoginSession>) this.getHibernateTemplate().find("from LoginSession a where s_id=?0 order by create_time desc", sessionId);
		if (list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override//删除session
	public String removeLoginSession(String sessionId) {
		List<LoginSessionURL> URLs = getSessionURLs(sessionId);
		if(URLs != null){
			for(int i=0;URLs.size()>i;i++){
				this.getHibernateTemplate().delete(URLs.get(i));//首先删除URL
			}
		}
		LoginSession loginSession = getSession(sessionId);
		if (loginSession != null){
			this.getHibernateTemplate().delete(loginSession);
			return "session删除完成"; 
		}
		return "session未发现"; 
	}
	
	@Override//获取该session里的URL
	public List<LoginSessionURL> getSessionURLs(String sessionId) {
		@SuppressWarnings("unchecked")
		List<LoginSessionURL> URLs = (List<LoginSessionURL>) this.getHibernateTemplate().find("from LoginSessionURL where s_id=?0", sessionId);
		if (URLs.size()>0){
			return URLs;
		}
		return null;
	}

	@Override
	public String addLoginSessionURL(LoginSessionURL loginSessionURL) {
		this.getHibernateTemplate().saveOrUpdate(loginSessionURL);
		return "session登录了的URL保存完成";
	}

	@Override
	public List<LoginSession> getSessionByBeforeCreateTime(Date date) {
		@SuppressWarnings("unchecked")
		List<LoginSession>  list = (List<LoginSession>) this.getHibernateTemplate().find("from LoginSession a where create_time<?0 order by create_time desc", date);
		return list;
	}





}
