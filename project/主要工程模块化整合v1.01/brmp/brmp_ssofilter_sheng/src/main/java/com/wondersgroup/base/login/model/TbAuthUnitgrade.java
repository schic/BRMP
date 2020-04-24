package com.wondersgroup.base.login.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 单位等级(TB_AUTH_UNITGRADE)
 * */
@SuppressWarnings("serial")
public class TbAuthUnitgrade implements java.io.Serializable {

	private String gradekey;
	private String gradename;
	
	// 一对多，一个单位等级对应多个单位信息
	private Set tbAuthUnits = new HashSet(0);
	
	public TbAuthUnitgrade() {
		super();
	}

	public TbAuthUnitgrade(String gradekey, String gradename, Set tbAuthUnits) {
		super();
		this.gradekey = gradekey;
		this.gradename = gradename;
		this.tbAuthUnits = tbAuthUnits;
	}

	public String getGradekey() {
		return gradekey;
	}

	public void setGradekey(String gradekey) {
		this.gradekey = gradekey;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public Set getTbAuthUnits() {
		return tbAuthUnits;
	}

	public void setTbAuthUnits(Set tbAuthUnits) {
		this.tbAuthUnits = tbAuthUnits;
	}
}