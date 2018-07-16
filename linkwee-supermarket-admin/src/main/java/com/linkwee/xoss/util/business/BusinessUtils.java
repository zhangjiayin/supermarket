package com.linkwee.xoss.util.business;

import org.apache.commons.lang.StringUtils;

public class BusinessUtils {
	
	/**
	 * 拼装用户名和手机号码
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public  static String getUserNameMobile(String userName,String mobile){
		String userNameMobile = null;
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(mobile)){
			userNameMobile = StringUtils.join(new Object[]{userName,StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***")},' ');
		} else if(StringUtils.isNotBlank(userName) && StringUtils.isBlank(mobile)){
			userNameMobile = userName;
		} else if(StringUtils.isBlank(userName) && StringUtils.isNotBlank(mobile)){
			userNameMobile = StringUtils.join(new Object[]{mobile.substring(0,3),mobile.substring(mobile.length()-4)},"***");
		}
		return userNameMobile;
	}

}
