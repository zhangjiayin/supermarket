package com.linkwee.openApi.request;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductUpdatRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     *产品状态(1-在售|2-售罄|3-募集失败)
     */
	//@NotNull(message="产品状态不能为空")
	//@Range(min=1,max=3,message="产品状态必需在1-3之间") 
	private Integer status;
	
    /**
     *第三方产品Id
     */
	//@NotBlank(message="第三方产品Id不能为空") 
	private String thirdProductId;
	
    /**
     *产品被投资总额
     */
	//@NotNull(message="产品被投资总额不能为空")
	//@Min(value=0, message="产品被投资总额必须大于等于0")
	private BigDecimal buyedTotalMoney;
	
    /**
     *产品已投资人数
     */
	//@NotNull(message="产品已投资人数不能为空")
	//@Min(value=0, message="产品已投资人数必须大于等于0")
	private Integer buyedTotalPeople;
    /**
     *加息利率
     */
	private BigDecimal addRate;
	/**
	 * 产品销售结束时间
	 */
	private String saleEndTime;
    /**
     *产品总额度
     */
	private BigDecimal buyTotalMoney;
	/**
	 *浮动最大利率
	 */
	private BigDecimal flowMaxRate;
	
    /**
     *浮动最小利率
     */
	private BigDecimal flowMinRate;		
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getThirdProductId() {
		return thirdProductId;
	}
	public void setThirdProductId(String thirdProductId) {
		this.thirdProductId = thirdProductId;
	}
	public BigDecimal getBuyedTotalMoney() {
		return buyedTotalMoney;
	}
	public void setBuyedTotalMoney(BigDecimal buyedTotalMoney) {
		this.buyedTotalMoney = buyedTotalMoney;
	}
	public Integer getBuyedTotalPeople() {
		return buyedTotalPeople;
	}
	public void setBuyedTotalPeople(Integer buyedTotalPeople) {
		this.buyedTotalPeople = buyedTotalPeople;
	}
	public BigDecimal getAddRate() {
		return addRate;
	}
	public void setAddRate(BigDecimal addRate) {
		this.addRate = addRate;
	}
	public String getSaleEndTime() {
		return saleEndTime;
	}
	public void setSaleEndTime(String saleEndTime) {
		this.saleEndTime = saleEndTime;
	}
	public BigDecimal getBuyTotalMoney() {
		return buyTotalMoney;
	}
	public void setBuyTotalMoney(BigDecimal buyTotalMoney) {
		this.buyTotalMoney = buyTotalMoney;
	}
	public BigDecimal getFlowMaxRate() {
		return flowMaxRate;
	}
	public void setFlowMaxRate(BigDecimal flowMaxRate) {
		this.flowMaxRate = flowMaxRate;
	}
	public BigDecimal getFlowMinRate() {
		return flowMinRate;
	}
	public void setFlowMinRate(BigDecimal flowMinRate) {
		this.flowMinRate = flowMinRate;
	}

}
