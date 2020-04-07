package cn.ittx.po.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 登录后将登录信息存入数据库，供其它系统使用。
 *
 */
@Entity
@Table(name="t_login_session")
public class LoginSession {
	
	@Id
	@Column(name="s_id")
	private String sId;
	
	@Column(name="s_value")
	private String sValue;
	
	@Column(name="create_time")
	private Date createTime;


	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsValue() {
		return sValue;
	}

	public void setsValue(String sValue) {
		this.sValue = sValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
