/**
 * 创建日期：Nov 16, 2012
 * 作者： "王胤洪"
 * 版权： 指明该文件的版权信息
 * 功能： 定义权限相关常量
 */

package com.wondersgroup.base.login.vo;

public class AuthConstants {

	//-------------------------Session中的键-------------------------------
	/**
	 * SESSION中所有用户对象KEY
	 */
	public final static String SESSION_USER_INFO = "wondersgroup.qyws.usersession";

	/**
	 * SESSION中随机数KEY
	 */
	public final static String SESSION_LOGIN_RANDOM = "login_random";

	/**
	 * SESSION中用户所有角色KEY
	 */
	public final static String SESSION_USER_ALL_ROLES = "wondersgroup.qyws.userallroles";

	/**
	 * SESSION中当前用户KEY
	 */
	public final static String SESSION_USER_CURRENT_INFO = "wondersgroup.qyws.curuser";

	/**
	 * 当前角色所拥有的权限KEY
	 */
	public final static String SESSION_USER_ROLE_RESOURCE = "wondersgroup.qyws.userroleresource";//当前登录用户角色所有能访问的资源放于Session中的KEY

	/**
	 * 当前查看个人明细数据时，隐私信息是否需要隐藏
	 */
	public final static String SESSION_PERSON_INFO_IS_HIDE = "wondersgroup.qyws.personinfoishide";//当前登录用户角色所有能访问的资源放于Session中的KEY

	/**
	* 
	*/
	//  public final static String SESSION_PERSON_INFO_IS_HIDE = "wondersgroup.qyws.personinfoishide";//当前登录用户角色所有能访问的资源放于Session中的KEY

	//--------------------------Request 和  Response中的键---------------------------------

	public final static String LOGIN_ERROR_MSG = "loginErrorMsg";//登陆错误信息

	public final static String USER_ROLE_MENU_RESOURCE = "userRoleMenuResource";//当前登录用户角色菜单资源

	public final static String PROJECT_NAME = "projectName";//当前登陆项目代码

	public final static String REDIRECT_URL = "redirectUrl";//重定向的url

	public final static String NOT_EXIST = "not exist";//登陆用户不存在

	public final static String ERROR_TOKEN = "wrong token";//登陆令牌错误  

	public final static String WRONG_PWD = "wrong password";//密码错误

	public final static String PROC_SUCCESS = "process success";//执行成功

	public final static String PROC_FAILED = "process_failed";

	public final static String NO_USER = "no user";//角色未分配人员

	public final static String NO_AUTH = "no auth";//无权限

	public final static String USER_ORG_SHORTNAME = "orgShortName";

	public static final String SESSION_USER_PROJECT_RESOURCE = "wondersgroup.qyws.projectresource";

}
