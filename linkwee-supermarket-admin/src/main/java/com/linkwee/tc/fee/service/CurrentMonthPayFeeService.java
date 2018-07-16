package com.linkwee.tc.fee.service;

import com.linkwee.core.exception.ServiceException;
import com.linkwee.tc.fee.model.FeeSummaryBuilder;
import com.linkwee.web.response.tc.CurrentMonthPayFeeStatistics;

/**
 * 本月佣金支付服务
 * @author ch
 *
 */
public interface CurrentMonthPayFeeService {
	
	/**
	 * 预支付佣金
	 * @param month 月份
	 * @return 预支付佣金统计信息
	 */
	CurrentMonthPayFeeStatistics prePayFee(String month,String monthStart,String monthEnd,FeeSummaryBuilder feeSummaryBuilder)throws ServiceException;;
	
	
	/**
	 * 支付佣金
	 * @param month 月份
	 * @return 支付佣金统计信息
	 */
	CurrentMonthPayFeeStatistics payFee(String month,String monthStart,String monthEnd,String operator)throws ServiceException;;
	

}
