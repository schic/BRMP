package com.wondersgroup.brmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.DataType;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;
import com.wondersgroup.brmp.po.webservicepo.ResponsePo;
import com.wondersgroup.brmp.service.intf.BrmpCenterService4ws;
import com.wondersgroup.brmp.util.common.ModelDataUtil;
import com.wondersgroup.brmp.util.webserviceutil.ResponseHead;
import com.wondersgroup.brmp.util.webserviceutil.ResponsePoMsg;

/**
 * 接收建立好的对应模型的数据
 */
@Service("SynchronousData")//名称为接口方法名称，不能随便修改;
public class BrmpSynchronousData implements BrmpCenterService4ws {

	@Autowired CommonDaoIntf commonDaoIntf;
	
	//@Autowired ModelDataDaoIntf modelDataDaoIntf;
	
	@Override
	public ResponsePo parseWs(String params, String systemId, String modelName) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("modelName", modelName);
		ModelData modelData = (ModelData) commonDaoIntf.selectObjByParam(ModelData.class, paramMap);
		if (null == modelData){
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "ModelType: 未找到对应的模型");
		}
		if (!systemId.equals("1")){//不是管理员系统接口不能调用本接口
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "在当前系统,未找到对应的模型");
		}
		if (modelData.getAuditStatus() != 9 && modelData.getAuditStatus() != 0 ){//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "模型不在测试传输状态或模型未通过审核");
		}
		if (modelData.getStatus() != 1){//状态  0:停用 1:启用
			return ResponsePoMsg.response2Obj(ResponseHead.Error, "模型已经被停用请联系管理员");
		}
		
		paramMap.clear();
		paramMap.put("modelId", modelData.getModelId());
		//获取modelData的 map list 存数据入
		List<ModelDataAttribute> modelDataAttributes = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
		List<DataType> dataTypes = commonDaoIntf.selectObj(DataType.class);
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
		
		String tableName = modelData.getModelTabName().concat("_change");//将河对面网络传输过来的同步数据保存到本地数据库对应模型以 _change结尾的表，剩下的步骤交给计算存储过程。
		String msg = commonDaoIntf.saveObj(dataList4save, tableName);
		
		return ResponsePoMsg.response2Obj(ResponseHead.Completenss, msg);
	}

}
