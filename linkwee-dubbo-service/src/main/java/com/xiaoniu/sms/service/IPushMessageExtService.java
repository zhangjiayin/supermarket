package com.xiaoniu.sms.service;

import com.xiaoniu.sms.req.PushMessageExtReq;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 推送消息
 * @author 颜彩云
 *
 */
public interface IPushMessageExtService {
	/**
	 * 推送消息
	 * @param pushMessageDto, @see PushMessageReq
	 * @return
	 */
	public SmsResult<Object> pushMessageExt(PushMessageExtReq pushExtReq);
	
	/**
	 * 推送消息(全部)
	 * @param pushMessageDto, @see PushMessageReq
	 * @return
	 */
	public SmsResult<Object> pushMessageAll(PushMessageExtReq pushExtReq);
}
