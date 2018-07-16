package com.linkwee.web.model.crm;

public class CrmCfpLevelTemp {

    /**
     *用户编号
     */
	private String userId;
	
    /**
     *状态: 0=无效 | 1 =有效
     */
	private Integer status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
