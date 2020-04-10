package com.wondersgroup.empi.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.empi.dao.intf.Dao4PersonIntf;
import com.wondersgroup.empi.po.EMPIObj;

@Repository 
public class Dao4PersonImpl implements Dao4PersonIntf {
	
	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<EMPIObj> selectPerson() throws IllegalArgumentException, IllegalAccessException, ParseException {
		String sql = "select sfzh,xm,xb,csrq,sjhm,jtdz,lxr,lxrdh,origin_id from test_system1_grxx";
		System.out.println(sql);
		Map<String,Object> paramMap = null;
        if ("".equals(sql)) {
        	return null;
        }
        BeanPropertyRowMapper<EMPIObj> rowMapper = new BeanPropertyRowMapper<EMPIObj>(EMPIObj.class);
        return jdbcTemplate.query(sql, paramMap, rowMapper);
	}

}
