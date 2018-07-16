package com.linkwee.api.request.cim;

import java.math.BigDecimal;

public class ProductAddfeeTicketRequest {
	
	/**
	 * 产品id  必需
	 */
	private String productId;
	
	/**
	 * 产品购买金额   非必需  默认为最大值
	 */
	private BigDecimal buyTotal = BigDecimal.valueOf(Double.MAX_VALUE);
	
	/**
	 * 产品购买天数   非必需
	 */
	private Integer deadLineValue;
	
	/**
	 * 购买用户  必需
	 */
	private String userId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getBuyTotal() {
		return buyTotal;
	}

	public void setBuyTotal(BigDecimal buyTotal) {
		this.buyTotal = buyTotal;
	}

	public Integer getDeadLineValue() {
		return deadLineValue;
	}

	public void setDeadLineValue(Integer deadLineValue) {
		this.deadLineValue = deadLineValue;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
