package com.wondersgroup.empi.util.common;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wondersgroup.empi.dao.intf.EMPIClientJobDaoIntf;
import com.wondersgroup.empi.service.intf.EMPIClientJobIntf;

@Component
public class EMPIClientJobQ implements Runnable {
	
	@Autowired EMPIClientJobQ empiClientJobQ;
	
	/**
	 * 向empiClient发布执行队列消息
	 */
	public static Queue<Boolean> empiClientJobQueue = new ArrayBlockingQueue<Boolean>(3); 
	
	@Autowired EMPIClientJobIntf empiClientJobIntf;
	
	@Autowired EMPIClientJobDaoIntf empiClientJobDaoIntf;
	
	@Autowired BaseResource baseResource;
	
	@Override
    public void run() {
		while (true) {
			try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			if(!empiClientJobQueue.isEmpty()){
				if (empiClientJobQueue.remove()){
					EMPIComputeServiceUtil.EMPIComputeMain(empiClientJobIntf, empiClientJobDaoIntf, baseResource);
				}
			}

		}
	}
	
	@PostConstruct
    public void testMq() throws InterruptedException {
        new Thread(empiClientJobQ).start();//在spring启动时运行本线程run()
    }

}


