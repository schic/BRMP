package com.wondersgroup.brmp.dao.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.brmp.dao.daoutil.ParamMapUtil;
import com.wondersgroup.brmp.dao.intf.SaveEMPIObjIntf;
import com.wondersgroup.brmp.po.empipo.EMPIObj;
import com.wondersgroup.brmp.util.common.EMPIObjUtil;


@Repository
public class SaveEMPIObjImpl implements SaveEMPIObjIntf {
	
	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public String saveEMPIObj(List<EMPIObj> list,String systemId,String sql) {
		System.out.println("开始保存(save_sql)EMPIObj".concat(":").concat(sql));
		for (int i=0;i<list.size();i++) {
			EMPIObj empiObj =list.get(i);
			empiObj.setOriginSystemId(systemId);//源系统id
			try {
				ParamMapUtil.setNull(empiObj);
				Map<String,Object> paramMap = new HashMap<String,Object>();
				Field[] fields = empiObj.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					paramMap.put(field.getName(), field.get(empiObj) );
				}
				EMPIObjUtil.setRecordid(empiObj);
				paramMap.put("recordid", empiObj.getRecordid());
				jdbcTemplate.update(sql, paramMap);
			} catch (Exception e) {
				e.printStackTrace();
				return "数据保存出错save_error:".concat(e.getMessage());
			} 
		}
		return "数据保存完成save_complates";
	}
	


}
