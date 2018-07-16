package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 更新系统消息（更新为已读） 参数
 * @author 颜彩云
 *
 */
public class UpdateSystemMessageReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private String userId;//用户id
	private Long userIdLong;//用户id Long（预留）
	private String systemMessageId;//系统消息id
	private int type = 0;//类型，0表示更新部分，其他值表示更新全部
	
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

	public String getSystemMessageId() {
		return systemMessageId;
	}

	public void setSystemMessageId(String systemMessageId) {
		this.systemMessageId = systemMessageId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean check(){
		if (StringUtils.isBlank(userId)) {
			return false;
		}
		if(type == 0){
			if (StringUtils.isBlank(systemMessageId)) {
				return false;
			}
		}
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		return true;
	}
}
