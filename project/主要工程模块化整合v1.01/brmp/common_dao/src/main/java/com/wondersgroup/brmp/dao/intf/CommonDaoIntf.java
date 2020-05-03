package com.wondersgroup.brmp.dao.intf;

import java.util.List;
import java.util.Map;

/**
 * 通用dao用于操作java 类对象，Map对象与数据库交互
 * 
 * 参数名注意数据库下划线与Java驼峰命名的对应
 */
public interface CommonDaoIntf {
	
	/**
	 * 判断表是否存在
	 * @param tableName
	 * @return
	 */
	boolean isTable(String tableName);

	/**
	 * 删除指定表(drop表)
	 * @param tableName
	 * @return
	 */
	String delDropTable(String tableName);

	/**
	 * 通过建表语句创建表  或者 执行特殊修改操作的sql
	 * @param createSql 建表语句
	 * @return
	 */
	String createTable(String createSql);

	/**
	 * 保存list数据,
	 * 一种是hashMap<String,Object>的list接收的,
	 * 一种是实体类的对象list 接收的
	 * @param list
	 * @param tableName 数据库表名
	 * @return
	 */
	<T> String saveObj(List<T> list, String tableName);
	
	/**
	 * 保存单个对象数据,
	 * 一种是hashMap<String,Object>接收的,
	 * 一种是实体类的对象的 接收的
	 * @param <T>
	 * @param Object
	 * @param tableName 数据库表名
	 * @return
	 */
	<T> String saveObj(Class<T> clazz, Object Object, String tableName);
	
	/**
	 * 查询获取实体模型数据，实体模型用HashMap封装
	 * @param attributeNames 模型属性名的List
	 * @param tableName 模型表名
	 * @param pageNo 第几页
	 * @param count 分页数
	 * @return
	 */
	List<Map<String, Object>> selectObj(List<String> attributeNames, String tableName,int pageNo,int count);

	/**
	 * 自定义写sql
	 * 查询获取实体模型数据，实体模型用HashMap封装
	 * @param sql 当连接的是oracle数据库时sql中select的字段一定要使用<  as "大小写属性名" > 才能获取大小写正确的key
	 * @return
	 */
	List<Map<String, Object>> selectObj(String sql);
	
	/**
	 * 查询获取实体模型数据，实体模型用类封装
	 * @param clazz 类名
	 * @param pageNo 第几页
	 * @param pageSize 分页数
	 * @return
	 */
	<T> List<T> selectObj(Class<T> clazz, int pageNo, int pageSize);
	
	/**
	 * 查询获取实体模型数据，实体模型用类封装
	 * @param clazz
	 * @return
	 */
	<T> List<T> selectObj(Class<T> clazz);
	
	/**
	 * 更新对象在数据库中用某一字段取值为筛选条件
	 * @param object 需要更新的对象
	 * @param tableName 数据库表名
	 * @param fieldNameById 筛选条件的属性名 !!!!千万注意是属性名!!!!!
	 * @return
	 */
	String updateObj(Object object, String tableName, String fieldNameById);
	
	/**
	 * 通过(=)参数条件获取对象 
	 * @param <T>
	 */
	<T> Object selectObjByParam(Class<T> clazz, Map<String, Object> paramMap);
	
	/**
	 * 通过(=)参数条件获取对象list
	 * @param clazz
	 * @param paramMap
	 * @return
	 */
	<T> List<T> selectObjListByParam(Class<T> clazz, Map<String, Object> paramMap);
	
	/**
	 * 通过(in)单个字段多参数条件查询获取对象list 
	 * @param clazz 对象
	 * @param paramMap 单个字段筛选条件的多参数集合(注意单个字段名多参数存入map为了使key不重复，字段名后面添加序号for循环的i保证key不重复，同时有多个参数)
	 *    paramMap.put("paramName1", paramValue1);
	 * 	  paramMap.put("paramName2", paramValue2);
	 * 
	 */
	<T> List<T> selectObjByParamlist(Class<T> clazz, Map<String, Object> paramMap);
	

	
	/**
	 * 获取某表数据量
	 */
	int getRecords(String tableName);
	
	/**
	 * 清空表 truncate
	 * @param tableName 模型表名
	 * @return
	 */
	String delTruncateTable(String tableName);
	

	/**
	 * 获取中心服务地址
	 * @return
	 */
	String getCenterUrl();

	


}
