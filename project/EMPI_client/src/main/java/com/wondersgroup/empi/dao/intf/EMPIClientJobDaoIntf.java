package com.wondersgroup.empi.dao.intf;

import java.util.List;
import java.util.Map;

import com.wondersgroup.empi.po.empipo.EMPIObj;

public interface EMPIClientJobDaoIntf {

	/**
	 * 更新语句执行
	 */
	String updateSql(String sql,Map<String,Object> paramMap);

	/**
	 * 更新语句执行更新标志位，用客户端id作为标志印记
	 */
	String updateBzSql(String sql,String flag);

	/**
	 * 获取需要查询的类的list
	 */
	<T> List<T> select(String sql ,Class<T> clazz, Map<String,Object> paramMap);
	
	
	/**
	 * 根据临时表的值，
	 * 查询是否存在该记录在主索引库
	 * 
	 */
	EMPIObj getEMPIObj(String sql,EMPIObj empiObj);

	/**
	 * 插入数据进入主索引表
	 * 
	 */
	String insertEMPIObj(String sql, EMPIObj empiObj);
	
	
	/**
	 * 删除标志位为完成了的数据
	 */
	String deleteEMPIObjByBz();
	
	
}
