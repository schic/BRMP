package com.wondersgroup.compute.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.compute.dao.intf.DataComputeDaoIntf;

@Repository
public class DataComputeDaoImpl implements DataComputeDaoIntf {
	
	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	

}
