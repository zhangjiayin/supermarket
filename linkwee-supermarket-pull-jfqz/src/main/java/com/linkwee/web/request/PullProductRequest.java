package com.linkwee.web.request;

import com.linkwee.core.base.BaseEntity;

public class PullProductRequest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求开始时间
	 */
	private String startTime;

	/**
	 * 请求截止时间
	 */
	private String endTime;
	
	/**
	 * 合作机构产品id
	 */
	private String thirdProductId;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getThirdProductId() {
		return thirdProductId;
	}

	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}
	
	public PullProductRequest(String startTime,String endTime,String thirdProductId){
		this.startTime = startTime;
		this.endTime = endTime;
		this.thirdProductId = thirdProductId;
	}
	
	public PullProductRequest(){
		
	}
}
