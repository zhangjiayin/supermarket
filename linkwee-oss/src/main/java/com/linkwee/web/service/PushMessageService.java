package com.linkwee.web.service;

import com.linkwee.web.model.PushMessageInfo;

/**
 * 消息推送
 * 
 * @author xuzhao
 * @Date 2016年3月8日 上午9:53:55
 */
public interface PushMessageService {

	void add(PushMessageInfo pushMessageInfo);
	
}
