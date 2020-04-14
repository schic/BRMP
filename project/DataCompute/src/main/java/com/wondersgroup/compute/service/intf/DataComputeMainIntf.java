package com.wondersgroup.compute.service.intf;

import com.wondersgroup.empi.po.empipo.ModelData;

public interface DataComputeMainIntf {
	

	/**
     * 将临时表作业标志 修改为 1 进行处理
     */
	void updateZYBZ1(ModelData modelData);
	
	/**
	 * 删除在主题库的重复数据(与临时库比较得出)
	 *      ↓
	 * 临时表去重插入主题库
	 * 		↓
	 * 将插入成功的作业标志修改为 2
	 * 
	 * @param modelData
	 */
	void updateMainData(ModelData modelData);

	/**
     * 删除临时表中，已经成功插入主题库的数据，通过作业标志为 2
     * 'delete '|| table_list.table_name || '_TEMP where ZYBZ= 2';
     */
	void delZYBZ2(ModelData modelData);
	
	

}
