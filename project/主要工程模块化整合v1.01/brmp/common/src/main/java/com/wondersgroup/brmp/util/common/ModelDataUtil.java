package com.wondersgroup.brmp.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;

public class ModelDataUtil {
	
	/**
	 * 模型的数据类型配置 由list转Map
	 * @param dataTypes
	 * @return
	 */
	public static Map<Integer, String> dataTypeList2Map( List<DataType> dataTypes){
		Map<Integer, String> dataTypeMap = new HashMap<Integer, String>();
		DataType dataType;
		for(int i=0;i<dataTypes.size();i++ ){
			dataType = dataTypes.get(i);
			dataTypeMap.put(dataType.getModelColType(), dataType.getDatatype());
		}
		return dataTypeMap;
	}
	
	/**
	 * 模型的数据类型配置 由list转Map 数据类型为java类型
	 * @param dataTypes
	 * @return
	 */
	public static Map<Integer, String> dataTypeListJava2Map( List<DataType> dataTypes){
		Map<Integer, String> dataTypeMap = new HashMap<Integer, String>();
		DataType dataType;
		for(int i=0;i<dataTypes.size();i++ ){
			dataType = dataTypes.get(i);
			dataTypeMap.put(dataType.getModelColType(), dataType.getJavaDatatype());
		}
		return dataTypeMap;
	}
	
	
	/**
	 * 根据建立的模型生成建表语句
	 * @param tableName
	 * @param modelDataAttributes
	 * @param dataTypes
	 * @return
	 */
	public static String createModelDataSql(String tableName,List<ModelDataAttribute> modelDataAttributes,Map<Integer, String> dataTypeMap, String databaseClass){

		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("CREATE TABLE ");
		sBuffer.append(tableName);
		sBuffer.append(" ( ");
		
		if (tableName.contains("_temp")){//temp表增加zybz字段用于计算作业插入到正式库的
			sBuffer.append("ZYBZ ");//作业标志
			sBuffer.append(dataTypeMap.get(1));//int型
			sBuffer.append("(1) DEFAULT '0'  ");
			if ("com.mysql.jdbc.Driver".equals(databaseClass)) {
				sBuffer.append(" COMMENT '作业标志,用于temp表' ");
			}
			sBuffer.append(",");
		}
		
		
		for(int i=0;i<modelDataAttributes.size();i++){
			sBuffer.append(modelDataAttributes.get(i).getModelColName());
			sBuffer.append(" ");
			sBuffer.append(dataTypeMap.get(modelDataAttributes.get(i).getModelColType()));
			if(modelDataAttributes.get(i).getModelColLenth()!=-1 ){
				sBuffer.append("(");
				sBuffer.append(modelDataAttributes.get(i).getModelColLenth());
				if (modelDataAttributes.get(i).getModelColDecimalLenth()!=-1) {
					sBuffer.append(",");
					sBuffer.append(modelDataAttributes.get(i).getModelColDecimalLenth());
				}
				sBuffer.append(") ");
			}
			
			if ("com.mysql.jdbc.Driver".equals(databaseClass)) {
				sBuffer.append(" COMMENT '");
				sBuffer.append(modelDataAttributes.get(i).getModelColDisplayName());
				sBuffer.append("'");
			}
			if(i != modelDataAttributes.size()-1 ){
				sBuffer.append(",");
			}
			
		}
		sBuffer.append(" ) ");
		
		String sql = sBuffer.toString();
		System.out.println(sql);
		return sql;
	}
	
	/**
	 * 根据建立的模型生成扩展表建表语句
	 * @param tableName
	 * @param modelDataAttributes
	 * @param dataTypeMap
	 * @param jdbcDriverClassName
	 * @return
	 */
	public static String createExTabelSql(String tableName, List<ModelDataAttribute> modelDataAttributes,
			Map<Integer, String> dataTypeMap, String databaseClass) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("CREATE TABLE ");
		sBuffer.append(tableName);
		sBuffer.append(" ( ");
		
