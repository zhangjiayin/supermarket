package cn.xn.user.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.xn.user.annotation.NotNullField;

public class UpdateRefereeReq extends BaseReq {

	private static final long serialVersionUID = -6078038213683153569L;
	/**
	 * 用户id
	 */
	@NotNullField
	private String memberNo;

	/**
	 * 推荐人手机号码
	 */
	@NotNullField
	private String refereeNo;
	
	/**
	 * 推荐人用户id
	 */
	private String refereeId;
	/**
	 * 天数
	 */
	private int days = 3;
	/**
	 * 小时数
	 */
	private int hour = 72;
	/**
	 * 默认按照小时数
	 */
	private boolean isDay = false;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getRefereeNo() {
		return refereeNo;
	}

	public void setRefereeNo(String refereeNo) {
		this.refereeNo = refereeNo;
	}

	public String getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public boolean isDay() {
		return isDay;
	}

	public void setDay(boolean isDay) {
		this.isDay = isDay;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
