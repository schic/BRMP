package com.wondersgroup.brmp.dao.intf;

import java.util.List;
import java.util.Map;

import com.wondersgroup.brmp.po.dicpo.Dictionary;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;


public interface ModelDataDaoIntf {
	
	/**
	 * 获取模型数据List带参数
	 * @param paramMap
	 * @return
	 */
	List<ModelData> queryModelData(Map<String, Object> paramMap);
	
	/**
	 * 获取字典表数据List带参数
	 * @param paramMap
	 * @return
	 */
	List<Dictionary> queryDictionary(Map<String, Object> paramMap);


	/**
	 * 更新模型和模型属性
	 * @param modelDataAttributes
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	String updateModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception;

	
	/**
	 * 新增模型和模型属性
	 * @param modelDataAttributes
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	String insertModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception;
	
	

	/**
	 * 验证建立好的模型表，用于测试数据传输
	 * @param modelData
	 * @return
	 */
	String verifyModelTable(ModelData modelData);


	

	

	

	


	


	

	
	
}
