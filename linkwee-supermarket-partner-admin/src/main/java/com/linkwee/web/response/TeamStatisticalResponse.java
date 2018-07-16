package com.linkwee.web.response;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TeamStatisticalResponse {
	private BigDecimal amt =  new BigDecimal(10000);
	private Integer investCount = new Integer(0);
	private Integer lcsCount = new Integer(0);
	private BigDecimal totalAmt =  BigDecimal.ZERO;
	private BigDecimal totalYearpurAmt=  BigDecimal.ZERO;
	private BigDecimal stockYearpurAmt=  BigDecimal.ZERO;
	private BigDecimal stockAmt=  BigDecimal.ZERO;
	private BigDecimal totalFeeAmt=  BigDecimal.ZERO;
	
	public Integer getInvestCount() {
		return investCount;
	}

	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	
	public void setLcsCount(Integer lcsCount) {
		this.lcsCount = lcsCount;
	}

	public String getTotalAmt() {
		String amtStr = totalAmt.compareTo(amt)>=0 ? totalAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : totalAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getAvgAmt() {
		if(totalAmt.compareTo(BigDecimal.ZERO)<=0)return "0";
		BigDecimal avgAmt = totalAmt.divide(new BigDecimal(lcsCount), BigDecimal.ROUND_DOWN);
		String amtStr = avgAmt.compareTo(amt)>=0 ? avgAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : avgAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}
	
	

	public String getTotalYearpurAmt() {
		
		String amtStr = totalYearpurAmt.compareTo(amt)>=0 ? totalYearpurAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : totalYearpurAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}

	public void setTotalYearpurAmt(BigDecimal totalYearpurAmt) {
		this.totalYearpurAmt = totalYearpurAmt;
	}

	public String getStockYearpurAmt() {
		String amtStr = stockYearpurAmt.compareTo(amt)>=0 ? stockYearpurAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : stockYearpurAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}

	public void setStockYearpurAmt(BigDecimal stockYearpurAmt) {
		this.stockYearpurAmt = stockYearpurAmt;
	}
	
	
	
	public void setStockAmt(BigDecimal stockAmt) {
		this.stockAmt = stockAmt;
	}

	public String getStockAmt() {
		//BigDecimal stockAmt = stockYearpurAmt.setScale(2,BigDecimal.ROUND_DOWN).multiply(new BigDecimal(12)).setScale(4,BigDecimal.ROUND_DOWN);
		String amtStr = stockAmt.compareTo(amt)>=0 ? stockAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : stockAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}


	public String getTotalFeeAmt() {
		String amtStr = totalFeeAmt.compareTo(amt)>=0 ? totalFeeAmt.divide(amt, 4, BigDecimal.ROUND_DOWN).toString()+"万元" : totalFeeAmt.setScale(2,BigDecimal.ROUND_DOWN).toString()+"元";
		return amtStr;
	}

	public void setTotalFeeAmt(BigDecimal totalFeeAmt) {
		this.totalFeeAmt = totalFeeAmt;
	}

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}

}
