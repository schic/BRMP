package com.wondersgroup.base.login.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 菜单资源(TB_AUTH_RESOURCE)
 * */
@SuppressWarnings("serial")
public class TbAuthResource implements java.io.Serializable {
	
	private String resid;
	private String resname;
	private String keyword;
	private String url;
	private String comments;
	private Long resLevel;
	private String treelayer;
	
	//多对一 ,多个菜单资源对应一个菜单资源
	private TbAuthResource tbAuthResource;
	//一对多  一个菜单资源对多个角色(TB_AUTH_ROLE_RESOURCE中间表)
	@SuppressWarnings("rawtypes")
	private Set tbAuthRoles = new HashSet(0);
	//菜单组成，父级菜单 一对多关系，一个菜单资源对应多个菜单资源
	@SuppressWarnings("rawtypes")
	private Set tbAuthResources = new HashSet(0);
	
	public TbAuthResource() {
		super();
	}

	public TbAuthResource(String resid, TbAuthResource tbAuthResource,
			String resname, String keyword, String url, String comments,
			Long resLevel, String treelayer, @SuppressWarnings("rawtypes") Set tbAuthRoles,
			@SuppressWarnings("rawtypes") Set tbAuthResources) {
		super();
		this.resid = resid;
		this.tbAuthResource = tbAuthResource;
		this.resname = resname;
		this.keyword = keyword;
		this.url = url;
		this.comments = comments;
		this.resLevel = resLevel;
		this.treelayer = treelayer;
		this.tbAuthRoles = tbAuthRoles;
		this.tbAuthResources = tbAuthResources;
	}

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}

	public TbAuthResource getTbAuthResource() {
		return tbAuthResource;
	}

	public void setTbAuthResource(TbAuthResource tbAuthResource) {
		this.tbAuthResource = tbAuthResource;
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getResLevel() {
		return resLevel;
	}

	public void setResLevel(Long resLevel) {
		this.resLevel = resLevel;
	}

	public String getTreelayer() {
		return treelayer;
	}

	public void setTreelayer(String treelayer) {
		this.treelayer = treelayer;
	}

	@SuppressWarnings("rawtypes")
	public Set getTbAuthRoles() {
		return tbAuthRoles;
	}

	public void setTbAuthRoles(@SuppressWarnings("rawtypes") Set tbAuthRoles) {
		this.tbAuthRoles = tbAuthRoles;
	}

	@SuppressWarnings("rawtypes")
	public Set getTbAuthResources() {
		return tbAuthResources;
	}

	public void setTbAuthResources(@SuppressWarnings("rawtypes") Set tbAuthResources) {
		this.tbAuthResources = tbAuthResources;
	}
}