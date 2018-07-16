package com.xiaoniu.account.domain.result;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DebtTransferRlt implements Serializable {

	private static final long serialVersionUID = -4427702597639470903L;
	
	/** 转让记录 */
	private String transferRecordNo;
	
	/** 接手人投资记录 */
	private String acceptInvestRecordNo;
	
	/** 转让人回款记录 */
	private String transferReturnRecordNo;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getTransferRecordNo() {
		return transferRecordNo;
	}

	public void setTransferRecordNo(String transferRecordNo) {
		this.transferRecordNo = transferRecordNo;
	}

	public String getAcceptInvestRecordNo() {
		return acceptInvestRecordNo;
	}

	public void setAcceptInvestRecordNo(String acceptInvestRecordNo) {
		this.acceptInvestRecordNo = acceptInvestRecordNo;
	}

	public String getTransferReturnRecordNo() {
		return transferReturnRecordNo;
	}

	public void setTransferReturnRecordNo(String transferReturnRecordNo) {
		this.transferReturnRecordNo = transferReturnRecordNo;
	}

}
