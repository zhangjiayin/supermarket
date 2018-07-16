package com.linkwee.web.request.funds.ifast;

public class OrderListRequest extends IfastBaseRequest {
					
	//定投计划代码,如提供，则返回过滤后的数据	
	private	String	rspId;
					
	//查询起始的下单日期(如提供，则返回过滤后的数据)(yyyy-MM-dd)	
	private	String	orderDateStart;
						
	//查询结束的下单日期(如提供，则返回过滤后的数据)(yyyy-MM-dd)	
	private	String	orderDateEnd;
					
	//交易状态(可多个，基金代码直接以逗号分隔，如transactionType=['received','completed'])(如提供，则返回过滤后的数据)	
	private	String	transactionStatus;
					
	//交易类型(可多个，基金代码直接以逗号分隔，如transactionType=['buy','sell'])(如提供，则返回过滤后的数据)	
	private	String	transactionType;
						
	//要查询的订单流水编号(如提供，则返回过滤后的数据)	
	private	String	merchantNumber;
						
	//要查询的基金代码(可多个，基金代码直接以逗号分隔，如fundCodes=['482002','219003'])(如提供，则返回过滤后的数据)	
	private	String	fundCodes;
					
	//每页记录数	
	private	Integer	pageSize = 1000;
				
	//页码	
	private	Integer	pageIndex = 1;

	public String getRspId() {
		return rspId;
	}

	public void setRspId(String rspId) {
		this.rspId = rspId;
	}

	public String getOrderDateStart() {
		return orderDateStart;
	}

	public void setOrderDateStart(String orderDateStart) {
		this.orderDateStart = orderDateStart;
	}

	public String getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(String orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	public String getFundCodes() {
		return fundCodes;
	}

	public void setFundCodes(String fundCodes) {
		this.fundCodes = fundCodes;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
}
