package cn.ittx.util.cipher;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BASE64 {
    /**  
     * BASE64加密 
     * @throws UnsupportedEncodingException 
     */  
    public static String enCrypt(String src) throws UnsupportedEncodingException {   
    	return Base64.getEncoder().encodeToString(src.getBytes("utf-8"));
    }   

    /**  
     * BASE64解密  
     * @throws UnsupportedEncodingException 
     */  
    public static String deCrypt(String src) throws UnsupportedEncodingException {   
        return new String(Base64.getDecoder().decode(src),"utf-8");   
    }  

}