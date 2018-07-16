package cn.xn.user.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class BindReq extends BaseReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7076896635428563507L;

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
	 * 绑定的外部系统名称
	 */
	@NotNullField
	private String fName;

	/**
	 * 外部绑定的唯一编码
	 */
	@NotNullField
	private String fUnionid;
	
	/**
	 * 外部系统的登录令牌
	 */
	private String fToken;
	
	/**
	 * 令牌过期时间
	 */
	private Date expireData;
	
	
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfUnionid() {
		return fUnionid;
	}

	public void setfUnionid(String fUnionid) {
		this.fUnionid = fUnionid;
	}

	public String getfToken() {
		return fToken;
	}

	public void setfToken(String fToken) {
		this.fToken = fToken;
	}

	public Date getExpireData() {
		return expireData;
	}

	public void setExpireData(Date expireData) {
		this.expireData = expireData;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
