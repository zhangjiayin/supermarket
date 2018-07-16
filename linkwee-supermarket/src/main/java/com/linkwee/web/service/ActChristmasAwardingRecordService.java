package com.linkwee.web.service;

import java.util.List;

import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActChristmasAwardingRecord;
 /**
 * 
 * @描述： ActChristmasAwardingRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:55:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActChristmasAwardingRecordService extends GenericService<ActChristmasAwardingRecord,Long>{

	/**
	 * 已使用的袜子数量
	 * @param userId
	 * @return
	 */
	int queryUsedSocks(String userId);

	/**
	 * 兑奖记录
	 * @param userId
	 * @return
	 */
	List<ActChristmasAwardingRecord> queryAwardingRecord(String userId);

	/**
	 * 客户活动期间投资总额（排除一些机构）
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	String queryInvestedMoneyExceptSomePlatform(String userId, String startDate,String endDate,List<String> platformList);
}
