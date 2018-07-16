package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class LevelFlagUpdateReq extends BaseReq {

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
	 * 0为未查看，1为已查看
	 */
	@NotNullField
	private int memberLevelFlag;

	public int getMemberLevelFlag() {
		return memberLevelFlag;
	}

	public void setMemberLevelFlag(int memberLevelFlag) {
		this.memberLevelFlag = memberLevelFlag;
	}

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
