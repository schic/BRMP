/**
 * 创建日期：Nov 1, 2012
 * 作者： "王胤洪"
 * 版权： 指明该文件的版权信息
 * 功能： 指明该文件所实现的功能
 */

package com.wondersgroup.base.login.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.wondersgroup.base.login.model.AuthConstants;
import com.wondersgroup.base.login.model.AuthInfo;


/**
 * 从SESSION中获取信息的工具类
 * @author "王胤洪"
 *
 */
public class SessionUtil {

	private static String ssowsUrl;

	/**
	 * 
	 * 功能：在SESSION中保存登录用户信息的当前的用户
	 * 创建日期：Nov 21, 2013
	 * @author： "华翔宇"
	 * @param request
	 * @param user
	 * @throws Exception
	 * void
	 * 如果该函数引用或修改了某些全局变量或对象，也应在函数级注释中说明
	 * ---------------------------------------
	 */
	public static void setCurrAuthInfo(HttpServletRequest request, AuthInfo authInfo) throws Exception {
		Assert.notNull(request);
		request.getSession(true).setAttribute(AuthConstants.SESSION_USER_CURRENT_INFO, authInfo);
	}


	/**
	 * 
	 *
	 * @描述：获取当前用户的基本信息
	 *
	 * @param request
	 * @return
	 * AuthInfo
	 * @创建人  ：acer
	 * @创建时间：2014-10-23下午4:29:34
	 * @修改人  ：acer
	 * @修改时间：2014-10-23下午4:29:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static AuthInfo getCurrAuthInfo(HttpServletRequest request) {
		Assert.notNull(request);
		return (AuthInfo) request.getSession(true).getAttribute(AuthConstants.SESSION_USER_CURRENT_INFO);
	}

	/**
	 * 
	 *
	 * @描述：将登陆用户相关联保存进session
	 *
	 * @param request
	 * @param authInfoList
	 * void
	 * @创建人  ：kh
	 * @创建时间：2014-10-26下午8:51:56
	 * @修改人  ：kh
	 * @修改时间：2014-10-26下午8:51:56
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static void setLoginUsers(HttpServletRequest request, List<AuthInfo> authInfoList) {
		Assert.notNull(request);
		request.getSession(true).setAttribute(AuthConstants.SESSION_USER_INFO, authInfoList);
	}

	/**
	 * 
	 *
	 * @描述：将登陆用户所有的相关信息取得
	 *
	 * @param request
	 * @return
	 * List<AuthInfo>
	 * @创建人  ：kh
	 * @创建时间：2014-10-26下午8:54:37
	 * @修改人  ：kh
	 * @修改时间：2014-10-26下午8:54:37
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public static List<AuthInfo> getLoginUsers(HttpServletRequest request) {
		Assert.notNull(request);
		return (List<AuthInfo>) request.getSession(true).getAttribute(AuthConstants.SESSION_USER_INFO);
	}

	/**
	 * 
	 *
	 * @描述：存放工程名或者工程根节点的资源ID
	 *
	 * @param request
	 * @param nameOrID
	 * void
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static void setProjectNameOrID(HttpServletRequest request, String nameOrID) {
		Assert.notNull(request);
		request.getSession(true).setAttribute(AuthConstants.PROJECT_NAME, nameOrID);
	}

	public static String getProjectNameOrID(HttpServletRequest request) {
		Assert.notNull(request);
		return (String) request.getSession(true).getAttribute(AuthConstants.PROJECT_NAME);
	}

	/**
	 * 在SESSION中放置对象
	 * 
	 * @author LiaoChangjun
	 * 
	 *         创建时间：2013-2-25 下午03:09:27
	 * @return
	 */

	public static void put(HttpServletRequest request, String key, Object obj) {
		Assert.notNull(request);
		request.getSession(true).setAttribute(key, obj);
	}

	/**
	 * 
	 * @Title: 保存对象到SESSION
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param request
	 * @param key
	 * @return Object
	 */
	public static Object get(HttpServletRequest request, String key) {

		return request.getSession(true).getAttribute(key);
	}

	public static String getSsowsUrl() {
		return ssowsUrl;
	}

	public static void setSsowsUrl(String ssowsUrl) {
		SessionUtil.ssowsUrl = ssowsUrl;
	}
}
