package com.linkwee.web.model.customer;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：理财师客户信息统计
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class LcsCustCountResp extends BaseEntity {

	private static final long serialVersionUID = 4669440007808370539L;

	/**
	 * 记录最小时间
	 */
	private Date minTime;
	/**
	 * 当日投资金额
	 */
	private Double dayInvestAmt;
	/**
	 * 本月投资金额
	 */
	private Double monthInvestAmt;
	/**
	 * 累计投资总额
	 */
	private Double totalInvestAmt;
	/**
	 * 当日投资笔数
	 */
	private Integer dayInvestCount;
	/**
	 * 本月投资笔数
	 */
	private Integer monthInvestCount;
	/**
	 * 累计投资笔数
	 */
	private Integer totalInvestCount;
	/**
	 * 当日投资人数
	 */
	private Integer dayInvestPersonCount;
	/**
	 * 本月投资人数
	 */
	private Integer monthInvestPersonCount;
	/**
	 * 累计投资人数
	 */
	private Integer totalInvestPersonCount;
	/**
	 * 交易动态-购买
	 */
	private Integer buytradeCount;
	/**
	 * 交易动态-赎回
	 */
	private Integer backtradeCount;
	
	/**
	 * 客户列表
	 */
	private Integer customerCount;
	/**
	 * 互动问答
	 */
	private Integer interlocutionCount;

	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public Double getDayInvestAmt() {
		return dayInvestAmt;
	}

	public void setDayInvestAmt(Double dayInvestAmt) {
		this.dayInvestAmt = dayInvestAmt;
	}

	public Double getMonthInvestAmt() {
		return monthInvestAmt;
	}

	public void setMonthInvestAmt(Double monthInvestAmt) {
		this.monthInvestAmt = monthInvestAmt;
	}

	public Double getTotalInvestAmt() {
		return totalInvestAmt;
	}

	public void setTotalInvestAmt(Double totalInvestAmt) {
		this.totalInvestAmt = totalInvestAmt;
	}

	public Integer getMonthInvestCount() {
		return monthInvestCount;
	}

	public void setMonthInvestCount(Integer monthInvestCount) {
		this.monthInvestCount = monthInvestCount;
	}

	public Integer getTotalInvestCount() {
		return totalInvestCount;
	}

	public void setTotalInvestCount(Integer totalInvestCount) {
		this.totalInvestCount = totalInvestCount;
	}

	public Integer getMonthInvestPersonCount() {
		return monthInvestPersonCount;
	}

	public void setMonthInvestPersonCount(Integer monthInvestPersonCount) {
		this.monthInvestPersonCount = monthInvestPersonCount;
	}

	public Integer getTotalInvestPersonCount() {
		return totalInvestPersonCount;
	}

	public void setTotalInvestPersonCount(Integer totalInvestPersonCount) {
		this.totalInvestPersonCount = totalInvestPersonCount;
	}

	

	public Integer getBuytradeCount() {
		return buytradeCount;
	}

	public void setBuytradeCount(Integer buytradeCount) {
		this.buytradeCount = buytradeCount;
	}

	public Integer getBacktradeCount() {
		return backtradeCount;
	}

	public void setBacktradeCount(Integer backtradeCount) {
		this.backtradeCount = backtradeCount;
	}

	public Integer getDayInvestCount() {
		return dayInvestCount;
	}

	public void setDayInvestCount(Integer dayInvestCount) {
		this.dayInvestCount = dayInvestCount;
	}

	public Integer getDayInvestPersonCount() {
		return dayInvestPersonCount;
	}

	public void setDayInvestPersonCount(Integer dayInvestPersonCount) {
		this.dayInvestPersonCount = dayInvestPersonCount;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public Integer getInterlocutionCount() {
		return interlocutionCount;
	}

	public void setInterlocutionCount(Integer interlocutionCount) {
		this.interlocutionCount = interlocutionCount;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
