package com.wondersgroup.brmp.po.dicpo;

import com.wondersgroup.brmp.util.anotation.Table;

/**
 * 值域字典数据项
 * 表名：brmp_dictionary_item
 */
@Table(name="brmp_dictionary_item")
public class DictionaryItem {
	
	private String dicId;//字典ID
	private String originSystemId;//源系统名称编号
	private String dicCode;//字典编码名称
	private String code;//值域代码
	private String name;//值域名称
	private int displayOrder;//展示顺序
	
	public String getDicId() {
		return dicId;
	}
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	public String getOriginSystemId() {
		return originSystemId;
	}
	public void setOriginSystemId(String originSystemId) {
		this.originSystemId = originSystemId;
	}
	public String getDicCode() {
		return dicCode;
	}
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	

}
