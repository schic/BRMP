package com.wondersgroup.empi.dao.intf;

import java.text.ParseException;
import java.util.List;

import com.wondersgroup.empi.po.Dept;
import com.wondersgroup.empi.po.Org;
import com.wondersgroup.empi.po.Person;
public interface DaoJoinIntf {
	
	long selectJoinPersonCount() throws IllegalArgumentException, IllegalAccessException, ParseException;
	
	List<Person> selectJoinPerson(long s , long e) throws IllegalArgumentException, IllegalAccessException, ParseException;	
	
	
	long selectJoinOrgCount() throws IllegalArgumentException, IllegalAccessException, ParseException;
	
	List<Org> selectJoinOrg(long s , long e) throws IllegalArgumentException, IllegalAccessException, ParseException;
	
	long selectJoinDeptCount() throws IllegalArgumentException, IllegalAccessException, ParseException;
	
	List<Dept> selectJoinDept(long s , long e) throws IllegalArgumentException, IllegalAccessException, ParseException;
}
