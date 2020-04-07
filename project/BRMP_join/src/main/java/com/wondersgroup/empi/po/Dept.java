package com.wondersgroup.empi.po;

import java.util.Date;

public class Dept {
	
	
	private String id;
	//机构ID
	private String orgId;
	//上一级部门 
	private String parentId;
	//部门编码
	private String deptCode;
	//部门名称 
	private String deptName;
	//所在地址 
	private String detailAddress;
	//诊疗科目代码
	private String medicalSubjectsCode;
	//诊疗科目名称
	private String medicalSubjectsName;
	//是否在用（状态） 0 停用 1 启用
	private String active;
	//创建日期
	private Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getMedicalSubjectsCode() {
		return medicalSubjectsCode;
	}
	public void setMedicalSubjectsCode(String medicalSubjectsCode) {
		this.medicalSubjectsCode = medicalSubjectsCode;
	}
	public String getMedicalSubjectsName() {
		return medicalSubjectsName;
	}
	public void setMedicalSubjectsName(String medicalSubjectsName) {
		this.medicalSubjectsName = medicalSubjectsName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
