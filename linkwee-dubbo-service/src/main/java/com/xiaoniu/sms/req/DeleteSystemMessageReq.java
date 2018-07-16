package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 删除系统消息参数
 * @author 颜彩云
 *
 */
public class DeleteSystemMessageReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private String userId;//用户id
	private Long userIdLong;//用户id Long（预留）
	private String systemMessageIds;//系统消息id，多个以英文逗号分隔
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getUserIdLong() {
		return userIdLong;
	}

	public void setUserIdLong(Long userIdLong) {
		this.userIdLong = userIdLong;
	}

	public String getSystemMessageIds() {
		return systemMessageIds;
	}

	public void setSystemMessageIds(String systemMessageIds) {
		this.systemMessageIds = systemMessageIds;
	}

	public boolean check(){
		if (StringUtils.isBlank(userId)) {
			return false;
		}
		if (StringUtils.isBlank(systemMessageIds)) {
			return false;
		}
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		return true;
	}
}
