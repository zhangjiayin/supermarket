package com.linkwee.tc.fee.model.vo;

import java.math.BigDecimal;

public class InsuranceFeedetailWrapper{
	
	   /**
     *投资订单编号
     */
	private String billId;
	
    /**
     *投资用户编号
     */
	private String investorId;
	
    /**
     *获利理财师编号
     */
	private String profitCfplannerId;
	
    /**
     *获利理财师类型(0=默认|1=上1级|2=上2级|上3级)
     */
	private String profitCfplannerIdLowType;
	
    /**
     *原始理财师上3级理财师编号
     */
	private String originCfplannerParent3Id;
	
    /**
     *原始理财师上2级理财师编号
     */
	private String originCfplannerParent2Id;
	
    /**
     *原始理财师上1级理财师编号
     */
	private String originCfplannerParent1Id;
	
    /**
     *原始理财师编号
     */
	private String originCfplannerId;
	
    /**
     *产品所属机构编号
     */
	private String productOrgId;
	
    /**
     *产品编号
     */
	private String productId;
	
    /**
     *产品佣金费率
     */
	private BigDecimal productFeeRate;
	
    /**
     *购买产品金额
     */
	private BigDecimal productAmount;
	
    /**
     *年化金额
     */
	private BigDecimal yearpurAmount;
	
    /**
     *描述
     */
	private String remark;
	
    /**
     *成功描述
     */
	private String succeedRemark;
	
    /**
     *计算佣金费率
     */
	private BigDecimal feeRate;
	
    /**
     *佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *佣金类型：1001=佣金|1002=推荐津贴|1005=直接管理津贴|1006=团队管理津贴
     */
	private String feeType;
	
	
	/*********仅仅佣金表需保存字段************/
    /**
     *下级理财师userID
     */
	private String lowUserId;
	
    /**
     *下级类型(0=默认|1=下级2=下下级)
     */
	private String lowType;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getProfitCfplannerId() {
		return profitCfplannerId;
	}

	public void setProfitCfplannerId(String profitCfplannerId) {
		this.profitCfplannerId = profitCfplannerId;
	}

	public String getProfitCfplannerIdLowType() {
		return profitCfplannerIdLowType;
	}

	public void setProfitCfplannerIdLowType(String profitCfplannerIdLowType) {
		this.profitCfplannerIdLowType = profitCfplannerIdLowType;
	}

	public String getOriginCfplannerParent3Id() {
		return originCfplannerParent3Id;
	}

	public void setOriginCfplannerParent3Id(String originCfplannerParent3Id) {
		this.originCfplannerParent3Id = originCfplannerParent3Id;
	}

	public String getOriginCfplannerParent2Id() {
		return originCfplannerParent2Id;
	}

	public void setOriginCfplannerParent2Id(String originCfplannerParent2Id) {
		this.originCfplannerParent2Id = originCfplannerParent2Id;
	}

	public String getOriginCfplannerParent1Id() {
		return originCfplannerParent1Id;
	}

	public void setOriginCfplannerParent1Id(String originCfplannerParent1Id) {
		this.originCfplannerParent1Id = originCfplannerParent1Id;
	}

	public String getOriginCfplannerId() {
		return originCfplannerId;
	}

	public void setOriginCfplannerId(String originCfplannerId) {
		this.originCfplannerId = originCfplannerId;
	}

	public String getProductOrgId() {
		return productOrgId;
	}

	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getProductFeeRate() {
		return productFeeRate;
	}

	public void setProductFeeRate(BigDecimal productFeeRate) {
		this.productFeeRate = productFeeRate;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public BigDecimal getYearpurAmount() {
		return yearpurAmount;
	}

	public void setYearpurAmount(BigDecimal yearpurAmount) {
		this.yearpurAmount = yearpurAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSucceedRemark() {
		return succeedRemark;
	}

	public void setSucceedRemark(String succeedRemark) {
		this.succeedRemark = succeedRemark;
	}

	public BigDecimal getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getLowUserId() {
		return lowUserId;
	}

	public void setLowUserId(String lowUserId) {
		this.lowUserId = lowUserId;
	}

	public String getLowType() {
		return lowType;
	}

	public void setLowType(String lowType) {
		this.lowType = lowType;
	}
}
