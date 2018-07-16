package com.xiaoniu.account.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiaoniu.account.domain.base.PageBackReqBase;
import com.xiaoniu.account.utils.enums.InRecordStatus;

/**
 * 
 * @Description: 查询充值记录
 * @author 周锋恒
 * @date 2015年8月12日
 *
 */
public class QueryInRecordBackReq extends PageBackReqBase implements Serializable {

	private static final long serialVersionUID = 12178115888236383L;

	/**  充值流水号 */
	private Long inRecordNo;
	
	/** 业务编号  */
	private String partnerId;
	
	/** 用户编号 */
	private String userId;
	
	/** 充值类型 */
	private String bisType;
	
	/** 用户名称 */
	private String userName;
	
	/** 支付流水号 */
	private String payNo;
	
	/** 充值状态  {@link InRecordStatus}*/
	private String status;
	
	
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

	public String getUserId() {
		return userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBisType() {
		return bisType;
	}


	public void setBisType(String bisType) {
		this.bisType = bisType;
	}


	public Long getInRecordNo() {
		return inRecordNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}


	public void setInRecordNo(Long inRecordNo) {
		this.inRecordNo = inRecordNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
