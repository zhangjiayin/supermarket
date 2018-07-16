package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class CheckPayPwdStatusReq extends BaseReq {

	private static final long serialVersionUID = 5692635905754933520L;

	/**
	 * 用户编码
	 */
	@NotNullField
	private String memberNo;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
