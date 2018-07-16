package com.linkwee.web.response;

import java.math.BigDecimal;

public class CfpYearpurAmountResponse {

    /**
     *获利理财师编号
     */
	private String profitCfplannerId;
	
    /**
     *年化金额
     */
	private BigDecimal yearpurAmount;
	
    /**
     *当前级别
     */
	private String curLevel;
    /**
     *月份
     */
	private Integer month;

	public String getProfitCfplannerId() {
		return profitCfplannerId;
	}

	public void setProfitCfplannerId(String profitCfplannerId) {
		this.profitCfplannerId = profitCfplannerId;
	}

	public BigDecimal getYearpurAmount() {
		return yearpurAmount;
	}

	public void setYearpurAmount(BigDecimal yearpurAmount) {
		this.yearpurAmount = yearpurAmount;
	}

	public String getCurLevel() {
		return curLevel;
	}

	public void setCurLevel(String curLevel) {
		this.curLevel = curLevel;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
}
