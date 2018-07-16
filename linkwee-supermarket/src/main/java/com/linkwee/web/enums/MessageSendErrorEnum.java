package com.linkwee.web.enums;

import com.linkwee.core.base.KeyValueEnum;

public enum MessageSendErrorEnum implements KeyValueEnum{
	/**
	 * 返回错误编号	错误说明
-1	参数为空。信息、电话号码等有空指针，登陆失败
-12	有异常电话号码
-14	实际号码个数超过100
-999	服务器内部错误
-10001	用户登陆不成功(帐号不存在/停用/密码错误)
-10003	用户余额不足
-10011	信息内容超长
-10029	此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)
-10030	不能发送移动号码
-10031	手机号码(段)非法
-10057	IP受限
-10056	连接数超限 

//聚合短信--服务级错误码参照
205401 	错误的手机号码
205402 	错误的短信模板ID
205403 	网络错误,请重试
205404 	发送失败，具体原因请参考返回reason
205405 	号码异常/同一号码发送次数过于频繁
205406 	不被支持的模板
  	
 //聚合短信--系统级错误码
10001 	错误的请求KEY
10002 	该KEY无请求权限
10003 	KEY过期 
10004 	错误的OPENID
10005 	应用未审核超时，请提交认证
10007 	未知的请求源
10008 	被禁止的IP
10009 	被禁止的KEY
10011 	当前IP请求超过限制
10012 	请求超过次数限制
10013 	测试KEY超过请求限制
10014 	系统内部异常
10020 	接口维护
10021 	接口停用
	 */
	LOGINERROR(-1,"参数为空。信息、电话号码等有空指针，登陆失败"),
	MOBILEERROR(-12,"有异常电话号码"),
	MOBILECOUNTOVERFLOW(-14,"实际号码个数超过100"),
	SERVERINNERERROR(-999,"服务器内部错误"),
	ACCOUNTERROR(-10001,"用户登陆不成功(帐号不存在/停用/密码错误)"),
	OUTOFBALANCEERROR(-10003,"用户余额不足"),
	CONTENTTOLONG(-10011,"信息内容超长"),
	PORTERROR(-10029,"此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)"),
	FOBIDENMOBILEERROR(-10030,"不能发送移动号码"),
	MOBILEILLEGALEERROR(-10031,"手机号码(段)非法"),
	IPLIMITERROR(-10057,"IP受限"),
	CONNECTOUTERROR(-10056,"连接数超限"),
	
	JUHEMOBILERROR(205401,"错误的手机号码【聚合数据】"),
	JUHEERRORTMPID(205402,"错误的短信模板ID【聚合数据】"),
	JUHEERRORNETWORK(205403,"网络错误,请重试【聚合数据】"),
	JUHESENDERROR(205404,"发送失败，具体原因请参考返回reason【聚合数据】"),
	JUHEHAOMAERROR(205405,"号码异常/同一号码发送次数过于频繁【聚合数据】"),
	JUHEDONTMP(205406,"不被支持的模板【聚合数据】"),
	
	
	JUHEERRORKEY(10001,"错误的请求KEY【聚合数据】"),
	JUHENOTPERMISSION(10002,"该KEY无请求权限【聚合数据】"),
	JUHEKEYOUTTIME(10003,"KEY过期 【聚合数据】"),
	JUHEEERROROPENID(10004,"错误的OPENID【聚合数据】"),
	JUHSUBMITAUTHEN(10005,"应用未审核超时，请提交认证【聚合数据】"),
	JUHEUNKNOWNREQUESTSOURCE(10007,"未知的请求源【聚合数据】"),
	JUHEFORBITIP(10008,"被禁止的IP【聚合数据】"),
	JUHEFORBITKEY(10009,"被禁止的KEY【聚合数据】"),
	JUHEIPTIMEOUT(10011,"当前IP请求超过限制【聚合数据】"),
	JUHEREQUESTLIMIT(10012,"请求超过次数限制【聚合数据】"),
	JUHETESTKEYLIMIT(10013,"测试KEY超过请求限制【聚合数据】"),
	JUHESYSTEMERROR(10014,"系统内部异常【聚合数据】"),
	JUHEINTERFACEMAINTENANCE(10020,"接口维护【聚合数据】"),
	JUHEINTERFACEDISABLED(10021,"接口停用【聚合数据】");
	
	MessageSendErrorEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;
	
	
	public int getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}

}
