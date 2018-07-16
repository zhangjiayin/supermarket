package com.linkwee.web.model.crm;

public class CrmCfpLevelMonth {
	
    /**
     *用户编号
     */
	private String userId;
	
    /**
     *月份
     */
	private Integer month;
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
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
