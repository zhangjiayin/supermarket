package com.linkwee.api.request.cim;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class PlatformBounsRequest {

	/**
	 * 平台奖励
	 */
	@NotNull(message="平台奖励不能为空")
	@DecimalMin(value="0", message="平台奖励必须大于等于0")
	@DecimalMax(value="9999999999999999", message="平台奖励必须小于等于9999999999999999")
	private BigDecimal virtualPlatformBouns;
	/**
	 * 投资记录
	 */
	@NotBlank(message="投资编号不能为空")
	@Length(min=1, max=64, message="投资编号长度必须在1-64位之间")
	private String investId;
	/**
	 * 用户Id
	 */
	private String userId;

	public BigDecimal getVirtualPlatformBouns() {
		return virtualPlatformBouns;
	}

	public void setVirtualPlatformBouns(BigDecimal virtualPlatformBouns) {
		this.virtualPlatformBouns = virtualPlatformBouns;
	}

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
