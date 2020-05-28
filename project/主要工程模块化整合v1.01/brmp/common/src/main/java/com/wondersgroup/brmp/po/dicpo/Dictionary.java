package com.wondersgroup.brmp.po.dicpo;

import java.util.Date;

import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 值域字典
 * 
 * 表名：brmp_dictionary
 */
@Table(name="brmp_dictionary")
public class Dictionary {
	
	private String dicId;//字典ID
	private String originSystemId;//源系统名称编号
	private String dicCode;//字典编码名称
	private String dicName;//字典中文名称
	private int status;//状态  0:停用 1:启用
	private int auditStatus;//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
	private Date dicUpdeteTime;//字典更新时间
	
	public String getDicId() {
		return dicId;
	}
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	public String getOriginSystemId() {
		return originSystemId;
	}
	public void setOriginSystemId(String originSystemId) {
		this.originSystemId = originSystemId;
	}
	public String getDicCode() {
		return dicCode;
	}
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Date getDicUpdeteTime() {
		return dicUpdeteTime;
	}
	public void setDicUpdeteTime(Date dicUpdeteTime) {
		this.dicUpdeteTime = dicUpdeteTime;
	}
	
	

}
