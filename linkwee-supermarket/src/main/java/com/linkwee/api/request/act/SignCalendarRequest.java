package com.linkwee.api.request.act;

import javax.validation.constraints.NotNull;

public class SignCalendarRequest {
	@NotNull(message="signTime不能为空")
	private String signTime;

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
}
