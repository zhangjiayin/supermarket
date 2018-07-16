package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class CustomerInfoReq2 extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -795341733730815823L;

	/**
	 * 用户编码
	 */
	@NotNullField
	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
