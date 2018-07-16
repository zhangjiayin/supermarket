package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.activity.response.OneYuanDrawRecordResponse;
import com.linkwee.api.activity.response.UserWinningRecordResponse;
import com.linkwee.api.response.activity.FortunePrizeResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.model.ActWheelWinningRecord;
import com.linkwee.web.service.ActOneYuanDrawRecordService;
 /**
 * 
 * @描述： ActOneYuanDrawRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月04日 11:38:32
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActOneYuanDrawRecordService extends GenericService<ActOneYuanDrawRecord,Long>{

	/**
	 * 一元抽奖活动 插入抽奖记录(带活动标识)
	 * @param baseLottery
	 * @param i
	 * @param userId
	 * @param mobile
	 * @param userType
	 * @param activityId
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	FortunePrizeResponse insertOneYuanDrawRecord(BaseLottery baseLottery, Integer i,String userId, String mobile, Integer userType, String activityId,String remark,int signTimes, int liecaiTimes,String bizId,Integer drawType)throws Exception;

	/**
	 * 一元抽奖活动 插入抽奖记录 十连抽
	 * @param baseLotteryList
	 * @param i
	 * @param userId
	 * @param mobile
	 * @param userType
	 * @param activityId
	 * @param remark
	 * @param signTimes
	 * @param liecaiTimes
	 * @return
	 * @throws Exception
	 */
	FortunePrizeResponse batchInsertOneYuanDrawRecord(List<BaseLottery> baseLotteryList, Integer i,String userId, String mobile, Integer userType, String activityId,String remark,int signTimes, int liecaiTimes)throws Exception;

	/**
	 * 一元抽奖跑马灯显示数据 包括中奖记录以及十连抽
	 * @return
	 */
	List<OneYuanDrawRecordResponse> queryOneYuanDrawRecord(String activityId);

	/**
	 * 查询幸运分总数
	 * @return
	 */
	Integer queryMaxId();

	/**
	 * 用户获奖记录
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	PaginatorResponse<UserWinningRecordResponse> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Integer isfortunePrize,Page<UserWinningRecordResponse> page);

	/**
	 * 平台幸运获奖记录
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	PaginatorResponse<FortunePrizeResponse> queryFortunePrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Page<FortunePrizeResponse> page);
}
