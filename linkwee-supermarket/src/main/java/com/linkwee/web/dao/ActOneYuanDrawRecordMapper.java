package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.activity.response.OneYuanDrawRecordResponse;
import com.linkwee.api.activity.response.UserWinningRecordResponse;
import com.linkwee.api.response.activity.FortunePrizeResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.web.model.ActOneYuanDrawRecord;
import com.linkwee.web.model.ActWheelWinningRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2018年04月04日 11:38:32
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActOneYuanDrawRecordMapper extends GenericDao<ActOneYuanDrawRecord,Long>{

	/**
	 * 一元抽奖跑马灯数据  包括抽奖记录和十连抽信息
	 * @return
	 */
	List<OneYuanDrawRecordResponse> queryOneYuanDrawRecord(@Param("activityId")String activityId);

	/**
	 * 幸运分总数
	 * @return
	 */
	int queryMaxId();

	/**
	 * 用户幸运奖获奖记录
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	List<UserWinningRecordResponse> queryUserFortunePrizeRecord(ActWheelWinningRecord actWheelWinningRecord,RowBounds page);

	/**
	 * 用户中奖记录
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	List<UserWinningRecordResponse> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,RowBounds page);

	/**
	 * 幸运奖中奖记录
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	List<FortunePrizeResponse> queryFortunePrizeRecord(ActWheelWinningRecord actWheelWinningRecord,RowBounds page);
	
}
