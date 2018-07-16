package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.enums.ResultType;

public class CheckUserRlt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5777800409507282799L;

	/**
	 * 是否实名标识
	 */
	private ResultType userFlag;

	/**
	 * 是否设置交易密码标识
	 */
	private ResultType payPwdFlag;

	/**
	 * 用户姓名
	 */
	private String memberName;

	/**
	 * 证件号码
	 */
	private String certNo;
	
	public ResultType getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(ResultType userFlag) {
		this.userFlag = userFlag;
	}

	public ResultType getPayPwdFlag() {
		return payPwdFlag;
	}

	public void setPayPwdFlag(ResultType payPwdFlag) {
		this.payPwdFlag = payPwdFlag;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
