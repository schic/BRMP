package com.wondersgroup.empi.util.daoutil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.common.CommonUtil;

public class CommonSql {
	
	/**
	 * 类转插入语句
	 * @param <T>
	 * @param tableName 表名
	 * @return
	 */
	public static <T> String insertSql(Class<T> clz,String tableName){
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = new ArrayList<String>();
		
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			
			String colName = field.getName();
			ColumnName columnName = field.getAnnotation(ColumnName.class);
			if (null != columnName){
				if ("".equals(colName)){
					continue;
				} else {
					colName = columnName.value().toLowerCase();
				}	
			}
			
			objFieldList.add(new String(colName.getBytes()));
		}
		
		sBuffer.append("insert into ");
		sBuffer.append(tableName);
		sBuffer.append("(");
		
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = CommonUtil.camelToUnderline(objFieldList.get(i));
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		sBuffer.append(") values (:");
		for(int i =0;i<objFieldList.size();i++){
			sBuffer.append(objFieldList.get(i));
			if (i != objFieldList.size()-1){
				sBuffer.append(",:");
			}
		}
		sBuffer.append(")");
		
		return sBuffer.toString();
	}
	
	/**
	 * 类转查询语句 (注意使用了驼峰)
	 * @param <T>
	 * @param tableName 表名
	 * @return
	 */
	public static <T> String selectSql(Class<T> clazz,String tableName,Map<String,Object> paramMap){
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = new ArrayList<String>();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			
			String colName = field.getName();
			ColumnName columnName = field.getAnnotation(ColumnName.class);
			if (null != columnName){
				if ("".equals(colName)){
					continue;
				} else {
					colName = columnName.value().toLowerCase().concat(" as ").concat(field.getName());
				}	
			}
			
			objFieldList.add(new String(colName.getBytes()));
		}
		
		sBuffer.append("select ");
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = CommonUtil.camelToUnderline(objFieldList.get(i));
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		
		sBuffer.append(" from ");
		sBuffer.append(tableName);
		
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		if (e.hasNext()){
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" where ");
			sBuffer.append(CommonUtil.camelToUnderline(param.getKey()) );
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		while (e.hasNext()) {
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" and ");
			sBuffer.append(CommonUtil.camelToUnderline(param.getKey()) );
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		
		return sBuffer.toString();
	}
	
	/**
	 * 类转查询语句 
	 * @param <T>
	 * @param tableName 表名
	 * @param boolean isCamelToUnderline 是否需要驼峰转下划线
	 * @return
	 */
	public static <T> String selectSql(Class<T> clazz, String tableName, Map<String, Object> paramMap, Boolean isCamel) {
		if (isCamel) {
			return selectSql(clazz, tableName, paramMap);
		} else {
			StringBuffer sBuffer = new StringBuffer();
			List<String> objFieldList = new ArrayList<String>();
			
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				
				String colName = field.getName();
				ColumnName columnName = field.getAnnotation(ColumnName.class);
				if (null != columnName){
					if ("".equals(colName)){
						continue;
					} else {
						colName = columnName.value().toLowerCase().concat(" as ").concat(field.getName());
					}	
				}
				
				objFieldList.add(new String(colName.getBytes()));
			}
			
			sBuffer.append("select ");
			for(int i =0;i<objFieldList.size();i++){
				String fieldName = objFieldList.get(i);
				sBuffer.append(fieldName);
				if (i != objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			
			sBuffer.append(" from ");
			sBuffer.append(tableName);
			
			Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
			if (e.hasNext()){
				Map.Entry<String, Object> param = e.next();
				sBuffer.append(" where ");
				sBuffer.append(param.getKey());
				sBuffer.append(" = :");
				sBuffer.append(param.getKey());
			}
			while (e.hasNext()) {
				Map.Entry<String, Object> param = e.next();
				sBuffer.append(" and ");
				sBuffer.append(param.getKey());
				sBuffer.append(" = :");
				sBuffer.append(param.getKey());
			}
			
			return sBuffer.toString();
		}
	}
	
	
	/**
	 * 单筛选条件list 查询  获取类的list
	 * @param clazz
	 * @param tableName
	 * @param paramMap 单个筛选条件的参数集合 
	 * 					paramMap.put("paramName0", paramValue0);
	 * 					paramMap.put("paramName1", paramValue1);
	 * 					paramMap.put("paramName2", paramValue2);
	 * 					...
	 * @return
	 */
	public static <T> String selectSqlParamlist(Class<T> clazz, String tableName, Map<String, Object> paramMap) {
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = new ArrayList<String>();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			
			String colName = field.getName();
			ColumnName columnName = field.getAnnotation(ColumnName.class);
			if (null != columnName){
				if ("".equals(colName)){
					continue;
				} else {
					colName = columnName.value().toLowerCase().concat(" as ").concat(field.getName());
				}	
			}
			
			objFieldList.add(new String(colName.getBytes()));
		}
		
		sBuffer.append("select ");
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = CommonUtil.camelToUnderline(objFieldList.get(i));
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		
		sBuffer.append(" from ");
		sBuffer.append(tableName);
		
		
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		boolean flagHasNext = false;
		if (e.hasNext()){
			flagHasNext = true;
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" where ");
			sBuffer.append(CommonUtil.camelToUnderline(param.getKey().substring(0, param.getKey().length()-1) ) );
			sBuffer.append(" in ( :");
			sBuffer.append(param.getKey());
		} else {
			sBuffer.append(" where 1=0 ");//如果没有参数则什么都不查询
		}
		
		while (e.hasNext()) {
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" , :");
			sBuffer.append(param.getKey());
		}
		if (flagHasNext) {
			sBuffer.append(" )");
		}
		
		
		return sBuffer.toString();
	}

	/**
	 * map转插入语句
	 * @param clazz
	 * @param tableName
	 * @return
	 */
	public static <T> String insertSql4Map(Map<String,Object> map, String tableName) {
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = new ArrayList<String>();
		
		sBuffer.append("insert into ");
		sBuffer.append(tableName);
		sBuffer.append("(");
		
		for (Map.Entry<String,Object> e : map.entrySet() ) {
			objFieldList.add(e.getKey());
		}
		
		/*可能的遍历方法
		Iterator<Map.Entry<String, Object>> e = map.entrySet().iterator();
		while (e.hasNext()) {
			Map.Entry<String, Object> entry = e.next();
			String fieldName = entry.getKey();
			sBuffer.append(fieldName);
			if (e.hasNext()) {
				sBuffer.append(",");
			}
		}
		*/
		
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = objFieldList.get(i);
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		sBuffer.append(") values (:");
		for(int i =0;i<objFieldList.size();i++){
			sBuffer.append(objFieldList.get(i));
			if (i != objFieldList.size()-1){
				sBuffer.append(",:");
			}
		}
		sBuffer.append(")");
		
		return sBuffer.toString();
	}

	/**
	 * 查询sql获取实体模型Map封装的List
	 * @param attributeNames 属性list
	 * @param tableName 数据库表名
	 * @param pageNo 页码
	 * @param pageSize 分页条数
	 * @param dataBaseName 数据库驱动类型名称
	 */
	public static String selectSql4Map(List<String> attributeNames, String tableName, int pageNo, int pageSize,String dataBaseName) {
		StringBuffer sBuffer = new StringBuffer();
		if ("com.mysql.jdbc.Driver".equals(dataBaseName)){
			sBuffer.append("select ");
			for(int i=0;i<attributeNames.size();i++){
				sBuffer.append(attributeNames.get(i));
				if (i!=attributeNames.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(tableName);
			sBuffer.append(" limit ").append((pageNo-1)*pageSize).append(",").append(pageSize);
			
		} else if ("oracle.jdbc.driver.OracleDriver".equals(dataBaseName)) {
			sBuffer.append("select ");
			for(int i=0;i<attributeNames.size();i++){
				sBuffer.append(attributeNames.get(i));
				if (i!=attributeNames.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append( " from (");
			sBuffer.append("select rownum as rowno, ");
			for(int i=0;i<attributeNames.size();i++){
				sBuffer.append(attributeNames.get(i));
				if (i!=attributeNames.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(tableName);
			sBuffer.append(" where rownum<= ").append(pageNo*pageSize).append(")");
			sBuffer.append(" where rowno >").append((pageNo-1)*pageSize);
		}
		
		
		return sBuffer.toString();
	}
	
	
	
	public static <T> String selectSql4Obj(Class<T> clazz, String tableName, int pageNo, int pageSize,
			String dataBaseName) {
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = new ArrayList<String>();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			
			String colName = field.getName();
			ColumnName columnName = field.getAnnotation(ColumnName.class);
			if (null != columnName){
				if ("".equals(colName)){
					continue;
				} else {
					colName = columnName.value().toLowerCase().concat(" as ").concat(field.getName());
				}	
			}
			
			objFieldList.add(new String(colName.getBytes()));
		}
		
		
		if ("com.mysql.jdbc.Driver".equals(dataBaseName)){
			sBuffer.append("select ");
			for(int i=0;i<objFieldList.size();i++){
				String fieldName = CommonUtil.camelToUnderline(objFieldList.get(i));
				sBuffer.append(fieldName);
				if (i!=objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(tableName);
			sBuffer.append(" limit ").append((pageNo-1)*pageSize).append(",").append(pageSize);
			
		} else if ("oracle.jdbc.driver.OracleDriver".equals(dataBaseName)) {
			sBuffer.append("select ");
			for(int i=0;i<objFieldList.size();i++){
				String fieldName = CommonUtil.camelToUnderline(objFieldList.get(i));
				sBuffer.append(fieldName);
				if (i!=objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append( " from (");
			sBuffer.append("select rownum as rowno, ");
			for(int i=0;i<objFieldList.size();i++){
				String fieldName = CommonUtil.camelToUnderline(objFieldList.get(i));
				sBuffer.append(fieldName);
				if (i!=objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(tableName);
			sBuffer.append(" where rownum<= ").append(pageNo*pageSize).append(")");
			sBuffer.append(" where rowno >").append((pageNo-1)*pageSize);
		}
		
		
		return sBuffer.toString();
	}
	
	/**
	 * 查询sql获取实体模型Map 带条件查询
	 * @param attributeNames 属性list
	 * @param tableName 数据库表名
	 * @param paramMap 查询条件参数Map
	 */
	public static String selectSql4Map(List<String> attributeNames, String tableName, Map<String, Object> paramMap) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("select ");
		for(int i=0;i<attributeNames.size();i++){
			sBuffer.append(attributeNames.get(i));
			if (i!=attributeNames.size()-1){
				sBuffer.append(",");
			}
		}
		sBuffer.append(" from ").append(tableName);
		
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		if (e.hasNext()){
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" where ");
			sBuffer.append(CommonUtil.camelToUnderline(param.getKey()) );
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		while (e.hasNext()) {
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" and ");
			sBuffer.append(CommonUtil.camelToUnderline(param.getKey()) );
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		
		return sBuffer.toString();
	}

	/**
	 * 获取更新语句(注意驼峰)
	 * @param paramMap 参数Map
	 * @param tableName 数据库表名
	 * @param fieldNameById 根据某一字段名作为筛选条件
	 * @return
	 */
	public static String updateSql(Map<String,Object> paramMap, String tableName, String... fieldNameByIds) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("update ").append(tableName).append(" set ");
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		while (e.hasNext()) {
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(CommonUtil.camelToUnderline(param.getKey()) );
			sBuffer.append(" = :").append(param.getKey());
			if (e.hasNext()) {
				sBuffer.append(",");
			}
		}
		
		boolean whereFlag = true;
		for (int i = 0; i < fieldNameByIds.length; i++) {
			String fieldNameById = fieldNameByIds[i];
			if (StringUtils.isNotBlank(fieldNameById)) {
				if(whereFlag){
					sBuffer.append(" where ").append(CommonUtil.camelToUnderline(fieldNameById)).append(" = :").append(fieldNameById);
					whereFlag = false;
				}else {
					sBuffer.append(" and ").append(CommonUtil.camelToUnderline(fieldNameById)).append(" = :").append(fieldNameById);
				}
			}
			
		}
			
		
		return  sBuffer.toString();
		
	}

	

	

	

	

}