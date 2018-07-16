package com.linkwee.xoss.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageSendUtil {
	public final static Map<Long,String> errorInfoMap = new HashMap<Long,String>();
	static{
		errorInfoMap.put(Long.valueOf("-1"), "参数为空。信息、电话号码等有空指针，登陆失败");
		errorInfoMap.put(Long.valueOf("-12"), "有异常电话号码");
		errorInfoMap.put(Long.valueOf("-14"), "实际号码个数超过100");
		errorInfoMap.put(Long.valueOf("-999"), "服务器内部错误");
		errorInfoMap.put(Long.valueOf("-10001"), "用户登陆不成功(帐号不存在/停用/密码错误)");
		errorInfoMap.put(Long.valueOf("-10003"), "用户余额不足");
		errorInfoMap.put(Long.valueOf("-10011"), "信息内容超长");
		errorInfoMap.put(Long.valueOf("-10029"), "此用户没有权限从此通道发送信息(用户没有绑定该性质的通道，比如：用户发了小灵通的号码)");
		errorInfoMap.put(Long.valueOf("-10030"), "不能发送移动号码");
		errorInfoMap.put(Long.valueOf("-10031"), "手机号码(段)非法");
		errorInfoMap.put(Long.valueOf("-10057"), "IP受限");
		errorInfoMap.put(Long.valueOf("-10056"), "连接数超限");
		
		//聚合短信--服务级错误码参照
		errorInfoMap.put(Long.valueOf("205401"), "错误的手机号码【聚合数据】");
		errorInfoMap.put(Long.valueOf("205402"), "错误的短信模板ID【聚合数据】");
		errorInfoMap.put(Long.valueOf("205403"), "网络错误,请重试【聚合数据】");
		errorInfoMap.put(Long.valueOf("205404"), "发送失败，具体原因请参考返回reason【聚合数据】");
		errorInfoMap.put(Long.valueOf("205405"), "号码异常/同一号码发送次数过于频繁【聚合数据】");
		errorInfoMap.put(Long.valueOf("205406"), "不被支持的模板【聚合数据】");
		//聚合短信--系统级错误码
		errorInfoMap.put(Long.valueOf("10001"), "错误的请求KEY【聚合数据】");
		errorInfoMap.put(Long.valueOf("10002"), "该KEY无请求权限【聚合数据】");
		errorInfoMap.put(Long.valueOf("10003"), "KEY过期【聚合数据】");
		errorInfoMap.put(Long.valueOf("10004"), "错误的OPENID【聚合数据】");
		errorInfoMap.put(Long.valueOf("10005"), "应用未审核超时，请提交认证【聚合数据】");
		errorInfoMap.put(Long.valueOf("10007"), "未知的请求源【聚合数据】");
		errorInfoMap.put(Long.valueOf("10008"), "被禁止的IP【聚合数据】");
		errorInfoMap.put(Long.valueOf("10009"), "被禁止的KEY【聚合数据】");
		errorInfoMap.put(Long.valueOf("10011"), "当前IP请求超过限制【聚合数据】");
		errorInfoMap.put(Long.valueOf("10012"), "请求超过次数限制【聚合数据】");
		errorInfoMap.put(Long.valueOf("10013"), "测试KEY超过请求限制【聚合数据】");
		errorInfoMap.put(Long.valueOf("10014"), "系统内部异常【聚合数据】");
		errorInfoMap.put(Long.valueOf("10020"), "接口维护【聚合数据】");
		errorInfoMap.put(Long.valueOf("10021"), "接口停用【聚合数据】");
	}

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
		/*long[] errorCodeArry = {-1, -12, -14, -999,-10001,-10003,-10011,-10029,-1003,-10031,-10057,-10056};
		boolean ret = true;
		for (int i = 0; i < errorCodeArry.length; i++)
		{
			if (element == errorCodeArry[i])
			{
				ret = false;
				break;
			}
		}
		return ret;*/
		return errorInfoMap.get(element) == null ? true : false;
	
	}
	
	
}
