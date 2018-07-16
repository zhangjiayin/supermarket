package com.xiaoniu.account.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiaoniu.account.domain.base.PageBackReqBase;


/**
 * 
 * @Description: 查询投资记录参数
 * @author 周锋恒
 * @date 2015年8月9日
 *
 */
public class QueryInvestRecordBackReq extends PageBackReqBase implements Serializable {

	private static final long serialVersionUID = 12178115888236583L;

	/** 投资流水号 */
	private Long investRecordNo;
	
	/** 业务ID */
	private String partnerId;
	
	/** 业务流水号 */
	private String partnerTradeNo;
	
	/** 产品代码 */
	private String productCode;
	
	/** 产品代码 */
	private String productName;
	
	/** 用户编号 */
	private String userId;
	
	/** 投资状态 */
	private String status;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Long getInvestRecordNo() {
		return investRecordNo;
	}

	public void setInvestRecordNo(Long investRecordNo) {
		this.investRecordNo = investRecordNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
