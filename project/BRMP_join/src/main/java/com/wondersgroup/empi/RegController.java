package com.wondersgroup.empi;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;
import com.wondersgroup.empi.service.intf.ReqEMPIJoinIntf;
import com.wondersgroup.empi.util.common.ApiService;
import com.wondersgroup.empi.util.common.HttpResult;

@Controller
public class RegController {

	@Autowired
	ReqEMPICenterIntf reqEMPICenterIntf;

	@Autowired
	ReqEMPIJoinIntf reqEMPIJoinIntf;

	@Value("${EMPICENTERDHIS2ADRESS}")
	private String EMPICENTERDHIS2ADRESS;
	
	@Value("${EMPIDHIS2GETCONDITIONORGINFO}")
	private String EMPIDHIS2GETCONDITIONORGINFO;
	
	@Value("${EMPIDHIS2ADDUSER}")
	private String EMPIDHIS2ADDUSER;	
	
	@Value("${GETCONDITIONORGQR}")
	private String GETCONDITIONORGQR;
	
	@Value("${READ_TIME_OUT}")
	private String READ_TIME_OUT;
	
	@Value("${SC_INTERNAL_SERVER_ERROR}")
	private String SC_INTERNAL_SERVER_ERROR;
	
	
	@Value("${USERNAME}")
	private String USERNAME;
	@Value("${PASSWORD}")
	private String PASSWORD;	
	
	@Value("${USERGROUPS}")
	private String USERGROUPS;
	@Value("${USERROLES}")
	private String USERROLES;
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping("/")
	public String indexPage() {
//		reqEMPICenterIntf.ReqEMPICenter();
		// reqEMPICenterIntf.ReqEMPICenter4Model();
		// reqEMPICenterIntf.ReqGir2ForModel();
		//reqEMPIJoinIntf.reqEMPPersion();

		return "index";
	}
	
	@RequestMapping("/index")
	public String index() {
//		reqEMPICenterIntf.ReqEMPICenter();
		// reqEMPICenterIntf.ReqEMPICenter4Model();
		// reqEMPICenterIntf.ReqGir2ForModel();
		//reqEMPIJoinIntf.reqEMPPersion();

		return "index";
	}

	@RequestMapping({ "/docRegPage" })
	public String docpage() {
		return "docReg";
	}
	
	@RequestMapping({ "/showOrgQr" })
	public String showOrgQr(HttpServletRequest request) {
		
		return "orgQr";
	}
	
	@RequestMapping({"/getOrgQr"})
	@ResponseBody
	public HttpResult getOrgQr(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
				
		HttpResult result = new HttpResult();
		
		try {
			
			String r= apiService.doGetString(GETCONDITIONORGQR+"&id="+id+"&ssd&name="+name);
			
			if(StringUtils.isNotBlank(r)) {
				
				result.setCode(200);			
				result.setDesc(r);
			}
			
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new HttpResult(HttpStatus.SC_INTERNAL_SERVER_ERROR,READ_TIME_OUT,null);
		
	}
	
	@RequestMapping({"/getOrgInfoByCon"})
	@ResponseBody
	public HttpResult getOrgInfoByCon() {
		
		HttpResult result = null;		
		
		try {
			result  = apiService.doGet(EMPICENTERDHIS2ADRESS+EMPIDHIS2GETCONDITIONORGINFO, USERNAME, PASSWORD);			
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new HttpResult(HttpStatus.SC_INTERNAL_SERVER_ERROR,READ_TIME_OUT,null);		
	}

	@RequestMapping({ "/userRegPage" })
	public String userpage() {
		
		return "userReg";
	}
	
	@RequestMapping({ "/saveUser" })
	@ResponseBody
	public HttpResult saveUser(HttpServletRequest request, HttpServletResponse response) {
		
		HttpResult result = null;
		
		String x = request.getParameter("x");
		String m = request.getParameter("m");
		String yhm = request.getParameter("yhm");
		String password = request.getParameter("password");
		String dzyj = request.getParameter("dzyj");
		String jg = request.getParameter("jg");
		String sj = request.getParameter("sj");
		String gz = request.getParameter("gz");
		
		JSONObject json = new JSONObject();
		
		json.put("firstName", x);
		json.put("surname", m);
		json.put("email", dzyj);		
		
		JSONObject joUserCredentials = new JSONObject();
		
		joUserCredentials.put("username", yhm);
		joUserCredentials.put("password", password);
		joUserCredentials.put("phoneNumber", sj);
		joUserCredentials.put("employer", gz);
		
		JSONArray jaUserRoles = new JSONArray();
		
		JSONObject userRoles = new JSONObject();
		
		userRoles.put("id", USERROLES);
		
		jaUserRoles.add(userRoles);
		
		joUserCredentials.put("userRoles", jaUserRoles);
		
		JSONArray jaOrganisationUnits = new JSONArray();
		
		JSONObject organisationUnits = new JSONObject();
		
		organisationUnits.put("id", jg);
		
		jaOrganisationUnits.add(organisationUnits);
		
		JSONArray jaUserGroups = new JSONArray();
		
		JSONObject groups = new JSONObject();
		
		groups.put("id", USERGROUPS);
		
		jaUserGroups.add(groups);		
		
		json.put("userCredentials", joUserCredentials);
		
		json.put("organisationUnits", jaOrganisationUnits);
		
		json.put("userGroups", jaUserGroups);
		
		System.out.println(json.toJSONString());
		
		try {
			
			result  = apiService.doPostJson(EMPICENTERDHIS2ADRESS + EMPIDHIS2ADDUSER,json.toJSONString(),USERNAME, PASSWORD);	
						
			return result;			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}		
				
		return new HttpResult(HttpStatus.SC_INTERNAL_SERVER_ERROR,READ_TIME_OUT,null);		
	}
	
	
	

	@RequestMapping({ "/saveDoctor" })
	@ResponseBody
	public String saveDoctor(HttpServletRequest request, HttpServletResponse response) {
		String xm = request.getParameter("xm");
		String xb = request.getParameter("xb");
		String sfzm = request.getParameter("sfzm");
		String csrq = request.getParameter("csrq");
		String gj = request.getParameter("gj");
		String fzjg = request.getParameter("fzjg");
		String fzrq = request.getParameter("fzrq");
		String qfr = request.getParameter("qfr");
		String zydd = request.getParameter("zydd");
		String hszyzsbh = request.getParameter("hszyzsbh");
		String hszyzszcrq = request.getParameter("hszyzszcrq");
		String hszyzsyxksrq = request.getParameter("hszyzsyxksrq");
		String hszyzsyxjsrq = request.getParameter("hszyzsyxjsrq");

		System.out.println(xm);
		System.out.println(xb);
		System.out.println(sfzm);
		System.out.println(csrq);
		System.out.println(gj);
		System.out.println(fzjg);
		System.out.println(fzrq);
		System.out.println();

		System.out.println("BBBBBBBBBBBBBBBB");

		return "reg";
	}

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext/applicationContext-commons.xml");
		context.registerShutdownHook();
		context.start();
		System.out.println(context);

		context.getBean("");

	}

}
