package com.openpltsdk.xyb.entity;

import java.math.BigDecimal;

/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：Assets
 * 类描述：用户资产信息
 * @author zenggang
 * @date 创建时间：2017年1月11日 上午11:11:56
 * 修改人：zenggang
 * 修改时间：2017年1月11日 上午11:11:56
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class Assets {
	/**
	 * awaitAmount:待收金额
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:12:07
	 * @version V1.3.1
	 */
	private BigDecimal awaitAmount;
	/**
	 * balanceAmount:账户余额
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:12:13
	 * @version V1.3.1
	 */
	private BigDecimal balanceAmount;
	/**
	 * totalAmount:资产总额
	 * @author zenggang
	 * @date 创建时间：2017年1月11日 上午11:12:16
	 * @version V1.3.1
	 */
	private BigDecimal totalAmount;
	
	public BigDecimal getAwaitAmount() {
		return awaitAmount;
	}
	
	public void setAwaitAmount(BigDecimal awaitAmount) {
		this.awaitAmount = awaitAmount;
	}
	
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}
	
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
