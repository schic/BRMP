package cn.ittx.po.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_activate_mail")
public class ActivateMail {
	
	@Id
	@Column(name="uid")
	private String uid;
	
	private String uname;
	
	@Column(name="lost_login_time")
	private Date lostLoginTime;
	
	private String email;
	
	@Column(name="activate_code")
	private String activateCode;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Date getLostLoginTime() {
		return lostLoginTime;
	}

	public void setLostLoginTime(Date lostLoginTime) {
		this.lostLoginTime = lostLoginTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivateCode() {
		return activateCode;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}
	
	

}
