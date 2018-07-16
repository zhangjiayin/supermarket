package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LoginRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5127800409507282799L;

	/**
	 * 用户编号
	 */
	private String memberNo;

	/**
	 * 令牌
	 */
	private String tokenId;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
