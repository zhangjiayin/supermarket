package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.CrmInvestor;
import com.linkwee.xoss.util.WebUtil;

/**
 * @描述：用户信息
 */
public class InvestorUserInfoResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public InvestorUserInfoResponse() {

	}

	public InvestorUserInfoResponse(CrmInvestor obj) {
		WebUtil.initObj(this,obj,DateUtils.FORMAT_LONG);
		this.setUserName(obj.getUserName());
		this.setMobile(obj.getMobile());
		this.setQrcode(obj.getQrcode());
	}

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 二维码
	 */
	private String qrcode;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
