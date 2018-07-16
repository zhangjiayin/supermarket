package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 团队销售统计
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class LeaderProfitStatisticsResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 累计奖励
	 */
	private Double totalProfit = 0.0;
	/**
	 * 本月奖励
	 */
	private Double monthProfit = 0.0;
	/**
	 * 间接理财师人数
	 */
	private Integer indiNumbers = 0;
	/**
	 * 间接理财师贡献奖励
	 */
	private Double contrProfit = 0.0;
	/**
	 * 直接理财师人数
	 */
	private String directNumbers;
	/**
	 * 是否满足发放leader奖励(0=满足1=不满足)
	 */
	private String haveLeader;
	
	
	public String getHaveLeader() {
		return haveLeader;
	}

	public void setHaveLeader(String haveLeader) {
		this.haveLeader = haveLeader;
	}

	
	public String getDirectNumbers() {
		return directNumbers;
	}


	public void setDirectNumbers(String directNumbers) {
		this.directNumbers = directNumbers;
	}


	public Double getTotalProfit() {
		return totalProfit;
	}


	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}


	public Double getMonthProfit() {
		return monthProfit;
	}


	public void setMonthProfit(Double monthProfit) {
		this.monthProfit = monthProfit;
	}

	public Integer getIndiNumbers() {
		return indiNumbers;
	}

	public void setIndiNumbers(Integer indiNumbers) {
		this.indiNumbers = indiNumbers;
	}

	public Double getContrProfit() {
		return contrProfit;
	}


	public void setContrProfit(Double contrProfit) {
		this.contrProfit = contrProfit;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
