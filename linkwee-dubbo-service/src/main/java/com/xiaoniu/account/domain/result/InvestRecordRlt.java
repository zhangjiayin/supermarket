package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 投资记录结果信息
 * @author zhoufengheng
 *
 */
public class InvestRecordRlt implements Serializable {

	private static final long serialVersionUID = -2684047918170632690L;

	/** 投资流水号 */
	private Long investRecordNo;
	
	/** 业务ID */
	private String partnerId;
	
	/** 业务流水号 */
	private String partnerTradeNo;
	
	/** 用户ID */
	private String userId;
	
	/** 投资名称 */
	private String bisName;
	
	/** 投资时间 */
	private Date bisTime;
	
	/** 产品代码 */
	private String productCode;
	
	/** 产品名称 */
	private String productName;
	
	/** 投资金额 */
	private Long investAmount = 0l;
	
	/** 预计收益 */
	private Long expectProfit = 0l;
	
	/** 实际收益 */
	private Long actualProfit = 0l;
	
	/** 状态 */
	private String status;
	
	/** 备注 */
	private String remark;
	
	/** 媒体来源	 */
	private String mediaSource;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getInvestRecordNo() {
		return investRecordNo;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public void setInvestRecordNo(Long investRecordNo) {
		this.investRecordNo = investRecordNo;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}


	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBisTime() {
		return bisTime;
	}

	public void setBisTime(Date bisTime) {
		this.bisTime = bisTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(Long investAmount) {
		this.investAmount = investAmount;
	}


	public Long getExpectProfit() {
		return expectProfit;
	}

	public void setExpectProfit(Long expectProfit) {
		this.expectProfit = expectProfit;
	}

	public Long getActualProfit() {
		return actualProfit;
	}

	public void setActualProfit(Long actualProfit) {
		this.actualProfit = actualProfit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
