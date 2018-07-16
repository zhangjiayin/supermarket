package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class UpdateFriendReq extends BaseReq {

	private static final long serialVersionUID = -6078038213683153569L;

	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 用户id
	 */
	@NotNullField
	private String memberNo;

	/**
	 * 好友信息json
	 */
	@NotNullField
	private String friendJson;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFriendJson() {
		return friendJson;
	}

	public void setFriendJson(String friendJson) {
		this.friendJson = friendJson;
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
