package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CustomerInfoBaseRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3128350223301860801L;

	/**
	 * 用户编码
	 */
	private String memberNo;
	
	/**
	 * 用户姓名
	 */
	private String memberName;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	/**
	 * 银行编码
	 */
	private String bankCode;
	/**
	 * 银行预留手机号
	 */
	private String bankMobile;
	/**
	 * 证件类型
	 */
	private String certType;
	/**
	 * 证件号
	 */
	private String certNo;
	
	/**
	 * 银行名称
	 */
	private String bankName;
	
	/**
	 * 用户是否实名认证
	 */
	private boolean userFlag;
	
	/**
	 * 会员等级
	 */
	private Integer memberLevel;
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public boolean isUserFlag() {
		return userFlag;
	}

	public void setUserFlag(boolean userFlag) {
		this.userFlag = userFlag;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankMobile() {
		return bankMobile;
	}

	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
