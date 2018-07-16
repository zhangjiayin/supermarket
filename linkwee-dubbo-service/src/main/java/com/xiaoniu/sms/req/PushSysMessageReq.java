package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 个人消息（站内信） 参数
 * @author 颜彩云
 *
 */
public class PushSysMessageReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private String userId;//用户id
	private Long userIdLong;//用户id Long（预留）
	private String value = "";//替换模板中的值，多个用英文逗号分隔
	
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean check(){
		if (StringUtils.isBlank(userId)) {
			return false;
		}
		if (StringUtils.isBlank(super.getPartnerId())) {
			return false;
		}
		if (StringUtils.isBlank(super.getModuleId())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
