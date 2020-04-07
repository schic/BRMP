package com.wondersgroup.empi.util.sqlutil;

public class ModelDataSql {
	

	public static final String queryModelData = "select * from brmp_conf_origin_system_modelbase";
	
	public static final String queryModelDataByModelId = "select * from brmp_conf_origin_system_modelbase where model_id = :modelId";

	public static final String queryModelDataByModelName = "select * from brmp_conf_origin_system_modelbase where model_name = :modelName ";
	
	public static final String queryModelDataAttributeByModelId = "select * from brmp_conf_origin_system_model where model_id = :modelId";

	public static final String queryDataTypes = "select * from brmp_dic_datatype";

	//删除模型id的字段属性
	public static final String deleteModelDataAttributes = "delete from brmp_conf_origin_system_model where model_id = :modelId";
	
	//修改模型
	public static final String updateModelData = "update brmp_conf_origin_system_modelbase set model_name = :modelName,model_description = :modelDescription,model_updete_time = :modelUpdeteTime where model_id = :modelId";

	
	
	
}
