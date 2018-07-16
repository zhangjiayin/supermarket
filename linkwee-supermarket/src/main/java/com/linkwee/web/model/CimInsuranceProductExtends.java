package com.linkwee.web.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class CimInsuranceProductExtends extends CimInsuranceProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 价格显示
	 */
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal priceString;

	public BigDecimal getPriceString() {
		priceString = new BigDecimal(getPrice()/100);
		return priceString;
	}

	public void setPriceString(BigDecimal priceString) {
		this.priceString = priceString;
	}
}
