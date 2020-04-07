package cn.ittx.quartz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ittx.po.auth.LoginSession;
import cn.ittx.service.intf.login.LoginService;

public class Quartz {
	
	@Autowired private LoginService loginService;
	
	void executeDeleteSession(){
		System.out.println("定时任务开始");
		
		long time = new Date().getTime();
		time = time - 2*60*60*1000;//获取两小时前的时间
		//time -= 1*60*1000;//获取1分钟前的时间
		System.out.println("两小时前的时间："+ new Date(time));
		
		List<LoginSession> loginSessions = loginService.getSessionByBeforeCreateTime(new Date(time));
		for(int i=0;i<loginSessions.size();i++){
			System.out.println("删除这个创建时间上的session：" + loginSessions.get(i).getCreateTime());
			loginService.removeLoginSession(loginSessions.get(i).getsId());
		}
		
		System.out.println("定时任务结束");
	}

}
