package cn.ittx.util.cipher;  
  
  
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec;  
  
public class AES {
	
	public static String getKey1(){//每天(24点)后变化key，加盐
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date =new Date();
		String keyString = sdf.format(date).concat("Hh%({$de");
		return MD5.enCoder(keyString).substring(4, 20);
	}
	
    // 加密  
    public static String enCrypt(String sSrc,String sKey){  
        String result = "";  
        try {  
            Cipher cipher;  
            cipher = Cipher.getInstance("AES");  
            byte[] raw = sKey.getBytes("utf-8");  
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);  
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8")); 
            result = Base64.getEncoder().encodeToString(encrypted); 
        } catch (Exception e) {
            e.printStackTrace();  
        }   
        // 此处使用BASE64做转码。  
        return result;  
                  
    }  
  
    // 解密  
    public static String deCrypt(String sSrc,String sKey){  
        try {  
            byte[] raw = sKey.getBytes("utf-8");  
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
            Cipher cipher = Cipher.getInstance("AES");  
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);  
            byte[] decrypted = Base64.getDecoder().decode(sSrc);// 先用base64解密  
            byte[] original = cipher.doFinal(decrypted);  
            String originalString = new String(original, "utf-8");  
            return originalString;  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            return "";  
        }  
    }  
  
}  