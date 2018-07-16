package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 获取上行/状态报告 接口参数
 * @author 颜彩云
 *
 */
public class SmsStatusReq implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3589357457569140585L;
	private int type;//请求类型(1:上行 2: 状态报告)
	private String partnerId;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
