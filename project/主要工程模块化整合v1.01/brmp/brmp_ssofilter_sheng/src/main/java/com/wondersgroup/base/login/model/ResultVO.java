package com.wondersgroup.base.login.model;

import java.io.Serializable;

/**
 * 
 * @描述:返回给前台AJAX调用的结果VO
 * @创建人：Hutao(hutao02@wondersgroup.com) 
 * @创建时间:2014-10-10 上午09:16:20   
 * @修改人:Hutao(hutao02@wondersgroup.com)   
 * @修改时间:2014-10-10 上午09:16:20   
 * @修改备注:   
 * @version 1.0
 *
 */
public class ResultVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2527254854666673613L;
	/**
	 * 返回给客户端的结果
	 */
	private String result;
	/**
	 * 返回给客户端的提示信息
	 */
	private String message;
	/**
	 * 返回给客户端的查询数据
	 */
	private Object data;

	/**
	 * 构造函数
	 */
	public ResultVO() {
		super();
	}

	/**
	 * 构造函数
	 * @param data 数据
	 */
	public ResultVO(Object data) {
		super();
		this.result = ResultConstant.RESULT_SUCCESS;
		this.message = ResultConstant.MESSAGE_SUCCESS;
		this.data = data;
	}

	/**
	 * 构造函数
	 * @param result 执行结果
	 * @param message 信息
	 * @param data 数据
	 */
	public ResultVO(String result, String message, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
