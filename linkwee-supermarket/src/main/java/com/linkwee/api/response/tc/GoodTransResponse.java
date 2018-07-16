package com.linkwee.api.response.tc;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.crm.GoodTransResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * @描述：V4.1.1出单喜报
 */
public class GoodTransResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;
	
	public GoodTransResponse() {}
	
	public GoodTransResponse(GoodTransResp obj) {
		WebUtil.initObj(this,obj,DateUtils.FORMAT_LONG);
		this.setUserName(obj.getUserName()!=null?writeName(obj.getUserName()):obj.getUserName());
		this.setMobile(obj.getMobile()!=null?obj.getMobile().substring(0, 3)+"****"+obj.getMobile().substring(obj.getMobile().length()-4, obj.getMobile().length()):null);
	    this.setAmount(obj.getAmount());
	    this.setInvestTime(obj.getInvestTime()!=null?obj.getInvestTime().substring(0, 4)+"年"+obj.getInvestTime().substring(5, 7)+"月"+obj.getInvestTime().substring(8,10)+"日":null);
	    this.setBillId(obj.getBillId());
	}

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
	

	public String writeName(String name){
		String wname = "";
		wname = name.substring(0,1);
		for(int i = 0;i<name.length()-1;i++){
			wname = wname+"*";
		}
		return wname;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public void setInvestTime(String investTime){
		this.investTime = investTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
