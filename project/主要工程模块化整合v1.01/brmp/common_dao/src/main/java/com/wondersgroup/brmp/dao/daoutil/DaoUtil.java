package com.wondersgroup.brmp.dao.daoutil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wondersgroup.brmp.util.anotation.Column;

/**
 * Dao工具
 */
public class DaoUtil {
	
	 /**
	  * 下划线转驼峰法
	  * @param line 源字符串
	  * @param smallCamel 大小驼峰,是否为小驼峰
	  * @return 转换后的字符串
	  */
	public static String underlineToCamel(String line,boolean smallCamel){
		if(line==null||"".equals(line)){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher=pattern.matcher(line);
		while(matcher.find()){
			String word=matcher.group();
			sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
			int index=word.lastIndexOf('_');
			if(index>0){
				sb.append(word.substring(1, index).toLowerCase());
			}else{
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}
	
	 /**
	  * 驼峰法转下划线
	  * @param line 源字符串
	  * @return 转换后的字符串
	  */
	public static String camelToUnderline(String line){
		if(line==null||"".equals(line)){
			return "";
		}
		line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb=new StringBuffer();
		Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher=pattern.matcher(line);
		while(matcher.find()){
			String word=matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end()==line.length()?"":"_");
		}
		return sb.toString();
	}
	
	
	/**
	 * 类转map
	 * @param object
	 * @return
	 */
	public static Map<String,Object> object2Map(Object object){
		Map<String,Object> result=new HashMap<String,Object>();
		//获得类的的属性名 数组
		Field[]fields=object.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				String name = new String(field.getName());
				
				Column column = field.getAnnotation(Column.class);
				if (null != column){
					if ("".equals(column.name())){//若字段名为""，则在数据库中不使用该字段
						continue;
					} else {
						name = column.name().toLowerCase();
					}	
				}
				
				result.put(name, field.get(object));
	 
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
