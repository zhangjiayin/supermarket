package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiaoniu.account.utils.DateUtils;

/**
 * 提现记录信息
 * @author zhoufengheng
 *
 */
public class OutRecord2Rlt implements Serializable {


	private static final long serialVersionUID = 7851878717055470984L;
	
	/** 提现流水号 */
	private Long outRecordNo;
	
	/** 业务ID */
	private String partnerId;
	
	/** 用户ID */
	private String userId;
	
	/** 用户名称 */
	private String userName;
	
	/** 名称 */
	private String bisName;
	
	/** 提现时间 */
	private Date bisTime;
	
	/** 交易总金额，值等于 提现金额+手续费 */
	private Long totalAmount;
	
	/** 提值金额 */
	private Long amount;
	
	/** 手续费 */
	private Long fee;
	 
	/** 提值状态 @see OutRecordStatus*/
	private String status;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getOutRecordNo() {
		return String.valueOf(outRecordNo);
	}

	public void setOutRecordNo(Long outRecordNo) {
		this.outRecordNo = outRecordNo;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public String getBisTime() {
		return DateUtils.format(bisTime, DateUtils.FORMAT_LONG);
	}

	public void setBisTime(Date bisTime) {
		this.bisTime = bisTime;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
