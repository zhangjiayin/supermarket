package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.activity.response.OneYuanDrawRecordResponse;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.ActWheelWinningRecord;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月01日 10:55:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActWheelWinningRecordMapper extends GenericDao<ActWheelWinningRecord,Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<ActWheelWinningRecord> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);

	/**
	 * 已经抽奖的次数
	 * @param userId
	 * @return
	 */
	int queryHasDrawTimes(@Param("userId")String userId, @Param("activityId")String activityId);

	/**
	 * 投资小牛在线在活动期限内投资金额
	 * @param userId
	 * @return
	 */
	Double queryInvestTotalMoney(@Param("userId")String userId, @Param("startDate")String startDate, @Param("endDate")String endDate);

	/**
	 * 用户抽奖记录
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	List<ActWheelWinningRecord> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,RowBounds page);

	/**
	 * 理财师名下投资者活动期间投资总额
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Double queryInvestorHasInvestedTotalMoney(@Param("userId")String userId, @Param("startDate")String startDate, @Param("endDate")String endDate);

	/**
	 * 理财师名下投资者活动期间投资总额(排除一些机构)
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	Double queryInvestorHasInvestedTotalMoneyExceptSomePlatform(@Param("userId")String userId,@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList);

	/**
	 * 客户活动期间投资总额（排除一些机构）
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	String queryInvestedMoneyExceptSomePlatform(@Param("userId")String userId,@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList);

	/**
	 * 投资排行榜分页列表前50（结合伪造数据）
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @param page
	 * @return
	 */
	List<InvestRankingListResponse> investRankingList(@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList,RowBounds page);

	/**
	 * 投资排行榜（我的排名）
	 * @param userId
	 * @return
	 */
	InvestRankingListResponse rankingListmyRank(@Param("userId")String userId,@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList);

	/**
	 * 自己和直推理财师出单年化排行
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @param page
	 * @return
	 */
	List<InvestRankingListResponse> subAndSelfInvestRankingList(@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList,RowBounds page);

	/**
	 * 我的（自己和直推理财师）累计出单年化
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	InvestRankingListResponse subAndSelfInvestMyRank(@Param("userId")String userId, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("platformList")List<String> platformList);

}
