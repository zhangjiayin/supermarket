package com.linkwee.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkwee.web.dao.PushMessageDao;
import com.linkwee.web.model.PushMessageInfo;
import com.linkwee.web.service.PushMessageService;
@Service("pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {
	@Autowired
	private PushMessageDao pushMessageDao;

	@Override
	public void add(PushMessageInfo pushMessageInfo) {
		try {
			pushMessageDao.add(pushMessageInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}

}
