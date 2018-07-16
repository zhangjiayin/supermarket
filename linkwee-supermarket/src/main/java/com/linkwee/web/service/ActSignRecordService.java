package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.List;

import com.linkwee.api.request.act.SignCalendarRequest;
import com.linkwee.api.response.act.BountyDetailResponse;
import com.linkwee.api.response.act.SignRecordResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.base.api.PaginatorRequest;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericService;
import com.linkwee.web.model.ActSignRecord;
 /**
 * 
 * @描述： ActSignRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月13日 16:59:18
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActSignRecordService extends GenericService<ActSignRecord,Long>{
	
	/**
	 * 今日是否已签到
	 * @param userId
	 * @param appType
	 * @return
	 */
	boolean hasSignedToday(String userId, Integer appType);

	/**
	 * 最新的签到
	 * @param userId
	 * @param appType
	 * @return
	 */
	ActSignRecord queryNewestSign(String userId, Integer appType);

	/**
	 * 签到（记录并发放奖励）
	 * @param userId
	 * @param appType
	 * @return
	 */
	BaseResponse sign(String userId, Integer appType);

	/**
	 * 分享成功发放奖励
	 * @param userId
	 * @param appType
	 * @return
	 */
	BaseResponse share(String userId, Integer appType);

	/**
	 * 今天签到信息
	 * @param userId
	 * @param appType
	 * @return
	 */
	ActSignRecord todaySign(String userId, Integer appType);

	/**
	 * 第一笔签到
	 * @param userId
	 * @param appType
	 * @return
	 */
	ActSignRecord queryLatestSign(String userId, Integer appType);

	/**
	 * 签到记录
	 * @param userId
	 * @param appType
	 * @param request 
	 * @return
	 */
	PaginatorResponse<SignRecordResponse> querySignRecords(String userId, Integer appType, PaginatorRequest request);

	/**
	 * 签到日历
	 * @param userId
	 * @param appType
	 * @param request
	 * @return
	 */
	List<String> querySignCalendar(String userId, Integer appType,SignCalendarRequest request);

	/**
	 * 发放奖励金
	 * @param userId
	 * @param userType
	 * @param amount
	 * @param bountyType
	 */
	void sendBounty(String userId, Integer userType, BigDecimal amount,int bountyType);

	/**
	 * 奖励金明细
	 * @param userId
	 * @param appType
	 * @param request
	 * @return
	 */
	PaginatorResponse<BountyDetailResponse> queryBountyDetails(String userId,Integer appType, PaginatorRequest request);
}
