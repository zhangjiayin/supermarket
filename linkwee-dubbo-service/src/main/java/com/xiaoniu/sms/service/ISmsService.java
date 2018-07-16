package com.xiaoniu.sms.service;

import java.util.List;

import com.xiaoniu.sms.domain.SmsRecordRlt;
import com.xiaoniu.sms.req.GetSendStatusReq;
import com.xiaoniu.sms.req.SendMsgReq;
import com.xiaoniu.sms.util.DataGridResult;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 梦网发送短信
 * @author 颜彩云
 *
 */
public interface ISmsService {
	/**
	 * 发送短信
	 * @param param 调用发送短信接口的相关参数, @see SendMsgReq
	 * @throws Exception
	 */
	public SmsResult<Object> sendMsg(SendMsgReq param)throws Exception;
	
	public DataGridResult<List<SmsRecordRlt>> getSendStatus(GetSendStatusReq req);
}