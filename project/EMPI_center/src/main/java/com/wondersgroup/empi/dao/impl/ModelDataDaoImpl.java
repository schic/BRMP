package com.wondersgroup.empi.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wondersgroup.empi.dao.intf.ModelDataDaoIntf;
import com.wondersgroup.empi.po.empipo.DataType;
import com.wondersgroup.empi.po.empipo.ModelData;
import com.wondersgroup.empi.po.empipo.ModelDataAttribute;
import com.wondersgroup.empi.po.empipo.OriginSystemInfo;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.daoutil.CommonSql;
import com.wondersgroup.empi.util.daoutil.ParamMapUtil;
import com.wondersgroup.empi.util.sqlutil.ModelDataSql;
import com.wondersgroup.empi.util.sqlutil.OriginSystemSql;

@Repository
public class ModelDataDaoImpl implements ModelDataDaoIntf {

	@Resource(name = "jdbcTemplate")
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<ModelData> queryModelData() {
		List<ModelData> modelDatas = jdbcTemplate.query(ModelDataSql.queryModelData, new BeanPropertyRowMapper<ModelData>(ModelData.class));
		return modelDatas;
	}
	
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
				sBuffer.append(CommonUtil.camelToUnderline(key)).append("= :").append(key);
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
				sBuffer.append(CommonUtil.camelToUnderline(key)).append("= :").append(key);
			}	
		}
		String sql = sBuffer.toString();
		System.out.println(sql);
		List<ModelData> modelDatas = jdbcTemplate.query(sql,paramMap, new BeanPropertyRowMapper<ModelData>(ModelData.class));
		return modelDatas;
	}
	
	@SuppressWarnings("finally")
	@Override
	public ModelData queryModelDataByModelId(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		ModelData modelData = null;
		try {
			modelData = jdbcTemplate.queryForObject(ModelDataSql.queryModelDataByModelId, paramMap, new BeanPropertyRowMapper<ModelData>(ModelData.class));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			return modelData;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ModelData queryModelDataByModelName(String modelName) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelName", modelName);
		ModelData modelData = null;
		try {
			modelData = jdbcTemplate.queryForObject(ModelDataSql.queryModelDataByModelName, paramMap, new BeanPropertyRowMapper<ModelData>(ModelData.class));
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			return modelData;
		}
	}

	@Override
	public List<ModelDataAttribute> queryModelByModelId(String modelId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelId);
		List<ModelDataAttribute> modelDataAttributes = jdbcTemplate.query(ModelDataSql.queryModelDataAttributeByModelId, paramMap, new BeanPropertyRowMapper<ModelDataAttribute>(ModelDataAttribute.class));
		return modelDataAttributes;
	}

	@SuppressWarnings("finally")
	@Override
	public OriginSystemInfo queryOriginSystemByOriginSystemId(String originSystemId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("originSystemId", originSystemId);
		OriginSystemInfo originSystemInfo = null;
		try {
			originSystemInfo = jdbcTemplate.queryForObject(OriginSystemSql.getOriginSystemById, paramMap, new BeanPropertyRowMapper<OriginSystemInfo>(OriginSystemInfo.class));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			return originSystemInfo;
		}
	}
	
	@Override
	public List<DataType> queryDataTypes() {
		List<DataType> dataTypes = jdbcTemplate.query(ModelDataSql.queryDataTypes, new BeanPropertyRowMapper<DataType>(DataType.class));
		return dataTypes;
	}

	
	@Override
	public String updateModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("originSystemId", modelData.getOriginSystemId());
		paramMap.put("modelId", modelData.getModelId());
		
		jdbcTemplate.update(ModelDataSql.deleteModelDataAttributes, paramMap);
		
		String insertModelDataAttributeSql = CommonSql.insertSql(modelDataAttributes.get(0).getClass(), "brmp_conf_origin_system_model");
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
	public String updateModelData(ModelData modelData) {
		Map<String,Object> paramMap = CommonUtil.object2Map(modelData);
		String sql = CommonSql.updateSql(paramMap,"brmp_conf_origin_system_modelbase","modelId");
		System.out.println(sql);
		jdbcTemplate.update(sql, paramMap);
		return "更新模型完成";
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
		
		return null;
	}

	

	

	

	
	

	

}
