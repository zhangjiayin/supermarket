package com.linkwee.api.response.activity;

import com.linkwee.core.base.BaseEntity;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.model.activity.HelpRaiseRateResp;
import com.linkwee.xoss.util.WebUtil;

/**
 * 
 * @描述：助力加息
 *
 */
public class CfpInfoRespspne extends BaseEntity {
	private static final long serialVersionUID = 3405963387464644389L;

	public CfpInfoRespspne() {

	}

	public CfpInfoRespspne(HelpRaiseRateResp obj) {
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
	 * 平台
	 */
	private String platform;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 头像
	 */
	private String headImage;

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
