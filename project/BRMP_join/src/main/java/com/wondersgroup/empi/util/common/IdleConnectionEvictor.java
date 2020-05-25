package com.wondersgroup.empi.util.common;

import org.apache.http.conn.HttpClientConnectionManager;


/** 
* @ClassName: IdleConnectionEvictor 
* @Description: (HttpClient连接管理器) 
* @author chl 
* @date 2016年12月13日 上午10:33:41 
*  
*/
public class IdleConnectionEvictor extends Thread {

    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        this.start();//启动当前线程
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    // 关闭失效的连接
                    connMgr.closeExpiredConnections();
                }
            }
        } catch (InterruptedException ex) {
            // 结束
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}