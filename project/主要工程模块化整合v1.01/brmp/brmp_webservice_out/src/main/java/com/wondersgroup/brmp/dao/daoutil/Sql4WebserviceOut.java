package com.wondersgroup.brmp.dao.daoutil;

import java.util.List;

import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;


public class Sql4WebserviceOut {
	
	/**
	 * 将扩展表的待传出的前5000条数据修改为即将作业
	 * CGBZ_1 0待传出 1成功 2失败 9作业中
	 * UPDATE md_db0359d112df4385a6f1_ex a set CGBZ_1=9 where CGBZ_1=0 LIMIT 5000
	 * update md_db0359d112df4385a6f1_ex set CGBZ_1=9 where CGBZ_1=0  and rownum<=5000
	 * @param tableName
	 * @param dataBaseName
	 * @return
	 */
	public static String updateEx9(String tableName, String dataBaseName){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("update ").append(tableName).append("_ex set CGBZ_1=9 where CGBZ_1=0 ");
		if ("com.mysql.jdbc.Driver".equals(dataBaseName)){
			sBuffer.append(" LIMIT 5000 ");
		} else if ("oracle.jdbc.driver.OracleDriver".equals(dataBaseName)) {
			sBuffer.append(" and rownum<=5000 ");
		}
		return sBuffer.toString();
	}
	
	/**
	 * 将作业中的数据修改为成功
	 * CGBZ_1 0待传出 1成功 2失败 9作业中
	 * UPDATE md_db0359d112df4385a6f1_ex a set CGBZ_1=1 where CGBZ_1=9
	 * @param tableName
	 * @param dataBaseName
	 * @return
	 */
	public static String updateEx1(String tableName){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("update ").append(tableName).append("_ex set CGBZ_1=1 where CGBZ_1=9 ");
		return sBuffer.toString();
	}
	
	/**
	 * 将作业中的数据修改为失败
	 * CGBZ_1 0待传出 1成功 2失败 9作业中
	 * UPDATE md_db0359d112df4385a6f1_ex a set CGBZ_1=2 where CGBZ_1=9
	 * @param tableName
	 * @param dataBaseName
	 * @return
	 */
	public static String updateEx2(String tableName){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("update ").append(tableName).append("_ex set CGBZ_1=2 where CGBZ_1=9 ");
		return sBuffer.toString();
	}
	
	/**
	 *  待作业插入 到 河对面网络  的数据 查询 sql
	 * 	
	 * @
		select a.* 
		from tableName a 
		inner join tableName_ex b 
		on a.id = b.id
		where b.cgbz_1=9
	* @param modelDataAttributes 对应模型的属性配置list
	* @param tableName 模型表名
	* @return
	*/
	public static <T> String getWaitInsert2Data(List<ModelDataAttribute> modelDataAttributes,String tableName){
		StringBuffer sBuffer = new StringBuffer();
		//TODO 查询语句待优化
		sBuffer.append("select ");
		for (int i=0;i<modelDataAttributes.size();i++) {
			ModelDataAttribute modelDataAttribute = modelDataAttributes.get(i);
			sBuffer.append(" a.").append(modelDataAttribute.getModelColName()).append(" as \"").append(modelDataAttribute.getModelColName()).append("\" ");
			if (i+1 != modelDataAttributes.size() ) {
				sBuffer.append(", ");
			}
		}
		sBuffer.append(" from ").append(tableName).append(" a ");
		sBuffer.append(" inner join ").append(tableName).append("_ex b");
		
		boolean onFlag = true;
		for (int i=0;i<modelDataAttributes.size();i++) {
			ModelDataAttribute modelDataAttribute = modelDataAttributes.get(i);
			int id = modelDataAttribute.getPk();
			if(id==1){
				
				if(onFlag){
					sBuffer.append(" on ");
					onFlag = false;
				} else {
					sBuffer.append(" and ");
				}
				sBuffer.append("a.").append(modelDataAttribute.getModelColName()).append(" = b.").append(modelDataAttribute.getModelColName());
			}
		}
		sBuffer.append(" where b.cgbz_1=9 ");
		return sBuffer.toString();
	}
	

}
