package com.linkwee.web.service;

import java.util.Map;

import com.linkwee.core.base.KeyValueEnum;
import com.linkwee.core.base.PaginatorSevReq;
import com.linkwee.core.base.PaginatorSevResp;
import com.linkwee.web.model.customer.RcCustomersResp;
import com.linkwee.web.model.customer.RcLcsCustomersResp;
import com.linkwee.web.model.customer.UnRcLcsCustomersResp;

/**
 * 
 * @描述：  邀请理财师
 * 
 * @创建人： Bob
 * 
 * @创建时间：2015年08月13日 17:27:15
 * 
 *  Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface RcCustomerService{

	/**
	 * 查询客户
	 * @return @see UnRcLcsCustomersResp
	 */
	public PaginatorSevResp<UnRcLcsCustomersResp> queryUnRcLcsCustomers(PaginatorSevReq request);
	
	/**
	 * 查询客户推荐情况
	 * @return @see RcLcsCustomersResp
	 */
	public PaginatorSevResp<RcLcsCustomersResp> queryRcLcsCustomers(PaginatorSevReq request);
	
	/**
	 * 统计邀请人数
	 * @param userNumber 理财师编号
	 * @return
	 */
	public Map<String,String> statisticRcCustCount(String userNumber,String[] types);
	
	/**
	 * 统计 注册客户、投资客户
	 * @param userMobile 用户手机号
	 * @return
	 */
	public Map<String,String> statisticRegCustCount(String userMobile);
	
	/**
	 * 邀请客户-客户列表
	 * @param request
	 * @return
	 */
	public PaginatorSevResp<RcCustomersResp> queryRcCustomers(PaginatorSevReq request);
	
	public static  enum QurplSortFieldEnum implements KeyValueEnum{
		TOTAL_INVEST_AMOUNT(1,"currInvestAmt"),//投资额
		NEW_INVEST_TIME(2,"newInvestTime"),//投资时间
		REGISTER_TIME(3,"registerTime"),//注册时间
		USER_NAME(4,"customerName");
		QurplSortFieldEnum(int key,String value){
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

	
	public static  enum QrplSortFieldEnum implements KeyValueEnum{
		RC_DATE(1,"rcDate");//推荐时间
		QrplSortFieldEnum(int key,String value){
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
}
