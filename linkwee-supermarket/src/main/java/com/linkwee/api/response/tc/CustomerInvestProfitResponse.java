package com.linkwee.api.response.tc;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class CustomerInvestProfitResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6730133537765817206L;
	private BigDecimal investAmt;//在投
	private BigDecimal paymentAmt;//回款中
	private BigDecimal paymentDoneAmt;//回款完成
	
	public String getInvestAmt() {
		return investAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setInvestAmt(BigDecimal investAmt) {
		this.investAmt = investAmt;
	}
	public String getPaymentAmt() {
		return paymentAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setPaymentAmt(BigDecimal paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	public String getPaymentDoneAmt() {
		return paymentDoneAmt.setScale(2,BigDecimal.ROUND_DOWN).toString();
	}
	public void setPaymentDoneAmt(BigDecimal paymentDoneAmt) {
		this.paymentDoneAmt = paymentDoneAmt;
	}
	
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
	
	
}
