package com.wondersgroup.brmp.util.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.wondersgroup.brmp.po.empipo.ModelDataAttribute;


/**
 * 用于验证页面申请配置的数据的有效性
 *
 */
public class ViewDataVerifyUtil {
	
	/**
	 * 验证页面的建立的模型字段数据
	 */
	public static String verifyModelDataAttributes(List<ModelDataAttribute> modelDataAttributes){
		//TODO 准备添加(人员或物)主索引字段 选择，判断是否是索引模型
		//TODO 准备添加值域字段，判断是否是需要值域转码的模型
		
		List<String> modelColNames = new ArrayList<String>(); 
		ModelDataAttribute modelDataAttribute;
		boolean hasPk = false;
		for(int i=0;i<modelDataAttributes.size();i++){
			modelDataAttribute = modelDataAttributes.get(i);
			if (modelDataAttribute.getPk()==1) {
				hasPk = true;//最后确认是否有主键字段定义
			}
			
			String modelColName = modelDataAttribute.getModelColName();
			if(!modelColName.equals(modelColName.trim())) {
				return "字段不能包含空格:".concat(modelColName);
			}
			if(NumberUtils.isNumber(String.valueOf(modelColName.charAt(0))) ){
				return "字段不能以数字开头:".concat(modelColName);
			}
			if("_".equals(String.valueOf(modelColName.charAt(0)))){
				return "字段不能以'_'开头";
			}
			if( !CommonUtil.isLetterDigit(modelColName.replace('_', '0')) ){
				return "字段只能是字母或数字或'_'的组合:".concat(modelColName);
			}
			
			for(int j=0;j<modelColNames.size();j++){
				if (modelColNames.get(j).equalsIgnoreCase(modelColName) ){
					return "字段重复:".concat(modelColName);
				}
			}
			modelColNames.add(modelColName);//for循环验证字段是否有重复
			
			if (modelDataAttribute.getModelColType()==0){//String 字符串类型字段长度应该在 1~3999之间
				
				modelDataAttribute.setModelColDecimalLenth(-1);//不使用小数
				if (modelDataAttribute.getModelColLenth() < 1 || modelDataAttribute.getModelColLenth() > 4000) {
					return "字段长度不在范围(".concat(modelColName).concat("):应该在 1~4000之间");
				} 
			} else if (modelDataAttribute.getModelColType()==1) {//int类型字段长度 1~8 之间
				
				modelDataAttributes.get(i).setModelColDecimalLenth(-1);//不使用小数位数
				if (modelDataAttribute.getModelColLenth() < 1) {
					return "字段长度不在范围(".concat(modelColName).concat("):应该在 1~8之间,长度超过8请使用long型");
				} else if (modelDataAttribute.getModelColLenth() > 8) {
					return modelColName.concat("字段长度超过8位，建议使用long型");
				}
			} else if (modelDataAttribute.getModelColType()==2) {//float
				
				if (modelDataAttribute.getModelColLenth() < 2 || modelDataAttribute.getModelColLenth() > 7) {
					return "字段长度不在范围(".concat(modelColName).concat("):应该在 2~7之间,长度超过7请使用double");
				}
				if (modelDataAttribute.getModelColDecimalLenth() < 1 ||  modelDataAttribute.getModelColDecimalLenth() >= modelDataAttribute.getModelColLenth() ){
					return "字段长度不在范围(".concat(modelColName).concat("):小数位数不在合适的范围");
				}
			} else if (modelDataAttribute.getModelColType()==3) {//Date
				
				modelDataAttributes.get(i).setModelColLenth(-1);//不使用字段长度
				modelDataAttributes.get(i).setModelColDecimalLenth(-1);//不使用小数位数
			} else if (modelDataAttribute.getModelColType()==4) {//long
				
				modelDataAttributes.get(i).setModelColDecimalLenth(-1);//不使用小数位数
				if (modelDataAttribute.getModelColLenth() < 9 || modelDataAttribute.getModelColLenth() > 19) {
					return "字段长度不在范围(".concat(modelColName).concat("):应该在 9~19之间");
				}
			} else if (modelDataAttribute.getModelColType()==5) {//double  2^52=4503599627370496 【15】
				if (modelDataAttribute.getModelColLenth() < 7 || modelDataAttribute.getModelColLenth() > 19) {
					return "字段长度不在范围(".concat(modelColName).concat("):应该在 7~19之间");
				}
				if (modelDataAttribute.getModelColDecimalLenth() < 1 ||  modelDataAttribute.getModelColDecimalLenth() >= modelDataAttribute.getModelColLenth() ){
					return "字段长度不在范围(".concat(modelColName).concat("):小数位数不在合适的范围");
				}
			} else if (modelDataAttribute.getModelColType()==6) {//String(n) nvarchar2 字符串类型字段长度应该在 1~2000之间
				modelDataAttribute.setModelColDecimalLenth(-1);//不使用小数
				if (modelDataAttribute.getModelColLenth() < 1 || modelDataAttribute.getModelColLenth() > 2000) {
					return "字段长度不在范围(".concat(modelColName).concat("):应该在 1~2000之间");
				} 
			}
			
			modelDataAttributes.get(i).setDisplayOrder(i+1);//展示顺序排列好
		}
		if(!hasPk){
			return "没有定义主键字段";
		}
		
		
		return "pass";
	}
	

}
