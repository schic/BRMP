package com.wondersgroup.empi.dao.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.empi.dao.intf.DaoJoinIntf;
import com.wondersgroup.empi.po.Dept;
import com.wondersgroup.empi.po.Org;
import com.wondersgroup.empi.po.Person;

@Repository 
public class DaoPersonJoinImpl implements DaoJoinIntf{
	
	@Resource(name = "jdbcTemplate_join")
	private NamedParameterJdbcTemplate jdbcTemplate_join;
	
	@Override
	public long selectPersonCount() throws IllegalArgumentException,
			IllegalAccessException, ParseException {
		
		String sql ="select count(*) from rl_jbxxb";
		
		Long l = jdbcTemplate_join.queryForObject(sql,new HashMap<String, Object>(),Long.class);		
		
		return l;
	}
	

	@Override
	public List<Person> selectJoinPerson(long s,long e) throws IllegalArgumentException, IllegalAccessException, ParseException {
		String sql = "SELECT * FROM  ( " + 
				"SELECT A.*, ROWNUM RN  \r\n" + 
				" FROM (select 0 xgbz,t1.id id,t2.id originId,t3.org_code orgCode,t3.org_name orgName,t1.job_number jobNumber,t1.post_type_code postTypeCode,t1.emp_type_code empTypeCode,t1.comments,t1.active,t1.create_date createDate,\r\n" + 
				" t2.name,t2.gender_code genderCode,t2.birth_date birthDate,t2.marital_st_code maritalStCode,t2.id_no idNo,t2.live_addr liveAddr,t2.work_date workDate,t2.graduated_school graduatedSchool \r\n" + 
				" from cen_reg.t_employee t1 left join cen_reg.t_reg_person t2 on t1.person_id = t2.id \r\n" + 
				" left join cen_reg.t_organization t3 on t1.org_id=t3.id " +
				" ) A   \r\n" + 
				"WHERE ROWNUM <= :e ) " + 
				"WHERE RN >= :s ";
				
		System.out.println(sql);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("s", s);
		paramMap.put("e", e);
        
        BeanPropertyRowMapper<Person> rowMapper = new BeanPropertyRowMapper<Person>(Person.class);      
                
        return jdbcTemplate_join.query(sql, paramMap, rowMapper);
	}

	@Override
	public long selectJoinPersonCount() throws IllegalArgumentException,
			IllegalAccessException, ParseException {
		
		String sql ="select count(*) from cen_reg.t_employee t1 left join cen_reg.t_reg_person t2 on t1.person_id = t2.id \r\n" + 
				" left join cen_reg.t_organization t3 on t1.org_id=t3.id ";
		
		Long l = jdbcTemplate_join.queryForObject(sql,new HashMap<String, Object>(),Long.class);		
		
		return l;
	}

	@Override
	public List<Org> selectJoinOrg(long s, long e)
			throws IllegalArgumentException, IllegalAccessException,
			ParseException {
		
		String sql = "SELECT * FROM  ( " + 
				"SELECT A.*, ROWNUM RN \r\n" + 
				"FROM (select id,parent_id parentId,org_code orgCode,org_name orgName,org_alias orgAlias,org_abbreviation orgAbbreviation\r\n" + 
				",admi_division_code admiDivisionCode,admi_division_name admiDivisionName,create_year createYear,repeal_yesr repealYesr\r\n" + 
				",type_code typeCode,status,create_date createDate,province_code provinceCode,city_code cityCode,area_code areaCode from cen_reg.t_organization) A   \r\n" + 
				"WHERE ROWNUM <= :e )" + 
				"WHERE RN >= :s ";
		System.out.println(sql);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("s", s);
		paramMap.put("e", e);
        
        BeanPropertyRowMapper<Org> rowMapper = new BeanPropertyRowMapper<Org>(Org.class);      
                
        return jdbcTemplate_join.query(sql, paramMap, rowMapper);
	}

	@Override
	public List<Dept> selectJoinDept(long s, long e)
			throws IllegalArgumentException, IllegalAccessException,
			ParseException {
		
		String sql = "SELECT * FROM  ( " + 
				"SELECT A.*, ROWNUM RN   \r\n" + 
				"FROM (select ID,ORG_ID,PARENT_ID,DEPT_CODE,DEPT_NAME,DETAIL_ADDRESS\r\n" + 
				",MEDICAL_SUBJECTS_CODE,MEDICAL_SUBJECTS_NAME,ACTIVE,CREATE_DATE from cen_reg.t_Department) A   \r\n" + 
				"WHERE ROWNUM <= :e ) " + 
				"WHERE RN >= :s";
		System.out.println(sql);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("s", s);
		paramMap.put("e", e);
        
        BeanPropertyRowMapper<Dept> rowMapper = new BeanPropertyRowMapper<Dept>(Dept.class);      
                
        return jdbcTemplate_join.query(sql, paramMap, rowMapper);
	}

	@Override
	public long selectJoinOrgCount() throws IllegalArgumentException,
			IllegalAccessException, ParseException {
		
		String sql ="select count(1) from cen_reg.t_organization";
		
		Long l = jdbcTemplate_join.queryForObject(sql,new HashMap<String, Object>(),Long.class);		
		
		return l;
	}

	@Override
	public long selectJoinDeptCount() throws IllegalArgumentException,
			IllegalAccessException, ParseException {
		
		String sql ="select count(1) from cen_reg.t_department";
		
		Long l = jdbcTemplate_join.queryForObject(sql,new HashMap<String, Object>(),Long.class);		
		
		return l;
	}

}
