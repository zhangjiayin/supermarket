package com.linkwee.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkwee.core.base.BaseDao;
import com.linkwee.web.model.customer.CustomerDetailResp;
import com.linkwee.web.model.customer.CustomerInvestStatisticReq;
import com.linkwee.web.model.customer.CustomerInvestStatisticResp;
import com.linkwee.web.model.customer.CustomerTradelistResp;
import com.linkwee.web.model.customer.LcsCustCountResp;
import com.linkwee.web.model.customer.MycustomersResp;
import com.linkwee.web.model.customer.StatisticCustomerReq;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

/**
 * 
 * @描述： 客户统计Dao
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface StatisticCustomerDao extends BaseDao {

	/**
	 * 累计注册用户
	 * @param statisticCustomerReq @see StatisticCustomerReq
	 * @return 注册用户
	 */
	public Integer queryRegCustomerCount(StatisticCustomerReq statisticCustomerReq);

	/**
	 * 累计投资用户
	 * @param statisticCustomerReq @see StatisticCustomerReq
	 * @return 投资用户
	 */
	public Integer queryInvestCustomerCount(StatisticCustomerReq statisticCustomerReq);

	/**
	 * 累计投资数
	 * @param statisticCustomerReq @see StatisticCustomerReq
	 * @return 投资数
	 */
	public Integer queryInvestCount(StatisticCustomerReq statisticCustomerReq);

	/**
	 * 年化金额
	 *  @param statisticCustomerReq @see StatisticCustomerReq
	 * @return 投资数
	 */
	public Double queryYearpuramount(StatisticCustomerReq statisticCustomerReq);
	
	/**
	 * 投资金额
	 *  @param statisticCustomerReq @see StatisticCustomerReq
	 * @return 投资数
	 */
	public Double queryPuramount(StatisticCustomerReq statisticCustomerReq);
	
	
	/**
	 * 投资金额
	 *  @param statisticCustomerReq @see StatisticCustomerReq
	 * @return 投资数
	 */
	public Integer queryTradeCount(StatisticCustomerReq statisticCustomerReq);
	
	/**
	 * 统计理财师客户信息
	 * @param statisticCustomerReq
	 * @return
	 */
	public LcsCustCountResp queryLcsCustCountResp (StatisticCustomerReq statisticCustomerReq);
	
	
	/**
	 * 邀请客户-客户列表总数
	 * @param params
	 * @return
	 */
	public int queryMycustomersCount(Map<String,Object> params);
	/**
	 * 邀请客户-客户列表
	 * @param request
	 * @return
	 */
	public PageList<MycustomersResp> queryMycustomers(PageRequest request);
	
	
	/**
	 * 交易动态-累计
	 * @param params
	 * @return
	 */
	public int queryCustomerTradelistCount(Map<String,Object> params);
	/**
	 * 交易动态
	 * @param request
	 * @return
	 */
	public PageList<CustomerTradelistResp> queryCustomerTradelistResp(PageRequest request);
	
	/**
	 * 客户详情
	 * @param customerId
	 * @return
	 */
	public CustomerDetailResp queryCustomerDetailResp(String userNumber,String customerId);
	
	/**
	 * 获取最小投资记录时间(如果没有，则去注册时间)
	 * @param saleUserNumber 理财师
	 * @param customerId 客户
	 * @return
	 */
	public Date queryMinInvestRecordTime(@Param("saleUserNumber") String saleUserNumber,@Param("saleCustomerId") String saleCustomerId,@Param("customerId") String customerId);
	
	/**
	 * 有效投资客户
	 * @param saleUserNumber
	 * @param saleCustomerId
	 * @return
	 */
	public int queryValidCustomerCount(@Param("saleUserNumber") String saleUserNumber,@Param("saleCustomerId") String saleCustomerId );

	/**
	 * 查询新增客户数
	 * @Auther xuzhao
	 * @Date 2016年1月21日 下午3:42:10
	 * @param statisticCustomerReq
	 * @return
	 */
	public Integer queryCustomerNum(StatisticCustomerReq statisticCustomerReq);
	/**
	 * 查询理财师投资统计
	 * @Auther xuzhao
	 * @Date 2016年1月23日 上午11:08:22
	 * @param req
	 * @return
	 */
	public CustomerInvestStatisticResp queryCustomerInvestStatistic(CustomerInvestStatisticReq req);

	/**
	 * 查询理财师投资统计列表总数
	 * @Auther xuzhao
	 * @Date 2016年1月23日 上午11:08:28
	 * @param query
	 * @return
	 */
	public int queryCustomerInvestStatisticListCount(Map<String, Object> query);
	/**
	 * 查询理财师投资统计列表
	 * @Auther xuzhao
	 * @Date 2016年1月23日 下午12:44:36
	 * @param req
	 * @return
	 */
	public List<CustomerTradelistResp> queryCustomerInvestStatisticListResp(PageRequest req);

	/**
	 *  客户列表-累计
	 * @Auther xuzhao
	 * @Date 2016年1月23日 下午5:08:28
	 * @param number
	 * @param customerId
	 * @return
	 */
	public int queryRegisterMycustomers(@Param("saleUserNumber") String saleUserNumber,@Param("saleCustomerId") String saleCustomerId  );

	/**
	 * 客户详情-到期日程
	 * @Auther xuzhao
	 * @Date 2016年1月28日 上午11:31:55
	 * @param query
	 * @return
	 */
	public int querycustomerDetailPageListCount(Map<String, Object> query);

	/**
	 * 客户详情-到期日程
	 * @Auther xuzhao
	 * @Date 2016年1月28日 上午11:39:07
	 * @param req
	 * @return
	 */
	public List<CustomerTradelistResp> queryquerycustomerDetailPageListResp(PageRequest req);

	/**
	 * 根据交易类别查询交易次数
	 * @Auther xuzhao
	 * @Date 2016年1月28日 下午5:25:25
	 * @param statisticCustomerReq
	 * @return
	 */
	public Integer queryTradeCountByType(StatisticCustomerReq statisticCustomerReq);

	/**
	 *  客户列表投资人数
	 * @Auther ZhongLing
	 * @Date 2016年3月1日
	 * @param saleUserNumber
	 * @param saleCustomerId
	 * @return
	 */
	public int queryInvestMycustomers(@Param("saleUserNumber") String saleUserNumber,@Param("saleCustomerId") String saleCustomerId  );
	/**
	 * 查询客户邀请客户数
	 * @Auther chenchy
	 * @Date 2016年4月18日 下午3:42:10
	 * @param statisticCustomerReq
	 * @return
	 */
	public Integer queryInvitedCustomerNum(String customerMobile);
}
