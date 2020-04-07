package com.wondersgroup.empi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;
import com.wondersgroup.empi.service.intf.ReqEMPIJoinIntf;

@Controller
@RequestMapping("/")
public class testController {
	
	@Autowired ReqEMPICenterIntf reqEMPICenterIntf;
	
	@Autowired ReqEMPIJoinIntf reqEMPIJoinIntf;
	
	
	@RequestMapping("/index")
	public String index2(){
//		reqEMPICenterIntf.ReqEMPICenter();
		//reqEMPICenterIntf.ReqEMPICenter4Model();
		//reqEMPICenterIntf.ReqGir2ForModel();
			reqEMPIJoinIntf.reqEMPPersion();
		
		return "index";
	}
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext/applicationContext-commons.xml");
		context.registerShutdownHook();
        context.start();
		System.out.println(context);
		
		context.getBean("");
		
	}
	

}
