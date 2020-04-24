package com.wondersgroup.brmp.po.empipo;

import java.util.Date;

/**
 * 默认主索引类
 */
public class EMPIObj {
	
	private String recordid;//记录唯一ID
	private String sfzh;//身份证号
	private String xm;//姓名 或 名称
	private String xb;//性别
	private Date csrq;//出生日期
	private String sjhm;//手机号码
	private String jtdz;//家庭地址
	private String lxr;//联系人
	private String lxrdh;//联系人电话
	private String originSystemId;//源系统编号
	
	private String originId;//源系统主键值
	
	private String objId;// 个人或事或物的唯一主索引号
	private int zqddf;// 准确度得分
	private Date gxrq;//记录更新日期
	
	
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public Date getCsrq() {
		return csrq;
	}
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getLxrdh() {
		return lxrdh;
	}
	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}
	/**
	 * 源系统在本系统的系统号
	 */
	public String getOriginSystemId() {
		return originSystemId;
	}
	public void setOriginSystemId(String originSystemId) {
		this.originSystemId = originSystemId;
	}
	/**
	 * 源系统主键值
	 */
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public int getZqddf() {
		return zqddf;
	}
	public void setZqddf(int zqddf) {
		this.zqddf = zqddf;
	}
	public Date getGxrq() {
		return gxrq;
	}
	public void setGxrq(Date gxrq) {
		this.gxrq = gxrq;
	}
	

}
