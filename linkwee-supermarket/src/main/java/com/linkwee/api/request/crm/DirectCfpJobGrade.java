package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

public class DirectCfpJobGrade {
	
	/**
	 * 用户Id
	 */
	@NotNull(message="用户Id不能为空")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
