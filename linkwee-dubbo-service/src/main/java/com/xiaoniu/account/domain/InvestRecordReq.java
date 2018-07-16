package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 投资请求参数
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public class InvestRecordReq extends BaseReq {

	private static final long serialVersionUID = -8240154407883431170L;

	
	/** 投资名称 */
	@NotNull
	private String bisName;
	
	/** 投资时间  yyyy-MM-dd HH:mm:ss    请按格式传入，方便对账	*/
	private String bisTime;
	
	/** 产品代码  */
	@NotNull
	private String productCode;
	
	/** 产品名称  */
	@NotNull
	private String productName;
	
	/** 开始时间  格式：  yyyy-MM-dd */
	private String beginTime;
	
	/** 结束时间  格式：yyyy-MM-dd  */
	private String endTime;
	
	/** 预期收益时间 格式：yyyy-MM-dd */
	private String expectTime; 
	
	/** 投资金额  */
	@NotNull
	private Long investAmount;
	
	/** 预期收益  */
	private Long expectProfit;
	
	/** 媒体来源	 */
	private String mediaSource;

	/** 用户操作IP  */
	private String clientIp;
	
	/** 备注信息 */
	private String remark;
	
	/**	如果投资是转让产品，请传入转让的编码	 */
	private String transferCode;

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(String expectTime) {
		this.expectTime = expectTime;
	}

	public String getBisTime() {
		return bisTime;
	}

	public void setBisTime(String bisTime) {
		this.bisTime = bisTime;
	}

	public String getRemark() {
		return remark;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getProductCode() {
		return productCode;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public void setExpectProfit(Long expectProfit) {
		this.expectProfit = expectProfit;
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

	public String getBeginTime() {
		return beginTime;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public void setExpectProfit(long expectProfit) {
		this.expectProfit = expectProfit;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
}
