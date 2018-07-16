package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class ResetLoginPwdReq extends BaseReq {

	private static final long serialVersionUID = 6754365513846641290L;
	/**
	 * 新密码
	 */
	@NotNullField
	private String loginNewPwd;
	
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

	public String getLoginNewPwd() {
		return loginNewPwd;
	}

	public void setLoginNewPwd(String loginNewPwd) {
		this.loginNewPwd = loginNewPwd;
	}

	@Override
	public String toString() {
	    ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toStringExclude(this, "loginNewPwd");
	}
}
