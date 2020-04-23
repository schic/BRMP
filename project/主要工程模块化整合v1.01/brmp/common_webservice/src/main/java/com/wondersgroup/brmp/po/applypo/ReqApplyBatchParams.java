package com.wondersgroup.brmp.po.applypo;

/**
 * 获取申请的批量数据的参数
 */
public class ReqApplyBatchParams {
	private int pageSize = 10;//页面大小
	private int pageNo = 1;//第几页
	
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
