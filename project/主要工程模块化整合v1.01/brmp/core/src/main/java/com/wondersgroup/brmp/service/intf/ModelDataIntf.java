package com.wondersgroup.brmp.service.intf;

import java.util.List;

import com.wondersgroup.brmp.po.dicpo.Dictionary;
import com.wondersgroup.brmp.po.dicpo.DictionaryItem;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;

/**
 * 关于 模型管理 的业务处理
 *
 */
public interface ModelDataIntf {
	
	List<ModelData> queryModelData();
	
	/**
	 * 通过参选获取 模型  配置表信息
	 * @param originSystemId 默认"1"为不添加系统筛选条件
	 * @param beginDate 筛选大于开始日期的创建日期，没有参数为""
	 * @param endDate 筛选小于结束日期的创建日期，没有参数为""
	 * @param status 启停状态筛选       状态  0:停用 1:启用
	 * @param auditStatus 审核状态筛选   审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过 
	 */
	List<ModelData> queryModelData(String originSystemId,String beginDate, String endDate, String status, String auditStatus);

	List<ModelDataAttribute> queryModelById(String modelId);

	List<DataType> queryDataTypes();

	String updateModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData);

	String insertModelData(List<ModelDataAttribute> modelDataAttributes, ModelData modelData);
	
	/**
	  * 接入系统测试审核通过后，创建正式表。
	 * @param modelId
	 * @return
	 */
	String createOfficialTable(String modelId);
	
	/**
	 * 创建正式表后,创建扩展表
	 */
	String createExTable(String modelId);
	
	/**
	 * 创建中间temp表,测试接入系统数据传入
	 * @param modelId
	 * @return
	 */
	String testSendData(String modelId);

	/**
	  * 获取测试传输模型的json参数
	 * @param modelId 模型id
	 * @return
	 */
	String getTestParams(String modelId);

	/**
	  * 获取中心服务地址，从数据库配置表查得
	 * @return
	 */
	String getCenterUrl();

	/**
	  * 获取实体模型测试数据(前20条)
	 * @param modelId
	 * @return json
	 */
	String getEntityModelData(String modelId) throws Exception;
	
	/**
	 * 获取实体模型正式表数据(前20条)
	 * @param modelId
	 * @return json
	 */
	String getOfficialModelData(String modelId) throws Exception;

	/**
	  * 清除实体模型测试数据
	 * @param modelId
	 * @return
	 */
	String clearEntityModelData(String modelId);

	/**
	  * 完成模型建立测试，提交审批
	 * @param modelId
	 * @return
	 */
	String sendAudit(String modelId);
	
	/**
	  * 通过接入系统id(list)获取List系统信息
	 * @param originSystemIds 接入系统Id号List
	 * @return
	 */
	List<OriginSystemInfo> queryOriginSystemByOriginSystemIds(List<String> originSystemIds);

	/**
	 * 通过接入系统id获取系统信息
	 * @param String 接入系统id
	 * @return
	 */
	OriginSystemInfo queryOriginSystemByOriginSystemId(String originSystemId);
	
	/**
	  * 通过接入系统名称获取系统信息
	 * @param String 接入系统名称
	 * @return
	 */
	OriginSystemInfo queryOriginSystemByOriginSystemName(String originSystemName);

	/**
	  * 通过接入系统中文名称获取系统信息
	 * @param String 接入系统名称
	 * @return
	 */
	OriginSystemInfo queryOriginSystemByOriginSystemCName(String originSystemCname);

	/**
	  * 保存系统信息
	 * @param originSystemInfo
	 * @return
	 */
	String saveOriginSystem(OriginSystemInfo originSystemInfo);

	/**
	  * 审核模型是否通过
	 * @param modelId 模型id
	 * @param i 审核状态
	 * @return
	 */
	String setAudit(String modelId, int i);

	
	/**
	 * 通过参选获取 字典表信息
	 * @param originSystemId 默认"1"为不添加系统筛选条件
	 * @param beginDate 筛选大于开始日期的创建日期，没有参数为""
	 * @param endDate 筛选小于结束日期的创建日期，没有参数为""
	 * @param status 启停状态筛选       状态  0:停用 1:启用
	 * @param auditStatus 审核状态筛选   审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过 
	 */
	List<Dictionary> queryDictionary(String originSystemId,String beginDate, String endDate, String status, String auditStatus);
	
	String updateDictionary(List<DictionaryItem> dictionaryItems, Dictionary dictionary);

	String insertDictionary(List<DictionaryItem> dictionaryItems, Dictionary dictionary);

}
