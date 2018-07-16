package com.xiaoniu.sms.service;

import com.xiaoniu.sms.req.PushAppMessageReq;
import com.xiaoniu.sms.req.PushMessageReq;
import com.xiaoniu.sms.req.PushSysMessageReq;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 推送消息
 * @author 颜彩云
 *
 */
public interface IPushMessageService {
	/**
	 * 推送消息
	 * @param pushMessageDto, @see PushMessageReq
	 * @return
	 */
	public SmsResult<Object> pushMessage(PushMessageReq pushMessageDto);
	
	/**
	 * 推送app消息
	 * @param req
	 * @return
	 */
	public SmsResult<Boolean> pushAppMessage(PushAppMessageReq req);
	
	/**
	 * 个人消息（站内信）
	 * @param req
	 * @return
	 */
	public SmsResult<Boolean> pushSysMessage(PushSysMessageReq req);
}
