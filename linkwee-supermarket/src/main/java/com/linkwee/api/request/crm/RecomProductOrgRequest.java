package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

public class RecomProductOrgRequest {
	
	/**
	 * 	产品或机构ID
	 */
	@NotNull(message="产品或机构ID不能为空")
	private String productOrgId;
	
	/**
	 * 	1=我的直推理财师 2 =我的客户
	 */
	@NotNull(message="用户类型不能为空")
	private String type;
	
	
	/**
	 * 	推荐给直接理财师或客户的userId
	 */
	@NotNull(message="用户Id不能为空")
	private String userId;
	
	
	/**
	 * 1=产品ID 2 =机构ID
	 */
	@NotNull(message="Id类型不能为空")
	private String idType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductOrgId() {
		return productOrgId;
	}

	public void setProductOrgId(String productOrgId) {
		this.productOrgId = productOrgId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
	
}
