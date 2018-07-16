package com.linkwee.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.linkwee.api.request.cim.MyInvestrecordRequest;
import com.linkwee.api.request.cim.PlatformBounsRequest;
import com.linkwee.api.request.tc.CfplannerCustomerInvestRecordRequest;
import com.linkwee.api.response.HomePageInvestResponse;
import com.linkwee.api.response.cim.MyInvestrecordResponse;
import com.linkwee.api.response.tc.CfpOrderResponse;
import com.linkwee.api.response.tc.CustomerInvestProfitResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordResponse;
import com.linkwee.api.response.tc.CustomerInvestRecordStatisticResponse;
import com.linkwee.api.response.tc.CustomerTradeMsgResponse;
import com.linkwee.api.response.tc.HotInvestResponse;
import com.linkwee.api.response.tc.InvestRecordResponse;
import com.linkwee.api.response.tc.RepaymentResponse;
import com.linkwee.api.response.tc.TradeNewlyDynamicResponse;
import com.linkwee.core.base.api.PaginatorResponse;
import com.linkwee.core.generic.GenericService;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.openapi.request.InvestRecordReq;
import com.linkwee.web.model.CimProduct;
import com.linkwee.web.model.cim.CimProductInvestRecord;
import com.linkwee.web.model.crm.GoodTransResp;
import com.linkwee.web.model.vo.InvestRecordWrapper;
import com.linkwee.xoss.api.AppRequestHead;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： hxb
 * 
 * @创建时间：2016年07月14日 18:04:53
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface CimProductInvestRecordService extends GenericService<CimProductInvestRecord,Long>{

	/**
	 * 查询被推荐已投资用户的userId
	 * @param refRegCustomers
	 * @return
	 */
	List<CimProductInvestRecord> selectRefInvestRecord(List<String> refRegCustomers);
	
	/**
	 * 插入投资记录
	 * @param investRecordReq 投资记录信息
	 */
	void insertInvestRecordProcess(InvestRecordReq investRecordReq) throws Exception;
	
	
	/**
	 * 更新回款状态
	 * @param investRecordNo 投资交易流水号
	 * @param status 回款状态 2=回款中 3=回款成功
	 * @param repaymentTime 回款时间
	 * @param accurateProfit 精准收益
	 */
	void updateRepaymentStatus(String investRecordNo,Integer status,Date repaymentTime,BigDecimal repaymentAmount,BigDecimal accurateProfit);
	
	/**
	 * 获取客户历史累计投资总额
	 * @param userId 客户编号
	 * @return 投资总额
	 */
	Double queryCustomerInvestTotalAmount(String userId);
	/**
	 * 当前在投总额
	 * @param userId
	 * @return
	 */
	Double queryCurrInvestAmount(String userId);
	
	/**
	 * 当前在投总额(处理千万金额时候出现科学计数法)
	 * @param userId
	 * @return
	 */
	BigDecimal queryCurrInvestAmount2(String userId);
	
	/**
	 * 获取客户投资收益总额
	 * @param userId 客户编号
	 * @return 投资收益总额
	 */
	Double queryCustomerInvestTotalProfit(String userId);
	
	/**
	 * 查询客户投资记录 
	 * @param userId 客户编号
	 * @param status 投资状态
	 * @return
	 */
	PaginatorResponse<InvestRecordResponse> queryCustomerInvestRecord(String userId,Integer status,Page<InvestRecordResponse> page);
	
	
	/**
	 * 查询理财师用户投资总计
	 * @param customerInvestRecordRequest
	 * @return
	 */
	CustomerInvestRecordStatisticResponse queryCfplannerCustomerInvestRecordStatistic(CfplannerCustomerInvestRecordRequest customerInvestRecordRequest);
	
	/**
	 * 查询理财师用户投资信息
	 * @param customerInvestRecordRequest
	 * @return
	 */
	PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerCustomerInvestRecord(CfplannerCustomerInvestRecordRequest customerInvestRecordRequest);
	
	/**
	 * 查询理财师用户投资信息
	 * @param customerInvestRecordRequest
	 * @return
	 */
	PaginatorResponse<CustomerInvestRecordResponse> queryCfplannerInvestCustomerDetail(CfplannerCustomerInvestRecordRequest customerInvestRecordRequest);
	
	/**
	 * 查询理财师客户即将回款列表
	 * @param userId
	 * @return
	 */
	PaginatorResponse<RepaymentResponse> queryCustomerRepayment(String userId,String customerId,Page<RepaymentResponse> page);
	
	/**
	 * 查询用户所有交易动态
	 * @param customerId
	 * @param userId
	 * @param page
	 * @return
	 */
	PaginatorResponse<CustomerTradeMsgResponse> queryCustomerTradeMsg(String customerId,String userId,Page<CustomerTradeMsgResponse> page);
	
	/**
	 * 申购动态消息数量
	 * @param userId
	 * @return
	 */
	int queryCustomerInvestTradeMsgCount(String userId);
	
	/**
	 * 客户申购交易消息
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CustomerTradeMsgResponse> queryCustomerInvestTradeMsg(String userId,String customerId,Page<CustomerTradeMsgResponse> page);
	
	/**
	 * 赎回动态消息数量
	 * @param userId
	 * @return
	 */
	int queryCustomerRepaymentTradeMsgCount(String userId);
	/**
	 * 客户赎回交易消息
	 * @param userId
	 * @return
	 */
	PaginatorResponse<CustomerTradeMsgResponse> queryCustomerRepaymentTradeMsg(String userId,String customerId,Page<CustomerTradeMsgResponse> page);
	
	/**
	 * 理财师动态汇总
	 * @param page
	 * @param conditions
	 * @return
	 */
	PaginatorResponse<TradeNewlyDynamicResponse> queryCfpNewlyDynamic(Page<TradeNewlyDynamicResponse> page,String userId);
	
	/**
	 * 理财师动态汇总
	 * @param page
	 * @param conditions
	 * @return
	 */
	int queryCfpNewlyDynamicUnReadCount(String userId);
	
	/**
	 * 根据产品查询在投投资记录 
	 * @param product 产品集合
	 * @return
	 */
	List<InvestRecordWrapper> getInvestRecordByProduct(CimProduct product);
	
	/**
	 * 根据产品查询在投投资记录 
	 * @param products 产品集合
	 * @return
	 */
	List<InvestRecordWrapper> getInvestRecordByProducts(List<CimProduct> productIds);
	
	/**
	 * 更新募集期 投资记录到期日期
	 * @param investRecord
	 * @return
	 */
	boolean updateInvestRecordEndTimeByProductId(CimProductInvestRecord investRecord)throws Exception;
	
	/**
	 * 获取投资记录数量
	 * @param userId
	 * @return
	 */
	Map<String, String> getInvestRecordCounts(String userId); 
	
	/**
	 * 用户在平台的投资记录数
	 * @author yalin 
	 * @date 2016年12月21日 上午10:54:01  
	 * @param userId
	 * @param platfromId
	 * @return
	 */
	int queryUserPlatfromInvestCount(String userId,String platfromId);
	
	/**
	 * 查询用户投资收益
	 * @param userId
	 * @return
	 */
	CustomerInvestProfitResponse getInvestProfit(String userId);

	/**
	 * 更新募集失败的投资记录的预期收益为0
	 * @param investId
	 */
	void updateBorrowRefuseInvestProfit(String investId);

	/**
	 * 理财师最近两个月的最新200个出单
	 * @return
	 */
	List<CfpOrderResponse> selectNewestTop200();

	/**
	 * 我的投资记录v4.0
	 * @param appRequestHead
	 * @param myInvestrecordRequest
	 * @return
	 */
	PaginatorResponse<MyInvestrecordResponse> myInvestrecord(AppRequestHead appRequestHead,MyInvestrecordRequest myInvestrecordRequest);

	/**
	 * 查询客户未读的投资记录条数
	 * @param userId
	 * @param investType 0-在投产品 1-已到期产品
	 * @return
	 */
	Integer queryNotReadRecord(String userId, int investType);

	/**
	 *  出单详情
	 */
	GoodTransResp getGoodTrans(String userId);
	
	/**
	 *  往期出单
	 */
	PaginatorResponse<GoodTransResp> queryOldGoodTransList(Page<GoodTransResp> page,
			Map<String, Object> conditions);

	/**
	 *  根据订单号查询出单详情
	 */
	GoodTransResp getGoodTransByInvestId(String billId);

	/**
	 *  查理财师的最近一笔交易时间
	 */
	Date selectTranRecordDate(String userId);

	/**
	 *  最近一笔回款日期
	 */
	Date selectPaymentDate(String userId);

	/**
	 *  本月收益
	 */
	BigDecimal queryMonthIncome(String thisMonth,String userId);
	
	/**
	 *  跟理财师有关的最近交易记录（包括下级投资）
	 */
	Date newTranRecordDate(String userId);

	/**
	 *  汇总收入
	 */
	BigDecimal queryAllTotalIncome(String userId);

	/**
	 * 满足签到条件的总投资
	 * @param signTime
	 * @return
	 */
	BigDecimal querySignRecordAmount(String userId,Date signTime);

	/**
	 * 投资记录平台奖励
	 * @param platformBounsRequest
	 */
	void updatePlatformBouns(PlatformBounsRequest platformBounsRequest);

	/**
	 * 满足签到条件的总投资 v4.6.0
	 * @param userId
	 * @param signTime
	 * @return
	 */
	BigDecimal querySignRecordAmount460(String userId, Date signTime);

	/**
	 * 猎财热投列表
	 * @return
	 */
	List<HotInvestResponse> queryHotInvestList();
	
	/**
	 * 查询用户投资次数
	 * @param userId
	 * @return
	 */
	int queryUserInvestCount(String userId);

	 /**
	  * 用户在投机构数量
	  * @param userId
	  * @param isGrayUser
	  * @return
	  */
	int investingOrgCount(String userId, Boolean isGrayUser);

	 /**
	  * 首页投资记录
	  * @return
	  */
	List<HomePageInvestResponse> homepageInvestList();

 }
