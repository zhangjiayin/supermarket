package com.linkwee.openplatform.xyb.model;

import java.math.BigDecimal;

public class Productxyb {
	
	/**
	 * 标的ID
	 */
	private String id;
	/**
	 * 标的URL
	 */
	private String url;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 借款人
	 */
	private String borrower;
	/**
	 * 借款金额
	 */
	private float borrowAmount;
	/**
	 * 剩余金额
	 */
	private float remainAmount;
	/**
	 * 起投金额
	 */
	private float minInvestAmount;
	/**
	 * 借款期限, 1d, 1m，如果为活期该字段为0
	 */
	private String period;
	/**
	 * 原始年化利率，13.5表示13.5%
	 */
	private float originalRate;
	/**
	 * 奖励利率，13.5表示13.5%
	 */
	private String rewardRate;
	/**
	 * 标的状态，见标的状态表格
	 */
	private int status;
	/**
	 * 还款方式
	 */
	private String repayment;
	/**
	 * 见标的类型表格
	 */
	private int type;
	/**
	 * 标的性质,比如:车贷，房贷，信用贷、融资租赁、保理等等
	 */
	private String prop;
	/**
	 * 标的创建时间
	 */
	private String createAt;
	/**
	 * 标的起投时间，如果有倒计时，这个时间会晚于标的创建时间
	 */
	private String publishAt;
	/**
	 * 标的截止购买时间
	 */
	private String closeAt;
	/**
	 * 标的满标时间
	 */
	private String fullAt;
	/**
	 * 预计还款日期(最后一期)
	 */
	private String repayString;
	/**
	 * 标签，数组，用以扩充标的属性。如：标的活动信息
	 */
	private String[] tags;
	/**
	 * 购买递增金额
	 */
	private BigDecimal buyIncreaseMoney;
	/**
	 * 产品单笔购买最大限度
	 */
	private BigDecimal buyMaxMoney;
	/**
	 * 产品已投资人数
	 */
	private int buyedTotalPeople;
	/**
	 * 募集开始时间
	 */
	private String collectBeginTime;
	/**
	 * 募集截止时间
	 */
	private String collectEndTime;
	/**
	 * 起息方式
	 */
	private int interestWay;
	/**
	 * 是否需要募集开始及结束时间
	 */
	private int isCollect;
	/**
	 * 是否拥有产品进度
	 */
	private int isHaveProgress;
	/**
	 * 是否限额产品
	 */
	private int isQuota;
	/**
	 * 是否可赎回可转让
	 */
	private int isRedemption;
	/**
	 * 货币类型
	 */
	private int moneyType;
	/**
	 * 产品类型
	 */
	private int productType;
	/**
	 * 风控类型
	 */
	private int riskContro1Type;
	/**
	 * 风险级别
	 */
	private int riskLevel;
	/**
	 * 产品起息日
	 */
	private String validBeginDate;
	/**
	 * 加息利率
	 */
	private BigDecimal addRate;
	/**
	 * 可赎回天数
	 */
	private int redemptionTime;
	/**
	 * 可转让天数
	 */
	private int assignmentTime;
	/**
	 * 是否是新手标
	 */
	private int ifRookie;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public float getBorrowAmount() {
		return borrowAmount;
	}
	public void setBorrowAmount(float borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	public float getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(float remainAmount) {
		this.remainAmount = remainAmount;
	}
	public float getMinInvestAmount() {
		return minInvestAmount;
	}
	public void setMinInvestAmount(float minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public float getOriginalRate() {
		return originalRate;
	}
	public void setOriginalRate(float originalRate) {
		this.originalRate = originalRate;
	}
	public String getRewardRate() {
		return rewardRate;
	}
	public void setRewardRate(String rewardRate) {
		this.rewardRate = rewardRate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRepayment() {
		return repayment;
	}
	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getPublishAt() {
		return publishAt;
	}
	public void setPublishAt(String publishAt) {
		this.publishAt = publishAt;
	}
	public String getCloseAt() {
		return closeAt;
	}
	public void setCloseAt(String closeAt) {
		this.closeAt = closeAt;
	}
	public String getFullAt() {
		return fullAt;
	}
	public void setFullAt(String fullAt) {
		this.fullAt = fullAt;
	}
	public String getRepayString() {
		return repayString;
	}
	public void setRepayString(String repayString) {
		this.repayString = repayString;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public BigDecimal getBuyIncreaseMoney() {
		return buyIncreaseMoney;
	}
	public void setBuyIncreaseMoney(BigDecimal buyIncreaseMoney) {
		this.buyIncreaseMoney = buyIncreaseMoney;
	}
	public BigDecimal getBuyMaxMoney() {
		return buyMaxMoney;
	}
	public void setBuyMaxMoney(BigDecimal buyMaxMoney) {
		this.buyMaxMoney = buyMaxMoney;
	}
	public int getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(int buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}
	public String getCollectBeginTime() {
		return collectBeginTime;
	}
	public void setCollectBeginTime(String collectBeginTime) {
		this.collectBeginTime = collectBeginTime;
	}
	public String getCollectEndTime() {
		return collectEndTime;
	}
	public void setCollectEndTime(String collectEndTime) {
		this.collectEndTime = collectEndTime;
	}
	public int getInterestWay() {
		return interestWay;
	}
	public void setInterestWay(int interestWay) {
		this.interestWay = interestWay;
	}
	public int getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}
	public int getIsHaveProgress() {
		return isHaveProgress;
	}
	public void setIsHaveProgress(int isHaveProgress) {
		this.isHaveProgress = isHaveProgress;
	}
	public int getIsQuota() {
		return isQuota;
	}
	public void setIsQuota(int isQuota) {
		this.isQuota = isQuota;
	}
	public int getIsRedemption() {
		return isRedemption;
	}
	public void setIsRedemption(int isRedemption) {
		this.isRedemption = isRedemption;
	}
	public int getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public int getRiskContro1Type() {
		return riskContro1Type;
	}
	public void setRiskContro1Type(int riskContro1Type) {
		this.riskContro1Type = riskContro1Type;
	}
	public int getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getValidBeginDate() {
		return validBeginDate;
	}
	public void setValidBeginDate(String validBeginDate) {
		this.validBeginDate = validBeginDate;
	}
	public BigDecimal getAddRate() {
		return addRate;
	}
	public void setAddRate(BigDecimal addRate) {
		this.addRate = addRate;
	}
	public int getRedemptionTime() {
		return redemptionTime;
	}
	public void setRedemptionTime(int redemptionTime) {
		this.redemptionTime = redemptionTime;
	}
	public int getAssignmentTime() {
		return assignmentTime;
	}
	public void setAssignmentTime(int assignmentTime) {
		this.assignmentTime = assignmentTime;
	}
	public int getIfRookie() {
		return ifRookie;
	}
	public void setIfRookie(int ifRookie) {
		this.ifRookie = ifRookie;
	}
}
