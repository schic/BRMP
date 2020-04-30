package com.wondersgroup.brmp.dao.daoutil;

public class ModelDataSql {
	

	public static final String queryModelData = "select * from brmp_conf_origin_system_mdbase";

	//删除模型id的字段属性
	public static final String deleteModelDataAttributes = "delete from brmp_conf_origin_system_model where model_id = :modelId";
	
	//修改模型
	public static final String updateModelData = "update brmp_conf_origin_system_mdbase set model_name = :modelName,model_description = :modelDescription,model_updete_time = :modelUpdeteTime where model_id = :modelId";
	
	
}
