package com.wondersgroup.brmp.po.empipo;

import java.util.Date;

import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 申请用户类，某项申请的信息
 * 
 * 表名brmp_apply_base
 */
@Table(name="brmp_apply_base")
public class ApplyManagement {
	
	private String applyId;//申请ID
	private String modelId;//源系统建立的模型编号(申请主资源编号 一个主要资源)
	private String userName;//申请用户用户名
	
	private String applyName;//申请资源目录名称（申请名称）
	private String applyOrgName;//申请机构名称
	private String applyUser;//申请人姓名
	private String applyDirection;//申请用途描述说明
	
	private Date applyTime;//申请时间
	private int auditStatus;//审核状态  1:待审核 2:审核拒绝 9:审核通过
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getApplyOrgName() {
		return applyOrgName;
	}
	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getApplyDirection() {
		return applyDirection;
	}
	public void setApplyDirection(String applyDirection) {
		this.applyDirection = applyDirection;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	

}
