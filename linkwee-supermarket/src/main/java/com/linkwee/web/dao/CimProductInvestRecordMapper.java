package com.linkwee.web.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.linkwee.api.response.HomePageInvestResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.api.request.cim.MyInvestrecordRequest;
import com.linkwee.api.request.cim.PlatformBounsRequest;
import com.linkwee.api.request.crm.InvestCalendarDetailRequest;
import com.linkwee.api.request.crm.InvestCalendarRequest;
import com.linkwee.api.request.crm.InvestCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RePaymentCalendarStatisticsRequest;
import com.linkwee.api.request.crm.RepamentCalendarRequest;
import com.linkwee.api.response.cim.MyInvestrecordResponse;
import com.linkwee.api.response.crm.CalendarStatisticsResponse;
import com.linkwee.api.response.crm.InvestCalendarDetailResponse;
import com.linkwee.api.response.crm.InvestCalendarResponse;
import com.linkwee.api.response.crm.P2pInvestCalendarStatisticsResponse;
import com.linkwee.api.response.crm.RepamentCalendarResponse;
import com.linkwee.api.response.tc.CfpOrderResponse;
import com.linkwee.api.response.tc.CustomerInvestProfitResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordStatisticResponse;
import com.linkwee.api.response.tc.CustomerTradeMsgResponse;
import com.linkwee.api.response.tc.HotInvestResponse;
import com.linkwee.api.response.tc.InvestRecordResponse;
import com.linkwee.api.response.tc.RepaymentResponse;
import com.linkwee.api.response.tc.TradeNewlyDynamicResponse;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.crm.GoodTransResp;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年07月20日 18:15:15
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInvestRecordMapper extends GenericDao<CimProductInvestRecord,Long>{
	
	/**
	 * 查询被推荐已投资用户的userId
	 * @param refRegCustomers
	 * @return
	 */
	List<CimProductInvestRecord> selectRefInvestRecord(List<String> refRegCustomers);
	
	
	/**
	 * 更新回款状态
	 * @param investRecordNo 投资交易流水号
	 * @param status 回款状态 2=回款中 3=回款成功
	 * @param repaymentTime 回款时间
	 * @param accurateProfit 精准收益
	 */
	void updateRepaymentStatus(@Param("investRecordNo") String investRecordNo,@Param("status")Integer status,@Param("repaymentTime")Date repaymentTime,@Param("repaymentAmount")BigDecimal repaymentAmount,@Param("accurateProfit")BigDecimal accurateProfit);
	
	
	/**
	 * 获取客户投资总额
	 * @param userId 客户编号
	 * @return
	 */
	BigDecimal queryCustomerInvestTotalAmount(@Param("userId")String userId);
	
	/**
	 * 获取客户投资收益
	 * @param userId 客户编号
	 * @return
	 */
	BigDecimal queryCustomerInvestTotalProfit(@Param("userId")String userId);
	
	/**
	 * 获取客户投资记录
	 * @param userId 客户编号
	 * @param status
	 * @param page
	 * @return
	 */
	List<InvestRecordResponse> queryCustomerInvestRecord(@Param("userId")String userId,@Param("status")Integer status,RowBounds page);
	/**
	 * 查询用户首次投资时间
	 * @param userId
	 * @return
	 */
	int queryFirsInvestTime(@Param("userId") String userId,@Param("orgNumber")String orgNumber);
	
	/**
	 * 查询理财师用户投资总计
	 * @param customerInvestRecordRequest
	 * @return
	 */
	CustomerInvestRecordStatisticResponse queryCfplannerCustomerInvestRecordStatistic(@Param("userId")String userId,@Param("dateType")Integer dateType,@Param("date")Date date);
	
	
	
	/**
	 * 查询理财师用户投资信息
	 * @param userId
	 * @param dateType
	 * @param date
	 * @param page
	 * @return
	 */
	List<CustomerInvestRecordResponse> queryCfplannerCustomerInvestRecord(@Param("userId")String userId,@Param("dateType")Integer dateType,@Param("date")Date date,RowBounds page);
	
	/**
	 * 查询理财师用户投资信息
	 * @param userId
	 * @param dateType
	 * @param date
	 * @param page
	 * @return
	 */
	List<CustomerInvestRecordResponse> queryCfplannerInvestCustomerDetail(@Param("userId")String userId,@Param("dateType")Integer dateType,@Param("date")Date date,RowBounds page);
	
	
	/**
	 * 查询理财师客户即将回款列表
	 * @param userId
	 * @return
	 */
	List<RepaymentResponse> queryCustomerRepayment(@Param("userId")String userId,@Param("customerId")String customerId,RowBounds page);
	
	/**
	 * 申购动态消息数量
	 * @param time
	 * @param userId
	 * @return
	 */
	int queryCustomerInvestTradeMsgCount(@Param("readTime") Date time,@Param("userId")String userId);
	
	/**
	 * 客户申购交易消息
	 * @param userId
	 * @param readTime
	 * @param page
	 * @return
	 */
	List<CustomerTradeMsgResponse> queryCustomerInvestTradeMsg(@Param("readTime")String readTime,@Param("customerId")String customerId,@Param("userId")String userId,RowBounds page);
	
	
	/**
	 * 赎回动态消息数量
	 * @param time
	 * @param userId
	 * @return
	 */
	int queryCustomerRepaymentTradeMsgCount(@Param("readTime") Date time,@Param("userId")String userId);
	/**
	 * 客户赎回交易消息
	 * @param userId
	 * @param readTime
	 * @param page
	 * @return
	 */
	List<CustomerTradeMsgResponse> queryCustomerRepaymentTradeMsg(@Param("readTime")String readTime,@Param("customerId")String customerId,@Param("userId")String userId,RowBounds page);
	
	/**
	 * 查询用户交易动态
	 * @param customerId
	 * @param userId
	 * @param page
	 * @return
	 */
	List<CustomerTradeMsgResponse> queryCustomerTradeMsg(@Param("customerId")String customerId,@Param("userId")String userId,RowBounds page);
	/**
	 * 理财师最新动态汇总
	 * @param readTime
	 * @param userId
	 * @param page
	 * @return
	 */
	List<TradeNewlyDynamicResponse> queryCfpNewlyDynamic(@Param("userId")String userId ,@Param("date")String date,RowBounds page);

	/**
	 * 当前在投总额
	 * @param userId
	 * @return
	 */
	BigDecimal queryCurrInvestAmount(String userId);
	/**
	 * 理财师最新动态汇总 未读count
	 * @param userId
	 * @return
	 */
	int queryCfpNewlyDynamicUnReadCount(@Param("userId")String userId,@Param("date")String date);
	
	/**
	 * 根据产品编号查询在投投资记录 
	 * @param productIds 产品编号集合
	 * @return
	 */
	List<CimProductInvestRecord> getInvestRecordByProductIds(@Param("productIds")Set<String> productIds);
	/**
	 * 根据机构编码查询机构当月的销售总额
	 */
	BigDecimal queryMonthInvestAmount(@Param("orgNumber")String orgNumber);
	
	/**
	 * 查询用户投资次数
	 * @param userId
	 * @return
	 */
	int queryUserInvestCount(@Param("userId")String userId);
	
	int queryUserPlatfromInvestCount(@Param("userId")String userId,@Param("platfromId")String platfromId);
	
	/**
	 * 更新募集期 投资记录到期日期
	 * @param investRecord
	 * @return
	 */
	int updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord);
	
	/**
	 * 获取用户投资记录数量
	 * @param userId
	 * @return
	 */
	Map<String, String> getInvestRecordCounts(@Param("userId")String userId);
	
	List<String> queryPlatfromInvestCount(@Param("userId")String userId,@Param("platfromIds")Set<String> platfromIds);
	
	CustomerInvestProfitResponse getInvestProfit(@Param("userId")String userId);

	/**
	 * 更新募集失败的投资记录的预期收益为0
	 * @param investId
	 */
	void updateBorrowRefuseInvestProfit(@Param("investRecordNo")String investId);

	/**
	 * 理财师最近两个月最新出单200条
	 * @return
	 */
	List<CfpOrderResponse> selectNewestTop200();
	
	/**
	 * 我的投资记录 v4.0
	 * @param myInvestrecordRequest
	 * @param page
	 * @return
	 */
	List<MyInvestrecordResponse> myInvestrecord(MyInvestrecordRequest myInvestrecordRequest,Page<MyInvestrecordResponse> page);

	/**
	 * 查询客户未读的投资记录条数
	 * @param userId
	 * @param investType	0-在投产品 1-已到期产品
	 * @param lastReaddate	最后一次查看列表时间
	 * @return
	 */
	Integer queryNotReadRecord(@Param("userId")String userId, @Param("investType")int investType,@Param("lastReaddate")String lastReaddate);

	/**
	 * 出单详情
	 */
	GoodTransResp getGoodTrans(@Param("userId")String userId);

	/**
	 * 往期出单
	 */
	List<GoodTransResp> queryOldGoodTransList(Page<GoodTransResp> page, Map<String, Object> conditions);

	/**
	 *  根据订单号查询出单详情
	 */
	GoodTransResp getGoodTransByInvestId(@Param("billId")String billId);

	/**
	 * 查询 回款日历
	 * @param repamentCalendarRequest
	 * @param page
	 * @return
	 */
	List<RepamentCalendarResponse> queryRepamentCalendarPageList(RepamentCalendarRequest repamentCalendarRequest,Page<RepamentCalendarResponse> page);

	/**
	 * 查询交易日历
	 * @param investCalendarRequest
	 * @param page
	 * @return
	 */
	List<InvestCalendarResponse> queryInvestCalendarPageList(InvestCalendarRequest investCalendarRequest,Page<InvestCalendarResponse> page);

	/**
	 * 查询交易详情
	 * @param investCalendarDetailRequest
	 * @return
	 */
	InvestCalendarDetailResponse queryInvestCalendarDetail(InvestCalendarDetailRequest investCalendarDetailRequest);

	/**
	 * 查理财师的最近一笔交易时间 
	 */
	Date selectTranRecordDate(@Param("userId")String userId);

	/**
	 * 查理财师的最近一笔回款时间 
	 */
	Date selectPaymentDate(@Param("userId")String userId);

	/**
	 * 本月收入
	 */
	BigDecimal queryMonthIncome(@Param("thisMonth")String thisMonth, @Param("userId")String userId);

	/**
	 * 跟理财师有关的最近交易记录（包括下级投资）
	 * */
	Date newTranRecordDate(@Param("userId")String userId);

	/**
	 * 累计收入
	 */
	BigDecimal queryAllTotalIncome(@Param("userId")String userId);

	/**
	 * 交易日历统计数量-网贷
	 * @param investCalendarStatisticsRequest
	 * @return
	 */
	List<CalendarStatisticsResponse> investCalendarStatisticsNumber(InvestCalendarStatisticsRequest investCalendarStatisticsRequest);

	/**
	 * 交易日历统计金额-网贷
	 * @param investCalendarStatisticsRequest
	 * @return
	 */
	P2pInvestCalendarStatisticsResponse investCalendarStatisticsTotal(InvestCalendarStatisticsRequest investCalendarStatisticsRequest);

	/**
	 * 回款日历统计金额-网贷
	 * @param rePaymentCalendarStatisticsRequest
	 * @return
	 */
	BigDecimal repamentCalendarStatisticsTotal(RePaymentCalendarStatisticsRequest rePaymentCalendarStatisticsRequest);
	
	/**
	 * 回款日历统计数量-网贷
	 * @param rePaymentCalendarStatisticsRequest
	 * @return
	 */
	List<CalendarStatisticsResponse> repamentCalendarStatisticsNumber(RePaymentCalendarStatisticsRequest rePaymentCalendarStatisticsRequest);

	/**
	 * 满足签到条件的总投资
	 * @param signTime
	 * @return
	 */
	BigDecimal querySignRecordAmount(@Param("userId")String userId,@Param("startTime")String startTime);
	
	/**
	 * 投资记录平台奖励
	 * @param platformBounsRequest
	 */
	void updatePlatformBouns(PlatformBounsRequest platformBounsRequest);

	/**
	 * 满足签到条件的总投资 v4.6.0
	 * @param userId
	 * @param startTime
	 * @return
	 */
	BigDecimal querySignRecordAmount460(@Param("userId")String userId,@Param("startTime")String startTime);

	/**
	 * 猎财热投
	 * @return
	 */
	List<HotInvestResponse> queryHotInvestList();

	 /**
	  * 在投机构数量
	  * @param userId
	  * @param isGrayUser
	  * @return
	  */
	int investingOrgCount(@Param("userId")String userId, @Param("isGrayUser")Boolean isGrayUser);

	 /**
	  * 首页投资记录
	  * @return
	  */
	List<HomePageInvestResponse> homepageInvestList();

 }
