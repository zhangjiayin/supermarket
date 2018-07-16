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
public class QueryReturnRecordReq extends PageBackReqBase implements Serializable {

	private static final long serialVersionUID = 12178115888236583L;

	/** 业务编号  */
	private String partnerId;
	
	/** 用户编号 */
	private String userId;
	
	/**  投资流水号 */
	private Long investRecordNo;
	
	/**  回款流水号 */
	private Long returnRecordNo;
	
	/** 产品代码  */
	private String productCode;
	
	/** 产品代码  */
	private String productName;
	
	/** 回款状态 */
	private String status;
	

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public Long getReturnRecordNo() {
		return returnRecordNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setReturnRecordNo(Long returnRecordNo) {
		this.returnRecordNo = returnRecordNo;
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
