package com.linkwee.openapi.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class OmsAccountBindRequest {

	/**
	 * 身份证
	 */
	@NotBlank(message="身份证不能为空")
	@Pattern(regexp="(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")
	private String idCard;
	
	/**
	 * 姓名
	 */
	@NotNull(message="姓名不能为空")
	private String userName;
	
	/**
	 * 手机号
	 */
	@NotNull(message="手机号不能为空")
	@Pattern(regexp="^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")
	private String mobile;
	
	/**
	 * 银行卡
	 */
	@NotNull(message="银行卡不能为空")
	private String bankCard;
	
	/**
	 * 开户行代码
	 */
	@NotNull(message="开户行代码不能为空")
	private String bankCode;
	
	/**
	 * 开户行名称
	 */
	@NotNull(message="开户行名称不能为空")
	private String bankName;
	
	/**
	 * 机构编码
	 */
	private String orgNumber;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
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

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
}
