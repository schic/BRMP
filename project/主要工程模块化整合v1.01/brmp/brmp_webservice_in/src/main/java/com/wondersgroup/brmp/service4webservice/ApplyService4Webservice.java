package com.wondersgroup.brmp.service4webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.brmp.dao.intf.CommonDaoIntf;
import com.wondersgroup.brmp.po.empipo.ApplyAttribute;
import com.wondersgroup.brmp.po.empipo.ModelData;
import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;

@Service
public class ApplyService4Webservice{

	@Autowired CommonDaoIntf commonDaoIntf;
	
	/**
	 * 通过申请id获取申请的模型list，里面是各个申请模型的申请属性list
	 * @param applyId
	 * @return
	 */
	public List<List<ModelDataAttribute>> queryApplyById4ApplyDataAttribute(String applyId) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("applyId", applyId);
		List<ApplyAttribute> applyAttributes = commonDaoIntf.selectObjListByParam(ApplyAttribute.class, paramMap);
		
		paramMap.clear();
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i=0;i<applyAttributes.size();i++){
			if(null != map.get(applyAttributes.get(i).getModelId()) ){
				continue;
			} else {
				map.put(applyAttributes.get(i).getModelId(), applyAttributes.get(i).getModelId());
				paramMap.put("modelId".concat(String.valueOf(i)), applyAttributes.get(i).getModelId());
				
			}
			
		}
		List<ModelData> modelDatas = commonDaoIntf.selectObjByParamlist(ModelData.class, paramMap);
		
		List<List<ModelDataAttribute>> applyModelDataAttributes = new ArrayList<List<ModelDataAttribute>>();
		for (int i=0;i<modelDatas.size();i++){
			paramMap.clear();
			paramMap.put("modelId", modelDatas.get(i).getModelId());

			List<ModelDataAttribute> modelDataAttributesTemp = commonDaoIntf.selectObjListByParam(ModelDataAttribute.class, paramMap);
			List<ModelDataAttribute> modelDataAttributes = new ArrayList<ModelDataAttribute>();
			for(int k=0;k<applyAttributes.size();k++){
				for(int j=0;j<modelDataAttributesTemp.size();j++){
					if(applyAttributes.get(k).getModelId().equals(modelDataAttributesTemp.get(j).getModelId() ) && applyAttributes.get(k).getModelColName().equals(modelDataAttributesTemp.get(j).getModelColName()) ){
						modelDataAttributes.add(modelDataAttributesTemp.get(j));
					}
				}
				
			}
			applyModelDataAttributes.add(modelDataAttributes);
		}
		
		return applyModelDataAttributes;
	}


	

}
