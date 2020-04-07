package com.wondersgroup.empi.util.common;

import com.wondersgroup.empi.po.webservicepo.ResponsePo;

public class ResponsePoMsg {
	
	/**
	 * 返回结果工具方法json
	 * @param ResponseHead responseHead 返回头部 说明请求是否成功
	 * @param String data 返回数据或者其它消息
	 * @return String ResponsePo的json字符串
	 */
	public static String response2Json (ResponseHead responseHead,String data) {
		ResponsePo resPo = new ResponsePo();
		resPo.setCode(responseHead.getIndex());
		resPo.setMessage(responseHead.getName());
		resPo.setData(data);
		return CommonUtil.toJSONString(resPo);
	}
	
	/**
	 * 返回结果工具方法Obj
	 * @param ResponseHead responseHead 返回头部 说明请求是否成功
	 * @param String data 返回数据或者其它消息
	 * @return ResponsePo ResponsePo的Obj
	 */
	public static ResponsePo response2Obj (ResponseHead responseHead,String data) {
		ResponsePo resPo = new ResponsePo();
		resPo.setCode(responseHead.getIndex());
		resPo.setMessage(responseHead.getName());
		resPo.setData(data);
		return resPo;
	}

}
