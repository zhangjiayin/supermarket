package com.xiaoniu.sms.req;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 校验验证码 参数
 * @author 颜彩云
 *
 */
public class CheckCodeReq extends BaseReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3803206583786777580L;
	private String code;//验证码
	private String mobile;//手机号
	private int ignoreCase = 0;//默认为不区分大小写（0），其他值为区分大小写
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getIgnoreCase() {
		return ignoreCase;
	}
	public void setIgnoreCase(int ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean check(){
		if (StringUtils.isBlank(code)) {
			return false;
		}
		if (StringUtils.isBlank(mobile)) {
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
}
