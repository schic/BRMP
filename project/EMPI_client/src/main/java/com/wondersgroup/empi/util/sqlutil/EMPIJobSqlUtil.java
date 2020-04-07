package com.wondersgroup.empi.util.sqlutil;

public class EMPIJobSqlUtil {
	
	/**
	 * 初始化标志位，作业开始
	 */
	public static String init_bz = "update brmp_grxx_inter set bz=:EMPIClientId where bz=0";

	/**
	 * 修改标志位，作业完成
	 */
	public static String completes_bz = "update brmp_grxx_inter set bz=:EMPIClientIdC where bz= :EMPIClientId";
	
	/**
	 * 从临时表中
	 * 获取类计算主索引
	 */
	public static String select_cen_inter_EMPIObj = "select * from brmp_grxx_inter t where t.bz=:EMPIClientId";


	/**
	 * 查询主总存库是否存在该条记录
	 */
	public static String select_cache_EMPIObj = "select * from brmp_grxx_cache t where t.ORIGIN_SYSTEM_ID = :originSystemId and t.ORIGIN_ID = :originId";


	/**
	 * 查询身份证号相同的索引记录
	 */
	public static String select_cache_EMPIObj_bySFZH = "select * from brmp_grxx_cache t where t.SFZH = :sfzh";

	
	/**
	 * 查询 名称 相同的索引记录
	 */
	public static String select_cache_EMPIObj_byXM = "select * from brmp_grxx_cache t where t.XM = :xm";
	
	/**
	 * 从索引终表
	 * 查询身份证号相同的索引记录 
	 */
	public static String select_EMPIObj_bySFZH = "select * from brmp_grxx t where t.SFZH = :sfzh";

	
	/**
	 * 保存到索引关联总表
	 * 插入数据库保存主索引
	 */
	public static String insert_cache_EMPIObj = "insert into brmp_grxx_cache (RECORDID,OBJ_ID,SFZH,XM,XB,CSRQ,SJHM,JTDZ,LXR,LXRDH,ORIGIN_SYSTEM_ID,ORIGIN_ID,ZQDDF,GXRQ) VALUES (:recordid,:objId,:sfzh,:xm,:xb,:csrq,:sjhm,:jtdz,:lxr,:lxrdh,:originSystemId,:originId,:zqddf,:gxrq)";

	
	/**
	 * 从索引终表
	 * 插入索引记录 
	 */
	public static String insert_EMPIObj = "insert into brmp_grxx (RECORDID,OBJ_ID,SFZH,XM,XB,CSRQ,SJHM,JTDZ,LXR,LXRDH,ZQDDF,GXRQ) VALUES (:recordid,:objId,:sfzh,:xm,:xb,:csrq,:sjhm,:jtdz,:lxr,:lxrdh,:zqddf,:gxrq)";


	
	
	/**
	 * 插入到到索引关联总表
	 * 插入数据库保存主索引
	 */
	public static String update_cache_EMPIObj = "update brmp_grxx_cache set OBJ_ID = :objId ,SFZH = :sfzh ,XM = :xm ,XB = :xb,CSRQ =:csrq,SJHM=:sjhm,JTDZ=:jtdz,LXR=:lxr,LXRDH=:lxrdh,ZQDDF=:zqddf,GXRQ=:gxrq where RECORDID = :recordid";

	/**
	 * 从索引终表
	 * 更新索引记录 
	 */
	public static String update_EMPIObj = "update brmp_grxx set RECORDID= :recordid ,SFZH = :sfzh ,XM = :xm ,XB = :xb,CSRQ =:csrq,SJHM=:sjhm,JTDZ=:jtdz,LXR=:lxr,LXRDH=:lxrdh,ZQDDF=:zqddf,GXRQ=:gxrq  where OBJ_ID = :objId";

	
	/**
	 * 索引计算完成后删除临时表完成作业数据
	 */
	public static String delete_by_bz = "delete from brmp_grxx_inter where bz > 10000";

	


	

	
	
	
	
}
 