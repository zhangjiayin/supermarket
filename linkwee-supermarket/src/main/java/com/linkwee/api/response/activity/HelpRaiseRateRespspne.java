package com.linkwee.api.response.activity;

import java.util.List;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.activity.HelpRaiseRateResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：助力加息
 *
 */
public class HelpRaiseRateRespspne extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public HelpRaiseRateRespspne() {

	}

	public HelpRaiseRateRespspne(HelpRaiseRateResp obj) {
		WebUtil.initObj(this,obj,DateUtils.FORMAT_LONG);
		if(obj != null) {
			this.setUserName(obj.getUserName());
		}
	}
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 已加息
	 */
	private String raisedRate;
	
	/**
	 * 助力明细
	 */
	private List<HelpRaiseRateDetailRespspne> helpDetailList;

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

	public String getRaisedRate() {
		return raisedRate;
	}

	public void setRaisedRate(String raisedRate) {
		this.raisedRate = raisedRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<HelpRaiseRateDetailRespspne> getHelpDetailList() {
		return helpDetailList;
	}

	public void setHelpDetailList(List<HelpRaiseRateDetailRespspne> helpDetailList) {
		this.helpDetailList = helpDetailList;
	}


	
}
