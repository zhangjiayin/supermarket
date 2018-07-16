package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

/**
 * 
 * @描述：1、注册    2、重置登录密码   3、重置支付密码
 *
 * @author Bob
 * @时间  2015年10月10日下午5:22:36
 *
 */
public enum SmsVcodeTypeEnum implements KeyValueEnum{
	
	REGISTER(1,"REGISTER"),
	RESETLOGIN(2,"RESETLOGIN"),
	RESETPAY(3,"RESETPAY"),
	RCLCSSIGN(4,"RCLCSSIGN"),
	RCLCSANON(5,"RCLCSANON");
	

	private int key;
	private String value;

	SmsVcodeTypeEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}


	public String getValue() {
		return value;
	}
}
