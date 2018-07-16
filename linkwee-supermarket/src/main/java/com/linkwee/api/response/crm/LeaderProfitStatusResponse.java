package com.linkwee.api.response.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：用户信息
 *
 * @author chenjl
 * @时间 2017年3月01日下午3:26:10
 *
 */
public class LeaderProfitStatusResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public LeaderProfitStatusResponse() {

	}

	/**
	 * 理财师符合leader奖励状态
	 */
	private String cfpStatus;

	
	public String getCfpStatus() {
		return cfpStatus;
	}


	public void setCfpStatus(String cfpStatus) {
		this.cfpStatus = cfpStatus;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
