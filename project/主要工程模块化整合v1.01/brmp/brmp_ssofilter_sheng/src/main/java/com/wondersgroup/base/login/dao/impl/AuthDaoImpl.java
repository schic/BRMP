package com.wondersgroup.base.login.dao.impl;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.wondersgroup.base.login.dao.AuthDao;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.TbAuthResource;
import com.wondersgroup.base.login.model.TbAuthUser;
import com.wondersgroup.base.login.util.SsoFilterConfResource;
import com.wondersgroup.base.login.model.AuthResource;



@SuppressWarnings({ "deprecation"})
@Component
public class AuthDaoImpl extends HibernateDaoSupport  implements AuthDao {

	protected Log logger = LogFactory.getLog(getClass());
	
	// URL正则表达式
	Pattern URL_PATTERN = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

	@Autowired
	public void setSuperSessionFactory(@Qualifier("sessionFactory_auth")SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Autowired SsoFilterConfResource ssoFilterConfResource;

	/**
	 * 
	 * @描述：通过登录名、机构ID获取登录人信息
	 * @see com.wondersgroup.rhip.login.dao.AuthDao#getAuthUserInfo(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AuthInfo> getAuthUserInfo(String loginName, String organId) {
		String sql = "select userId \"userId\",loginName \"loginName\",psw \"pwd\",orgId \"organId\",org_Code \"organCode\",org_Abbreviation \"organName\","
				+ " dept_Code \"departmentCode\",dept_Name \"departmentName\",empId \"empId\",job_Number \"gh\",personname \"personName\",admi_division_code \"manageAreaCode\","
				+ " admi_division_name \"manageAreaShortName\" "
				+ " from cen_auth.v_tb_auth_user t where t.loginname=:loginName and t.orgId=:organId  ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("loginName", loginName);
		query.setString("organId", organId);
		query.setResultTransformer(Transformers.aliasToBean(AuthInfo.class));
		return query.list();
	}

	/**
	 * 
	 * @描述：修改密码
	 * @see com.wondersgroup.base.login.dao.AuthDao#modifyUserPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean modifyUserPassword(String orgId, String loginName, String newPassword) {
		String queryString = "UPDATE tb_auth_user SET psw = :password WHERE loginname =:loginName and orgcode=:orgId ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		query.setString("password", newPassword);
		query.setString("loginName", loginName);
		query.setString("orgId", orgId);
		int result = query.executeUpdate();
		return result > 0;
	}
	
	@SuppressWarnings("unchecked")
	public TbAuthUser checkLogin(String name, String pass) {
		String hql = "from TbAuthUser us where us.loginname=? and us.psw=?";
		//List<TbAuthUser> list =this.find(hql, new Object[]{ name, pass});
		List<TbAuthUser> list = (List<TbAuthUser>)this.getHibernateTemplate().find(hql, new Object[]{ name, pass});
		if (list != null && list.size() != 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public TbAuthResource getFatherMenuItem(String resid) {
		String hql = "from TbAuthResource ar where ar.resid=?";
		//List<TbAuthResource> list =this.find(hql, new Object[]{ Long.parseLong(resid)});
		List<TbAuthResource> list = (List<TbAuthResource>)this.getHibernateTemplate().find(hql, new Object[]{ Long.parseLong(resid)});
		if (list != null && list.size() != 0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public TbAuthResource getResourceByKeyWord(String keyword) {
		String hql = "from TbAuthResource tr where tr.keyword=?";
		List<TbAuthResource> list = (List<TbAuthResource>)this.getHibernateTemplate().find(hql, new Object[]{ keyword});
		//List<TbAuthResource> list = this.find(hql, new Object[]{keyword});
		return (list != null && list.size() != 0) ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<TbAuthResource> getAllResources() {
		String hql = "from TbAuthResource tr order by tr.treelayer asc";
		return (List<TbAuthResource>) this.getHibernateTemplate().find(hql);
		//return this.find(hql);
	}

	public boolean changePassword(final String loginName, final String passWord) {
		String hql = "update TbAuthUser us set us.psw=? where us.loginname=?";
		int count = this.getHibernateTemplate().bulkUpdate(hql, new Object[]{passWord,loginName});
		if (count != 0){
			return true;
		}
		return false;
//		List<TbAuthResource> list = this.find(hql, new Object[]{keyword});
//		return (Boolean)this.getHibernateTemplate().execute(new HibernateCallback(){
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				String hql = "update TbAuthUser us set us.psw=? where us.loginname=?";
//				Query query = session.createQuery(hql);
//				query.setString(0, passWord);
//				query.setString(1, loginName);
//				int count = query.executeUpdate();
//				if (count != 0){
//					return true;
//				}
//				return false;
//			}
//		});
	}

	@SuppressWarnings("unchecked")
	public TbAuthUser getUserByLoginName(String loginName) {
		String hql = " from TbAuthUser us where us.loginname=?";
		List<TbAuthUser> list = (List<TbAuthUser>) this.getHibernateTemplate().find(hql, new Object[]{loginName});
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TbAuthResource> getChildrenResource(String keyword) {
		String hql = "from TbAuthResource tar where tar.tbAuthResource.resid = (select tr.resid from TbAuthResource tr where tr.keyword = ?)";
		return (List<TbAuthResource>) this.getHibernateTemplate().find(hql, new Object[]{keyword});
	}

	@SuppressWarnings("unchecked")
	public TbAuthResource getResourceByTreelayer(String treelayer) {
		String hql = "from TbAuthResource tr where tr.treelayer=?";
		List<TbAuthResource> list = (List<TbAuthResource>) this.getHibernateTemplate().find(hql, new Object[]{treelayer});
		return (list != null && list.size() != 0) ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<TbAuthResource> getAllResource() {
		try {
			String hql = "from TbAuthResource tar where tar.tbAuthResource.resid =1 or tar.tbAuthResource.resid =2";
			List<TbAuthResource> list=(List<TbAuthResource>) this.getHibernateTemplate().find(hql, new Object[]{});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuthInfo> getUserByLoginNameByOrgid(String loginName, String orgid) {
		String sql = "select userId \"userId\",loginName \"loginName\",psw \"pwd\",orgId \"organId\",org_Code \"organCode\",org_Abbreviation \"organName\","
				+ " dept_Code \"departmentCode\",dept_Name \"departmentName\",empId \"empId\",job_Number \"gh\",personname \"personName\",admi_division_code \"manageAreaCode\","
				+ " admi_division_name \"manageAreaShortName\" "
				+ " from cen_auth.v_tb_auth_user t where t.loginname=:loginName and t.orgId=:organId  ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("loginName", loginName);
		query.setString("organId", orgid);
		query.setResultTransformer(Transformers.aliasToBean(AuthInfo.class));
		return query.list();
	}

	@Override
	public AuthInfo checkLogin(String loginName, String passWord, String orgId) {
		String sql = "select userId \"userId\",loginName \"loginName\",psw \"pwd\",orgId \"organId\",org_Code \"organCode\",org_Abbreviation \"organName\","
				+ " dept_Code \"departmentCode\",dept_Name \"departmentName\",empId \"empId\",job_Number \"gh\""
				+ " from v_tb_auth_user t where t.loginname=:loginName and t.orgId=:organId and t.psw=:psw ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("loginName", loginName);
		query.setString("organId", orgId);
		query.setString("psw", passWord);
		query.setResultTransformer(Transformers.aliasToBean(AuthInfo.class));
		@SuppressWarnings("rawtypes")
		List list = query.list();

		return (AuthInfo) ((list != null && list.size() > 0) ? list.get(0) : null);
	}

	/**
	 * 
	 * @描述：通过登录名、机构ID、是否内网来获取资源
	 * @see com.wondersgroup.qyws.index.dao.AuthDao#getResources(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public Map<String, List<AuthResource>> getResources(String loginName, String orgId, boolean isInnerUrl, String requestUrl) {

		String sql = "";
		/**
		 * 如果为内网
		 */
		if (isInnerUrl) {
			sql = " ,t.INNER_URL \"url\" ";
		} else {//外网
			sql = " ,t.OUTER_URL \"url\" ";
		}
		sql = "select distinct t.resid \"resid\", t.resname \"resname\", t.keyword \"keyword\", t.app_type \"apptype\", t.icon_large \"iconLarge\" "
				+ sql
				+ " ,t.SORT, w.res_id as \"isWB\" from v_menu_resource t"
				+ " left join TB_AUTH_USER_WORKBENCH w on w.user_id = t.USERID and w.org_id = t.ORGID and t.RESID = w.res_id"
				+ " where 1=1 "
				+ " and t.presid in (select distinct a.resid from v_menu_resource a where a.keyword = 'PT_LOGIN') "
				+ " and t.loginname=:loginname and t.orgid=:orgid order by t.app_type, t.sort";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("loginname", loginName);
		query.setString("orgid", orgId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = query.list();

		/****************************对菜单进行组装********************************/
		Map<String, List<AuthResource>> group = new LinkedHashMap<String, List<AuthResource>>(0);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AuthResource ar = new AuthResource();
				Map<String, String> map = list.get(i);
				ar.setResId(map.get("resid"));
				ar.setResCode(map.get("keyword"));
				ar.setResName(map.get("resname"));
				
				// modify begin by huaxy 20160328
				// reason：无法同时支持移动、电信、联通等多个IP地址的访问，故作此修改
				//ar.setResContent(map.get("url"));
				String realUrl = map.get("url");
				// 内网IP访问，或当前URL为空
//				if (!isInnerUrl && StringUtils.isNotBlank(realUrl))
//				{
//					Matcher m=URL_PATTERN.matcher(realUrl);
//					if (m.find()){
//						realUrl = realUrl.replaceFirst(m.group(), requestUrl);
//					}
//				}
				ar.setResContent(realUrl);
				// modify end
				
				ar.setResSort(Long.valueOf(i));
				ar.setResImgURL(map.get("iconLarge"));
				ar.setIsWB(!StringUtils.isBlank(map.get("isWB")));

				String appType = String.valueOf(map.get("apptype"));
				if (!group.containsKey(appType)) {
					group.put(appType, new ArrayList<AuthResource>(0));
				}

				List<AuthResource> resultList = group.get(appType);
				resultList.add(ar);
			}

		}
		return group;
	}

	@Override
	public boolean modifyUserPassword(String loginName, String orgId, String oldpwd, String newPassWord) {
		String queryString = "UPDATE tb_auth_user SET psw = :password WHERE loginname =:loginName and orgcode=:orgCode ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(queryString);
		query.setString("password", newPassWord);
		query.setString("loginName", loginName);
		query.setString("orgCode", orgId);
		int result = query.executeUpdate();
		return result > 0;
	}

	@Override
	public void addApp(String userid, String orgid, String resId) {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery("INSERT INTO TB_AUTH_USER_WORKBENCH VALUES('"+userid+"','"+orgid+"','"+resId+"')");
		query.executeUpdate();
	}

	@Override
	public void delApp(String userid, String orgid, String resId) {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(
				"DELETE TB_AUTH_USER_WORKBENCH WHERE USER_ID = '" + userid + "' AND ORG_ID = '" + orgid
						+ "' AND RES_ID = '" + resId + "'");
		query.executeUpdate();
	}

	@Override
	public List<AuthResource> queryApp(String userId, String organId, boolean isInnerUrl, String requestUrl) {
		String sql = "";
		/**
		 * 如果为内网
		 */
		if (isInnerUrl) {
			sql = " ,t.INNER_URL \"url\" ";
		} else {//外网
			sql = " ,t.OUTER_URL \"url\" ";
		}
		sql = "select distinct t.resid \"resid\", t.resname \"resname\", t.keyword \"keyword\", t.app_type \"apptype\", t.icon_large \"iconLarge\" "
				+ sql
				+ " ,t.SORT, w.res_id as \"isWB\" from TB_AUTH_USER_WORKBENCH w"
				+ " inner join table(fn_get_menu_resouce('',:userid,:orgid)) t on w.user_id = t.USERID and w.org_id = t.ORGID and t.RESID = w.res_id"
				+ " where 1=1 "
				//+ " and t.presid in (select distinct a.resid from v_menu_resource a where a.keyword = 'PT_LOGIN') "
				+ " order by t.sort";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("userid", userId);
		query.setString("orgid", organId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = query.list();
		/****************************对菜单进行组装********************************/
		List<AuthResource> resultList = new ArrayList<AuthResource>(0);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				AuthResource ar = new AuthResource();
				Map<String, String> map = list.get(i);
				ar.setResId(map.get("resid"));
				ar.setResCode(map.get("keyword"));
				ar.setResName(map.get("resname"));
				
				// modify begin by huaxy 20160328
				// reason：无法同时支持移动、电信、联通等多个IP地址的访问，故作此修改
				//ar.setResContent(map.get("url"));
				String realUrl = map.get("url");
				// 内网IP访问，或当前URL为空
//				if (!isInnerUrl && StringUtils.isNotBlank(realUrl))
//				{
//					Matcher m=URL_PATTERN.matcher(realUrl);
//					if (m.find()){
//						realUrl = realUrl.replaceFirst(m.group(), requestUrl);
//					}
//				}
				ar.setResContent(realUrl);
				// modify end
				
				ar.setResSort(Long.valueOf(i));
				ar.setResImgURL(map.get("iconLarge"));
				ar.setIsWB(!StringUtils.isBlank(map.get("isWB")));

				resultList.add(ar);
			}

		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryPersonDetail(String empId) {
		String sql = "select p.* from cen_reg.t_employee e left join cen_reg.t_reg_person p on e.person_id = p.id where e.id=:empId";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("empId", empId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean modifyUserInfo(String param) {
		
		return false;
	}

	@Override
	public void deleteFile(String fileid) {
		String sql = "delete cen_reg.t_file_info f where f.id='"+fileid+"'";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public Map<String, Object> queryPersonDetailById(String personId) {
		String sql = "select p.* from cen_reg.t_reg_person p where p.id=:personId";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("personId", personId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> queryFileById(String fileid) {
		String sql = "select f.* from cen_reg.t_file_info f where f.id=:fileid";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("fileid", fileid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveFile(List<String> list) {
		String sql ="";
		for (String str : list) {
			sql  =sql+"'"+str+"'," ;
		}
		sql = sql +"sysdate,sysdate,";
		sql = "insert into cen_reg.t_file_info (id,file_name,file_type,path,file_size, create_by, modify_by,create_date,modify_date) values ("+sql.substring(0, sql.length()-1)+")";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void updatePerson(String id,Map<String, String> info) {
		String sql ="";
		for (Map.Entry<String, String> entry : info.entrySet()) {
			sql = sql+entry.getKey()+"='"+entry.getValue()+"',";
		}
		sql = sql +"modify_date = sysdate,create_date = sysdate,";
		String sql1 = "update cen_reg.t_reg_person_audit set "+sql.substring(0, sql.length()-1)+" where id = '"+id+"'";
		String sql2 = "update cen_reg.t_reg_person set "+sql.substring(0, sql.length()-1)+" where id = '"+id+"'";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql1);
		Query query1 = this.getSessionFactory().getCurrentSession().createSQLQuery(sql2);
		query.executeUpdate();
		query1.executeUpdate();
	}

	@Override
	public String getUserType(String userId) {
		String sql = "select roleid from cen_auth.tb_auth_user_role where userid = :userId ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString("userId", userId);
		@SuppressWarnings("unchecked")
		List<String> roleIds = query.list();
		String userType = "user";
		for (int i=0;i<roleIds.size();i++){
			if (roleIds.get(i).equals(ssoFilterConfResource.getBrmpSystem()) ){
				userType = "system";
				break;
			}
		}
		for (int i=0;i<roleIds.size();i++){
			if (roleIds.get(i).equals(ssoFilterConfResource.getBrmpAdmin()) ){
				userType = "admin";
				break;
			}
		}
		return userType;
	}

}
