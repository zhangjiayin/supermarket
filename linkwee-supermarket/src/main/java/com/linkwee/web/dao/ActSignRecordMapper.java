package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.response.act.BountyDetailResponse;
import com.linkwee.api.response.act.SignRecordResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActSignRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月13日 16:59:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActSignRecordMapper extends GenericDao<ActSignRecord,Long>{
	
	/**
	 * 
	 * @param signRecord
	 * @return
	 */
	ActSignRecord todaySign(ActSignRecord signRecord);

	/**
	 * 最新的签到
	 * @param signRecord
	 * @return
	 */
	ActSignRecord queryNewestSign(ActSignRecord signRecord);

	/**
	 * 本周分享翻倍次数
	 * @param signRecord
	 * @return
	 */
	int shareDoubleTimes(ActSignRecord signRecord);

	/**
	 * 第一笔签到
	 * @param signRecord
	 * @return
	 */
	ActSignRecord queryLatestSign(ActSignRecord signRecord);

	/**
	 * 签到记录
	 * @param userId
	 * @param appType
	 * @param page
	 * @return
	 */
	List<SignRecordResponse> querySignRecords(@Param("userId")String userId, @Param("userType")Integer userType,Page<SignRecordResponse> page);

	/**
	 * 签到日历
	 * @param userId
	 * @param appType
	 * @param signTime
	 * @return
	 */
	List<String> querySignCalendar(@Param("userId")String userId, @Param("userType")Integer userType,@Param("signTime")String signTime);

	/**
	 * 奖励金明细
	 * @param userId
	 * @param appType
	 * @param page
	 * @return
	 */
	List<BountyDetailResponse> queryBountyDetails(@Param("userId")String userId, @Param("userType")Integer userType,RowBounds page);
}
