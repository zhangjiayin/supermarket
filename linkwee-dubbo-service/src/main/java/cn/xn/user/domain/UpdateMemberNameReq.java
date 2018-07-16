package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class UpdateMemberNameReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -795341733730815823L;

	/**
	 * 用户编码
	 */
	@NotNullField
	private String memberNo;
	/**
	 * 姓名
	 */
	@NotNullField
	private String memberName;
	/**
	 * 身份证号
	 */
	@NotNullField
	private String certNo;

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

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
