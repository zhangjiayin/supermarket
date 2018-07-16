package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CustomerListRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3928350223301860801L;

	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 用户编码
	 */
	private String memberNo;
	/**
	 * 用户名称
	 */
	private String memberName;
	/**
	 * 会员级别
	 */
	private int memberLevel;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 证件号
	 */
	private String certNo;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
