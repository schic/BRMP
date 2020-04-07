import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test {
	static String params = "[ {\"originId\":\"test005\",\"自定义字段1\":\"521203198706302947\",\"自定义字段2\":\"2\",\"自定义字段3\":\"自定义字段4\",\"自定义字段5\":0}, {\"originId\":\"test006\",\"自定义字段1\":\"521311197611264522\",\"自定义字段2\":\"18055556666\",\"自定义字段3\":\"1\",\"自定义字段3\":\"测试人员3\",\"自定义字段4\":0},{\"originId\":\"test007\",\"自定义字段1\":\"521203198706302947\",\"自定义字段2 \":\"2\",\"自定义字段3\":\"测试错误姓名1\",\"自定义字段4\":0},{\"originId\":\"testNull\",\"自定义字段4\":0}   ] ";

	public static void main(String[] args) {
		
		List<String> dataLists  = JSON.parseArray(params, String.class);
		for(int i=0;i<dataLists.size();i++){
			System.out.println(i+":");
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) JSON.parse(dataLists.get(i));
			for (Map.Entry<String , Object> a : map.entrySet()) {
				System.out.println("key: "+a.getKey() + " ,value: " + a.getValue());
			}
		
		}
		
		
	}
}
