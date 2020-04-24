package com.wondersgroup.base.login.dao;

import java.util.List;
import java.util.Map;

import com.wondersgroup.core.hibernate.PageRequest;

public interface MenuDao {

	List<Map<String, String>> getResources(String loginName, String organId, String projectName);
	// 获取系统发布版本
	public String getVersionByResid(String resid);
	//分页查询系统历史版本信息
	public Map<String,Object> queryLogList(String resid,PageRequest pageRequest);
}
