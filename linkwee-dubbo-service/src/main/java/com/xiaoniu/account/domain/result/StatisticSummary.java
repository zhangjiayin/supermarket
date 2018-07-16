package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户资产统计，包括符合条件的总条数及金额之和
 * @author 颜彩云
 *
 */
public class StatisticSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int totalRecord;//总条数
	private long toInvestSum;//用户余额之和
	private long investedSum;//用户在投金额之和

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public long getToInvestSum() {
		return toInvestSum;
	}

	public void setToInvestSum(long toInvestSum) {
		this.toInvestSum = toInvestSum;
	}

	public long getInvestedSum() {
		return investedSum;
	}

	public void setInvestedSum(long investedSum) {
		this.investedSum = investedSum;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
