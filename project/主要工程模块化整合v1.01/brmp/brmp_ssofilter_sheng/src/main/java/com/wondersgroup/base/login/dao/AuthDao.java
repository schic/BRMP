package com.wondersgroup.base.login.dao;

import java.util.List;
import java.util.Map;

import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.base.login.model.TbAuthResource;
import com.wondersgroup.base.login.model.TbAuthUser;


public interface AuthDao {

	List<AuthInfo> getAuthUserInfo(String loginName, String organId);

	boolean modifyUserPassword(String orgId, String loginName, String newPassword);
	
	public TbAuthUser checkLogin(String name, String pass);
	
	public TbAuthUser getUserByLoginName(String loginName);
	
	public TbAuthResource getFatherMenuItem(String resid);
	
	public List<TbAuthResource> getAllResources();
	
	public TbAuthResource getResourceByKeyWord(String keyword);
	
	public boolean changePassword(String loginName, String passWord);
	
	public List<TbAuthResource> getChildrenResource(String keyword);
	
	public TbAuthResource getResourceByTreelayer(String treelayer);

	public List<TbAuthResource> getAllResource();

	public List<AuthInfo> getUserByLoginNameByOrgid(String loginName, String orgid);

	public AuthInfo checkLogin(String loginName, String passWord, String orgId);

	public Map<String, List<AuthResource>> getResources(String loginName, String orgId, boolean isInnerUrl, String requestUrl);

	public boolean modifyUserPassword(String loginName, String orgId, String oldpwd, String newPassWord);
	
	public boolean modifyUserInfo(String param);

	public void addApp(String userid, String orgid, String resId);

	public void delApp(String userid, String orgid, String resId);

	public List<AuthResource> queryApp(String userId, String organId, boolean isInnerUrl, String requestUrl);
	
	//查询登陆人员详细信息
	public Map<String, Object> queryPersonDetail(String empId);
	
	public Map<String, Object> queryPersonDetailById(String personId);
	
	public Map<String, Object> queryFileById(String fileid);
	
	public void deleteFile(String fileid);
	
	public void saveFile(List<String> list);
	
	public void updatePerson(String id,Map<String, String> person);

}
