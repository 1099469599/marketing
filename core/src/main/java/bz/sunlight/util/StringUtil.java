package bz.sunlight.util;

import java.io.UnsupportedEncodingException;

import bz.sunlight.constant.BaseConstant;

public class StringUtil {

	public static String shortToString(Short s){
		if(s!=null){
			return Short.toString(s);
		}
		return "";
	}
	
	public static String isoStringToU8String(String str){
		if(str!=null){
			try {
				
				str = new String(str.getBytes(BaseConstant.ISO_8859_1),BaseConstant.UTF_8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
		return null;
	}
	
	
}
