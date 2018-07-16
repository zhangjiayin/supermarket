package com.linkwee.openapi.response;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.core.util.StringUtils;

public class OmsProductResponse {
	
    /**
     *加息利率
     */
	private BigDecimal addRate;
	/**
	 *可转让天数
	 */
	private Integer assignmentTime;
	/**
	 *可赎回天数
	 */
	private Integer redemptionTime;
	/**
	 * 产品单笔购买最小额度
	 */
	private BigDecimal buyMaxMoney;
	/**
	 * 产品单笔购买最小额度
	 */
	private BigDecimal buyMinMoney;
	/**
	 * 	产品总额度
	 */
	private BigDecimal  buyTotalMoney;
	/**
	 * 产品被投资总额
	 */
	private BigDecimal  buyedTotalMoney;
	/**
	 * 产品已投资人数
	 */
	private Integer  buyedTotalPeople;
    /**
     *募集开始时间|格式yyyy-mm-ddhh:mm:ss
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectBeginTime;
    /**
     *募集截止时间可以为nul格式yyyy-mm-ddhh:mm:ss
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date collectEndTime;
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
	 * 产品期限
	 */
	private String deadLineValueText;
	/**
	 *  浮动最大利率 
	 */
	private BigDecimal flowMaxRate;
	/**
	 *  浮动最小利率 
	 */
	private BigDecimal flowMinRate;
    /**
     *是否需要募集开始及截止时间(1=不需要|2=需要)
     */
	private Integer isCollect;
    /**
     *是否固定期限(1=固定期限|2=浮动期限)
     */
	private Integer isFixedDeadline;
	/**
	 * 1固定利率；2浮动利率 
	 */
	private Integer isFlow;
	/**
     *是否拥有产品进度(0=有|1没有)
     */
	private Integer isHaveProgress;
    /**
     *'是否限额产品。1-限额、2-不限额'
     */
	private Integer isQuota;
    /**
     *是否可赎回可转让(0=不支持赎回和转让|1=可赎回|2=可转让|3=可赎回且可转让)
     */
	private Integer isRedemption;
    /**
     *金额限制(元)
     */
	private BigDecimal orgAmountLimit;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 *  产品id 
	 */
	private String productId;
	/**
	 * 产品平台logo
	 */
	private String productLogo;
	/**
	 *  产品名称 
	 */
	private String productName;
	/**
	 * 产品利率Text
	 */
	private String productRateText;
    /**
     *产品销售开始时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date saleStartTime;	
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	private Integer status;
    /**
     *是否是新手标(1=新手标|2=非新手标)
     */
	private Integer ifRookie;
	
	public BigDecimal getAddRate() {
		return addRate;
	}
	public void setAddRate(BigDecimal addRate) {
		this.addRate = addRate;
	}
	public Integer getAssignmentTime() {
		return assignmentTime;
	}
	public void setAssignmentTime(Integer assignmentTime) {
		this.assignmentTime = assignmentTime;
	}
	public Integer getRedemptionTime() {
		return redemptionTime;
	}
	public void setRedemptionTime(Integer redemptionTime) {
		this.redemptionTime = redemptionTime;
	}
	public BigDecimal getBuyMaxMoney() {
		return buyMaxMoney;
	}
	public void setBuyMaxMoney(BigDecimal buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	public BigDecimal getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(BigDecimal buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public BigDecimal getBuyedTotalMoney() {
		return buyedTotalMoney;
	}
	public void setBuyedTotalMoney(BigDecimal buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}
	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}
	public Date getCollectBeginTime() {
		return collectBeginTime;
	}
	public void setCollectBeginTime(Date collectBeginTime) {
		this.collectBeginTime = collectBeginTime;
	}
	public Date getCollectEndTime() {
		return collectEndTime;
	}
	public void setCollectEndTime(Date collectEndTime) {
		this.collectEndTime = collectEndTime;
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
	public String getDeadLineValueText() {
		if(isFixedDeadline != null){
			if (isFixedDeadline == 1){
				if(StringUtils.isNotBlank(deadLineMinSelfDefined)){
					deadLineValueText = deadLineMinSelfDefined;
				} else {
					deadLineValueText = deadLineMinValue+"天";
				}
			} else {
				if(StringUtils.isNotBlank(deadLineMinSelfDefined) && StringUtils.isNotBlank(deadLineMaxSelfDefined)){
					deadLineValueText = deadLineMinSelfDefined+"~"+deadLineMaxSelfDefined;
				} else {
					deadLineValueText = deadLineMinValue+"天~"+deadLineMaxValue+"天";
				}
			}
			return StringUtils.separateNumberChinese(deadLineValueText, ",");
		} else {
			return "";
		}
	}
	public void setDeadLineValueText(String deadLineValueText) {
		this.deadLineValueText = deadLineValueText;
	}
	public BigDecimal getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(BigDecimal flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public BigDecimal getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(BigDecimal flowMinRate) {
		this.flowMinRate = flowMinRate;
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
	public Integer getIsFlow() {
		return isFlow;
	}
	public void setIsFlow(Integer isFlow) {
		this.isFlow = isFlow;
	}
	public Integer getIsHaveProgress() {
		return isHaveProgress;
	}
	public void setIsHaveProgress(Integer isHaveProgress) {
		this.isHaveProgress = isHaveProgress;
	}
	public Integer getIsQuota() {
		return isQuota;
	}
	public void setIsQuota(Integer isQuota) {
		this.isQuota = isQuota;
	}
	public Integer getIsRedemption() {
		return isRedemption;
	}
	public void setIsRedemption(Integer isRedemption) {
		this.isRedemption = isRedemption;
	}
	public BigDecimal getOrgAmountLimit() {
		return orgAmountLimit;
	}
	public void setOrgAmountLimit(BigDecimal orgAmountLimit) {
		this.orgAmountLimit = orgAmountLimit;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductLogo() {
		return productLogo;
	}
	public void setProductLogo(String productLogo) {
		this.productLogo = productLogo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public Date getSaleStartTime() {
		return saleStartTime;
	}
	public void setSaleStartTime(Date saleStartTime) {
		this.saleStartTime = saleStartTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIfRookie() {
		return ifRookie;
	}
	public void setIfRookie(Integer ifRookie) {
		this.ifRookie = ifRookie;
	}
}
