package com.wondersgroup.brmp.service.intf;

/**
 * 发出请求接口，使跨网 从数据库 配置表 获取到政务网页面程序的配置内容。
 * 从而同步两边网络 传输数据，用户请求等功能。
 */
public interface BrmpSendPropertiesIntf {
	
	/**
	 * 发出请求接口。
	 * 将主数据库配置的 系统模型配置数据 和 申请等数据  传入至  从数据库 配置表 接口。
	 * @return
	 */
	String sendProperties();

}
