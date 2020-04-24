/**
 * 创建日期：Nov 16, 2012
 * 作者： "王胤洪"
 * 版权： 指明该文件的版权信息
 * 功能： 指明该文件所实现的功能
 */

package com.wondersgroup.base.login.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;

import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.base.login.web.LoginController;


public class LoginFilter implements Filter {

	private String myMatchUrl = "";

	private String returnPage = "";

	private String notLoginUrl = "";

	private String[] arrayMyMatchUtl;

	private LoginController loginAction;

	/**
	 * 内网地址
	 */
	private static List<String> innerUrl;

	public static List<String> getInnerUrl() {
		return innerUrl;
	}

	public static void setInnerUrl(List<String> innerUrl) {
		LoginFilter.innerUrl = innerUrl;
	}

	/**
	 * @return the loginAction
	 */
	public LoginController getLoginAction() {
		return loginAction;
	}

	/**
	 * @param loginAction
	 *          the loginAction to set
	 */

	public void setLoginAction(LoginController loginAction) {
		this.loginAction = loginAction;
	}

	/**
	 * 
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * 功能：描述该函数所实现的功能（不要描述如何实现这些功能） 创建日期：Aug 9, 2011
	 * 
	 * @author： "王胤洪"
	 * @return 如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 *         ---------------------------------------
	 */
	public String getMyMatchUrl() {
		return myMatchUrl;
	}

	/**
	 * 
	 * 功能：描述该函数所实现的功能（不要描述如何实现这些功能） 创建日期：Aug 9, 2011
	 * 
	 * @author： "王胤洪"
	 * @param myMatchUrl
	 *          如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 *          ---------------------------------------
	 */
	public void setMyMatchUrl(String myMatchUrl) {
		this.myMatchUrl = myMatchUrl;
	}

	/**
	 * 
	 * 功能：描述该函数所实现的功能（不要描述如何实现这些功能） 创建日期：Aug 9, 2011
	 * 
	 * @author： "王胤洪"
	 * @return 如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 *         ---------------------------------------
	 */
	public String getReturnPage() {
		return returnPage;
	}

	/**
	 * 
	 * 功能：描述该函数所实现的功能（不要描述如何实现这些功能） 创建日期：Aug 9, 2011
	 * 
	 * @author： "王胤洪"
	 * @param returnPage
	 *          如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 *          ---------------------------------------
	 */
	public void setReturnPage(String returnPage) {
		this.returnPage = returnPage;
	}

	/**
	 * 
	 * 功能：描述该函数所实现的功能（不要描述如何实现这些功能） 创建日期：Aug 9, 2011
	 * 
	 * @author： "王胤洪"
	 * @return 如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 *         ---------------------------------------
	 */
	public String getNotLoginUrl() {
		return notLoginUrl;
	}

	/**
	 * 
	 * 功能：描述该函数所实现的功能（不要描述如何实现这些功能） 创建日期：Aug 9, 2011
	 * 
	 * @author： "王胤洪"
	 * @param notLoginUrl
	 *          如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 *          ---------------------------------------
	 */
	public void setNotLoginUrl(String notLoginUrl) {
		this.notLoginUrl = notLoginUrl;
	}

