package com.linkwee.api.response.act;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class BountyDetailResponse {

	/**
     *金额
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal amount;
	
	/**
	 * 金额描述
	 */
	private Integer amountType;
	
    /**
     *类型  1：签到  2：抽奖奖励金  3：转出到猎财余额  4：抽奖消耗
     */
	private Integer type;
	
	/**
	 * 类型名称
	 */
	private String typeName;
	
    /**
     *时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")   
	private Date createTime;
	

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getTypeName() {
		if(type == 1){
			return "签到";
		}else if(type == 2){
			return "抽奖收入";
		}else if(type == 3){
			return "转出到猎财余额";
		}else if(type == 4){
			return "抽奖支出";
		}else if(type == 5){
			return "晒单奖励";
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getAmountType() {
		if(type == 1 || type == 2||type == 5){
			return 1;
		}else{
			return 0;
		}
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}
}
