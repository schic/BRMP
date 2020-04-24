package com.wondersgroup.base.login.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.wondersgroup.base.login.dao.MenuDao;
import com.wondersgroup.core.hibernate.PageRequest;


@Component
public class MenuDaoImpl extends HibernateDaoSupport implements MenuDao {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	public void setSuperSessionFactory(@Qualifier("sessionFactory_auth")SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}


	/**
	 * 
	 * @描述：通过登录名、机构ID、工程名
	 * @see com.wondersgroup.rhip.login.dao.MenuDao#getResources(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getResources(String loginName, String organId, String resid) {
		String sql = "";
		List<String> argsList = new ArrayList<String>();
		/**
		 * 对参数进行逻辑化处理
		 */
		if (loginName != null) {
			sql = sql + " and t.loginname=? ";
			argsList.add(loginName);
		} else {
			sql = sql + " and t.loginname is null ";
		}
		if (organId != null) {
			sql = sql + " and t.orgid=? ";
			argsList.add(organId);
		} else {
			sql = sql + " and t.orgid is null ";
		}
		argsList.add(resid);
		/**
		 * 通过ＳＱＬ来查询数据
		 */
		sql = "select distinct t.resid \"resid\",t.presid \"presid\" ,t.resname \"resname\" ,t.keyword \"keyword\",t.node_type \"type\",t.url \"url\",t.SORT \"sort\", t.icon_small \"icon_small\", t.icon_large \"icon_large\" "
				+ "from ( select * from cen_auth.v_menu_resource t where 1=1  "
				+ sql
				+ ")t START WITH  t.resid=? CONNECT BY PRIOR t.resid=t.presid order by t.sort ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < argsList.size(); i++) {
			query.setString(i, (String) argsList.get(i));
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		logger.info("已获取菜单资源");

		return query.list();
	}

	// 获取系统发布版本
	@Override
	public String getVersionByResid(String resid) {
		String version = "无" ;
		String sql = " select version from TB_AUTH_RESOURCE_RELEASE_LOG t where t.resid =:resid order by  t.release_time desc";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setParameter("resid", resid);
		query.setMaxResults(1);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> list = query.list();
		if(list != null && list.size()>0){
			version = list.get(0).get("VERSION").toString();
		}
		return version;
	}
	
	//分页查询系统历史版本信息
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> queryLogList(String resid,PageRequest pageRequest){
		List<Object> listArr=new ArrayList<Object>();
		
		String countSql = "select count(1) count from TB_AUTH_RESOURCE_RELEASE_LOG t where "
				+ " t.resid =:resid "  ;
		String sql = "select t.id , t.resid ,t.version ,t.content ,to_char(t.release_time,'yyyy-mm-dd') RELEASETIME"
				+ " from TB_AUTH_RESOURCE_RELEASE_LOG t where "
				+ " t.resid =:resid  order by  t.release_time desc " ;
		SQLQuery countQuery = this.getSessionFactory().getCurrentSession().createSQLQuery(countSql);
		countQuery.setParameter("resid", resid);
		int count = Integer.parseInt(countQuery.uniqueResult().toString());
		List<Map<String,String>> list = new ArrayList<Map<String ,String>>();
		if(count >0){
			SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
			query.setParameter("resid", resid);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			query.setFirstResult(pageRequest.getOffset());
			query.setMaxResults(pageRequest.getPageSize());
			list = query.list();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
}
