package cn.xn.user.utils;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

public class MD5 {
	
	/**
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	public static String md5(String str, String charset) {
		return encrypt(str, charset);
	}
	

	public static String md5(String str) {
		return encrypt(str, null);
	}
	
	private static String encrypt(String str,String charset){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(StringUtils.isNotBlank(charset)){
				md.update(str.getBytes(charset));
			}else{
				md.update(str.getBytes());
			}
			
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return str;
	}

}
