package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author 周锋恒
 * 
 * @Date 2015年11月23日
 */
public class TransferRecordReq extends BaseReq {

	private static final long serialVersionUID = -8240154407883431170L;
	
	/** 出售人用户ID */
	@NotNull
	private String transferUserId;
	
	/** 出售人的投资记录 */
	@NotNull
	private Long investRecordNo;	
	
	/** 转让时间  yyyy-MM-dd HH:mm:ss  */
	@NotNull
	private String bisTime;
	
	/** 产品代码  */
	@NotNull
	private String productCode;
	
	/** 产品名称  */
	@NotNull
	private String productName;
	
	/** 转让金额 */
	@NotNull
	private Long transferAmount;
	
	/** 转让人收息金额 */
	@NotNull
	private Long transferUserProfit;

	/** 转让人手续费 */
	@NotNull
	private Long transferUserFee;
	
	/** 开始时间  格式：  yyyy-MM-dd */
	private String beginDate;
	
	/** 结束时间  格式：yyyy-MM-dd */
	private String endDate;
	
	/** 转让份额 */
	private Integer portion;
	
	/** 预期收益  */
	private Long expectProfit;
	
	/** 媒体来源	 */
	private String mediaSource;

	/** 用户操作IP  */
	private String clientIp;
	
	/** 备注信息 */
	private String remark;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


	public String getTransferUserId() {
		return transferUserId;
	}

	public void setTransferUserId(String transferUserId) {
		this.transferUserId = transferUserId;
	}

	public Long getInvestRecordNo() {
		return investRecordNo;
	}


	public void setInvestRecordNo(Long investRecordNo) {
		this.investRecordNo = investRecordNo;
	}


	public String getBisTime() {
		return bisTime;
	}

	public Integer getPortion() {
		return portion;
	}


	public void setPortion(Integer portion) {
		this.portion = portion;
	}


	public void setBisTime(String bisTime) {
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


	public String getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public Long getTransferAmount() {
		return transferAmount;
	}


	public void setTransferAmount(Long transferAmount) {
		this.transferAmount = transferAmount;
	}


	public Long getTransferUserProfit() {
		return transferUserProfit;
	}


	public void setTransferUserProfit(Long transferUserProfit) {
		this.transferUserProfit = transferUserProfit;
	}


	public Long getTransferUserFee() {
		return transferUserFee;
	}


	public void setTransferUserFee(Long transferUserFee) {
		this.transferUserFee = transferUserFee;
	}


	public Long getExpectProfit() {
		return expectProfit;
	}


	public void setExpectProfit(Long expectProfit) {
		this.expectProfit = expectProfit;
	}


	public String getMediaSource() {
		return mediaSource;
	}


	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}


	public String getClientIp() {
		return clientIp;
	}


	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
