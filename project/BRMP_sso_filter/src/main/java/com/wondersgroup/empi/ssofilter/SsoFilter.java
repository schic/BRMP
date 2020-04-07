package com.wondersgroup.empi.ssofilter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.empi.util.cipher.AES;


public class SsoFilter implements Filter {

	private static String[] excludedPages;//不拦截的url     getServletPath();

	private static String ssoUrl;
	
	private static String localUrl;
	
    public static String getSsoUrl() {
		return ssoUrl;
	}

	public static String getLocalUrl() {
		return localUrl;
	}

	public SsoFilter() { }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;//实际中,由于request的实现就是就是new HttpServerletRequest(),此处可以强转..
		HttpServletResponse resp = (HttpServletResponse) response;
		//System.out.println(req.getServletPath());//输出拦截了的路径 
		String path = req.getServletPath();//是否排除拦截
		for(String excludedPage : excludedPages){
			if (excludedPage.contains("*")){
				int x = excludedPage.indexOf('*');
				if (path.length() < x) {//路径没有排除路径的长度,不是需要排除拦截的路径
					continue;
				}
				if ( path.substring(0, x).equals(excludedPage.substring(0,x) )){
					chain.doFilter(req, response);//执行下一个
					return;
				}
			} else {
				if (path.equals(excludedPage)){
					chain.doFilter(req, response);//执行下一个
					return;
				}
			}
		}
		
		
		HttpSession session = req.getSession();//获取当前session
		
		//判断session是否有用户信息，没有不通过验证。
		String userJSON = JSON.toJSONString(session.getAttribute("ssoUser"));
		if (StringUtils.isNotEmpty(userJSON) && !"null".equals(userJSON)) {
			chain.doFilter(req, response);
			return;
		} else {
			if ( StringUtils.isEmpty(userJSON) || "null".equals(userJSON) ) {
				String qStr = req.getQueryString();//获取URL内容
				if(!StringUtils.isEmpty(qStr) && qStr.length() > 300){//加密参数内容的判断，可根据内容变化调整。
					String sessionJSON = AES.deCrypt(qStr, AES.getKey1());
					
					Map<String, Object> mapSession = (Map<String, Object>) JSON.parseObject(sessionJSON);
					if ("succeed".equals(mapSession.get("succeed"))){
						session.setAttribute("sid", mapSession.get("sid"));
						@SuppressWarnings("unchecked")
						Map<String, Object> mapAttribute = (Map<String, Object>) mapSession.get("sessionAttribute");
						for (Entry<String, Object> entry : mapAttribute.entrySet()) {
							session.setAttribute(entry.getKey(),entry.getValue());
						}
						String url = req.getRequestURL().toString();
						resp.sendRedirect(url);//将URL的参数字符串抹掉重定向
						return;
						//将URL返回的值传入session保存,再重新通过用户验证。
					}	
				} else {
				
					String nextURL = req.getRequestURL().toString();
					session.setAttribute("nextURL", nextURL);//上次访问的URL转到下一个页面
					resp.sendRedirect("http://".concat(ssoUrl).concat("/sso?nextURL=").concat(nextURL));
					return;
					
				}
				
			}
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ssoUrl = fConfig.getInitParameter("ssoUrl");
		localUrl = fConfig.getInitParameter("localUrl");
		String excludedPagesString = fConfig.getInitParameter("excludedPages");
		excludedPages = StringUtils.split(excludedPagesString, ';');
	}

}
