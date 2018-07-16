package com.linkwee.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.ActChristmasAwardingRecordMapper;
import com.linkwee.web.model.ActChristmasAwardingRecord;
import com.linkwee.web.service.ActChristmasAwardingRecordService;


 /**
 * 
 * @描述：ActChristmasAwardingRecordService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:55:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actChristmasAwardingRecordService")
public class ActChristmasAwardingRecordServiceImpl extends GenericServiceImpl<ActChristmasAwardingRecord, Long> implements ActChristmasAwardingRecordService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActChristmasAwardingRecordServiceImpl.class);
	
	@Resource
	private ActChristmasAwardingRecordMapper actChristmasAwardingRecordMapper;
	
	@Override
    public GenericDao<ActChristmasAwardingRecord, Long> getDao() {
        return actChristmasAwardingRecordMapper;
    }

	@Override
	public int queryUsedSocks(String userId) {
		return actChristmasAwardingRecordMapper.queryUsedSocks(userId);
	}

	@Override
	public List<ActChristmasAwardingRecord> queryAwardingRecord(String userId) {
		return actChristmasAwardingRecordMapper.queryAwardingRecord(userId);
	}

	@Override
	public String queryInvestedMoneyExceptSomePlatform(String userId,String startDate, String endDate, List<String> platformList) {
		return actChristmasAwardingRecordMapper.queryInvestedMoneyExceptSomePlatform(userId,startDate,endDate,platformList);
	}

}
