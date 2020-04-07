package cn.ittx.po.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_auth_role")
public class TAuthRole {
		
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(name="auth_id")
	private Auth auth;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
