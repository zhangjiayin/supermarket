package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * 团队销售统计
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class MonthSaleStatisticsResp extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 销售总额
	 */
	private Double totalAmount;
	/**
	 * 总收益
	 */
	private Double totalProfit;
	/**
	 * 直接推荐奖励
	 */
	private Double allowance;
	/**
	 * 间接推荐奖励
	 */
	private Double childrenAllowance;
	
	/**
	 * 销售笔数
	 */
	private int salesCount;
	
	/**
	 * leader奖励
	 */
	private String leaderProfit;
	
	/**
	 * 直接管理津贴
	 */
	private Double directAllowance;
	
	/**
	 * 团队管理津贴
	 */
	private Double teamAllowance;

	public String getLeaderProfit() {
		return leaderProfit;
	}

	public void setLeaderProfit(String leaderProfit) {
		this.leaderProfit = leaderProfit;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getAllowance() {
		return allowance;
	}

	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}

	public Double getChildrenAllowance() {
		return childrenAllowance;
	}

	public void setChildrenAllowance(Double childrenAllowance) {
		this.childrenAllowance = childrenAllowance;
	}

	public int getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getDirectAllowance() {
		return directAllowance;
	}

	public void setDirectAllowance(Double directAllowance) {
		this.directAllowance = directAllowance;
	}

	public Double getTeamAllowance() {
		return teamAllowance;
	}

	public void setTeamAllowance(Double teamAllowance) {
		this.teamAllowance = teamAllowance;
	}


}
