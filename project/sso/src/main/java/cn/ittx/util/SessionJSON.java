package cn.ittx.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

/**
 * 根据request的session提取需要的数据保存成JSON格式
 */
public class SessionJSON {

	public static String getSessionJSON(HttpSession session) {
		Map<String, Object> mapAttribute = new HashMap<>();
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object obj = session.getAttribute(name);
			mapAttribute.put(name, obj);
		}
		
		Map<String, Object> mapSession = new HashMap<>();
		mapSession.put("succeed","succeed");
		mapSession.put("sid", session.getId());
		mapSession.put("sessionAttribute", mapAttribute);
		
		String str = JSON.toJSONString(mapSession);
		
		return str;
	}

}
