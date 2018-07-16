package com.linkwee.web.model.activity;

import com.linkwee.core.base.BaseEntity;

/**
 * 
 * @描述：助力加息
 *
 * @author Bob
 * @时间 2015年10月16日上午11:16:39
 *
 */
public class HelpRaiseRateResp extends BaseEntity {
	private static final long serialVersionUID = 9117301712001026112L;

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
	private Double raisedRate;
	

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

	public Double getRaisedRate() {
		return raisedRate;
	}

	public void setRaisedRate(Double raisedRate) {
		this.raisedRate = raisedRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
