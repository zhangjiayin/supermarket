package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.linkwee.core.base.PaginatorSevReq;

public class UserTypeRequest {
	
	/**
	 * 1理财师团队成员2客户成员
	 */
	@NotNull(message="类型不能为空")
	private String type;
	
	/**
	 * 1未投资客户 2我关注的客户 3未出单的直推理财师 4我关注的直推理财师
	 */
	private Integer attenInvestType = 0;
	
	/**
	 * 搜索条件
	 */
	private String nameOrMobile;

	/**
	 * 第几页
	 */
	private Integer pageIndex =1;
	
	/**
	 * 页面大小
	 */
	private Integer pageSize = 10;
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 顺序
	 */
	private Integer order;
	/**
	 * 用户ID
	 */
	private String userId;
	
	public PaginatorSevReq toPaginatorSevReq(){
		PaginatorSevReq ret = new PaginatorSevReq();
		ret.setPageIndex(pageIndex);
		ret.setPageSize(pageSize);
		ret.setOrder(order);
		ret.setSort(sort);
		return ret;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "PaginatorRequest [pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + ", sort=" + sort + ", order=" + order + "]";
	}

	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


	public Integer getAttenInvestType() {
		return attenInvestType;
	}

	public void setAttenInvestType(Integer attenInvestType) {
		this.attenInvestType = attenInvestType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNameOrMobile() {
		return nameOrMobile;
	}

	public void setNameOrMobile(String nameOrMobile) {
		this.nameOrMobile = nameOrMobile;
	}

}
