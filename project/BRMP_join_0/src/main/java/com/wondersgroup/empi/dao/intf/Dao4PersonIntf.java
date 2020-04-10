package com.wondersgroup.empi.dao.intf;

import java.text.ParseException;
import java.util.List;

import com.wondersgroup.empi.po.EMPIObj;

public interface Dao4PersonIntf {
	
	List<EMPIObj> selectPerson() throws IllegalArgumentException, IllegalAccessException, ParseException;

}
