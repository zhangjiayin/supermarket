package com.linkwee.web.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.base.BaseEntity;
 /**
 * 
 * @描述： 团队理财师收益贡献明细
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2016年06月07日 10:42:59
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public class TeamAllowanceListResp extends BaseEntity {
	
	private static final long serialVersionUID = 3625951331734709011L;
	
    /**
     * 自增长主键
     */
	private Integer id;
	/**
	 * 用户
	 */
	private String userName;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 关系等级
	 */
	private String relLevel;
	/**
	 * 职级
	 */
	private String jobGrade;
	/**
     * 年化销售额
     */
	private BigDecimal salesYearAmount;
	/**
     * 直接下级
     */
	private String directChildren;
	/**
     * 推荐奖励
     */
	private BigDecimal allowance;
	
	/**
	 * 直接管理津贴
	 */
	private Double directAllowance;
	
	/**
	 * 团队管理津贴
	 */
	private Double teamAllowance;
	/**
     * 绑定时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getRelLevel() {
		return relLevel;
	}
	public void setRelLevel(String relLevel) {
		this.relLevel = relLevel;
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
	public BigDecimal getSalesYearAmount() {
		return salesYearAmount;
	}
	public void setSalesYearAmount(BigDecimal salesYearAmount) {
		this.salesYearAmount = salesYearAmount;
	}
	public String getDirectChildren() {
		return directChildren;
	}
	public void setDirectChildren(String directChildren) {
		this.directChildren = directChildren;
	}
	public BigDecimal getAllowance() {
		return allowance;
	}
	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

