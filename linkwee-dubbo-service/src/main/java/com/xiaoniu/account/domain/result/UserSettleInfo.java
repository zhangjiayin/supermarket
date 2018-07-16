package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author 周锋恒
 * 
 * @Date 2016年3月24日
 */
public class UserSettleInfo  implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 是否首次提现 */
	private boolean isFirstSettle;
	
	/** 是否要支行信息 */
	private boolean isNeedSubBank;
	
	/** 银行编号 */
	private String bankCode;
	
	/** 提现渠道 */
	private String settleProvider;
	
	/** 银行名称 */
	private String bankName;
	
	/** 城市 */
	private String city;
	
	/** 支行信息 */
	private String subBank;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public boolean isFirstSettle() {
		return isFirstSettle;
	}

	public void setFirstSettle(boolean isFirstSettle) {
		this.isFirstSettle = isFirstSettle;
	}

	public String getSettleProvider() {
		return settleProvider;
	}

	public void setSettleProvider(String settleProvider) {
		this.settleProvider = settleProvider;
	}

	public boolean isNeedSubBank() {
		return isNeedSubBank;
	}

	public void setNeedSubBank(boolean isNeedSubBank) {
		this.isNeedSubBank = isNeedSubBank;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubBank() {
		return subBank;
	}

	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}
	
}
