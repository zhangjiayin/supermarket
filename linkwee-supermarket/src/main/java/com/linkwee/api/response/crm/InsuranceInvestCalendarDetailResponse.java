package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;


public class InsuranceInvestCalendarDetailResponse{
	
	/**
	 * 投资记录id
	 */
	private String  investId;
	/**
	 * 投资金额
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal investAmt;
	/**
	 * 投资时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date startTime;
	
    /**
     *起保日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date startDate;
	
    /**
     *终保日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date endDate;
	
	/**
	 * 用户类型  0- 客户 1-直推 2-二级 3-三级
	 */
	private Integer userType;
	
	/**
	 * 理财师名称
	 */
	private String userName;
	
	/**
	 * 理财师头像
	 */
	private String headImage;
	
	/**
	 * 平台名称
	 */
	private String platformName;
    /**
     *产品名称
     */
	private String productName;
	
    /**
     *佣金率
     */
	private BigDecimal productFeeRate;
	/**
	 * 我的佣金
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal feeAmountSum;
	
	/**
	 * 保险审核佣金计算状态 0-待计算 1-计算成功 2-计算失败
	 */
	private Integer clearingStatus;
	
	/**
	 * 犹豫期
	 */
	private Integer hesitateDate;
	
	/**
	 * 佣金计算  信息 map
	 */
	private Map<String, String> feeRateCalculateMap;
	
	/**
	 * 佣金信息List
	 */
	private List<String> feeList;
	
	/**
	 * 客户直推理财师理财师userId
	 */
	private String userId;
	
    /**
     *投保单号
     */
	private String insureNum;

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public BigDecimal getInvestAmt() {
		return investAmt;
	}

	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductFeeRate() {
		return productFeeRate;
	}

	public void setProductFeeRate(BigDecimal productFeeRate) {
		this.productFeeRate = productFeeRate;
	}

	public BigDecimal getFeeAmountSum() {
		return feeAmountSum;
	}

	public void setFeeAmountSum(BigDecimal feeAmountSum) {
		this.feeAmountSum = feeAmountSum;
	}

	public Integer getClearingStatus() {
		return clearingStatus;
	}

	public void setClearingStatus(Integer clearingStatus) {
		this.clearingStatus = clearingStatus;
	}

	public Integer getHesitateDate() {
		return hesitateDate;
	}

	public void setHesitateDate(Integer hesitateDate) {
		this.hesitateDate = hesitateDate;
	}

	public Map<String, String> getFeeRateCalculateMap() {
		return feeRateCalculateMap;
	}

	public void setFeeRateCalculateMap(Map<String, String> feeRateCalculateMap) {
		this.feeRateCalculateMap = feeRateCalculateMap;
	}

	public List<String> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<String> feeList) {
		this.feeList = feeList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInsureNum() {
		return insureNum;
	}

	public void setInsureNum(String insureNum) {
		this.insureNum = insureNum;
	}
}
