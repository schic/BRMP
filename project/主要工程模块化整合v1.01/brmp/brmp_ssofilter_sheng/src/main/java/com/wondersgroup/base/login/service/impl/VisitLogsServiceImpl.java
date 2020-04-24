package com.wondersgroup.base.login.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.base.login.dao.AuthDao;
import com.wondersgroup.base.login.dao.VisitLogsDao;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.base.login.model.ResultConstant;
import com.wondersgroup.base.login.model.ResultVO;
import com.wondersgroup.base.login.model.VisitLogs;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.service.VisitLogsService;
import com.wondersgroup.base.login.util.BaseCache;
import com.wondersgroup.base.login.util.MD5Utils;

/**
 * 
 * @描述    ： 获取登录人想=相关信息service
 *
 * @创建人  ：kh
 * @创建时间：2014-10-24 下午3:30:56
 * @修改人  ：kh
 * @修改时间：2014-10-24 下午3:30:56
 * @修改备注：
 *
 * @version 1.0
 */
@Service
@Transactional
public class VisitLogsServiceImpl implements VisitLogsService {

	@Autowired
	private VisitLogsDao visitLogsDao;

	/**
	 * 
	 * @描述：保存访问日志
	 * 
	 */
	@Override
	public void save(VisitLogs visitLogs) {
//		if (visitLogs.getMenuId() == null || visitLogs.getMenuId().isEmpty() || visitLogs.getSystemId() == null
//				|| visitLogs.getSystemId().isEmpty()) {
//			throw new RuntimeException("菜单ID或系统ID为空");
//		}
		if (visitLogs.getUserId()==null) {
			throw new RuntimeException("用户ID为空");
		}
		visitLogsDao.save(visitLogs);
	}

}
