package com.wondersgroup.empi.dao.intf;

import java.util.List;
import java.util.Map;

import com.wondersgroup.empi.po.empipo.ApplyManagement;

/**
 * 申请管理dao
 *
 */
public interface ApplyDaoIntf {

	/**
	 * 获取申请的信息列表
	 * @param paramMap 筛选查询条件
	 * @return
	 */
	List<ApplyManagement> qureyApplyManagement(Map<String, Object> paramMap);
	
}
