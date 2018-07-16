package com.linkwee.api.response.crm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;
import com.linkwee.core.util.DateUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.tc.fee.common.CalculateTools;

public class InvestCalendarDetailResponse {

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
	 * 预计回款时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date endTime;
	/**
	 * 预计收益
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal profit;
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
	 * 平台名称
	 */
	private String platformName;
    /**
     *产品名称
     */
	private String productName;
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
	
    /**
     *产品最小期限天数
     */
	private Integer deadLineMinValue;
	
    /**
     *产品最大期限天数
     */
	private Integer deadLineMaxValue;
	
    /**
     *产品最小期限天数 自定义显示
     */
	private String deadLineMinSelfDefined;
	
    /**
     *产品最大期限天数 自定义显示
     */
	private String deadLineMaxSelfDefined;
	
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
	 * 产品期限 (30天~6个月)
	 */
	private String deadLineValueNewText;
	
	/**
	 * 佣金计算  信息头部
	 */
	private String feeRateCalculateMsg;
	
	/**
	 * 佣金计算  信息 map
	 */
	private Map<String, String> feeRateCalculateMap;
	
    /**
     *产品销售结束时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleEndTime;
	
	/**
	 * 佣金信息List
	 */
	private List<String> feeList;
	
	/**
	 * 客户和直推理财师理财师userId
	 */
	private String userId;
	
	/**
	 * 是否过了锁定期  0-未过  1-已过
	 */
	private Integer ifPassLockDate;
	
	/**
	 * 1固定利率；2浮动利率 
	 */
	private Integer isFlow;
	
	/**
	 * 产品最小利率
	 */
	private String flowMinRate;
	
	/**
	 * 产品最大利率
	 */
	private String flowMaxRate;
	
	/**
	 * 产品利率Text
	 */
	private String productRateText;
	
	/**
	 * 红包收益
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal redpacketProfit;
	
	/**
	 * 虚拟平台奖励
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal virtualPlatformBouns;
	
	/**
	 * 是否自购
	 */
	private Integer isSelf;
	
	/**
	 * 年化投资额
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal yearpurAmount;
	
	/**
	 * 个人加佣佣金
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal personAddfeeAmount;
	
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
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
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
	public Integer getIsFixedDeadline() {
		return isFixedDeadline;
	}
	public void setIsFixedDeadline(Integer isFixedDeadline) {
		this.isFixedDeadline = isFixedDeadline;
	}
	public Integer getDeadLineMinValue() {
		return deadLineMinValue;
	}
	public void setDeadLineMinValue(Integer deadLineMinValue) {
		this.deadLineMinValue = deadLineMinValue;
	}
	public Integer getDeadLineMaxValue() {
		return deadLineMaxValue;
	}
	public void setDeadLineMaxValue(Integer deadLineMaxValue) {
		this.deadLineMaxValue = deadLineMaxValue;
	}
	public String getDeadLineMinSelfDefined() {
		return deadLineMinSelfDefined;
	}
	public void setDeadLineMinSelfDefined(String deadLineMinSelfDefined) {
		this.deadLineMinSelfDefined = deadLineMinSelfDefined;
	}
	public String getDeadLineMaxSelfDefined() {
		return deadLineMaxSelfDefined;
	}
	public void setDeadLineMaxSelfDefined(String deadLineMaxSelfDefined) {
		this.deadLineMaxSelfDefined = deadLineMaxSelfDefined;
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
	
	public String getDeadLineValueNewText() {
		if (getIsFixedDeadline() == 1){
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined())){
				deadLineValueNewText = getDeadLineMinSelfDefined();
			} else {
				deadLineValueNewText = getDeadLineMinValue()+"天";
			}
		} else {
			if(StringUtils.isNotBlank(getDeadLineMinSelfDefined()) && StringUtils.isNotBlank(getDeadLineMaxSelfDefined())){
				deadLineValueNewText = getDeadLineMinSelfDefined()+"~"+getDeadLineMaxSelfDefined();
			} else {
				deadLineValueNewText = getDeadLineMinValue()+"天~"+getDeadLineMaxValue()+"天";
			}
		}
		return deadLineValueNewText;
	}

	public void setDeadLineValueNewText(String deadLineValueNewText) {
		this.deadLineValueNewText = deadLineValueNewText;
	}
	public String getFeeRateCalculateMsg() {
		return feeRateCalculateMsg;
	}
	public void setFeeRateCalculateMsg(String feeRateCalculateMsg) {
		this.feeRateCalculateMsg = feeRateCalculateMsg;
	}
	public Map<String, String> getFeeRateCalculateMap() {
		return feeRateCalculateMap;
	}
	public void setFeeRateCalculateMap(Map<String, String> feeRateCalculateMap) {
		this.feeRateCalculateMap = feeRateCalculateMap;
	}
	public Date getSaleEndTime() {
		return saleEndTime;
	}
	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
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
	public Integer getIfPassLockDate() {
		if(new Date().after(DateUtils.addDay(startTime,deadLineMinValue))){
			ifPassLockDate = 1;
		} else {
			ifPassLockDate = 0;
		}
		return ifPassLockDate;
	}
	public void setIfPassLockDate(Integer ifPassLockDate) {
		this.ifPassLockDate = ifPassLockDate;
	}
	public Integer getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}
	public String getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(String flowMinRate) {
		this.flowMinRate = flowMinRate;
	}
	public String getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(String flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public String getProductRateText() {
		if(isFlow == 1){
			productRateText = flowMinRate+"%";
		} else if(isFlow == 2){
			productRateText = flowMinRate+"%~"+flowMaxRate+"%";
		}
		return productRateText;
	}
	public void setProductRateText(String productRateText) {
		this.productRateText = productRateText;
	}
	public BigDecimal getRedpacketProfit() {
		return redpacketProfit;
	}
	public void setRedpacketProfit(BigDecimal redpacketProfit) {
		this.redpacketProfit = redpacketProfit;
	}
	public BigDecimal getVirtualPlatformBouns() {
		return virtualPlatformBouns;
	}
	public void setVirtualPlatformBouns(BigDecimal virtualPlatformBouns) {
		this.virtualPlatformBouns = virtualPlatformBouns;
	}
	public Integer getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}
	public BigDecimal getYearpurAmount() {	
		return CalculateTools.yearpurAmountCompute(investAmt, deadLineMinValue);
	}
	public void setYearpurAmount(BigDecimal yearpurAmount) {
		this.yearpurAmount = yearpurAmount;
	}
	public BigDecimal getPersonAddfeeAmount() {
		return personAddfeeAmount;
	}
	public void setPersonAddfeeAmount(BigDecimal personAddfeeAmount) {
		this.personAddfeeAmount = personAddfeeAmount;
	}
}
