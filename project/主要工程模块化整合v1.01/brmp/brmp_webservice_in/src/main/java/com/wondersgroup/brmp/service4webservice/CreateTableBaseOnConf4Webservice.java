package com.wondersgroup.brmp.service4webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.brmp.dao.daoutil.DaoConfResource;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.util.common.ModelDataUtil;

@Service
public class CreateTableBaseOnConf4Webservice {
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired DaoConfResource daoConfResource;
	
	/**
	 * 只运行在 从网应用服务，根据主网传过来的配置，判断是否需要新建表，并完成建表。
	 */
	public String createTable() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status", 1);
		paramMap.put("auditStatus", 9);
		/*
		private int status;//状态  0:停用 1:启用
		private int auditStatus;//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
		*/
		List<ModelData> modelDatas = commonDaoIntf.selectObjListByParam(ModelData.class, paramMap);
		List<DataType> dataTypes = commonDaoIntf.selectObj(DataType.class);
		Map<Integer, String> dataTypeMap = ModelDataUtil.dataTypeList2Map(dataTypes);
		
		for(int i=0;i<modelDatas.size();i++){
			ModelData modelData = modelDatas.get(i);
			if (!commonDaoIntf.isTable(modelData.getModelTabName())) {
				paramMap.clear();
				paramMap.put("modelId", modelData.getModelId());
				List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
				String tableName = modelData.getModelTabName();
				String createSql = ModelDataUtil.createModelDataSql(tableName.concat("_change"), modelDataAttributes, dataTypeMap, daoConfResource.getJdbcDriverClassName());
				commonDaoIntf.createTable(createSql);//创建_change表
				createSql = ModelDataUtil.createModelDataSql(tableName.concat("_temp"), modelDataAttributes, dataTypeMap, daoConfResource.getJdbcDriverClassName());
				commonDaoIntf.createTable(createSql);//创建_temp表
				createSql = ModelDataUtil.createModelDataSql(tableName, modelDataAttributes, dataTypeMap, daoConfResource.getJdbcDriverClassName());
				commonDaoIntf.createTable(createSql);//创建正式表
				/**
				 * 注意
				 * 扩展表_ex的建表语句不同于其它表的建表语句。调用方法不同
				 */
				createSql = ModelDataUtil.createExTabelSql(tableName.concat("_ex"), modelDataAttributes, dataTypeMap, daoConfResource.getJdbcDriverClassName());
				commonDaoIntf.createTable(createSql);
				
			}
		}
		return "完成";
	}

}
