package com.wondersgroup.brmp.util.cipher;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class IDUtil {
	/**
	 * 随机生成唯一id
	 * @return
	 */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    
    /**
     * 根据字符串生成唯一id
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getUUIDC(String str) throws UnsupportedEncodingException{
    	return UUID.nameUUIDFromBytes(str.getBytes("utf-8")).toString().replace("-","");
    }

    /**
     * 生成数字订单id
     * @return
     * @deprecated
     */
    private static byte[] lock = new byte[0];
	// 位数，默认是8位
	private final static long w = 100000000;
	/**
	 * 随机生成数字id，可能有重复，
	 * @deprecated
	 * @return
	 */
	public static String createOrderId() {
		long r = 0;
		synchronized (lock) {
			r = (long) ((Math.random() + 1) * w);
		}
		return System.currentTimeMillis() + String.valueOf(r).substring(1);
	}

    public static void main(String[] args){
        for (int i = 0; i<100; i++) {
        	double id ; 
        	//id= IDUtil.getUUID();
        	/*
        	try {
				id = IDUtil.getUUIDC("中国123");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			*/
        	id = Math.random();
        	//id = createOrderId();
        	System.out.println(id);
        }
    }
}