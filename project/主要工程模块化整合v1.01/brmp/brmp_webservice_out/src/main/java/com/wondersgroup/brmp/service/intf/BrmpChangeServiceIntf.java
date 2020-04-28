package com.wondersgroup.brmp.service.intf;

public interface BrmpChangeServiceIntf {
	
	/**
	 * 将目前网络的数据库模型数据传入 对应互通另一网络的接口，达成数据同步互通。
	 * @return
	 */
	String sendModelData();

}
