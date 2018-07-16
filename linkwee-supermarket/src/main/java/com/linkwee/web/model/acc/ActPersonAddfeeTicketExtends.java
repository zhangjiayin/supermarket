package com.linkwee.web.model.acc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.xoss.util.BigDecimalUtil;

public class ActPersonAddfeeTicketExtends  extends ActPersonAddfeeTicket{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 投资记录id
	 */
	private String investId;
	
    /**
     *生效时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validBeginTime;
	
    /**
     *过期时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date validEndTime;
	
	/**
	 * 适用平台
	 */
	private String platformLimitMsg;
	
	/**
	 * 适用产品
	 */
	private String productLimitMsg;
	
	/**
	 * 首投限制
	 */
	private String investLimitMsg;
	
	/**
	 * 加佣信息
	 */
	private String addFeeLimitDayMsg;
	
	/**
	 * 状态 1：未过期 2：已过期 3：已使用
	 */
	private Integer status;
	
    /**
     *实际佣金
     */
	private BigDecimal feeAmount;
	
    /**
     *实际加佣天数
     */
	private Integer addFeeDay;
	
	/**
	 * 加佣券使用明细ID
	 */
	private Integer ticketDetailId;

	public String getInvestId() {
		return investId;
	}

	public void setInvestId(String investId) {
		this.investId = investId;
	}

	public Date getValidBeginTime() {
		return validBeginTime;
	}

	public void setValidBeginTime(Date validBeginTime) {
		this.validBeginTime = validBeginTime;
	}

	public Date getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(Date validEndTime) {
		this.validEndTime = validEndTime;
	}

	public String getPlatformLimitMsg() {
		if(getPlatformLimit() == 0){
			platformLimitMsg = "不限";
		} else if(getPlatformLimit() == 1){
			platformLimitMsg = getPlatformLimitOrgName();
		}
		return platformLimitMsg;
	}

	public void setPlatformLimitMsg(String platformLimitMsg) {
		this.platformLimitMsg = platformLimitMsg;
	}

	public String getProductLimitMsg() {
		
		if(getAmountLimit() == 0){
			productLimitMsg = "投资金额不限";
		} else if(getAmountLimit() == 1){
			if(getAmount() != null){
				productLimitMsg = "投资金额>"+BigDecimalUtil.scale(getAmount(),0,RoundingMode.DOWN).toString();
			}
		} else if(getAmountLimit() == 2){
			if(getAmount() != null){			
				productLimitMsg = "投资金额≥"+BigDecimalUtil.scale(getAmount(),0,RoundingMode.DOWN).toString();
			}
		}
		
		if(StringUtils.isNotBlank(productLimitMsg)){
			productLimitMsg += ";";
		}
		
		if(getProductLimit() == 1000){
			productLimitMsg += "期限不限";
		} else if(getProductLimit() == 1001){
			productLimitMsg += getProductLimitName();
		} else if(getProductLimit() == 1002){
			productLimitMsg += "期限"+getProductLimitDeadline()+"天";
		} else if(getProductLimit() == 1003){
			productLimitMsg += "期限"+getProductLimitDeadline()+"天以上(含)";
		}
		return productLimitMsg;
	}

	public void setProductLimitMsg(String productLimitMsg) {
		this.productLimitMsg = productLimitMsg;
	}

	public String getInvestLimitMsg() {
		if(getInvestLimit() == 0){
			investLimitMsg = "不限";
		} else if(getInvestLimit() == 1){
			investLimitMsg = "用户首投";
		} else if(getInvestLimit() == 2){
			investLimitMsg = "平台首投";
		}
		return investLimitMsg;
	}

	public void setInvestLimitMsg(String investLimitMsg) {
		this.investLimitMsg = investLimitMsg;
	}

	public String getAddFeeLimitDayMsg() {
		if(getAddFeeLimit() == 1){
			addFeeLimitDayMsg = "加佣"+getAddFeeLimitDay()+"天";
		}
		return addFeeLimitDayMsg;
	}

	public void setAddFeeLimitDayMsg(String addFeeLimitDayMsg) {
		this.addFeeLimitDayMsg = addFeeLimitDayMsg;
	}

	public Integer getStatus() {
		if(StringUtils.isNotBlank(investId)){
			status = 3;
		} else if(getValidEndTime().before(new Date())){
			status = 2;
		} else {
			status = 1;
		}
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount) {
		this.feeAmount = feeAmount;
	}

	public Integer getAddFeeDay() {
		return addFeeDay;
	}

	public void setAddFeeDay(Integer addFeeDay) {
		this.addFeeDay = addFeeDay;
	}

	public Integer getTicketDetailId() {
		return ticketDetailId;
	}

	public void setTicketDetailId(Integer ticketDetailId) {
		this.ticketDetailId = ticketDetailId;
	}
}
