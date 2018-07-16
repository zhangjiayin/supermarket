package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户资产信息
 * @author 周锋恒
 * @date 2015年7月29日
 *
 */
public class UserAssetRlt implements Serializable {

	private static final long serialVersionUID = -1901537490283470862L;

	/** 用户ID */
	private String userId;
	
	/** 用户姓名 */
	private String userName;
	
	/** 用户总资产 */
	private Long totalAmount;
	
	/** 已投资资产 */
	private Long investedAmount;
	
	/** 可投资资产 */
	private Long toInvestAmount;
	
	/** 冻结资产 */
	private Long blockedAmount;
	
	/** 累计已回款收益 */
	private Long totalProfit;
	
	/** 预计收益 */
	private Long expectProfit;
	
	/** 预计今日收益 */
	private Long expectTodayProfit;
	
	/** 状态 1:正常，2暂停 */
	private Integer status;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Long investedAmount) {
		this.investedAmount = investedAmount;
	}

	public Long getToInvestAmount() {
		return toInvestAmount;
	}

	public void setToInvestAmount(Long toInvestAmount) {
		this.toInvestAmount = toInvestAmount;
	}

	public Long getBlockedAmount() {
		return blockedAmount;
	}

	public void setBlockedAmount(Long blockedAmount) {
		this.blockedAmount = blockedAmount;
	}

	public Long getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Long totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Long getExpectProfit() {
		return expectProfit;
	}

	public void setExpectProfit(Long expectProfit) {
		this.expectProfit = expectProfit;
	}

	public Long getExpectTodayProfit() {
		return expectTodayProfit;
	}

	public void setExpectTodayProfit(Long expectTodayProfit) {
		this.expectTodayProfit = expectTodayProfit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
