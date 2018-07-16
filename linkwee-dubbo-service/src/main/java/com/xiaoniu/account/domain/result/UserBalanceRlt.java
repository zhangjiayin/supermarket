package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 用户资产流水记录信息
 * @author 周锋恒
 * @date 2015年7月29日
 *
 */
public class UserBalanceRlt implements Serializable {

	private static final long serialVersionUID = -1901537490283470862L;

	/** 业务id */
	private String partnerId;
	
	/** 交易类型 @see TransTypeEnum */
	private Integer transType;
	/**
	 * 交易类型名称
	 */
	private String transTypeDesc;
	
	/** 交易日期*/
	private Date transDate;
	
	/** 流水号 */
	private String serialNumber;
	
	/** 交易产品id */
	private String transProductId;
	
	/** 交易产品名称 */
	private String transProductName;
	
	/** 总资产交易操作符，= 表示总资产前后不变，+ 表示交易后总资产=交易前总资产+交易金额， - 表示交易后总资产=交易前总资产-交易金额 */
	private String totalOp;
	
	/** 交易金额(单位：毫) */
	private Long transAmount;
	
	/** 交易前总资产(单位：毫) */
	private Long beforeTotalAmount;
	
	/** 交易后总资产(单位：毫) */
	private Long afterTotalAmount;
	
	
	/** 可用投资资产操作符，=， +， - 的描述和总资产一致 */
	private String toInvestOp;
	
	/** 交易前可用投资资产(单位：毫) */
	private Long beforeToInvestAmount;
	
	/** 交易后可用投资资产(单位：毫) */
	private Long afterToInvestAmount;
	

	/** 已投资资产操作符，=， +， - 的描述和总资产一致 */
	private String investedOp;
	
	/** 交易前已投资资产(单位：毫) */
	private Long beforeInvestedAmount;
	
	/** 交易后已投资资产(单位：毫) */
	private Long afterInvestedAmount;
	
	
	/** 冻结资产操作符，=， +， - 的描述和总资产一致 */
	private String blockedOp;
	
	/** 交易前冻结资产(单位：毫) */
	private Long beforeBlockedAmount;
	
	/** 交易后冻结资产(单位：毫) */
	private Long afterBlockedAmount;
	
	/** 备注信息 */
	private String remark;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public Date getTransDate() {
		return transDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTransProductId() {
		return transProductId;
	}

	public void setTransProductId(String transProductId) {
		this.transProductId = transProductId;
	}

	public String getTransProductName() {
		return transProductName;
	}

	public void setTransProductName(String transProductName) {
		this.transProductName = transProductName;
	}

	public String getTotalOp() {
		return totalOp;
	}

	public void setTotalOp(String totalOp) {
		this.totalOp = totalOp;
	}

	public Long getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(Long transAmount) {
		this.transAmount = transAmount;
	}

	public Long getBeforeTotalAmount() {
		return beforeTotalAmount;
	}

	public void setBeforeTotalAmount(Long beforeTotalAmount) {
		this.beforeTotalAmount = beforeTotalAmount;
	}

	public Long getAfterTotalAmount() {
		return afterTotalAmount;
	}

	public void setAfterTotalAmount(Long afterTotalAmount) {
		this.afterTotalAmount = afterTotalAmount;
	}

	public String getToInvestOp() {
		return toInvestOp;
	}

	public void setToInvestOp(String toInvestOp) {
		this.toInvestOp = toInvestOp;
	}

	public Long getBeforeToInvestAmount() {
		return beforeToInvestAmount;
	}

	public void setBeforeToInvestAmount(Long beforeToInvestAmount) {
		this.beforeToInvestAmount = beforeToInvestAmount;
	}

	public Long getAfterToInvestAmount() {
		return afterToInvestAmount;
	}

	public void setAfterToInvestAmount(Long afterToInvestAmount) {
		this.afterToInvestAmount = afterToInvestAmount;
	}

	public String getInvestedOp() {
		return investedOp;
	}

	public void setInvestedOp(String investedOp) {
		this.investedOp = investedOp;
	}

	public Long getBeforeInvestedAmount() {
		return beforeInvestedAmount;
	}

	public void setBeforeInvestedAmount(Long beforeInvestedAmount) {
		this.beforeInvestedAmount = beforeInvestedAmount;
	}

	public Long getAfterInvestedAmount() {
		return afterInvestedAmount;
	}

	public void setAfterInvestedAmount(Long afterInvestedAmount) {
		this.afterInvestedAmount = afterInvestedAmount;
	}

	public String getBlockedOp() {
		return blockedOp;
	}

	public void setBlockedOp(String blockedOp) {
		this.blockedOp = blockedOp;
	}

	public Long getBeforeBlockedAmount() {
		return beforeBlockedAmount;
	}

	public void setBeforeBlockedAmount(Long beforeBlockedAmount) {
		this.beforeBlockedAmount = beforeBlockedAmount;
	}

	public Long getAfterBlockedAmount() {
		return afterBlockedAmount;
	}

	public void setAfterBlockedAmount(Long afterBlockedAmount) {
		this.afterBlockedAmount = afterBlockedAmount;
	}

	public String getTransTypeDesc() {
		return transTypeDesc;
	}

	public void setTransTypeDesc(String transTypeDesc) {
		this.transTypeDesc = transTypeDesc;
	}
	
}
