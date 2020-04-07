package cn.ittx.util;

import org.apache.commons.lang3.StringUtils;

public class VerifyABC {

	public static void main(String[] args) {
		//System.out.println(new VerifyABC().isEmail("dkfsdalfj"));
		System.out.println(getSystemURLString("http://localhost:8080/test_sso_system1/?dsdfdfdsfsdfds"));
	}
	
	/*
	 *验证是否是email 
	 */
	public static boolean isEmail(String email){
		String regex = "^[A-Za-z0-9]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,10}$";
	    return email.matches(regex);
	}
	
	/*
	 * 获取项目根目录的url
	 */
	public static String getSystemURLString(String url){
		int index = 0;
		if (url==null && StringUtils.isEmpty(url)){
			return "";
		}
		String fistURL = url;
		String nameURL = "";
		int b = 0;
		for(int i=0;i<3;i++){
			b = fistURL.indexOf('/')+1;
			index += b;
			fistURL = fistURL.substring(b);
			if (i == 2) {
				nameURL=fistURL;
			}
		}

		if (nameURL.indexOf('/') == -1) {
			if (nameURL.indexOf('?') == -1){
				return url;
			}
			index += nameURL.indexOf('?');
		} else {
			index += nameURL.indexOf('/');
		}
		return url.substring(0,index);
	}
	
	
}
