package com.wondersgroup.brmp.util.cipher;

import com.wondersgroup.brmp.po.empipo.ApplyManagement;

public class EncryptUtil {
	
	/**
	 * 
	 * 接入加密解密方式 0:无 ; 1:AES; 2:AESVi; 3:DES; 4:ThreeDES; 5:AES变化; 6:AESVi变化; 7:DES变化
	 * @param params
	 * @param applyManagement
	 * @return
	 */
	public static String enctypt(String params, ApplyManagement applyManagement){
		if (applyManagement.getEncryptionType()==0){
			return params;
		} else if (applyManagement.getEncryptionType()==1) {
			/**
			 * 采用AES加密的，使用接口的username+password拼接字符串生成key
			 */
			String skey = applyManagement.getUserName().concat(applyManagement.getApplyId());
			return AES.encrypt(params, AES.getKey(skey));
		} else if (applyManagement.getEncryptionType()==2) {
			/**
			 * 采用AESVi加密的，使用 password+接入系统中文名称 拼接字符串生成key
			 */
			String skey = applyManagement.getApplyId().concat(applyManagement.getApplyName());
			return AESVi.encrypt(params, AESVi.getKey(skey));
		} else if (applyManagement.getEncryptionType()==3) {
			/**
			 * 采用DES加密的，使用 password+username 拼接字符串为key
			 * 向量使用 DES.getsIv()
			 */
			String sKey = applyManagement.getApplyId().concat(applyManagement.getUserName());
			return DES.encrypt(params, sKey, DES.getsIv());
		} else if (applyManagement.getEncryptionType()==4) {
			/**
			 * ThreeDES直接使用，加密解密
			 * ThreeDES.bytes2HexString(ThreeDES.encode(ThreeDES.getKey(), cSrc.getBytes()));
			 * new String(ThreeDES.decrypt(ThreeDES.getKey(), ThreeDES.hexStringToBytes(params)));
			 */
			return ThreeDES.bytes2HexString(ThreeDES.encode(ThreeDES.getKey(), params.getBytes()));
		} else if (applyManagement.getEncryptionType()==5) {
			/**
			 * AES变化,解密出错时尝试使用前一天的变化key
			 * AES变化key，需要保持系统日期时间准确。
			 * 每天(24点)后变化key，加点盐。
			 * 
			 * String result = AES.decrypt(params, AES.getKey1(applyManagement.getApplyId()));
				if ("".equals(result)){
					result = AES.decrypt(params, AES.getKey1yes(applyManagement.getApplyId()));
				}
			 */
			return AES.encrypt(params, AES.getKey1(applyManagement.getApplyId()));
		} else if (applyManagement.getEncryptionType()==6) {
			return AESVi.encrypt(params, AESVi.getKey1(applyManagement.getApplyId()));
		} else if (applyManagement.getEncryptionType()==7) {
			return DES.encrypt(params, DES.getKey1(applyManagement.getApplyId()), DES.getsIv());
		}
		return "";
	}
	
	
	
	/**
	 * 解密applyManagement
	 * 
	   if (applyManagement.getEncryptionType()==0){
			return params;
		} else if (applyManagement.getEncryptionType()==1) {
			String skey = applyManagement.getUserName().concat(applyManagement.getApplyId());
			return AES.decrypt(params,AES.getKey(skey));
		} else if (applyManagement.getEncryptionType()==2) {
			String skey = applyManagement.getApplyId().concat(applyManagement.getApplyName());
			return AESVi.decrypt(params, AESVi.getKey(skey));
		} else if (applyManagement.getEncryptionType()==3) {
			String sKey = applyManagement.getApplyId().concat(applyManagement.getUserName());
			DES.decrypt(params, sKey, DES.getsIv());
		} else if (applyManagement.getEncryptionType()==4) {
			return new String(ThreeDES.decrypt(ThreeDES.getKey(), ThreeDES.hexStringToBytes(params)));
		} else if (applyManagement.getEncryptionType()==5) {
			String result = AES.decrypt(params, AES.getKey1(applyManagement.getApplyId()));
			if ("".equals(result)){
				result = AES.decrypt(params, AES.getKey1yes(applyManagement.getApplyId()));
			}
			return result;
		} else if (applyManagement.getEncryptionType()==6) {
			String result = AESVi.decrypt(params, AESVi.getKey1(applyManagement.getApplyId()));
			if ("".equals(result)){
				result = AESVi.decrypt(params, AESVi.getKey1yes(applyManagement.getApplyId()));
			}
			return result;
		} else if (applyManagement.getEncryptionType()==7) {
			String result = DES.decrypt(params, DES.getKey1(applyManagement.getApplyId()), DES.getsIv());
			if ("".equals(result)){
				result = DES.decrypt(params, DES.getKey1yes(applyManagement.getApplyId()), DES.getsIv());
			}
			return result;
		}
		return "";
	 */

}
