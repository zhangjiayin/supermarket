package com.linkwee.api.request.act;

import javax.validation.constraints.NotNull;

/**
 * 
 * @描述：检测手机号码 入参
 *
 * @author Bob
 * @时间  2015年8月8日下午2:32:15
 *
 */
public class WeixinInfoRequest {
	
	/**
	 * code
	 */
	@NotNull(message="code不能为空")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
