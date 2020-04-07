package com.wondersgroup.empi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.empi.dao.intf.EMPIClientJobDaoIntf;
import com.wondersgroup.empi.po.empipo.EMPIObj;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.daoutil.ParamMapUtil;
import com.wondersgroup.empi.util.sqlutil.EMPIJobSqlUtil;


@Repository
public class EMPIClientJobDaoImpl implements EMPIClientJobDaoIntf {

	@Autowired
	private BaseResource baseResource;
	
	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public String updateSql(String sql,Map<String,Object> paramMap) {
		System.out.println("开始更新update_sql：".concat(sql));
		try {
			jdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "数据保存出错save_error:".concat(e.getMessage());
		} 
		return "数据更新完成update_complates";
	}
	
	@Override
	public String updateBzSql(String sql,String flag) {
		System.out.println("开始更新update_sql：".concat(sql));
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("EMPIClientId", baseResource.getEMPIClientId() );//修改标志位
		if (flag.equals("completes")) {//完成标志位10000+客户端ID
			paramMap.put("EMPIClientIdC", Integer.parseInt(baseResource.getEMPIClientId())+10000 );
		}
		try {
			jdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "数据保存出错save_error:".concat(e.getMessage());
		} 
		return "数据更新完成update_complates";
	}

	@Override
	public <T> List<T> select(String sql,Class<T> clazz,Map<String,Object> paramMap) {
		System.out.println("开始查询select_sql：".concat(sql));
		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<T>(clazz));
	}

	@Override
	public EMPIObj getEMPIObj(String sql, EMPIObj empiObj) {
		Map<String, Object> paramMap;
		EMPIObj empiObj1 = new EMPIObj();
		try {
			paramMap = ParamMapUtil.getParamMap(empiObj);
			paramMap.put("EMPIClientId", baseResource.getEMPIClientId());//如果有需要，添加标志位查询
			System.out.println("开始查询select_sql：".concat(sql));
			empiObj1 = jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<EMPIObj>(EMPIObj.class));
		} catch (Exception e) {
			System.out.println("查询无记录");
			return empiObj1;
		} 
		return empiObj1;
		
	}

	@Override
	public String insertEMPIObj(String sql, EMPIObj empiObj) {
		Map<String, Object> paramMap;
		try {
			paramMap = ParamMapUtil.getParamMap(empiObj);
			System.out.println("开始插入insert_sql：".concat(sql));
			jdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("插入出错insert_error");
			return "插入出错insert_error";
		} 	
		return "插入完成insert_complates";
	}

	@Override
	public String deleteEMPIObjByBz() {
		try {
			jdbcTemplate.update(EMPIJobSqlUtil.delete_by_bz, new HashMap<String, Object>());
		} catch (Exception e) {
			e.printStackTrace();
			return "删除出错delete_erorr";
		}
		return "删除完成delete_complates";
	}
	
	
	
	

}
