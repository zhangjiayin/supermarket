package com.xiaoniu.sms.service;

import com.xiaoniu.sms.req.SendEmailReq;
import com.xiaoniu.sms.util.SmsResult;

public interface IEmailService {
	
	public SmsResult<Object> sendEmailMsg(SendEmailReq param) throws Exception;
}
