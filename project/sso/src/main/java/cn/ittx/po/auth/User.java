package cn.ittx.po.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="t_user")
public class User {
	
	@Id
	@Column(name="uid")
	private String uid;
	
	/*
	@OneToMany(mappedBy="User")
	@JoinTable(name="t_user_role",joinColumns={@JoinColumn(name="uid")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<TUserRole> tUserRole = new HashSet<TUserRole>();
	*/
	
	@Column(name="role_id")
	private String roleId;
	
	@Column(name="u_pid")
	private String uPid;
	
	@Column(name="pw_type")
	private int pwType;
	
	private String uname;
	
	@Column(name="create_date")
	private Date createDate;
		
	private char status;
	
	@Column(name="lost_login_time")
	private Date lostLoginTime;
	
	private String email;
	
	@Column(name="user_type")
	private String userType;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	/*
	public Set<TUserRole> gettUserRole() {
		return tUserRole;
	}

	public void settUserRole(Set<TUserRole> tUserRole) {
		this.tUserRole = tUserRole;
	}
	*/

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getuPid() {
		return uPid;
	}

	public void setuPid(String uPid) {
		this.uPid = uPid;
	}

	public int getPwType() {
		return pwType;
	}

	public void setPwType(int pwType) {
		this.pwType = pwType;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
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
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
