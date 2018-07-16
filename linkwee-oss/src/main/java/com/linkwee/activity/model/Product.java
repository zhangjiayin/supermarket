package com.linkwee.activity.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Product implements Serializable{
    
	private static final long serialVersionUID = -4141279311355008348L;
	
	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品利率
	 */
	private Double rate;
	
	/**
	 * 佣金率
	 */
	private Double feeRatio;
	
	/**
	 * 产品期限
	 */
	private Integer productDeadLine;

	
	
	
	
	public Integer getProductDeadLine() {
		return productDeadLine;
	}





	public void setProductDeadLine(Integer productDeadLine) {
		this.productDeadLine = productDeadLine;
	}





	public String getProductId() {
		return productId;
	}





	public void setProductId(String productId) {
		this.productId = productId;
	}





	public String getProductName() {
		return productName;
	}





	public void setProductName(String productName) {
		this.productName = productName;
	}





	public Double getRate() {
		return rate;
	}





	public void setRate(Double rate) {
		this.rate = rate;
	}





	public Double getFeeRatio() {
		return feeRatio;
	}





	public void setFeeRatio(Double feeRatio) {
		this.feeRatio = feeRatio;
	}





	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}