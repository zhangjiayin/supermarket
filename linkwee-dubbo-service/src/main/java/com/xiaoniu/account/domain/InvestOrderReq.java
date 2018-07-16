package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 认筹下单请求参数
 * @author 周锋恒
 * 
 * @Date 2015年12月2日
 */
public class InvestOrderReq extends BaseReq {

	private static final long serialVersionUID = -8240154407883431175L;
	
	/** 下单时间  yyyy-MM-dd HH:mm:ss */
	@NotNull
	private String bisTime;
	
	/** 产品代码  */
	@NotNull
	private String productCode;
	
	/** 产品名称  */
	@NotNull
	private String productName;
	
	/** 投资金额  */
	@NotNull
	private Long investAmount;
	
	/** 红包金额 */
	@NotNull
	private Long redPaperAmount;
	
	/** 转让的编码 */
	private String transferCode;
	
	/** 红包描述  流水展示用*/
	private String redPaperDesc;
	
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

	public String getBisTime() {
		return bisTime;
	}

	public void setBisTime(String bisTime) {
		this.bisTime = bisTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public String getRedPaperDesc() {
		return redPaperDesc;
	}

	public void setRedPaperDesc(String redPaperDesc) {
		this.redPaperDesc = redPaperDesc;
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

	public Long getRedPaperAmount() {
		return redPaperAmount;
	}

	public void setRedPaperAmount(Long redPaperAmount) {
		this.redPaperAmount = redPaperAmount;
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
