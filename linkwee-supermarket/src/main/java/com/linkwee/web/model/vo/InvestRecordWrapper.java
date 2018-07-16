package com.linkwee.web.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class InvestRecordWrapper {
	
	/**
	 * 业务编号(系统内部UUID)  `invest_id
	 */
	private String bizId;
	
	/**
	 * 投资记录编号 invest_record_no
	 */
	private String investId;
	
	
    /**
     *用户id
     */
	private String userId;
	
	
	/**
	 * 机构编码
	 */
	private String productOrgId;
	
	private String orgName;
	
    /**
     *产品id
     */
	private String productId;
	
	 /**
     *产品编号
     */
	private String productName;
	
	/**
	 * 产品期限
	 */
	private Integer productDays;
	
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
	
	/**
	 * 产品佣金率
	 */
	private BigDecimal feeRatio;
	
    /**
     *购买本金
     */
	private BigDecimal investAmt;
	
	/**
	 * 年化业绩
	 */
	private BigDecimal yearPurAmount;
	
	
    /**
     *投资日期
     */ 
	private Date investTime;
	
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让)
     */
	private Integer isRedemption;
	
	private Integer DeadLineMinValue;
	
    /**
     *产品最大期限
     */
	private Integer DeadLineMaxValue;
	
	/**
	 * 是否平台新用户
	 */
	private Boolean isPlatfromNewUser;
	
	/**
	 * 是否首次投资 1是 0否
	 */
	private boolean isFirstInvest;
	/**
	 * 是否平台首次投资1是 0否
	 */
	private boolean isPlatfromFirstInvest;
	
	/**
	 * 描述
	 */
	private String remark;
	
	/**
	 * 还款日期
	 */
	private Date endTime;
	
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
	/**
	 * 首笔销售费用cpa+cps
	 */
	private Integer isCpaAndCps;
	
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	private Integer isCollect;
	
	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getInvestId() {
		return investId;
	}


	public void setInvestId(String investId) {
		this.investId = investId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}


	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}


	public BigDecimal getInvestAmt() {
		return investAmt;
	}


	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}


	public Date getInvestTime() {
		return investTime;
	}


	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}


	public String getBizId() {
		return bizId;
	}


	public void setBizId(String bizId) {
		this.bizId = bizId;
	}


	public String getProductOrgId() {
		return productOrgId;
	}


	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}

	

	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public Integer getProductDays() {
		return productDays;
	}


	public void setProductDays(Integer productDays) {
		this.productDays = productDays;
	}

	
	public Integer getIsRedemption() {
		return isRedemption;
	}


	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}

	
	

	public Integer getDeadLineMinValue() {
		return DeadLineMinValue;
	}


	public void setDeadLineMinValue(Integer deadLineMinValue) {
		DeadLineMinValue = deadLineMinValue;
	}


	public Integer getDeadLineMaxValue() {
		return DeadLineMaxValue;
	}


	public void setDeadLineMaxValue(Integer deadLineMaxValue) {
		DeadLineMaxValue = deadLineMaxValue;
	}
	
	
	public Boolean getIsPlatfromNewUser() {
		return isPlatfromNewUser;
	}


	public void setIsPlatfromNewUser(Boolean isPlatfromNewUser) {
		this.isPlatfromNewUser = isPlatfromNewUser;
	}


	public boolean isFirstInvest() {
		return isFirstInvest;
	}


	public void setFirstInvest(boolean isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}
	
	public boolean isPlatfromFirstInvest() {
		return isPlatfromFirstInvest;
	}


	public void setPlatfromFirstInvest(boolean isPlatfromFirstInvest) {
		this.isPlatfromFirstInvest = isPlatfromFirstInvest;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public BigDecimal getYearPurAmount() {
		return yearPurAmount;
	}


	public void setYearPurAmount(BigDecimal yearPurAmount) {
		this.yearPurAmount = yearPurAmount;
	}


	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getIsCpaAndCps() {
		return isCpaAndCps;
	}


	public void setIsCpaAndCps(Integer isCpaAndCps) {
		this.isCpaAndCps = isCpaAndCps;
	}


	public Integer getIsCollect() {
		return isCollect;
	}


	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}


	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}


	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}
	

}
