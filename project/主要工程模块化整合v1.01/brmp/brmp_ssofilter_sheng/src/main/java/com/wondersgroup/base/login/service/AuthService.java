package com.wondersgroup.base.login.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.base.login.model.ResultVO;
import com.wondersgroup.base.login.model.TbAuthResource;
import com.wondersgroup.base.login.model.TbAuthUser;

public interface AuthService {

	List<AuthInfo> getAuthUserInfo(String loginName, String organId);

	ResultVO modifyPassword(String orgId, String loginName, String oldPwd, String newPassword);
	
	/**
	 * 登录
	 * @param name 用户名
	 * @param pass 密码
	 * */
	public TbAuthUser checkLogin(String name, String pass);
	
	/**
	 * 获取用户信息
	 * @param loginName 登录名
	 * */
	public TbAuthUser getUserByLoginName(String loginName);
	
	/**
	 * 查询父级菜单项
	 * @param resid 节点ID
	 * */
	public TbAuthResource getFatherMenuItem(String resid);
	
	/**
	 * 获得指定父节点下的子节点列表
	 * */
	public List<TbAuthResource> getChildrenResource(String keyword);
	
	/**
	 * 修改密码
	 * @param loginName 登录名
	 * @param oldPass 原密码
	 * @param newPass 新密码
	 * */
	public String changePassword(String loginName, String oldPass, String newPass);
	
	/**
	 * 根据treelayer获得该菜单
	 * */
	public TbAuthResource getResourceByTreelayer(String treelayer);
	/**
	 * 获取所有的TB_AUTH_RESOURCE中presid为1
	 * @return
	 */
	public List<TbAuthResource> getAllResource();

	/**
	 * 
	 *
	 * @描述：通过登录名与机构ID查询登录人员
	 *
	 * @param loginName
	 * @return
	 * AuthUser
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<AuthInfo> getUserByLoginNameByOrgid(String loginName, String orgid);

	public AuthInfo checkLogin(String loginName, String passWord, String orgId);

	public Map<String, List<AuthResource>> getResources(HttpServletRequest request, String loginName, String orgId);

	public void setSystemize(HttpServletRequest request, AuthInfo currentAuthInfo, List<AuthResource> resourceList);

	public AuthInfo getUserByLoginName(String loginName, String orgId);

	public boolean modifyUserPassword(String loginName, String orgId, String oldpwd, String newPassWord);
	
	public boolean modifyUserInfo(Map<String, String> info,String empid) throws Exception;

	public void addApp(String userid, String orgid, String resId);

	public void delApp(String userId, String organId, String resId);

	public List<AuthResource> queryApp(HttpServletRequest request, String userId, String organId);
	
	public Map<String, Object> queryPersonDetail(String empId);
	
	/**
	 * 资源管理系统添加，根据userId获取 用户类型
	 * @param userId
	 * @return
	 */
	public String getUserType(String userId);

}
