package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class LevelUpdateReq extends BaseReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = -795341733730815823L;

	/**
	 * 用户编码
	 */
	@NotNullField
	private String memberNo;
	
	/**
	 * 类型，为-1表示降级，为1表示升级为黑金会员的条件之一成立，为null和其他值表示用之前逻辑
	 */
	private Integer type;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
