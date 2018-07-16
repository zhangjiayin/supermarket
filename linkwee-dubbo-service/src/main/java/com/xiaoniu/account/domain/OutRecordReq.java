package com.xiaoniu.account.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 提现请求参数
 * @author 周锋恒
 * @date 2015年7月24日
 *
 */
public class OutRecordReq implements Serializable {
	
	private static final long serialVersionUID = -5270964622675280828L;

	/** 业务编号  */
	@NotNull
	private String partnerId;
	
	/** 用户编号 */
	@NotNull
	private String userId;
	
	/** 用户名称 */
	@NotNull
	private String userName;
	
	/** 业务名称， 提现 */
	@NotNull
	private String bisName;
	
	/** 身份证号码 */
	@NotNull
	private String identityCard;
	
	/** 银行账号 */
	private String bankAccount;
	
	/** 提现金额  单位（毫） */
	@NotNull
	private Long amount;	
	
	/** 下单IP */
	@NotNull
	private String clientIp;
	
	/** 银行编号 */
	private String bankCode;
	
	/** 银行名称 */
	private String bankName;
	
	/** 城市 */
	private String city;
	
	/** 开户行 */
	private String kaiHuHang;
	
	/** 备注信息 */
	private String remark;
	
	/** 签名编码 */
	@NotNull
	private String charset;
	
	/** 签名编码 */
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

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getUserId() {
		return userId;
	}

	public String getRemark() {
		return remark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getKaiHuHang() {
		return kaiHuHang;
	}

	public void setKaiHuHang(String kaiHuHang) {
		this.kaiHuHang = kaiHuHang;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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
	
}
