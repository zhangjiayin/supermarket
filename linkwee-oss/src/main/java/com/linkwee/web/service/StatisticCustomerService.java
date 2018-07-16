package com.linkwee.web.service;

import java.util.Date;

import com.linkwee.core.base.KeyValueEnum;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.web.model.SaleUserInfo;
import com.linkwee.web.model.customer.CustomerDetailResp;
import com.linkwee.web.model.customer.CustomerExpireRedeemResp;
import com.linkwee.web.model.customer.CustomerInvestStatisticResp;
import com.linkwee.web.model.customer.CustomerTradelistResp;
import com.linkwee.web.model.customer.LcsCustCountResp;
import com.linkwee.web.model.customer.MycustomersResp;
import com.linkwee.web.model.customer.MycustomersStatisticsResp;



/**
 * 
 * @描述：   我的客户服务
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface StatisticCustomerService{

	
	/**
	 * 统计理财师客户信息
	 * @param userNumber 理财师用户编码
	 * @param customerId 理财师客户编码
	 * @param now 当前日期
	 * @return
	 */
	public LcsCustCountResp queryLcsCustCountResp(String userNumber,String customerId,Date now,Integer appType);
	
	/**
	 * 客户列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<MycustomersResp> queryMycustomers(PaginatorSevReq request);
	
	/**
	 * 交易动态
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<CustomerTradelistResp> queryCustomerTradelistResp(PaginatorSevReq request);
	
	/**
	 * 客户详情
	 * @param saleUserNumber 理财师编码
	 * @param saleCustomerId 理财师用户id
	 * @param customerId 客户用户id
	 * @return
	 */
	public CustomerDetailResp queryCustomerDetailResp(String saleUserNumber,String saleCustomerId,String customerId);
	/**
	 * 查询未读交易次数
	 * @param saleUserNumber 理财师编码
	 * @param saleCustomerId 理财师用户id
	 * @return
	 */
	public Integer queryTradeCount(String saleUserNumber,String saleCustomerId,Integer appType);
	
	
	
	 static  enum QmSortFieldEnum implements KeyValueEnum{
		CURR_INVEST_AMT(1,"currInvestAmt"),//投资额
		REGISTER_TIME(2,"registerTime"),//注册时间
		NEAR_INVEST_DATE(3,"nearInvestDate"),//投资时间
		NEAR_END_DATE(4,"nearEndDate");//到期时间
		
		QmSortFieldEnum(int key,String value){
			this.key = key;
			this.value = value;
		}
		
		private int key;
		private String value;

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}



	 /**
	  * 查询理财师投资统计
	  * @Auther xuzhao
	  * @Date 2016年1月21日 下午5:06:43
	  * @param number
	  * @param customerId
	  * @param time
	  * @param timeType
	  * @return
	  */
	public CustomerInvestStatisticResp queryCustomerInvestStatistic(String number, String customerId, String time,
			int timeType);

	/**
	 * 查询理财师端  投资统计-分页
	 * @Auther xuzhao
	 * @Date 2016年1月23日 上午10:38:36
	 * @param pageRequest
	 * @return
	 */
	public PaginatorSevResp<CustomerTradelistResp> customerInvestStatisticPageList(
			PaginatorSevReq pageRequest);

	/**
	 *  客户列表-累计
	 * @Auther xuzhao
	 * @Date 2016年1月23日 下午5:08:28
	 * @param number
	 * @return
	 */
	public MycustomersStatisticsResp queryMycustomersStatisticsResp(SaleUserInfo userInfo);

	/**
	 * 客户详情-分页
	 * @Auther xuzhao
	 * @Date 2016年1月27日 上午11:45:39
	 * @param pageRequest
	 * @return
	 */
	public PaginatorSevResp<CustomerTradelistResp> customerDetailPageList(PaginatorSevReq pageRequest);


}
