package com.wondersgroup.brmp.util.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * 基本工具类
 * 
 */
public class CommonUtil {
	
	/**
	 * 使用此方法配置日期格式等设置
	 * 再调用fastjson的toJSONString方法
	 * 调用此方法等同全局配置
	 */
	public static String toJSONString(Object obj){
		TypeUtils.compatibleWithFieldName =true;
		return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 判断是否为纯数字字母
	 */
	public static boolean isLetterDigit(String str) {
		String regex = "^[a-z0-9A-Z]+$";
		return str.matches(regex);
	}
	
	
}
