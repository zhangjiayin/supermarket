package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AcceptOutNoticeReq implements Serializable {

	private static final long serialVersionUID = 332572553089042907L;

	/** 业务编号 */
	@NotNull
	private String partnerId;
	
	/** 交易提现流水 */
	@NotNull
	private Long outRecordNo;
	
	/** 状态  success， fail  */
	@NotNull
	private String status;
	
	/** 支付打款单号 */
	@NotNull
	private String settlementId;
	
	/** 备注 */
	private String remark;
	
	/** 银行名称 */
	private String bankName;
	
	/** 卡号 */
	@NotNull
	private String cardNo;
	
	/** 签名编码 */
	
	private String charset;
	
	/** 签名 */
	
	private String sign;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getOutRecordNo() {
		return outRecordNo;
	}

	public void setOutRecordNo(Long outRecordNo) {
		this.outRecordNo = outRecordNo;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}


	
}
