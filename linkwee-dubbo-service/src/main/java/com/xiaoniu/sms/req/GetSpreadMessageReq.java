package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 获取推广消息 参数
 * @author 颜彩云
 *
 */
public class GetSpreadMessageReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private Long spreadMessageId;//推广消息id
	private String userId;

	public Long getSpreadMessageId() {
		return spreadMessageId;
	}

	public void setSpreadMessageId(Long spreadMessageId) {
		this.spreadMessageId = spreadMessageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean check(){
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		if(spreadMessageId == null){
			return false;
		}
		return true;
	}
}
