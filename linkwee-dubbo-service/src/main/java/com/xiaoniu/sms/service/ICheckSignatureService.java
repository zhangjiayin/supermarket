package com.xiaoniu.sms.service;

import java.util.TreeMap;

import com.xiaoniu.sms.util.SmsResult;

/**
 * 检查签名服务
 * @author 颜彩云
 *
 */
public interface ICheckSignatureService {
	/**
	 * 检查签名
	 * @param paramTreeMap http接口参数
	 * @param partnerId 业务id
	 * @param signature http接口传入的签名
	 * @return
	 */
	public SmsResult<Object> checkSignature(TreeMap<String, String> paramTreeMap,String partnerId,String signature);
	
}
