package com.wondersgroup.brmp.util.common;

import java.io.UnsupportedEncodingException;

import com.wondersgroup.brmp.po.empipo.EMPIObj;
import com.wondersgroup.brmp.util.cipher.IDUtil;


public class EMPIObjUtil {
	
	/**
	 * 设置唯一号id
	 * @param empiObj
	 */
	public static void setRecordid(EMPIObj empiObj) {
		if (null == empiObj.getRecordid() || "-".equals(empiObj.getRecordid())) {
			StringBuffer sb = new StringBuffer();
			if (null == empiObj.getSfzh()) {
				sb.append("-");
			} else {
				sb.append(empiObj.getSfzh());
			}	
		
			if (null == empiObj.getXm() ) {
				sb.append("-");
			} else {
				sb.append(empiObj.getXm());
			}
			
			if (null == empiObj.getOriginSystemId()) {
				sb.append("-");
			} else {
				sb.append(empiObj.getOriginSystemId());
			}
			
			if (null == empiObj.getOriginId() ) {
				sb.append("-");
			} else {
				sb.append(empiObj.getOriginId());
			}
			
			try {
				empiObj.setRecordid(IDUtil.getUUIDC(sb.toString()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}	
	}

}
