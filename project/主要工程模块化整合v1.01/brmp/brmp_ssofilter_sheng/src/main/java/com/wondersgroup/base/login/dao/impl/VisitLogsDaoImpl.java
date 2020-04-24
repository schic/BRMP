package com.wondersgroup.base.login.dao.impl;


import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.wondersgroup.base.login.dao.VisitLogsDao;
import com.wondersgroup.base.login.model.VisitLogs;



@Component
public class VisitLogsDaoImpl extends HibernateDaoSupport  implements VisitLogsDao {

	protected Log logger = LogFactory.getLog(getClass());

	@Autowired
	public void setSuperSessionFactory(@Qualifier("sessionFactory_auth")SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * @描述：通过登录名、机构ID获取登录人信息
	 * @see com.wondersgroup.rhip.login.dao.AuthDao#getAuthUserInfo(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void save(VisitLogs visitLogs) {
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO cen_bom.T_VISIT_LOGS (ID,USER_ORG_ID,USER_DEPT_ID,USER_ID,USER_NAME,SYSTEM_ID,MENU_ID,VISIT_TIME) ");
		sql.append(" VALUES(?,?,?,?,?,?,?,?)");
		SQLQuery query=this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		query.setParameter(0, UUID.randomUUID());
		query.setParameter(1, visitLogs.getUserOrgId());
		query.setParameter(2, visitLogs.getUserDeptId());
		query.setParameter(3, visitLogs.getUserId());
		query.setParameter(4, visitLogs.getUserName());
		query.setParameter(5, visitLogs.getSystemId());
		query.setParameter(6, visitLogs.getMenuId());
		query.setParameter(7, new Date());
		query.executeUpdate();
	}

}
