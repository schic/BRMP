package com.wondersgroup.brmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.brmp.dao.daoutil.DaoConfResource;
import com.wondersgroup.brmp.po.dicpo.Dictionary;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.service.intf.ModelDataIntf;

@Controller
@RequestMapping("/dic_admin")
public class DicAdminController {
	
	@Autowired ModelDataIntf modelDataIntf;
	
	@Autowired AuthService authService;
	
	@Autowired DaoConfResource daoConfResource;
	
	@RequestMapping("/datatype/ajax/dicQuery")
	@ResponseBody
	public List<Dictionary> dataTypeAjaxDicQuery(String beginDate,String endDate,String status,String auditStatus,HttpServletRequest request,HttpServletResponse response){
		//Map<String,Object> ssoUser = SsoUtil.getSsoUser(request);
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		if (null == authInfo.getUserType()){
			authInfo.setUserType(authService.getUserType(authInfo.getUserId()));
			try {
				SessionUtil.setCurrAuthInfo(request, authInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null == request.getSession().getAttribute("originSystemInfo") ) {
			String originSystemId = authInfo.getUserId();
			if ("admin".equals(authInfo.getUserType())){
				originSystemId = "1";//管理员admin默认为"1"
			}
			OriginSystemInfo originSystemInfo = modelDataIntf.queryOriginSystemByOriginSystemId(originSystemId);
			request.getSession().setAttribute("originSystemInfo", originSystemInfo);
		}
		OriginSystemInfo originSystemInfo = (OriginSystemInfo) request.getSession().getAttribute("originSystemInfo");
		//"1"现在默认未1系统为管理员，查询全部模板不对系统id过滤
		List<Dictionary> dictionary = modelDataIntf.queryDictionary(originSystemInfo.getOriginSystemId(),beginDate,endDate,status,auditStatus);
		return dictionary;
	}

}
