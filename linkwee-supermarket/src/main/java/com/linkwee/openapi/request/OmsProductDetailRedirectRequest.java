package com.linkwee.openapi.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OmsProductDetailRedirectRequest {

	/**
	 * 领会用户标识
	 */
	@NotNull(message="领会用户标识不能为空")
	private String userId;
	/**
	 * 产品标识
	 */
	@NotNull(message="产品标识不能为空")
	private String productId;
	/**
	 * 请求来源   web：PC端  wap：移动端
	 */
	@NotNull(message="请求来源不能为空")
	@Pattern(regexp="^(web|wap)$")
	private String requestFrom;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
}
