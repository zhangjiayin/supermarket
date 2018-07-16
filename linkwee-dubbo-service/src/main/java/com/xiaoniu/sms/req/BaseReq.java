package com.xiaoniu.sms.req;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 生成验证码/验证验证码/发送短信  公共参数
 * @author 颜彩云
 *
 */
public class BaseReq {
	private String partnerId;//业务id，区分钱罐子和众筹
	private String moduleId;//各个业务下的模块id,同一个业务id下不能有相同的模块id
	private String signature = "signature";// 签名，所有http接口都需要验证签名, dubbo接口不需要验证此参数
	private String ip;
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
