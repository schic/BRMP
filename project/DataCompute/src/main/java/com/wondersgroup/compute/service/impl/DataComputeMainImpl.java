package com.wondersgroup.compute.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.compute.dao.intf.CommonDaoIntf;
import com.wondersgroup.compute.dao.intf.DataComputeDaoIntf;
import com.wondersgroup.compute.service.intf.DataComputeMainIntf;
import com.wondersgroup.empi.po.empipo.ModelData;
import com.wondersgroup.empi.po.empipo.ModelDataAttribute;

@Service("DataComputeMain")
public class DataComputeMainImpl implements DataComputeMainIntf {

	@Autowired DataComputeDaoIntf dataComputeDaoIntf;
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	/**
     * 将临时表作业标志 修改为 1 进行处理
     */
	@Override
	public void updateZYBZ1(ModelData modelData){
		StringBuffer ZYBZBuffer = new StringBuffer();
		ZYBZBuffer.append("update ").append(modelData.getModelTabName()).append("_TEMP set ZYBZ= 1 where ZYBZ=0");
		commonDaoIntf.updateForSql(ZYBZBuffer.toString());
	}
	
	/**
	 * 删除在主题库的重复数据(与临时库比较得出)
	 *      ↓
	 * 临时表去重插入主题库
	 * 		↓
	 * 将插入成功的作业标志修改为 2
	 * 
	 * @param modelData
	 */
	@Override
	public void updateMainData(ModelData modelData){
		StringBuffer sBuffer = new StringBuffer();
		
		/*
         * 删除在主题库的重复数据(与临时库比较得出)
         */
		sBuffer.append("delete from ")
				.append(modelData.getModelTabName()).append(" where Id in ( select d.ID	from ( ")
				.append(" select a.Id from ").append(modelData.getModelTabName()).append(" a inner join ( select Id from ")
				.append(modelData.getModelTabName()).append("_TEMP b WHERE b.zybz = 1 group by id ) c ON a.Id = c.Id ) d )");
		commonDaoIntf.updateForSql(sBuffer.toString());
		
		/*
         * 临时表去重插入主题库
         * 
          insert into table_list.table_name ( table_list.col ) 
         	select
			table_list.col
			from
				(select table_list.col from	md_9fb6518c3e1a439c8e49a16a56ba9791_temp a1 group by a1.updatetime,a1.id) a
			where
				a.zybz = 1 
				and a.updatetime = ( select max( b.updatetime ) from md_9fb6518c3e1a439c8e49a16a56ba9791_temp b where b.zybz = 1 and a.id = b.id );
         */
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("modelId", modelData.getModelId());
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, "brmp_conf_origin_system_model", paramMap);
		sBuffer.setLength(0);//清空sql，重新写入
		for(int i=0;i<modelDataAttributes.size();i++){
			sBuffer.append(modelDataAttributes.get(i).getModelColName());
			if (i!=modelDataAttributes.size()-1) {
				sBuffer.append(", ");
			}
		}
		String wmCol = sBuffer.toString();//字段组合用于sql
		
		
		sBuffer.setLength(0);//清空sql，重新写入
		sBuffer.append("insert into ")
				.append(modelData.getModelTabName()).append(" ( ").append(wmCol).append(" ) ")
				.append(" select ").append(wmCol).append(" from ( select @num := IF(@id = id, @num + 1, 1) num, @id := id as id0,")
				.append(wmCol).append(" from ").append(modelData.getModelTabName()).append("_temp, (SELECT @id := '', @num := 0) t1 where ZYBZ = 1 order by id ,updateTime desc")
				.append(") b where b.num=1");
		commonDaoIntf.updateForSql(sBuffer.toString());
		
		/*
		 * 将插入成功的作业标志修改为 2
         col_sql := 'update '|| table_list.table_name || '_TEMP set ZYBZ= 2 where ZYBZ=1';
		 */
		sBuffer.setLength(0);
		sBuffer.append("update ").append(modelData.getModelTabName()).append("_TEMP set ZYBZ= 2 where ZYBZ=1");
		commonDaoIntf.updateForSql(sBuffer.toString());
	}
	
	
	/**
     * 删除临时表中，已经成功插入主题库的数据，通过作业标志为 2
     * 'delete '|| table_list.table_name || '_TEMP where ZYBZ= 2';
     */
	@Override
	public void delZYBZ2(ModelData modelData){
		StringBuffer ZYBZBuffer = new StringBuffer();
		ZYBZBuffer.append("delete from ").append(modelData.getModelTabName()).append("_TEMP where ZYBZ= 2");
		commonDaoIntf.updateForSql(ZYBZBuffer.toString());
	}

}
