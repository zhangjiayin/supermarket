package com.linkwee.web.model.crm;

import java.util.Date;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述： 理财师销售与收益列表
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月03日 15:53:27
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class CfplannerListNewResp extends BaseEntity {

	private static final long serialVersionUID = -1442643838535851690L;

	/**
	 * 流水号
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 累计销售
	 */
	private Double totalSales;
	/**
	 * 销售笔数
	 */
	private Double countSales;
	/**
	 * 佣金
	 */
	private Double fee;
	/**
	 * 推荐收益
	 */
	private Double allowance;
	/**
	 * 活动奖励
	 */
	private Double activityReward;
	/**
	 * 客户在投
	 */
	private Double currInvestAmount;
	/**
	 * 理财师注册时间
	 */
	private Date createTime;
	
	/**
	 * 理财师团队人数
	 */
	private Integer teamMemberCount;
	/**
	 * 理财师客户人数
	 */
	private Integer customerCount;
	
	/**
	 * 直接管理津贴
	 */
	private Double directAllowance;
	
	/**
	 * 团队管理津贴
	 */
	private Double teamAllowance;
	
	/**
	 * 职级
	 */
	private String jobGrade;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}
	public Double getCountSales() {
		return countSales;
	}
	public void setCountSales(Double countSales) {
		this.countSales = countSales;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getActivityReward() {
		return activityReward;
	}
	public void setActivityReward(Double activityReward) {
		this.activityReward = activityReward;
	}
	public Double getCurrInvestAmount() {
		return currInvestAmount;
	}
	public void setCurrInvestAmount(Double currInvestAmount) {
		this.currInvestAmount = currInvestAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getAllowance() {
		return allowance;
	}
	public void setAllowance(Double allowance) {
		this.allowance = allowance;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getTeamMemberCount() {
		return teamMemberCount;
	}
	public void setTeamMemberCount(Integer teamMemberCount) {
		this.teamMemberCount = teamMemberCount;
	}
	public Integer getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
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
	public String getJobGrade() {
		if(jobGrade.equals("TA")){
			jobGrade = "见习";
		}else if(jobGrade.equals("SM1")){
			jobGrade = "顾问";
		}else if(jobGrade.equals("SM2")){
			jobGrade = "经理";
		}else if(jobGrade.equals("SM3")){
			jobGrade = "总监";
		}
		return jobGrade;
	}
	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}


}
