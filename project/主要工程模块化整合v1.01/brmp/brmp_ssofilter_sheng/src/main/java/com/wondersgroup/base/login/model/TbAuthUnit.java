package com.wondersgroup.base.login.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 单位信息(T_SJBM)
 * */
@SuppressWarnings("serial")
public class TbAuthUnit implements java.io.Serializable {

	private String unitid;
	private String unitname;
	private String unitcode;
	private String jgjm;
	private String jgsm;
	private Integer px;
	private Integer jgbz;
	
	private Long resLevel;
	private String treelayer;
	
	private TbAuthUser tbAuthUser;//节点管理员
	
	// 多对一，多个单位信息对应一个单位等级
	private TbAuthUnitgrade tbAuthUnitgrade;
	// 一对多，一个单位对应多个用户信息
	private Set<TbAuthUser> tbAuthUsers = new HashSet<TbAuthUser>(0);
	
	private TbAuthOrganization tbAuthOrganization;//组织所属机构
	
	private TbAuthUnit tbAuthUnitRoot; //根节点
	//父亲节点
	private TbAuthUnit tbAuthUnit;
	//一对多  一个菜单资源对多个角色(TB_AUTH_ROLE_RESOURCE中间表)
	private Set<TbAuthRole> tbAuthRoles = new HashSet<TbAuthRole>(0);
	//菜单组成，父级菜单 一对多关系，一个菜单资源对应多个菜单资源
	private Set<TbAuthUnit> tbAuthUnits = new HashSet<TbAuthUnit>(0);
	
	//提供资源
	private Set<TbAuthResource> tbAuthResources = new HashSet<TbAuthResource>(0);
	
	@SuppressWarnings("unused")
	private String unitidString;

	
	public TbAuthUnitgrade getTbAuthUnitgrade() {
		return tbAuthUnitgrade;
	}

	public void setTbAuthUnitgrade(TbAuthUnitgrade tbAuthUnitgrade) {
		this.tbAuthUnitgrade = tbAuthUnitgrade;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getJgjm() {
		return jgjm;
	}

	public void setJgjm(String jgjm) {
		this.jgjm = jgjm;
	}

	public String getJgsm() {
		return jgsm;
	}

	public void setJgsm(String jgsm) {
		this.jgsm = jgsm;
	}

	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	public Integer getJgbz() {
		return jgbz;
	}

	public void setJgbz(Integer jgbz) {
		this.jgbz = jgbz;
	}
	
	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
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

	public TbAuthUnit getTbAuthUnit() {
		return tbAuthUnit;
	}

	public void setTbAuthUnit(TbAuthUnit tbAuthUnit) {
		this.tbAuthUnit = tbAuthUnit;
	}

	public Set<TbAuthUser> getTbAuthUsers() {
		return tbAuthUsers;
	}

	public void setTbAuthUsers(Set<TbAuthUser> tbAuthUsers) {
		this.tbAuthUsers = tbAuthUsers;
	}

	public Set<TbAuthRole> getTbAuthRoles() {
		return tbAuthRoles;
	}

	public void setTbAuthRoles(Set<TbAuthRole> tbAuthRoles) {
		this.tbAuthRoles = tbAuthRoles;
	}

	public Set<TbAuthUnit> getTbAuthUnits() {
		return tbAuthUnits;
	}

	public void setTbAuthUnits(Set<TbAuthUnit> tbAuthUnits) {
		this.tbAuthUnits = tbAuthUnits;
	}

	public Set<TbAuthResource> getTbAuthResources() {
		return tbAuthResources;
	}

	public void setTbAuthResources(Set<TbAuthResource> tbAuthResources) {
		this.tbAuthResources = tbAuthResources;
	}

	public TbAuthOrganization getTbAuthOrganization() {
		return tbAuthOrganization;
	}

	public void setTbAuthOrganization(TbAuthOrganization tbAuthOrganization) {
		this.tbAuthOrganization = tbAuthOrganization;
	}

	public TbAuthUnit getTbAuthUnitRoot() {
		return tbAuthUnitRoot;
	}

	public void setTbAuthUnitRoot(TbAuthUnit tbAuthUnitRoot) {
		this.tbAuthUnitRoot = tbAuthUnitRoot;
	}

	public TbAuthUser getTbAuthUser() {
		return tbAuthUser;
	}

	public void setTbAuthUser(TbAuthUser tbAuthUser) {
		this.tbAuthUser = tbAuthUser;
	}

	public String getUnitidString() {
		if(this.unitid!=null){
			return unitid.toString();
		}
		else{
			return "";
		}
		
	}

}