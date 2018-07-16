package com.linkwee.api.response.act;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.jackson.MoneySerializer;

public class SignShareResponse extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566456048657115931L;

	/**
	 * 奖励类型  0：没有奖励  1：翻倍   2：红包
	 */
	private int prizeType;
	/**
	 * 奖励金额
	 */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal bouns;
	/**
	 * 红包信息
	 */
	private RedpacketResponse redpacketResponse = new RedpacketResponse();
	
	public int getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(int prizeType) {
		this.prizeType = prizeType;
	}
	public BigDecimal getBouns() {
		return bouns;
	}
	public void setBouns(BigDecimal bouns) {
		this.bouns = bouns;
	}
	public RedpacketResponse getRedpacketResponse() {
		return redpacketResponse;
	}
	public void setRedpacketResponse(RedpacketResponse redpacketResponse) {
		this.redpacketResponse = redpacketResponse;
	}
	
}
