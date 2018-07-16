package com.xiaoniu.account.domain.result;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.xiaoniu.account.utils.DateUtils;
import com.xiaoniu.account.utils.enums.InRecordStatus;



public class InRecordBackRlt implements Serializable {
	
	
	private static final long serialVersionUID = -5387846735155523861L;

	/** 充值流水号 */
	private Long inRecordNo;
	
	/** 业务ID */
	private String partnerId;
	
	/** 业务流水号 */
	private String partnerTradeNo;
	
	/** 用户ID */
	private String userId;
	
	/** 用户名 */
	private String userName;
	
	/** 身份证号 */
	private String identityCard;
	
	/** 充值类型 see t_trans_type*/
	private Integer bisType;
	
	/** 名称  */
	private String bisName;
	
	/** 充值时间 */
	private Date bisTime;
	
	/** 通知时间 */
	private Date noticeTime;
	
	/** 充值金额 */
	private Long amount;
	
	/** 充值状态  {@link InRecordStatus}*/
	private String status;
	
	/** 支付方式 */
	private String payMethod;
	
	/** 支付流水号 */
	private String payNo;
	
	/** 红包ID */
	private String redPaperId;
	
	/** 银行编号 */
	private String bankCode;
	
	/** 媒体来源	 */
	private String mediaSource;
	
	/** 客户IP */
	private String clientIp;
	
	private String remark;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getInRecordNo() {
		return String.valueOf(inRecordNo);
	}

	public void setInRecordNo(Long inRecordNo) {
		this.inRecordNo = inRecordNo;
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

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
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

	public Integer getBisType() {
		return bisType;
	}

	public void setBisType(Integer bisType) {
		this.bisType = bisType;
	}

	public String getBisTime() {
		return DateUtils.format(bisTime, DateUtils.FORMAT_LONG);
	}

	public void setBisTime(Date bisTime) {
		this.bisTime = bisTime;
	}

	public String getBisName() {
		return bisName;
	}

	public void setBisName(String bisName) {
		this.bisName = bisName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getRedPaperId() {
		return redPaperId;
	}

	public void setRedPaperId(String redPaperId) {
		this.redPaperId = redPaperId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
