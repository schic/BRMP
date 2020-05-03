package com.wondersgroup.brmp.dao.daoutil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.brmp.util.anotation.Column;
import com.wondersgroup.brmp.util.anotation.Table;


public class CommonSql {
	
	
	
	/**
	 * 类转插入语句
	 * @param <T>
	 * @param tableName 表名
	 * @return
	 */
	public static <T> String insertSql(Class<T> clazz, String tableName){
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = getConvertColNames(clazz,"insert");
		List<String> objFieldListO = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			objFieldListO.add(new String(field.getName()));
		}
		
		sBuffer.append("insert into ");
		sBuffer.append(tableName);
		sBuffer.append("(");
		
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = objFieldList.get(i);
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		sBuffer.append(") values (:");
		for(int i =0;i<objFieldListO.size();i++){
			sBuffer.append(objFieldListO.get(i));
			if (i != objFieldListO.size()-1){
				sBuffer.append(",:");
			}
		}
		sBuffer.append(")");
		
		return sBuffer.toString();
	}
	
	/**
	 * 类转查询语句 (注意使用了驼峰)
	 * @param clazz 类
	 * @param paramMap 参数HashMap
	 * @return
	 */
	public static <T> String selectSql(Class<T> clazz, Map<String,Object> paramMap){
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = getConvertColNames(clazz, "select");
		Table table = clazz.getAnnotation(Table.class);
		
		sBuffer.append("select ");
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = objFieldList.get(i);
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		
		sBuffer.append(" from ");
		sBuffer.append(table.name());
		
		
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		if (e.hasNext()){
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" where ");
			if (table != null && !table.camel()){
				sBuffer.append(param.getKey());
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(param.getKey()) );
			}	
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		while (e.hasNext()) {
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" and ");
			if (table != null && !table.camel()){
				sBuffer.append(param.getKey());
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(param.getKey()) );
			}	
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		
		return sBuffer.toString();
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
	public static <T> String selectSqlParamlist(Class<T> clazz, Map<String, Object> paramMap) {
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = getConvertColNames(clazz, "select");
		Table table = clazz.getAnnotation(Table.class);
		
		sBuffer.append("select ");
		for(int i =0;i<objFieldList.size();i++){
			String fieldName = objFieldList.get(i);
			sBuffer.append(fieldName);
			if (i != objFieldList.size()-1){
				sBuffer.append(",");
			}
		}
		
		sBuffer.append(" from ");
		sBuffer.append(table.name());
		
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		boolean flagHasNext = false;
		if (e.hasNext()){
			flagHasNext = true;
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" where ");
			if (table != null && !table.camel()){
				sBuffer.append(param.getKey().substring(0, param.getKey().length()-1));
			} else {
				sBuffer.append(DaoUtil.camelToUnderline(param.getKey().substring(0, param.getKey().length()-1) ) );
			}
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
				sBuffer.append(attributeNames.get(i)).append(" as \"").append(attributeNames.get(i)).append("\" ");//注意oacle此处as保证attributeName的在map里面的key的大小写准确
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
	
	
	
	public static <T> String selectSql4Obj(Class<T> clazz, int pageNo, int pageSize, String dataBaseName) {
		StringBuffer sBuffer = new StringBuffer();
		List<String> objFieldList = getConvertColNames(clazz, "select");
		Table table = clazz.getAnnotation(Table.class);
		
		if ("com.mysql.jdbc.Driver".equals(dataBaseName)){
			sBuffer.append("select ");
			for(int i=0;i<objFieldList.size();i++){
				String fieldName = objFieldList.get(i);
				sBuffer.append(fieldName);
				if (i!=objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(table.name());
			sBuffer.append(" limit ").append((pageNo-1)*pageSize).append(",").append(pageSize);
			
		} else if ("oracle.jdbc.driver.OracleDriver".equals(dataBaseName)) {
			sBuffer.append("select ");
			for(int i=0;i<objFieldList.size();i++){
				String fieldName = objFieldList.get(i);
				if (fieldName.contains(" as ")){
					int beginIndex = fieldName.lastIndexOf(" as ")+4;
					fieldName = fieldName.substring(beginIndex);
				}
				sBuffer.append(fieldName);
				if (i!=objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append( " from (");
			sBuffer.append("select rownum as rowno, ");
			for(int i=0;i<objFieldList.size();i++){
				String fieldName = objFieldList.get(i);
				sBuffer.append(fieldName);
				if (i!=objFieldList.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(table.name());
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
			sBuffer.append(attributeNames.get(i)).append(" as \"").append(attributeNames.get(i)).append("\" ");//注意oacle此处as保证attributeName的在map里面的key的大小写准确;
			if (i!=attributeNames.size()-1){
				sBuffer.append(",");
			}
		}
		sBuffer.append(" from ").append(tableName);
		
		Iterator<Map.Entry<String, Object>> e = paramMap.entrySet().iterator();
		if (e.hasNext()){
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" where ");
			sBuffer.append(DaoUtil.camelToUnderline(param.getKey()) );
			sBuffer.append(" = :");
			sBuffer.append(param.getKey());
		}
		while (e.hasNext()) {
			Map.Entry<String, Object> param = e.next();
			sBuffer.append(" and ");
			sBuffer.append(DaoUtil.camelToUnderline(param.getKey()) );
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
			sBuffer.append(DaoUtil.camelToUnderline(param.getKey()) );
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
					sBuffer.append(" where ").append(DaoUtil.camelToUnderline(fieldNameById)).append(" = :").append(fieldNameById);
					whereFlag = false;
				}else {
					sBuffer.append(" and ").append(DaoUtil.camelToUnderline(fieldNameById)).append(" = :").append(fieldNameById);
				}
			}
			
		}
			
		
		return  sBuffer.toString();
		
	}

	

	
	
		
	/**
	 * 判断类属性名如何转换成数据库字段名
	 * @param clazz
	 * @param dbType 数据库操作方式 insert 和 select
	 * @return List<String>
	 */
	private static <T> List<String> getConvertColNames(Class<T> clazz,String dbType){
		List<String> objFieldList = new ArrayList<String>();
		Table table = clazz.getAnnotation(Table.class);
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String colNameCamel;
			if (table != null && !table.camel()){
				colNameCamel = field.getName().toLowerCase();
			} else {
				colNameCamel = DaoUtil.camelToUnderline(field.getName());
			}
			String colName = colNameCamel;
			Column column = field.getAnnotation(Column.class);
			if (null != column){
				if ("".equals(column.name())){//若字段名为""，则在数据库中不使用该字段
					continue;
				} else {
					if ("select".equals(dbType)) {
						colName = column.name().toLowerCase().concat(" as ").concat(colNameCamel);
					}  else {
						colName = column.name().toLowerCase();
					}
				}	
			}
			
			objFieldList.add(new String(colName.getBytes()));
		}
		
		return objFieldList;
	}

	

	

}