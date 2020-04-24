package com.wondersgroup.base.login.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 岗位
 */
@SuppressWarnings("serial")
public class TbAuthDepartment implements java.io.Serializable {

	private String depid;
	private String depbm;
	private String depmc;
	
	//一对多，一个岗位对应多个用户
	private Set<TbAuthUser> users = new HashSet<TbAuthUser>(0);

	public TbAuthDepartment() {
		super();
	}

	public TbAuthDepartment(String depid, String depbm, String depmc,
			Set<TbAuthUser> users) {
		super();
		this.depid = depid;
		this.depbm = depbm;
		this.depmc = depmc;
		this.users = users;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	public String getDepbm() {
		return depbm;
	}

	public void setDepbm(String depbm) {
		this.depbm = depbm;
	}

	public String getDepmc() {
		return depmc;
	}

	public void setDepmc(String depmc) {
		this.depmc = depmc;
	}

	public Set<TbAuthUser> getUsers() {
		return users;
	}

	public void setUsers(Set<TbAuthUser> users) {
		this.users = users;
	}
}