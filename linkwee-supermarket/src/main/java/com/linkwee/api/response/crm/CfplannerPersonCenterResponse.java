package com.linkwee.api.response.crm;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.CrmCfplanner;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：v4.0个人中心
 *
 * @author chenjl
 * @时间 2017年06月22日
 *
 */
public class CfplannerPersonCenterResponse extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public CfplannerPersonCenterResponse() {

	}

	public CfplannerPersonCenterResponse(CrmCfplanner obj) {
		WebUtil.initObj(this,obj,DateUtils.FORMAT_LONG);
		this.setAuthenName(obj.getUserName()!=null?"*"+obj.getUserName().substring(1,obj.getUserName().length()):null);
		this.setBankCard(obj.getBankCard()!=null?"**** **** **** "+obj.getBankCard().substring(obj.getBankCard().length()-4):null);
		this.setHeadImage(obj.getHeadImage());
		this.setMobile(obj.getMobile()!=null?
				obj.getMobile().substring(0, 3)+"****"+obj.getMobile().substring(obj.getMobile().length()-4, obj.getMobile().length())
				:null);
	}

	/**
	 * 实名认证
	 */
	private String authenName;
	
	/**
	 * 	银行卡
	 */
	private String bankCard;
	
	/**
	 * 头像
	 */
	private String headImage;

	/**
	 * 手机号码
	 */
	private String mobile;

	public String getAuthenName() {
		return authenName;
	}

	public void setAuthenName(String authenName) {
		this.authenName = authenName;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
