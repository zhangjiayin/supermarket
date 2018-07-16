package com.linkwee.tc.fee.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 佣金明细包装类
 * @author ch
 *
 */
public class FeedetailWrapper {

	/**
	 * 理财师信息
	 */
	private String profitCfplannerId;//收益理财师
	private String originCfplannerId; //原始理财师
	private Double ratio; // 当前理财师提成比例
	private String remark;// 描述
	

	/**
	 * 基本信息
	 */
	
	private String billId; //单据编号
	private String investorId; //用户id
	private String productId; //产品编码
	private String productName;//产品名称
	private String productOrgId; //产品所属机构编号
	private Integer productDays; //产品固定期限
	private String feetype;// 佣金类型。《 1001：基础佣金, 1002： 推荐奖励,  1005： 直接管理津贴,  1006：团队管理津贴》
	private BigDecimal feeamount= BigDecimal.ZERO;//佣金
	private BigDecimal investmentAmount=BigDecimal.ZERO; //购买金额
	private BigDecimal yearPurAmount= BigDecimal.ZERO; //年化购买金额
	private Date investDate = DateTime.now().toDate();
	private Date endDate = DateTime.now().toDate();
	private String lowUserId;//下级理财师userId
	private String lowType;//下级类型(0=默认|1=下级2=下下级)	


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



	public String getProfitCfplannerId() {
		return profitCfplannerId;
	}



	public void setProfitCfplannerId(String profitCfplannerId) {
		this.profitCfplannerId = profitCfplannerId;
	}



	public String getOriginCfplannerId() {
		return originCfplannerId;
	}



	public void setOriginCfplannerId(String originCfplannerId) {
		this.originCfplannerId = originCfplannerId;
	}



	public Double getRatio() {
		return ratio;
	}



	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}


	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



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



	public String getProductOrgId() {
		return productOrgId;
	}



	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}



	public Integer getProductDays() {
		return productDays;
	}



	public void setProductDays(Integer productDays) {
		this.productDays = productDays;
	}



	public String getFeetype() {
		return feetype;
	}



	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}



	public BigDecimal getFeeamount() {
		return feeamount;
	}



	public void setFeeamount(BigDecimal feeamount) {
		this.feeamount = feeamount;
	}



	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}



	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}



	public BigDecimal getYearPurAmount() {
		return yearPurAmount;
	}



	public void setYearPurAmount(BigDecimal yearPurAmount) {
		this.yearPurAmount = yearPurAmount;
	}



	public Date getInvestDate() {
		return investDate;
	}



	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}
