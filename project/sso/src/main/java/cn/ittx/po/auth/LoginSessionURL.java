package cn.ittx.po.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 登录后将登录信息存入数据库，供其它系统使用。
 * 存储其它系统的url
 */
@Entity
@Table(name="t_login_session_url")
public class LoginSessionURL {
	
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="s_id")
	private LoginSession loginSession;
	
	@Column(name="url")
	private String url;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LoginSession getLoginSession() {
		return loginSession;
	}

	public void setLoginSession(LoginSession loginSession) {
		this.loginSession = loginSession;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
