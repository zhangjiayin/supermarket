package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class GetFriendRlt extends BaseReq {

	private static final long serialVersionUID = -6078038213683153569L;

	/**
	 * 手机号码
	 */
	@NotNullField
	private String mobile;

	/**
	 * 类型，0为获取全部，1为获取已注册，2为获取未注册
	 */
	@NotNullField
	private Integer type;

	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