	/**
	 * 过滤器
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestUri = httpRequest.getRequestURI().toUpperCase();
		String rootPath = httpRequest.getContextPath();

		boolean match = false;

		if (!ArrayUtils.isEmpty(this.arrayMyMatchUtl)) {
			for (int i = 0; i < this.arrayMyMatchUtl.length; i++) {
				if (requestUri.matches(this.arrayMyMatchUtl[i])) {
					match = true;
					break;
				}
			}
		}

		if (match) {
			chain.doFilter(request, response);
			return;
		}

		Object cuser = null;
		try {
			cuser = SessionUtil.getCurrAuthInfo((HttpServletRequest) request);//. ((HttpServletRequest) request).getSession().getAttribute("user");
		} catch (Exception e) {

		}

		// 已经登录或正在登录，则继续
		if (cuser != null) {
			chain.doFilter(request, response);
		} else {
			// 非登录状态,且单点登录已经完成，需要去做一次登录验证
			if ((loginAction != null) && (((HttpServletRequest) request).getRemoteUser() != null)
					&& (!"".equals(((HttpServletRequest) request).getRemoteUser()))) {
				try {
					// 登录成功
					if ("SUCCESS".equalsIgnoreCase(loginAction.login(request, response))) {
						chain.doFilter(request, response);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					String message = e.getMessage();

					/**
					 * 输出错误信息，同时跳转到登录页面（系统本身页面/单点登录页面）
					 */
					HttpServletResponse httpResponse = (HttpServletResponse) response;
					httpResponse.reset();
					httpResponse.resetBuffer();
					((HttpServletResponse) response).setHeader("Content-type", "text/html;charset=UTF-8");
					PrintWriter printWriter = httpResponse.getWriter();
					printWriter.println("<html>");
					printWriter.println("<head><title></title></head>");
					printWriter.println("<body>");
					printWriter.println("<SCRIPT LANGUAGE=\"JavaScript\">");
					printWriter.println("window.onload=function(){");
					printWriter.println("alert('" + message + ",需要注销单点登录！');window.location =\"ssoLogout.action\"}");//
					printWriter.println("</SCRIPT>");
					printWriter.println("</body>");
					printWriter.println("</html>");
					printWriter.flush();

					//CASFilter.logOut((HttpServletRequest) request, (HttpServletResponse) response);
					return;
				}
			}

			// XML或AJAX请求，返回json格式数据
			if ((null != httpRequest.getHeader("X-Requested-With"))
					&& (httpRequest.getHeader("X-Requested-With").equals("XMLHttpRequest"))) {
				((HttpServletResponse) response).setStatus(500);
				String result = "{\"success\":false, errors:[{\"msg\":\"用户登陆已过期，请重新登陆\"}]" + ",target:\"" + returnPage
						+ "\"} ";
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(result);
			} else {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.reset();
				httpResponse.sendRedirect("toLogin");
			}
			return;
		}
	}

	public void init(FilterConfig config) throws ServletException {
		if (myMatchUrl == null || "".equals(myMatchUrl)) {

			myMatchUrl = config.getInitParameter("myMatchUrl");
			if (myMatchUrl != null) {
				myMatchUrl = myMatchUrl.toUpperCase();
			}
		}
		if (returnPage == null || "".equals(returnPage)) {
			returnPage = config.getInitParameter("returnPage");
			if (returnPage == null || "".equals(returnPage)) {
				returnPage = "/login.jsp";
			}
		}
		if (arrayMyMatchUtl == null && myMatchUrl != null) {
			arrayMyMatchUtl = myMatchUrl.trim().split(",");
		}
	}

	/**
	 * 
	 *
	 * @描述：内网时返回true,外网返回false
	 *
	 * @param request
	 * @return
	 * boolean
	 * @创建人  ：KouHao
	 * @创建时间：2014-4-2下午06:33:52
	 * @修改人  ：KouHao
	 * @修改时间：2014-4-2下午06:33:52
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static boolean getRealCasPath(HttpServletRequest request) {
		String scheme = request.getScheme();
		String serverName = request.getHeader("host");
		if (serverName.indexOf(":") > -1) {
			serverName = serverName.split(":")[0];
		}
		int serverPort = request.getServerPort();
		// 如果配置的是用相对地址（relativeAddress="true"）对请求的地址不最任何改变，反之要读取redirectAddressMap里面想要的映射关系
		if (innerUrl != null && innerUrl.size() > 0) {
			for (String i : innerUrl) {
				Pattern pattern = Pattern.compile(i);
				Matcher isTrue = pattern.matcher(serverName);
				if (isTrue.matches()) {
					return true;
				}
			}
		}
		return false;
	}
}
