package com.xiaoniu.account.domain.result;

import java.io.Serializable;

/**
 * 
 * @author 颜彩云
 *
 */
public class TransTypeBackRlt implements Serializable {

	private static final long serialVersionUID = -1901537490283470862L;

	/** 业务id */
	private String partnerId;
	
	/** 交易类型 @see TransTypeEnum */
	private Integer transType;
	/**
	 * 交易类型名称
	 */
	private String transName;
	
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
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}

	
}
