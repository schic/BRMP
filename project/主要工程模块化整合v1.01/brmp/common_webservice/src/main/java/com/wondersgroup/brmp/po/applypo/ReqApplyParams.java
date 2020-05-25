package com.wondersgroup.brmp.po.applypo;

import java.util.Date;
import java.util.Map;

public class ReqApplyParams {
	int pageSize = 10;//页面大小
	int pageNo = 1;//第几页
	Date bigenDate;//记录更新时间JLGXSJ 开始日期
	Date endDate;//记录更新时间JLGXSJ 结束日期
	Map<String,Object> paramMap;//condition
	
	/**
	 * 分页页面大小
	 */
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 第几页
	 */
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Date getBigenDate() {
		return bigenDate;
	}
	public void setBigenDate(Date bigenDate) {
		this.bigenDate = bigenDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	

}
