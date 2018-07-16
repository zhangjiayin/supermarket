package com.linkwee.web.service;

import java.util.List;

import com.linkwee.api.activity.BaseLottery;
import com.linkwee.api.activity.response.OneYuanDrawRecordResponse;
import com.linkwee.api.response.act.InvestRankingListResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActWheelWinningRecord;
 /**
 * 
 * @描述： ActWheelWinningRecordService服务接口
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年12月01日 10:55:51
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ActWheelWinningRecordService extends GenericService<ActWheelWinningRecord,Long>{

	/**
	 * 查询ActWheelWinningRecord列表,为data-tables封装
	 * @param dataTable
	 * @return
	 */
	DataTableReturn selectByDatatables(DataTable dataTable);

	/**
	 * 投资小牛在线在活动期限内投资金额
	 * @param userId
	 * @return
	 */
	Double queryInvestTotalMoney(String userId,String startDate,String endDate);

	/**
	 * 插入抽奖记录
	 * @param baseLottery
	 * @param i
	 * @param userId
	 * @param mobile
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType) throws Exception;
	
	/**
	 * 插入抽奖记录--春节活动
	 * @param baseLottery
	 * @param i
	 * @return
	 */
	Integer insertDrawRecord(BaseLottery baseLottery, Integer i, String userId, String mobile, Integer userType, String activityId) throws Exception;

	/**
	 * 用户中奖记录分页
	 * @param actWheelWinningRecord
	 * @param page
	 * @return
	 */
	PaginatorResponse<ActWheelWinningRecord> queryUserPrizeRecord(ActWheelWinningRecord actWheelWinningRecord,Page<ActWheelWinningRecord> page);

	/**
	 * 查询活动已经抽奖的次数
	 * @param userId
	 * @param activityId
	 * @return
	 */
	Integer queryHasDrawTimes(String userId, String activityId);

	/**
	 * 理财师名下投资者活动期间投资的总金额
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Double queryInvestorHasInvestedTotalMoney(String userId, String startDate,String endDate);
	
	/**
	 * 理财师名下投资者活动期间投资的总金额(排除一些机构)
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Double queryInvestorHasInvestedTotalMoneyExceptSomePlatform(String userId, String startDate,String endDate,List<String> platformList);

	/**
	 * 插入抽奖记录(带活动标识)
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
	Integer insertDrawRecordWithRemark(BaseLottery baseLottery, Integer i,String userId, String mobile, Integer userType, String activityId,String remark) throws Exception;

	/**
	 * 客户活动期间投资总额（排除一些机构）
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	String queryInvestedMoneyExceptSomePlatform(String userId, String startDate,String endDate,List<String> platformList);

	/**
	 * 投资排行榜（结合伪造数据）
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @return
	 */
	PaginatorResponse<InvestRankingListResponse> investRankingList(String startDate,String endDate,List<String> platformList,Page<InvestRankingListResponse> page);

	/**
	 * 投资排行榜（结合伪造数据）用户排名
	 * @param userId
	 * @return
	 */
	InvestRankingListResponse rankingListmyRank(String userId,String startDate,String endDate,List<String> platformList);

	/**
	 * 自己和直推理财师出单年化排行
	 * @param startDate
	 * @param endDate
	 * @param platformList
	 * @param page
	 * @return
	 */
	PaginatorResponse<InvestRankingListResponse> subAndSelfInvestRankingList(String startDate,String endDate,List<String> platformList,Page<InvestRankingListResponse> page);

	/**
	 * 我的（自己和直推理财师）累计出单年化
	 * @param startDate
	 * @param endDate
	 * @param string 
	 * @param platformList
	 * @return
	 */
	InvestRankingListResponse subAndSelfInvestMyRank(String userId,String startDate,String endDate, List<String> platformList);

}