		for(int i=0;i<modelDataAttributes.size();i++){
			if (modelDataAttributes.get(i).getPk()==1) {//扩展表只建立主键字段确定唯一
				sBuffer.append(modelDataAttributes.get(i).getModelColName());
				sBuffer.append(" ");
				sBuffer.append(dataTypeMap.get(modelDataAttributes.get(i).getModelColType()));
				if(modelDataAttributes.get(i).getModelColLenth()!=-1 ){
					sBuffer.append("(");
					sBuffer.append(modelDataAttributes.get(i).getModelColLenth());
					if (modelDataAttributes.get(i).getModelColDecimalLenth()!=-1) {
						sBuffer.append(",");
						sBuffer.append(modelDataAttributes.get(i).getModelColDecimalLenth());
					}
					sBuffer.append(") ");
				}
				
				if ("com.mysql.jdbc.Driver".equals(databaseClass)) {
					sBuffer.append(" COMMENT '");
					sBuffer.append(modelDataAttributes.get(i).getModelColDisplayName());
					sBuffer.append("'");
				}
				sBuffer.append(",");
				
			}
			
			
		}
		
		/*
		sBuffer.append("GIRID ");//证照id
		sBuffer.append(dataTypeMap.get(0));//String型
		sBuffer.append("(256) DEFAULT '0'  ");
		if ("com.mysql.jdbc.Driver".equals(databaseClass)) {
			sBuffer.append(" COMMENT '证照id,保存大数据中心反给平台数据的唯一id' ");
		}
		sBuffer.append(",");
		*/
		
		sBuffer.append("CGBZ_1 ");//成功标志,两网(互联网区和政务网区)数据交换的成功标志
		sBuffer.append(dataTypeMap.get(1));//int型
		sBuffer.append("(1) DEFAULT '0'  ");
		if ("com.mysql.jdbc.Driver".equals(databaseClass)) {
			sBuffer.append(" COMMENT '判断数据是否上传完成,0未上传,1上传完成' ");
		}
		//sBuffer.append(",");//最后一个字段不加","
		
		sBuffer.append(" ) ");
		
		String sql = sBuffer.toString();
		System.out.println(sql);
		return sql;
		
	}
	
	/**
	 * 将模型传入的参数的json数组 ，保存为可以转换为sql参数的 List<Map<String, Object>>，并验证传入的json的合法性
	 * @param dataLists json字符的模型对象的list
	 * @param modelDataAttributes 模型在模型库里的属性字段list
	 * @param dataTypeMap 模型字段类型参数字典map
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> beforeSaveModelData4Convert(List<String> dataLists,List<ModelDataAttribute> modelDataAttributes,Map<Integer, String> dataTypeMap) throws Exception{
		List<Map<String, Object>> dataList4save = new ArrayList<Map<String,Object>>();//接收传入的模型数据List

		for (int i=0;i<dataLists.size();i++){
			Map<String, Object> data = new HashMap<String, Object>();//接收传入的模型数据
			
			@SuppressWarnings("unchecked")
			Map<String, Object> dataMap = (Map<String, Object>) JSON.parse(dataLists.get(i));
			
			for (int j=0; j<modelDataAttributes.size(); j++){
				ModelDataAttribute modelDataAttribute = modelDataAttributes.get(j);
				String attributeName = modelDataAttribute.getModelColName();
				String attributeType = dataTypeMap.get(modelDataAttribute.getModelColType());
				//System.out.println(" 属性名"+ attributeName + " 属性类型" + attributeType);
				Object value = dataMap.get(attributeName);
				if ("String".equals(attributeType) ) {
					if (value == null) {
						value = "-";
					} else {
						value = String.valueOf(value);
					}
				} else if ("int".equals(attributeType)) {
					if (value == null) {
						value = -1;
					} else {
						value = Integer.parseInt(String.valueOf(value));
					}
				} else if ("Date".equals(attributeType)) {
					if (value == null) {
						value = DateUtils.parseDate("1900-01-01", "yyyy-MM-dd");
					} else {
						value = DateUtils.parseDate(StringUtils.defaultString(String.valueOf(value), "1900-01-01") , "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
					}
				} else if ("long".equals(attributeType) ) {
					if (value == null) {
						value = -1L;
					} else {
						value = Long.parseLong(String.valueOf(value));
					}
				} else if ("float".equals(attributeType)) {
					if (value == null) {
						value = 0.0;
					} else {
						value = Float.parseFloat(String.valueOf(value));
					}
				} else if ("double".equals(attributeType)) {
					if (value == null) {
						value = 0.0;
					} else {
						value = Double.parseDouble(String.valueOf(value));
					}
				}
				
				//System.out.println("测试: 值"+value   +" 属性名"+ attributeName + " 属性类型" + attributeType);
				data.put(attributeName, value);
				
			}
			dataList4save.add(data);
			
		}
		
		return dataList4save;
	}
	

}
