package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 推送消息 参数
 * @author 颜彩云
 *
 */
public class PushMessageReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6315753490753534981L;
	private String userId;//用户id
	private Long userIdLong;//用户id Long（预留）
	private String value = "";//替换模板中的值，多个用英文逗号分隔
	private String customContent;//自定义内容，以json形式传入，不支持嵌套多级
	private boolean inputPushToken = false;//是否需要传入推送的token
	private String pushToken;//传入的推送token
	private String osType;//操作系统，详见MobileOsTypeEnum
	
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

	public String getCustomContent() {
		return customContent;
	}

	public void setCustomContent(String customContent) {
		this.customContent = customContent;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public boolean isInputPushToken() {
		return inputPushToken;
	}

	public void setInputPushToken(boolean inputPushToken) {
		this.inputPushToken = inputPushToken;
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
		if(isInputPushToken()){
			if (StringUtils.isBlank(pushToken) || StringUtils.isBlank(osType)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
