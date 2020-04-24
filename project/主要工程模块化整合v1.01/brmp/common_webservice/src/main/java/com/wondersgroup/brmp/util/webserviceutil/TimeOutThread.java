package com.wondersgroup.brmp.util.webserviceutil;

import org.apache.cxf.jaxrs.client.WebClient;

/**
 * 超时计时器 for
 * cxf工具的 web客户端
 */
public class TimeOutThread extends Thread {
	
	private int timeOut;
	
	private WebClient webClient;
	
	public TimeOutThread(int timeOut,WebClient webClient) {
		super();
		this.webClient = webClient;
		this.timeOut = timeOut;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			return;
		}
		System.out.println("WebClient timeout[rest连接超时]");
		webClient.close();
	}
	

}
