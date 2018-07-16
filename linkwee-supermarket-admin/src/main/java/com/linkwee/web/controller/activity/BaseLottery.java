package com.linkwee.web.controller.activity;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseLottery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3958471990716748009L;

	private Integer id;
	/**
	 * 奖项
	 */
	private String prize;
	/**
	 * 概率
	 */
	private Integer variable;
	/**
	 * 奖励类型 1、现金红包 2、投资奖励红包3、其他4、职级体验券5、奖励金
	 */
	private Integer lotteryType;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 收件地址类型 0：不需要收件地址  1：邮寄地址   2：爱奇艺账户
	 */
	private Integer addressType;
		
	public BaseLottery(Integer id, String prize, Integer variable,Integer lotteryType, BigDecimal amount, Integer addressType) {
		super();
		this.id = id;
		this.prize = prize;
		this.variable = variable;
		this.lotteryType = lotteryType;
		this.amount = amount;
		this.addressType = addressType;
	}

	public BaseLottery(Integer id, String prize, Integer variable,Integer lotteryType, BigDecimal amount) {
		super();
		this.id = id;
		this.prize = prize;
		this.variable = variable;
		this.lotteryType = lotteryType;
		this.amount = amount;
	}

	public BaseLottery(Integer id, String prize, Integer variable) {
		super();
		this.id = id;
		this.prize = prize;
		this.variable = variable;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public Integer getVariable() {
		return variable;
	}
	public void setVariable(Integer variable) {
		this.variable = variable;
	}

	public Integer getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(Integer lotteryType) {
		this.lotteryType = lotteryType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}
}
