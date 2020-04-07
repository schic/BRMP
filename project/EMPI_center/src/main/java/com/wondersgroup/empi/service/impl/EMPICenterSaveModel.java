package com.wondersgroup.empi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.empi.dao.intf.CommonDaoIntf;
import com.wondersgroup.empi.dao.intf.ModelDataDaoIntf;
import com.wondersgroup.empi.po.empipo.DataType;
import com.wondersgroup.empi.po.empipo.ModelData;
import com.wondersgroup.empi.po.empipo.ModelDataAttribute;
import com.wondersgroup.empi.po.webservicepo.ResponsePo;
import com.wondersgroup.empi.service.intf.EMPICenterService4ws;
import com.wondersgroup.empi.util.common.ModelDataUtil;
import com.wondersgroup.empi.util.common.ResponseHead;
import com.wondersgroup.empi.util.common.ResponsePoMsg;

@Service("model")//名称为接口方法名称，不能随便修改;
public class EMPICenterSaveModel implements EMPICenterService4ws {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	@Autowired ModelDataDaoIntf modelDataDaoIntf;
	
	@Override
	public ResponsePo parseWs(String params, String systemId, String modelName) {
		ModelData modelData = modelDataDaoIntf.queryModelDataByModelName(modelName);
		if (null == modelData){
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "ModelType: 未找到对应的模型");
		}
		if (modelData.getAuditStatus() != 9 && modelData.getAuditStatus() != 0 ){//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "模型不在测试传输状态或模型未通过审核");
		}
		if (modelData.getStatus() != 1){//状态  0:停用 1:启用
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "模型已经被停用请联系管理员");
		}
		
		//获取modelData的 map list 存数据入
		List<ModelDataAttribute> modelDataAttributes = modelDataDaoIntf.queryModelByModelId(modelData.getModelId());
		List<DataType> dataTypes = modelDataDaoIntf.queryDataTypes();
		Map<Integer, String> dataTypeMap = ModelDataUtil.dataTypeListJava2Map(dataTypes);
		
		List<Map<String, Object>> dataList4save = null;
		try {
			List<String> dataLists = JSON.parseArray(params, String.class);
			if (dataLists.size()>5000){
				return ResponsePoMsg.response2Obj(ResponseHead.Error, "数据单次传入超过限制，单次传入不超过5000条");
			}
			
			dataList4save = ModelDataUtil.beforeSaveModelData4Convert(dataLists, modelDataAttributes, dataTypeMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "params参数的json格式出错——".concat(e.getMessage()));
		}
		
		String tableName = modelData.getModelTabName().concat("_temp");
		String msg = commonDaoIntf.saveObj(dataList4save, tableName);
		
		return ResponsePoMsg.response2Obj(ResponseHead.Completenss, msg);
	}

}
