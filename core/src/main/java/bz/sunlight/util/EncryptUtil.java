package bz.sunlight.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class EncryptUtil {

//	base64加密
	public static String base64En(String source){
        Base64 base64 = new Base64();
        byte[] encode = base64.encode(source.getBytes());
        return new String(encode);
    }
	
//	MD5加密
	public static String MD5Decode(String password){
        MessageDigest md5=null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Base64 base64 = new Base64();
		byte[] passwordDecode = md5.digest(password.getBytes());
		return base64.encodeBase64String(passwordDecode);
        
    }
	
}
