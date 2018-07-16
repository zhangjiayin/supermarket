package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class RegisterReq extends BaseReq {

	private static final long serialVersionUID = 1044416004087614612L;
	
	/**
	 * 登录名
	 */
	@NotNullField
	private String loginName;

	/**
	 * 登录密码
	 */
	@NotNullField
	private String loginPwd;

	/**
	 * 推荐人ID
	 */
	private String refereeUid;
	
	/**
	 * 推荐人手机号
	 */
	private String refereeMobile;
	
	/**
	 * 用户来源渠道：微信、腾讯、新浪、百度
	 */
	private String channel;	
	
	/**
	 * 来源搜索引擎
	 */
	private String engine;
	
	/**
	 * 搜索关键字
	 */
	private String keyword;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getRefereeUid() {
		return refereeUid;
	}

	public void setRefereeUid(String refereeUid) {
		this.refereeUid = refereeUid;
	}
	
	public String getRefereeMobile() {
		return refereeMobile;
	}

	public void setRefereeMobile(String refereeMobile) {
		this.refereeMobile = refereeMobile;
	}

	@Override
	public String toString() {
	    ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toStringExclude(this, "loginPwd");
	}

}
