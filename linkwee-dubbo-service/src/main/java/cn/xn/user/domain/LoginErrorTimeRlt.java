package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LoginErrorTimeRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5127800409507282799L;

	/**
	 * 用户手机号
	 */
	private String loginName;

	/**
	 * 登录错误次数
	 */
	private Integer loginErrorTime;
	/**
	 * 系统编号
	 */
	private String systemType;

	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getLoginErrorTime() {
		return loginErrorTime;
	}

	public void setLoginErrorTime(Integer loginErrorTime) {
		this.loginErrorTime = loginErrorTime;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
