package com.linkwee.api.response.act;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

public class SignResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6805097245057241858L;

	/**
	 * 奖励金
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal bonus;
	
	/**
	 * 倍数（7/15/30签到翻倍）
	 */
	private int times;
	
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal timesBonus;
	
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public BigDecimal getTimesBonus() {
		return timesBonus;
	}
	public void setTimesBonus(BigDecimal timesBonus) {
		this.timesBonus = timesBonus;
	}
	
}
