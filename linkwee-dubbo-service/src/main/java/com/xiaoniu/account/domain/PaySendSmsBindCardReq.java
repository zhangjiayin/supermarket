package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 首次支付发送短信或绑卡支付请求参数
 * @author 周锋恒
 * @date 2015年7月23日
 *
 */
public class PaySendSmsBindCardReq  implements Serializable {

	private static final long serialVersionUID = -3165246967948130321L;

	/** 业务编号 */
	@NotNull
	private String partnerId;
	
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
	
	/**  如果选择网银直连或快捷支付则必选   */
	private String bankCode;
	
	/** standard:普通    quick_pay : 快捷支付 	mobile_wap : */
	@NotNull
	private String paymentMethod;

	/** 支付类型： standard、direct、card_front   */
	private String payType;
	
	/** 商品名称 */
	@NotNull
	private String itemName;
	
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
	

	
	/** 手机验证码  绑卡时要传 */
	private String validCode;
	
	/** 充值流水号  绑卡时要传 */
	private Long inRecordNo;
	
	/** 网银支付回跳地址   */
	private String returnUrl;

	/** 特殊支付渠道，微信支付必填，值为weixin*/
	private String specialProvider;

	/** 微信公众号支付必填,格式：openid=xxx */
	private String attach;
	
	/** 签名 */
	@NotNull
	private String sign;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
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

	public Long getInRecordNo() {
		return inRecordNo;
	}

	public void setInRecordNo(Long inRecordNo) {
		this.inRecordNo = inRecordNo;
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public String getSpecialProvider() {
		return specialProvider;
	}

	public void setSpecialProvider(String specialProvider) {
		this.specialProvider = specialProvider;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}
}
