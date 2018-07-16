package cn.xn.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 好友信息结果
 * @author 颜彩云
 *
 */
public class FriendInfoRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3928350223301860801L;
	
	/**
	 * 好友用户id，如果是未注册好友，该字段为""
	 */
	private String friendMemberNo;
	/**
	 * 好友手机号码
	 */
	private String friendMobile;
	/**
	 * 好友名字（通讯录中的名字）
	 */
	private String friendName;
	/**
	 * 是否注册，Y为已注册，N为未注册
	 */
	private String isRegister;
	/**
	 * 匿名设置，Y为匿名设置开启，N为匿名设置关闭
	 */
	private String secret;
	/**
	 * 建立好友关系时间
	 */
	private Date createTime;
	
	public String getFriendMobile() {
		return friendMobile;
	}

	public void setFriendMobile(String friendMobile) {
		this.friendMobile = friendMobile;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFriendMemberNo() {
		return friendMemberNo;
	}

	public void setFriendMemberNo(String friendMemberNo) {
		this.friendMemberNo = friendMemberNo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
