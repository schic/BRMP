package com.wondersgroup.brmp.service.intf;

import com.wondersgroup.brmp.po.webservicepo.ResponsePo;

public interface BrmpCenterService4ws {

	/**
	 * 执行请求到EMPI中心的服务
	 * @param params 执行参数
	 * @param systemId 源请求系统id
	 * @return
	 */
	ResponsePo parseWs(String params,String systemId,String modelName);

}
