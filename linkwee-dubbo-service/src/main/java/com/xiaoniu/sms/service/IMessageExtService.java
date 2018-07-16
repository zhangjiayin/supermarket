package com.xiaoniu.sms.service;

import java.util.HashMap;

import com.xiaoniu.sms.domain.MessageTemplateRlt;
import com.xiaoniu.sms.req.GetMessageTemplateReq;
import com.xiaoniu.sms.req.GetUnreadCountReq;
import com.xiaoniu.sms.req.UpdateSpreadMessageReq;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 消息扩展接口
 * @author 颜彩云
 *
 */
public interface IMessageExtService {
	/**
	 * 更新推广消息（更新为已读）
	 * @param req
	 * @return
	 */
	public SmsResult<Boolean> updateSpreadMessage(UpdateSpreadMessageReq req);
	
	/**
	 * 获取未读消息条数,总未读数（total），公告未读数（spread），我的消息未读数（system）
	 * @param req
	 * @return
	 */
	public SmsResult<HashMap<String, Integer>> getUnreadCount(GetUnreadCountReq req);
	
	/**
	 * 获取消息模板
	 * @param req
	 * @return
	 */
	public SmsResult<MessageTemplateRlt> getMessageTemplate(GetMessageTemplateReq req);
	
}

 