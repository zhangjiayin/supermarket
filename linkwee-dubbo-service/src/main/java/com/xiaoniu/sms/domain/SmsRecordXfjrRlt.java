package com.xiaoniu.sms.domain;

/**
 * 消费金融发送短信返回结果
 * @author 颜彩云
 *
 */
public class SmsRecordXfjrRlt extends SmsRecordRlt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String smsType;
	private String smsCategory;
	private String smsNumber;
	private String contract;
	private String customerName;
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	public String getSmsCategory() {
		return smsCategory;
	}
	public void setSmsCategory(String smsCategory) {
		this.smsCategory = smsCategory;
	}
	public String getSmsNumber() {
		return smsNumber;
	}
	public void setSmsNumber(String smsNumber) {
		this.smsNumber = smsNumber;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
