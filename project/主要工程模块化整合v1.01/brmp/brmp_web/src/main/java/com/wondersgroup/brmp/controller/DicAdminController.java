package com.wondersgroup.brmp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.brmp.po.dicpo.Dictionary;
import com.wondersgroup.brmp.po.dicpo.DictionaryItem;
import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;
import com.wondersgroup.brmp.service.intf.ModelDataIntf;
import com.wondersgroup.brmp.util.cipher.IDUtil;

@Controller
@RequestMapping("/dic_admin")
public class DicAdminController {
	
	@Autowired ModelDataIntf modelDataIntf;
	
	@Autowired AuthService authService;
	
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
	
	@RequestMapping("datatype/ajax/newDic")
	@ResponseBody
	public String dataTypeAjaxNewDic(String json,String jsonDic){
		List<DictionaryItem> dictionaryItems = JSONArray.parseArray(json, DictionaryItem.class);
		Dictionary dictionary = JSON.parseObject(jsonDic, Dictionary.class);
		dictionary.setDicId(IDUtil.getUUID());
		dictionary.setAuditStatus(0);//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
		dictionary.setDicUpdeteTime(new Date());
		dictionary.setStatus(1);//状态  0:停用 1:启用
		dictionaryItems.getClass();
		
		return "";
	}

}
