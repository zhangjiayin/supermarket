package com.linkwee.web.request;

public class FeeRequest extends PaginatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4977432770614747845L;

	private String saleUser; // 手机号码或者用户编号

	private String month; // 年月

	private int querytype; // 查询类型(0:默认; 1:发放中 2:发放成功; 3:发放失败)

	public String getSaleUser() {
		return saleUser;
	}

	public void setSaleUser(String saleUser) {
		this.saleUser = saleUser;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getQuerytype() {
		return querytype;
	}

	public void setQuerytype(int querytype) {
		this.querytype = querytype;
	}

}
