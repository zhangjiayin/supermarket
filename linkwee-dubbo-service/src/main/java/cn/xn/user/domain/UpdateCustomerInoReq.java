package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;
import cn.xn.user.enums.CertType;

public class UpdateCustomerInoReq extends BaseReq {

	private static final long serialVersionUID = -3866463757520974929L;

	/**
	 * 用户编码
	 */
	@NotNullField
	private String memberNo;

	/**
	 * 用户名称
	 */
	@NotNullField
	private String memberName;

	/**
	 * 证件类型
	 */
	private CertType certType;

	/**
	 * 证件号
	 */
	@NotNullField
	private String certNo;

	/**
	 * 银行卡号
	 */
	@NotNullField
	private String bankCardNo;

	/**
	 * 银行预留手机号
	 */
	private String bankMobile;
	
	/**
	 * 银行名称
	 */
	private String bankName;
	
	/**
	 * 银行编码
	 */
	private String banckCode;
	
	
	public String getBanckCode() {
		return banckCode;
	}

	public void setBanckCode(String banckCode) {
		this.banckCode = banckCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public CertType getCertType() {
		return certType;
	}

	public void setCertType(CertType certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
