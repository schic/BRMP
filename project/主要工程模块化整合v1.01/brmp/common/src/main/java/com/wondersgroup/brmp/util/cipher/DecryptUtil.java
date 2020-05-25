package com.wondersgroup.brmp.util.cipher;

import com.wondersgroup.brmp.po.empipo.OriginSystemInfo;

/**
 * 系统用解密工具类
 */
public class DecryptUtil {
	
	/**
	 * 
	 * 接入加密解密方式 0:无 ; 1:AES; 2:AESVi; 3:DES; 4:ThreeDES; 5:AES变化; 6:AESVi变化; 7:DES变化
	 * @param params
	 * @param originSystemInfo
	 * @return
	 */
	public static String decrypt(String params, OriginSystemInfo originSystemInfo){
		if (originSystemInfo.getEncryptionType()==0){
			return params;
		} else if (originSystemInfo.getEncryptionType()==1) {
			
		}
		//TODO 
		
		
		return "";
	}

}
