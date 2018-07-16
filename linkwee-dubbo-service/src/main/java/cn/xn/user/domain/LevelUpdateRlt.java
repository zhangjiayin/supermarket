package cn.xn.user.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class LevelUpdateRlt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5727800409507282799L;

	/**
	 * 用户编号
	 */
	private String memberNo;

	/**
	 * 用户以前会员等级
	 */
	private String memberOldLevel;

	/**
	 * 用户最新会员等级
	 */
	private String memberNewLevel;

	
	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberOldLevel() {
		return memberOldLevel;
	}

	public void setMemberOldLevel(String memberOldLevel) {
		this.memberOldLevel = memberOldLevel;
	}

	public String getMemberNewLevel() {
		return memberNewLevel;
	}

	public void setMemberNewLevel(String memberNewLevel) {
		this.memberNewLevel = memberNewLevel;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
