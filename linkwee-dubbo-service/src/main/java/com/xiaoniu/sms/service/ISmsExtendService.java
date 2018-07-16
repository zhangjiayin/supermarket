package com.xiaoniu.sms.service;

import java.util.List;

import com.xiaoniu.sms.req.SmsStatusReq;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 短信相关扩展接口（不提供dubbo服务）
 * @author 颜彩云
 *
 */
public interface ISmsExtendService {
	/**
	 * 获取上行/状态报告
	 * @param param 调用获取上行/状态报告接口的相关参数
	 * @return
	 * @throws Exception
	 */
	public SmsResult<List<String>> getMsgDeliver(SmsStatusReq param,String partnerId) throws Exception;
	
	/**
	 * 查询剩余可发送短信数量
	 * @return
	 * @throws Exception
	 */
	public SmsResult<String> getLeftMsg(String partnerId) throws Exception;
}
