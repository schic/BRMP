package com.wondersgroup.empi.util.webserviceutil;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class RestCXFClient {
	
	public void reqEMPICenterGet(){
		String baseAddress = "http://localhost:8980/EMPI_center/webservice/EMPIObj/putEMPIObj";
		WebClient webClient = WebClient.create(baseAddress);
		webClient.acceptEncoding("utf-8");
		webClient.encoding("utf-8");
		webClient.accept(MediaType.APPLICATION_JSON);
		webClient.type(MediaType.APPLICATION_JSON);
		Response resp = webClient.post("A91o哦");
		
		//Response resp = webClient.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get();
		//System.out.println(resp.getMetadata());
		//readEntity（“方法的返回值类型”）
		//System.out.println(resp3.readEntity(User.class));
		System.out.println(resp.readEntity(String.class));
	}
	
	/**
	 * 向 EMPIClient
	 * 请求post
	 */
	public static String reqEMPIClient(String baseClientURL,String str){
		WebClient webClient = WebClient.create(baseClientURL);
		webClient.acceptEncoding("utf-8");
		webClient.encoding("utf-8");
		webClient.accept(MediaType.APPLICATION_JSON);
		webClient.type(MediaType.APPLICATION_JSON);
		Response resp = webClient.post(str);
		return resp.readEntity(String.class);
	}
	
	/**
	 * 向 EMPIClient
	 * 请求post
	 */
	public static String reqEMPIClient(String baseClientURL,String str,int timeOut){
		WebClient webClient = WebClient.create(baseClientURL);
		webClient.acceptEncoding("utf-8");
		webClient.encoding("utf-8");
		webClient.accept(MediaType.APPLICATION_JSON);
		webClient.type(MediaType.APPLICATION_JSON);
		TimeOutThread timeOutThread = new TimeOutThread(timeOut, webClient);
		timeOutThread.start();//开始计时
		Response resp = webClient.post(str);
		if (timeOutThread.isAlive()){//结束计时
			timeOutThread.interrupt();
		}
		return resp.readEntity(String.class);
		
	}

}
