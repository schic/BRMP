package com.wondersgroup.base.login.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.base.login.filter.FilterChainProxy;
import com.wondersgroup.base.login.model.AuthConstants;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.base.login.model.ResultVO;
import com.wondersgroup.base.login.model.VisitLogs;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.service.MenuService;
import com.wondersgroup.base.login.service.VisitLogsService;
import com.wondersgroup.base.login.util.BeanUtil;
import com.wondersgroup.base.login.util.MD5util;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.core.hibernate.PageRequest;

@Controller
public class LoginController {

	/*
	 * 日志
	 */
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private VisitLogsService visitLogsService;

	/**
	 * 
	 *
	 * @描述：跳转到登录页面
	 *
	 * @param request
	 * @param model
	 * @return
	 * String
	 * @创建人  ：kh
	 * @创建时间：2014-10-22下午1:52:35
	 * @修改人  ：kh
	 * @修改时间：2014-10-22下午1:52:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value="toLogin")
	public String toLogin(HttpServletRequest request, Model model) {
		return "login";

	}

	/**
	 * 
	 *
	 * @描述：跳转到工程主体框架页面
	 *
	 * @param request
	 * @param model
	 * @param response
	 * @param projectName---工程名
	 * @return
	 * String
	 * @创建人  ：kh
	 * @创建时间：2014-10-22下午1:56:12
	 * @修改人  ：kh
	 * @修改时间：2014-10-22下午1:56:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value = "toIndex")
	public String toIndex(HttpServletRequest request, Model model, HttpServletResponse response, String projectName) {
		//step1 获取顶部与左侧树菜单
		return "layout/index";

	}

	/**
	 * 
	 *
	 * @描述：获取菜单
	 *
	 * @param model
	 * @param projectName---工程名
	 * @param request
	 * @return
	 * @throws Exception
	 * Model
	 * @创建人  ：kh
	 * @创建时间：2014-10-23下午2:20:12
	 * @修改人  ：kh
	 * @修改时间：2014-10-23下午2:20:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value = "/getResources")
	@ResponseBody
	public Map<String, Object> getResources(HttpServletRequest request, Model model, String resId) throws Exception {
		String systemName = ""; // 登录系统名
		// 获取当前登录的用户的相关信息
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		Map<String, Object> map = new HashMap<String, Object>();
		/**
		 * 通过用户名，机构ID来获取顶部菜单
		 */
		List<AuthResource> menuReourceBeans = null;
		try {
			AuthResource resources = menuService
					.getResources(authInfo.getLoginName(), authInfo.getOrganId(), resId, "");
			systemName = resources.getResName();
			AuthResource newresources = (AuthResource) BeanUtil.cloneObject(resources);
			//BeanUtils.copyProperties(newresources, resources);
			newresources = menuService.removeSomeResources(newresources);
			SessionUtil.setProjectNameOrID(request, resId);
			/**
			 * 获取子菜单list
			 */
			if (newresources != null && newresources.getChildRes() != null) {
				menuReourceBeans = newresources.getChildRes();
			} else {
				menuReourceBeans = new ArrayList<AuthResource>();
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		//		menuReourceBeans = new ArrayList<AuthResource>();
		map.put("menuResource", menuReourceBeans);
		map.put("user", SessionUtil.getCurrAuthInfo(request));
		map.put("sysName", systemName);
		//系统版本号
		String version = menuService.getVersionByResid(resId);
		map.put("version", version);
		return map;
	}

	/**
	 * 
	 *
	 * @描述：对系统进行登录
	 *
	 * @param request
	 * @param response
	 * @return
	 * String
	 * @创建人  ：kh
	 * @创建时间：2014-10-23下午2:21:30
	 * @修改人  ：kh
	 * @修改时间：2014-10-23下午2:21:30
	 * @修改备注：
	 * @version 1.0
	 * @throws Exception 
	 *
	 */
	public String login(ServletRequest request, ServletResponse response) throws Exception {
		AuthInfo authInfo = new AuthInfo();
		if (StringUtils.isBlank(((HttpServletRequest) request).getRemoteUser())) {
			//对登录进行验证
			if (!validateLogin(request, response)) {
				return "FALSE";
			}
			authInfo.setLoginName(request.getParameter("logname"));
			authInfo.setOrganId(request.getParameter("orgId"));//获取机构ID
		} else {
			Assertion assertion = AssertionHolder.getAssertion();
			AttributePrincipal ap = assertion.getPrincipal(); //获取AttributePrincipal对象，这是客户端对象
			authInfo.setLoginName(ap.getName());//获取用户名
			authInfo.setOrganId((String) ap.getAttributes().get("orgId"));
		}
		sessionInitOpt(((HttpServletRequest) request), authInfo);
		return "SUCCESS";
	}

	/**
	 * 
	 *
	 * @描述：验证登录名与登录密码、登录机构ID
	 *
	 * @return
	 * boolean
	 * @创建人  ：kh
	 * @创建时间：2014-10-28下午4:39:06
	 * @修改人  ：kh
	 * @修改时间：2014-10-28下午4:39:06
	 * @修改备注：
	 * @version 1.0
	 * @param response 
	 * @param request 
	 *
	 */
	private boolean validateLogin(ServletRequest request, ServletResponse response) {
		return false;
	}

	/**
	 * 
	 *
	 * @描述：对session进行初始化
	 *
	 * @param request
	 * @throws Exception
	 * void
	 * @创建人  ：kh
	 * @创建时间：2014-10-23上午10:14:26
	 * @修改人  ：kh
	 * @修改时间：2014-10-23上午10:14:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void sessionInitOpt(HttpServletRequest request, AuthInfo authInfo) throws Exception {

		log.info("用户[" + authInfo.getLoginName() + "]开始用户初始化!");
		/**
		 * 获取用户的信息，并将用户信息加入session
		 * 通过用户名与机构ID来获取对应的登录人信息
		 * step1 查出登录人信息，将该信息存入session
		 */
		List<AuthInfo> authInfoList = authService.getAuthUserInfo(authInfo.getLoginName(), authInfo.getOrganId());
		if (authInfoList == null || authInfoList.size() < 1) {
			//throw new Exception("登录信息不匹配！");
		} else {
			//将所有的用户相关信息保存入session
			SessionUtil.setLoginUsers(request, authInfoList);
		}
		/**
		 * step2 获取当前的用户,将该信息存入session
		 */
		AuthInfo currentAuthInfo = null;
		for (AuthInfo auIn : authInfoList) {
			if (auIn.getLoginName().equals(authInfo.getLoginName()) && auIn.getOrganId().equals(authInfo.getOrganId())) {
				currentAuthInfo = auIn;
				break;
			}
		}
		if (currentAuthInfo == null) {
			//throw new Exception("登录信息不匹配！");
		} else {
			//将当前用户相关信息保存人session
			SessionUtil.setCurrAuthInfo(request, currentAuthInfo);
		}
		SessionUtil.setProjectNameOrID(request, request.getParameter("resId"));
		log.info("用户[" + authInfo.getLoginName() + "]结束用户初始化!");
	}

