package com.linkwee.xoss.util;

import java.util.UUID;

public class StringUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static boolean isBlank(String str) {
		return !isNotBlank(str);
	}

	public static boolean isNotBlank(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static String upperCaseFirstChar(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String lowerCaseFirstChar(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 字符串是否为数字
	 * @Auther ZhongLing
	 * @Date 2016年2月22日
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 将字符串中大写字母转换成下划线小写，比如roleName->role_name
	 * @param str
	 * @return string
	 */
	public static String convertColumn(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c:str.toCharArray()) {
			if (Character.isUpperCase(c)) {
				sb.append('_').append(Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
}
