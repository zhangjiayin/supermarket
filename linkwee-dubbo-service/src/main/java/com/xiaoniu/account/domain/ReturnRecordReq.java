package com.xiaoniu.account.domain;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description: 回款请求参数
 * @author 周锋恒
 * @date 2015年7月25日
 *
 */
public class ReturnRecordReq extends BaseReq {

	private static final long serialVersionUID = -8240154407883431170L;
	
	/** 产品代码  */
	@NotNull
	private String productCode;
	
	/** 产品名称  */
	@NotNull
	private String productName;
	
	/** 回款名称 */
	@NotNull
	private String bisName;
	
	/** 投资时间  yyyy-MM-dd HH:mm:ss*/
	private String bisTime;
	
	/** 对应投资的流水  */
	@NotNull
	private Long investRecordNo;
	
	/** 旧数据的投资流水   */
	private String oldInvestTradeNo;
	
	/** 回款的金额，本金的金额 */
	@NotNull
	private Long returnAmount;
	
	/** 收益金额 */
	@NotNull
	private Long profitAmount;

	/** 用户操作IP  */
	private String clientIp;
	
	/** 备注信息 */
	private String remark;
	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


	public String getOldInvestTradeNo() {
		return oldInvestTradeNo;
	}

	public String getRemark() {
		return remark;
	}

	public String getBisTime() {
		return bisTime;
	}

	public void setBisTime(String bisTime) {
		this.bisTime = bisTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setOldInvestTradeNo(String oldInvestTradeNo) {
		this.oldInvestTradeNo = oldInvestTradeNo;
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

	public Long getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Long returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Long getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(Long profitAmount) {
		this.profitAmount = profitAmount;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
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
	
}