	/**
	 * 
	 *
	 * @描述：系统的注销
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 * @创建人  ：kh
	 * @创建时间：2014-10-23上午10:19:23
	 * @修改人  ：kh
	 * @修改时间：2014-10-23上午10:19:23
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value = "/logout")
	public void logout(ServletRequest request, ServletResponse response) throws Exception {
		try {
			System.out.println("注销用户[" + SessionUtil.getCurrAuthInfo((HttpServletRequest) request).getLoginName()
					+ "]……");
			((HttpServletRequest) request).getSession().invalidate();
			((HttpServletRequest) request).getSession().removeAttribute(AuthConstants.SESSION_USER_INFO);
			((HttpServletRequest) request).getSession().removeAttribute(AuthConstants.SESSION_USER_CURRENT_INFO);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("logOut error " + ex.getMessage());
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect("toLogin");
	}

	@RequestMapping(value = "/ssoLogout")
	public void ssoLogout(ServletRequest request, ServletResponse response) throws Exception {
		try {
			((HttpServletRequest) request).getSession().invalidate();
			((HttpServletRequest) request).getSession().removeAttribute(AuthConstants.SESSION_USER_INFO);
			((HttpServletRequest) request).getSession().removeAttribute(AuthConstants.SESSION_USER_CURRENT_INFO);
			FilterChainProxy.logOut((HttpServletRequest) request, (HttpServletResponse) response);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.debug("logOut error " + ex.getMessage());
		}
	}

	/**
	 * 
	 *
	 * @描述：获取菜单的功能按钮
	 *
	 * @param request
	 * @param url
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 * @创建人  ：kh
	 * @创建时间：2014-11-4上午9:36:14
	 * @修改人  ：kh
	 * @修改时间：2014-11-4上午9:36:14
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value = "/getFunctionResources")
	@ResponseBody
	public Map<String, Object> getFunctionResources(HttpServletRequest request, Model model, String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("functionResources", menuService.getFunctionMenus(request, url));
		} catch (Exception e) {
			e.getStackTrace();
		}
		return map;
	}

	/**
	 * 
	 *
	 * @描述：修改密码
	 *
	 * @param request
	 * @param password
	 * @return
	 * @throws Exception
	 * Boolean
	 * @创建人  ：acer
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO modifyPassword(HttpServletRequest request, String passWord)
			throws Exception {
		passWord = java.net.URLDecoder.decode(passWord, "UTF-8");
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		String loginName = authInfo.getLoginName();
		ResultVO resultVO = authService.modifyPassword(authInfo.getOrganId(), loginName, authInfo.getPwd(),
				passWord);
		return resultVO;
	}
	
	/**
	 * 
	 *
	 * @描述：获取菜单
	 *
	 * @param model
	 * @param projectName---工程名
	 * @param request
	 * @return
	 * @throws Exception
	 * Model
	 * @创建人  ：kh
	 * @创建时间：2014-10-23下午2:20:12
	 * @修改人  ：kh
	 * @修改时间：2014-10-23下午2:20:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping(value = "/saveVisitLogs")
	@ResponseBody
	public Map<String, Object> saveVisitLogs(HttpServletRequest request, Model model, String jsonParams) throws Exception {
		// 获取当前登录的用户的相关信息
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			JSONObject jsonObject = JSONObject.fromObject(jsonParams);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setIgnoreDefaultExcludes(false); 
			jsonConfig.setRootClass(VisitLogs.class);
			VisitLogs visitLogs=(VisitLogs) JSONObject.toBean(jsonObject,jsonConfig);
			visitLogs.setUserId(authInfo.getEmpId());
			visitLogs.setUserOrgId(authInfo.getOrganId());
			visitLogs.setUserDeptId(authInfo.getDepartmentCode());
			visitLogs.setUserName(authInfo.getPersonName());
			visitLogsService.save(visitLogs);
			map.put("result", "success");
			map.put("message", "保存成功");
		}catch(Exception e){
			log.error(e.getMessage(), e);
			map.put("result", "fail");
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/modifyInfo")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, String param, String photoInfo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String empid = SessionUtil.getCurrAuthInfo(request).getEmpId();
				
		Map<String, String> person = new HashMap<String, String>();
		JSONArray info = JSONArray.fromObject(param);
		for (Object o : info) {
			JSONObject object = JSONObject.fromObject(o);
			if (!object.isEmpty()) {
				person.put(object.getString("name"), object.getString("value"));
			}
		}
		person.put("photoInfo", photoInfo);
		boolean result = authService.modifyUserInfo(person, empid);
		if (result) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}
	
	@RequestMapping(value = "/validatePassword")
	@ResponseBody
	public Map<String, Object> validatePassword(HttpServletRequest request, String passWord) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(passWord)) {
			passWord = java.net.URLDecoder.decode(passWord, "UTF-8");
			AuthInfo authUser = SessionUtil.getCurrAuthInfo(request);
			if (authUser != null && authUser.getPwd().equals(MD5util.getInstance().calcMD5(passWord).toLowerCase())) {
				map.put("result", true);
			} else {
				map.put("result", false);
			}
		} else {
			map.put("result", false);
		}
		return map;
	}
	
	@RequestMapping(value="/queryLogList")
	@ResponseBody
	public Map<String, Object> queryLogList(HttpServletRequest request,PageRequest pageRequest,
			String resid){
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			map = menuService.queryLogList(resid, pageRequest);
		}catch(Exception e){
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", 0);
		}
		return map;
	}
}
