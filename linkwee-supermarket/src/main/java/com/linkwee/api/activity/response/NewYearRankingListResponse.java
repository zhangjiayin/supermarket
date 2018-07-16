package com.linkwee.api.activity.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class NewYearRankingListResponse {
	/**
	 * 排名
	 */
	private int rank;
	/**
	 * 用户名
	 */
	private String userName; 
	/**
	 * 累计年化业绩（自己和直接推荐理财师出单）
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal saleAmount;
	
	public NewYearRankingListResponse(int rank, String userName,BigDecimal saleAmount) {
		this.rank = rank;
		this.userName = userName;
		this.saleAmount = saleAmount;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	
}
