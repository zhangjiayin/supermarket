package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class FiredBindReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7076896635428563501L;

	/**
	 * 内部用户编码
	 */
	@NotNullField
	private String memberNo;

	/**
	 * 绑定的外部系统
	 */
	@NotNullField
	private String sysSource;

	/**
	 * 外部绑定的唯一编码
	 */
	private String fUnionid;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getSysSource() {
		return sysSource;
	}

	public void setSysSource(String sysSource) {
		this.sysSource = sysSource;
	}

	public String getfUnionid() {
		return fUnionid;
	}

	public void setfUnionid(String fUnionid) {
		this.fUnionid = fUnionid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
