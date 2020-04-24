package com.wondersgroup.base.login.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 机构信息(Tb_Auth_Org)
 * */
@SuppressWarnings("serial")
public class TbAuthOrganization implements java.io.Serializable {

	private String orgid;
	private String orgname;  //机构名称
	private String orgcode; //编码
	private String orgjc;   //简称
	private String orgsm;  //说明
	private TbAuthDicOrgType tbAuthDicOrgType; //机构类型

	private String status; //"0",待审核，"1",审核通过，"2"审核不通过
	private Date checkDate;//审核日期

	//一对多  一个菜单资源对多个角色(TB_AUTH_ROLE_RESOURCE中间表)
	private Set<TbAuthRole> tbAuthRoles = new HashSet<TbAuthRole>(0);
	//提供资源
	private Set<TbAuthResource> tbAuthResources = new HashSet<TbAuthResource>(0);
	//节点管理员
	private Set<TbAuthUser> tbAuthUsers = new HashSet<TbAuthUser>(0);
	
	private TbAuthUser tbAuthUser;//暂时只为一个节点管理员
    
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgjc() {
		return orgjc;
	}

	public void setOrgjc(String orgjc) {
		this.orgjc = orgjc;
	}

	public String getOrgsm() {
		return orgsm;
	}

	public void setOrgsm(String orgsm) {
		this.orgsm = orgsm;
	}
	
	public Set<TbAuthRole> getTbAuthRoles() {
		return tbAuthRoles;
	}

	public void setTbAuthRoles(Set<TbAuthRole> tbAuthRoles) {
		this.tbAuthRoles = tbAuthRoles;
	}

	public Set<TbAuthResource> getTbAuthResources() {
		return tbAuthResources;
	}

	public void setTbAuthResources(Set<TbAuthResource> tbAuthResources) {
		this.tbAuthResources = tbAuthResources;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public TbAuthDicOrgType getTbAuthDicOrgType() {
		return tbAuthDicOrgType;
	}

	public void setTbAuthDicOrgType(TbAuthDicOrgType tbAuthDicOrgType) {
		this.tbAuthDicOrgType = tbAuthDicOrgType;
	}

	public Set<TbAuthUser> getTbAuthUsers() {
		return tbAuthUsers;
	}

	public void setTbAuthUsers(Set<TbAuthUser> tbAuthUsers) {
		this.tbAuthUsers = tbAuthUsers;
	}

	public TbAuthUser getTbAuthUser() {
		if(tbAuthUsers!=null && tbAuthUsers.size()>0){
			return (TbAuthUser)tbAuthUsers.toArray()[0];
		}
		else{
			return null;
		}
	}


}