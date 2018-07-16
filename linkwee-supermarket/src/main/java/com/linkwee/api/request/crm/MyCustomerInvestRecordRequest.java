package com.linkwee.api.request.crm;

import javax.validation.constraints.NotNull;

import com.linkwee.core.base.PaginatorSevReq;

public class MyCustomerInvestRecordRequest {
	
	/**
	 * 1理财师团队成员2客户成员
	 */
	@NotNull(message="类型不能为空")
	private String type;
	
	/**
	 * 用户ID
	 */
	@NotNull(message="用户ID不能为空")
	private String 	userId;
	

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
