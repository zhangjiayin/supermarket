package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;

/**
 * @描述：v4.0邀请记录-统计数量
 */
public class InvitationNumResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public InvitationNumResponse() {

	}

	/**
	 * 	推荐理财师数量
	 */
	private int cfpNum;
	
	/**
	 * 	邀请客户数量
	 */
	private int investorNum;

	public int getCfpNum() {
		return cfpNum;
	}

	public void setCfpNum(int cfpNum) {
		this.cfpNum = cfpNum;
	}

	public int getInvestorNum() {
		return investorNum;
	}

	public void setInvestorNum(int investorNum) {
		this.investorNum = investorNum;
	}

}
