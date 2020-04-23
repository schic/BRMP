package com.wondersgroup.brmp.dao.intf;

import java.util.List;

import com.wondersgroup.brmp.po.empipo.EMPIObj;

public interface SaveEMPIObjIntf {
	
		
	/**
	 * 保存EMPIObj时设置自定义唯一id 和 来源系统的id
	 * @param list
	 * @param systemId
	 * @param sql
	 * @return
	 */
	String saveEMPIObj(List<EMPIObj> list,String systemId,String sql);

}
