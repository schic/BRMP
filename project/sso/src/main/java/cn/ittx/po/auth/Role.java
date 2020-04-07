package cn.ittx.po.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="t_role")
public class Role {
	
	@Id
	@Column(name="role_id")
	private String roleId;

	@Column(name="role_pid")
	private String rolePid;
	
	@Column(name="role_name")
	private String roleDame;
	
	@Column(name="create_date")
	private Date createDate;
	
	private char status;
	
	private int level;
	
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRolePid() {
		return rolePid;
	}

	public void setRolePid(String rolePid) {
		this.rolePid = rolePid;
	}

	public String getRoleDame() {
		return roleDame;
	}

	public void setRoleDame(String roleDame) {
		this.roleDame = roleDame;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	

}
