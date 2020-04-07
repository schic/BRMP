package cn.ittx.po.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_auth")
public class Auth {
	
	@Id
	@Column(name="auth_id")
	private String authId;
	
	@Column(name="p_aid")
	private String pAid;
	
	@Column(name="auth_name")
	private String authName;
	
	private String description;

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getpAid() {
		return pAid;
	}

	public void setpAid(String pAid) {
		this.pAid = pAid;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
