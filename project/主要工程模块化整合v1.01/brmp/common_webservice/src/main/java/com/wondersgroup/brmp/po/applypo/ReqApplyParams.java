package com.wondersgroup.brmp.po.applypo;

public class ReqApplyParams {
	int pageSize = 10;//页面大小
	int pageNo = 1;//第几页
	
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

}
