package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 回款记录信息
 * @author zhoufengheng
 *
 */
public class ReturnRecordRlt implements Serializable {

	private static final long serialVersionUID = 619598361471576723L;

	/** 回款流水号 */
	private Long returnRecordNo;
	
	/** 业务ID */
	private String partnerId;
	
	/** 业务流水号 */
	private String partnerTradeNo;
	
	/** 投资流水号 */
	private Long investRecordNo;
	
	/** 用户ID */
	private String userId;
	
	/** 回款名称 */
	private String bisName;
	
	/** 产品代码  */
	private String productCode;
	
	/** 产品名称  */
	private String productName;
	
	/** 回款时间 */
	private Date bisTime;
	
	/** 本金金额 */
	private Long totalAmount = 0l;
	
	/** 本金金额 */
	private Long returnAmount = 0l;
	
	/** 利息金额 */
	private Long profitAmount = 0l;
	
	/** 回款状态 */
	private String status;
	
	/** 备注 */
	private String remark;
	
	/** 客户IP */
	private String clientIp;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Long getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(Long profitAmount) {
		this.profitAmount = profitAmount;
	}

	public Long getReturnRecordNo() {
		return returnRecordNo;
	}

	public void setReturnRecordNo(Long returnRecordNo) {
		this.returnRecordNo = returnRecordNo;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public Long getInvestRecordNo() {
		return investRecordNo;
	}

	public void setInvestRecordNo(Long investRecordNo) {
		this.investRecordNo = investRecordNo;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBisTime() {
		return bisTime;
	}

	public void setBisTime(Date bisTime) {
		this.bisTime = bisTime;
	}

	public Long getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Long returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

}
