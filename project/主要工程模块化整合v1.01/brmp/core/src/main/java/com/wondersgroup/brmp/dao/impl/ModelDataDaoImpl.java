package com.wondersgroup.brmp.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.brmp.dao.daoutil.CommonSql;
import com.wondersgroup.brmp.dao.daoutil.DaoUtil;
import com.wondersgroup.brmp.dao.daoutil.ModelDataSql;
import com.wondersgroup.brmp.dao.daoutil.ParamMapUtil;
import com.wondersgroup.brmp.dao.intf.ModelDataDaoIntf;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;


@Repository
public class ModelDataDaoImpl implements ModelDataDaoIntf {

	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<ModelData> queryModelData(Map<String, Object> paramMap) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(ModelDataSql.queryModelData);
		Iterator<String> it=paramMap.keySet().iterator();
		String key;
		if (it.hasNext()) {
			key = it.next();
			sBuffer.append(" where ");
			if("beginDate".equals(key)) {
				sBuffer.append("model_create_time >= :").append(key);
			} else if ("endDate".equals(key)) {
				sBuffer.append("model_create_time <= :").append(key);
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(key)).append("= :").append(key);
			}
		}
		while(it.hasNext()){
			sBuffer.append(" and ");
			key = it.next().toString();
			if("beginDate".equals(key)) {
				sBuffer.append("model_create_time >= :").append(key);
			} else if ("endDate".equals(key)) {
				sBuffer.append("model_create_time <= :").append(key);
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(key)).append("= :").append(key);
			}	
		}
		String sql = sBuffer.toString();
		System.out.println(sql);
		List<ModelData> modelDatas = jdbcTemplate.query(sql,paramMap, new BeanPropertyRowMapper<ModelData>(ModelData.class));
		return modelDatas;
	}
	
	@Override
	public String updateModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("originSystemId", modelData.getOriginSystemId());
		paramMap.put("modelId", modelData.getModelId());
		
		jdbcTemplate.update(ModelDataSql.deleteModelDataAttributes, paramMap);
		
		String insertModelDataAttributeSql = CommonSql.insertSql(ModelDataAttribute.class, "brmp_conf_origin_system_model");
		for(int i=0;i<modelDataAttributes.size();i++){
			paramMap.put("modelColName", modelDataAttributes.get(i).getModelColName());
			paramMap.put("modelColDisplayName", modelDataAttributes.get(i).getModelColDisplayName());
			paramMap.put("modelColType", modelDataAttributes.get(i).getModelColType());
			paramMap.put("modelColLenth", modelDataAttributes.get(i).getModelColLenth());
			paramMap.put("modelColDecimalLenth", modelDataAttributes.get(i).getModelColDecimalLenth());
			paramMap.put("displayOrder", i+1);
			jdbcTemplate.update(insertModelDataAttributeSql, paramMap);
		}
		
		paramMap.put("modelName", modelData.getModelName());
		paramMap.put("modelDescription", modelData.getModelDescription());
		paramMap.put("modelUpdeteTime", new Date());
		jdbcTemplate.update(ModelDataSql.updateModelData, paramMap);
		
		return "修改模型字段完成";
	}
	
	@Override
	public String insertModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception {
		String insertModelDataSql = CommonSql.insertSql(ModelData.class, "brmp_conf_origin_system_modelbase");
		System.out.println(insertModelDataSql);
		ParamMapUtil.setNull(modelData);
		jdbcTemplate.update(insertModelDataSql, ParamMapUtil.getParamMap(modelData));
		
		String insertModelDataAttributeSql = CommonSql.insertSql(ModelDataAttribute.class, "brmp_conf_origin_system_model");
		System.out.println(insertModelDataAttributeSql);
		
		for(int i=0;i<modelDataAttributes.size();i++){
			ModelDataAttribute modelDataAttribute = modelDataAttributes.get(i);
			ParamMapUtil.setNull(modelDataAttribute);
			Map<String,Object> paramMap = ParamMapUtil.getParamMap(modelDataAttribute);
			paramMap.put("originSystemId", modelData.getOriginSystemId());
			paramMap.put("modelId", modelData.getModelId());
			jdbcTemplate.update(insertModelDataAttributeSql, paramMap);
		}
		
		return "新增完成";//返回给前端页面勿变更
	}

	@Override
	public String verifyModelTable(ModelData modelData) {
		//TODO
		return null;
	}

	

	

	

	
	

	

}
