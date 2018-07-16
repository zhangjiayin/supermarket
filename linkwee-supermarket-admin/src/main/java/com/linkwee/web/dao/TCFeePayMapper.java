package com.linkwee.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.generic.GenericDao;
import com.linkwee.tc.fee.model.TCFeePay;
import com.linkwee.tc.fee.model.TCFeebalance;
import com.linkwee.web.response.tc.PayFeeInfoResponse;

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ch
 * 
 * @创建时间：2016年09月08日 16:07:00
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface TCFeePayMapper extends GenericDao<TCFeePay,Long>{
	
	int inserts(@Param("feebalances")List<TCFeebalance> feebalances);
	
	
	
	int getNoPayFeeCount(String month);
	
	List<TCFeePay> getNoPayFeeList(String month,RowBounds page);
	
	int updateStatus(@Param("ids")List<String> ids ,@Param("status")int status,@Param("msgCode")String msgCode,@Param("msg")String msg);
	
	/**
	 * 保存本月预支付佣金信息
	 * @param feePayInfos 本月预支付佣金信息
	 * @return int 保存数量
	 */
	int insertFeePays(@Param("feePayInfos")List<TCFeePay> feePayInfos);
	
	/**
	 * 根据本月佣金明细获取支付佣金信息
	 * @param type 佣金类型 1001=佣金|1002=推荐奖励|1005=直接管理津贴|1006=团队管理津贴
	 * @param start 月份开始时间
	 * @param end 月份结束时间
	 * @todd 数据量小不分页，后续可扩展为分页
	 * @return
	 */
	List<TCFeePay> getPayFeeInfoByCurMonthFeedetail(@Param("type")String type,@Param("start")String start,@Param("end")String end);
	
	/**
	 * 根据月份获取支付佣金
	 * @param productClassify 产品类型  0-网贷 1-保险
	 * @param type 佣金类型
	 * @param month 月份
	 * @return TCFeePay
	 */
	List<TCFeePay> getPayFeeByCurMonth(@Param("productClassify")Integer productClassify,@Param("type")String type,@Param("month")String month);
	
	/**
	 * 根据状态判断佣金是否统计
	 * @param month 月份
	 * @param status 0=已支付 | 1=预支付
	 * @return true | false
	 */
	boolean isPayFeeSummaryByStutas(@Param("month")String month,@Param("status")Integer status);
	
	/**
	 * 下载本月佣金支付数据
	 * @param month
	 * @return
	 */
	List<PayFeeInfoResponse> payFeeInfoDownload(@Param("month")String month);


    /**
     * 佣金发放汇总短信
     * */
	List<TCFeePay> getPayFeeByCurMonthUseMessage(@Param("month")String month);

	/**
	 * 根据本月佣金明细获取支付佣金信息-保险
	 * @param type 佣金类型 1001=佣金|1002=推荐奖励|1005=直接管理津贴|1006=团队管理津贴
	 * @param start 月份开始时间
	 * @param end 月份结束时间
	 * @todd 数据量小不分页，后续可扩展为分页
	 * @return
	 */
	List<TCFeePay> getInsurancePayFeeInfoByCurMonthFeedetail(@Param("type")String feeType,@Param("start")String monthStart, @Param("end")String monthEnd);
}
