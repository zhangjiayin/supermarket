package com.linkwee.web.util;

import com.linkwee.core.util.EnumUtils;
import com.linkwee.web.enums.AppTypeEnum;

public class AppUtils {


	/**
	 * 根据appkey 获取系统类型
	 * @param app_key
	 * @return
	 */
	public static AppTypeEnum getAppType(String app_key){
		String type =  app_key.substring(0,app_key.lastIndexOf("_"));
		return (AppTypeEnum)EnumUtils.getEnumByValue(type, AppTypeEnum.values());
	}
	
	/**
	 * 是否为理财师客户端
	 * @param app_key
	 * @return
	 */
	public static boolean isChannelApp(String app_key){
		AppTypeEnum appType = getAppType(app_key);
		return AppTypeEnum.CHANNEL.equals(appType);
	}
	
	/**
	 * 是否为投资者客户端
	 * @param app_key
	 * @return
	 */
	public static boolean isInvestorApp(String app_key){
		AppTypeEnum appType = getAppType(app_key);
		return AppTypeEnum.INVESTOR.equals(appType);
	}
	
}
