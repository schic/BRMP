package com.wondersgroup.brmp.service.intf;

import com.wondersgroup.brmp.po.webservicepo.ResponsePo;

public interface BrmpCenterService4ws {

	/**
	 * 执行请求到BRMP中心的服务
	 * @param params 执行参数
	 * @param systemId 源请求系统id
	 * @param modelName 类名或模型名或XX名
	 * @return
	 */
	ResponsePo parseWs(String params,String systemId,String modelName);

}
