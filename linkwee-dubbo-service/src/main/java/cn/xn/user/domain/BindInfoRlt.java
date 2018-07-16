package cn.xn.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BindInfoRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3928350223301860801L;

	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 用户编码
	 */
	private String memberNo;
	/**
	 * 用户名称
	 */
	private String memberName;
	/**
	 * 用户手机号
	 */
	private String mobile;
	/**
	 * 推荐人手机号
	 */
	private String refereeNo;
	/**
	 * 注册时间
	 */
	private Date createDate;
	
	/**
	 * 用户来源渠道：微信、腾讯、新浪、百度, 如果没有获取到，则为""
	 */
	private String channel;	
	/**
	 * 绑定的地方账号唯一id
	 */
	private String unionId;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRefereeNo() {
		return refereeNo;
	}

	public void setRefereeNo(String refereeNo) {
		this.refereeNo = refereeNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
