package com.wondersgroup.empi.util.daoutil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.wondersgroup.empi.po.empipo.EMPIObj;

public class ParamMapUtil {
	
	/**
	 * 根据对象类生成对象字段的Map参数用于保存至数据库
	 * @param obj
	 * @return 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	public static Map<String, Object> getParamMap(Object obj) throws IllegalArgumentException, IllegalAccessException, ParseException{
		//setNull(obj);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			paramMap.put(field.getName(), field.get(obj) );
		}
		return paramMap;
	}

	/**
	 * 替换对象数据的空值 为默认值,用于保存至数据库不用报错
	 * @param obj
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	public static void setNull(Object obj) throws IllegalArgumentException, IllegalAccessException, ParseException {
		String normString = "-" ;
		String normDate = "1900-01-01";
		String value;
		Type genericType;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);//不检查 直接取值
			genericType = field.getGenericType();
			if ( genericType.equals(String.class) ) {
				value = (String) field.get(obj);
				if (StringUtils.isBlank(value)) {//将空值替换
					field.set(obj,normString);
	  			}
			} else if (genericType.equals(Date.class) ) {
				if (null == field.get(obj)) {//如果是时间类型为空则返回一个默认日期，避免返回空字符串格式报错
					field.set(obj,DateUtils.parseDate(normDate,"yyyy-MM-dd"));
				}
			}
		}
	}
	
	/**
	 * 测试 getParamMap 
	 * @param args
	 */
	public static void main(String[] args) {
		EMPIObj empiObj = new EMPIObj();
		/*
		try {
			empiObj.setCsrq(DateUtils.parseDate("2018-09-08", "yyyy-MM-dd"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		*/
		empiObj.setJtdz("test陈");
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			setNull(empiObj);
			map = getParamMap(empiObj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
		
	}

}
