package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.NumberUtils;
import com.linkwee.web.model.crm.LeaderProfitStatisticsResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 团队销售统计
 * 
 * @Author ZhongLing
 * @Date 2016年1月20日 下午4:20:57
 */
public class LeaderProfitStatisticsResponse extends BaseEntity {

	public LeaderProfitStatisticsResponse() {
	}

	public LeaderProfitStatisticsResponse(LeaderProfitStatisticsResp obj) {
		WebUtil.initObj(this, obj);
		this.setTotalProfit(NumberUtils.getFormat(obj.getTotalProfit(), "0.00"));
		this.setMonthProfit(NumberUtils.getFormat(obj.getMonthProfit(), "0.00"));
		this.setContrProfit(NumberUtils.getFormat(obj.getContrProfit(), "0.00"));
		this.setIndiNumbers(obj.getIndiNumbers()!=null?obj.getIndiNumbers()+"":"0");
		this.setDirectNumbers(obj.getDirectNumbers()!=null?obj.getDirectNumbers():"0");
		this.setHaveLeader(obj.getHaveLeader());
	}
	
	private static final long serialVersionUID = 1L;
	/**
	 * 累计奖励
	 */
	private String totalProfit;
	/**
	 * 本月奖励
	 */
	private String monthProfit;
	/**
	 * 间接理财师人数
	 */
	private String indiNumbers;
	/**
	 * 直接理财师人数
	 */
	private String directNumbers;
	/**
	 * 间接理财师贡献奖励
	 */
	private String contrProfit;
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

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	public String getIndiNumbers() {
		return indiNumbers;
	}

	public void setIndiNumbers(String indiNumbers) {
		this.indiNumbers = indiNumbers;
	}

	public String getContrProfit() {
		return contrProfit;
	}

	public void setContrProfit(String contrProfit) {
		this.contrProfit = contrProfit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
