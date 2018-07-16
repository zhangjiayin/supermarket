package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class UpdateSecretReq extends BaseReq {

	private static final long serialVersionUID = -6078038213683153569L;

	/**
	 * 用户id
	 */
	@NotNullField
	private String memberNo;
	
	/**
	 * 匿名设置，Y为匿名设置开启，N为匿名设置关闭
	 */
	@NotNullField
	private String secretFlag;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getSecretFlag() {
		return secretFlag;
	}

	public void setSecretFlag(String secretFlag) {
		this.secretFlag = secretFlag;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
