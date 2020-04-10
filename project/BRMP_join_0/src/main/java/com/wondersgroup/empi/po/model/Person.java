package com.wondersgroup.empi.po.model;

import java.util.Date;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.anotation.Table;

@Table(name="v_emp")
public class Person {
	
	//修改标志
	@ColumnName("")
	private int xgbz;
	//模型中本记录主键
	private String Id;
	//记录关联人(或物)的主键
	private String originId;
	//记录生成日期
	@ColumnName("")
	private Date updateTime;
	//机构代码
	private String orgCode;
	//机构名称
	private String orgName;
	//工号
	private String jobNumber;
	//岗位类别 
	private String postTypeCode;
	//人员性质
	private String empTypeCode;
	//备注
	private String comments;
	//状态（是否在用 ）1 启用，0 停用
	private int active;
	//创建日期
	private Date createDate;
	//姓名
	private String name;
	//性别
	private String genderCode;
	//出生日期
	private Date birthDate;
	//婚姻状况
	private String maritalStCode;
	//身份证号
	private String idNo;
	//家庭地址
	private String liveAddr;
	//参加工作数据
	private Date workDate;
	//毕业学校
	private String graduatedSchool;
	public int getXgbz() {
		return xgbz;
	}
	public void setXgbz(int xgbz) {
		this.xgbz = xgbz;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getPostTypeCode() {
		return postTypeCode;
	}
	public void setPostTypeCode(String postTypeCode) {
		this.postTypeCode = postTypeCode;
	}
	public String getEmpTypeCode() {
		return empTypeCode;
	}
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getMaritalStCode() {
		return maritalStCode;
	}
	public void setMaritalStCode(String maritalStCode) {
		this.maritalStCode = maritalStCode;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getLiveAddr() {
		return liveAddr;
	}
	public void setLiveAddr(String liveAddr) {
		this.liveAddr = liveAddr;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getGraduatedSchool() {
		return graduatedSchool;
	}
	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}

}
