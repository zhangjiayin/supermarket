package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActChristmasAwardingRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:55:08
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActChristmasAwardingRecordMapper extends GenericDao<ActChristmasAwardingRecord,Long>{
	
	/**
	 * 已使用的袜子数量
	 * @param userId
	 * @return
	 */
	int queryUsedSocks(@Param("userId")String userId);

	/**
	 * 兑奖记录
	 * @param userId
	 * @return
	 */
	List<ActChristmasAwardingRecord> queryAwardingRecord(@Param("userId")String userId);

	/**
	 * 客户活动期间出单年化（排除一些机构）
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	String queryInvestedMoneyExceptSomePlatform(@Param("userId")String userId,@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList);
}
