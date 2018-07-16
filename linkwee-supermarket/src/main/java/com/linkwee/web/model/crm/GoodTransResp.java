package com.linkwee.web.model.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：V4.1.1出单喜报
 */
public class GoodTransResp extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	/**
	 * 	出单金额
	 */
	private String amount;

	/**
	 * 	投资订单ID
	 */
	private String billId;
	
	/**
	 * 	出单时间
	 */
	private String investTime;
	
	/**
	 * 	出单人
	 */
	private String userName;
	
	/**
	 * 	手机号码
	 */
	private String mobile;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getInvestTime() {
		return investTime;
	}

	public void setInvestTime(String investTime) {
		this.investTime = investTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
