package com.linkwee.activity.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ProductUseCondition implements Serializable{
    
	private static final long serialVersionUID = -4141279311355008348L;
	

	/**
	 * 是否首次投资
	 */
	private Integer isFirstInvest; //投资用户限制 0=所有用户 不限制|1=首次投资用户
	

	/**
	 * 投资金额
	 */
	private Double investAmount;
	
	/**
	 * 投资的产品
	 */
	private List<Product> products;
	
	private String productRemark;
	
	private String investAmountRemark;
	
	
	public String getProductRemark() {
		return productRemark;
	}


	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}



	public String getInvestAmountRemark() {
		return investAmountRemark;
	}



	public void setInvestAmountRemark(String investAmountRemark) {
		this.investAmountRemark = investAmountRemark;
	}



	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Double getInvestAmount() {
		return investAmount;
	}



	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}

	

	public Integer getIsFirstInvest() {
		return isFirstInvest;
	}


	public void setIsFirstInvest(Integer isFirstInvest) {
		this.isFirstInvest = isFirstInvest;
	}


	@Override
	public String toString() {
		return  JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}