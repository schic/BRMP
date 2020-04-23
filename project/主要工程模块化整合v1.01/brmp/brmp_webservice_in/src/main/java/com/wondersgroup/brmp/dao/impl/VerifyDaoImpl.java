package com.wondersgroup.brmp.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.brmp.dao.daoutil.VerifyOriginSystemSql;
import com.wondersgroup.brmp.dao.intf.VerifyDaoIntf;
import com.wondersgroup.brmp.po.webservicepo.VerifiedPo;


@Repository
public class VerifyDaoImpl implements VerifyDaoIntf {
	
	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public VerifiedPo verifyOriginSystem(String username,String password) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		VerifiedPo verifiedPo = new VerifiedPo();
		try {
			verifiedPo = jdbcTemplate.queryForObject(VerifyOriginSystemSql.verify_system, paramMap , new BeanPropertyRowMapper<VerifiedPo>(VerifiedPo.class) );
		} catch (Exception e) {
			verifiedPo.setMsg("用户名或密码错误或paramType错误");
		}
		return verifiedPo;
	}

	@Override
	public VerifiedPo verifyApplyUser(String username, String password) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		VerifiedPo verifiedPo = new VerifiedPo();
		try {
			verifiedPo = jdbcTemplate.queryForObject(VerifyOriginSystemSql.verify_apply_user, paramMap , new BeanPropertyRowMapper<VerifiedPo>(VerifiedPo.class) );
		} catch (Exception e) {
			verifiedPo.setMsg("用户名或密码错误或paramType错误");
		}
		return verifiedPo;
	}

}
