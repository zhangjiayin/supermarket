package com.linkwee.api.request.crm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.linkwee.core.base.BaseEntity;

public class RegisterSevReq extends BaseEntity{

	private static final long serialVersionUID = 8923374586082126651L;
	
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 推荐人id
	 */
	private String parentId;
	/**
	 * 来源地址
	 */
	private String fromUrl;
	
	/**
	 * 受访地址
	 */
	private String accessUrl;
	
	/**
	 * 所在树的ID=团队id
	 */
	private String salesOrgId;
	
	/**
	 * 所在树的深度=层级
	 */
	private Integer salesOrgDepth;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

	public String getFromUrl() {
		return fromUrl;
	}

	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getSalesOrgId() {
		return salesOrgId;
	}

	public void setSalesOrgId(String salesOrgId) {
		this.salesOrgId = salesOrgId;
	}

	public Integer getSalesOrgDepth() {
		return salesOrgDepth;
	}

	public void setSalesOrgDepth(Integer salesOrgDepth) {
		this.salesOrgDepth = salesOrgDepth;
	}
	
}
