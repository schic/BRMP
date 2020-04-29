package com.wondersgroup.brmp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.dao.daoutil.BrmpConfResource;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.webservicepo.ResponsePo;
import com.wondersgroup.brmp.service.intf.BrmpCenterService4ws;
import com.wondersgroup.brmp.service4webservice.CreateTableBaseOnConf4Webservice;
import com.wondersgroup.brmp.util.anotation.Table;
import com.wondersgroup.brmp.util.webserviceutil.ResponseHead;
import com.wondersgroup.brmp.util.webserviceutil.ResponsePoMsg;

/**
 * 接收 跨网主系统的 服务请求，更新从网 数据库模型等配置表。
 * 主网系统不需要此配置
 */
@Service("updateProperties")//名称为接口方法名称，不能随便修改;
public class BrmpUpdateProperties implements BrmpCenterService4ws{
	
	@Autowired BrmpConfResource brmpConfResource;
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired CreateTableBaseOnConf4Webservice createTableBaseOnConf4Webservice;
	
	private static boolean modelDataComplate = false;
	private static boolean modelDataAttributeComplate = false;

	@Override
	public ResponsePo parseWs(String params, String systemId, String modelName) {
		if (!"1".equals(systemId)) {//不是系统管理员接口不能操作此接口
			ResponsePoMsg.response2Obj(ResponseHead.NoSupport,"系统不支持此项服务，请联系系统管理员");
		}
		if ("true".equals(brmpConfResource.getMainWebService())){
			ResponsePoMsg.response2Json(ResponseHead.NoSupport, "主网系统不需要更新配置表");
		}
		/**
		 * 通过类名
		 * 获取配置类
		 */
		Class<?> clazz;
		try {
			//					   com.wondersgroup.brmp.po.empipo.ModelData
//			clazz = Class.forName("com.wondersgroup.brmp.po.empipo.".concat(modelName));
			clazz = Class.forName(modelName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "ModelType: 未找到对应的配置表类");
		}
		List<String> dataLists = JSON.parseArray(params, String.class);
		List<Object> objects = new ArrayList<Object>();
		for(int i=0;i<dataLists.size();i++){
			objects.add(JSON.parseObject(dataLists.get(i), clazz));
		}
		Table table = clazz.getAnnotation(Table.class);
		commonDaoIntf.delTruncateTable(table.name());
		String msg = commonDaoIntf.saveObj(objects, table.name());
		
		if("数据保存完成save_complates".equals(msg)){//配置更新完成后，判断是否需要新建表，并完成建表。
			if ("com.wondersgroup.brmp.po.empipo.ModelData".equals(modelName) ) {
				modelDataComplate = true;
			} else if ("com.wondersgroup.brmp.po.empipo.ModelDataAttribute".equals(modelName) ) {
				modelDataAttributeComplate = true;
			}
			if(modelDataComplate && modelDataAttributeComplate){
				createTableBaseOnConf4Webservice.createTable();
				modelDataComplate = false;
				modelDataAttributeComplate = false;
			}	
		}
		return ResponsePoMsg.response2Obj(ResponseHead.Completenss, "更新完成");
	}

}
