package com.linkwee.xoss.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageSendUtil {
	public static String RegexString(String content){
		Pattern p = Pattern.compile("<string[^>]*>(.*?)</string>"); 
		Matcher ms = p.matcher(content); 
		if(ms.find()) { 
				return ms.group(1);
		}else
		{
			return content;
		}
	}
	public static boolean isSuccessReturn(long element){
		long[] errorCodeArry = {-1, -12, -14, -999,-10001,-10003,-10011,-10029,-1003,-10031,-10057,-10056};
		boolean ret = true;
		for (int i = 0; i < errorCodeArry.length; i++)
		{
			if (element == errorCodeArry[i])
			{
				ret = false;
				break;
			}
		}
		return ret;
	
	}
}
