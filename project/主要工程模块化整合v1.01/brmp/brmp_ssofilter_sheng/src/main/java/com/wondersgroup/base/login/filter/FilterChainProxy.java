package com.wondersgroup.base.login.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.acegisecurity.intercept.web.FilterInvocation;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wondersgroup.sso.client.session.CasSingleLogoutClusterFilter;

public class FilterChainProxy extends AbstractConfigurationFilter {

	Logger logger = LoggerFactory.getLogger(FilterChainProxy.class);
	private String[] myMatchUrl;//不经过单点登录的路径
	private Filter[] filters;
	private static Map<String, String> redirectStaticAddressMap;
	private static String casStaticServerLogoutUrl;
	private static String localStaticPortalIndexUrl;
	private List<String> peerURLs;
	private String serverName;
	private String service;
	private String casServerLoginUrl;
	private String casServerUrlPrefix;
	private Map<String, String> redirectAddressMap;
	private String casServerLogoutUrl;
	private String localPortalIndexUrl = "index";
	
	
	/* (non-Javadoc) 
	 * @see javax.servlet.Filter#destroy() 
	 */
	public void destroy() {

		for (int i = 0; i < filters.length; i++) {
			if (filters[i] != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Destroying Filter defined in ApplicationContext: '" + filters[i].toString() + "'");
				}

				filters[i].destroy();
			}
		}

	}



	/* (non-Javadoc) 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);

		if (filters.length == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug(fi.getRequestUrl() + " has an empty filter list");
			}
			chain.doFilter(request, response);

			return;
		}

		VirtualFilterChain virtualFilterChain = new VirtualFilterChain(fi, filters);
		virtualFilterChain.doFilter(fi.getRequest(), fi.getResponse());

	}

	/* (non-Javadoc) 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		filters = obtainAllDefinedFilters();
		FilterChainProxy.casStaticServerLogoutUrl = this.casServerLogoutUrl;
		FilterChainProxy.localStaticPortalIndexUrl = this.localPortalIndexUrl;
		FilterChainProxy.redirectStaticAddressMap = this.redirectAddressMap;

		for (int i = 0; i < filters.length; i++) {
			if (filters[i] != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Initializing Filter defined in ApplicationContext: '" + filters[i].toString() + "'");
				}
				filters[i].init(filterConfig);
			}
		}

	}

	/** 
	 * 初始化单点登录需要的过滤器 
	 * @return 
	 */
	private Filter[] obtainAllDefinedFilters() {
		
		Set<Filter> list = new LinkedHashSet<Filter>();
		
		// CasSingleLogoutClusterFilter
		if(peerURLs!=null){
			list.add(new CasSingleLogoutClusterFilter(peerURLs));
		}
		// SingleSignOutFilter  
		list.add(new SingleSignOutFilter());
		// AuthenticationFilter  
		list.add(new CASFilter(serverName, service, casServerLoginUrl, casServerLogoutUrl));////new AuthenticationFilter()
		// Cas20ProxyReceivingTicketValidationFilter  
		list.add(new ValidationFilter(serverName, service, casServerUrlPrefix));//new Cas20ProxyReceivingTicketValidationFilter()
		// AssertionThreadLocalFilter  
		list.add(new AssertionThreadLocalFilter());
		// HttpServletRequestWrapperFilter  
		list.add(new HttpServletRequestWrapperFilter());

		return (Filter[]) list.toArray(new Filter[0]);
	}

	public String[] getMyMatchUrl() {
		return myMatchUrl;
	}

	public void setMyMatchUrl(String[] myMatchUrl) {
		this.myMatchUrl = myMatchUrl;
	}


	public static Map<String, String> getRedirectStaticAddressMap() {
		return redirectStaticAddressMap;
	}

	public static void setRedirectStaticAddressMap(Map<String, String> redirectStaticAddressMap) {
		FilterChainProxy.redirectStaticAddressMap = redirectStaticAddressMap;
	}

	public List<String> getPeerURLs() {
		return peerURLs;
	}

	public void setPeerURLs(List<String> peerURLs) {
		this.peerURLs = peerURLs;
	}

	public static String getCasStaticServerLogoutUrl() {
		return casStaticServerLogoutUrl;
	}

	public static void setCasStaticServerLogoutUrl(String casStaticServerLogoutUrl) {
		FilterChainProxy.casStaticServerLogoutUrl = casStaticServerLogoutUrl;
	}

	public static String getLocalStaticPortalIndexUrl() {
		return localStaticPortalIndexUrl;
	}

	public static void setLocalStaticPortalIndexUrl(String localStaticPortalIndexUrl) {
		FilterChainProxy.localStaticPortalIndexUrl = localStaticPortalIndexUrl;
	}

	public Map<String, String> getRedirectAddressMap() {
		return redirectAddressMap;
	}

	public void setRedirectAddressMap(Map<String, String> redirectAddressMap) {
		this.redirectAddressMap = redirectAddressMap;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	public String getLocalPortalIndexUrl() {
		return localPortalIndexUrl;
	}


	public String getCasServerLogoutUrl() {
		return casServerLogoutUrl;
	}

	public void setCasServerLogoutUrl(String casServerLogoutUrl) {
		this.casServerLogoutUrl = casServerLogoutUrl;
	}

	public void setLocalPortalIndexUrl(String localPortalIndexUrl) {
		this.localPortalIndexUrl = localPortalIndexUrl;
	}

	@SuppressWarnings("unused")
	public static String[] getRealCasPath(HttpServletRequest request) {
		String scheme = request.getScheme();
		String serverName = request.getHeader("host");
		int serverPort = request.getServerPort();

		if ((redirectStaticAddressMap.get("relativeAddress") != null)
				&& (!"true".equals(redirectStaticAddressMap.get("relativeAddress")))) {
			serverName = (String) redirectStaticAddressMap.get(serverName);
		}
		String realCasPath = scheme + "://" + serverName;
		String[] realCasPaths = { realCasPath, request.getHeader("host") };
		return realCasPaths;
	}

	@SuppressWarnings({ "deprecation" })
	public static void logOut(HttpServletRequest request, HttpServletResponse response) {
		String realCasPath = getRealCasPath(request)[0];
		try {
			if ((casStaticServerLogoutUrl != null) && (!"".equals(casStaticServerLogoutUrl.trim())))
				if ((localStaticPortalIndexUrl != null) && (!"".equals(localStaticPortalIndexUrl.trim()))) {
					if ((localStaticPortalIndexUrl.trim().indexOf(":") < 1)
							&& (URLDecoder.decode(localStaticPortalIndexUrl).indexOf(":") < 1)) {
						String[] rs = request.getRequestURI().split("/");
						String appPath = "/" + rs[1];

						if ((!localStaticPortalIndexUrl.toUpperCase().startsWith(appPath.toUpperCase()))
								&& (!localStaticPortalIndexUrl.startsWith("/"))) {
							appPath = appPath + "/";
						}

						response.sendRedirect(realCasPath
								+ casStaticServerLogoutUrl
								+ "?service="
								+ URLEncoder.encode(new StringBuilder(request.getScheme()+"://"+request.getHeader("host")+String.valueOf(appPath)).append("").toString()));
						return;
					}

					response.sendRedirect(realCasPath + casStaticServerLogoutUrl + "?service=" + URLEncoder.encode(""));
				} else {
					response.sendRedirect(realCasPath + casStaticServerLogoutUrl);
				}
		} catch (Exception e) {
			System.out.println("[" + new Date().toLocaleString() + "]sso logout failed:" + e.getMessage());
		}
	}

	public static String getRemoteHost(javax.servlet.http.HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}
