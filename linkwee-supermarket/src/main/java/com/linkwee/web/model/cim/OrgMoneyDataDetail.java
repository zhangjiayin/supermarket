package com.linkwee.web.model.cim;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkwee.core.jackson.MoneySerializer;

public class OrgMoneyDataDetail {

    /**
     *日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")   
	private Date time;
	
    /**
     *资金净流入(元)
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal money;
	
    /**
     *资金净流入(万元)
     */
	@JsonSerialize(using=MoneySerializer.class)
	private BigDecimal moneyWY;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getMoneyWY() {
		moneyWY = money.divide(new BigDecimal(10000));
		return moneyWY;
	}

	public void setMoneyWY(BigDecimal moneyWY) {
		this.moneyWY = moneyWY;
	}
}
