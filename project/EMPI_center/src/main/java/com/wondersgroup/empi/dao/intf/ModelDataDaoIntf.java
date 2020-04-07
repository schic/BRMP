package com.wondersgroup.empi.dao.intf;

import java.util.List;
import java.util.Map;

import com.wondersgroup.empi.po.empipo.DataType;
import com.wondersgroup.empi.po.empipo.ModelData;
import com.wondersgroup.empi.po.empipo.ModelDataAttribute;
import com.wondersgroup.empi.po.empipo.OriginSystemInfo;

public interface ModelDataDaoIntf {
	
	/**
	 * 获取模型数据List
	 * @return
	 */
	List<ModelData> queryModelData();
	
	/**
	 * 获取模型数据List带参数
	 * @param paramMap
	 * @return
	 */
	List<ModelData> queryModelData(Map<String, Object> paramMap);

	/**
	 * 根据模型id查询获取模型
	 * @param modelId
	 * @return
	 */
	ModelData queryModelDataByModelId(String modelId);
	
	/**
	 * 根据模型name查询获取模型
	 * @param modelName
	 * @return
	 */
	ModelData queryModelDataByModelName(String modelName);

	/**
	 * 根据模型id查询获取模型属性list
	 * @param modelId
	 * @return
	 */
	List<ModelDataAttribute> queryModelByModelId(String modelId);

	/**
	 * 更新模型和模型属性
	 * @param modelDataAttributes
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	String updateModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception;

	/**
	 * 更新模型
	 * @param modelData
	 * @return
	 */
	String updateModelData(ModelData modelData);
	
	/**
	 * 新增模型和模型属性
	 * @param modelDataAttributes
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	String insertModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData) throws Exception;
	
	/**
	 * 根据系统id获取系统信息
	 * @param originSystemId
	 * @return
	 */
	OriginSystemInfo queryOriginSystemByOriginSystemId(String originSystemId);
	
	/**
	 * 获取数据类型配置信息
	 * @return
	 */
	List<DataType> queryDataTypes();

	/**
	 * 验证建立好的模型表，用于测试数据传输
	 * @param modelData
	 * @return
	 */
	String verifyModelTable(ModelData modelData);

	

	

	


	


	

	
	
}
