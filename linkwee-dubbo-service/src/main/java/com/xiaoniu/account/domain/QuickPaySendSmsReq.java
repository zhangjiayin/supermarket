package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 快捷支付发送短信请求参数。
 * @author 周锋恒
 * 
 * @Date 2016年3月16日
 */
public class QuickPaySendSmsReq  implements Serializable {

	private static final long serialVersionUID = -3165246967948130321L;

	/** 业务编号 */
	@NotNull
	private String partnerId;
	
	/** 业务流水号 */
	private String partnerTradeNo;
	
	/** 还款单 */
	private String partnerRepaymentBillNo;
	
	/** 用户编号 */
	@NotNull
	private String userId;
		
	/** 用户名称 */
	@NotNull
	private String userName;
	
	/** 用户手机*/
	@NotNull
	private String userMobile;
	
	/** 用户卡号 */
	@NotNull
	private String userPayAccount;

	/**  银行编号  */
	@NotNull
	private String bankCode;
	
	/** 13：充值、27：还款代扣 */
	private Integer bisType;
	
	/** 商品名称 */
	@NotNull
	private String bisName;
	
	/** 身份证号 */
	@NotNull
	private String identityCard;
	
	/** 金额 单位毫 */
	@NotNull
	private Long totalAmount;
	
	/** 媒体来源	 */
	@NotNull
	private String mediaSource;
	
	/** 下单IP */
	@NotNull
	private String clientIp;
	
	/** 签名编码 utf-8 */
	@NotNull
	private String charset;
	
	/** 签名 */
	@NotNull
	private String sign;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPayAccount() {
		return userPayAccount;
	}

	public void setUserPayAccount(String userPayAccount) {
		this.userPayAccount = userPayAccount;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	
	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
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

	public Integer getBisType() {
		return bisType;
	}

	public void setBisType(Integer bisType) {
		this.bisType = bisType;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getPartnerRepaymentBillNo() {
		return partnerRepaymentBillNo;
	}

	public void setPartnerRepaymentBillNo(String partnerRepaymentBillNo) {
		this.partnerRepaymentBillNo = partnerRepaymentBillNo;
	}
}
