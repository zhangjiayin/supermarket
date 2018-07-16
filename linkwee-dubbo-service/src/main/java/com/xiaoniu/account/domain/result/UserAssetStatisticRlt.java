package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 用户资产统计，除包括用户资产信息外，还包括部分用户信息
 * @author 颜彩云
 *
 */
public class UserAssetStatisticRlt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;//用户id
	private String userName;//用户姓名
	private Long totalAmount;//总资产(单位：毫)
	private Long investedAmount;//已投资资产(单位：毫)
	private Long toInvestAmount;//可投资资产(单位：毫)
	private Long blockedAmount;//冻结资产(单位：毫)
	private String loginName;//手机号
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Long investedAmount) {
		this.investedAmount = investedAmount;
	}

	public Long getToInvestAmount() {
		return toInvestAmount;
	}

	public void setToInvestAmount(Long toInvestAmount) {
		this.toInvestAmount = toInvestAmount;
	}

	public Long getBlockedAmount() {
		return blockedAmount;
	}

	public void setBlockedAmount(Long blockedAmount) {
		this.blockedAmount = blockedAmount;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
