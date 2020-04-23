package com.wondersgroup.brmp.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.brmp.dao.daoutil.ApplySql;
import com.wondersgroup.brmp.dao.daoutil.DaoUtil;
import com.wondersgroup.brmp.dao.intf.ApplyDaoIntf;
import com.wondersgroup.brmp.po.empipo.ApplyManagement;


@Repository
public class ApplyDaoImpl implements ApplyDaoIntf {

	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ApplyManagement> qureyApplyManagement(Map<String, Object> paramMap) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(ApplySql.queryApply);
		Iterator<String> it=paramMap.keySet().iterator();
		String key;
		if (it.hasNext()) {
			key = it.next();
			sBuffer.append(" where ");
			if("beginDate".equals(key)) {
				sBuffer.append("apply_time >= :").append(key);
			} else if ("endDate".equals(key)) {
				sBuffer.append("apply_time <= :").append(key);
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(key)).append("= :").append(key);
			}
		}
		while(it.hasNext()){
			sBuffer.append(" and ");
			key = it.next().toString();
			if("beginDate".equals(key)) {
				sBuffer.append("apply_time >= :").append(key);
			} else if ("endDate".equals(key)) {
				sBuffer.append("apply_time <= :").append(key);
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(key)).append("= :").append(key);
			}
		}	
		String sql = sBuffer.toString();
		System.out.println(sql);
		List<ApplyManagement> applyManagements = jdbcTemplate.query(sql,paramMap, new BeanPropertyRowMapper<ApplyManagement>(ApplyManagement.class));
		return applyManagements;	
	}

}
